package com.quwu.xinwo.bean;

import java.util.List;

public class QCBean {
	private List<String> urls;
	private String goods_name;
	private String goods_describe;
	private String area_time;
	private String btnStr;
	private String one;
	private String tow;
	private String three;

	/**
	 * 全价
	 * */
	public QCBean(List<String> urls, String goods_name, String goods_describe,
			String area_time, String btnStr, String one) {
		super();
		this.urls = urls;
		this.goods_name = goods_name;
		this.goods_describe = goods_describe;
		this.area_time = area_time;
		this.btnStr = btnStr;
		this.one = one;
	}

	/**
	 * 众筹、出租、拍卖
	 * */
	public QCBean(List<String> urls, String goods_name, String goods_describe,
			String area_time, String btnStr, String tow, String three) {
		super();
		this.urls = urls;
		this.goods_name = goods_name;
		this.goods_describe = goods_describe;
		this.area_time = area_time;
		this.btnStr = btnStr;
		this.tow = tow;
		this.three = three;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_describe() {
		return goods_describe;
	}

	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}

	public String getArea_time() {
		return area_time;
	}

	public void setArea_time(String area_time) {
		this.area_time = area_time;
	}

	public String getBtnStr() {
		return btnStr;
	}

	public void setBtnStr(String btnStr) {
		this.btnStr = btnStr;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTow() {
		return tow;
	}

	public void setTow(String tow) {
		this.tow = tow;
	}

	public String getThree() {
		return three;
	}

	public void setThree(String three) {
		this.three = three;
	}
}
