package com.talentport.login.service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.talentport.login.dao.LoginDao;
import com.talentport.login.dto.RolDTO;
import com.talentport.login.dto.UserDTO;
import com.talentport.login.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public UserDTO getUserById(String userId) throws Exception {
		return loginDao.getUserById(userId);
	}
	
	
}
