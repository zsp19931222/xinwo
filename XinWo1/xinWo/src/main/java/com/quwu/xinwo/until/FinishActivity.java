package com.quwu.xinwo.until;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * finish页面
 * */
public class FinishActivity {
public static void finish(int id,final Activity activity) {
	activity.findViewById(id).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			activity.finish();
		}
	});
}
}
