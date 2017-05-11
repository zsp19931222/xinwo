package com.quwu.xinwo.bean;

public class DoubleListviewBean {
private String	region_id;//地区ID
private String		region_name;//地区名称
public DoubleListviewBean(String region_id, String region_name) {
	super();
	this.region_id = region_id;
	this.region_name = region_name;
}
public String getRegion_id() {
	return region_id;
}
public void setRegion_id(String region_id) {
	this.region_id = region_id;
}
public String getRegion_name() {
	return region_name;
}
public void setRegion_name(String region_name) {
	this.region_name = region_name;
}
}
