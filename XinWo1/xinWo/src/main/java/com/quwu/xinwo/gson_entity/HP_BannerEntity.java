package com.quwu.xinwo.gson_entity;

public class HP_BannerEntity {
	private String banner_photo;// //图片路径
	private String banner_jumpmode;
	// 1-跳转到商品详情，
	// 2-跳转到加入清单的页面(指一类商品)，
	// 3-网页页面，
	// 4-跳公告，
	// 5-跳首页，
	// 6-跳活动，
	// 7-跳定位城市的最新商品，
	// 8-跳定位城市的最热商品，
	// 9-什么都不跳
	// 10-跳充值
	// 11-跳红包页面
	// 12-跳积分页面
	private String banner_url;// 网页的网址(如果不跳网页则没有数据)
	private String banner_search;// 搜索关键字
	private String banner_title;//活动标题
	
	/**
	 * 分类
	 * */
	private String oneshowpictures_id;////自增ID
	private String 	oneshowpictures_url;////图片路径
	private String 	oneshowpictures_title;////图片对应的标题
	private String 	oneshowpictures_state;////0-显示，1-不显示(是否使用该图标)
	private String 	oneshowpictures_frame;////框架值
	/**
	 * 专区
	 * */
	private String	twoshowpictures_id;//自增ID
	private String	twoshowpictures_url;//图片路径
	private String	twoshowpictures_title;//图片对应的标题
	private String	twoshowpictures_state;//0-显示，1-不显示(是否使用该图标)
	private String	twoshowpictures_describe;//描述
	
	/**
	 * 众筹好货
	 * */
	private String goods_photo;// 图片路径
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String participant_person;// 已经参与的人次
	private String total_person;// 总需
	private String goods_id;// 商品ID
	/**
	 * 附近好货
	 * */
	private String fourshowpictures_id;//自增ID
	private String	fourshowpictures_url;//图片路径
	private String	fourshowpictures_title;//图片对应的标题
	private String	fourshowpictures_state;//0-显示，1-不显示(是否使用该图标)
	/**
	 * 附近好货
	 * */
	private String	browse_volume;///商品的浏览量
	private String	goods_price;//商品价格
	
	public String getBrowse_volume() {
		return browse_volume;
	}

	public void setBrowse_volume(String browse_volume) {
		this.browse_volume = browse_volume;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}

	public String getFourshowpictures_id() {
		return fourshowpictures_id;
	}

	public void setFourshowpictures_id(String fourshowpictures_id) {
		this.fourshowpictures_id = fourshowpictures_id;
	}

	public String getFourshowpictures_url() {
		return fourshowpictures_url;
	}

	public void setFourshowpictures_url(String fourshowpictures_url) {
		this.fourshowpictures_url = fourshowpictures_url;
	}

	public String getFourshowpictures_title() {
		return fourshowpictures_title;
	}

	public void setFourshowpictures_title(String fourshowpictures_title) {
		this.fourshowpictures_title = fourshowpictures_title;
	}

	public String getFourshowpictures_state() {
		return fourshowpictures_state;
	}

	public void setFourshowpictures_state(String fourshowpictures_state) {
		this.fourshowpictures_state = fourshowpictures_state;
	}

	public String getGoods_photo() {
		return goods_photo;
	}

	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGood_description() {
		return good_description;
	}

	public void setGood_description(String good_description) {
		this.good_description = good_description;
	}

	public String getParticipant_person() {
		return participant_person;
	}

	public void setParticipant_person(String participant_person) {
		this.participant_person = participant_person;
	}

	public String getTotal_person() {
		return total_person;
	}

	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public HP_BannerEntity(String twoshowpictures_id,
			String twoshowpictures_url, String twoshowpictures_title,
			String twoshowpictures_state, String twoshowpictures_describe,int i) {
		super();
		this.twoshowpictures_id = twoshowpictures_id;
		this.twoshowpictures_url = twoshowpictures_url;
		this.twoshowpictures_title = twoshowpictures_title;
		this.twoshowpictures_state = twoshowpictures_state;
		this.twoshowpictures_describe = twoshowpictures_describe;
	}

