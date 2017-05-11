package com.quwu.xinwo.home_page;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.QCAdapter;
import com.quwu.xinwo.bean.QCBean;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class QCActivity extends SwipeBackActivity implements OnClickListener {
	private PullToRefreshListView listView;
	private List<String> urlsList;
	private List<QCBean> qcList;
	private QCAdapter adapter;

	/**
	 * 
	 * 众筹
	 * 
	 * */
	private LinearLayout official_qc_crowdfundingLin;
	private TextView official_qc_crowdfundingText;
	private ImageView official_qc_crowdfundingImage;
	/**
	 * 
	 * 全价
	 * 
	 * */
	private LinearLayout official_qc_allLin;
	private TextView official_qc_allText;
	private ImageView official_qc_allImage;
	/**
	 * 
	 * 出租
	 * 
	 * */
	private LinearLayout official_qc_rent_outLin;
	private TextView official_qc_rent_outText;
	private ImageView official_qc_rent_outImage;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private LinearLayout official_qc_auctionLin;
	private TextView official_qc_auctionText;
	private ImageView official_qc_auctionImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.official_qc);
		FinishActivity.finish(R.id.official_qc_returnRel, QCActivity.this);
		findID();
		isListView(0);
	}

	private void findID() {
		listView=(PullToRefreshListView) findViewById(R.id.official_qc_listview); 
		
		official_qc_crowdfundingLin = (LinearLayout) findViewById(R.id.official_qc_crowdfundingLin);
		official_qc_allLin = (LinearLayout) findViewById(R.id.official_qc_allLin);
		official_qc_rent_outLin = (LinearLayout) findViewById(R.id.official_qc_rent_outLin);
		official_qc_auctionLin = (LinearLayout) findViewById(R.id.official_qc_auctionLin);

		official_qc_crowdfundingLin.setOnClickListener(this);
		official_qc_allLin.setOnClickListener(this);
		official_qc_rent_outLin.setOnClickListener(this);
		official_qc_auctionLin.setOnClickListener(this);

		official_qc_crowdfundingText = (TextView) findViewById(R.id.official_qc_crowdfundingText);
		official_qc_allText = (TextView) findViewById(R.id.official_qc_allText);
		official_qc_rent_outText = (TextView) findViewById(R.id.official_qc_rent_outText);
		official_qc_auctionText = (TextView) findViewById(R.id.official_qc_auctionText);

		official_qc_crowdfundingImage = (ImageView) findViewById(R.id.official_qc_crowdfundingImage);
		official_qc_allImage = (ImageView) findViewById(R.id.official_qc_allImage);
		official_qc_rent_outImage = (ImageView) findViewById(R.id.official_qc_rent_outImage);
		official_qc_auctionImage = (ImageView) findViewById(R.id.official_qc_auctionImage);
	}

	public void onClick(View v) {
		init();
		switch (v.getId()) {
		case R.id.official_qc_crowdfundingLin:
			official_qc_crowdfundingText.setTextColor(this.getResources().getColor(R.color.淡红色));
			official_qc_crowdfundingImage.setVisibility(View.VISIBLE);
			isListView(0);
			break;
		case R.id.official_qc_allLin:
			official_qc_allText.setTextColor(this.getResources().getColor(R.color.淡红色));
			official_qc_allImage.setVisibility(View.VISIBLE);
			isListView(1);
			break;
		case R.id.official_qc_rent_outLin:
			official_qc_rent_outText.setTextColor(this.getResources().getColor(R.color.淡红色));
			official_qc_rent_outImage.setVisibility(View.VISIBLE);
			isListView(2);
			break;
		case R.id.official_qc_auctionLin:
			official_qc_auctionText.setTextColor(this.getResources().getColor(R.color.淡红色));
			official_qc_auctionImage.setVisibility(View.VISIBLE);
			isListView(3);
			break;

		default:
			break;
		}
	}

	private void init() {
		official_qc_crowdfundingText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		official_qc_allText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		official_qc_rent_outText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		official_qc_auctionText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		official_qc_crowdfundingImage.setVisibility(View.INVISIBLE);
		official_qc_allImage.setVisibility(View.INVISIBLE);
		official_qc_rent_outImage.setVisibility(View.INVISIBLE);
		official_qc_auctionImage.setVisibility(View.INVISIBLE);
	}
	private void isListView(int state){
		if (state==0) {
			urlsList=new ArrayList<String>();
			for (int i = 0; i < 6; i++) {
				urlsList.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
			}
			qcList=new ArrayList<QCBean>();
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "时间地区", "我要抢", "258", "35"));
		
		}else if(state==1){
			urlsList=new ArrayList<String>();
			for (int i = 0; i < 2; i++) {
				urlsList.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
			}
			qcList=new ArrayList<QCBean>();
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "时间地区", "我要抢", "2582"));
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "时间地区", "我要抢", "2582"));
		}else if (state==2) {
			urlsList=new ArrayList<String>();
			for (int i = 0; i < 3; i++) {
				urlsList.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
			}
			qcList=new ArrayList<QCBean>();
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "时间地区", "我要抢", "258", "35"));
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "时间地区", "我要抢", "258", "210"));
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "时间地区", "我要抢", "258", "100"));
		}else if (state==3) {
			urlsList=new ArrayList<String>();
			for (int i = 0; i < 4; i++) {
				urlsList.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
			}
			qcList=new ArrayList<QCBean>();
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "1个小时前\t\t重庆 渝北区", "我要抢", "258", "35"));
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "1个小时前\t\t重庆 渝北区", "我要抢", "258", "210"));
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "1个小时前\t\t重庆 渝北区", "我要抢", "258", "100"));
			qcList.add(new QCBean(urlsList, "商品名称", "商品描述", "1个小时前\t\t重庆 渝北区", "我要抢", "258", "80"));
		}
		adapter=new QCAdapter(qcList, state, QCActivity.this, QCActivity.this);
		listView.setAdapter(adapter);
	}
}
