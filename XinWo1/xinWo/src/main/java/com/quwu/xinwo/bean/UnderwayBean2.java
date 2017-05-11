package com.quwu.xinwo.bean;

public class UnderwayBean2 {
private String url,name,price,nowprice,person,message,state,describe;

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

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getNowprice() {
	return nowprice;
}

public void setNowprice(String nowprice) {
	this.nowprice = nowprice;
}

public String getPerson() {
	return person;
}

public void setPerson(String person) {
	this.person = person;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getDescribe() {
	return describe;
}

public void setDescribe(String describe) {
	this.describe = describe;
}

public UnderwayBean2(String url, String name,String describe, String price, String nowprice,
		String person, String message, String state) {
	super();
	this.url = url;
	this.name = name;
	this.describe = describe;
	this.price = price;
	this.nowprice = nowprice;
	this.person = person;
	this.message = message;
	this.state = state;
}
}
