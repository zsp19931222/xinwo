package com.quwu.xinwo.bean;

public class AccountBean {
	private String	transaction_num;//账号
	private String	account_name;//银行名称
	private String	bank_card;//卡种
	private String transaction_id;// 收款信息ID
	public AccountBean(String transaction_num, String account_name,
			String bank_card,String transaction_id) {
		super();
		this.transaction_num = transaction_num;
		this.account_name = account_name;
		this.bank_card = bank_card;
		this.transaction_id=transaction_id;
	}
	
	public AccountBean(String transaction_num, String account_name,
			String transaction_id) {
		super();
		this.transaction_num = transaction_num;
		this.account_name = account_name;
		this.transaction_id = transaction_id;
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
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getBank_card() {
		return bank_card;
	}
	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}
	
}
