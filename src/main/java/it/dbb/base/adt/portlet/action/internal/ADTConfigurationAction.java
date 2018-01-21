package it.dbb.base.adt.portlet.action.internal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.portlet.ConfigurationAction;

import it.dbb.base.adt.constants.ADTPortletKeys;
import it.dbb.base.adt.portlet.action.BaseADTConfigurationAction;

@Component(
		configurationPid = "com.liferay.docs.exampleconfig.configuration.ExampleConfiguration",
	    configurationPolicy = ConfigurationPolicy.OPTIONAL,
	    immediate = true,
		property = {
				"javax.portlet.name="+ADTPortletKeys.ADT
	},service = ConfigurationAction.class)
public class ADTConfigurationAction extends BaseADTConfigurationAction {

}
