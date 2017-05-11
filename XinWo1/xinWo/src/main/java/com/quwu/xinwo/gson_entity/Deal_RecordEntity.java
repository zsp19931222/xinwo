package com.quwu.xinwo.gson_entity;

public class Deal_RecordEntity {
	private String describe;//描述（比如充值，红包，竞拍商品等）（String类型）
	private String	money;//金额（int类型）
	private String	transactionrecord_time;//交易时间（int类型）
	private String	goods_name;//商品名称（String类型）
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getTransactionrecord_time() {
		return transactionrecord_time;
	}
	public void setTransactionrecord_time(String transactionrecord_time) {
		this.transactionrecord_time = transactionrecord_time;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
}
