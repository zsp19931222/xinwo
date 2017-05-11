package com.quwu.xinwo.mine;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;

/**
 * 
 * 系统界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

public class Mine_System_MessageActivity extends SwipeBackActivity implements
		OnClickListener {
	private RelativeLayout returnRel;
	private PullToRefreshListView listView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_system_messages);
		findID();
	}

	private void findID() {
		returnRel = (RelativeLayout) findViewById(R.id.mine_system_message_returnRel);
		returnRel.setOnClickListener(this);
		
		listView=(PullToRefreshListView) findViewById(R.id.mine_system_messages_listview);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mine_system_message_returnRel:
			finish();
			break;

		default:
			break;
		}
	}
}
