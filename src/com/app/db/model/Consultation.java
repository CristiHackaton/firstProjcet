package com.app.db.model;

import java.util.Date;

public class Consultation {
	private Date consultationDate; 
	private Date duration;
	private int id;
	private int patientId;
	private int doctorId;
	private String notes;
	

public Consultation(){
	
}


public Date getConsultationDate() {
	return consultationDate;
}


public void setConsultationDate(Date consultationDate) {
	this.consultationDate = consultationDate;
}


public Date getDuration() {
	return duration;
}


public void setDuration(Date duration) {
	this.duration = duration;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public int getPatientId() {
	return patientId;
}


public void setPatientId(int patientId) {
	this.patientId = patientId;
}


public int getDoctorId() {
	return doctorId;
}


public void setDoctorId(int doctorId) {
	this.doctorId = doctorId;
}


public String getNotes() {
	return notes;
}


public void setNotes(String notes) {
	this.notes = notes;
}
}