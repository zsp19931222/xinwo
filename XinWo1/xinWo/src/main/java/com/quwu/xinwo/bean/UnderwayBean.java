package com.quwu.xinwo.bean;

public class UnderwayBean {
private String url,name,alldemand,residue,person,check,btnText;
private String describe;
private int bainum;
public UnderwayBean(String url, String name,String describe, String alldemand, String residue,
		String person, String check, String btnText, int bainum) {
	super();
	this.url = url;
	this.name = name;
	this.describe = describe;
	this.alldemand = alldemand;
	this.residue = residue;
	this.person = person;
	this.check = check;
	this.btnText = btnText;
	this.bainum = bainum;
}
public String getDescribe() {
	return describe;
}
public void setDescribe(String describe) {
	this.describe = describe;
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
public String getAlldemand() {
	return alldemand;
}
public void setAlldemand(String alldemand) {
	this.alldemand = alldemand;
}
public String getResidue() {
	return residue;
}
public void setResidue(String residue) {
	this.residue = residue;
}
public String getPerson() {
	return person;
}
public void setPerson(String person) {
	this.person = person;
}
public String getCheck() {
	return check;
}
public void setCheck(String check) {
	this.check = check;
}
public String getBtnText() {
	return btnText;
}
public void setBtnText(String btnText) {
	this.btnText = btnText;
}
public int getBainum() {
	return bainum;
}
public void setBainum(int bainum) {
	this.bainum = bainum;
}
}
