package com.quwu.xinwo.gson_entity;

public class Manage_Shipping_AddressEntity {
	private String	address_id;// 地址标示id(这里没有用到，删除地址或编辑地址会用到)
	private String	user_id ;//用户id
	private String	receipt_address;// 收货地址
	private String	receipt_name;// 收货人
	private String	receipt_phone;// 电话号码
	private String	receipt_area;//详细地址
	
	private String	state;// 是否是默认地址（0=默认，1=非默认）
	public String getAddress_id() {
		return address_id;
	}
	public String getReceipt_area() {
		return receipt_area;
	}
	public void setReceipt_area(String receipt_area) {
		this.receipt_area = receipt_area;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReceipt_address() {
		return receipt_address;
	}
	public void setReceipt_address(String receipt_address) {
		this.receipt_address = receipt_address;
	}
	public String getReceipt_name() {
		return receipt_name;
	}
	public void setReceipt_name(String receipt_name) {
		this.receipt_name = receipt_name;
	}
	public String getReceipt_phone() {
		return receipt_phone;
	}
	public void setReceipt_phone(String receipt_phone) {
		this.receipt_phone = receipt_phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
