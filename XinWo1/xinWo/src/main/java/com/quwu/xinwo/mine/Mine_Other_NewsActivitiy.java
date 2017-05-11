package com.quwu.xinwo.mine;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
/**
 * 
 * 其他消息界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class Mine_Other_NewsActivitiy extends SwipeBackActivity {

	private RelativeLayout returnRel;
	private PullToRefreshListView listView;
	
	private TextView mine_other_news_rentText;//租金
	private TextView mine_other_news_tenancytermText;//租期
	private Button mine_other_news_change_priceBtn;//修改价格
	
	private String BtnText;//按钮文字（上个页面传过来的）

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_other_news);
		findID();
	}

	private void findID() {
		mine_other_news_change_priceBtn=(Button) findViewById(R.id.mine_other_news_change_priceBtn);
		BtnText=this.getIntent().getExtras().getString("BtnText");
		if (BtnText!=null) {
			mine_other_news_change_priceBtn.setText(BtnText);
		}
		mine_other_news_rentText=(TextView) findViewById(R.id.mine_other_news_rentText);
		mine_other_news_tenancytermText=(TextView) findViewById(R.id.mine_other_news_tenancytermText);
		
		returnRel = (RelativeLayout) findViewById(R.id.mine_other_news_returnRel);
		returnRel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		listView = (PullToRefreshListView) findViewById(R.id.mine_other_news_listview);

	}
}
