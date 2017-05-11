package com.quwu.xinwo.bean;

import java.util.List;

public class DF1_ListViewBean {
private String imageUrl;
private String name;
private String time_area;
private String original_price;
private String goods_name;
private String goods_describe;
private String totle;
private String residue;
private String liske;
private List<String> urlList;
public DF1_ListViewBean(String imageUrl, String name, String time_area,
		String original_price, String goods_name, String goods_describe,
		String totle, String residue, String liske,List<String> urlList) {
	super();
	this.imageUrl = imageUrl;
	this.name = name;
	this.time_area = time_area;
	this.original_price = original_price;
	this.goods_name = goods_name;
	this.goods_describe = goods_describe;
	this.totle = totle;
	this.residue = residue;
	this.liske = liske;
	this.urlList=urlList;
}
public List<String> getUrlList() {
	return urlList;
}
public void setUrlList(List<String> urlList) {
	this.urlList = urlList;
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
public String getTime_area() {
	return time_area;
}
public void setTime_area(String time_area) {
	this.time_area = time_area;
}
public String getOriginal_price() {
	return original_price;
}
public void setOriginal_price(String original_price) {
	this.original_price = original_price;
}
public String getGoods_name() {
	return goods_name;
}
public void setGoods_name(String goods_name) {
	this.goods_name = goods_name;
}
public String getGoods_describe() {
	return goods_describe;
}
public void setGoods_describe(String goods_describe) {
	this.goods_describe = goods_describe;
}
public String getTotle() {
	return totle;
}
public void setTotle(String totle) {
	this.totle = totle;
}
public String getResidue() {
	return residue;
}
public void setResidue(String residue) {
	this.residue = residue;
}
public String getLiske() {
	return liske;
}
public void setLiske(String liske) {
	this.liske = liske;
}
}
