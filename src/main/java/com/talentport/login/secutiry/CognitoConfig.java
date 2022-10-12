package com.talentport.login.secutiry;

import java.io.Serializable;

public class CognitoConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String clientId;
	private String userPoolId;
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getUserPoolId() {
		return userPoolId;
	}
	public void setUserPoolId(String userPoolId) {
		this.userPoolId = userPoolId;
	}
	
	
	
	@Override
	public String toString() {
		return "CognitoConfig [clientId=" + clientId + ", userPoolId=" + userPoolId + "]";
	}
	
	
	
	
	
}
