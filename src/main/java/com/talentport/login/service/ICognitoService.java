package com.talentport.login.service;

import com.talentport.login.response.Token;

public interface ICognitoService {

    public Token getToken(String username, String password) throws Exception;
}
