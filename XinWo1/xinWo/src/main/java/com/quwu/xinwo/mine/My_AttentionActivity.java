package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.My_AttentionAdapter;
import com.quwu.xinwo.bean.My_AttentionBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.My_AttentionEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;

public class My_AttentionActivity extends SwipeBackActivity implements
		OnClickListener {

	private XListView listView;
	private My_AttentionAdapter adapter;
	private List<My_AttentionBean> list;
	private List<My_AttentionEntity> json_list;

	private RelativeLayout my_attention_promptRel;
	private Button my_attention_promptBtn;
	private TextView my_attention_promptText;

	private int pageSize = 10;//一页加载多少条数据
	private int pageNo = 1;//多少页

	/**
	 * 返回参数
	 * */
	private String goods_photo;// 商品图片
	private String goods_price;// 价格
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String browse_person;// 关注人数
	private String browse_volume;// 浏览人数
	private String goods_id;// 商品id(int类型)
	private String panduan;// (1=众筹,2=出租,3=拍卖,4=白送,5=全新)
	private String total_person;// 总需人次
	private String surplus_person;// 剩余人次
	private String rent_date;// 租期
	private String rent_price;// 租金
	private String auction_currentprice;// 当前拍卖价格
	private String auction_person;// 拍卖参与人次

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(My_AttentionActivity.this);
			progressDialog.setMessage("正在加载中...");
			my_attention_promptRel.setVisibility(View.GONE);
			listView.setVisibility(View.INVISIBLE);
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
		setContentView(R.layout.my_attention);
		FinishActivity.finish(R.id.my_attentionRel, My_AttentionActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		my_attention_promptBtn = (Button) findViewById(R.id.my_attention_promptBtn);
		my_attention_promptRel = (RelativeLayout) findViewById(R.id.my_attention_promptRel);
		my_attention_promptRel.setOnClickListener(this);
		my_attention_promptText = (TextView) findViewById(R.id.my_attention_promptText);

		listView = (XListView) findViewById(R.id.my_account_listview);
		listView.setPullRefreshEnable(true);// 刷新
		listView.setPullLoadEnable(true);// 加载更多
		// 设置回调函数
		listView.setXListViewListener(new IXListViewListener() {
			public void onRefresh() {
				pageNo = 1;
				new LoadDataTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}

			public void onLoadMore() {
				pageNo++;
				new LoadDataTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
	}

	private class LoadDataTask extends AsyncTask<Void, Void, Void> {
		String result1;// 服务器返回json数据

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}

		protected Void doInBackground(Void... params) {
			if (pageNo == 1) {
				list = new ArrayList<My_AttentionBean>();
			}
			result1 = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/getcollection.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),
					"pageSize", String.valueOf(pageSize), "pageNo",
					String.valueOf(pageNo));
			System.out.println(result1);
			if (result1 != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(result1);
					String result = jsonObject.getString("1");
					JSONArray jsonArray=new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						String string=jsonArray.getString(i);
					if (string != null) {
						if (result.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (result.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(string,
									new TypeToken<List<My_AttentionEntity>>() {
									}.getType());
							for (int j = 0; j < json_list.size(); j++) {
								goods_photo = json_list.get(j).getGoods_photo();
								goods_price = json_list.get(j).getGoods_price();
								goods_name = json_list.get(j).getGoods_name();
								good_description = json_list.get(j)
										.getGood_description();
								browse_person = json_list.get(j)
										.getBrowse_person();
								browse_volume = json_list.get(j)
										.getBrowse_volume();
								goods_id = json_list.get(j).getGoods_id();
								panduan = json_list.get(j).getPanduan();
								total_person = json_list.get(j)
										.getTotal_person();
								surplus_person = json_list.get(j)
										.getSurplus_person();
								rent_date = json_list.get(j).getRent_date();
								rent_price = json_list.get(j).getRent_price();
								auction_currentprice = json_list.get(j)
										.getAuction_currentprice();
								auction_person = json_list.get(j)
										.getAuction_person();
								if (panduan.equals("5")) {
									list.add(new My_AttentionBean(
											MyApp.base_address + goods_photo,
											goods_name, good_description,
											browse_volume + "人关注",
											browse_person + "次关注", goods_price,
											"", panduan, goods_id));
								} else if (panduan.equals("1")) {
									list.add(new My_AttentionBean(
											MyApp.base_address + goods_photo,
											goods_name, good_description,
											browse_volume + "人关注",
											browse_person + "次关注",
											total_person, "剩余："
													+ surplus_person, panduan,
											goods_id));
								} else if (panduan.equals("2")) {
									list.add(new My_AttentionBean(
											MyApp.base_address + goods_photo,
											goods_name, good_description,
											browse_volume + "人关注",
											browse_person + "次关注", rent_price,
											"租期：" + rent_date + "月", panduan,
											goods_id));
								} else if (panduan.equals("3")) {
									list.add(new My_AttentionBean(
											MyApp.base_address + goods_photo,
											goods_name, good_description,
											browse_volume + "人关注",
											browse_person + "次关注",
											auction_currentprice, "竞拍人数："
													+ auction_person, panduan,
											goods_id));
								} else if (panduan.equals("4")) {
									list.add(new My_AttentionBean(
											MyApp.base_address + goods_photo,
											goods_name, good_description,
											browse_volume + "人关注",
											browse_person + "次关注", "28", "",
											panduan, goods_id));
								}
							}
							handler.sendEmptyMessageDelayed(0, 10);
						}
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				my_attention_promptRel.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter = new My_AttentionAdapter(getApplicationContext(),
							list);
					listView.setAdapter(adapter);
				} else {
					if (adapter != null) {
						adapter.notifyDataSetChanged();
					}
				}
				listView.stopRefresh();
				listView.stopLoadMore();
				stopProgressDialog();
				break;
			case 10000:
				my_attention_promptRel.setVisibility(View.VISIBLE);
				listView.setVisibility(View.GONE);
				my_attention_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				my_attention_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						my_attention_promptBtn, 0.27);
				stopProgressDialog();
				break;
			case 10001:
				if (pageNo == 1) {
					my_attention_promptRel.setVisibility(View.VISIBLE);
					listView.setVisibility(View.GONE);
					SetW_H.setRelativeLayout(getApplicationContext(),
							my_attention_promptBtn, 0.27);
				} else {
					MyToast.Toast(getApplicationContext(), "没有更多数据了哟");
				}
				stopProgressDialog();
				listView.stopRefresh();
				listView.stopLoadMore();
				break;
			default:
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_attention_promptRel:// 点击刷新页面（访问失败的情况下）
			new LoadDataTask().executeOnExecutor(Executors
					.newCachedThreadPool());
			break;

		default:
			break;
		}
	}
}
