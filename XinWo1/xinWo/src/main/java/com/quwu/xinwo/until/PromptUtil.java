package com.quwu.xinwo.until;
/**
 * 提示框
 * */
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.quwu.xinwo.mywight.MyAlertDialog;

public class PromptUtil {
	
	public static void isQuestionImage(String title, String message,String btn,Context context,final View view) {
		final MyAlertDialog alertDialog = new MyAlertDialog(context
				);
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		view.setAlpha(0.5f);
		alertDialog.setNegativeButton(btn, new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
				view.setAlpha(1f);
			}
		});
	}
}
