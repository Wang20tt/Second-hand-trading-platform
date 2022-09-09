package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.ManagerUser;

public class ManagerUserDao {
	/**
	 * µÇÂ½ÑéÖ¤
	 * @param con
	 * @param managerUser
	 * @return
	 * @throws Exception
	 */

	public  ManagerUser login(Connection con,ManagerUser managerUser)throws Exception{
		ManagerUser resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, managerUser.getUserName());
		pstmt.setString(2, managerUser.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new ManagerUser();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
}
