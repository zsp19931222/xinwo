package com.quwu.xinwo.bean;

public class After_SaleBean {
private String url,goodState,time,name,price,relation,boxText,orderNumText;
private boolean box;
public String getBoxText() {
	return boxText;
}
public void setBoxText(String boxText) {
	this.boxText = boxText;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getGoodState() {
	return goodState;
}
public void setGoodState(String goodState) {
	this.goodState = goodState;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
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
public String getRelation() {
	return relation;
}
public void setRelation(String relation) {
	this.relation = relation;
}
public boolean isBox() {
	return box;
}
public void setBox(boolean box) {
	this.box = box;
}
public After_SaleBean(String url, String goodState, String time, String name,
		String price, String relation, String boxText, String orderNumText,
		boolean box) {
	super();
	this.url = url;
	this.goodState = goodState;
	this.time = time;
	this.name = name;
	this.price = price;
	this.relation = relation;
	this.boxText = boxText;
	this.orderNumText = orderNumText;
	this.box = box;
}
public String getOrderNumText() {
	return orderNumText;
}
public void setOrderNumText(String orderNumText) {
	this.orderNumText = orderNumText;
}
}
