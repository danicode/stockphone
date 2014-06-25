package com.logic.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProviderDTO implements Serializable {

	private int id_provider;
	private String name;	
	private String cod_article_provider;
	public final static String PROVIDER_SERIALIZABLE = "ProviderDTO";
	
	public int getId_provider() {
		return id_provider;
	}
	public void setId_provider(int id_provider) {
		this.id_provider = id_provider;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getCod_article_provider() {
		return cod_article_provider;
	}
	public void setCod_article_provider(String cod_article_provider) {
		this.cod_article_provider = cod_article_provider;
	}	
}
