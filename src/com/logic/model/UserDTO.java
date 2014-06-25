package com.logic.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {

	private int id_user;
	private String user;
	private String password;
	public final static String USER_SERIALIZABLE = "UserDTO";
	private String email;
	
	public int getId_user() {
		return this.id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}			
}
