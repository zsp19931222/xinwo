package com.quwu.xinwo.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;

public class Wipe_CacheActivity extends Activity {

	private LinearLayout layout;
	private Button cancleBtn;
	private Button confirmBtn;
	private TextView title;
	private TextView maessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wipe_cache);
		findID();
		setFinishOnTouchOutside(true);
	}

	private void findID() {
		layout = (LinearLayout) findViewById(R.id.wipe_cache_Lin);
		layout.setAlpha(0.8f);
		cancleBtn=(Button) findViewById(R.id.wipe_cache_cancleBtn);
		confirmBtn=(Button) findViewById(R.id.wipe_cache_confirmBtn);
		title=(TextView) findViewById(R.id.wipe_cache_head_title);
		maessage=(TextView) findViewById(R.id.wipe_cache_message);
		
		
		title.setText(this.getIntent().getExtras().getString("title"));
		maessage.setText(this.getIntent().getExtras().getString("maessage"));
		
			cancleBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			confirmBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

				}
			});
	}
}
