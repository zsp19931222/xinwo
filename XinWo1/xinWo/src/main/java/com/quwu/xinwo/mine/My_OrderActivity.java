package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.My_OrderAdapter;
import com.quwu.xinwo.bean.My_OrderBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.My_OrderEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

/**
 * 
 * 我的订单界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class My_OrderActivity extends SwipeBackActivity implements
		OnClickListener {

	private List<My_OrderBean> list;
	private My_OrderAdapter adapter;
	private PullToRefreshListView listView;
	private ImageLoader imageLoader;

	private RelativeLayout my_order_promptLin;
	private Button my_order_promptBtn;
	private TextView my_order_promptText;

	private String goods_description;// 商品描述（String类型）
	private String goods_photo;// 商品图片（String类型）
	private String auction_startprice;// 起拍价（拍卖的）（int类型）
	private String goods_price;// 价格（全价的）（int类型）
	private String orderinfo_state;// 属于哪一个分类（1=全价,2=众筹,3=出租,4=免费送）（int类型）
	private String auction_currentprice;// 成交价（拍卖的）（int类型）
	private String rent_date;// 租期（出租的）（int类型）
	private String goods_id;// 商品id（点击去付款时会用到）（int类型）
	private String addorder_time;// 下单时间（int类型）
	private String goods_name;// 商品名字（String类型）
	private String rent_price;// 租金（出租的）（int类型）
	private List<My_OrderEntity> json_list;

	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(My_OrderActivity.this);
			progressDialog.setMessage("正在加载中...");
			my_order_promptLin.setVisibility(View.GONE);
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order);
		imageLoader = ImageLoader.getInstance();
		FinishActivity.finish(R.id.my_orderRel, My_OrderActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.my_order_listview);

		listView.setMode(Mode.BOTH);
		listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			public void onLastItemVisible() {
				pageNo++;
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			}
		});

		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo = 1;
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo++;
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			}
		});
		
		my_order_promptLin = (RelativeLayout) findViewById(R.id.my_order_promptLin);
		my_order_promptBtn = (Button) findViewById(R.id.my_order_promptBtn);
		my_order_promptText = (TextView) findViewById(R.id.my_order_promptText);
		my_order_promptLin.setOnClickListener(this);

	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_order_promptLin:
			pageNo = 1;
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
			break;

		default:
			break;
		}
	}

	private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}

		protected Void doInBackground(Void... params) {
			if (pageNo == 1) {
				list = new ArrayList<My_OrderBean>();
			}
			String result = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/myorder.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),
					"pageNo", String.valueOf(pageNo), "pageSize",
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
							handler.sendEmptyMessageDelayed(	MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(result1,
									new TypeToken<List<My_OrderEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								goods_description=json_list.get(i).getGoods_description();
								goods_photo=json_list.get(i).getGoods_photo();
								auction_startprice=json_list.get(i).getAuction_startprice();
								goods_price=json_list.get(i).getGoods_price();
								orderinfo_state=json_list.get(i).getOrderinfo_state();
								auction_currentprice=json_list.get(i).getAuction_currentprice();
								rent_date=json_list.get(i).getRent_date();
								goods_id=json_list.get(i).getGoods_id();
								addorder_time=json_list.get(i).getAddorder_time();
								goods_name=json_list.get(i).getGoods_name();
								rent_price=json_list.get(i).getRent_price();
								long l=Long.valueOf(addorder_time);
								Log.e("", orderinfo_state);
								if (orderinfo_state.equals("1")) {
									list.add(new My_OrderBean(MyApp.base_address+goods_photo, goods_name, goods_description, "", "价格：¥"+goods_price, "", TimeUtils.Time1(l)));
								}else if (orderinfo_state.equals("2")) {
									list.add(new My_OrderBean(MyApp.base_address+goods_photo, goods_name, goods_description, "", "租金："+rent_price+"元/月", rent_date, TimeUtils.Time1(l)));
								}else if (orderinfo_state.equals("3")) {
									list.add(new My_OrderBean(MyApp.base_address+goods_photo, goods_name, goods_description, "¥："+auction_startprice, "成交价："+auction_currentprice, "", TimeUtils.Time1(l)));
								}
							}
							handler.sendEmptyMessageAtTime(0, 10);
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
				my_order_promptLin.setVisibility(View.VISIBLE);
				listView.setVisibility(View.GONE);
				my_order_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				my_order_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						my_order_promptBtn, 0.27);
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			case 10001:
				if (pageNo == 1) {
					my_order_promptLin.setVisibility(View.VISIBLE);
					listView.setVisibility(View.GONE);
					my_order_promptText.setText("您还没有订单哦~");
					SetW_H.setRelativeLayout(getApplicationContext(),
							my_order_promptBtn, 0.27);
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
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter = new My_OrderAdapter(getApplicationContext(),
							list, imageLoader);
					listView.setAdapter(adapter);
				} else {
					if (adapter != null) {
						adapter.notifyDataSetChanged();
					}
				}
				listView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
						true, true));
				stopProgressDialog();
				listView.onRefreshComplete();
				break;

			default:
				break;
			}
		};
	};
}
