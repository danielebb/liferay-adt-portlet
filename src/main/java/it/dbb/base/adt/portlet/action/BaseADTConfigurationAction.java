package it.dbb.base.adt.portlet.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

public abstract class BaseADTConfigurationAction extends DefaultConfigurationAction implements ConfigurationAction {

	public abstract String getClassName();
	
	@Override
	public String getJspPath(HttpServletRequest request) {

		return "/configuration.jsp";
	}
	
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("className", getClassName());
		
		super.include(portletConfig, request, response);
	}

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.UPDATE)) {

			super.processAction(portletConfig, actionRequest, actionResponse);
		}
	}
}
