package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
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
import com.quwu.xinwo.adapter.My_ReleaseAdapter;
import com.quwu.xinwo.bean.My_ReleaseBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.My_ReleaseEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.Tool;

public class My_ReleaseActivity extends SwipeBackActivity implements
		OnClickListener {

	private PullToRefreshListView listView;
	private My_ReleaseAdapter adapter;
	private List<My_ReleaseBean> list;
	private List<String> urlList;
	private TextView my_release_off_the_shelfText;

	private RelativeLayout my_release_promptRel;
	private Button my_release_promptBtn;
	private TextView my_release_promptText;

	/**
	 * 
	 * 众筹
	 * 
	 * */
	private LinearLayout myrelease_crowdfundingLin;
	private TextView myrelease_crowdfundingText;
	private ImageView myrelease_crowdfundingImage;
	/**
	 * 
	 * 全价
	 * 
	 * */
	private LinearLayout myrelease_allLin;
	private TextView myrelease_allText;
	private ImageView myrelease_allImage;
	/**
	 * 
	 * 出租
	 * 
	 * */
	private LinearLayout myrelease_rent_outLin;
	private TextView myrelease_rent_outText;
	private ImageView myrelease_rent_outImage;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private LinearLayout myrelease_auctionLin;
	private TextView myrelease_auctionText;
	private ImageView myrelease_auctionImage;

	/**
	 * 
	 * 数据库返回数据
	 * 
	 * */
	private String goods_price;// 商品价格（int类型）
	private String goods_name;// 商品名称（String类型）
	private String browse_person;// 多少人喜欢（int类型）
	private String detailed_description;// 商品描述（String类型）
	private String browse_volume;// 浏览人次（int类型）
	private String total_person;// 总需人次（int类型）
	private String participant_person;// 参与人次（int类型）
	private String rent_date;// 租期（int类型）
	private String rent_price;// 租金（int类型）
	private String auction_currentprice;// 当前价（int类型）
	private String auction_person;// 好多人参与？（int类型）
	private String photo1;// 商品图片1（String类型）
	private String photo2;// 商品图片2（String类型）
	private String photo3;// 商品图片3（String类型）
	private String photo4;// 商品图片1（String类型）
	private String photo5;// 商品图片2（String类型）
	private String photo6;// 商品图片3（String类型）
	private String photo7;// 商品图片1（String类型）
	private String photo8;// 商品图片2（String类型）
	private String photo9;// 商品图片3（String类型）
	private String photo10;// 商品图片3（String类型）

	private List<My_ReleaseEntity> json_list;

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(My_ReleaseActivity.this);
			progressDialog.setMessage("正在加载中...");
			my_release_promptRel.setVisibility(View.GONE);
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

	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页
	private int index = 1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_release);
		FinishActivity.finish(R.id.my_releaseRel, My_ReleaseActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
				"1");
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.my_release_listview);
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
				} else if (index == 3) {
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

		my_release_promptRel = (RelativeLayout) findViewById(R.id.my_release_promptRel);
		my_release_promptBtn = (Button) findViewById(R.id.my_release_promptBtn);
		my_release_promptText = (TextView) findViewById(R.id.my_release_promptText);
		my_release_promptRel.setOnClickListener(this);

		my_release_off_the_shelfText = (TextView) findViewById(R.id.my_release_off_the_shelfText);
		my_release_off_the_shelfText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (Tool.isFastDoubleClick()) {
					return;
				}else {
				Intent intent = new Intent(My_ReleaseActivity.this,
						Off_The_ShelfActivity.class);
				startActivity(intent);				
				}
			}
		});

		myrelease_crowdfundingLin = (LinearLayout) findViewById(R.id.myrelease_crowdfundingLin);
		myrelease_allLin = (LinearLayout) findViewById(R.id.myrelease_allLin);
		myrelease_rent_outLin = (LinearLayout) findViewById(R.id.myrelease_rent_outLin);
		myrelease_auctionLin = (LinearLayout) findViewById(R.id.myrelease_auctionLin);

		myrelease_crowdfundingLin.setOnClickListener(this);
		myrelease_allLin.setOnClickListener(this);
		myrelease_rent_outLin.setOnClickListener(this);
		myrelease_auctionLin.setOnClickListener(this);

		myrelease_crowdfundingText = (TextView) findViewById(R.id.myrelease_crowdfundingText);
		myrelease_allText = (TextView) findViewById(R.id.myrelease_allText);
		myrelease_rent_outText = (TextView) findViewById(R.id.myrelease_rent_outText);
		myrelease_auctionText = (TextView) findViewById(R.id.myrelease_auctionText);

		myrelease_crowdfundingImage = (ImageView) findViewById(R.id.myrelease_crowdfundingImage);
		myrelease_allImage = (ImageView) findViewById(R.id.myrelease_allImage);
		myrelease_rent_outImage = (ImageView) findViewById(R.id.myrelease_rent_outImage);
		myrelease_auctionImage = (ImageView) findViewById(R.id.myrelease_auctionImage);
	}

	public void onClick(View v) {
		init();
		switch (v.getId()) {
		case R.id.myrelease_crowdfundingLin:
			index = 2;
			pageNo = 1;
			myrelease_crowdfundingText.setTextColor(this.getResources()
					.getColor(R.color.淡红色));
			myrelease_crowdfundingImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(
					Executors.newCachedThreadPool(), "2");
			break;
		case R.id.myrelease_allLin:
			index = 1;
			pageNo = 1;
			myrelease_allText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			myrelease_allImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(
					Executors.newCachedThreadPool(), "1");
			break;
		case R.id.myrelease_rent_outLin:
			index = 3;
			pageNo = 1;
			myrelease_rent_outText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			myrelease_rent_outImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(
					Executors.newCachedThreadPool(), "3");
			break;
		case R.id.myrelease_auctionLin:
			index = 4;
			pageNo = 1;
			myrelease_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			myrelease_auctionImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(
					Executors.newCachedThreadPool(), "4");
			break;
		case R.id.my_release_promptRel:
			pageNo = 1;
			if (index == 1) {
				myrelease_allText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				myrelease_allImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "1");
			} else if (index == 2) {
				myrelease_crowdfundingText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				myrelease_crowdfundingImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "2");
			} else if (index == 3) {
				myrelease_rent_outText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				myrelease_rent_outImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "3");
			} else if (index == 4) {
				myrelease_auctionText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				myrelease_auctionImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "4");
			}
			break;

		default:
			break;
		}
	}

	private void init() {
		myrelease_crowdfundingText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		myrelease_allText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		myrelease_rent_outText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		myrelease_auctionText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		myrelease_crowdfundingImage.setVisibility(View.INVISIBLE);
		myrelease_allImage.setVisibility(View.INVISIBLE);
		myrelease_rent_outImage.setVisibility(View.INVISIBLE);
		myrelease_auctionImage.setVisibility(View.INVISIBLE);
	}

	private class LoadDataTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}

		protected Void doInBackground(String... params) {
			if (pageNo == 1) {
				list = new ArrayList<My_ReleaseBean>();
			}
			String result = OKHTTP_POST.doPost4(MyApp.base_address
					+ "usersaction/getrelease.do", "user_id",
					MySharePreferences.GetUser_ID(getApplicationContext()),
					"state", params[0], "pageNo", String.valueOf(pageNo),
					"pageSize", String.valueOf(pageSize));

			if (result != null) {
				JSONObject jsonObject;
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
									new TypeToken<List<My_ReleaseEntity>>() {
									}.getType());
							urlList = new ArrayList<String>();
							for (int i = 0; i < json_list.size(); i++) {
								goods_price = json_list.get(i).getGoods_price();
								goods_name = json_list.get(i).getGoods_name();
								browse_person = json_list.get(i)
										.getBrowse_person();
								detailed_description = json_list.get(i)
										.getDetailed_description();
								browse_volume = json_list.get(i)
										.getBrowse_volume();
								total_person = json_list.get(i)
										.getTotal_person();
								participant_person = json_list.get(i)
										.getParticipant_person();
								rent_date = json_list.get(i).getRent_date();
								rent_price = json_list.get(i).getRent_price();
								auction_currentprice = json_list.get(i)
										.getAuction_currentprice();
								auction_person = json_list.get(i)
										.getAuction_person();
								photo1 = json_list.get(i).getPhoto1();
								photo2 = json_list.get(i).getPhoto2();
								photo3 = json_list.get(i).getPhoto3();
								photo4 = json_list.get(i).getPhoto4();
								photo5 = json_list.get(i).getPhoto5();
								photo6 = json_list.get(i).getPhoto6();
								photo7 = json_list.get(i).getPhoto7();
								photo8 = json_list.get(i).getPhoto8();
								photo9 = json_list.get(i).getPhoto9();
								photo10 = json_list.get(i).getPhoto10();
								if (photo1 != null && !photo1.equals("")) {
									urlList.add(MyApp.base_address + photo1);
								} else if (photo2 != null && !photo2.equals("")) {
									urlList.add(MyApp.base_address + photo2);
								} else if (photo3 != null && !photo3.equals("")) {
									urlList.add(MyApp.base_address + photo3);
								} else if (photo4 != null && !photo4.equals("")) {
									urlList.add(MyApp.base_address + photo4);
								} else if (photo5 != null && !photo5.equals("")) {
									urlList.add(MyApp.base_address + photo5);
								} else if (photo6 != null && !photo6.equals("")) {
									urlList.add(MyApp.base_address + photo6);
								} else if (photo7 != null && !photo7.equals("")) {
									urlList.add(MyApp.base_address + photo7);
								} else if (photo8 != null && !photo8.equals("")) {
									urlList.add(MyApp.base_address + photo8);
								} else if (photo9 != null && !photo9.equals("")) {
									urlList.add(MyApp.base_address + photo9);
								} else if (photo10 != null
										&& !photo10.equals("")) {
									urlList.add(MyApp.base_address + photo10);
								}
								if (params[0].equals("1")) {
									list.add(new My_ReleaseBean(goods_name,
											detailed_description,
											browse_volume, browse_person,
											goods_price, "", "", "", "",
											urlList));
								} else if (params[0].equals("2")) {
									list.add(new My_ReleaseBean(
											goods_name,
											detailed_description,
											browse_volume,
											browse_person,
											total_person,
											String.valueOf(Integer
													.valueOf(total_person)
													- Integer
															.valueOf(participant_person)),
											"", "", "", urlList));
								} else if (params[0].equals("3")) {
									list.add(new My_ReleaseBean(goods_name,
											detailed_description,
											browse_volume, browse_person,
											rent_price, "", rent_date, "", "",
											urlList));
								} else if (params[0].equals("4")) {
									list.add(new My_ReleaseBean(goods_name,
											detailed_description,
											browse_volume, browse_person,
											auction_currentprice, "", "",
											auction_person, "第1次延时", urlList));
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
				my_release_promptRel.setVisibility(View.VISIBLE);
				my_release_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				my_release_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						my_release_promptBtn, 0.27);
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 10001:
				if (pageNo == 1) {
					my_release_promptRel.setVisibility(View.VISIBLE);
					String string = (String) msg.obj;
					if (string.equals("1")) {
						my_release_promptText.setText("您还没有卖出的全价商品哦~");
					} else if (string.equals("2")) {
						my_release_promptText.setText("您还没有卖出的众筹商品哦~");
					} else if (string.equals("3")) {
						my_release_promptText.setText("您还没有卖出的出租商品哦~");
					} else if (string.equals("4")) {
						my_release_promptText.setText("您还没有卖出的拍卖商品哦~");
					}
					SetW_H.setRelativeLayout(getApplicationContext(),
							my_release_promptBtn, 0.27);
				} else {
					ILoadingLayout startLabels = listView
							.getLoadingLayoutProxy(false, true);
					startLabels.setPullLabel("没有更多数据了哟");// 刚下拉时，显示的提示
					startLabels.setRefreshingLabel("没有更多数据了哟");// 刷新时
					startLabels.setReleaseLabel("没有更多数据了哟");// 下来达到一定距离时，显示的提示
				}
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 0:
				listView.setVisibility(View.VISIBLE);
				String string2 = (String) msg.obj;
				if (pageNo == 1) {
					adapter = new My_ReleaseAdapter(getApplicationContext(),
							list, My_ReleaseActivity.this,
							Integer.valueOf(string2));
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
