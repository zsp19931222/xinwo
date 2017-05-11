package com.quwu.xinwo.mywight;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
	public static void Toast(Context context,String string){
		if (string!=null) {
			Toast.makeText(context, string, 500).show();
		}
	} 
}
