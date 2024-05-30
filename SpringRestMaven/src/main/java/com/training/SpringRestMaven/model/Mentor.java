package com.training.SpringRestMaven.model;

public class Mentor {
	private long id;
	private String name;
	private String contact;
	private String email;
	private int activeClasses;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActiveClasses() {
		return activeClasses;
	}
	public void setActiveClasses(int activeClasses) {
		this.activeClasses = activeClasses;
	}
	
}
