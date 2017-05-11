package com.quwu.xinwo.bean;

public class DF1_GridViewBean {
	private String imageUrl, name, message;

	public DF1_GridViewBean(String imageUrl, String name, String message) {
		super();
		this.imageUrl = imageUrl;
		this.name = name;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
