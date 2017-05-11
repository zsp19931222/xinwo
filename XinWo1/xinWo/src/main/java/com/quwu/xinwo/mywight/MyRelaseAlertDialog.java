package com.quwu.xinwo.mywight;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.quwu.xinwo.R;

public class MyRelaseAlertDialog {
	Context context;
	android.app.AlertDialog ad;
	LinearLayout buttonLayout;
	LinearLayout cancleLayout;
	Window window;

	public MyRelaseAlertDialog(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		ad = new android.app.AlertDialog.Builder(context).create();
		ad.show();
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		window = ad.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		window.setContentView(R.layout.release);

//		buttonLayout = (LinearLayout) window.findViewById(R.id.release_btnLin);
//		cancleLayout = (LinearLayout) window
//				.findViewById(R.id.release_cancleLin);
	}

	/**
	 * 设置取消按钮
	 * 
	 * @param text
	 * @param listener
	 */
	public void setCancleButton(String text,final View.OnClickListener listener) {
		Button button = new Button(context);
		LinearLayout.LayoutParams params = new LayoutParams(0,
				LayoutParams.MATCH_PARENT, 1);
		button.setLayoutParams(params);
		button.setBackgroundResource(0);
		button.setText(text);
		button.setTextSize(16);
		button.setTextColor(context.getResources().getColor(R.color.white));
		button.setGravity(Gravity.CENTER);
		button.setOnClickListener(listener);
		Drawable rightDrawable = context.getResources().getDrawable(
				R.drawable.fb_icn_close);
		rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(),
				rightDrawable.getMinimumHeight());
		button.setCompoundDrawables(null, rightDrawable, null, null);
		cancleLayout.addView(button);
	}

	/**
	 * 设置按钮
	 * 
	 * @param text
	 * @param listener
	 */
	public void setPositiveButton(String text,
			final View.OnClickListener listener) {
		Button button = new Button(context);
		LinearLayout.LayoutParams params = new LayoutParams(0,
				LayoutParams.MATCH_PARENT, 1);
		button.setLayoutParams(params);
		button.setBackgroundResource(0);
		button.setText(text);
		button.setTextSize(16);
		button.setTextColor(context.getResources().getColor(R.color.white));
		button.setGravity(Gravity.CENTER);
		button.setOnClickListener(listener);
		Drawable rightDrawable = context.getResources().getDrawable(
				R.drawable.fb_icn_album);
		rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(),
				rightDrawable.getMinimumHeight());
		button.setCompoundDrawables(null, rightDrawable, null, null);
		buttonLayout.addView(button);
	}

	/**
	 * 设置按钮
	 * 
	 * @param text
	 * @param listener
	 */
	public void setNegativeButton(String text,
			final View.OnClickListener listener) {
		Button button = new Button(context);
		LinearLayout.LayoutParams params = new LayoutParams(0,
				LayoutParams.MATCH_PARENT, 1);
		button.setLayoutParams(params);
		button.setBackgroundResource(0);
		button.setText(text);
		button.setTextColor(context.getResources().getColor(R.color.white));
		button.setGravity(Gravity.CENTER);
		button.setTextSize(16);
		button.setOnClickListener(listener);
		Drawable rightDrawable = context.getResources().getDrawable(
				R.drawable.fb_icn_camera);
		rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(),
				rightDrawable.getMinimumHeight());
		button.setCompoundDrawables(null, rightDrawable, null, null);
		if (buttonLayout.getChildCount() > 0) {
			params.setMargins(0, 0, 0, 0);
			button.setLayoutParams(params);
			buttonLayout.addView(button, 1);
		} else {
			button.setLayoutParams(params);
			buttonLayout.addView(button);
		}
	}

	/**
	 * 关闭对话框
	 */
	public void dismiss() {
		ad.dismiss();
	}
}
