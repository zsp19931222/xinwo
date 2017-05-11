package com.quwu.xinwo.home_page;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.City_WideAdapter;
import com.quwu.xinwo.bean.City_WideBean;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class City_Wide_Vp_ClassesActivity extends SwipeBackActivity {

	private TextView titleText;
	private PullToRefreshListView listView;
	private List<City_WideBean> listviewList;
	private City_WideAdapter listviewadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_wide_vp_classes);
		FinishActivity.finish(R.id.city_wide_vp_classesRel,
				City_Wide_Vp_ClassesActivity.this);
		isTitle();
		isListView();
	}

	private void isListView() {
		listView = (PullToRefreshListView) findViewById(R.id.city_wide_vp_classes_listview);
		listviewList = new ArrayList<City_WideBean>();
		for (int i = 0; i < 10; i++) {
			listviewList.add(new City_WideBean(
					"http://pic1a.nipic.com/2008-12-01/2008121175139413_2.jpg",
					"商品名称", "商品描述", "重庆 渝北区\t\t10小时前", "2700", "1", "654"));
		}
		listviewadapter = new City_WideAdapter(listviewList,
				getApplicationContext());
		listView.setAdapter(listviewadapter);
	}
	private void isTitle() {
		titleText=(TextView) findViewById(R.id.city_wide_vp_classes_titleText);
	}
}
