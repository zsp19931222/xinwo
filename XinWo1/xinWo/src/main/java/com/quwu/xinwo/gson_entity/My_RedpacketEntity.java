package com.quwu.xinwo.gson_entity;

public class My_RedpacketEntity {
	private String redpacket_num;// 红包的数量（int类型）
	private String redexchange_state;// 状态是否拆分过（0=没有拆开1=已拆开）（int类型）
	private String redpacket_price;// 红包金额（int类型）
	private String redpacket_id;// 拆开红包传的数（不用于显示）（int类型）
	public String getRedpacket_num() {
		return redpacket_num;
	}
	public void setRedpacket_num(String redpacket_num) {
		this.redpacket_num = redpacket_num;
	}
	public String getRedexchange_state() {
		return redexchange_state;
	}
	public void setRedexchange_state(String redexchange_state) {
		this.redexchange_state = redexchange_state;
	}
	public String getRedpacket_price() {
		return redpacket_price;
	}
	public void setRedpacket_price(String redpacket_price) {
		this.redpacket_price = redpacket_price;
	}
	public String getRedpacket_id() {
		return redpacket_id;
	}
	public void setRedpacket_id(String redpacket_id) {
		this.redpacket_id = redpacket_id;
	}
}
