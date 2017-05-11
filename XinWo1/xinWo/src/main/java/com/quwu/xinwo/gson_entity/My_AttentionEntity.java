package com.quwu.xinwo.gson_entity;

public class My_AttentionEntity {
	private String goods_photo;// 商品图片
	private String goods_price;// 价格
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String browse_person;// 关注人数
	private String browse_volume;// 浏览人数
	private String goods_id;//商品id(int类型)
	private String	panduan;//(1=众筹,2=出租,3=拍卖,4=白送,5=全新)
	private String	total_person;//总需人次
	private String	surplus_person;//剩余人次
	private String	rent_date;//租期
	private String	rent_price;//租金
	private String	auction_currentprice;//当前拍卖价格
	private String	auction_person;//拍卖参与人次
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getPanduan() {
		return panduan;
	}
	public void setPanduan(String panduan) {
		this.panduan = panduan;
	}
	public String gettotal_person() {
		return total_person;
	}
	public String getTotal_person() {
		return total_person;
	}
	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}
	public String getSurplus_person() {
		return surplus_person;
	}
	public void setSurplus_person(String surplus_person) {
		this.surplus_person = surplus_person;
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGood_description() {
		return good_description;
	}
	public void setGood_description(String good_description) {
		this.good_description = good_description;
	}
	public String getBrowse_person() {
		return browse_person;
	}
	public void setBrowse_person(String browse_person) {
		this.browse_person = browse_person;
	}
	public String getBrowse_volume() {
		return browse_volume;
	}
	public void setBrowse_volume(String browse_volume) {
		this.browse_volume = browse_volume;
	}
}
