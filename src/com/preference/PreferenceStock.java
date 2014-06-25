package com.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceStock implements IPreference {
	
	public final static String PREFERENCE_NAME = "stock_preference";
	public final static String KEY_ORDER = "order";
	public final static String KEY_FILTER = "filter";	
	
	public final static int VALUE_FILTER_COD_ARTICLE = 0;
	public final static int VALUE_FILTER_COD_ARTICLE_PROVIDER = 1;
	public final static int VALUE_FILTER_CATEGORY = 2;	
	public final static int VALUE_FILTER_DESCRIPTION = 3;
	public final static int VALUE_FILTER_PROVIDER = 4;
	
	public final static int VALUE_ORDER_COD_ARTICLE = 0;
	public final static int VALUE_ORDER_COD_ARTICLE_PROVIDER = 1;
	public final static int VALUE_ORDER_CATEGORY = 2;	
	public final static int VALUE_ORDER_PROVIDER = 3;
	
	private int customFilter;				
	private int customOrder;
	private boolean initial;
	
	public boolean getInitial() {		
		return this.initial;
	}

	public void setInitial(boolean initial) {
		this.initial = initial;
	}

	public int getCustomFilter() {
		return this.customFilter;
	}

	public void setCustomFilter(int customFilter) {
		this.customFilter = customFilter;
	}

	public int getCustomOrder() {
		return this.customOrder;
	}

	public void setCustomOrder(int customOrder) {
		this.customOrder = customOrder;
	}
	
	public void loadPreference(Context context) {
		SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
		int preference = sp.getInt(KEY_ORDER,VALUE_ORDER_COD_ARTICLE);
		this.setCustomOrder(preference);
		preference= sp.getInt(KEY_FILTER,VALUE_FILTER_COD_ARTICLE);
		this.setCustomFilter(preference);		
	}
	
	public void savePreference(Context context) {
		SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(KEY_ORDER,this.getCustomOrder());
		editor.putInt(KEY_FILTER,this.getCustomFilter());
		editor.commit();		
	}
	
	public void firstTime(Context context) {
		this.setInitial(INIT_VALUE);
		this.setCustomFilter(VALUE_FILTER_COD_ARTICLE);
		this.setCustomOrder(VALUE_ORDER_COD_ARTICLE);
		
		SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(INITIALIZED,this.getInitial());
		editor.putInt(KEY_FILTER,this.getCustomFilter());
		editor.putInt(KEY_ORDER,this.getCustomOrder());
		editor.commit();
	}
	
	public boolean isInitialized(Context context) {
		SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
		return  sp.getBoolean(INITIALIZED,false);		
	}
}
