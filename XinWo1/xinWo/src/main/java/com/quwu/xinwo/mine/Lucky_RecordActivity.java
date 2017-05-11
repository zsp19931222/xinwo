package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import android.os.AsyncTask;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Lucky_RecordAdapter;
import com.quwu.xinwo.bean.Lucky_RecordBean;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class Lucky_RecordActivity extends SwipeBackActivity {

	private PullToRefreshListView listView;
	private Lucky_RecordAdapter adapter;
	private List<Lucky_RecordBean> list;
	private ImageLoader imageLoader ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lucky_record);
		FinishActivity.finish(R.id.lucky_recordRel, Lucky_RecordActivity.this);
		
	new StartTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private class StartTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			findiD();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			isListView();
		}
		}
	
	private void findiD() {
		listView = (PullToRefreshListView) findViewById(R.id.lucky_record_listview);
	}

	private void isListView() {
		list = new ArrayList<Lucky_RecordBean>();
		 imageLoader = ImageLoader.getInstance();
		for (int i = 0; i < 20; i++) {
		list.add(new Lucky_RecordBean(
				"http://v1.qzone.cc/pic/201510/31/11/17/563432b7977c7764.jpeg%21600x600.jpg",
				"商品名称", "描述", "总需：", "1988", "参与人次：", "3", "幸运号：", "10086",
				"揭晓时间：", "2015-2-11", "0"));
		list.add(new Lucky_RecordBean(
				"http://v1.qzone.cc/pic/201510/31/11/17/563432b7977c7764.jpeg%21600x600.jpg",
				"商品名称", "描述", "", "","租期：", "18月", "租金：", "100元/月",  "下单时间：",
				"2015-2-11", "1"));
		list.add(new Lucky_RecordBean(
				"http://v1.qzone.cc/pic/201510/31/11/17/563432b7977c7764.jpeg%21600x600.jpg",
				"商品名称", "描述", "", "", "", "", "价格：", "1988", "下单时间：",
				"2015-2-11", "2"));
		list.add(new Lucky_RecordBean(
				"http://v1.qzone.cc/pic/201510/31/11/17/563432b7977c7764.jpeg%21600x600.jpg",
				"商品名称", "描述", "起拍价：", "1", "成交价：", "¥1234", "抢夺人数：", "100",
				"开拍时间：", "2015-2-11", "3"));
		}
		adapter = new Lucky_RecordAdapter(list, getApplicationContext(),imageLoader);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}
}
