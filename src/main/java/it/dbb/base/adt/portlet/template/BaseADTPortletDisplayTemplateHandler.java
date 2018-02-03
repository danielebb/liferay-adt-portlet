package it.dbb.base.adt.portlet.template;

import java.util.Locale;
import java.util.ResourceBundle;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public abstract class BaseADTPortletDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {

	@Override
	public String getName(Locale locale) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", locale, getClass());

		String name = LanguageUtil.get(locale, "template");
		
		String portletTitle = PortalUtil.getPortletTitle(getClassName(), resourceBundle);
		
		if(Validator.isNotNull(portletTitle)) {
			
			name = portletTitle.concat(StringPool.SPACE).concat(name);
		}

		return name;
	}
	
	@Override
	public String getClassName() {
		
		return getResourceName();
	}
}
