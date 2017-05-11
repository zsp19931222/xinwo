package com.quwu.xinwo.bean;

import java.util.List;

public class My_ReleaseBean {
private String name,describe,browse,like;
private String price,residue,tenancy_term,participation,delay;
private List<String> urlList;
private int state;
private String status;
private String time;
private String reason;
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public My_ReleaseBean(String name, String describe, String browse, String like,
		String price, String residue, String tenancy_term,
		String participation, String delay, List<String> urlList) {
	super();
	this.name = name;
	this.describe = describe;
	this.browse = browse;
	this.like = like;
	this.price = price;
	this.residue = residue;
	this.tenancy_term = tenancy_term;
	this.participation = participation;
	this.delay = delay;
	this.urlList = urlList;
}
public My_ReleaseBean(String name, String describe, String browse, String like,
		String price, String residue, String tenancy_term,
		String participation, String delay, List<String> urlList, int state,
		String status, String time, String reason) {
	super();
	this.name = name;
	this.describe = describe;
	this.browse = browse;
	this.like = like;
	this.price = price;
	this.residue = residue;
	this.tenancy_term = tenancy_term;
	this.participation = participation;
	this.delay = delay;
	this.urlList = urlList;
	this.state = state;
	this.status = status;
	this.time = time;
	this.reason = reason;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescribe() {
	return describe;
}
public void setDescribe(String describe) {
	this.describe = describe;
}
public String getBrowse() {
	return browse;
}
public void setBrowse(String browse) {
	this.browse = browse;
}
public String getLike() {
	return like;
}
public void setLike(String like) {
	this.like = like;
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
public String getTenancy_term() {
	return tenancy_term;
}
public void setTenancy_term(String tenancy_term) {
	this.tenancy_term = tenancy_term;
}
public String getParticipation() {
	return participation;
}
public void setParticipation(String participation) {
	this.participation = participation;
}
public String getDelay() {
	return delay;
}
public void setDelay(String delay) {
	this.delay = delay;
}
public List<String> getUrlList() {
	return urlList;
}
public void setUrlList(List<String> urlList) {
	this.urlList = urlList;
}
}
