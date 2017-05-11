package com.quwu.xinwo.until;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeUtils {
	public static String DealTime1(long redpacket_time){
		Date date = new Date(redpacket_time);
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd");
		String time=sdf.format(date);
		return time;
	}
	public static String DealTime2(long redpacket_time){
		Date date = new Date(redpacket_time);
		SimpleDateFormat sdf = new SimpleDateFormat(
				"HH-mm-ss");
		String time=sdf.format(date);
		return time;
	}
public static String Time(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");
	String time=sdf.format(date);
	return time;
}
/**
 * 精确到秒
 * **/
public static String Time1(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	String time=sdf.format(date);
	return time;
}
/**
 * 精确到秒
 * **/
public static String Time7(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"MM-dd HH:mm:ss");
	String time=sdf.format(date);
	return time;
}
/**
 * 不要年月日
 * **/
public static String Time3(long redpacket_time){
	String b = null;
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"mm:ss:SSS");
	String time=sdf.format(date);
	if (time.length()>8) {
		 b=time.substring(0,8);
	}
	return b;
}
/**
 * 精确到天
 * **/
public static String Time2(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");
	String time=sdf.format(date);
	return time;
}
/**
 * 精确到天
 * **/
public static String Time4(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");
	String time=sdf.format(date);
	return time;
}
/**
 * 精确到天
 * **/
public static String Time6(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"HH:mm");
	String time=sdf.format(date);
	return time;
}
/**
 * 精确到天
 * **/
public static String Time5(long redpacket_time){
	Date date = new Date(redpacket_time);
	SimpleDateFormat sdf = new SimpleDateFormat(
			"MM-dd");
	String time=sdf.format(date);
	return time;
}
/**
 * 获取时间差
 * */
public static String isTime_Difference(long diff) {
	long days = diff / (1000 * 60 * 60 * 24);
	long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
			* (1000 * 60 * 60))
			/ (1000 * 60);
	System.out.println("" + days + "天" + hours + "小时" + minutes + "分");
	if (days != 0) {
		return TimeUtils.DealTime1(diff);
	} else if (days == 0 && hours != 0) {
		return hours + "小时前";
	} else if (days == 0 && hours == 0 && minutes > 10) {
		return minutes + "分钟前";
	} else {
		return "刚刚";
	}

}
}
