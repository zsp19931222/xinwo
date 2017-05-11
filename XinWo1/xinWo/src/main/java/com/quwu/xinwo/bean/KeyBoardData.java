package com.quwu.xinwo.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KeyBoardData implements Serializable{
private String selling_price;//卖价
private String original_price;//原价
private String freight;//运费
private String reserve_price;//保留价
private String goods_value;//商品价值
private boolean check;//是否包邮
public KeyBoardData(String selling_price, String original_price,
		String freight, String reserve_price, String goods_value, boolean check) {
	super();
	this.selling_price = selling_price;
	this.original_price = original_price;
	this.freight = freight;
	this.reserve_price = reserve_price;
	this.goods_value = goods_value;
	this.check = check;
}
public String getSelling_price() {
	return selling_price;
}
public void setSelling_price(String selling_price) {
	this.selling_price = selling_price;
}
public String getOriginal_price() {
	return original_price;
}
public void setOriginal_price(String original_price) {
	this.original_price = original_price;
}
public String getFreight() {
	return freight;
}
public void setFreight(String freight) {
	this.freight = freight;
}
public String getReserve_price() {
	return reserve_price;
}
public void setReserve_price(String reserve_price) {
	this.reserve_price = reserve_price;
}
public String getGoods_value() {
	return goods_value;
}
public void setGoods_value(String goods_value) {
	this.goods_value = goods_value;
}
public boolean isCheck() {
	return check;
}
public void setCheck(boolean check) {
	this.check = check;
}
}
