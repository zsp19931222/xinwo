package com.quwu.xinwo.bean;

public class DF2_ListViewBean {
	private String imageUrl, name, describe,price,participation;

	public DF2_ListViewBean(String imageUrl, String name, String describe,
			String price, String participation) {
		super();
		this.imageUrl = imageUrl;
		this.name = name;
		this.describe = describe;
		this.price = price;
		this.participation = participation;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getParticipation() {
		return participation;
	}

	public void setParticipation(String participation) {
		this.participation = participation;
	}

}
