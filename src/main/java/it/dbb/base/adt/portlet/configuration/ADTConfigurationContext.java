package it.dbb.base.adt.portlet.configuration;

import java.util.List;
import java.util.Map;

public interface ADTConfigurationContext {

	public <T> List<T> getEntries();
	
	public String getClassName();
	
	public void populateContextObjects(Map<String, Object> contextObjects);
}
