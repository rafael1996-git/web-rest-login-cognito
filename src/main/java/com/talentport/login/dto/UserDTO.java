package com.talentport.login.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
    private String email;
    private String name;
    private String lastname;
    private String phone;
    private String role;
    private String[] roles;
    private int status;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonIgnore
	@JsonProperty(value = "role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String[] getRoles()
	{	
		if(this.getRole().trim().length() == 0)
		{
			return  new String[] {};
		}
		
		return this.getRole().split(",");
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	    
	
}
