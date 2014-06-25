package com.preference;

public interface IPreference {

	public static final String INITIALIZED = "initialize";
	public static final boolean INIT_VALUE = true;
	
	public boolean getInitial();
	public void setInitial(boolean initial);
}
