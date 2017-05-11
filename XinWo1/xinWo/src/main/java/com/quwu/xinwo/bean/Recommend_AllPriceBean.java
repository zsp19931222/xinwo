package com.quwu.xinwo.bean;

public class Recommend_AllPriceBean {
private String url,describe,name,price,goods_id;

public Recommend_AllPriceBean(String url, String describe, String name,
		String price,
		String goods_id
		) {
	super();
	this.url = url;
	this.describe = describe;
	this.name = name;
	this.price = price;
	this.goods_id=goods_id;
}

public String getGoods_id() {
	return goods_id;
}

public void setGoods_id(String goods_id) {
	this.goods_id = goods_id;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getDescribe() {
	return describe;
}

public void setDescribe(String describe) {
	this.describe = describe;
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
}
