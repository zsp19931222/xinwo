package com.quwu.xinwo.gson_entity;

public class AdvertisementEntity {
	private String cover_time;//广告时间
	private String cover_url;//广告链接
	private String cover_pictrue;//广告图片
	/**
	 *导航栏图标
	 * */
	private String sixshowpictures_id;//自增ID
	public AdvertisementEntity(String sixshowpictures_id,
			String sixshowpictures_url, String sixshowpictures_title,
			String sixshowpictures_state) {
		super();
		this.sixshowpictures_id = sixshowpictures_id;
		this.sixshowpictures_url = sixshowpictures_url;
		this.sixshowpictures_title = sixshowpictures_title;
		this.sixshowpictures_state = sixshowpictures_state;
	}
	private String sixshowpictures_url;//图片路径
	private String sixshowpictures_title;//图片对应的标题
	private String sixshowpictures_state;//0-显示，1-不显示(是否使用该图标)
	
	public String getSixshowpictures_id() {
		return sixshowpictures_id;
	}
	public void setSixshowpictures_id(String sixshowpictures_id) {
		this.sixshowpictures_id = sixshowpictures_id;
	}
	public String getSixshowpictures_url() {
		return sixshowpictures_url;
	}
	public void setSixshowpictures_url(String sixshowpictures_url) {
		this.sixshowpictures_url = sixshowpictures_url;
	}
	public String getSixshowpictures_title() {
		return sixshowpictures_title;
	}
	public void setSixshowpictures_title(String sixshowpictures_title) {
		this.sixshowpictures_title = sixshowpictures_title;
	}
	public String getSixshowpictures_state() {
		return sixshowpictures_state;
	}
	public void setSixshowpictures_state(String sixshowpictures_state) {
		this.sixshowpictures_state = sixshowpictures_state;
	}
	public String getCover_time() {
		return cover_time;
	}
	public void setCover_time(String cover_time) {
		this.cover_time = cover_time;
	}
	public String getCover_url() {
		return cover_url;
	}
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}
	public String getCover_pictrue() {
		return cover_pictrue;
	}
	public void setCover_pictrue(String cover_pictrue) {
		this.cover_pictrue = cover_pictrue;
	}
}
