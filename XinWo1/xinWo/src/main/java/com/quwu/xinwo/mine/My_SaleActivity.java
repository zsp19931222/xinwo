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
import android.util.Log;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.My_SaleAdapter;
import com.quwu.xinwo.bean.My_SaleBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.My_SaleEntity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

public class My_SaleActivity extends SwipeBackActivity implements
		OnClickListener {

	private My_SaleAdapter adapter;
	private PullToRefreshListView listView;
	private List<My_SaleBean> list;
	private TextView my_sale_after_saleText;
	private ImageLoader imageLoader;
	
	private RelativeLayout my_sale_promptRel;
	private Button my_sale_promptBtn;
	private TextView my_sale_promptText;
	
	private int index=1;//点击了哪一个选项（1：全价，2：众筹，3：出租，4：拍卖）

	/**
	 * 
	 * 众筹
	 * 
	 * */
	private LinearLayout my_sale_crowdfundingLin;
	private TextView my_sale_crowdfundingText;
	private ImageView my_sale_crowdfundingImage;
	
	
	/**
	 * 
	 * 全价
	 * 
	 * */
	private LinearLayout my_sale_allLin;
	private TextView my_sale_allText;
	private ImageView my_sale_allImage;
	
	/**
	 * 
	 * 出租
	 * 
	 * */
	private LinearLayout my_sale_rent_outLin;
	private TextView my_sale_rent_outText;
	private ImageView my_sale_rent_outImage;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private LinearLayout my_sale_auctionLin;
	private TextView my_sale_auctionText;
	private ImageView my_sale_auctionImage;
	/**
	 * 
	 * 数据库返回数据
	 * 
	 * */
	private List<My_SaleEntity> json_list;
	
	private String	ins_time;//上架时间（int类型）
	private String	user_name;//买家名字（String类型）
	private String	goods_photo;//商品图片（String类型）
	private String	goods_price;//价格（int类型）
	private String	state;//商品状态（1=买家已付款,2=卖家已发货,3=交易成功（买家已收货）)（int类型）
	private String	goods_name;//商品名称（String类型）
	private String	good_sellfinshed;//商品交易时间（int类型）
	
	private String	total_person;//总需（int类型）
	
	private String rent_date;//租期（int类型）
	private String	rent_price;//租金/每月（int类型）
	
	private String	auction_currentprice;//成交价（int类型）
	private String	auction_person;//几人参与（int类型）

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(My_SaleActivity.this);
			progressDialog.setMessage("正在加载中...");
			listView.setVisibility(View.GONE);
			my_sale_promptRel.setVisibility(View.GONE);
		}
		progressDialog.show();
	}

	private void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	private int pageSize = 10;//一页加载多少条数据
	private int pageNo = 1;//多少页
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_sale);
		imageLoader=ImageLoader.getInstance();
		FinishActivity.finish(R.id.my_saleRel, My_SaleActivity.this);
		findID();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.my_sale_listview);
		listView.setMode(Mode.BOTH);
		listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			public void onLastItemVisible() {
				pageNo++;
				if (index==1) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
				}else if (index==2) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "2");
				}else if (index==3) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "3");
				}else if (index==4) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "4");
				}
			}
		});
		
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo=1;
				if (index==1) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
				}else if (index==2) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "2");
				}else if (index==3) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "3");
				}else if (index==4) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "4");
				}
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo++;
				if (index==1) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
				}else if (index==2) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "2");
				}else if (index==3) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "3");
				}else if (index==4) {
					new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "4");
				}
			}
		});
		
		my_sale_promptRel=(RelativeLayout) findViewById(R.id.my_sale_promptRel);
		my_sale_promptBtn=(Button) findViewById(R.id.my_sale_promptBtn);
		my_sale_promptText=(TextView) findViewById(R.id.my_sale_promptText);
		my_sale_promptRel.setOnClickListener(this);
		
		my_sale_after_saleText = (TextView) findViewById(R.id.my_sale_after_saleText);
		my_sale_after_saleText.setOnClickListener(this);

		my_sale_crowdfundingLin = (LinearLayout) findViewById(R.id.my_sale_crowdfundingLin);
		my_sale_allLin = (LinearLayout) findViewById(R.id.my_sale_allLin);
		my_sale_rent_outLin = (LinearLayout) findViewById(R.id.my_sale_rent_outLin);
		my_sale_auctionLin = (LinearLayout) findViewById(R.id.my_sale_auctionLin);

		my_sale_crowdfundingLin.setOnClickListener(this);
		my_sale_allLin.setOnClickListener(this);
		my_sale_rent_outLin.setOnClickListener(this);
		my_sale_auctionLin.setOnClickListener(this);

		my_sale_crowdfundingText = (TextView) findViewById(R.id.my_sale_crowdfundingText);
		my_sale_allText = (TextView) findViewById(R.id.my_sale_allText);
		my_sale_rent_outText = (TextView) findViewById(R.id.my_sale_rent_outText);
		my_sale_auctionText = (TextView) findViewById(R.id.my_sale_auctionText);

		my_sale_crowdfundingImage = (ImageView) findViewById(R.id.my_sale_crowdfundingImage);
		my_sale_allImage = (ImageView) findViewById(R.id.my_sale_allImage);
		my_sale_rent_outImage = (ImageView) findViewById(R.id.my_sale_rent_outImage);
		my_sale_auctionImage = (ImageView) findViewById(R.id.my_sale_auctionImage);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_sale_allLin:
			init();
			index=1;
			pageNo=1;
			my_sale_allText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			my_sale_allImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
			break;
		case R.id.my_sale_crowdfundingLin:
			init();
			index=2;
			pageNo=1;
			my_sale_crowdfundingText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			my_sale_crowdfundingImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "2");
			break;
		case R.id.my_sale_rent_outLin:
			init();
			index=3;
			pageNo=1;
			my_sale_rent_outText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			my_sale_rent_outImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "3");
			break;
		case R.id.my_sale_auctionLin:
			init();
			index=4;
			pageNo=1;
			my_sale_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			my_sale_auctionImage.setVisibility(View.VISIBLE);
			new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool(), "4");
			break;
		case R.id.my_sale_after_saleText:
			Intent intent = new Intent(My_SaleActivity.this,
					Goods_After_SaleActivity.class);
			startActivity(intent);
			break;
		case R.id.my_sale_promptRel:
			pageNo=1;
			if (index == 1) {
				my_sale_allText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				my_sale_allImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "1");
			} else if (index == 2) {
				my_sale_crowdfundingText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				my_sale_crowdfundingImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "2");
			} else if (index==3) {
				my_sale_rent_outText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				my_sale_rent_outImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(Executors
						.newCachedThreadPool(),"3");
			}else if (index==4) {
				my_sale_auctionText.setTextColor(this.getResources().getColor(
						R.color.淡红色));
				my_sale_auctionImage.setVisibility(View.VISIBLE);
				new LoadDataTask().executeOnExecutor(Executors
						.newCachedThreadPool(),"4");
			}
			break;
		default:
			break;
		}
	}

	private void init() {
		my_sale_crowdfundingText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		my_sale_allText
				.setTextColor(this.getResources().getColor(R.color.黑字体色));
		my_sale_rent_outText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		my_sale_auctionText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		my_sale_crowdfundingImage.setVisibility(View.INVISIBLE);
		my_sale_allImage.setVisibility(View.INVISIBLE);
		my_sale_rent_outImage.setVisibility(View.INVISIBLE);
		my_sale_auctionImage.setVisibility(View.INVISIBLE);
	}
	private class LoadDataTask extends AsyncTask<String, Void, Void>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (pageNo==1) {
				startProgressDialog();
			}
		}
		
		@Override
		protected Void doInBackground(String... params) {
			Log.e("index", index+"");
			if (pageNo==1) {
				list=new ArrayList<My_SaleBean>();
			}
			String result=OKHTTP_POST.doPost4(MyApp.base_address+"usersaction/getsell.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()), "state", params[0],"pageNo",String.valueOf(pageNo),"pageSize",String.valueOf(pageSize));
			if (result!=null) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String result1 = jsonObject.getString("1");
				if (result1!=null) {
				if (result1.equals("程序异常")) {
					handler.sendEmptyMessageDelayed(
							MyApp.Program_Exception, 10);
				} else if (result1.equals("没有数据")) {
					handler.sendMessage(handler.obtainMessage(MyApp.NODATA, params[0]));
				} else {
					Gson gson = new Gson();
					json_list = gson.fromJson(result1,
							new TypeToken<List<My_SaleEntity>>() {
							}.getType());
					for (int i = 0; i < json_list.size(); i++) {
						ins_time=json_list.get(i).getIns_time();
						user_name=json_list.get(i).getUser_name();
						goods_photo=json_list.get(i).getGoods_photo();
						goods_price=json_list.get(i).getGoods_price();
						goods_name=json_list.get(i).getGoods_name();
						state=json_list.get(i).getState();
						good_sellfinshed=json_list.get(i).getGood_sellfinshed();
						total_person=json_list.get(i).getTotal_person();
						rent_date=json_list.get(i).getRent_date();
						rent_price=json_list.get(i).getRent_price();
						auction_currentprice=json_list.get(i).getAuction_currentprice();
						auction_person=json_list.get(i).getAuction_person();
						if (params[0].equals("1")) {
							list.add(new My_SaleBean(MyApp.base_address+goods_photo, goods_name, goods_price, "买家："+user_name,"上架时间："+ TimeUtils.Time2(Long.valueOf(ins_time)), "交易时间："+TimeUtils.Time2(Long.valueOf(good_sellfinshed)), rent_price, "租期:"+rent_date+"周", auction_person,state));
						}else if (params[0].equals("2")) {
							list.add(new My_SaleBean(MyApp.base_address+goods_photo, goods_name, total_person, "买家："+user_name, "上架时间："+TimeUtils.Time2(Long.valueOf(ins_time)), "交易时间："+TimeUtils.Time2(Long.valueOf(good_sellfinshed)), rent_price, "租期:"+rent_date+"周", auction_person,state));
						}else if (params[0].equals("3")) {
							list.add(new My_SaleBean(MyApp.base_address+goods_photo, goods_name, total_person, "买家："+user_name,"上架时间："+ TimeUtils.Time2(Long.valueOf(ins_time)), "交易时间："+TimeUtils.Time2(Long.valueOf(good_sellfinshed)), rent_price, "租期:"+rent_date+"周", auction_person,state));
						}else if (params[0].equals("4")) {
							list.add(new My_SaleBean(MyApp.base_address+goods_photo, goods_name, auction_currentprice, "买家："+user_name, "上架时间："+TimeUtils.Time2(Long.valueOf(ins_time)), "交易时间："+TimeUtils.Time2(Long.valueOf(good_sellfinshed)), rent_price, "租期:"+rent_date+"周", auction_person+"人参与",state));
						}
					}
					handler.sendMessage(handler.obtainMessage(0, params[0]));
				}
				}else {
					handler.sendEmptyMessageDelayed(
							MyApp.Program_Exception, 10);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				handler.sendEmptyMessageDelayed(
						MyApp.Program_Exception, 10);
			}
			return null;
		}
		
	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 10000:
				my_sale_promptRel.setVisibility(View.VISIBLE);
				my_sale_promptBtn.setBackgroundResource(R.drawable.fail_landing);
				my_sale_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getApplicationContext(),
						my_sale_promptBtn, 0.27);
				stopProgressDialog();
				break;
			case 10001:
				if (pageNo==1) {
					my_sale_promptRel.setVisibility(View.VISIBLE);
				String string=(String) msg.obj;
				if (string.equals("1")) {
					my_sale_promptText.setText("您还没有卖出的全价商品哦~");
				}else if (string.equals("2")) {
					my_sale_promptText.setText("您还没有卖出的众筹商品哦~");
				}else if (string.equals("3")) {
					my_sale_promptText.setText("您还没有卖出的出租商品哦~");
				}else if (string.equals("4")) {
					my_sale_promptText.setText("您还没有卖出的拍卖商品哦~");
				}
				SetW_H.setRelativeLayout(getApplicationContext(),
						my_sale_promptBtn, 0.27);
				}else {
					ILoadingLayout startLabels = listView.getLoadingLayoutProxy(
							false, true);
					startLabels.setPullLabel("没有更多数据了哟");// 刚下拉时，显示的提示
					startLabels.setRefreshingLabel("没有更多数据了哟");// 刷新时
					startLabels.setReleaseLabel("没有更多数据了哟");// 下来达到一定距离时，显示的提示
				}
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 0:
				listView.setVisibility(View.VISIBLE);
				if (pageNo==1) {
					adapter = new My_SaleAdapter(getApplicationContext(), list, Integer.valueOf((String)msg.obj),
							My_SaleActivity.this,imageLoader);
					listView.setAdapter(adapter);
				}else {
					if (adapter!=null) {
						adapter.notifyDataSetChanged();
					}
				}
				listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
				stopProgressDialog();
				listView.onRefreshComplete();
				break;
			default:
				break;
			}
		};
	};
}
