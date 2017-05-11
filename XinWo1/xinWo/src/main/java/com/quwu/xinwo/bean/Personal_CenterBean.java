package com.quwu.xinwo.bean;

import java.util.List;

public class Personal_CenterBean {
	private List<String> urls;
private String userImage;
private String userName;
private String area;
private String goodsName;
private String goodsDescribe;

private String winnerImage;
private String winnerName;
private String winnerParticipation;
private String winnerLucknum;
private String winnerTime;
private String newPrice;
private String oldPrice;
private int winner_state;

private String rent;
private String tenancy_term;


private String auction_nowPrice;
private String auction_transactionPrice;
private String auction_participation;
private String auction_delayed;
private int auction_state;

/**
 * 拍卖
 */
public Personal_CenterBean(List<String> urls, String userImage,
		String userName, String area, String goodsName, String goodsDescribe,
		String auction_nowPrice, String auction_transactionPrice,
		String auction_participation, String auction_delayed, int auction_state) {
	super();
	this.urls = urls;
	this.userImage = userImage;
	this.userName = userName;
	this.area = area;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
	this.auction_nowPrice = auction_nowPrice;
	this.auction_transactionPrice = auction_transactionPrice;
	this.auction_participation = auction_participation;
	this.auction_delayed = auction_delayed;
	this.auction_state = auction_state;
}

/**
 *出价
 * */
public Personal_CenterBean(List<String> urls, String userImage,
		String userName, String area, String goodsName, String goodsDescribe,
		String rent, String tenancy_term) {
	super();
	this.urls = urls;
	this.userImage = userImage;
	this.userName = userName;
	this.area = area;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
	this.rent = rent;
	this.tenancy_term = tenancy_term;
}



/**
 * 二手
 * */
public Personal_CenterBean(List<String> urls, String userImage,
		String userName, String area, String goodsName, String goodsDescribe,
		String winnerImage, String winnerName, String winnerParticipation,
		String winnerLucknum, String winnerTime, String newPrice,
		String oldPrice, int winner_state) {
	super();
	this.urls = urls;
	this.userImage = userImage;
	this.userName = userName;
	this.area = area;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
	this.winnerImage = winnerImage;
	this.winnerName = winnerName;
	this.winnerParticipation = winnerParticipation;
	this.winnerLucknum = winnerLucknum;
	this.winnerTime = winnerTime;
	this.newPrice = newPrice;
	this.oldPrice = oldPrice;
	this.winner_state = winner_state;
}

/**
 * 免费送
 * */
public Personal_CenterBean(List<String> urls, String userImage,
		String userName, String area, String goodsName, String goodsDescribe) {
	super();
	this.urls = urls;
	this.userImage = userImage;
	this.userName = userName;
	this.area = area;
	this.goodsName = goodsName;
	this.goodsDescribe = goodsDescribe;
}

public List<String> getUrls() {
	return urls;
}

public void setUrls(List<String> urls) {
	this.urls = urls;
}

public String getUserImage() {
	return userImage;
}

public void setUserImage(String userImage) {
	this.userImage = userImage;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getArea() {
	return area;
}

public void setArea(String area) {
	this.area = area;
}

public String getGoodsName() {
	return goodsName;
}

public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}

public String getGoodsDescribe() {
	return goodsDescribe;
}

public void setGoodsDescribe(String goodsDescribe) {
	this.goodsDescribe = goodsDescribe;
}

public String getWinnerImage() {
	return winnerImage;
}

public void setWinnerImage(String winnerImage) {
	this.winnerImage = winnerImage;
}

public String getWinnerName() {
	return winnerName;
}

public void setWinnerName(String winnerName) {
	this.winnerName = winnerName;
}

public String getWinnerParticipation() {
	return winnerParticipation;
}

public void setWinnerParticipation(String winnerParticipation) {
	this.winnerParticipation = winnerParticipation;
}

public String getWinnerLucknum() {
	return winnerLucknum;
}

public void setWinnerLucknum(String winnerLucknum) {
	this.winnerLucknum = winnerLucknum;
}

public String getWinnerTime() {
	return winnerTime;
}

public void setWinnerTime(String winnerTime) {
	this.winnerTime = winnerTime;
}

public String getNewPrice() {
	return newPrice;
}

public void setNewPrice(String newPrice) {
	this.newPrice = newPrice;
}

public String getOldPrice() {
	return oldPrice;
}

public void setOldPrice(String oldPrice) {
	this.oldPrice = oldPrice;
}

public int getWinner_state() {
	return winner_state;
}

public void setWinner_state(int winner_state) {
	this.winner_state = winner_state;
}

public String getRent() {
	return rent;
}

public void setRent(String rent) {
	this.rent = rent;
}

public String getTenancy_term() {
	return tenancy_term;
}

public void setTenancy_term(String tenancy_term) {
	this.tenancy_term = tenancy_term;
}

public String getAuction_nowPrice() {
	return auction_nowPrice;
}

public void setAuction_nowPrice(String auction_nowPrice) {
	this.auction_nowPrice = auction_nowPrice;
}

public String getAuction_transactionPrice() {
	return auction_transactionPrice;
}

public void setAuction_transactionPrice(String auction_transactionPrice) {
	this.auction_transactionPrice = auction_transactionPrice;
}

public String getAuction_participation() {
	return auction_participation;
}

public void setAuction_participation(String auction_participation) {
	this.auction_participation = auction_participation;
}

public String getAuction_delayed() {
	return auction_delayed;
}

public void setAuction_delayed(String auction_delayed) {
	this.auction_delayed = auction_delayed;
}

public int getAuction_state() {
	return auction_state;
}

public void setAuction_state(int auction_state) {
	this.auction_state = auction_state;
}



}
