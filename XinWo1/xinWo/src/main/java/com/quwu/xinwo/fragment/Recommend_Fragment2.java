package com.quwu.xinwo.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Recommend_AuctionAdapter;
import com.quwu.xinwo.adapter.Recommend_Crowd_BundingAdapter;
import com.quwu.xinwo.adapter.Recommend_Rent_OutAdapter;
import com.quwu.xinwo.bean.Recommend_Fragment2Bean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Recommend_Fragment1Entity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;

public class Recommend_Fragment2 extends Fragment {

	private View view;
	private PullToRefreshListView listView;
	private int i;// 标识 （0=众筹，1=拍卖，2=出租）
	private List<Recommend_Fragment2Bean> list;
	
	private RelativeLayout recommend_fragment2_promptRel;
	private Button recommend_fragment2_promptBtn;
	private TextView recommend_fragment2_promptText;


	private List<Recommend_Fragment1Entity> json_list;

	private int pageSize = 10;// 一页加载多少条数据
	private int pageNo = 1;// 多少页

	/**
	 * 等待动画
	 * */

	private CustomProgressDialog progressDialog = null;

	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(getActivity());
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

	public Recommend_Fragment2(int i) {
		super();
		this.i = i;
	}

	/**
	 * 返回的参数
	 * */
	private String	bigpicture1;//	图片1
	private String	bigpicture2;//	图片2
	private String	bigpicture3;//	图片3
	private String	bigpicture4;//	图片4
	private String	bigpicture5;//	图片5
	private String	bigpicture6;//	图片6
	private String	bigpicture7;//	图片7
	private String	bigpicture8;//	图片8
	private String	bigpicture9;//	图片9
	private String	bigpicture10;//	图片10
	private String	goods_name;//		商品名字
	private String	goods_id;//		商品id
	private String	good_description;//		商品描述
	private String	ins_time;//		几小时钱（时间戳）
	private String	total_person	;//总需人次
	private String	participant_person;//	参与人次
	
	private String	goods_photo;//	商品图片
	private String	current_price;//		当前价格
	
	private String	goods_price	;//价格
	private String	rentprice	;//租金
	private String	renttime	;
	private String	address	;// 地区
	
	
	/**
	 * 众筹
	 * */
	private Recommend_Crowd_BundingAdapter crowd_BundingAdapter;
	private List<String> urlList;

	/**
	 * 拍卖
	 * */
	private Recommend_AuctionAdapter auctionAdapter;
	
	/**
	 * 出租
	 * */
	private Recommend_Rent_OutAdapter rent_OutAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.recommend_fragment2, null);
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool(),String.valueOf(i));
		return view;
	}

	private void findID() {
		listView = (PullToRefreshListView) view
				.findViewById(R.id.recommend_fragment2_listview);
		recommend_fragment2_promptRel = (RelativeLayout) view
				.findViewById(R.id.recommend_fragment2_promptRel);
		recommend_fragment2_promptBtn = (Button) view
				.findViewById(R.id.recommend_fragment2_promptBtn);
		recommend_fragment2_promptText = (TextView) view
				.findViewById(R.id.recommend_fragment2_promptText);
		recommend_fragment2_promptRel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool(),String.valueOf(i));
			}
		});
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
			pageNo=1;
			new LoadTask().executeOnExecutor(Executors
					.newCachedThreadPool(),String.valueOf(i));
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				pageNo++;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool(),String.valueOf(i));				
			}
		});
	}

