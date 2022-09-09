package com.model;

public class Customer {

	private int id;
	private String customerName;
	private String password1;
	private String password2;
	private float money;
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int id, float money) {
		super();
		this.id = id;
		this.money = money;
	}
	
	
	
	public Customer(String customerName, String password1) {
		super();
		this.customerName = customerName;
		this.password1 = password1;
	}
	public Customer(String customerName, String password1, String password2,
			float money) {
		super();
		this.customerName = customerName;
		this.password1 = password1;
		this.password2 = password2;
		this.money = money;
	}
	public Customer(int id, String customerName, String password1,
			String password2, float money) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.password1 = password1;
		this.password2 = password2;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
	
}
