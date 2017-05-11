package com.quwu.xinwo.bean;

public class Lucky_RecordBean {
	private String url, name, describe, text1, text1_1, text2, text2_1, text3,
			text3_1, time1, time2;
	private String state;

	public Lucky_RecordBean(String url, String name, String describe,
			String text1, String text1_1, String text2, String text2_1,
			String text3, String text3_1, String time1, String time2,String state) {
		super();
		this.url = url;
		this.name = name;
		this.describe = describe;
		this.text1 = text1;
		this.text1_1 = text1_1;
		this.text2 = text2;
		this.text2_1 = text2_1;
		this.text3 = text3;
		this.text3_1 = text3_1;
		this.time1 = time1;
		this.time2 = time2;
		this.state=state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText1_1() {
		return text1_1;
	}

	public void setText1_1(String text1_1) {
		this.text1_1 = text1_1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText2_1() {
		return text2_1;
	}

	public void setText2_1(String text2_1) {
		this.text2_1 = text2_1;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getText3_1() {
		return text3_1;
	}

	public void setText3_1(String text3_1) {
		this.text3_1 = text3_1;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}
}
