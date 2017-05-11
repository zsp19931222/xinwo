package com.quwu.xinwo.mywight;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.quwu.xinwo.R;

public class CustomProgressDialog3 extends ProgressDialog{
	private GifView gifView;
	 public CustomProgressDialog3(Context context) {
		  super(context);
		 }
		  public CustomProgressDialog3(Context context,int theme) { 
		           super(context,theme); 
		  }
		 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.customprogressdialog3);
		  gifView=(GifView) findViewById(R.id.gif_linear);
		  gifView.setBackgroundResource(0);
		  gifView.setMovieResource(R.drawable.gif);
		 }
		  public static CustomProgressDialog3 show (Context context) { 
		   CustomProgressDialog3 dialog = new CustomProgressDialog3(context,R.style.dialog2);
		   dialog.show();
		   return dialog;
		  }
		 
		}