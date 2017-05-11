package com.quwu.xinwo.bean;

import java.util.List;

public class PinnedHeaderExpandableBean {
	String string;
	String id;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	public PinnedHeaderExpandableBean(String string, String id) {
		super();
		this.string = string;
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
