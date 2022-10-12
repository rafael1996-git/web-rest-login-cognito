package com.talentport.login.dto;

import java.io.Serializable;
import java.util.Set;

public class RequestDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	
	private String device; 
	

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
