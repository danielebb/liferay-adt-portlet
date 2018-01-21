<%@include file="init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" varImpl="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm" >

	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL.toString() %>" />

	<div class="container-fluid-1280">
		<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="display-settings" />

		<aui:fieldset-group markupView="lexicon">
		
			<aui:fieldset cssClass="general-display-settings">

				<aui:input helpMessage="enable-adt-button-help" name="preferences--adtEnabled--" type="toggle-switch" value="<%= displayContext.isADTEnabled() %>" />
		
				<div class="display-template">
					<liferay-ddm:template-selector
						className="<%= className %>"
						defaultDisplayStyle="<%= ADTPortletKeys.DEFAULT_DISPLAY_STYLE %>"
						displayStyle="<%= displayContext.getDisplayStyle() %>"
						displayStyleGroupId="<%= displayContext.getDisplayStyleGroupId() %>"
						displayStyles="<%= Arrays.asList(new String[] { ADTPortletKeys.DEFAULT_DISPLAY_STYLE }) %>"
						label="display-template"
						refreshURL="<%= configurationRenderURL.toString() %>"
					/>
				</div>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
	
	<aui:button type="submit" value="save" />

</aui:form>	