package com.quwu.xinwo.home_page;

import java.io.IOException;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Crowd_FoundingAdapter;
import com.quwu.xinwo.bean.City_wide_classify_popBean;
import com.quwu.xinwo.bean.Crowd_FoundingBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.City_WideEntity;
import com.quwu.xinwo.gson_entity.Crowd_FundingEntity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop.OnClassesWindowClickListener;
import com.quwu.xinwo.popupwindow.City_Wide_Classify_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Classify_Pop.OnRankWindowClickListener;
import com.quwu.xinwo.popupwindow.City_Wide_Screen_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Screen_Pop.OnScreenWindowClickListener;
import com.quwu.xinwo.popupwindow.ThreeListview;
import com.quwu.xinwo.popupwindow.ThreeListview.OnThreeClickListener;
import com.quwu.xinwo.product_details.Crowdfunding_Product_DetailsActivity;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MyProgressDialog;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;
import com.quwu.xinwo.until.Tool;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class Crowd_FundingActivity extends SwipeBackActivity implements
		OnClickListener {

	private LinearLayout all;
	private LinearLayout classes;
	private LinearLayout rank;
	private LinearLayout screen;

	private TextView crowd_funding_allcityText;
	private TextView crowd_funding_classesText;

	private PullToRefreshListView listView;
	private Crowd_FoundingAdapter adapter;
	private List<Crowd_FoundingBean> list;
	private List<String> urls;// 图片集合
	private ImageLoader imageLoader;

	private List<City_WideEntity> json_list;
	private List<City_wide_classify_popBean> rank_pop_list;

	private RelativeLayout crowd_funding_promptRel;// 没有数据和程序异常提示的布局
	private Button crowd_funding_promptBtn;
	private TextView crowd_funding_promptText;
	/**
	 * 所需参数
	 * */
	private int pageNow = 1;
	private int pageSize = 8;
	private String normal_use = "0";// 正常使用，0-没有选中，1-选中了
	private String warranty_period = "0";// 保修期内，0-没有选中，1-选中了
	private String no_repair = "0";// 无拆无修，0-没有选中，1-选中了
	private String brand_new = "0";// 全新商品，0-没有选中，1-选中了
	private String jingdong = "0";// 京东，0-没有选中，1-选中了
	private String mainland_licensed = "0";// 大陆行货，0-没有选中，1-选中了
	private String since = "0";// 可以自提，0-没有选中，1-选中了
	private String good_region1 = "0";// 上架的市ID 如 10
	private String city_region1 = "0";// 上架的市ID 如 10
	private String small_area1 = "0";// 上架商品的区县ID（注意：如果是选的全地区则传0） 如20
	private String twolevel_id = "0";// 商品二级分类 如 数码商品
	private String three_id = "0"; // 商品的三级分类 如 手机
	private String sortorder_id = "1";// 排序方式 如1
	private String goods_lprice = "0";// 商品的最低价 如 10
	private String goods_hprice = "10000000";// 商品的最高价 如200

	/**
	 * 返回的数据
	 * */
	private String bigpicture1;// 图片路径1
	private String bigpicture2;// 图片路径2
	private String bigpicture3;// 图片路径3
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String good_region;// 商品上架的省份（包括直辖市等）
	private String city_region;// 市
	private String small_area;// 区县
	private String ins_time;// 上架时间
	private String current_time1;// 服务器当前时间
	private String total_person;// 总需人次
	private String surplus_person;// 剩余人次
	private String goods_id;// 商品ID

	private List<Crowd_FundingEntity> entities;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crowd_funding);
		imageLoader = ImageLoader.getInstance();
		FinishActivity.finish(R.id.crowd_funding_returnRel,
				Crowd_FundingActivity.this);
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		crowd_funding_promptRel = (RelativeLayout) findViewById(R.id.crowd_funding_promptRel);
		crowd_funding_promptBtn = (Button) findViewById(R.id.crowd_funding_promptBtn);
		crowd_funding_promptText = (TextView) findViewById(R.id.crowd_funding_promptText);
		SetW_H.setRelativeLayout1(getApplicationContext(),
				crowd_funding_promptBtn, 0.3);
		listView = (PullToRefreshListView) findViewById(R.id.crowd_funding_listview);
		listView.setMode(Mode.BOTH);
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNow = 1;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}

			
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNow++;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
		all = (LinearLayout) findViewById(R.id.crowd_funding_allcityLin);
		classes = (LinearLayout) findViewById(R.id.crowd_funding_classesLin);
		rank = (LinearLayout) findViewById(R.id.crowd_funding_rankLin);
		screen = (LinearLayout) findViewById(R.id.crowd_funding_screenLin);

		all.setOnClickListener(this);
		classes.setOnClickListener(this);
		rank.setOnClickListener(this);
		screen.setOnClickListener(this);

		crowd_funding_allcityText = (TextView) findViewById(R.id.crowd_funding_allcityText);
		crowd_funding_classesText = (TextView) findViewById(R.id.crowd_funding_classesText);
	}

	
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {

			switch (v.getId()) {
			case R.id.crowd_funding_allcityLin:
				ThreeListview all_pop = new ThreeListview(
						Crowd_FundingActivity.this);
				all_pop.showPopupWindow(all);
				all_pop.setOnThreeClickListener(new OnThreeClickListener() {
					public void send(String area, String good_region1,
							String city_region1, String small_area1) {
						Crowd_FundingActivity.this.good_region1 = good_region1;
						Crowd_FundingActivity.this.city_region1 = city_region1;
						Crowd_FundingActivity.this.small_area1 = small_area1;
						crowd_funding_allcityText.setText(area);
						pageNow = 1;
						new LoadTask().executeOnExecutor(Executors
								.newCachedThreadPool());
					}
				});
				break;
			case R.id.crowd_funding_classesLin:
				City_Wide_Classes_Pop classes_pop = new City_Wide_Classes_Pop(
						Crowd_FundingActivity.this);
				classes_pop.showPopupWindow(classes);
				classes_pop
						.setOnClassesWindowClickListener(new OnClassesWindowClickListener() {

							
							public void send(String name, String twolevel_id,
									String three_id) {
								Crowd_FundingActivity.this.twolevel_id = twolevel_id;
								Crowd_FundingActivity.this.three_id = three_id;
								crowd_funding_classesText.setText(name);
								pageNow = 1;
								new LoadTask().executeOnExecutor(Executors
										.newCachedThreadPool());
							}
						});
				break;
			case R.id.crowd_funding_rankLin:
				new RankTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			case R.id.crowd_funding_screenLin:
				City_Wide_Screen_Pop screen_pop = new City_Wide_Screen_Pop(
						Crowd_FundingActivity.this, normal_use,
						warranty_period, no_repair, brand_new, jingdong,
						mainland_licensed, since, goods_hprice, goods_lprice);
				screen_pop.showPopupWindow(screen);
				screen_pop
						.setOnScreenWindowClickListener(new OnScreenWindowClickListener() {
							public void send(String normal_use,
									String warranty_period, String no_repair,
									String brand_new, String jingdong,
									String mainland_licensed, String since,
									String high, String low) {
								Crowd_FundingActivity.this.normal_use = normal_use;
								Crowd_FundingActivity.this.warranty_period = warranty_period;
								Crowd_FundingActivity.this.no_repair = no_repair;
								Crowd_FundingActivity.this.brand_new = brand_new;
								Crowd_FundingActivity.this.jingdong = jingdong;
								Crowd_FundingActivity.this.mainland_licensed = mainland_licensed;
								Crowd_FundingActivity.this.since = since;
								Crowd_FundingActivity.this.goods_lprice = low;
								Crowd_FundingActivity.this.goods_hprice = high;
								pageNow = 1;
								new LoadTask().executeOnExecutor(Executors
										.newCachedThreadPool());
							}
						});
				break;

			default:
				break;
			}
		}
	}

	/**
	 * 加载排序数据
	 * */
	private class RankTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			rank_pop_list = new ArrayList<City_wide_classify_popBean>();
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selectsortorder.do", "", "");
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string,
								new TypeToken<List<City_WideEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							rank_pop_list.add(new City_wide_classify_popBean(
									json_list.get(i).getSortorder_id(),
									json_list.get(i).getSortorder_content()));
						}
						handler.sendEmptyMessageDelayed(4, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (listView != null) {
				listView.onRefreshComplete();

			}
			switch (msg.what) {
			case 10000:
				crowd_funding_promptRel.setVisibility(View.VISIBLE);
				crowd_funding_promptText.setText("服务器异常，点我试一试~");
				crowd_funding_promptRel
						.setOnClickListener(new OnClickListener() {

							
							public void onClick(View v) {
								pageNow = 1;
								new LoadTask().executeOnExecutor(Executors
										.newCachedThreadPool());
							}
						});
				MyProgressDialog.stopProgressDialog();
				break;
			case 10001:
				if (pageNow == 1) {
					crowd_funding_promptRel.setVisibility(View.VISIBLE);
					crowd_funding_promptText.setText("没有查询到相关数据");
				} else {
					MyToast.Toast(getApplicationContext(), "没有更多数据了哟~");
				}
				MyProgressDialog.stopProgressDialog();
				break;
			case 0:
				if (pageNow == 1) {
					adapter = new Crowd_FoundingAdapter(list,
							Crowd_FundingActivity.this,
							Crowd_FundingActivity.this, imageLoader);
					listView.setAdapter(adapter);
				} else {
					adapter.notifyDataSetChanged();
				}
				listView.setOnScrollListener(new PauseOnScrollListener(
						imageLoader, true, true));
				listView.setVisibility(View.VISIBLE);
				MyProgressDialog.stopProgressDialog();
				listView.setOnItemClickListener(new OnItemClickListener() {

					
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(Crowd_FundingActivity.this,
								Crowdfunding_Product_DetailsActivity.class);
						intent.putExtra("goods_id", list.get(position)
								.getGoods_id());
						startActivity(intent);
					}
				});
				break;
			case 4:
				City_Wide_Classify_Pop rank_pop = new City_Wide_Classify_Pop(
						Crowd_FundingActivity.this, rank_pop_list, sortorder_id);
				rank_pop.showPopupWindow(rank);
				rank_pop.setOnRankWindowClickListener(new OnRankWindowClickListener() {
					public void send(String id, String name) {
						sortorder_id = id;
						new LoadTask().executeOnExecutor(Executors
								.newCachedThreadPool());
					}
				});
				break;

			default:
				break;
			}
		};
	};

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNow == 1) {
				MyProgressDialog.startProgressDialog(
						Crowd_FundingActivity.this, listView,
						crowd_funding_promptRel);
			}
		}

		protected Void doInBackground(Void... params) {
			if (pageNow == 1) {
				list = new ArrayList<Crowd_FoundingBean>();
			}
			String result = MyDoPost(MyApp.base_address
					+ "raisepublic/selectinformation.do", normal_use,
					warranty_period, no_repair, brand_new, jingdong,
					mainland_licensed, since, good_region1, city_region1,
					small_area1, twolevel_id, three_id, sortorder_id,
					goods_lprice, goods_hprice,
					MySharePreferences.GetUser_ID(getApplicationContext()),
					pageNow, pageSize);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {
						handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
					} else if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else {
						Gson gson = new Gson();
						entities = gson.fromJson(string,
								new TypeToken<List<Crowd_FundingEntity>>() {
								}.getType());
						for (int i = 0; i < entities.size(); i++) {
							urls = new ArrayList<String>();
							bigpicture1 = entities.get(i).getBigpicture1();
							bigpicture2 = entities.get(i).getBigpicture2();
							bigpicture3 = entities.get(i).getBigpicture3();
							goods_name = entities.get(i).getGoods_name();
							good_description = entities.get(i)
									.getGood_description();
							good_region = entities.get(i).getGood_region();
							city_region = entities.get(i).getCity_region();
							small_area = entities.get(i).getSmall_area();
							ins_time = entities.get(i).getIns_time();
							current_time1 = entities.get(i).getCurrent_time1();
							total_person = entities.get(i).getTotal_person();
							surplus_person = entities.get(i)
									.getSurplus_person();
							goods_id = entities.get(i).getGoods_id();
							urls.add(MyApp.base_address + bigpicture1);
							urls.add(MyApp.base_address + bigpicture2);
							urls.add(MyApp.base_address + bigpicture3);
							list.add(new Crowd_FoundingBean(urls, goods_name,
									good_description, TimeUtils
											.isTime_Difference(Long
													.valueOf(current_time1)
													- Long.valueOf(ins_time))
											+ "\t\t"
											+ good_region
											+ " "
											+ city_region + " " + small_area,
									total_person, surplus_person, goods_id));
						}
						handler.sendEmptyMessageDelayed(0, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
			}
			return null;
		}
	}

	/**
	 * 根据条件查询商品的网络请求
	 * */
	public static String MyDoPost(String url, String normal_use,
			String warranty_period, String no_repair, String brand_new,
			String jingdong, String mainland_licensed, String since,
			String good_region, String city_region, String small_area,
			String twolevel_id, String three_id, String sortorder_id,
			String goods_lprice, String goods_hprice, String buy_userid,
			int pageNow, int pageSize) {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormEncodingBuilder()
				.add("normal_use", normal_use)
				.add("warranty_period", warranty_period)
				.add("no_repair", no_repair).add("brand_new", brand_new)
				.add("jingdong", jingdong)
				.add("mainland_licensed", mainland_licensed)
				.add("since", since).add("good_region", good_region)
				.add("city_region", city_region).add("small_area", small_area)
				.add("twolevel_id", twolevel_id).add("three_id", three_id)
				.add("sortorder_id", sortorder_id)
				.add("goods_lprice", goods_lprice)
				.add("goods_hprice", goods_hprice)
				.add("buy_userid", buy_userid)
				.add("pageNow", String.valueOf(pageNow))
				.add("pageSize", String.valueOf(pageSize)).build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				String body = response.body().string();
				return body;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}// execute

		return null;
	}
}
