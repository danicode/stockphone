package com.logic.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoryDTO implements Serializable {

	private int id_category;
	private String name;
	public final static String CATEGORY_SERIALIZABLE = "CaegoryDTO";
	
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
