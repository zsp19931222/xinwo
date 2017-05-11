package com.quwu.xinwo.mine;

import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
/**
 * 
 * 留言界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

public class Mine_leave_MessageActivity extends SwipeBackActivity{
	
	private PullToRefreshListView listView;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mine_leave_message);
	findID();
	
}
private void findID() {
	FinishActivity.finish(R.id.mine_leave_message_returnRel, Mine_leave_MessageActivity.this);
	listView=(PullToRefreshListView) findViewById(R.id.mine_leave_messages_listview);
}
}
