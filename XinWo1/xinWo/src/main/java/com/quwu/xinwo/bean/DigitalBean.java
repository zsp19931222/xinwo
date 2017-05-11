package com.quwu.xinwo.bean;

public class DigitalBean {
private String url;
private String name;
private String price;
private String browse;
private String goods_id;
private int progress;
public DigitalBean(String url, String name, String price, int progress,String browse,String goods_id) {
	super();
	this.url = url;
	this.name = name;
	this.price = price;
	this.progress = progress;
	this.browse=browse;
	this.goods_id=goods_id;
}

public String getGoods_id() {
	return goods_id;
}

public void setGoods_id(String goods_id) {
	this.goods_id = goods_id;
}

public String getBrowse() {
	return browse;
}

public void setBrowse(String browse) {
	this.browse = browse;
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
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public int getProgress() {
	return progress;
}
public void setProgress(int progress) {
	this.progress = progress;
}
}
