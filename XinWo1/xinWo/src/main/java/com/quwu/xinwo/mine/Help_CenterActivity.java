package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Help_CenterAdapter;
import com.quwu.xinwo.bean.Help_CenterBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.webpage.WebPage;

/**
 * 
 * 帮助中心界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

/**
 * 
 * 帮助界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class Help_CenterActivity extends SwipeBackActivity {

	private PullToRefreshListView listView;
	private Help_CenterAdapter adapter;
	private List<Help_CenterBean> list;

	private RelativeLayout off_the_shelf_promptRel;
	private Button off_the_shelf_promptBtn;
	private TextView off_the_shelf_promptText;

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(Help_CenterActivity.this);
			progressDialog.setMessage("正在加载中...");
			off_the_shelf_promptRel.setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
		}
		progressDialog.show();
	}

	private void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_center);
		FinishActivity.finish(R.id.help_center_returnRel, this);
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.help_center_listview);
		off_the_shelf_promptRel = (RelativeLayout) findViewById(R.id.off_the_shelf_promptRel);
		off_the_shelf_promptBtn = (Button) findViewById(R.id.off_the_shelf_promptBtn);
		off_the_shelf_promptText = (TextView) findViewById(R.id.off_the_shelf_promptText);
		off_the_shelf_promptRel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			startProgressDialog();
		}

		@Override
		protected Void doInBackground(Void... params) {
			list = new ArrayList<Help_CenterBean>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "usersaction/helpzie.do", "", "");
			System.out.println(string);
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(0, 10);
					} else if (string2.equals("没有数据")) {
						handler.sendEmptyMessageDelayed(1, 10);
					} else {
						JSONArray array = new JSONArray(string2);
						for (int i = 0; i < array.length(); i++) {
							JSONObject jsonObject2 = array.getJSONObject(i);
							String commonproblem_title = jsonObject2
									.getString("commonproblem_title");
							String commonproblem_url = jsonObject2
									.getString("commonproblem_url");
							list.add(new Help_CenterBean(commonproblem_title,
									commonproblem_url));
						}
						handler.sendEmptyMessageDelayed(2, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(0, 10);
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				off_the_shelf_promptRel.setVisibility(View.VISIBLE);
				off_the_shelf_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				off_the_shelf_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						off_the_shelf_promptBtn, 0.27);
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 1:
				off_the_shelf_promptRel.setVisibility(View.VISIBLE);
				off_the_shelf_promptBtn.setBackgroundResource(R.drawable.cry);
				off_the_shelf_promptText.setText("还没有相关数据哟~");
				SetW_H.setRelativeLayout(getApplicationContext(),
						off_the_shelf_promptBtn, 0.27);
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 2:
				listView.setVisibility(View.VISIBLE);
				adapter = new Help_CenterAdapter(getApplicationContext(), list);
				listView.setAdapter(adapter);
				listView.onRefreshComplete();
				stopProgressDialog();
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						int i=position-1;
						Intent intent=new Intent(Help_CenterActivity.this,WebPage.class);
						intent.putExtra("url", list.get(i).getCommonproblem_url());
						startActivity(intent);
					}
				});
				break;

			default:
				break;
			}
		};
	};
}
