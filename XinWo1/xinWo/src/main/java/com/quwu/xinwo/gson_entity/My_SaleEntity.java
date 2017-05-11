package com.quwu.xinwo.gson_entity;

public class My_SaleEntity {
	private String	ins_time;//上架时间（int类型）
	private String	user_name;//买家名字（String类型）
	private String	goods_photo;//商品图片（String类型）
	private String	goods_price;//价格（int类型）
	private String	state;//（1=买家已付款,2=卖家已发货,3=交易成功（买家已收货）)（int类型）
	private String	goods_name;//商品名称（String类型）
	private String	good_sellfinshed;//商品交易时间（int类型）
	
	private String	total_person;//总需（int类型）
	
	private String rent_date;//租期（int类型）
	private String	rent_price;//租金/每月（int类型）
	
	private String	auction_currentprice;//成交价（int类型）
	private String	auction_person;//几人参与（int类型）
	public String getIns_time() {
		return ins_time;
	}
	public void setIns_time(String ins_time) {
		this.ins_time = ins_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGoods_photo() {
		return goods_photo;
	}
	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGood_sellfinshed() {
		return good_sellfinshed;
	}
	public void setGood_sellfinshed(String good_sellfinshed) {
		this.good_sellfinshed = good_sellfinshed;
	}
	public String getTotal_person() {
		return total_person;
	}
	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}
	public String getRent_date() {
		return rent_date;
	}
	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}
	public String getRent_price() {
		return rent_price;
	}
	public void setRent_price(String rent_price) {
		this.rent_price = rent_price;
	}
	public String getAuction_currentprice() {
		return auction_currentprice;
	}
	public void setAuction_currentprice(String auction_currentprice) {
		this.auction_currentprice = auction_currentprice;
	}
	public String getAuction_person() {
		return auction_person;
	}
	public void setAuction_person(String auction_person) {
		this.auction_person = auction_person;
	}
}
