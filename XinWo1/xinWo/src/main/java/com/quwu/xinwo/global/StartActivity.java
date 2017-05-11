package com.quwu.xinwo.global;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.quwu.xinwo.R;
import com.quwu.xinwo.main.Main_Activity;

public class StartActivity extends Activity {
	private CountDownTimer countDownTimer;

	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startactivity);
		countDownTimer = new CountDownTimer(1500, 1000) {
			public void onTick(long millisUntilFinished) {
			}
			public void onFinish() {
				countDownTimer.cancel();
				redirectTo();
			}
		};
		countDownTimer.start();
	}

	/**
	 * 跳转到...
	 */
	private void redirectTo() {
		Intent intent = new Intent(StartActivity.this, Main_Activity.class);
		StartActivity.this.startActivity(intent);
		StartActivity.this.finish();
	}
}
