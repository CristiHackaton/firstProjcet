package com.app.db.model;

public class User {
	public int userID;
	public String username;
	public String email;
	public int userType;
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
