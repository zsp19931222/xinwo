package com.quwu.xinwo.bean;

import java.util.List;

public class RentBean {
private List<String> urls;
private String goodsName;
private String goodsDescribe;
private String area_time;
private String rentprice;
private String rent_period;
private String goods_id;
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
public String getRentprice() {
	return rentprice;
}
public void setRentprice(String rentprice) {
	this.rentprice = rentprice;
}
public String getRent_period() {
	return rent_period;
}
public void setRent_period(String rent_period) {
	this.rent_period = rent_period;
}
public String getGoods_id() {
	return goods_id;
}
public void setGoods_id(String goods_id) {
	this.goods_id = goods_id;
}
public RentBean(List<String> urls, String goodsName, String goodsDescribe,
		String area_time, String rentprice, String rent_period,String goods_id) {
	super();
	this.urls = urls;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
	this.area_time = area_time;
	this.rentprice = rentprice;
	this.rent_period = rent_period;
	this.goods_id=goods_id;
}
}
