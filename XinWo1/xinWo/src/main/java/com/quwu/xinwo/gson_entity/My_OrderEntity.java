package com.quwu.xinwo.gson_entity;

public class My_OrderEntity {
	private String goods_description;// 商品描述（String类型）
	private String goods_photo;// 商品图片（String类型）
	private String auction_startprice;// 起拍价（拍卖的）（int类型）
	private String goods_price;// 价格（全价的）（int类型）
	private String orderinfo_state;// 属于哪一个分类（1=全价,2=众筹,3=出租,4=免费送）（int类型）
	private String auction_currentprice;// 成交价（拍卖的）（int类型）
	private String rent_date;// 租期（出租的）（int类型）
	private String goods_id;// 商品id（点击去付款时会用到）（int类型）
	private String addorder_time;// 下单时间（int类型）
	private String goods_name;// 商品名字（String类型）
	private String rent_price;// 租金（出租的）（int类型）
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public String getGoods_photo() {
		return goods_photo;
	}
	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
	}
	public String getAuction_startprice() {
		return auction_startprice;
	}
	public void setAuction_startprice(String auction_startprice) {
		this.auction_startprice = auction_startprice;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getOrderinfo_state() {
		return orderinfo_state;
	}
	public void setOrderinfo_state(String orderinfo_state) {
		this.orderinfo_state = orderinfo_state;
	}
	public String getAuction_currentprice() {
		return auction_currentprice;
	}
	public void setAuction_currentprice(String auction_currentprice) {
		this.auction_currentprice = auction_currentprice;
	}
	public String getRent_date() {
		return rent_date;
	}
	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getAddorder_time() {
		return addorder_time;
	}
	public void setAddorder_time(String addorder_time) {
		this.addorder_time = addorder_time;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getRent_price() {
		return rent_price;
	}
	public void setRent_price(String rent_price) {
		this.rent_price = rent_price;
	}
}
