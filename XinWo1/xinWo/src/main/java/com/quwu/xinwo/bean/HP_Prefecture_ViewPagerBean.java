package com.quwu.xinwo.bean;

public class HP_Prefecture_ViewPagerBean {
	private String name, message, image;

	public HP_Prefecture_ViewPagerBean(String name, String message, String image) {
		super();
		this.name = name;
		this.message = message;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
