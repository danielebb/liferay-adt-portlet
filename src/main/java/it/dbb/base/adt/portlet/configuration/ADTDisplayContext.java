package it.dbb.base.adt.portlet.configuration;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import it.dbb.base.adt.constants.ADTPortletKeys;

public class ADTDisplayContext {

	private HttpServletRequest _request;
	private PortletRequest _portletRequest;
	private PortletResponse _portletResponse;
	private PortletPreferences _portletPreferences;
	private String _displayStyle;
	private Long _displayStyleGroupId;
	private Boolean _adtEnabled;

	public ADTDisplayContext(PortletRequest portletRequest, PortletResponse portletResponse,
			PortletPreferences portletPreferences) {

		_request = PortalUtil.getHttpServletRequest(portletRequest);
		_portletRequest = portletRequest;
		_portletResponse = portletResponse;

		_portletPreferences = portletPreferences;
	}

	public boolean isADTEnabled() {

		if (_adtEnabled == null) {

			_adtEnabled = GetterUtil.getBoolean(_portletPreferences.getValue("adtEnabled", null), false);
		}

		return _adtEnabled;
	}

	public String getDisplayStyle() {

		if (_displayStyle == null) {

			_displayStyle = GetterUtil
					.getString(_portletPreferences.getValue("displayStyle", ADTPortletKeys.DEFAULT_DISPLAY_STYLE));
		}

		return _displayStyle;
	}

	public long getDisplayStyleGroupId() {

		if (_displayStyleGroupId == null) {

			ThemeDisplay themeDisplay = (ThemeDisplay) _request.getAttribute(WebKeys.THEME_DISPLAY);

			_displayStyleGroupId = GetterUtil.getLong(_portletPreferences.getValue("displayStyleGroupId", null),
					themeDisplay.getScopeGroupId());
		}

		return _displayStyleGroupId;
	}

	public PortletRequest getPortletRequest() {

		return _portletRequest;
	}

	public PortletResponse getPortletResponse() {

		return _portletResponse;
	}
}
