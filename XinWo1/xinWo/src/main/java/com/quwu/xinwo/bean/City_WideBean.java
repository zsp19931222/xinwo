package com.quwu.xinwo.bean;

public class City_WideBean {
	private String imageurl;
	private String name;
	private String describe;
	private String area_time;
	private String total;
	private String residue;
	private String like;

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
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

	public String getArea_time() {
		return area_time;
	}

	public void setArea_time(String area_time) {
		this.area_time = area_time;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getResidue() {
		return residue;
	}

	public void setResidue(String residue) {
		this.residue = residue;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public City_WideBean(String imageurl, String name, String describe,
			String area_time, String total, String residue, String like) {
		super();
		this.imageurl = imageurl;
		this.name = name;
		this.describe = describe;
		this.area_time = area_time;
		this.total = total;
		this.residue = residue;
		this.like = like;
	}
}
