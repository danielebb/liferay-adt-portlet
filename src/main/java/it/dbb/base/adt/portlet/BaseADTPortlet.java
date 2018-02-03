package it.dbb.base.adt.portlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.util.tracker.ServiceTracker;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;
import com.liferay.portlet.display.template.PortletDisplayTemplateUtil;

import it.dbb.base.adt.portlet.configuration.ADTConfigurationContext;
import it.dbb.base.adt.portlet.configuration.ADTDisplayContext;
import it.dbb.base.adt.portlet.template.BaseADTPortletDisplayTemplateHandler;

/**
 * @author danielebb
 */
public abstract class BaseADTPortlet extends MVCPortlet implements ADTConfigurationContext {

	@Override
	protected void include(String path, PortletRequest portletRequest, PortletResponse portletResponse,
			String lifecycle) throws IOException, PortletException {

		ADTDisplayContext displayContext = new ADTDisplayContext(portletRequest, portletResponse,
				portletRequest.getPreferences());

		if (RenderRequest.RENDER_PHASE.equals(lifecycle)) {

			if (displayContext.isADTEnabled()) {

				DDMTemplate portletDisplayDDMTemplate = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplate(
						displayContext.getDisplayStyleGroupId(), PortalUtil.getClassNameId(getPortletName()),
						displayContext.getDisplayStyle(), true);

				if (portletDisplayDDMTemplate != null) {

					HttpServletRequest request = PortalUtil.getHttpServletRequest(portletRequest);
					HttpServletResponse response = PortalUtil.getHttpServletResponse(portletResponse);

					try {

						List<?> entries = getEntries();
						
						if(entries == null) {
							
							entries = Collections.emptyList();
						}
						
						Map<String, Object> contextObjects = new HashMap<>();

						populateContextObjects(contextObjects);

						String renderedDDMTemplate = PortletDisplayTemplateUtil.renderDDMTemplate(request, response,
								portletDisplayDDMTemplate.getTemplateId(), entries, contextObjects);

						Writer writer = getResponseWriter(portletResponse);

						writer.write(renderedDDMTemplate);

						if (clearRequestParameters) {

							portletResponse.setProperty("clear-request-parameters", "true");
						}

						return;

					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
		}

		super.include(path, portletRequest, portletResponse, lifecycle);
	}

	protected Writer getResponseWriter(PortletResponse portletResponse) throws IOException {

		Writer writer = null;

		if (portletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse) portletResponse;

			writer = UnsyncPrintWriterPool.borrow(mimeResponse.getWriter());

		} else {

			writer = new UnsyncStringWriter();
		}

		return writer;
	}

	@Activate
	protected void activate(BundleContext bundleContext, Map<String, Object> properties) throws InvalidSyntaxException {

		String portletName = GetterUtil.getString(properties.get("javax.portlet.name"));

		registerDisplayTemplateHandler(bundleContext, portletName);
	}

	protected void registerDisplayTemplateHandler(BundleContext bundleContext, String portletName)
			throws InvalidSyntaxException {

		// register a new templateHandler based on the configurationContext, if none
		// exists
		Filter filter = bundleContext.createFilter(
				"(&(objectclass=" + TemplateHandler.class.getName() + ")(javax.portlet.name=" + portletName + "))");

		ServiceTracker serviceTracker = new ServiceTracker<>(bundleContext, filter, null);

		serviceTracker.open();

		if (serviceTracker.isEmpty()) {

			// create a new templateHandler
			BaseADTPortletDisplayTemplateHandler templateHandler = new BaseADTPortletDisplayTemplateHandler() {

				@Override
				public String getResourceName() {

					return getClassName();
				}
			};

			Hashtable<String, Object> properties = new Hashtable<String, Object>();

			properties.put("javax.portlet.name", portletName);

			bundleContext.registerService(TemplateHandler.class, templateHandler, properties);
		}

		serviceTracker.close();

	}
}