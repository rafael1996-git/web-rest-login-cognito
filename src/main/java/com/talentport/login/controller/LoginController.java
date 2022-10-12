package com.talentport.login.controller;

import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.talentport.login.dto.RequestDTO;
import com.talentport.login.dto.UserDTO;
import com.talentport.login.helpers.Helpers;
import com.talentport.login.response.Token;
import com.talentport.login.service.CognitoService;
import com.talentport.login.service.imp.LoginServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@RestController
@EnableWebMvc
@RequestMapping("/auth")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    @Autowired
    private CognitoService cognitoService;
    
    @Autowired
    private LoginServiceImp loginService;
    

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login( @RequestBody RequestDTO requestDTO )
    {
    	Map<String, Object> data = new HashMap<>();       
        
        Token token = null;
        UserDTO userDTO = new UserDTO();
        
        try {
            token = cognitoService.getToken(requestDTO.getEmail(), requestDTO.getPassword()); 
            userDTO = loginService.getUserById(token.getIdCognitoUser());
            
            String msg = "Login Success";
           
            
            if(null == userDTO.getId()) {
            	return Helpers.ResponseClass(500, null, "", "User not found in database");
            }
            
            if(userDTO.getStatus() == 0)
            {
            	msg = "The user's account has been deleted";
            }
            
            data.put("token", userDTO.getStatus() == 1 ? token.getAccessToken() : "");
            data.put("RefreshToken", userDTO.getStatus() == 1 ? token.getRefreshToken() : "");
            data.put("user", userDTO);
            
            return Helpers.ResponseClass(200, data, msg, "");
            
        } catch (InvalidParameterException e) {	
        	logger.error(e.toString());
            return Helpers.ResponseClass(500, null, "", "Internal server error");

        } catch (Exception e) {
        	logger.error(e.toString());
        	
        	if(e.toString().contains("NEW_PASSWORD_REQUIRED")){
                return Helpers.ResponseClass(400, null, "", "User has not confirmed his password");
            }
            if(e.toString().contains("NotAuthorizedException")){
            	if(e.toString().contains("User is disabled"))
            	{
            		return Helpers.ResponseClass(400, null, "", "User is disabled");
            	}
                return Helpers.ResponseClass(400, null, "", "Usuario y/o contraseña no válido");
            }
            return Helpers.ResponseClass(500, null, "", "Internal server error");
        }
    }
}
