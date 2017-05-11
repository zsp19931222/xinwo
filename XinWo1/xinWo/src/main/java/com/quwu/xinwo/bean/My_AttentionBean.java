package com.quwu.xinwo.bean;

public class My_AttentionBean {
	private String url,goodsName,goodsDescribe,attention,browse;
	private String one,tow;
	private String state;
	private String goods_id;
	public My_AttentionBean(String url, String goodsName, String goodsDescribe,
			String attention, String browse, String one, String tow, String state,String goods_id) {
		super();
		this.url = url;
		this.goodsName = goodsName;
		this.goodsDescribe = goodsDescribe;
		this.attention = attention;
		this.browse = browse;
		this.one = one;
		this.tow = tow;
		this.state = state;
		this.goods_id=goods_id;
	}
	
	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getBrowse() {
		return browse;
	}
	public void setBrowse(String browse) {
		this.browse = browse;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
