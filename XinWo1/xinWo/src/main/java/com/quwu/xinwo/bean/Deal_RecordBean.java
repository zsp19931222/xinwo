package com.quwu.xinwo.bean;

public class Deal_RecordBean {
private String time1,time2,message,money;

public Deal_RecordBean(String time1, String time2, String message, String money) {
	super();
	this.time1 = time1;
	this.time2 = time2;
	this.message = message;
	this.money = money;
}

public String getTime1() {
	return time1;
}

public void setTime1(String time1) {
	this.time1 = time1;
}

public String getTime2() {
	return time2;
}

public void setTime2(String time2) {
	this.time2 = time2;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getMoney() {
	return money;
}

public void setMoney(String money) {
	this.money = money;
}
}
