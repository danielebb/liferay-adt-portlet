<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/flags" prefix="liferay-flags" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="it.dbb.base.adt.portlet.configuration.ADTDisplayContext"%>
<%@page import="java.util.Arrays"%>
<%@page import="it.dbb.base.adt.constants.ADTPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String className = (String)request.getAttribute("className");

ADTDisplayContext displayContext = new ADTDisplayContext(renderRequest, renderResponse, portletPreferences); 
%>