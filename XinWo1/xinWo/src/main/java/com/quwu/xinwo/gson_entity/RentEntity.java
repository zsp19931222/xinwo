package com.quwu.xinwo.gson_entity;

public class RentEntity {
	private String bigpicture1;//图片路径1
	private String	bigpicture2;//图片路径2   
	private String	bigpicture3;//图片路径3
	private String	goods_name;//商品名称
	private String	good_description;//商品描述
	private String	good_region;//商品上架的省份（包括直辖市等）
	private String	city_region;//商品上架的区县
	private String	small_area;//商品上架的区县
	private String	ins_time;//上架时间
	private String	current_time1;//服务器当前时间（时间戳）
	private String	renttime;//租期
	private String rentprice;//租金
	private String goods_id;
	
	public String getCity_region() {
		return city_region;
	}
	public void setCity_region(String city_region) {
		this.city_region = city_region;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getBigpicture1() {
		return bigpicture1;
	}
	public void setBigpicture1(String bigpicture1) {
		this.bigpicture1 = bigpicture1;
	}
	public String getBigpicture2() {
		return bigpicture2;
	}
	public void setBigpicture2(String bigpicture2) {
		this.bigpicture2 = bigpicture2;
	}
	public String getBigpicture3() {
		return bigpicture3;
	}
	public void setBigpicture3(String bigpicture3) {
		this.bigpicture3 = bigpicture3;
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
	public String getGood_region() {
		return good_region;
	}
	public void setGood_region(String good_region) {
		this.good_region = good_region;
	}
	public String getSmall_area() {
		return small_area;
	}
	public void setSmall_area(String small_area) {
		this.small_area = small_area;
	}
	public String getIns_time() {
		return ins_time;
	}
	public void setIns_time(String ins_time) {
		this.ins_time = ins_time;
	}
	public String getCurrent_time1() {
		return current_time1;
	}
	public void setCurrent_time1(String current_time1) {
		this.current_time1 = current_time1;
	}
	public String getRenttime() {
		return renttime;
	}
	public void setRenttime(String renttime) {
		this.renttime = renttime;
	}
	public String getRentprice() {
		return rentprice;
	}
	public void setRentprice(String rentprice) {
		this.rentprice = rentprice;
	}
}
