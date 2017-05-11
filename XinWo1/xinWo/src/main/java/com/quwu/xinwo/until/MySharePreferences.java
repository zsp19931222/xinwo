package com.quwu.xinwo.until;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MySharePreferences {
public static void Put(String user_id,String state,Activity activity){
	SharedPreferences preferences=activity.getSharedPreferences("user_id",Activity.MODE_PRIVATE);
	Editor editor=preferences.edit();
	editor.putString("user_id", user_id);
	editor.putString("state", state);
	editor.commit();
}
public static void PutPhone(String phone,Activity activity){
	SharedPreferences preferences=activity.getSharedPreferences("isphone",Activity.MODE_PRIVATE);
	Editor editor=preferences.edit();
	editor.putString("phone", phone);
	editor.commit();
}
public static String GetPhone(Activity activity){
	SharedPreferences preferences=activity.getSharedPreferences("isphone",Activity.MODE_PRIVATE);
	String user_id1=preferences.getString("phone", "");
	return user_id1;
}
public static String GetUser_ID(Context activity){
	SharedPreferences preferences=activity.getSharedPreferences("user_id",Activity.MODE_PRIVATE);
	String user_id1=preferences.getString("user_id", "");
	return user_id1;
}
public static String GetState(Activity activity){
	SharedPreferences preferences=activity.getSharedPreferences("user_id",Activity.MODE_PRIVATE);
	String state=preferences.getString("state", "");
	return state;
}
public static void PutHongBao(Context context,String hongbao,String num){
	SharedPreferences preferences=context.getSharedPreferences("hongbao",Activity.MODE_PRIVATE);
	Editor editor=preferences.edit();
	editor.putString("hongbao", hongbao);
	editor.putString("num", num);
	editor.commit();
}

public static String GetHongBao(Context context){
	SharedPreferences preferences=context.getSharedPreferences("hongbao",Activity.MODE_PRIVATE);
	String hongbao=preferences.getString("hongbao", "");
	return hongbao;
}
public static String GetHongBaoNum(Activity activity){
	SharedPreferences preferences=activity.getSharedPreferences("hongbao",Activity.MODE_PRIVATE);
	String hongbao=preferences.getString("num", "");
	return hongbao;
}
public static String GetWeiXin(Context context){
	SharedPreferences preferences=context.getSharedPreferences("weixin",Activity.MODE_PRIVATE);
	String hongbao=preferences.getString("weixinstate", "");
	return hongbao;
}
public static void PutWeiXin(String state,Activity activity){
	SharedPreferences preferences=activity.getSharedPreferences("weixin",Activity.MODE_PRIVATE);
	Editor editor=preferences.edit();
	editor.putString("weixinstate", state);
	editor.commit();
}
}
