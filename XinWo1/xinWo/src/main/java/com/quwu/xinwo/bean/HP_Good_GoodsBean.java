package com.quwu.xinwo.bean;

public class HP_Good_GoodsBean {
	private String goods_photo;// 图片路径
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String participant_person;// 已经参与的人次
	private String total_person;// 总需
	private String goods_id;// 商品ID
	public String getGoods_photo() {
		return goods_photo;
	}
	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
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
	public String getParticipant_person() {
		return participant_person;
	}
	public void setParticipant_person(String participant_person) {
		this.participant_person = participant_person;
	}
	public String getTotal_person() {
		return total_person;
	}
	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public HP_Good_GoodsBean(String goods_photo, String goods_name,
			String good_description, String participant_person,
			String total_person, String goods_id) {
		super();
		this.goods_photo = goods_photo;
		this.goods_name = goods_name;
		this.good_description = good_description;
		this.participant_person = participant_person;
		this.total_person = total_person;
		this.goods_id = goods_id;
	}

}
