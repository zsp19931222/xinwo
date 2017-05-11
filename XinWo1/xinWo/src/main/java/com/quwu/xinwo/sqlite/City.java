package com.quwu.xinwo.sqlite;

public class City {
	public String name;
	public String pinyi;
	private String id;

	public City(String name, String pinyi,String id) {
		super();
		this.name = name;
		this.pinyi = pinyi;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public City() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyi() {
		return pinyi;
	}

	public void setPinyi(String pinyi) {
		this.pinyi = pinyi;
	}

}