	public String getTwoshowpictures_id() {
		return twoshowpictures_id;
	}

	public void setTwoshowpictures_id(String twoshowpictures_id) {
		this.twoshowpictures_id = twoshowpictures_id;
	}

	public String getTwoshowpictures_url() {
		return twoshowpictures_url;
	}

	public void setTwoshowpictures_url(String twoshowpictures_url) {
		this.twoshowpictures_url = twoshowpictures_url;
	}

	public String getTwoshowpictures_title() {
		return twoshowpictures_title;
	}

	public void setTwoshowpictures_title(String twoshowpictures_title) {
		this.twoshowpictures_title = twoshowpictures_title;
	}

	public String getTwoshowpictures_state() {
		return twoshowpictures_state;
	}

	public void setTwoshowpictures_state(String twoshowpictures_state) {
		this.twoshowpictures_state = twoshowpictures_state;
	}

	public String getTwoshowpictures_describe() {
		return twoshowpictures_describe;
	}

	public void setTwoshowpictures_describe(String twoshowpictures_describe) {
		this.twoshowpictures_describe = twoshowpictures_describe;
	}

	public HP_BannerEntity(String oneshowpictures_id,
			String oneshowpictures_url, String oneshowpictures_title,
			String oneshowpictures_state, String oneshowpictures_frame,String string) {
		super();
		this.oneshowpictures_id = oneshowpictures_id;
		this.oneshowpictures_url = oneshowpictures_url;
		this.oneshowpictures_title = oneshowpictures_title;
		this.oneshowpictures_state = oneshowpictures_state;
		this.oneshowpictures_frame = oneshowpictures_frame;
	}

	public String getOneshowpictures_id() {
		return oneshowpictures_id;
	}

	public void setOneshowpictures_id(String oneshowpictures_id) {
		this.oneshowpictures_id = oneshowpictures_id;
	}

	public String getOneshowpictures_url() {
		return oneshowpictures_url;
	}

	public void setOneshowpictures_url(String oneshowpictures_url) {
		this.oneshowpictures_url = oneshowpictures_url;
	}

	public String getOneshowpictures_title() {
		return oneshowpictures_title;
	}

	public void setOneshowpictures_title(String oneshowpictures_title) {
		this.oneshowpictures_title = oneshowpictures_title;
	}

	public String getOneshowpictures_state() {
		return oneshowpictures_state;
	}

	public void setOneshowpictures_state(String oneshowpictures_state) {
		this.oneshowpictures_state = oneshowpictures_state;
	}

	public String getOneshowpictures_frame() {
		return oneshowpictures_frame;
	}

	public void setOneshowpictures_frame(String oneshowpictures_frame) {
		this.oneshowpictures_frame = oneshowpictures_frame;
	}

	public HP_BannerEntity(String banner_photo, String banner_jumpmode,
			String banner_url, String banner_search,String banner_title) {
		super();
		this.banner_photo = banner_photo;
		this.banner_jumpmode = banner_jumpmode;
		this.banner_url = banner_url;
		this.banner_search = banner_search;
		this.banner_title=banner_title;
	}
	
	public String getBanner_title() {
		return banner_title;
	}

	public void setBanner_title(String banner_title) {
		this.banner_title = banner_title;
	}

	public String getBanner_photo() {
		return banner_photo;
	}
	public void setBanner_photo(String banner_photo) {
		this.banner_photo = banner_photo;
	}
	public String getBanner_jumpmode() {
		return banner_jumpmode;
	}
	public void setBanner_jumpmode(String banner_jumpmode) {
		this.banner_jumpmode = banner_jumpmode;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public String getBanner_search() {
		return banner_search;
	}
	public void setBanner_search(String banner_search) {
		this.banner_search = banner_search;
	}
}
