package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Personal_CenterAdapter;
import com.quwu.xinwo.bean.Personal_CenterBean;
import com.quwu.xinwo.mywight.MarqueeText;
import com.quwu.xinwo.mywight.MyListView1;
import com.quwu.xinwo.pull.MyListener;
import com.quwu.xinwo.pull.PullToRefreshLayout;
import com.quwu.xinwo.pull.PullableScrollView;
import com.quwu.xinwo.pull.PullableScrollView.OnScrollListener;
import com.quwu.xinwo.pull.ScrollViewListener;
import com.quwu.xinwo.release.Release_Activity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

public class Personal_CenterActvity extends Activity implements
		OnClickListener, OnScrollListener, ScrollViewListener {

	private List<Personal_CenterBean> list;
	private List<String> Imageurls;
	private Personal_CenterAdapter adapter;
	private MyListView1 listView;

	private LinearLayout layout1, layout2;
	private PullToRefreshLayout parent;
	private PullableScrollView scrollView;

	private RelativeLayout personal_center_changeRel;
	private RelativeLayout personal_center_saleRel;

	private MarqueeText marqueeText;
	private int i = 0;

	/**
	 * 
	 * 众筹
	 * 
	 * */
	private LinearLayout personal_center_crowdfundingLin;
	private TextView personal_center_crowdfundingText;
	private ImageView personal_center_crowdfundingImage;
	private LinearLayout personal_center_crowdfundingLin1;
	private TextView personal_center_crowdfundingText1;
	private ImageView personal_center_crowdfundingImage1;
	/**
	 * 
	 * 全价
	 * 
	 * */
	private LinearLayout personal_center_allLin;
	private TextView personal_center_allText;
	private ImageView personal_center_allImage;
	private LinearLayout personal_center_allLin1;
	private TextView personal_center_allText1;
	private ImageView personal_center_allImage1;
	/**
	 * 
	 * 出租
	 * 
	 * */
	private LinearLayout personal_center_rent_outLin;
	private TextView personal_center_rent_outText;
	private ImageView personal_center_rent_outImage;
	private LinearLayout personal_center_rent_outLin1;
	private TextView personal_center_rent_outText1;
	private ImageView personal_center_rent_outImage1;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private LinearLayout personal_center_auctionLin;
	private TextView personal_center_auctionText;
	private ImageView personal_center_auctionImage;
	private LinearLayout personal_center_auctionLin1;
	private TextView personal_center_auctionText1;
	private ImageView personal_center_auctionImage1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center);
		FinishActivity.finish(R.id.personal_center_returnRel, this);
		findID();
		isListView(0);
		new StartTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		personal_center_changeRel = (RelativeLayout) findViewById(R.id.personal_center_changeRel);
		personal_center_changeRel.setOnClickListener(this);
		personal_center_saleRel = (RelativeLayout) findViewById(R.id.personal_center_saleRel);
		personal_center_saleRel.setOnClickListener(this);

		marqueeText = (MarqueeText) findViewById(R.id.personal_center_paomading);

		listView = (MyListView1) findViewById(R.id.personal_center_listview);

		layout2 = (LinearLayout) findViewById(R.id.personal_center_layout1);
		layout1 = (LinearLayout) findViewById(R.id.personal_center_layout2);
		parent = (PullToRefreshLayout) findViewById(R.id.personal_center_parent);
		scrollView = (PullableScrollView) findViewById(R.id.personal_center_scrollView);
		parent.setOnRefreshListener(new MyListener());
		parent.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					public void onGlobalLayout() {
						onScroll(scrollView.getScrollY());
					}
				});
		View view = findViewById(R.id.personal_center_layout2);
		View view1 = findViewById(R.id.personal_center_layout1);

		personal_center_crowdfundingLin = (LinearLayout) view
				.findViewById(R.id.personal_center_crowdfundingLin);
		personal_center_allLin = (LinearLayout) view
				.findViewById(R.id.personal_center_allLin);
		personal_center_rent_outLin = (LinearLayout) view
				.findViewById(R.id.personal_center_rent_outLin);
		personal_center_auctionLin = (LinearLayout) view
				.findViewById(R.id.personal_center_auctionLin);

		personal_center_crowdfundingLin1 = (LinearLayout) view1
				.findViewById(R.id.personal_center_crowdfundingLin);
		personal_center_allLin1 = (LinearLayout) view1
				.findViewById(R.id.personal_center_allLin);
		personal_center_rent_outLin1 = (LinearLayout) view1
				.findViewById(R.id.personal_center_rent_outLin);
		personal_center_auctionLin1 = (LinearLayout) view1
				.findViewById(R.id.personal_center_auctionLin);

		personal_center_crowdfundingLin.setOnClickListener(this);
		personal_center_allLin.setOnClickListener(this);
		personal_center_rent_outLin.setOnClickListener(this);
		personal_center_auctionLin.setOnClickListener(this);

		personal_center_crowdfundingLin1.setOnClickListener(this);
		personal_center_allLin1.setOnClickListener(this);
		personal_center_rent_outLin1.setOnClickListener(this);
		personal_center_auctionLin1.setOnClickListener(this);

		personal_center_crowdfundingText = (TextView) view
				.findViewById(R.id.personal_center_crowdfundingText);
		personal_center_allText = (TextView) view
				.findViewById(R.id.personal_center_allText);
		personal_center_rent_outText = (TextView) view
				.findViewById(R.id.personal_center_rent_outText);
		personal_center_auctionText = (TextView) view
				.findViewById(R.id.personal_center_auctionText);

		personal_center_crowdfundingText1 = (TextView) view1
				.findViewById(R.id.personal_center_crowdfundingText);
		personal_center_allText1 = (TextView) view1
				.findViewById(R.id.personal_center_allText);
		personal_center_rent_outText1 = (TextView) view1
				.findViewById(R.id.personal_center_rent_outText);
		personal_center_auctionText1 = (TextView) view1
				.findViewById(R.id.personal_center_auctionText);

		personal_center_crowdfundingImage = (ImageView) view
				.findViewById(R.id.personal_center_crowdfundingImage);
		personal_center_allImage = (ImageView) view
				.findViewById(R.id.personal_center_allImage);
		personal_center_rent_outImage = (ImageView) view
				.findViewById(R.id.personal_center_rent_outImage);
		personal_center_auctionImage = (ImageView) view
				.findViewById(R.id.personal_center_auctionImage);

		personal_center_crowdfundingImage1 = (ImageView) view1
				.findViewById(R.id.personal_center_crowdfundingImage);
		personal_center_allImage1 = (ImageView) view1
				.findViewById(R.id.personal_center_allImage);
		personal_center_rent_outImage1 = (ImageView) view1
				.findViewById(R.id.personal_center_rent_outImage);
		personal_center_auctionImage1 = (ImageView) view1
				.findViewById(R.id.personal_center_auctionImage);
	}

	private class StartTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			marqueeText.setVisibility(View.VISIBLE);
			while (i < 20) {
				try {
					Thread.sleep(1000);
					Message message = new Message();
					message.what = 2;
					handler.sendMessage(message);
					i++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			init();
			switch (v.getId()) {
			case R.id.personal_center_crowdfundingLin:
				personal_center_crowdfundingText.setTextColor(this
						.getResources().getColor(R.color.淡红色));
				personal_center_crowdfundingImage.setVisibility(View.VISIBLE);
				personal_center_crowdfundingText1.setTextColor(this
						.getResources().getColor(R.color.淡红色));
				personal_center_crowdfundingImage1.setVisibility(View.VISIBLE);
				isListView(1);
				break;
			case R.id.personal_center_allLin:
				personal_center_allText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				personal_center_allImage.setVisibility(View.VISIBLE);
				personal_center_allText1.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				personal_center_allImage1.setVisibility(View.VISIBLE);
				isListView(0);
				break;
			case R.id.personal_center_rent_outLin:
				personal_center_rent_outText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				personal_center_rent_outImage.setVisibility(View.VISIBLE);
				personal_center_rent_outText1.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				personal_center_rent_outImage1.setVisibility(View.VISIBLE);
				isListView(2);
				break;
			case R.id.personal_center_auctionLin:
				personal_center_auctionText.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				personal_center_auctionImage.setVisibility(View.VISIBLE);
				personal_center_auctionText1.setTextColor(this.getResources()
						.getColor(R.color.淡红色));
				personal_center_auctionImage1.setVisibility(View.VISIBLE);
				isListView(3);
				break;
			case R.id.personal_center_changeRel:
				intent = new Intent(Personal_CenterActvity.this,
						Personal_DataActivity.class);
				break;
			case R.id.personal_center_saleRel:
				intent = new Intent(Personal_CenterActvity.this,
						Release_Activity.class);
				break;

			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	private void init() {
		personal_center_crowdfundingText.setTextColor(this.getResources()
				.getColor(R.color.黑字体色));
		personal_center_allText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		personal_center_rent_outText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		personal_center_auctionText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		personal_center_crowdfundingImage.setVisibility(View.INVISIBLE);
		personal_center_allImage.setVisibility(View.INVISIBLE);
		personal_center_rent_outImage.setVisibility(View.INVISIBLE);
		personal_center_auctionImage.setVisibility(View.INVISIBLE);

		personal_center_crowdfundingText1.setTextColor(this.getResources()
				.getColor(R.color.黑字体色));
		personal_center_allText1.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		personal_center_rent_outText1.setTextColor(this.getResources()
				.getColor(R.color.黑字体色));
		personal_center_auctionText1.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		personal_center_crowdfundingImage1.setVisibility(View.INVISIBLE);
		personal_center_allImage1.setVisibility(View.INVISIBLE);
		personal_center_rent_outImage1.setVisibility(View.INVISIBLE);
		personal_center_auctionImage1.setVisibility(View.INVISIBLE);
	}

	private void isListView(int state) {
		Imageurls = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			Imageurls
					.add("http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg");
		}
		if (state == 0) {
			list = new ArrayList<Personal_CenterBean>();
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述",
					"http://picm.photophoto.cn/082/050/005/0500050031.jpg",
					"获奖者名称", "本次参与：22人次", "102352654",
					"揭晓时间：2015-10-08  10:28", "2700", "", 0));
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述",
					"http://picm.photophoto.cn/082/050/005/0500050031.jpg",
					"获奖者名称", "本次参与：22人次", "102352654",
					"揭晓时间：2015-10-08  10:28", "2700", "3566", 1));
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述",
					"http://picm.photophoto.cn/082/050/005/0500050031.jpg",
					"获奖者名称", "本次参与：22人次", "102352654",
					"揭晓时间：2015-10-08  10:28", "2700", "", 0));
		} else if (state == 1) {
			list = new ArrayList<Personal_CenterBean>();
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述", "2700", "12"));
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述", "2700", "12"));
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述", "2700", "12"));
		} else if (state == 2) {
			list = new ArrayList<Personal_CenterBean>();
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述"));
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述"));
		} else if (state == 3) {
			list = new ArrayList<Personal_CenterBean>();
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述", "2700", "",
					"15", "12", 0));
			list.add(new Personal_CenterBean(
					Imageurls,
					"http://img5.imgtn.bdimg.com/it/u=1889250223,3195298994&fm=21&gp=0.jpg",
					"用户名", "1个小时前\t\t重庆  渝北区", "商品名称", "商品描述", "", "3621", "",
					"", 1));
		}
		adapter = new Personal_CenterAdapter(list, Personal_CenterActvity.this,
				state);
		if (adapter != null) {
			listView.setAdapter(adapter);
		}
	}

	/**
	 * 监听滑动高度
	 * */
	public void onScroll(int scrollY) {
		int mBuyLayout2ParentTop = Math.max(scrollY, layout2.getTop());
		layout1.layout(0, mBuyLayout2ParentTop, layout1.getWidth(),
				mBuyLayout2ParentTop + layout1.getHeight());
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

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				if (i == 19) {
					marqueeText.setVisibility(View.GONE);
				}
				break;

			default:
				break;
			}
		};
	};
}
