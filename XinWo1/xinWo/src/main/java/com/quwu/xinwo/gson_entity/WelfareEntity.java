package com.quwu.xinwo.gson_entity;

public class WelfareEntity {
	private String total_integral;// 累计收获（int类型）
	private String integral;// 当前积分（int类型）
	private String sign_num;// 签到收获（int类型）
	private String sign_num1;// 今日收获（int类型）
	public String getTotal_integral() {
		return total_integral;
	}
	public void setTotal_integral(String total_integral) {
		this.total_integral = total_integral;
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	public String getSign_num() {
		return sign_num;
	}
	public void setSign_num(String sign_num) {
		this.sign_num = sign_num;
	}
	public String getSign_num1() {
		return sign_num1;
	}
	public void setSign_num1(String sign_num1) {
		this.sign_num1 = sign_num1;
	}
}
