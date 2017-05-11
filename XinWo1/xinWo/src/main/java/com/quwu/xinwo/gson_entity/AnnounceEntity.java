package com.quwu.xinwo.gson_entity;

public class AnnounceEntity {
	private String user_name;//用户名（String类型）
	private String goods_photo;//商品图片（String类型）
	private String announced_time;//揭晓时间（int类型）
	private String goods_name;//商品名称（String类型）
	private String total_person;//总需（int类型）
	private String participant_person;//本期参与（int类型）
	private String good_description;//商品描述（String类型）
	private String personnum;//本次参与（int类型）
	private String lucky_number;//幸运号（int类型）
	private String	auction_currentprice;//成交价（int类型）
	private String	auction_person;//抢夺人数（int类型）
	private String	u_id;//查看拍到的用户所用到的id（int类型）
	private String	user_pictrue;//获奖者用户头像
	public String getUser_pictrue() {
		return user_pictrue;
	}
	public void setUser_pictrue(String user_pictrue) {
		this.user_pictrue = user_pictrue;
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
	public String getAnnounced_time() {
		return announced_time;
	}
	public void setAnnounced_time(String announced_time) {
		this.announced_time = announced_time;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getTotal_person() {
		return total_person;
	}
	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}
	public String getParticipant_person() {
		return participant_person;
	}
	public void setParticipant_person(String participant_person) {
		this.participant_person = participant_person;
	}
	public String getGood_description() {
		return good_description;
	}
	public void setGood_description(String good_description) {
		this.good_description = good_description;
	}
	public String getPersonnum() {
		return personnum;
	}
	public void setPersonnum(String personnum) {
		this.personnum = personnum;
	}
	public String getLucky_number() {
		return lucky_number;
	}
	public void setLucky_number(String lucky_number) {
		this.lucky_number = lucky_number;
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
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
}
