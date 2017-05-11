package com.quwu.xinwo.mywight;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.quwu.xinwo.R;

public class MyAlertDialog {
    Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    TextView messageView;
    LinearLayout buttonLayout;
    Window window ;
    public MyAlertDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
         window = ad.getWindow();
        window.setContentView(R.layout.myalertdialog);
        titleView=(TextView)window.findViewById(R.id.myalertdialog_head_title);
        messageView=(TextView)window.findViewById(R.id.myalertdialog_message);
        buttonLayout=(LinearLayout)window.findViewById(R.id.myalertdialog_buttonLayout);
    }
    public void setTitle(int resId)
    {
        titleView.setText(resId);
    }
    public void setTitle(String title) {
        titleView.setText(title);
    }
    public void setMessage(int resId) {
        messageView.setText(resId);
    } 	public void setMessage(String message)
    {
        messageView.setText(message);
    }
    /**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text,final View.OnClickListener listener)
    {
        Button button=new Button(context);
        LinearLayout.LayoutParams params=new LayoutParams(0, LayoutParams.MATCH_PARENT,1);
        button.setLayoutParams(params);
        button.setBackgroundResource(R.drawable.tooltipbtnright_selector_bg);
        button.setText(text);
        button.setTextSize(20);
        button.setGravity(Gravity.CENTER);
        button.setTextColor(context.getResources().getColor(R.color.lanse));
        button.setOnClickListener(listener);
        buttonLayout.addView(button);
    } 	/**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setNegativeButton(String text,final View.OnClickListener listener)
    {
        Button button=new Button(context);
        LinearLayout.LayoutParams params=new LayoutParams(0, LayoutParams.MATCH_PARENT,1);
        button.setLayoutParams(params);
        button.setBackgroundResource(R.drawable.tooltipbtnlift_selector_bg);
        button.setText(text);
        button.setTextColor(context.getResources().getColor(R.color.lanse));
        button.setGravity(Gravity.CENTER);
        button.setTextSize(20);
        button.setOnClickListener(listener);
        if(buttonLayout.getChildCount()>0)
        {
            params.setMargins(0, 0, 0, 0);
            button.setLayoutParams(params);
            buttonLayout.addView(button, 1);
        }else{
            button.setLayoutParams(params);
            buttonLayout.addView(button);
        } 	}
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    } }
