package com.talentport.login.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Helpers {

	public static ResponseEntity ResponseClass(int codeError, Map<String, Object> object, String msgSucces, String msgError) {
	        Map<String, Object> response = new HashMap();
	        response.put("statusCode", codeError);
	        response.put("Data", object);
	        response.put("Message",  msgSucces);
	        response.put("Error",  msgError);
	        return new ResponseEntity<Map<String, Object>>(response, codeError == 500 ? HttpStatus.INTERNAL_SERVER_ERROR :
	        	codeError == 200? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	 }
	
	
	public static boolean containRoles(String device, String[] roles) {
		boolean found = false;
		String[] inRole = null;
		if(device.equals("IOS") || device.equals("ANDROID"))
		{
			inRole = new String[]{"ROLE_MOBILE"};
		}
		if(device.equals("WEB"))
		{
			inRole = new String[]{"ROLE_ADMIN", "ROLE_SUPERADMIN"};
		}
		for(String x : inRole){
			for(String y : roles){
				if(x.equals(y))
				{
					found = true;
					break;
				}	
			}
		}
		return found;
	}
	
}
