package com.quwu.xinwo.gson_entity;

public class Personal_DataEntity {
	private String	user_pictrue;//头像
	private String	user_name;//昵称
	private String	user_sex;//性别
	private String	address;//地区
	private String	binding_phone;//手机号
	private String	receipt_address;//收货地址
	private String	describe;//个人介绍
	private String	authentication;//实名认证
	public String getUser_pictrue() {
		return user_pictrue;
	}
	public void setUser_pictrue(String user_pictrue) {
		this.user_pictrue = user_pictrue;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBinding_phone() {
		return binding_phone;
	}
	public void setBinding_phone(String binding_phone) {
		this.binding_phone = binding_phone;
	}
	public String getReceipt_address() {
		return receipt_address;
	}
	public void setReceipt_address(String receipt_address) {
		this.receipt_address = receipt_address;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
}
