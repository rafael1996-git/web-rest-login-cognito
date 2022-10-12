package com.talentport.login.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.talentport.core.db.oracle.OracleDBPool;
import com.talentport.login.dto.UserDTO;
import com.talentport.login.utils.DBConfig;
import com.talentport.login.utils.EnvironmentData;
import com.talentport.login.utils.SecretManagerAWSUtils;

import oracle.jdbc.OracleTypes;

@Repository
public class LoginDao {
	
	
public UserDTO getUserById(String id) throws Exception {
		
		Connection conn =null;
		CallableStatement ps =null;
		try{
			 conn = OracleDBPool.getSingletonConnectionJDBC();
			 ps= conn.prepareCall("{ CALL TALENTPORT.PAUSERCURD.SPUSUARIOS(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,NULL,'SelectOne',?) }");
			/* IN */
			ps.setString(1, id);
			
			/* OUT */
			ps.registerOutParameter(2, OracleTypes.CURSOR); 
			ps.execute();
			conn.commit();
			
			ResultSet rs = (ResultSet) ps.getObject(2);
			UserDTO user = new UserDTO();
			
			if(rs.next()){
				user.setId(id);
				user.setName(rs.getString("FCNOMBRES"));
				user.setLastname(rs.getString("FCAPELLIDOSPATERNO"));
				user.setPhone(rs.getString("FCTELEFONO"));
				user.setRole(rs.getString("FCROLES"));
				user.setStatus(rs.getInt("FIACTIVO"));
			} 
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}
	}
	static {
		try {
			 String secretBD = SecretManagerAWSUtils
	                    .getParameter(EnvironmentData.getPropertyValue("OraDBKey"));
			Gson gson = new Gson();
			DBConfig cognitoConfig = gson.fromJson(secretBD, DBConfig.class);
			OracleDBPool.initSingletonConnectionCredentials(cognitoConfig.getUrl(), cognitoConfig.getUser(), cognitoConfig.getPass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
