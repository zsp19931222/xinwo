package com.quwu.xinwo.until;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 隐藏软键盘
 * */

public class MyInputMethodManager {
public static void MyInputMethodManager1(View view,Context context){
	InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
//	editText.setCursorVisible(false);//失去光标
	if (view!=null) {
		Log.e("", "进来了");
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
}
