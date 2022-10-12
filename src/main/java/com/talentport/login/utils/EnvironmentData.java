package com.talentport.login.utils;

public class EnvironmentData {
	 public static String getPropertyValue(String propertyName) throws Exception {
	        String property = System.getenv(propertyName);
	        if (property == null)
	            throw new Exception(new StringBuilder("Missing Environment variable ").append(propertyName).append(". please define before using").toString());
	        return property;
	    }
}
