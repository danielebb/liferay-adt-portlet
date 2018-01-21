package it.dbb.base.adt.portlet.template;

import java.util.Locale;
import java.util.ResourceBundle;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;

public abstract class BaseADTPortletDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {

	public abstract String getPortletName();

	@Override
	public String getClassName() {

		//long classId = PortalUtil.getClassNameId(getPortletName());
		
		return getPortletName();
	}

	@Override
	public String getName(Locale locale) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", locale, getClass());

		String portletTitle = PortalUtil.getPortletTitle(getPortletName(), resourceBundle);

		return portletTitle.concat(StringPool.SPACE).concat(LanguageUtil.get(locale, "template"));
	}

	@Override
	public String getResourceName() {

		return getPortletName();
	}

}
