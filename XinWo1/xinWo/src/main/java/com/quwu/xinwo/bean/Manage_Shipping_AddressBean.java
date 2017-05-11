package com.quwu.xinwo.bean;

public class Manage_Shipping_AddressBean {
private String name,phoneNum,area;
private boolean checked;
private String	address_id;// 地址标示id(这里没有用到，删除地址或编辑地址会用到)
private String	user_id ;//用户id
private String receipt_area;
public Manage_Shipping_AddressBean(String name, String phoneNum, String area,
		boolean checked, String address_id, String user_id,String receipt_area) {
	super();
	this.name = name;
	this.phoneNum = phoneNum;
	this.area = area;
	this.checked = checked;
	this.address_id = address_id;
	this.user_id = user_id;
	this.receipt_area=receipt_area;
}

public String getReceipt_area() {
	return receipt_area;
}

public void setReceipt_area(String receipt_area) {
	this.receipt_area = receipt_area;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhoneNum() {
	return phoneNum;
}
public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public boolean isChecked() {
	return checked;
}
public void setChecked(boolean checked) {
	this.checked = checked;
}
public String getAddress_id() {
	return address_id;
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
}
