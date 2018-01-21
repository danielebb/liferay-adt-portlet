package it.dbb.base.adt.portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD
public interface BaseADTConfiguration {

	@Meta.AD(required = false)
	boolean adtEnabled();
	
	@Meta.AD(required = false)
	String displayStyle();
	
	@Meta.AD(required = false)
	long displayStyleGroupId();
}
