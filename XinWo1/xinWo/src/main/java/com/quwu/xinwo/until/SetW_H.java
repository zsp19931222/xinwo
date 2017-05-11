package com.quwu.xinwo.until;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/**
 * 动态设置控件宽高（宽高一样）
 * scale：比例
 * view：控件
 * context：上下文
 * */
public class SetW_H {
	public static void setRelativeLayout(Context context, View view,double scale) {
		int w = (int) (ScreenUtils.getScreenWidth(context) * (scale));
		RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.width = w;
		linearParams.height = w;
		view.setLayoutParams(linearParams);
	}
	public static void setRelativeLayout1(Context context, View view,double scale) {
		int w = (int) (ScreenUtils.getScreenWidth(context) * (scale));
		RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.width = w;
		linearParams.height = w+20;
		view.setLayoutParams(linearParams);
	}

	public static void setLinearLayout(Context context, View view,double scale) {
		int w = (int) (ScreenUtils.getScreenWidth(context) * (scale));
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.width = w;
		linearParams.height = w;
		view.setLayoutParams(linearParams);
	}
	public static void setLinearLayout1(Context context, View view,double scale) {
		int w = (int) (ScreenUtils.getScreenWidth(context));
		int h = (int) (ScreenUtils.getScreenHeight(context)*scale);
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.width = w;
		linearParams.height = h;
		view.setLayoutParams(linearParams);
	}
}
