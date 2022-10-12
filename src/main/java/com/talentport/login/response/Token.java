package com.talentport.login.response;

public class Token {

    private String AccessToken;
    private int ExpiresIn;
    private String TokenType;
    private String idCognitoUser;
    private String RefreshToken;


    public String getRefreshToken() {
		return RefreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}

	public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public int getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        ExpiresIn = expiresIn;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }

	public String getIdCognitoUser() {
		return idCognitoUser;
	}

	public void setIdCognitoUser(String idCognitoUser) {
		this.idCognitoUser = idCognitoUser;
	}
    
    
}
