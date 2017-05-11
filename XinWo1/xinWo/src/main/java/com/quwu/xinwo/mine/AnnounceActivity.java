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
import com.quwu.xinwo.adapter.AnnounceAdapter1;
import com.quwu.xinwo.adapter.AnnounceAdapter2;
import com.quwu.xinwo.bean.AnnounceBean1;
import com.quwu.xinwo.bean.AnnounceBean2;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.AnnounceEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

public class AnnounceActivity extends SwipeBackActivity implements
		OnClickListener {
	private PullToRefreshListView listView;
	private List<AnnounceEntity> json_list;
	/**
	 * 
	 * 众筹
	 * 
	 * */
	private LinearLayout announce_crowdfundingLin;
	private TextView announce_crowdfundingText;
	private ImageView announce_crowdfundingImage;
	private List<AnnounceBean1> list;
	private AnnounceAdapter1 adapter;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private LinearLayout announce_auctionLin;
	private TextView announce_auctionText;
	private ImageView announce_auctionImage;
	private List<AnnounceBean2> list2;
	private AnnounceAdapter2 adapter2;

	private RelativeLayout announce_promptLin;
	private Button announce_promptBtn;
	private TextView announce_promptText;
	
	private int index = 1;// 点击了哪一个选项（1：众筹，2：拍卖）
	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页
	
	/**
	 * 
	 * 服务器返回数据
	 * 
	 * */
	private String user_name;//用户名（String类型）
	private String goods_photo;//商品图片（String类型）
	private String announced_time;//揭晓时间（int类型）
	private String goods_name;//商品名称（String类型）
	private String total_person;//总需（int类型）
	private String participant_person;//本期参与（int类型）
	private String good_description;//商品描述（String类型）
	private String personnum;//本次参与（int类型）
	private String lucky_number;//幸运号（int类型）
	private String	auction_currentprice;//成交价（int类型）
	private String	auction_person;//抢夺人数（int类型）
	private String	u_id;//查看拍到的用户所用到的id（int类型）
	private String	user_pictrue;//获奖者用户头像
	
	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(AnnounceActivity.this);
			progressDialog.setMessage("正在加载中...");
			announce_promptLin.setVisibility(View.GONE);
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
		setContentView(R.layout.announce);
		FinishActivity.finish(R.id.announce_returnRel, AnnounceActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
				"0");
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.announce_listview);
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
				}
			}
		});
		
		announce_promptLin = (RelativeLayout) findViewById(R.id.announce_promptLin);
		announce_promptBtn = (Button) findViewById(R.id.announce_promptBtn);
		announce_promptText = (TextView) findViewById(R.id.announce_promptText);
		announce_promptLin.setOnClickListener(this);
		
		announce_crowdfundingLin = (LinearLayout) findViewById(R.id.announce_crowdfundingLin);
		announce_auctionLin = (LinearLayout) findViewById(R.id.announce_auctionLin);

		announce_crowdfundingLin.setOnClickListener(this);
		announce_auctionLin.setOnClickListener(this);

		announce_crowdfundingText = (TextView) findViewById(R.id.announce_crowdfundingText);
		announce_auctionText = (TextView) findViewById(R.id.announce_auctionText);

		announce_crowdfundingImage = (ImageView) findViewById(R.id.announce_crowdfundingImage);
		announce_auctionImage = (ImageView) findViewById(R.id.announce_auctionImage);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.announce_crowdfundingLin:
			announce_crowdfundingText.setTextColor(this.getResources()
					.getColor(R.color.淡红色));
			announce_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡灰色));
			announce_crowdfundingImage.setVisibility(View.VISIBLE);
			announce_auctionImage.setVisibility(View.GONE);
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
					"0");
			break;
		case R.id.announce_auctionLin:
			announce_crowdfundingText.setTextColor(this.getResources()
					.getColor(R.color.淡灰色));
			announce_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			announce_crowdfundingImage.setVisibility(View.GONE);
			announce_auctionImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
					"1");
			break;
		case R.id.announce_promptLin:
			pageNo = 1;
			if (index == 1) {
				announce_crowdfundingText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				announce_auctionText.setTextColor(this.getResources().getColor(
						R.color.淡灰色));
				announce_crowdfundingImage.setVisibility(View.VISIBLE);
				announce_auctionImage.setVisibility(View.GONE);
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
						"0");
			} else if (index == 2) {
				announce_crowdfundingText.setTextColor(this.getResources()
						.getColor(R.color.淡灰色));
				announce_auctionText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				announce_crowdfundingImage.setVisibility(View.GONE);
				announce_auctionImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(),
						"1");
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
					list = new ArrayList<AnnounceBean1>();
				} else if (params[0].equals("1")) {
					list2 = new ArrayList<AnnounceBean2>();
				}
			}
			String result = OKHTTP_POST.doPost4(MyApp.base_address
					+ "usersaction/announced.do", "user_id", "1", "state",
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
									new TypeToken<List<AnnounceEntity>>() {
									}.getType());
							if (params[0].equals("0")) {
								for (int i = 0; i < json_list.size(); i++) {
									user_name = json_list.get(i)
											.getUser_name();
									goods_photo=json_list.get(i).getGoods_photo();
									announced_time=json_list.get(i).getAnnounced_time();
									goods_name = json_list.get(i)
											.getGoods_name();
									total_person = json_list.get(i)
											.getTotal_person();
									participant_person = json_list.get(i)
											.getParticipant_person();
									good_description = json_list.get(i)
											.getGood_description();
									personnum = json_list.get(i)
											.getPersonnum();
									lucky_number=json_list.get(i).getLucky_number();
								list.add(new AnnounceBean1(MyApp.base_address+goods_photo, goods_name, good_description, "总需："+total_person, participant_person, "查看我的夺宝号", MyApp.base_address+user_pictrue, user_name, lucky_number, personnum, TimeUtils.Time(Long.valueOf(announced_time))));
								}
							} else if (params[0].equals("1")) {
								for (int i = 0; i < json_list.size(); i++) {
									goods_photo=json_list.get(i).getGoods_photo();
									auction_currentprice=json_list.get(i).getAuction_currentprice();
									auction_person=json_list.get(i).getAuction_person();
									goods_name = json_list.get(i)
											.getGoods_name();
									u_id=json_list.get(i).getU_id();
									participant_person = json_list.get(i)
											.getParticipant_person();
								good_description=json_list.get(i).getGood_description();
								list2.add(new AnnounceBean2(MyApp.base_address+goods_photo, goods_name, good_description,"出价："+ participant_person, auction_person, auction_currentprice, "查看拍到用户"));
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
				announce_promptLin.setVisibility(View.VISIBLE);
				announce_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				announce_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						announce_promptBtn, 0.27);
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			case 10001:
				if (pageNo == 1) {
					announce_promptLin.setVisibility(View.VISIBLE);
					String string = (String) msg.obj;
					if (string.equals("0")) {
						announce_promptText.setText("没有已经揭晓的众筹商品哦~");
					} else {
						announce_promptText.setText("没有已经揭晓拍卖商品哦~");
					}
					SetW_H.setRelativeLayout(getApplicationContext(),
							announce_promptBtn, 0.27);
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
				String string=(String) msg.obj;
				if (string.equals("0")) {
					if (pageNo == 1) {
						adapter=new AnnounceAdapter1(list, getApplicationContext());
						listView.setAdapter(adapter);
					} else {
						if (adapter != null) {
							adapter.notifyDataSetChanged();
						}
					}
				}else {
					if (pageNo == 1) {
						adapter2=new AnnounceAdapter2(list2, getApplicationContext());
						listView.setAdapter(adapter2);
					} else {
						if (adapter2 != null) {
							adapter2.notifyDataSetChanged();
						}
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
