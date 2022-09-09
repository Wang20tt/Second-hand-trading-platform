package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Customer;
import com.model.ManagerUser;

public class CustomerUserDao {

	public int customerAdd(Connection con,Customer customer)throws Exception{
		String sql="insert into t_customer values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getPassword1());
		pstmt.setString(3, customer.getPassword2());
		pstmt.setFloat(4, customer.getMoney());
		return pstmt.executeUpdate();
	}
	

	public  Customer login(Connection con, Customer customer)throws Exception{
		Customer resultUser=null;
		String sql="select * from t_customer where customerName=? and password1=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2,customer.getPassword1());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new Customer();
			resultUser.setCustomerName(rs.getString("customerName"));
			resultUser.setPassword1(rs.getString("password1"));
		}
		return resultUser;
	}
}
