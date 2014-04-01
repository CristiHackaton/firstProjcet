package com.app.db.model;

import java.util.Date;

public class Pacient {
	private int id; 
	private String name;
	private String cnp;
	private String identitiCard;
	private Date birth; 
	private String address;

public Pacient(){
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCnp() {
	return cnp;
}

public void setCnp(String cnp) {
	this.cnp = cnp;
}

public String getIdentitiCard() {
	return identitiCard;
}

public void setIdentitiCard(String identitiCard) {
	this.identitiCard = identitiCard;
}

public Date getBirth() {
	return birth;
}

public void setBirth(Date birth) {
	this.birth = birth;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}
}