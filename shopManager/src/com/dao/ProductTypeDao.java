package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.ProductType;
import com.util.StringUtil;

public class ProductTypeDao {

	public int productTypeAdd(Connection con,ProductType productType)throws Exception{
		String sql="insert into t_productType values(null,?,?)";//把名字和描述植入数据库
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, productType.getProductTypeName());
		pstmt.setString(2, productType.getProductTypeDesc());
		return pstmt.executeUpdate();
	}
	public ResultSet productTypeList(Connection con,ProductType productType)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_productType");
		if(StringUtil.isNotEmtpty(productType.getProductTypeName())){
			sb.append(" and productTypeName like '%"+productType.getProductTypeName()+"%'");
		}//查询语句的经典算法, 设置数据文件的搜索路径append
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		//把有条件的全部都用where来替换，没条件的就显示出全部
		return pstmt.executeQuery();
	}
	public int productTypeDelete(Connection con,String id)throws Exception{
		String sql="delete from t_productType where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
		
	public int productTypeModify(Connection con,ProductType productType)throws Exception{
		String sql="update t_productType set productTypeName=?,productTypeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, productType.getProductTypeName());//凡是要改变数据库内容的都要用setString（）；
		pstmt.setString(2, productType.getProductTypeDesc());
		pstmt.setInt(3, productType.getId());
		return pstmt.executeUpdate();
	}
}
