package com.quwu.xinwo.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.quwu.xinwo.R;
import com.quwu.xinwo.mywight.CustomProgressDialog3;

public class Loading_Pop extends PopupWindow{
	private View view;
	private CustomProgressDialog3 dialog3;
	public Loading_Pop(final Activity activity) {
		LayoutInflater inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.loading, null);
		// 自定义PopupWindow
		// int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(view);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.anim.activity_translate_in);
		dialog3 = new CustomProgressDialog3(activity,
				R.style.dialog);
		dialog3.setCancelable(false);
		dialog3.show();
	}
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.CENTER,
					Gravity.CENTER, Gravity.CENTER);
		} else {
			this.dismiss();
		}
	}
	public void MyDismiss() {
		this.dismiss();
		dialog3.dismiss();
	}
}
