package com.quwu.xinwo.bean;

public class My_SaleBean {
private String url,name,price,buyer,putawayTime,soldoutTime;
private String rent,tenancy_term,participation;
private String deliver_goodsState;
public My_SaleBean(String url, String name, String price, String buyer,
		String putawayTime, String soldoutTime, String rent,
		String tenancy_term, String participation,String deliver_goodsState) {
	super();
	this.url = url;
	this.name = name;
	this.price = price;
	this.buyer = buyer;
	this.putawayTime = putawayTime;
	this.soldoutTime = soldoutTime;
	this.rent = rent;
	this.tenancy_term = tenancy_term;
	this.participation = participation;
	this.deliver_goodsState=deliver_goodsState;
}
public String getDeliver_goodsState() {
	return deliver_goodsState;
}
public void setDeliver_goodsState(String deliver_goodsState) {
	this.deliver_goodsState = deliver_goodsState;
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
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getBuyer() {
	return buyer;
}
public void setBuyer(String buyer) {
	this.buyer = buyer;
}
public String getPutawayTime() {
	return putawayTime;
}
public void setPutawayTime(String putawayTime) {
	this.putawayTime = putawayTime;
}
public String getSoldoutTime() {
	return soldoutTime;
}
public void setSoldoutTime(String soldoutTime) {
	this.soldoutTime = soldoutTime;
}
public String getRent() {
	return rent;
}
public void setRent(String rent) {
	this.rent = rent;
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

}
