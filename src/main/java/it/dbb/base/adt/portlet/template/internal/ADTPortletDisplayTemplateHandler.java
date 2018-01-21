package it.dbb.base.adt.portlet.template.internal;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.template.TemplateHandler;

import it.dbb.base.adt.constants.ADTPortletKeys;
import it.dbb.base.adt.portlet.template.BaseADTPortletDisplayTemplateHandler;

@Component(immediate = true, property = { "javax.portlet.name=" + ADTPortletKeys.ADT }, service = TemplateHandler.class)
public class ADTPortletDisplayTemplateHandler extends BaseADTPortletDisplayTemplateHandler {

	@Override
	public String getPortletName() {

		return ADTPortletKeys.ADT;
	}

}
