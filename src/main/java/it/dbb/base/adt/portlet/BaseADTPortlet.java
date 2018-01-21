package it.dbb.base.adt.portlet;

import java.io.IOException;
import java.io.Writer;

import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;
import com.liferay.portlet.display.template.PortletDisplayTemplateUtil;

import it.dbb.base.adt.portlet.configuration.ADTDisplayContext;

/**
 * @author danielebb
 */
public class BaseADTPortlet extends MVCPortlet {

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

						String renderedDDMTemplate = PortletDisplayTemplateUtil.renderDDMTemplate(request, response,
								portletDisplayDDMTemplate.getTemplateId(), null, /* contextObjects */ null);

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
}