package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.model.Customer;
import com.util.StringUtil;


public class ProductChosenDao {
	
	public int productBuy(Connection con,Float buyNum,Customer  customer)throws Exception{
		String sql="update t_customer  set money=money-"+buyNum+"   where  id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);

		
		pstmt.setInt(1, customer.getId());		
		
		
		return pstmt.executeUpdate();
	}
	public int productBuyNot(Connection con,Float buyNum,Customer  customer)throws Exception{
		String sql="update t_customer  set money=money+"+buyNum+"   where  id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);

		
		pstmt.setInt(1, customer.getId());		
		
		
		return pstmt.executeUpdate();
	}
	
	public float productSum(Connection con)throws Exception{
		
		String sql="select sum(price) as num from t_productchosen";
		PreparedStatement pstmt=con.prepareStatement(sql);

		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			System.out.println(rs.getString("num"));
		}
	
		return Float.parseFloat(rs.getString("num"));
	}
	public ResultSet customerList(Connection con,Customer customer)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_customer");
		if(StringUtil.isNotEmtpty(customer.getCustomerName())){
			sb.append(" and customerName like '%"+customer.getCustomerName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
}
