package com.quwu.xinwo.until;

import android.content.Context;
import android.view.View;

import com.quwu.xinwo.mywight.CustomProgressDialog;

public class MyProgressDialog {
	/**
	 * 等待动画
	 * */

	private static CustomProgressDialog progressDialog = null;

	public static void startProgressDialog(Context context,View view1,View view2) {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(context);
			progressDialog.setMessage("正在加载中...");
			view1.setVisibility(View.GONE);
			view2.setVisibility(View.GONE);
		}
		progressDialog.show();
	}

	public static void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
}
