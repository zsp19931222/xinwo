package com.quwu.xinwo.bean;

public class My_RedpacketBean {
private String money,num,redexchange_state;

public My_RedpacketBean(String money, String num, String redexchange_state) {
	super();
	this.money = money;
	this.num = num;
	this.redexchange_state = redexchange_state;
}

public String getMoney() {
	return money;
}

public void setMoney(String money) {
	this.money = money;
}

public String getNum() {
	return num;
}

public void setNum(String num) {
	this.num = num;
}

public String getRedexchange_state() {
	return redexchange_state;
}

public void setRedexchange_state(String redexchange_state) {
	this.redexchange_state = redexchange_state;
}

}
