package com.quwu.xinwo.bean;

public class My_OrderBean {
	private String url, name, decribe, startprice, price, tenancy, time;

	public My_OrderBean(String url, String name, String decribe,
			String startprice, String price, String tenancy, String time) {
		super();
		this.url = url;
		this.name = name;
		this.decribe = decribe;
		this.startprice = startprice;
		this.price = price;
		this.tenancy = tenancy;
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecribe() {
		return decribe;
	}

	public void setDecribe(String decribe) {
		this.decribe = decribe;
	}

	public String getStartprice() {
		return startprice;
	}

	public void setStartprice(String startprice) {
		this.startprice = startprice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTenancy() {
		return tenancy;
	}

	public void setTenancy(String tenancy) {
		this.tenancy = tenancy;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
