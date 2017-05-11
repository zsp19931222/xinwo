package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.My_RedpacketAdapter;
import com.quwu.xinwo.bean.My_RedpacketBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.My_RedpacketEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;

/**
 * 
 * 红包界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class My_RedpacketActivity extends SwipeBackActivity implements
		OnClickListener {

	private PullToRefreshGridView gridView;
	private List<My_RedpacketBean> list;
	private My_RedpacketAdapter adapter;

	private RelativeLayout my_redpacket_promptLin;
	private Button my_redpacket_promptBtn;
	private TextView my_redpacket_promptText;

	private String redpacket_num;// 红包的数量（int类型）
	private String redexchange_state;// 状态是否拆分过（0=没有拆开1=已拆开）（int类型）
	private String redpacket_price;// 红包金额（int类型）
	private String redpacket_id;// 拆开红包传的数（不用于显示）（int类型）

	private List<My_RedpacketEntity> json_list;
	
	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(My_RedpacketActivity.this);
			progressDialog.setMessage("正在加载中...");
			my_redpacket_promptLin.setVisibility(View.GONE);
			gridView.setVisibility(View.GONE);
		}
		progressDialog.show();
	}

	private void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_redpacket);
		FinishActivity.finish(R.id.my_redpacketRel, My_RedpacketActivity.this);
	findID();
	new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		gridView = (PullToRefreshGridView) findViewById(R.id.my_redpacket_gridview);
		
		gridView.setMode(Mode.BOTH);
		gridView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			public void onLastItemVisible() {
				pageNo++;
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			}
		});

		gridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				pageNo = 1;
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				pageNo++;
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			}
		});
		
		my_redpacket_promptLin = (RelativeLayout) findViewById(R.id.my_redpacket_promptLin);
		my_redpacket_promptBtn = (Button) findViewById(R.id.my_redpacket_promptBtn);
		my_redpacket_promptText = (TextView) findViewById(R.id.my_redpacket_promptText);
		my_redpacket_promptLin.setOnClickListener(this);
	}

//	private void isGridView() {
//		list = new ArrayList<My_RedpacketBean>();
//		list.add(new My_RedpacketBean("23", "1"));
//		list.add(new My_RedpacketBean("22", "4"));
//		list.add(new My_RedpacketBean("2", "2"));
//		list.add(new My_RedpacketBean("3", "6"));
//		list.add(new My_RedpacketBean("23", "7"));
//		adapter = new My_RedpacketAdapter(getApplicationContext(), list,
//				My_RedpacketActivity.this);
//		gridView.setAdapter(adapter);
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_redpacket_promptLin:
			pageNo=1;
			new LoadDataTask().executeOnExecutor(Executors
					.newCachedThreadPool());
			break;

		default:
			break;
		}
	}

	private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNo==1) {
				startProgressDialog();
			}
		}

		protected Void doInBackground(Void... params) {
			list = new ArrayList<My_RedpacketBean>();
			String result = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/redpacket.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),"pageNo",String.valueOf(pageNo),"pageSize",String.valueOf(pageSize));
			JSONObject jsonObject;
			if (result != null) {
				try {
					jsonObject = new JSONObject(result);
					String result1 = jsonObject.getString("1");
					if (result1 != null) {
						if (result1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (result1.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(result1,
									new TypeToken<List<My_RedpacketEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								redpacket_num=json_list.get(i).getRedpacket_num();
								redexchange_state=json_list.get(i).getRedexchange_state();
								redpacket_price=json_list.get(i).getRedpacket_price();
								redpacket_id=json_list.get(i).getRedpacket_id();
								list.add(new My_RedpacketBean(redpacket_price, redpacket_num,redexchange_state));
							}
							handler.sendEmptyMessageAtTime(0, 10);
						}
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}
	};

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 10000:
				my_redpacket_promptLin.setVisibility(View.VISIBLE);
				gridView.setVisibility(View.GONE);
				my_redpacket_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				my_redpacket_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						my_redpacket_promptBtn, 0.27);
				stopProgressDialog();
				gridView.onRefreshComplete();
				break;
			case 10001:
				if (pageNo == 1) {
					my_redpacket_promptLin.setVisibility(View.VISIBLE);
					gridView.setVisibility(View.GONE);
					my_redpacket_promptText.setText("您还没有红包哦~\n赶快去参与吧！");
					SetW_H.setRelativeLayout(getApplicationContext(),
							my_redpacket_promptBtn, 0.27);
				} else {
					ILoadingLayout startLabels = gridView
							.getLoadingLayoutProxy(false, true);
					startLabels.setPullLabel("没有更多数据了哟");// 刚下拉时，显示的提示
					startLabels.setRefreshingLabel("没有更多数据了哟");// 刷新时
					startLabels.setReleaseLabel("没有更多数据了哟");// 下来达到一定距离时，显示的提示
				}
				stopProgressDialog();
				gridView.onRefreshComplete();
				break;
			case 0:
				gridView.setVisibility(View.VISIBLE);
				adapter = new My_RedpacketAdapter(getApplicationContext(),
						list, My_RedpacketActivity.this);
				gridView.setAdapter(adapter);
				stopProgressDialog();
				gridView.onRefreshComplete();
				break;

			default:
				break;
			}
		};
	};

}
