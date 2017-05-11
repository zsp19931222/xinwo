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
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Recommend_AllPriceAdapter;
import com.quwu.xinwo.bean.Recommend_AllPriceBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Recommend_Fragment1Entity;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.SetW_H;

public class Recommend_Fragment1 extends Fragment {

	private View view;
	private PullToRefreshGridView gridView;
	private Recommend_AllPriceAdapter adapter;
	private List<Recommend_AllPriceBean> list;

	private RelativeLayout recommend_fragment1_promptRel;
	private Button recommend_fragment1_promptBtn;
	private TextView recommend_fragment1_promptText;

	/**
	 * 返回数据
	 * */
	private String goods_photo; // 商品图片
	private String goods_name; // 商品名字
	private String goods_id; // 商品id
	private String good_description; // 商品描述
	private String goods_price; // 价格
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
			recommend_fragment1_promptRel.setVisibility(View.GONE);
			gridView.setVisibility(View.VISIBLE);
		}
		progressDialog.show();
	}

	private void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.recommend_fragment1, null);
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
		return view;
	}

	private void findID() {
		gridView = (PullToRefreshGridView) view
				.findViewById(R.id.recommend_fragment1_gridview);
		recommend_fragment1_promptRel = (RelativeLayout) view
				.findViewById(R.id.recommend_fragment1_promptRel);
		recommend_fragment1_promptBtn = (Button) view
				.findViewById(R.id.recommend_fragment1_promptBtn);
		recommend_fragment1_promptText = (TextView) view
				.findViewById(R.id.recommend_fragment1_promptText);
		recommend_fragment1_promptRel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
		gridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<GridView> refreshView) {
			pageNo=1;
			new LoadTask().executeOnExecutor(Executors
					.newCachedThreadPool());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				pageNo++;
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());				
			}
		});
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pageNo == 1) {
				startProgressDialog();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			if (pageNo==1) {
				list = new ArrayList<Recommend_AllPriceBean>();
			}
			String string = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/recommend.do", "state", "1", "pageNo",
					String.valueOf(pageNo), "pageSize",
					String.valueOf(pageSize));
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
						for (int i = 0; i < json_list.size(); i++) {
							goods_photo = json_list.get(i).getGoods_photo();
							goods_name = json_list.get(i).getGoods_name();
							goods_id = json_list.get(i).getGoods_id();
							good_description = json_list.get(i)
									.getGood_description();
							goods_price = json_list.get(i).getGoods_price();
							list.add(new Recommend_AllPriceBean(
									MyApp.base_address + goods_photo,
									good_description, goods_name, goods_price,
									goods_id));
						}
						handler.sendEmptyMessageDelayed(2, 10);
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
				recommend_fragment1_promptRel.setVisibility(View.VISIBLE);
				recommend_fragment1_promptBtn
						.setBackgroundResource(R.drawable.fail_landing);
				recommend_fragment1_promptText.setText("咦，怎么加载失败了！\n点击刷新试试");
				SetW_H.setRelativeLayout(getActivity(),
						recommend_fragment1_promptBtn, 0.27);
				gridView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 1:
				if (pageNo == 1) {
					recommend_fragment1_promptRel.setVisibility(View.VISIBLE);
					recommend_fragment1_promptBtn
							.setBackgroundResource(R.drawable.cry);
					recommend_fragment1_promptText.setText("您还没有下架商品哦~");
					SetW_H.setRelativeLayout(getActivity(),
							recommend_fragment1_promptBtn, 0.27);
				} else {
					ILoadingLayout startLabels = gridView
							.getLoadingLayoutProxy(false, true);
					startLabels.setPullLabel("没有更多数据了哟");// 刚下拉时，显示的提示
					startLabels.setRefreshingLabel("没有更多数据了哟");// 刷新时
					startLabels.setReleaseLabel("没有更多数据了哟");// 下来达到一定距离时，显示的提示
				}
				gridView.onRefreshComplete();
				stopProgressDialog();
				break;
			case 2:
				gridView.setVisibility(View.VISIBLE);
				if (pageNo == 1) {
					adapter = new Recommend_AllPriceAdapter(getActivity(), list);
					gridView.setAdapter(adapter);
				} else {
					if (adapter != null) {
						adapter.notifyDataSetChanged();
					}
				}
				gridView.onRefreshComplete();
				stopProgressDialog();
				break;

			default:
				break;
			}
		};
	};
}
