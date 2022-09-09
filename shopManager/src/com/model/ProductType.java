package com.model;

public class ProductType {

	private int id;
	private String productTypeName;
	private String productTypeDesc;
	
	
	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductType(String productTypeName, String productTypeDesc) {
		super();
		this.productTypeName = productTypeName;
		this.productTypeDesc = productTypeDesc;
	}
	
	public ProductType(int id, String productTypeName, String productTypeDesc) {
		super();
		this.id = id;
		this.productTypeName = productTypeName;
		this.productTypeDesc = productTypeDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductTypeDesc() {
		return productTypeDesc;
	}
	public void setProductTypeDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}
	@Override
	public String toString() {
		return this.getProductTypeName();
	}
	
	
}
