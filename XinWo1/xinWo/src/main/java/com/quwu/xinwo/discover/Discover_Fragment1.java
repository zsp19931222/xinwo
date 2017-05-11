package com.quwu.xinwo.discover;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.DF1_GridViewAdapter;
import com.quwu.xinwo.adapter.DF1_ListViewAdapter;
import com.quwu.xinwo.bean.DF1_GridViewBean;
import com.quwu.xinwo.bean.DF1_ListViewBean;
import com.quwu.xinwo.mywight.CustomProgressDialog;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.product_details.Crowdfunding_Product_DetailsActivity;

/**
 * 
 * 发现第一个界面
 * 
 * 
 * */

public class Discover_Fragment1 extends Fragment {
	private View view;

	private List<DF1_GridViewBean> list;
	private DF1_GridViewAdapter adapter;
	private MyGridView gridView;

	private MyListView listView;
	private DF1_ListViewAdapter listViewAdapter;
	private List<DF1_ListViewBean> listViewList;
	private List<String> urlList;
	private ImageLoader imageLoader;
	private CustomProgressDialog progressDialog = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.discover_fragment1, null);
		imageLoader = ImageLoader.getInstance();
		new GridViewTask().executeOnExecutor(Executors.newCachedThreadPool());
		return view;
	}


	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(getActivity());
			progressDialog.setMessage("正在加载中...");
		}
		progressDialog.show();
	}

	private void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	private class GridViewTask extends AsyncTask<Void, Void, Void> {

		
		protected Void doInBackground(Void... params) {
			isGridView();
			return null;
		}

		
		protected void onPreExecute() {
			
			super.onPreExecute();
			startProgressDialog();
		}

		
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
			isGridView1();
			new ListViewTask().executeOnExecutor(Executors
					.newCachedThreadPool());
		}
	}

	private class ListViewTask extends AsyncTask<Void, Void, Void> {

		
		protected Void doInBackground(Void... params) {
			isListView();
			return null;
		}

		
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			isListView1();
			stopProgressDialog();
		}
	}

	/***
	 * 
	 * 热门集市汇
	 * 
	 * */
	private void isGridView() {
		gridView = (MyGridView) view.findViewById(R.id.df1_gridview);
		gridView.setHorizontalSpacing(40); // 设置列表项水平间距
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		list = new ArrayList<DF1_GridViewBean>();
		for (int i = 0; i < 3; i++) {
			list.add(new DF1_GridViewBean(
					"http://h.hiphotos.baidu.com/image/h%3D200/sign=cd65e7fa13d5ad6eb5f963eab1cb39a3/377adab44aed2e7394aa5a128f01a18b87d6fa49.jpg",
					"123123", "2312342342323"));
		}
	}

	private void isGridView1() {
		adapter = new DF1_GridViewAdapter(list, getActivity());
		gridView.setAdapter(adapter);
	}

	/***
	 * 
	 * 心窝新鲜货
	 * 
	 * */
	private void isListView() {
		listView = (MyListView) view.findViewById(R.id.df1_listview);
		urlList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			urlList.add("http://h.hiphotos.baidu.com/image/h%3D200/sign=cd65e7fa13d5ad6eb5f963eab1cb39a3/377adab44aed2e7394aa5a128f01a18b87d6fa49.jpg");
		}
		listViewList = new ArrayList<DF1_ListViewBean>();
		for (int i = 0; i < 20; i++) {
			listViewList.add(new DF1_ListViewBean(urlList.get(0), "用户名",
					"时间  地区", "原价", "商品名称", "商品描述", "2700", "270", "1665",
					urlList));
		}
	}

	private void isListView1() {
		listViewAdapter = new DF1_ListViewAdapter(getActivity(), listViewList,
				getActivity(), imageLoader);
		listView.setAdapter(listViewAdapter);
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
				true, true));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						Crowdfunding_Product_DetailsActivity.class);
				startActivity(intent);
			}
		});
	}
}
