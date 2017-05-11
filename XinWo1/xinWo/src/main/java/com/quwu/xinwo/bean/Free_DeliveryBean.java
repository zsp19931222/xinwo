package com.quwu.xinwo.bean;

import java.util.List;

public class Free_DeliveryBean {
	private List<String> urls;
	private String goodsName;
	private String goodsDescribe;
	private String area_time;
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public Free_DeliveryBean(List<String> urls, String goodsName,
			String goodsDescribe, String area_time,String goods_id) {
		super();
		this.urls = urls;
		this.goodsName = goodsName;
		this.goodsDescribe = goodsDescribe;
		this.area_time = area_time;
		this.goods_id=goods_id;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
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
	public String getArea_time() {
		return area_time;
	}
	public void setArea_time(String area_time) {
		this.area_time = area_time;
	}
}
