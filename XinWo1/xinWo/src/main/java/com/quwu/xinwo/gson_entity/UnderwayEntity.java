package com.quwu.xinwo.gson_entity;

public class UnderwayEntity {
	/**
	 * 服务器数据 (众筹)
	 */
	private String surplus_person;// 剩余人次（int类型）
	private String total_person;// 商品总需（int类型）
	/**
	 * 服务器数据 (拍卖)
	 */
	private String transcend;// 超越人数（int类型）
	private String auction_currentprice;// 当前价格（int类型）
	private String auction_person;// 参与人次（int类型）
	private String user_id;// 用户id（int类型）
	/**
	 * 服务器数据 (公共)
	 */
	private String goods_photo;// 商品图片（String类型）
	private String goods_name;// 商品名称（String类型）
	private String participant_person;// 出价（int类型）
	private String good_description;// 商品描述（String类型）
	public String getSurplus_person() {
		return surplus_person;
	}
	public void setSurplus_person(String surplus_person) {
		this.surplus_person = surplus_person;
	}
	public String getTotal_person() {
		return total_person;
	}
	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}
	public String getTranscend() {
		return transcend;
	}
	public void setTranscend(String transcend) {
		this.transcend = transcend;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
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
}
