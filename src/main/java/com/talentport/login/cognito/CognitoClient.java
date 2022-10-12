package com.talentport.login.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.google.gson.Gson;
import com.talentport.login.response.Token;
import com.talentport.login.secutiry.CognitoConfig;
import com.talentport.login.utils.EnvironmentData;
import com.talentport.login.utils.SecretManagerAWSUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CognitoClient {
    public Token login(String username, String password) throws Exception
    {
    	String secretBD = SecretManagerAWSUtils.getParameter(EnvironmentData.getPropertyValue("CognitKey"));
    	Gson gson = new Gson();
		CognitoConfig cognitoConfig = gson.fromJson(secretBD, CognitoConfig.class);
        Token token = new Token();
        
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withRegion("us-east-1").build();
        
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", username);
        authParams.put("PASSWORD", password);

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH).withClientId(cognitoConfig.getClientId())
                .withUserPoolId(cognitoConfig.getUserPoolId()).withAuthParameters(authParams);


            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);

            AuthenticationResultType authenticationResult = null;

            if (result.getChallengeName() != null && !result.getChallengeName().isEmpty()) {

                if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
                	 throw new RuntimeException("User has not confirmed his password " + result.getChallengeName());
                } else {
                    throw new RuntimeException("User has other challenge " + result.getChallengeName());
                }
            } else {

                authenticationResult = result.getAuthenticationResult();
                token.setAccessToken(authenticationResult.getAccessToken());
                token.setExpiresIn(authenticationResult.getExpiresIn());
                token.setTokenType(authenticationResult.getTokenType());
                token.setRefreshToken(authenticationResult.getRefreshToken());
            }
            
            
			AdminGetUserRequest user = new AdminGetUserRequest().withUsername(username)
					.withUserPoolId(cognitoConfig.getUserPoolId());
			AdminGetUserResult userResult = cognitoClient.adminGetUser(user);

			token.setIdCognitoUser(userResult.getUsername());

        cognitoClient.shutdown();
        return token;
    }

}
