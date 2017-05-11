package com.quwu.xinwo.discover;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.DF2_ListViewAdapter;
import com.quwu.xinwo.bean.DF2_ListViewBean;
import com.quwu.xinwo.until.Tool;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * 
 * 发现第二个界面
 * 
 * */

public class Discover_Fragment2 extends Fragment implements OnClickListener {

	private View view;
	private PullToRefreshListView listView;
	private List<DF2_ListViewBean> list;
	private DF2_ListViewAdapter adapter;
	private LinearLayout cityLin, lifeLin, rankLin, screenLin;
	private ImageLoader imageLoader;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
imageLoader=ImageLoader.getInstance();
		view = inflater.inflate(R.layout.discover_fragment2, null);
		isListView(0);
//		findID();
		return view;
	}

//	private void findID() {
//		cityLin = (LinearLayout) view.findViewById(R.id.df2_cityLin);
//		lifeLin = (LinearLayout) view.findViewById(R.id.df2_lifeLin);
//		rankLin = (LinearLayout) view.findViewById(R.id.df2_rankLin);
//		screenLin = (LinearLayout) view.findViewById(R.id.df2_screenLin);
//
//		cityLin.setOnClickListener(this);
//		lifeLin.setOnClickListener(this);
//		rankLin.setOnClickListener(this);
//		screenLin.setOnClickListener(this);
//
//	}

	private void isListView(int a) {
		listView = (PullToRefreshListView) view.findViewById(R.id.df2_listview);
		list = new ArrayList<DF2_ListViewBean>();
//		if (a == 0) {// 全市
			for (int i = 0; i < 10; i++) {
				list.add(new DF2_ListViewBean(
						"http://f.hiphotos.baidu.com/image/h%3D200/sign=a31c9680a1773912db268261c8198675/730e0cf3d7ca7bcb5f591712b6096b63f624a8e9.jpg",
						"商品名字", "商品描述", "123", "45"));
			}
//		} else if (a == 1) {// 生活大件
//			for (int i = 0; i < 10; i++) {
//				list.add(new DF2_ListViewBean(
//						"http://h.hiphotos.baidu.com/image/h%3D200/sign=7ae91b27d6c8a786a12a4d0e570bc9c7/a50f4bfbfbedab645cb9f61df036afc378311e27.jpg",
//						"商品名字", "商品描述", "123", "45"));
//			}
//		} else if (a == 2) {// 排序
//			for (int i = 0; i < 10; i++) {
//				list.add(new DF2_ListViewBean(
//						"http://img4.duitang.com/uploads/item/201402/19/20140219214112_VHNVS.jpeg",
//						"商品名字", "商品描述", "123", "45"));
//			}
//		} else if (a == 3) {// 筛选
//			for (int i = 0; i < 10; i++) {
//				list.add(new DF2_ListViewBean(
//						"http://cdn.duitang.com/uploads/item/201309/22/20130922202150_ntvAB.thumb.600_0.jpeg",
//						"商品名字", "商品描述", "123", "45"));
//			}
//		}
		adapter = new DF2_ListViewAdapter(getActivity(), list,imageLoader);
//		getActivity().runOnUiThread(new Runnable() {
//
//			public void run() {
				listView.setAdapter(adapter);
				listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
//			}
//		});
	}

//	@Override
	public void onClick(View v) {
//		if (Tool.isFastDoubleClick()) {
//			return;
//		} else {
//			switch (v.getId()) {
//			case R.id.df2_cityLin:
//				isListView(0);
//				break;
//			case R.id.df2_lifeLin:
//				isListView(1);
//				break;
//			case R.id.df2_rankLin:
//				isListView(2);
//				break;
//			case R.id.df2_screenLin:
//				isListView(3);
//				break;
//
//			default:
//				break;
//			}
//		}
	}
}
