package com.quwu.xinwo.mine;

import android.app.Activity;
import android.os.Bundle;

import com.quwu.xinwo.R;
import com.quwu.xinwo.until.FinishActivity;

public class ShareActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		FinishActivity.finish(R.id.share_cancleBtn, ShareActivity.this);
		FinishActivity.finish(R.id.share_Lin, ShareActivity.this);
	}
}
