package com.quwu.xinwo.gson_entity;

public class After_SaleEntity {
	private String order_no;// 订单编号
	private String gettime;// 获得时间
	private String goods_pictrue;// 商品图片
	private String goods_name;// 商品名字
	private String price;// 交易金额
	private String state;// 状态（0==还没有申请退款，1=已申请退款）
	private String u_id;// 卖家
	private String user_id;// 买家
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getGettime() {
		return gettime;
	}
	public void setGettime(String gettime) {
		this.gettime = gettime;
	}
	public String getGoods_pictrue() {
		return goods_pictrue;
	}
	public void setGoods_pictrue(String goods_pictrue) {
		this.goods_pictrue = goods_pictrue;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
