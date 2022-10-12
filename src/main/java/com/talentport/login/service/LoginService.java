package com.talentport.login.service;

import com.talentport.login.dto.RolDTO;
import com.talentport.login.dto.UserDTO;

public interface LoginService {
	UserDTO getUserById(String userId) throws Exception;
}
