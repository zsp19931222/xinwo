package com.quwu.xinwo.until;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 获取验证码倒计时
 * */
public class MyCountDownTimer extends CountDownTimer {
	
	private TextView vertifyView;

	public MyCountDownTimer(long millisInFuture, long countDownInterval,TextView vertifyView) {
		super(millisInFuture, countDownInterval);
		this.vertifyView=vertifyView;
	}

	public void onTick(long millisUntilFinished) {
		vertifyView.setText((millisUntilFinished / 1000) + "秒后可重发"); 
		vertifyView.setEnabled(false);
	}

	public void onFinish() {
		 vertifyView.setEnabled(true);  
         vertifyView.setText("获取验证码");
	}

}
