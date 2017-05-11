package com.quwu.xinwo.home_page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.DF2_ListViewAdapter;
import com.quwu.xinwo.bean.DF2_ListViewBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.popupwindow.City_Wide_All_pop;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Rank_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Screen_Pop;
import com.quwu.xinwo.pull.MyListener;
import com.quwu.xinwo.pull.PullToRefreshLayout;
import com.quwu.xinwo.pull.PullableScrollView;
import com.quwu.xinwo.pull.PullableScrollView.OnScrollListener;
import com.quwu.xinwo.pull.ScrollViewListener;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

public class AuctionActivity extends SwipeBackActivity implements
		OnClickListener, OnScrollListener, ScrollViewListener {
	private PullableScrollView scrollView;
	private MyListView listView;
	private List<DF2_ListViewBean> list;
	private DF2_ListViewAdapter adapter;
	private City_Wide_All_pop all_pop;
	private HashMap<Integer, Integer> scrollYMap;

	private LinearLayout allCityLin;// 全市
	private LinearLayout classesLin;// 类别
	private LinearLayout rankLin;// 排序
	private LinearLayout screenLin;// 筛选
	// 顶部
	private LinearLayout allCityLin1;// 全市
	private LinearLayout classesLin1;// 类别
	private LinearLayout rankLin1;// 排序
	private LinearLayout screenLin1;// 筛选

	private LinearLayout layout2;
	private LinearLayout layout1;
	private PullToRefreshLayout parent_layout;

	private ImageView imageView;

	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auction);
		imageLoader = ImageLoader.getInstance();
		scrollYMap = new HashMap<Integer, Integer>();
		FinishActivity.finish(R.id.auction_returnRel, AuctionActivity.this);
		new StartTask().executeOnExecutor(Executors.newCachedThreadPool());

	}

	private class StartTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			findID();
			isListView();
		}
	}

	private void findID() {
		imageView = (ImageView) findViewById(R.id.auction_iamge);
		ImageloaderUtils
				.MyImageLoader2(
						"http://g.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ecfc13ccc066d0f703918fc12c.jpg",
						imageView, AuctionActivity.this);

		layout1 = (LinearLayout) findViewById(R.id.auction_layout2);
		layout2 = (LinearLayout) findViewById(R.id.auction_layout1);
		parent_layout = (PullToRefreshLayout) findViewById(R.id.auction_parent_layout);
		scrollView = (PullableScrollView) findViewById(R.id.auction_scrollView);
		scrollView.setOnScrollListener(this);
		scrollView.setScrollViewListener(this);
		parent_layout.setOnRefreshListener(new MyListener());
		parent_layout.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					public void onGlobalLayout() {
						onScroll(scrollView.getScrollY());
					}
				});

		listView = (MyListView) findViewById(R.id.auction_listview);

		View view = findViewById(R.id.auction_layout2);
		View view1 = findViewById(R.id.auction_layout1);
		allCityLin = (LinearLayout) view
				.findViewById(R.id.city_wide_allcityLin);
		allCityLin1 = (LinearLayout) view1
				.findViewById(R.id.city_wide_allcityLin);
		allCityLin.setOnClickListener(this);
		allCityLin1.setOnClickListener(this);
		classesLin = (LinearLayout) view
				.findViewById(R.id.city_wide_classesLin);
		classesLin1 = (LinearLayout) view1
				.findViewById(R.id.city_wide_classesLin);
		classesLin.setOnClickListener(this);
		classesLin1.setOnClickListener(this);
		rankLin = (LinearLayout) view.findViewById(R.id.city_wide_rankLin);
		rankLin1 = (LinearLayout) view1.findViewById(R.id.city_wide_rankLin);
		rankLin.setOnClickListener(this);
		rankLin1.setOnClickListener(this);
		screenLin = (LinearLayout) view.findViewById(R.id.city_wide_screenLin);
		screenLin1 = (LinearLayout) view1
				.findViewById(R.id.city_wide_screenLin);
		screenLin.setOnClickListener(this);
		screenLin1.setOnClickListener(this);
	}

	/**
	 * 监听滑动
	 * */
	public void onScrollChanged(PullableScrollView scrollView, int x, int y,
			int oldx, int oldy) {
		// 监听到底部
		View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
		int d = view.getBottom();
		d -= (scrollView.getHeight() + scrollView.getScrollY());
		if (d == 0) {
			// new Task().executeOnExecutor(Executors.newCachedThreadPool());
		} else {

		}
	}

	/**
	 * 监听滑动高度
	 * */
	public void onScroll(int scrollY) {
		int mBuyLayout2ParentTop = Math.max(scrollY, layout2.getTop());
		if (scrollY == 0) {
			scrollYMap.put(scrollY, mBuyLayout2ParentTop);
		}
		layout1.layout(0, mBuyLayout2ParentTop, layout1.getWidth(),
				mBuyLayout2ParentTop + layout1.getHeight());
	}

	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.city_wide_allcityLin:
				all_pop = new City_Wide_All_pop(AuctionActivity.this,
						scrollView, scrollYMap.get(0));
				all_pop.showPopupWindow(allCityLin);
				break;
			case R.id.city_wide_classesLin:
				City_Wide_Classes_Pop classes_pop = new City_Wide_Classes_Pop(
						AuctionActivity.this);
				classes_pop.showPopupWindow(classesLin);
				scrollView.smoothScrollTo(0, scrollYMap.get(0) / 2);
				break;
			case R.id.city_wide_rankLin:
				City_Wide_Rank_Pop rank_pop = new City_Wide_Rank_Pop(
						AuctionActivity.this);
				rank_pop.showPopupWindow(classesLin);
				scrollView.smoothScrollTo(0, scrollYMap.get(0) / 2);
				break;
			case R.id.city_wide_screenLin:
				City_Wide_Screen_Pop screen_pop = new City_Wide_Screen_Pop(
						AuctionActivity.this,"","","","","","","","","");
				screen_pop.showPopupWindow(screenLin);
				scrollView.smoothScrollTo(0, scrollYMap.get(0) / 2);
				break;
			default:
				break;
			}
		}
	}

	private void isListView() {
		list = new ArrayList<DF2_ListViewBean>();
		for (int i = 0; i < 10; i++) {
			list.add(new DF2_ListViewBean(
					"http://f.hiphotos.baidu.com/image/h%3D200/sign=a31c9680a1773912db268261c8198675/730e0cf3d7ca7bcb5f591712b6096b63f624a8e9.jpg",
					"商品名字", "商品描述", "123", "45"));
		}
		adapter = new DF2_ListViewAdapter(AuctionActivity.this, list,
				imageLoader);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
				true, true));
	}
}
