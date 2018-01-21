package it.dbb.base.adt.portlet.internal;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import it.dbb.base.adt.constants.ADTPortletKeys;
import it.dbb.base.adt.portlet.BaseADTPortlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=liferay-adt-portlet Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ADTPortletKeys.ADT, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class, configurationPid = "it.dbb.base.adt.portlet.configuration.internal.ADTConfiguration")
public class ADTPortlet extends BaseADTPortlet {

	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(PortalUtil.getClassNameId(ADTPortletKeys.ADT));
		
		templateHandler.getResourceName();
		
		super.doView(renderRequest, renderResponse);
	}
}
