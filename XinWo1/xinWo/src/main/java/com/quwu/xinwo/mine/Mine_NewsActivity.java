package com.quwu.xinwo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.Tool;

/**
 * 
 * 消息界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

public class Mine_NewsActivity extends SwipeBackActivity implements
		OnClickListener {

	private RelativeLayout mine_news_returnRel;// 返回

	private LinearLayout mine_news_teamLin;// 团队
	private LinearLayout mine_news_buyLin;// 购买
	private LinearLayout mine_news_auctionLin;// 竞拍
	private LinearLayout mine_news_sellLin;// 出售
	private LinearLayout mine_news_leaveLin;// 留言

	private TextView mine_news_team_messageText;// 团队消息
	private TextView mine_news_buy_messageText;// 购买消息
	private TextView mine_news_auction_messageText;// 竞拍消息
	private TextView mine_news_sell_messageText;// 出售消息
	private TextView mine_news_leave_messageText;// 留言消息

	private TextView mine_news_team_timeText;// 团队消息时间
	private TextView mine_news_buy_timeText;// 购买消息时间
	private TextView mine_news_auction_timeText;// 竞拍消息时间
	private TextView mine_news_sell_timeText;// 出售消息时间
	private TextView mine_news_leave_message_timeText;// 留言消息时间

	private TextView mine_news_team_message_numText;// 团队消息数量
	private TextView mine_news_buy_message_numText;// 购买消息数量
	private TextView mine_news_auction_message_numText;// 竞拍消息数量
	private TextView mine_news_sell_message_numText;// 出售消息数量
	private TextView mine_news_leave_message_numText;// 留言消息数量

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_news);
		findID();
	}

	private void findID() {
		mine_news_returnRel = (RelativeLayout) findViewById(R.id.mine_news_returnRel);
		mine_news_returnRel.setOnClickListener(this);

		mine_news_teamLin = (LinearLayout) findViewById(R.id.mine_news_teamLin);
		mine_news_buyLin = (LinearLayout) findViewById(R.id.mine_news_buyLin);
		mine_news_auctionLin = (LinearLayout) findViewById(R.id.mine_news_auctionLin);
		mine_news_sellLin = (LinearLayout) findViewById(R.id.mine_news_sellLin);
		mine_news_leaveLin = (LinearLayout) findViewById(R.id.mine_news_leaveLin);

		mine_news_teamLin.setOnClickListener(this);
		mine_news_buyLin.setOnClickListener(this);
		mine_news_sellLin.setOnClickListener(this);
		mine_news_auctionLin.setOnClickListener(this);
		mine_news_leaveLin.setOnClickListener(this);

		mine_news_team_messageText = (TextView) findViewById(R.id.mine_news_team_messageText);
		mine_news_buy_messageText = (TextView) findViewById(R.id.mine_news_buy_messageText);
		mine_news_auction_messageText = (TextView) findViewById(R.id.mine_news_auction_messageText);
		mine_news_sell_messageText = (TextView) findViewById(R.id.mine_news_sell_messageText);
		mine_news_team_messageText = (TextView) findViewById(R.id.mine_news_team_messageText);

		mine_news_team_timeText = (TextView) findViewById(R.id.mine_news_team_timeText);
		mine_news_buy_timeText = (TextView) findViewById(R.id.mine_news_buy_timeText);
		mine_news_auction_timeText = (TextView) findViewById(R.id.mine_news_auction_timeText);
		mine_news_sell_timeText = (TextView) findViewById(R.id.mine_news_sell_timeText);
		mine_news_leave_message_timeText = (TextView) findViewById(R.id.mine_news_leave_message_timeText);

		mine_news_team_message_numText = (TextView) findViewById(R.id.mine_news_team_message_numText);
		mine_news_buy_message_numText = (TextView) findViewById(R.id.mine_news_buy_message_numText);
		mine_news_auction_message_numText = (TextView) findViewById(R.id.mine_news_auction_time_numText);
		mine_news_sell_message_numText = (TextView) findViewById(R.id.mine_news_sell_time_numText);
		mine_news_leave_message_numText = (TextView) findViewById(R.id.mine_news_leave_message_time_numText);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.mine_news_returnRel:
				finish();
				break;
			case R.id.mine_news_teamLin:
				intent = new Intent(Mine_NewsActivity.this,
						Mine_System_MessageActivity.class);
				break;
			case R.id.mine_news_buyLin:
				intent = new Intent(Mine_NewsActivity.this,
						Mine_Other_NewsActivitiy.class);
				intent.putExtra("BtnText", "修改价格");
				break;
			case R.id.mine_news_auctionLin:
				intent = new Intent(Mine_NewsActivity.this,
						Mine_Other_NewsActivitiy.class);
				intent.putExtra("BtnText", "卖给他了");
				break;
			case R.id.mine_news_sellLin:
				intent = new Intent(Mine_NewsActivity.this,
						Mine_Other_NewsActivitiy.class);
				intent.putExtra("BtnText", "修改价格");
				break;
			case R.id.mine_news_leaveLin:
				intent = new Intent(Mine_NewsActivity.this,
						Mine_leave_MessageActivity.class);
				break;
			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}
}
