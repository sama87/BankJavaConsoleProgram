package com.dollarsbank.model;

public class Customer {

	static int idCounter = 1;
	
	private int id;
	private String name;
	private String address;
	private String phone;
	private String userName;
	private String password;
	
	
	public Customer() {
		this.id = idCounter;
		idCounter++;
	}
	
	public Customer(String name, String address, String phone, String userName, String password) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
		this.id = idCounter;
		idCounter++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", userName="
				+ userName + ", password=" + password + "]";
	}
	
	
	
	
	
}
