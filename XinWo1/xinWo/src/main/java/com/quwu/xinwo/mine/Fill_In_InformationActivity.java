package com.quwu.xinwo.mine;

import android.os.Bundle;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class Fill_In_InformationActivity extends SwipeBackActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fill_in_information);
		FinishActivity.finish(R.id.fii_returnRel, this);
	}
}
