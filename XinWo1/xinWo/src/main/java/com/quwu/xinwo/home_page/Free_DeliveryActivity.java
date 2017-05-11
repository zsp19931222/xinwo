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
import com.quwu.xinwo.adapter.Free_DeliveryAdapter;
import com.quwu.xinwo.bean.Free_DeliveryBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Crowd_FundingEntity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop.OnClassesWindowClickListener;
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

public class Free_DeliveryActivity extends SwipeBackActivity implements OnClickListener{
	
	private LinearLayout all;
	private LinearLayout classes;
	private ImageLoader imageLoader;

	private PullToRefreshListView listView;
	private Free_DeliveryAdapter adapter;
	private List<Free_DeliveryBean> list;
	private List<String> urls;// 图片集合
	
	private TextView free_delivery_allText;
	private TextView free_delivery_classesText;
	
	private RelativeLayout free_delivery_promptRel;//没有数据和程序异常提示的布局
	private Button free_delivery_promptBtn;
	private TextView free_delivery_promptText;
	
	/**
	 * 所需参数
	 * */
	private int pageNow = 1;
	private int pageSize = 8;
	private String good_region1="0";// 上架的市ID 如 10
	private String city_region1="0";// 上架的市ID 如 10
	private String small_area1 = "0";// 上架商品的区县ID（注意：如果是选的全地区则传0） 如20
	private String twolevel_id = "0";// 商品二级分类 如 数码商品
	private String three_id = "0"; // 商品的三级分类 如 手机
	
