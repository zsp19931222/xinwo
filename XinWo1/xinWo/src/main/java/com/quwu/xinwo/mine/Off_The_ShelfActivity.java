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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Off_The_ShelfAdapter;
import com.quwu.xinwo.bean.My_ReleaseBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Off_The_ShelfEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

public class Off_The_ShelfActivity extends SwipeBackActivity {

	private PullToRefreshListView listView;
	private List<My_ReleaseBean> list;
	private Off_The_ShelfAdapter adapter;
	private List<String> urlList;

	private RelativeLayout off_the_shelf_promptRel;
	private Button off_the_shelf_promptBtn;
	private TextView off_the_shelf_promptText;

	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页

	private String user_id;// 用户id
	private String goods_id;// 商品id
	private String goods_name;// 商品id
	private String goods_description;// 商品描述
	private String rent_time;// 租期
	private String rent_price;// 租金
	private String undercarriage_time;// 下架时间
	private String price;// 价格
	private String ending_price;// 当前价
	private String auction_person;// 参与人次
	private String class1;// （1=全价，2=众筹，3=拍卖，4=出租，5=免费送）
	private String reason;// 下架原因
	private String delayed1;// 延时次数
	private String total_person;// 总需
	private String participant_person;// 参与
	private String picture1;// 图片1
	private String picture2;// 图片2
	private String picture3;// 图片3
	private String picture4;// 图片4
	private String picture5;// 图片5
	private String picture6;// 图片6
	private String picture7;// 图片7
	private String picture8;// 图片8
	private String picture9;// 图片9
	private String picture10;// 图片10
	private List<Off_The_ShelfEntity> json_list;

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(Off_The_ShelfActivity.this);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.off_the_shelf);
		FinishActivity.finish(R.id.off_the_shelf_returnRel, this);
		// isListView();
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.off_the_shelf_listview);
		listView.setMode(Mode.BOTH);
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo = 1;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo++;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
		off_the_shelf_promptRel = (RelativeLayout) findViewById(R.id.off_the_shelf_promptRel);
		off_the_shelf_promptBtn = (Button) findViewById(R.id.off_the_shelf_promptBtn);
		off_the_shelf_promptText = (TextView) findViewById(R.id.off_the_shelf_promptText);
		off_the_shelf_promptRel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pageNo = 1;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			list = new ArrayList<My_ReleaseBean>();
			String string = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/undercarriage.do", "user_id",
					MySharePreferences.GetUser_ID(Off_The_ShelfActivity.this),
					"pageSize", String.valueOf(pageSize), "pageNo",
					String.valueOf(pageNo));
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(0, 10);
					} else if (string2.equals("没有数据")) {
						handler.sendEmptyMessageDelayed(1, 10);
					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string2,
								new TypeToken<List<Off_The_ShelfEntity>>() {
								}.getType());
						urlList = new ArrayList<String>();
						for (int i = 0; i < json_list.size(); i++) {
							user_id = json_list.get(i).getUser_id();
							goods_id = json_list.get(i).getGoods_id();
							goods_name = json_list.get(i).getGoods_name();
							goods_description = json_list.get(i)
									.getGoods_description();
							rent_time = json_list.get(i).getRent_time();
							rent_price = json_list.get(i).getRent_price();
							undercarriage_time = json_list.get(i)
									.getUndercarriage_time();
							price = json_list.get(i).getPrice();
							ending_price = json_list.get(i).getEnding_price();
							auction_person = json_list.get(i)
									.getAuction_person();
							class1 = json_list.get(i).getClass1();
							reason = json_list.get(i).getReason();
							delayed1 = json_list.get(i).getDelayed1();
							total_person=json_list.get(i).getTotal_person();
							participant_person=json_list.get(i).getParticipant_person();
							picture1 = json_list.get(i).getPicture1();
							picture2 = json_list.get(i).getPicture2();
							picture3 = json_list.get(i).getPicture3();
							picture4 = json_list.get(i).getPicture4();
							picture5 = json_list.get(i).getPicture5();
							picture6 = json_list.get(i).getPicture6();
							picture7 = json_list.get(i).getPicture7();
							picture8 = json_list.get(i).getPicture8();
							picture9 = json_list.get(i).getPicture9();
							picture10 = json_list.get(i).getPicture10();
							if (picture1 != null && !picture1.equals("")) {
								urlList.add(MyApp.base_address + picture1);
							} else if (picture2 != null && !picture2.equals("")) {
								urlList.add(MyApp.base_address + picture2);
							} else if (picture3 != null && !picture3.equals("")) {
								urlList.add(MyApp.base_address + picture3);
							} else if (picture4 != null && !picture4.equals("")) {
								urlList.add(MyApp.base_address + picture4);
							} else if (picture5 != null && !picture5.equals("")) {
								urlList.add(MyApp.base_address + picture5);
							} else if (picture6 != null && !picture6.equals("")) {
								urlList.add(MyApp.base_address + picture6);
							} else if (picture7 != null && !picture7.equals("")) {
								urlList.add(MyApp.base_address + picture7);
							} else if (picture8 != null && !picture8.equals("")) {
								urlList.add(MyApp.base_address + picture8);
							} else if (picture9 != null && !picture9.equals("")) {
								urlList.add(MyApp.base_address + picture9);
							} else if (picture10 != null
									&& !picture10.equals("")) {
								urlList.add(MyApp.base_address + picture10);
							}
							if (class1.equals("1")) {
								list.add(new My_ReleaseBean(
										goods_name,
										goods_description,
										"",
										"",
										price,
										"",
										"",
										"",
										"",
										urlList,
										0,
										"商品状态：已下架",
										"下架时间："
												+ TimeUtils.Time2(Long
														.valueOf(undercarriage_time)),
										"下架原因：" + reason));
							} else if (class1.equals("2")) {
								list.add(new My_ReleaseBean(
										goods_name,
										goods_description,
										"",
										"",
										total_person,
										participant_person,
										"",
										"",
										"",
										urlList,
										1,
										"商品状态：已下架",
										"下架时间："
												+ TimeUtils.Time2(Long
														.valueOf(undercarriage_time)),
										"下架原因：" + reason));
							} else if (class1.equals("3")) {
								list.add(new My_ReleaseBean(
										goods_name,
										goods_description,
										"",
										"",
										ending_price,
										"",
										"",
										auction_person + "人参与",
										"第" + delayed1 + "次延时",
										urlList,
										3,
										"商品状态：已下架",
										"下架时间："
												+ TimeUtils.Time2(Long
														.valueOf(undercarriage_time)),
										"下架原因：" + reason));
							} else if (class1.equals("4")) {
								list.add(new My_ReleaseBean(
										goods_name,
										goods_description,
										"",
										"",
										rent_price,
										"",
										rent_time,
										"",
										"",
										urlList,
										2,
										"商品状态：已下架",
										"下架时间："
												+ TimeUtils.Time2(Long
														.valueOf(undercarriage_time)),
										"下架原因：" + reason));
							} else if (class1.equals("5")) {
								list.add(new My_ReleaseBean(
										goods_name,
										goods_description,
										"",
										"",
										price,
										"",
										"",
										"",
										"",
										urlList,
										4,
										"商品状态：已下架",
										"下架时间："
												+ TimeUtils.Time2(Long
														.valueOf(undercarriage_time)),
										"下架原因：" + reason));
							}
						}
						handler.sendEmptyMessageDelayed(2, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				handler.sendEmptyMessageDelayed(0, 10);
			
			}
			return null;
		}
	}
	private Handler handler=new Handler(){
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
				if (pageNo==1) {
				off_the_shelf_promptRel.setVisibility(View.VISIBLE);
				off_the_shelf_promptBtn
						.setBackgroundResource(R.drawable.cry);
				off_the_shelf_promptText.setText("您还没有下架商品哦~");
				SetW_H.setRelativeLayout(getApplicationContext(),
						off_the_shelf_promptBtn, 0.27);
				}else {
					ILoadingLayout startLabels = listView
							.getLoadingLayoutProxy(false, true);
					startLabels.setPullLabel("没有更多数据了哟");// 刚下拉时，显示的提示
					startLabels.setRefreshingLabel("没有更多数据了哟");// 刷新时
					startLabels.setReleaseLabel("没有更多数据了哟");// 下来达到一定距离时，显示的提示
				}
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 2:
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter = new Off_The_ShelfAdapter(Off_The_ShelfActivity.this, list, Off_The_ShelfActivity.this);
					listView.setAdapter(adapter);
				} else {
					if (adapter != null) {
						adapter.notifyDataSetChanged();
					}
				}
				listView.onRefreshComplete();
				stopProgressDialog();
				break;

			default:
				break;
			}
		};
	};
}
