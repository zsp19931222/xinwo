package com.quwu.xinwo.bean;

public class HP_NearbyBean {
private String imageUrl,type;

public HP_NearbyBean(String imageUrl, String type) {
	super();
	this.imageUrl = imageUrl;
	this.type = type;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}
}
