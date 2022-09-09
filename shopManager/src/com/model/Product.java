package com.model;

public class Product {
	private int id;
	private String productName;
	private String productTime;
	private float price;
	private String productDesc;
	private int productTypeId=-1;
	private String ProductTypeName;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String productName, String productTime, float price,
			String productDesc, int productTypeId) {
		super();
		this.id = id;
		this.productName = productName;
		this.productTime = productTime;
		this.price = price;
		this.productDesc = productDesc;
		this.productTypeId = productTypeId;
	}
	
	public Product(String productName, String productTime, float price,
			String productDesc, int productTypeId) {
		super();
		this.productName = productName;
		this.productTime = productTime;
		this.price = price;
		this.productDesc = productDesc;
		this.productTypeId = productTypeId;
	}
	
	public Product(String productName, String productTime, int productTypeId) {
		super();
		this.productName = productName;
		this.productTime = productTime;
		this.productTypeId = productTypeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductTime() {
		return productTime;
	}
	public void setProductTime(String productTime) {
		this.productTime = productTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return ProductTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		ProductTypeName = productTypeName;
	}
	
	
	

}
