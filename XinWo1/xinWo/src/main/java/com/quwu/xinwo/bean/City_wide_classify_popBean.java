package com.quwu.xinwo.bean;

public class City_wide_classify_popBean {
private String	firstlevel_id;//分类ID
private String	firstlevel_name;//分类名称

public String getFirstlevel_id() {
	return firstlevel_id;
}
public void setFirstlevel_id(String firstlevel_id) {
	this.firstlevel_id = firstlevel_id;
}
public String getFirstlevel_name() {
	return firstlevel_name;
}
public void setFirstlevel_name(String firstlevel_name) {
	this.firstlevel_name = firstlevel_name;
}
public City_wide_classify_popBean(String firstlevel_id, String firstlevel_name) {
	super();
	this.firstlevel_id = firstlevel_id;
	this.firstlevel_name = firstlevel_name;
}
}
