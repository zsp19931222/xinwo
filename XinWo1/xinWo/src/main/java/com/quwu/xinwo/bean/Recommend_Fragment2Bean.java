package com.quwu.xinwo.bean;

import java.util.List;

public class Recommend_Fragment2Bean {
	/**
	 * 公共
	 * */
	private String name, describe, price, url,goods_id;

	/**
	 * 众筹
	 * */
	private List<String> urlList;
	private int bar;
	private String residue, time, area;
	/**
	 * 拍卖
	 * */
	private String participation;
	/**
	 * 出租
	 * */
	private String rent, tenancy_term, btnText;

	// 众筹
	public Recommend_Fragment2Bean(List<String> urlList, String name,
			String describe, String time, String area, String price,
			String residue, int bar,String goods_id) {
		super();
		this.urlList = urlList;
		this.name = name;
		this.describe = describe;
		this.time = time;
		this.area = area;
		this.price = price;
		this.residue = residue;
		this.bar = bar;
		this.goods_id = goods_id;
	}

	// 拍卖
	public Recommend_Fragment2Bean(String name, String describe, String price,
			String url, String participation,String goods_id) {
		super();
		this.name = name;
		this.describe = describe;
		this.price = price;
		this.url = url;
		this.participation = participation;
		this.goods_id = goods_id;
	}

	// 出租
	public Recommend_Fragment2Bean(String name, String price, String url,
			String rent, String tenancy_term, String btnText,String goods_id) {
		super();
		this.name = name;
		this.price = price;
		this.url = url;
		this.rent = rent;
		this.tenancy_term = tenancy_term;
		this.btnText = btnText;
		this.goods_id = goods_id;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getRent() {
		return rent;
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
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

	public void setParticipation(String participation) {
		this.participation = participation;
	}

	public String getParticipation() {
		return participation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResidue() {
		return residue;
	}

	public void setResidue(String residue) {
		this.residue = residue;
	}

	public int getBar() {
		return bar;
	}

	public void setBar(int bar) {
		this.bar = bar;
	}
}