	/**
	 * 返回的数据
	 * */
private String 	bigpicture1;//图片路径1
private String	bigpicture2;//图片路径2
private String	bigpicture3;//图片路径3
private String	goods_name;//商品名称
private String	good_description;//商品描述
private String	good_region;//商品上架的省份（包括直辖市等）
private String	city_region;//市
private String	small_area;//区县
private String	ins_time;//上架时间
private String	current_time1;//服务器当前时间
private String	goods_id ;//商品ID
	
private List<Crowd_FundingEntity> entities;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.free_delivery);
		imageLoader=ImageLoader.getInstance();
		FinishActivity.finish(R.id.free_delivery_returnRel,
				Free_DeliveryActivity.this);
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}
	private void findID() {
		free_delivery_promptRel=(RelativeLayout) findViewById(R.id.free_delivery_promptRel);
		free_delivery_promptBtn=(Button) findViewById(R.id.free_delivery_promptBtn);
		free_delivery_promptText=(TextView) findViewById(R.id.free_delivery_promptText);
		free_delivery_allText=(TextView) findViewById(R.id.free_delivery_allText);
		free_delivery_classesText=(TextView) findViewById(R.id.free_delivery_classesText);
		SetW_H.setRelativeLayout1(getApplicationContext(), free_delivery_promptBtn, 0.3);
		
		listView = (PullToRefreshListView) findViewById(R.id.free_delivery_listview);
		listView.setMode(Mode.BOTH);
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNow=1;
				new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNow++;
				new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
			}
		});

		all = (LinearLayout) findViewById(R.id.free_delivery_allcityLin);
		classes = (LinearLayout) findViewById(R.id.free_delivery_classesLin);

		all.setOnClickListener(this);
		classes.setOnClickListener(this);
	}
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		}else {

		switch (v.getId()) {
		case R.id.free_delivery_allcityLin:
			ThreeListview all_pop = new ThreeListview(Free_DeliveryActivity.this);
			all_pop.showPopupWindow(all);
			all_pop.setOnThreeClickListener(new OnThreeClickListener() {
				
				@Override
				public void send(String area, String good_region1,String city_region1, String small_area1) {
					Free_DeliveryActivity.this.good_region1=good_region1;
					Free_DeliveryActivity.this.city_region1=city_region1;
					Free_DeliveryActivity.this.small_area1=small_area1;
					free_delivery_allText.setText(area);
					pageNow=1;
					new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
				}
			});
			break;
		case R.id.free_delivery_classesLin:
			City_Wide_Classes_Pop classes_pop = new City_Wide_Classes_Pop(
					Free_DeliveryActivity.this);
			classes_pop.showPopupWindow(classes);
			classes_pop.setOnClassesWindowClickListener(new OnClassesWindowClickListener() {
				
				@Override
				public void send(String name,String twolevel_id, String three_id) {
					Free_DeliveryActivity.this.twolevel_id=twolevel_id;
					Free_DeliveryActivity.this.three_id=three_id;
					free_delivery_classesText.setText(name);
					pageNow=1;
					new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
				}
			});
			break;
		default:
			break;
		}			
		}
	}
	private class LoadTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (pageNow==1) {
				MyProgressDialog.startProgressDialog(Free_DeliveryActivity.this, listView, free_delivery_promptRel);
			}
		}
		protected Void doInBackground(Void... params) {
			if (pageNow==1) {
				list = new ArrayList<Free_DeliveryBean>();
			}
			String result=MyDoPost(MyApp.base_address+"freedelivery/freedeliverygoods.do", good_region1, city_region1, small_area1, twolevel_id, three_id, MySharePreferences.GetUser_ID(getApplicationContext()), pageNow, pageSize);
			if (result!=null) {
				try {
					JSONObject jsonObject=new JSONObject(result);
				String string=jsonObject.getString("1");
				if (string.equals("当前还没有数据")) {
					handler.sendEmptyMessageDelayed(
							MyApp.NODATA, 10);
				}else if (string.equals("程序异常")) {
					handler.sendEmptyMessageDelayed(
							MyApp.Program_Exception, 10);
				}else {
					Gson gson = new Gson();
					entities = gson.fromJson(string,
							new TypeToken<List<Crowd_FundingEntity>>() {
							}.getType());
					for (int i = 0; i < entities.size(); i++) {
						urls = new ArrayList<String>();
						bigpicture1=entities.get(i).getBigpicture1();
						bigpicture2=entities.get(i).getBigpicture2();
						bigpicture3=entities.get(i).getBigpicture3();
						goods_name=entities.get(i).getGoods_name();
						good_description=entities.get(i).getGood_description();
						good_region=entities.get(i).getGood_region();
						city_region=entities.get(i).getCity_region();
						small_area=entities.get(i).getSmall_area();
						ins_time=entities.get(i).getIns_time();
						current_time1=entities.get(i).getCurrent_time1();
						goods_id=entities.get(i).getGoods_id();
						urls.add(MyApp.base_address+bigpicture1);
						if (!bigpicture2.equals("")) {
							urls.add(MyApp.base_address+bigpicture2);
						}
						if(!bigpicture3.equals("")) {
							urls.add(MyApp.base_address+bigpicture3);
						}
						list.add(new Free_DeliveryBean(urls, goods_name, good_description, TimeUtils.isTime_Difference(Long
								.valueOf(current_time1)
								- Long.valueOf(ins_time))+"\t\t"+good_region+" "+city_region+" "+small_area,goods_id));
					}
					handler.sendEmptyMessageDelayed(0, 10);
				}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				handler.sendEmptyMessageDelayed(
						MyApp.NODATA, 10);
			}
			return null;
		}
	}
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (listView!=null) {
				listView.onRefreshComplete();
				
			}
			switch (msg.what) {
			case 10000:
				free_delivery_promptRel.setVisibility(View.VISIBLE);
				free_delivery_promptText.setText("服务器异常，点我试一试~");
				free_delivery_promptRel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						pageNow=1;
						new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
					}
				});
				MyProgressDialog.stopProgressDialog();
			break;
			case 10001:
				if (pageNow==1) {
					free_delivery_promptRel.setVisibility(View.VISIBLE);
					free_delivery_promptText.setText("没有查询到相关数据");
				}else {
					MyToast.Toast(getApplicationContext(), "没有更多数据了哟~");
				}
				MyProgressDialog.stopProgressDialog();
				break;
			case 0:
				if (pageNow==1) {
					adapter = new Free_DeliveryAdapter(list, Free_DeliveryActivity.this, Free_DeliveryActivity.this,imageLoader);
					listView.setAdapter(adapter);
				}else {
					adapter.notifyDataSetChanged();
				}
				listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
				listView.setVisibility(View.VISIBLE);
				MyProgressDialog.stopProgressDialog();
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent=new Intent(Free_DeliveryActivity.this,Crowdfunding_Product_DetailsActivity.class);
						intent.putExtra("goods_id", list.get(position).getGoods_id());
						startActivity(intent);
					}
				});
				break;
			case 4:
				break;

			default:
				break;
			}
		};
	};
	/**
	 * 根据条件查询商品的网络请求
	 * */
	public static String MyDoPost(String url,
			String good_region,String city_region, String small_area, String twolevel_id,
			String three_id, String buy_userid,int pageNow,int pageSize) {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormEncodingBuilder()
				.add("good_region", good_region)
				.add("city_region", city_region)
				.add("small_area", small_area)
				.add("twolevel_id", twolevel_id)
				.add("three_id", three_id)
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
