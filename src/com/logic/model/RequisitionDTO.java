package com.logic.model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RequisitionDTO implements Serializable {

	private UserDTO userDTO;
	private ArrayList<ArticleDTO> articles;
	private String fileName;
	private String user;
	private String dateValue;
	private String timeValue;
	private String date;
	private String time;
	public final static String REQUISITION_SERIALIZABLE = "RequisitionDTO";
	private String cod_art;
	private String description;
	private String provider;
	private String cod_art_prov;
	private String quantity;
	private String articleQuantity;
	private String articleTotal;
	
	public ArrayList<ArticleDTO> getArticles() {
		return articles;
	}
	public void setArticles(ArrayList<ArticleDTO> articles) {
		this.articles = articles;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDateValue() {
		return dateValue;
	}
	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}
	public String getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}
	public String getCod_art() {
		return cod_art;
	}
	public void setCod_art(String cod_art) {
		this.cod_art = cod_art;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getCod_art_prov() {
		return cod_art_prov;
	}
	public void setCod_art_prov(String cod_art_prov) {
		this.cod_art_prov = cod_art_prov;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getArticleQuantity() {
		return articleQuantity;
	}
	public void setArticleQuantity(String articleQuantity) {
		this.articleQuantity = articleQuantity;
	}
	public String getArticleTotal() {
		return articleTotal;
	}
	public void setArticleTotal(String articleTotal) {
		this.articleTotal = articleTotal;
	}		
}
