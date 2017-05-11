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
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.After_SaleAdapter;
import com.quwu.xinwo.adapter.Recommend_AllPriceAdapter;
import com.quwu.xinwo.bean.After_SaleBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.After_SaleEntity;
import com.quwu.xinwo.gson_entity.Recommend_Fragment1Entity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

/**
 * 
 * 售后界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class After_SaleActivity extends SwipeBackActivity {

	private List<After_SaleBean> list;
	private After_SaleAdapter adapter;
	private PullToRefreshListView listView;
	private ImageLoader imageLoader;

	private RelativeLayout recommend_fragment2_promptRel;
	private Button recommend_fragment2_promptBtn;
	private TextView recommend_fragment2_promptText;

	private List<After_SaleEntity> json_list;
	private String order_no;// 订单编号
	private String gettime;// 获得时间
	private String goods_pictrue;// 商品图片
	private String goods_name;// 商品名字
	private String price;// 交易金额
	private String state;// 状态（0==还没有申请退款，1=已申请退款）
	private String u_id;// 卖家
	private String user_id;// 买家

	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog
					.createDialog(After_SaleActivity.this);
			progressDialog.setMessage("正在加载中...");
			recommend_fragment2_promptRel.setVisibility(View.GONE);
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

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageLoader = ImageLoader.getInstance();
		setContentView(R.layout.after_sale);
		FinishActivity.finish(R.id.after_saleRel, After_SaleActivity.this);
//		isListView();
	}

	private void findID() {
		listView = (PullToRefreshListView) findViewById(R.id.after_sale_listview);
		recommend_fragment2_promptRel = (RelativeLayout) 
				findViewById(R.id.recommend_fragment2_promptRel);
		recommend_fragment2_promptBtn = (Button) 
				findViewById(R.id.recommend_fragment2_promptBtn);
		recommend_fragment2_promptText = (TextView) 
				findViewById(R.id.recommend_fragment2_promptText);
		recommend_fragment2_promptRel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
			pageNo=1;
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
	}
	
//	private void isListView() {
//		listView = (PullToRefreshListView) findViewById(R.id.after_sale_listview);
//		list = new ArrayList<After_SaleBean>();
//		list.add(new After_SaleBean(
//				"http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
//				"等待卖家发货", "获得时间：2016-11-11-11 11：11：11", "商品名称", "¥9999",
//				"联系卖家", "申请退款", false));
//		list.add(new After_SaleBean(
//				"http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
//				"等待确认收货", "获得时间：2016-11-11-11 11：11：11", "商品名称", "¥9999",
//				"联系卖家", "申请退款", true));
//		list.add(new After_SaleBean(
//				"http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
//				"退款中", "获得时间：2016-11-11-11 11：11：11", "商品名称", "¥9999", "联系卖家",
//				"查看申请", true));
//		list.add(new After_SaleBean(
//				"http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
//				"退款成功", "获得时间：2016-11-11-11 11：11：11", "商品名称", "¥9999", "联系卖家",
//				"已退款", false));
//		adapter = new After_SaleAdapter(getApplicationContext(), list,
//				imageLoader);
//		listView.setAdapter(adapter);
//		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
//				true, true));
//	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			if (pageNo==1) {
				list=new ArrayList<After_SaleBean>();
			}
			String string = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/apply.do", "user_id",
					MySharePreferences.GetUser_ID(After_SaleActivity.this),
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
						json_list = gson
								.fromJson(
										string2,
										new TypeToken<List<After_SaleEntity>>() {
										}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							order_no=json_list.get(i).getOrder_no();
							gettime=json_list.get(i).getGettime();
							goods_pictrue	=json_list.get(i).getGoods_pictrue();
							goods_name=json_list.get(i).getGoods_name();
							price=json_list.get(i).getPrice();
							state=json_list.get(i).getState();
							u_id=json_list.get(i).getU_id();
							user_id=json_list.get(i).getUser_id();
							if (state.equals("0")) {//还没有申请退款
//								list.add(new After_SaleBean(MyApp.base_address+goods_pictrue, goodState,"获得时间："+ TimeUtils.Time(Long.valueOf(gettime)), goods_name, "¥"+price, "联系卖家", "申请退款", false,"订单编号："order_no))
							}else if (state.equals("1")) {//已申请退款
//								list.add(new After_SaleBean(MyApp.base_address+goods_pictrue, goodState,"获得时间："+ TimeUtils.Time(Long.valueOf(gettime)), goods_name, "¥"+price, "联系卖家", boxText, true,"订单编号："order_no))
							}
						}
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
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				recommend_fragment2_promptRel.setVisibility(View.VISIBLE);
				recommend_fragment2_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				recommend_fragment2_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(After_SaleActivity.this,
						recommend_fragment2_promptBtn, 0.27);
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 1:
				if (pageNo == 1) {
					recommend_fragment2_promptRel.setVisibility(View.VISIBLE);
					recommend_fragment2_promptBtn
							.setBackgroundResource(R.drawable.cry);
					recommend_fragment2_promptText.setText("您还没有下架商品哦~");
					SetW_H.setRelativeLayout(After_SaleActivity.this,
							recommend_fragment2_promptBtn, 0.27);
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
			case 2:
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter = new After_SaleAdapter(After_SaleActivity.this, list, imageLoader);
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
