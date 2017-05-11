package com.quwu.xinwo.gson_entity;

public class Account_ManagementEntity {
	/**
	 * 返回数据
	 * */
	private String transaction_id;// 收款信息表的ID
	private String transaction_num;// 账号
	private String transaction_phone;// 绑定的手机号码
	private String transaction_name;// 姓名
	private String account_name;// 银行卡名称或者支付宝
	
	public Account_ManagementEntity(String transaction_id,
			String transaction_num, String transaction_phone,String transaction_name,
			String account_name) {
		super();
		this.transaction_id = transaction_id;
		this.transaction_num = transaction_num;
		this.transaction_phone = transaction_phone;
		this.transaction_name = transaction_name;
		this.account_name = account_name;
	}
	
	public String getTransaction_name() {
		return transaction_name;
	}

	public void setTransaction_name(String transaction_name) {
		this.transaction_name = transaction_name;
	}

	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_num() {
		return transaction_num;
	}
	public void setTransaction_num(String transaction_num) {
		this.transaction_num = transaction_num;
	}
	public String getTransaction_phone() {
		return transaction_phone;
	}
	public void setTransaction_phone(String transaction_phone) {
		this.transaction_phone = transaction_phone;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
}
