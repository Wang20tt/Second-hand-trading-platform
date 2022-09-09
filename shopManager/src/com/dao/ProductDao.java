package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Product;
import com.util.StringUtil;

public class ProductDao {

	public int productAdd(Connection con,Product product)throws Exception{
		String sql="insert into t_product values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, product.getProductName());
		pstmt.setString(2,product.getProductTime());
		pstmt.setFloat(3, product.getPrice());
		pstmt.setString(4, product.getProductDesc());
		pstmt.setInt(5, product.getProductTypeId());
		return pstmt.executeUpdate();
		
	}
	public int productAddIntoCar(Connection con,Product product)throws Exception{
		String sql="insert into t_productchosen values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, product.getProductName());
		pstmt.setString(2,product.getProductTime());
		pstmt.setFloat(3, product.getPrice());
		pstmt.setString(4, product.getProductDesc());
		pstmt.setInt(5, product.getProductTypeId());
		return pstmt.executeUpdate();
		
	}
	//如果是罗列的话，只需con就行了
	public ResultSet productList(Connection con,Product product)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_product p,t_productType pt where p.productTypeId=pt.id ");
		if(StringUtil.isNotEmtpty(product.getProductName())){
			sb.append(" and p.productName like '%"+product.getProductName()+"%'");
		}//查询语句的经典算法, 设置数据文件的搜索路径append
		if(StringUtil.isNotEmtpty(product.getProductTime())){
			sb.append(" and p.productTime like '%"+product.getProductTime()+"%'");
		}//查询语句的经典算法, 设置数据文件的搜索路径append
		if(product.getProductTypeId()!=-1){
			sb.append(" and p.productTypeId ="+product.getProductTypeId());
		}//查询语句的经典算法, 设置数据文件的搜索路径append
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet productChosenList(Connection con)throws Exception{
		String sql="select * from t_productchosen p,t_productType pt where p.productTypeId=pt.id ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	public int productDelete(Connection con,String id)throws Exception{
		String sql="delete from t_product where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	public int productChosenDelete(Connection con,String id)throws Exception{
		String sql="delete from t_productchosen where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	public int productModify(Connection con,Product product)throws Exception{
		String sql="update t_product set productName=?,productTime=?,price=?,productDesc=?,productTypeId=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, product.getProductName());
		pstmt.setString(2, product.getProductTime());
		pstmt.setFloat(3, product.getPrice());
		pstmt.setString(4, product.getProductDesc());
		pstmt.setInt(5, product.getProductTypeId());
		pstmt.setInt(6, product.getId());
		return pstmt.executeUpdate();
	}
	public boolean getProductByProductTypeId(Connection con,String productTypeId)throws Exception{
		String sql="select * from t_product where productTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, productTypeId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
}
