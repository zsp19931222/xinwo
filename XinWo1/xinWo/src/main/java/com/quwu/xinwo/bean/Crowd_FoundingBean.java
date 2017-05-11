package com.quwu.xinwo.bean;

import java.util.List;

public class Crowd_FoundingBean {
private List<String> urls;
private String goodsName;
private String goodsDescribe;
private String area_time;
private String price;
private String residue;
private String	goods_id ;//商品ID;
public List<String> getUrls() {
	return urls;
}
public void setUrls(List<String> urls) {
	this.urls = urls;
}
public String getGoodsName() {
	return goodsName;
}
public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}
public String getGoodsDescribe() {
	return goodsDescribe;
}
public void setGoodsDescribe(String goodsDescribe) {
	this.goodsDescribe = goodsDescribe;
}
public String getArea_time() {
	return area_time;
}
public void setArea_time(String area_time) {
	this.area_time = area_time;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getResidue() {
	return residue;
}
public void setResidue(String residue) {
	this.residue = residue;
}
public String getGoods_id() {
	return goods_id;
}
public void setGoods_id(String goods_id) {
	this.goods_id = goods_id;
}
public Crowd_FoundingBean(List<String> urls, String goodsName,
		String goodsDescribe, String area_time, String price, String residue,String	goods_id) {
	super();
	this.urls = urls;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
	this.area_time = area_time;
	this.price = price;
	this.residue = residue;
	this.goods_id=goods_id;
}
}
