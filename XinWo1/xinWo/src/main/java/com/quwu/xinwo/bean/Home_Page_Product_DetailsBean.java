package com.quwu.xinwo.bean;

public class Home_Page_Product_DetailsBean {
private String imageurl;
private String user_name;
private String participation_num;
private String participation_time;
public Home_Page_Product_DetailsBean(String imageurl, String user_name,
		String participation_num, String participation_time) {
	super();
	this.imageurl = imageurl;
	this.user_name = user_name;
	this.participation_num = participation_num;
	this.participation_time = participation_time;
}
public String getImageurl() {
	return imageurl;
}
public void setImageurl(String imageurl) {
	this.imageurl = imageurl;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getParticipation_num() {
	return participation_num;
}
public void setParticipation_num(String participation_num) {
	this.participation_num = participation_num;
}
public String getParticipation_time() {
	return participation_time;
}
public void setParticipation_time(String participation_time) {
	this.participation_time = participation_time;
}

}
