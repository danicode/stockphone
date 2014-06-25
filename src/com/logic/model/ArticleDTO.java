package com.logic.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ArticleDTO implements Serializable {

	private int id_article;
	private int id_provider;
	private int id_category;
	private String cod_article;
	private String providerName;
	private String cod_article_provider;
	private String categoryName;
	private int quantity;
	private String description;
	public final static String ARTICLE_SERIALIZABLE = "ArticleDTO";
	
	public int getId_article() {
		return id_article;
	}
	public void setId_article(int id_article) {
		this.id_article = id_article;
	}
	public String getCod_article() {
		return cod_article;
	}
	public void setCod_article(String cod_article) {
		this.cod_article = cod_article;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getCod_article_provider() {
		return cod_article_provider;
	}
	public void setCod_article_provider(String cod_article_provider) {
		this.cod_article_provider = cod_article_provider;
	}
	public int getId_provider() {
		return id_provider;
	}
	public void setId_provider(int id_provider) {
		this.id_provider = id_provider;
	}
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}			
	
	@Override
	public boolean equals(Object o) {
		ArticleDTO aux = (ArticleDTO) o;		
		return (this.getId_article() == aux.getId_article());
	}
	
	@Override
	public int hashCode() {
		return this.getId_article();
	}
}
