package com.quwu.xinwo.bean;

public class HP_HotBean {
private String imageUrl,name,price,describe,btn_text;

public HP_HotBean(String imageUrl, String name, String price, String describe,
		String btn_text) {
	super();
	this.imageUrl = imageUrl;
	this.name = name;
	this.price = price;
	this.describe = describe;
	this.btn_text = btn_text;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
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

public String getDescribe() {
	return describe;
}

public void setDescribe(String describe) {
	this.describe = describe;
}

public String getBtn_text() {
	return btn_text;
}

public void setBtn_text(String btn_text) {
	this.btn_text = btn_text;
}
}
