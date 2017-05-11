package com.quwu.xinwo.mywight;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.quwu.xinwo.R;

public class CustomProgressDialog2 extends ProgressDialog{
	 public CustomProgressDialog2(Context context) {
		  super(context);
		 }
		  public CustomProgressDialog2(Context context,int theme) { 
		           super(context,theme); 
		  }
		 @Override
		 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.customprogressdialog2);
		//  setContentView(android.R.layout.alert_dialog_progress);
		  
		 }
		  public static CustomProgressDialog2 show (Context context) { 
		   CustomProgressDialog2 dialog = new CustomProgressDialog2(context,R.style.dialog);
		   dialog.show();
		   return dialog;
		  }
		 
		}