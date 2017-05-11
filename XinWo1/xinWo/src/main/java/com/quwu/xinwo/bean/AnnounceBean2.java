package com.quwu.xinwo.bean;

public class AnnounceBean2 {
private String url,name,describe,bid,person,transaction_price,check;

public AnnounceBean2(String url, String name, String describe, String bid,
		String person, String transaction_price, String check) {
	super();
	this.url = url;
	this.name = name;
	this.describe = describe;
	this.bid = bid;
	this.person = person;
	this.transaction_price = transaction_price;
	this.check = check;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTransaction_price() {
		return transaction_price;
	}

	public void setTransaction_price(String transaction_price) {
		this.transaction_price = transaction_price;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
}
