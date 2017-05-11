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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Deal_RecordAdapter;
import com.quwu.xinwo.bean.Deal_RecordBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Deal_RecordEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

public class Deal_RecordActivity extends SwipeBackActivity implements
		OnClickListener {
	/**
	 * 
	 * 最近三个月
	 * 
	 * */

	private LinearLayout deal_record_three_monthsLin;
	private TextView deal_record_three_monthsText;
	private ImageView deal_record_three_monthsImage;

	/**
	 * 
	 * 本月
	 * 
	 * */

	private LinearLayout deal_record_monthLin;
	private TextView deal_record_monthText;
	private ImageView deal_record_monthImage;

	/**
	 * 
	 * 本周
	 * 
	 * */

	private LinearLayout deal_record_weekLin;
	private TextView deal_record_weekText;
	private ImageView deal_record_weekImage;

	/**
	 * 
	 * 所有
	 * 
	 * */

	private LinearLayout deal_record_allLin;
	private TextView deal_record_allText;
	private ImageView deal_record_allImage;

	private PullToRefreshListView listView;
	private Deal_RecordAdapter adapter;
	private List<Deal_RecordBean> list;

	private RelativeLayout deal_record_promptLin;
	private Button deal_record_promptBtn;
	private TextView deal_record_promptText;
	/**
	 * 服务器返回的数据
	 * */
	private String describe;//描述（比如充值，红包，竞拍商品等）（String类型）
	private String	money;//金额（int类型）
	private String	transactionrecord_time;//交易时间（int类型）
	private String	goods_name;//商品名称（String类型）
	private List<Deal_RecordEntity> json_list;
	
	private int index = 1;// 点击了哪一个选项（1：众筹，2：拍卖）
	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页
	
	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(Deal_RecordActivity.this);
			progressDialog.setMessage("正在加载中...");
			deal_record_promptLin.setVisibility(View.GONE);
			listView.setVisibility(View.GONE);
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deal_record);
		FinishActivity.finish(R.id.deal_recordRel, Deal_RecordActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.deal_record_listview);
		listView.setMode(Mode.BOTH);
		listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			public void onLastItemVisible() {
				pageNo++;
				if (index == 1) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "1");
				} else if (index == 2) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "2");
				}  else if (index == 3) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "3");
				} else if (index == 4) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "4");
				}
			}
		});

		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo = 1;
				if (index == 1) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "1");
				} else if (index == 2) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "2");
				} else if (index == 3) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "3");
				} else if (index == 4) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "4");
				}
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo++;
				if (index == 1) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "1");
				} else if (index == 2) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "2");
				} else if (index == 3) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "3");
				} else if (index == 4) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "4");
				}
			}
		});
		deal_record_promptLin = (RelativeLayout) findViewById(R.id.deal_record_promptLin);
		deal_record_promptBtn = (Button) findViewById(R.id.deal_record_promptBtn);
		deal_record_promptText = (TextView) findViewById(R.id.deal_record_promptText);
		deal_record_promptLin.setOnClickListener(this);
		
		deal_record_three_monthsLin = (LinearLayout) findViewById(R.id.deal_record_three_monthsLin);
		deal_record_monthLin = (LinearLayout) findViewById(R.id.deal_record_monthLin);
		deal_record_weekLin = (LinearLayout) findViewById(R.id.deal_record_weekLin);
		deal_record_allLin = (LinearLayout) findViewById(R.id.deal_record_allLin);

		deal_record_three_monthsLin.setOnClickListener(this);
		deal_record_monthLin.setOnClickListener(this);
		deal_record_weekLin.setOnClickListener(this);
		deal_record_allLin.setOnClickListener(this);

		deal_record_three_monthsText = (TextView) findViewById(R.id.deal_record_three_monthsText);
		deal_record_monthText = (TextView) findViewById(R.id.deal_record_monthText);
		deal_record_weekText = (TextView) findViewById(R.id.deal_record_weekText);
		deal_record_allText = (TextView) findViewById(R.id.deal_record_allText);

		deal_record_three_monthsImage = (ImageView) findViewById(R.id.deal_record_three_monthsImage);
		deal_record_monthImage = (ImageView) findViewById(R.id.deal_record_monthImage);
		deal_record_weekImage = (ImageView) findViewById(R.id.deal_record_weekImage);
		deal_record_allImage = (ImageView) findViewById(R.id.deal_record_allImage);
	}

	@Override
	public void onClick(View v) {
		isCheckLin();
		switch (v.getId()) {
		case R.id.deal_record_three_monthsLin:
			index=1;
			pageNo=1;
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
			deal_record_three_monthsText.setTextColor(this.getResources()
					.getColor(R.color.淡红色));
			deal_record_three_monthsImage.setVisibility(View.VISIBLE);
			break;
		case R.id.deal_record_monthLin:
			index=2;
			pageNo=1;
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "2");
			deal_record_monthText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			deal_record_monthImage.setVisibility(View.VISIBLE);
			
			break;
		case R.id.deal_record_weekLin:
			index=3;
			pageNo=1;
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "3");
			deal_record_weekText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			deal_record_weekImage.setVisibility(View.VISIBLE);
			break;
		case R.id.deal_record_allLin:
			index=4;
			pageNo=1;
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "4");
			deal_record_allText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			deal_record_allImage.setVisibility(View.VISIBLE);
			break;
		case R.id.deal_record_promptLin:
			pageNo = 1;
			if (index == 1) {
				deal_record_three_monthsText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				deal_record_three_monthsImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "1");
			} else if (index == 2) {
				deal_record_monthText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				deal_record_monthImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "2");
			} else if (index == 3) {
				deal_record_weekText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				deal_record_weekImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "3");
			} else if (index == 4) {
				deal_record_allText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				deal_record_allImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "4");
			}
			break;
		default:
			break;
		}
	}

	private void isCheckLin() {
		deal_record_three_monthsText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		deal_record_monthText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		deal_record_weekText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		deal_record_allText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		deal_record_three_monthsImage.setVisibility(View.INVISIBLE);
		deal_record_monthImage.setVisibility(View.INVISIBLE);
		deal_record_weekImage.setVisibility(View.INVISIBLE);
		deal_record_allImage.setVisibility(View.INVISIBLE);
	}

	
	private class LoadDataTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}

		protected Void doInBackground(String... params) {
			if (pageNo == 1) {
					list = new ArrayList<Deal_RecordBean>();
			}
			String result = OKHTTP_POST.doPost4(MyApp.base_address
					+ "usersaction/transaction.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()), "state",
					params[0], "pageNo", String.valueOf(pageNo), "pageSize",
					String.valueOf(pageSize));
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
							handler.sendMessage(handler.obtainMessage(
									MyApp.NODATA, params[0]));
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(result1,
									new TypeToken<List<Deal_RecordEntity>>() {
									}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							describe=json_list.get(i).getDescribe();
							money=json_list.get(i).getMoney();
							transactionrecord_time=json_list.get(i).getTransactionrecord_time();
							goods_name=json_list.get(i).getGoods_name();
							long l=Long.valueOf(transactionrecord_time);
							if (goods_name.equals("")) {
								list.add(new Deal_RecordBean(TimeUtils.DealTime1(l), TimeUtils.DealTime2(l), describe,
										"+¥"+money));
							}else {
								list.add(new Deal_RecordBean(TimeUtils.DealTime1(l), TimeUtils.DealTime2(l), describe+"（"+goods_name+"）",
										"-¥"+money));
							}
						}
							handler.sendMessage(handler.obtainMessage(0,
									params[0]));
						}
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
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
				deal_record_promptLin.setVisibility(View.VISIBLE);
				listView.setVisibility(View.GONE);
				deal_record_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				deal_record_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						deal_record_promptBtn, 0.27);
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			case 10001:
				if (pageNo == 1) {
					deal_record_promptLin.setVisibility(View.VISIBLE);
					listView.setVisibility(View.GONE);
					String string = (String) msg.obj;
					if (string.equals("1")) {
						deal_record_promptText.setText("您还没有最近三个月的交易记录哦~");
					} else if(string.equals("2")){
						deal_record_promptText.setText("您还没有本月的交易记录哦~");
					}else if(string.equals("3")){
						deal_record_promptText.setText("您还没有本周的交易记录哦~");
					}else if(string.equals("4")){
						deal_record_promptText.setText("您还没有所有的交易记录哦~");
					}
					SetW_H.setRelativeLayout(getApplicationContext(),
							deal_record_promptBtn, 0.27);
				} else {
					ILoadingLayout startLabels = listView
							.getLoadingLayoutProxy(false, true);
					startLabels.setPullLabel("没有更多数据了哟");// 刚下拉时，显示的提示
					startLabels.setRefreshingLabel("没有更多数据了哟");// 刷新时
					startLabels.setReleaseLabel("没有更多数据了哟");// 下来达到一定距离时，显示的提示
				}
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			case 0:
				deal_record_promptLin.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter=new Deal_RecordAdapter(list, getApplicationContext());
					listView.setAdapter(adapter);
				} else {
					if (adapter != null) {
						adapter.notifyDataSetChanged();
					}
				}
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			case 1:
				break;

			default:
				break;
			}
		};
	};
}
