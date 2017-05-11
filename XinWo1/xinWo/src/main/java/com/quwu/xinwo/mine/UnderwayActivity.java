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
import com.quwu.xinwo.adapter.UnderwayAdapter;
import com.quwu.xinwo.adapter.UnderwayAdapter2;
import com.quwu.xinwo.bean.UnderwayBean;
import com.quwu.xinwo.bean.UnderwayBean2;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.UnderwayEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;

public class UnderwayActivity extends SwipeBackActivity implements
		OnClickListener {
	private PullToRefreshListView listView;

	private RelativeLayout underway_promptLin;
	private Button underway_promptBtn;
	private TextView underway_promptText;
	/**
	 * 
	 * 众筹
	 * 
	 * */
	private List<UnderwayBean> list;
	private UnderwayAdapter adapter;
	private LinearLayout underway_crowdfundingLin;
	private TextView underway_crowdfundingText;
	private ImageView underway_crowdfundingImage;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private List<UnderwayBean2> list2;
	private UnderwayAdapter2 adapter2;
	private LinearLayout underway_auctionLin;
	private TextView underway_auctionText;
	private ImageView underway_auctionImage;

	/**
	 * 服务器数据 (众筹)
	 */
	private String surplus_person;// 剩余人次（int类型）
	private String total_person;// 商品总需（int类型）
	/**
	 * 服务器数据 (拍卖)
	 */
	private String transcend;// 超越人数（int类型）
	private String auction_currentprice;// 当前价格（int类型）
	private String auction_person;// 参与人次（int类型）
	private String user_id;// 用户id（int类型）
	/**
	 * 服务器数据 (公共)
	 */
	private String goods_photo;// 商品图片（String类型）
	private String goods_name;// 商品名称（String类型）
	private String participant_person;// 出价（int类型）
	private String good_description;// 商品描述（String类型）
	private List<UnderwayEntity> json_list;

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
					.createDialog(UnderwayActivity.this);
			progressDialog.setMessage("正在加载中...");
			underway_promptLin.setVisibility(View.GONE);
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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.underway);
		FinishActivity.finish(R.id.underway_returnRel, UnderwayActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
				"0");
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.underway_listview);
		listView.setMode(Mode.BOTH);
		listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			public void onLastItemVisible() {
				pageNo++;
				if (index == 1) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "0");
				} else if (index == 2) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "1");
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
							Executors.newCachedThreadPool(), "0");
				} else if (index == 2) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "1");
				}
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo++;
				if (index == 1) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "0");
				} else if (index == 2) {
					new LoadDataTask().executeOnExecutor(
							Executors.newCachedThreadPool(), "1");
				}
			}
		});

		underway_promptLin = (RelativeLayout) findViewById(R.id.underway_promptLin);
		underway_promptBtn = (Button) findViewById(R.id.underway_promptBtn);
		underway_promptText = (TextView) findViewById(R.id.underway_promptText);
		underway_promptLin.setOnClickListener(this);

		underway_crowdfundingLin = (LinearLayout) findViewById(R.id.underway_crowdfundingLin);
		underway_auctionLin = (LinearLayout) findViewById(R.id.underway_auctionLin);

		underway_crowdfundingLin.setOnClickListener(this);
		underway_auctionLin.setOnClickListener(this);

		underway_crowdfundingText = (TextView) findViewById(R.id.underway_crowdfundingText);
		underway_auctionText = (TextView) findViewById(R.id.underway_auctionText);

		underway_crowdfundingImage = (ImageView) findViewById(R.id.underway_crowdfundingImage);
		underway_auctionImage = (ImageView) findViewById(R.id.underway_auctionImage);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.underway_crowdfundingLin:
			index = 1;
			pageNo = 1;
			underway_crowdfundingText.setTextColor(this.getResources()
					.getColor(R.color.淡红色));
			underway_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡灰色));
			underway_crowdfundingImage.setVisibility(View.VISIBLE);
			underway_auctionImage.setVisibility(View.GONE);
			new LoadDataTask().executeOnExecutor(
					Executors.newCachedThreadPool(), "0");
			break;
		case R.id.underway_auctionLin:
			index = 2;
			pageNo = 1;
			underway_crowdfundingText.setTextColor(this.getResources()
					.getColor(R.color.淡灰色));
			underway_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			underway_crowdfundingImage.setVisibility(View.GONE);
			underway_auctionImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(
					Executors.newCachedThreadPool(), "1");
			break;
		case R.id.underway_promptLin:
			pageNo = 1;
			if (index == 1) {
				underway_crowdfundingText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				underway_auctionText.setTextColor(this.getResources().getColor(
						R.color.淡灰色));
				underway_crowdfundingImage.setVisibility(View.VISIBLE);
				underway_auctionImage.setVisibility(View.GONE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "0");
			} else if (index == 2) {
				underway_crowdfundingText.setTextColor(this.getResources()
						.getColor(R.color.淡灰色));
				underway_auctionText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				underway_crowdfundingImage.setVisibility(View.GONE);
				underway_auctionImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "1");
			}
			break;
		default:
			break;
		}
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
				if (params[0].equals("0")) {
					list = new ArrayList<UnderwayBean>();
				} else if (params[0].equals("1")) {
					list2 = new ArrayList<UnderwayBean2>();
				}
			}
			String result = OKHTTP_POST.doPost4(MyApp.base_address
					+ "usersaction/ongoing.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()), "state",
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
									new TypeToken<List<UnderwayEntity>>() {
									}.getType());
							if (params[0].equals("0")) {
								for (int i = 0; i < json_list.size(); i++) {
									goods_photo = json_list.get(i)
											.getGoods_photo();
									surplus_person = json_list.get(i)
											.getSurplus_person();
									goods_name = json_list.get(i)
											.getGoods_name();
									total_person = json_list.get(i)
											.getTotal_person();
									participant_person = json_list.get(i)
											.getParticipant_person();
									good_description = json_list.get(i)
											.getGood_description();
									list.add(new UnderwayBean(
											MyApp.base_address + goods_photo,
											goods_name,
											good_description,
											total_person,
											surplus_person,
											participant_person,
											"查看我的夺宝号",
											"追加",
											Integer.valueOf(total_person)
													- Integer
															.valueOf(surplus_person)));

								}
							} else if (params[0].equals("1")) {
								for (int i = 0; i < json_list.size(); i++) {
									goods_photo = json_list.get(i)
											.getGoods_photo();
									transcend = json_list.get(i).getTranscend();
									auction_currentprice = json_list.get(i)
											.getAuction_currentprice();
									auction_person = json_list.get(i)
											.getAuction_person();
									goods_name = json_list.get(i)
											.getGoods_name();
									user_id = json_list.get(i).getUser_id();
									participant_person = json_list.get(i)
											.getParticipant_person();
									good_description = json_list.get(i)
											.getGood_description();
									if (transcend.equals("0")) {
										list2.add(new UnderwayBean2(
												MyApp.base_address
														+ goods_photo,
												goods_name, good_description,
												"出价：" + participant_person,
												auction_currentprice,
												auction_person,
												"土豪，您已经领先所有人，为最高出价", "1"));
									} else {
										list2.add(new UnderwayBean2(
												MyApp.base_address
														+ goods_photo,
												goods_name, good_description,
												"出价：" + participant_person,
												auction_currentprice,
												auction_person, "已经有"
														+ transcend
														+ "人出价超过您了...", "0"));
									}
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
				underway_promptLin.setVisibility(View.VISIBLE);
				listView.setVisibility(View.GONE);
				underway_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				underway_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						underway_promptBtn, 0.27);
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			case 10001:
				if (pageNo == 1) {
					underway_promptLin.setVisibility(View.VISIBLE);
					listView.setVisibility(View.GONE);
					String string = (String) msg.obj;
					if (string.equals("0")) {
						underway_promptText.setText("没有正在进行众筹的商品哦~");
					} else {
						underway_promptText.setText("没有正在进行拍卖的商品哦~");
					}
					SetW_H.setRelativeLayout(getApplicationContext(),
							underway_promptBtn, 0.27);
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
				underway_promptLin.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter = new UnderwayAdapter(list, getApplicationContext());
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
				underway_promptLin.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter2 = new UnderwayAdapter2(list2,
							getApplicationContext());
					listView.setAdapter(adapter2);
				} else {
					if (adapter2 != null) {
						adapter.notifyDataSetChanged();
					}
				}
				stopProgressDialog();
				listView.onRefreshComplete();
				break;

			default:
				break;
			}
		};
	};
}
