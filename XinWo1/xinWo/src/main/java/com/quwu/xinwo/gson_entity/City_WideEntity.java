package com.quwu.xinwo.gson_entity;

public class City_WideEntity {
	private String	citypages_url;//图标路径
	private String	citypages_title;//图标标题
	private String	firstlevel_id;//分类ID
	private String	firstlevel_name;//分类名称
	
	private String	region_id;//地区ID
	private String	pinyin;//地区拼音
	private String	parent_id;//省ID

	private String	goods_photo;//商品图片
	private String	goods_name;//商品名称
	private String	good_description;//商品描述
	private String	good_region;//省份
	private String	city_region;//市
	private String	small_area;//区县
	private String	ins_time;//商品上架时间
	private String	current_time1;//服务器当前时间
	private String	total_person;//总需
	private String	surplus_person;//剩余人次
	private String	browse_person;//商品浏览量
	private String	goodslevel;//商品的一级分类
	private String	goods_id;//商品的ID
	private String	buy_userid;//点击查询的用户的ID（如果没有登录则传0）
	
	private String	sortorder_id;//排序的ID
	private String	sortorder_content;//排序内容
	
	public String getSortorder_id() {
		return sortorder_id;
	}
	public void setSortorder_id(String sortorder_id) {
		this.sortorder_id = sortorder_id;
	}
	public String getSortorder_content() {
		return sortorder_content;
	}
	public void setSortorder_content(String sortorder_content) {
		this.sortorder_content = sortorder_content;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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
	public String getGood_region() {
		return good_region;
	}
	public void setGood_region(String good_region) {
		this.good_region = good_region;
	}
	public String getCity_region() {
		return city_region;
	}
	public void setCity_region(String city_region) {
		this.city_region = city_region;
	}
	public String getSmall_area() {
		return small_area;
	}
	public void setSmall_area(String small_area) {
		this.small_area = small_area;
	}
	public String getIns_time() {
		return ins_time;
	}
	public void setIns_time(String ins_time) {
		this.ins_time = ins_time;
	}
	public String getCurrent_time1() {
		return current_time1;
	}
	public void setCurrent_time1(String current_time1) {
		this.current_time1 = current_time1;
	}
	public String getTotal_person() {
		return total_person;
	}
	public void setTotal_person(String total_person) {
		this.total_person = total_person;
	}
	public String getSurplus_person() {
		return surplus_person;
	}
	public void setSurplus_person(String surplus_person) {
		this.surplus_person = surplus_person;
	}
	public String getBrowse_person() {
		return browse_person;
	}
	public void setBrowse_person(String browse_person) {
		this.browse_person = browse_person;
	}
	public String getGoodslevel() {
		return goodslevel;
	}
	public void setGoodslevel(String goodslevel) {
		this.goodslevel = goodslevel;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getBuy_userid() {
		return buy_userid;
	}
	public void setBuy_userid(String buy_userid) {
		this.buy_userid = buy_userid;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getFirstlevel_id() {
		return firstlevel_id;
	}
	public void setFirstlevel_id(String firstlevel_id) {
		this.firstlevel_id = firstlevel_id;
	}
	public String getFirstlevel_name() {
		return firstlevel_name;
	}
	public void setFirstlevel_name(String firstlevel_name) {
		this.firstlevel_name = firstlevel_name;
	}
	public String getCitypages_url() {
		return citypages_url;
	}
	public void setCitypages_url(String citypages_url) {
		this.citypages_url = citypages_url;
	}
	public String getCitypages_title() {
		return citypages_title;
	}
	public void setCitypages_title(String citypages_title) {
		this.citypages_title = citypages_title;
	}
}
