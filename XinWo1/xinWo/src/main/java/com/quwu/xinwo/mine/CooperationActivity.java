package com.quwu.xinwo.mine;

import android.os.Bundle;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class CooperationActivity extends SwipeBackActivity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
setContentView(R.layout.cooperation);
FinishActivity.finish(R.id.cooperationRel, CooperationActivity.this);
}
}
