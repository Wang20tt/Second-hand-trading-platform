package com.model;
/**
 * ”√ªßModel¿‡
 * @author Administrator
 *
 */

public class ManagerUser {

	private int id;
	private String userName;
	private String password;
	
	
	public ManagerUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
