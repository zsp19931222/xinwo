package com.quwu.xinwo.bean;

public class Help_CenterBean {
	private String commonproblem_title;// 标题
	private String commonproblem_url;// 地址

	public Help_CenterBean(String commonproblem_title, String commonproblem_url) {
		super();
		this.commonproblem_title = commonproblem_title;
		this.commonproblem_url = commonproblem_url;
	}

	public String getCommonproblem_title() {
		return commonproblem_title;
	}

	public void setCommonproblem_title(String commonproblem_title) {
		this.commonproblem_title = commonproblem_title;
	}

	public String getCommonproblem_url() {
		return commonproblem_url;
	}

	public void setCommonproblem_url(String commonproblem_url) {
		this.commonproblem_url = commonproblem_url;
	}
}
