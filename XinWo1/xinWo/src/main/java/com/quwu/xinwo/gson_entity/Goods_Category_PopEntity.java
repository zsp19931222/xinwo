package com.quwu.xinwo.gson_entity;

public class Goods_Category_PopEntity {
	private String	twolevel_id;//分类ID
	private String	twolevel_name;//分类名称
	private String	images_url;//图片路径
	public String getTwolevel_id() {
		return twolevel_id;
	}
	public void setTwolevel_id(String twolevel_id) {
		this.twolevel_id = twolevel_id;
	}
	public String getTwolevel_name() {
		return twolevel_name;
	}
	public void setTwolevel_name(String twolevel_name) {
		this.twolevel_name = twolevel_name;
	}
	public String getImages_url() {
		return images_url;
	}
	public void setImages_url(String images_url) {
		this.images_url = images_url;
	}
	public Goods_Category_PopEntity(String twolevel_id, String twolevel_name,
			String images_url) {
		super();
		this.twolevel_id = twolevel_id;
		this.twolevel_name = twolevel_name;
		this.images_url = images_url;
	}
	
}