//	private void islistView() {
//		if (i == 0) {
//			urlList = new ArrayList<String>();
//			for (int i = 0; i < 20; i++) {
//				urlList.add("http://img3.3lian.com/2013/c2/64/d/73.jpg");
//			}
//			list = new ArrayList<Recommend_Fragment2Bean>();
//			for (int i = 0; i < 5; i++) {
//				list.add(new Recommend_Fragment2Bean(urlList,
//						"商品名称", "商品描述", "时间", "地区", "122", "剩余：12", 54));
//			}
//			crowd_BundingAdapter = new Recommend_Crowd_BundingAdapter(
//					list, getActivity(), getActivity());
//			listView.setAdapter(crowd_BundingAdapter);
//		}else if (i==1) {
//			list=new ArrayList<Recommend_Fragment2Bean>();
//			for (int i = 0; i < 4; i++) {
//				list.add(new Recommend_Fragment2Bean("商品名称", "商品描述", "45", "http://c.hiphotos.baidu.com/zhidao/pic/item/72f082025aafa40f51382e0ba964034f79f0196d.jpg", "12人参与"));
//			}
//			auctionAdapter=new Recommend_AuctionAdapter(getActivity(), list);
//			listView.setAdapter(auctionAdapter);
//		}else if (i==2) {
//			list=new ArrayList<Recommend_Fragment2Bean>();
//			for (int i = 0; i < 4; i++) {
//				list.add(new Recommend_Fragment2Bean("商品名称", "24", "http://cdn.duitang.com/uploads/item/201510/11/20151011195344_aVZRx.jpeg", "58", "3月", "我想试试>>"));
//			}
//			rent_OutAdapter=new Recommend_Rent_OutAdapter(getActivity(), list);
//			listView.setAdapter(rent_OutAdapter);
//		}
//	}
	private class LoadTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}

		@Override
		protected Void doInBackground(String... params) {
			if (pageNo==1) {
				list = new ArrayList<Recommend_Fragment2Bean>();
			}
			String string = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/recommend.do", "state", params[0], "pageNo",
					String.valueOf(pageNo), "pageSize",
					String.valueOf(pageSize));
			System.out.println(string);
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
										new TypeToken<List<Recommend_Fragment1Entity>>() {
										}.getType());
						if (params[0].equals("2")) {
							urlList = new ArrayList<String>();
							for (int i = 0; i <json_list.size(); i++) {
								bigpicture1=json_list.get(i).getBigpicture1();
								bigpicture2=json_list.get(i).getBigpicture2();
								bigpicture3=json_list.get(i).getBigpicture3();
								bigpicture4=json_list.get(i).getBigpicture4();
								bigpicture5=json_list.get(i).getBigpicture5();
								bigpicture6=json_list.get(i).getBigpicture6();
								bigpicture7=json_list.get(i).getBigpicture7();
								bigpicture8=json_list.get(i).getBigpicture8();
								bigpicture9=json_list.get(i).getBigpicture9();
								bigpicture10=json_list.get(i).getBigpicture10();
								goods_name=json_list.get(i).getGoods_name();
								goods_id=json_list.get(i).getGoods_id();
								good_description=json_list.get(i).getGood_description();
								ins_time=json_list.get(i).getIns_time();
								total_person=json_list.get(i).getTotal_person();
								participant_person=json_list.get(i).getParticipant_person();
								address =json_list.get(i).getAddress();
								if (bigpicture1!=null||!bigpicture1.equals("")) {
									urlList.add(MyApp.base_address+bigpicture1);
								}
								else if (bigpicture2!=null||!bigpicture2.equals("")) {
									urlList.add(MyApp.base_address+bigpicture2);
								}
								else if (bigpicture3!=null||!bigpicture3.equals("")) {
									urlList.add(MyApp.base_address+bigpicture3);
								}
								else if (bigpicture4!=null||!bigpicture4.equals("")) {
									urlList.add(MyApp.base_address+bigpicture4);
								}
								else if (bigpicture5!=null||!bigpicture5.equals("")) {
									urlList.add(MyApp.base_address+bigpicture5);
								}
								else if (bigpicture6!=null||!bigpicture6.equals("")) {
									urlList.add(MyApp.base_address+bigpicture6);
								}
								else if (bigpicture7!=null||!bigpicture7.equals("")) {
									urlList.add(MyApp.base_address+bigpicture7);
								}
								else if (bigpicture8!=null||!bigpicture8.equals("")) {
									urlList.add(MyApp.base_address+bigpicture8);
								}
								else if (bigpicture9!=null||!bigpicture9.equals("")) {
									urlList.add(MyApp.base_address+bigpicture9);
								}
								else if (bigpicture10!=null||!bigpicture10.equals("")) {
									urlList.add(MyApp.base_address+bigpicture10);
								}
								list.add(new Recommend_Fragment2Bean(urlList, goods_name, good_description, TimeUtils.isTime_Difference(Long.valueOf(ins_time)), address, total_person, "剩余："+participant_person, (Integer.valueOf(total_person)- Integer.valueOf(participant_person)),goods_id));
							}
							handler.sendEmptyMessageDelayed(2, 10);
						}else if (params[0].equals("3")) {
							for (int i = 0; i <json_list.size() ; i++) {
							goods_photo=json_list.get(i).getGoods_photo();
							goods_name=json_list.get(i).getGoods_name();
							goods_id	=json_list.get(i).getGoods_id();
							good_description	=json_list.get(i).getGood_description();
							current_price	=json_list.get(i).getCurrent_price();
							participant_person	=json_list.get(i).getParticipant_person();
							list.add(new Recommend_Fragment2Bean(goods_name, good_description, current_price, MyApp.base_address+goods_photo, participant_person, goods_id));
						}
							handler.sendEmptyMessageDelayed(3, 10);
						}else if (params[0].equals("4")) {
							for (int i = 0; i <json_list.size() ; i++) {
								goods_photo=json_list.get(i).getGoods_photo();
								goods_name=json_list.get(i).getGoods_name();
								goods_id	=json_list.get(i).getGoods_id();
								goods_price	=json_list.get(i).getGoods_price();
								rentprice	=json_list.get(i).getRentprice();
								renttime	=json_list.get(i).getRenttime();
								list.add(new Recommend_Fragment2Bean(goods_name, goods_price, MyApp.base_address+goods_photo, rentprice, renttime, "我想试试>>", goods_id));
							}
							handler.sendEmptyMessageDelayed(4, 10);
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
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
				SetW_H.setRelativeLayout(getActivity(),
						recommend_fragment2_promptBtn, 0.27);
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 1:
				if (pageNo == 1) {
					recommend_fragment2_promptRel.setVisibility(View.VISIBLE);
					recommend_fragment2_promptBtn
							.setBackgroundResource(R.drawable.cry);
					recommend_fragment2_promptText.setText("当前还没有数据哟~");
					SetW_H.setRelativeLayout(getActivity(),
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
			case 3://拍卖
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					auctionAdapter =new Recommend_AuctionAdapter(getActivity(), list);
					listView.setAdapter(auctionAdapter);
				} else {
					if (auctionAdapter != null) {
						auctionAdapter.notifyDataSetChanged();
					}
				}
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 2://众筹
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					crowd_BundingAdapter =new Recommend_Crowd_BundingAdapter(list, getActivity(), getActivity());
					listView.setAdapter(crowd_BundingAdapter);
				} else {
					if (crowd_BundingAdapter != null) {
						crowd_BundingAdapter.notifyDataSetChanged();
					}
				}
				listView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 4://出租
				listView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					rent_OutAdapter =new Recommend_Rent_OutAdapter(getActivity(), list);
					listView.setAdapter(rent_OutAdapter);
				} else {
					if (rent_OutAdapter != null) {
						rent_OutAdapter.notifyDataSetChanged();
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
