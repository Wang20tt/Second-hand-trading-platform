package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.ProductType;
import com.util.StringUtil;

public class ProductTypeDao {

	public int productTypeAdd(Connection con,ProductType productType)throws Exception{
		String sql="insert into t_productType values(null,?,?)";//�����ֺ�����ֲ�����ݿ�
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, productType.getProductTypeName());
		pstmt.setString(2, productType.getProductTypeDesc());
		return pstmt.executeUpdate();
	}
	public ResultSet productTypeList(Connection con,ProductType productType)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_productType");
		if(StringUtil.isNotEmtpty(productType.getProductTypeName())){
			sb.append(" and productTypeName like '%"+productType.getProductTypeName()+"%'");
		}//��ѯ���ľ����㷨, ���������ļ�������·��append
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		//����������ȫ������where���滻��û�����ľ���ʾ��ȫ��
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
		pstmt.setString(1, productType.getProductTypeName());//����Ҫ�ı����ݿ����ݵĶ�Ҫ��setString������
		pstmt.setString(2, productType.getProductTypeDesc());
		pstmt.setInt(3, productType.getId());
		return pstmt.executeUpdate();
	}
}
