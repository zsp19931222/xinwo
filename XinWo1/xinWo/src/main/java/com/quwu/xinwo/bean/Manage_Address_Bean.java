package com.quwu.xinwo.bean;

public class Manage_Address_Bean {
	private String name, phone, address,state,id;

	public Manage_Address_Bean(String name, String phone, String address,
			String state,String id) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.state = state;
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
