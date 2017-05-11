package com.quwu.xinwo.bean;

import java.util.List;

public class ChoicenessBean {
private List<String> urls;
private String imageurl;
private String userName;
private String time_area;
private String nowPrice;
private String goodsName;
private String goodsDescribe;
private String participation;
private String delayed;
public ChoicenessBean(List<String> urls, String imageurl, String userName,
		String time_area, String nowPrice, String goodsName,
		String goodsDescribe, String participation, String delayed) {
	super();
	this.urls = urls;
	this.imageurl = imageurl;
	this.userName = userName;
	this.time_area = time_area;
	this.nowPrice = nowPrice;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
	this.participation = participation;
	this.delayed = delayed;
}
public List<String> getUrls() {
	return urls;
}
public void setUrls(List<String> urls) {
	this.urls = urls;
}
public String getImageurl() {
	return imageurl;
}
public void setImageurl(String imageurl) {
	this.imageurl = imageurl;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getTime_area() {
	return time_area;
}
public void setTime_area(String time_area) {
	this.time_area = time_area;
}
public String getNowPrice() {
	return nowPrice;
}
public void setNowPrice(String nowPrice) {
	this.nowPrice = nowPrice;
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
public String getParticipation() {
	return participation;
}
public void setParticipation(String participation) {
	this.participation = participation;
}
public String getDelayed() {
	return delayed;
}
public void setDelayed(String delayed) {
	this.delayed = delayed;
}

}
