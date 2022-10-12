package com.talentport.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentport.login.cognito.CognitoClient;
import com.talentport.login.response.Token;

@Service
public class CognitoService implements ICognitoService {

    @Autowired
    private CognitoClient cognitoClient;

    @Override
    public Token getToken(String username, String password) throws Exception {
        return cognitoClient.login(username, password);
    }

}
