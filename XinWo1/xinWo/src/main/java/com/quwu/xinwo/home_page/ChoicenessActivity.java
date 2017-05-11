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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.ChoicenessAdapter;
import com.quwu.xinwo.bean.ChoicenessBean;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class ChoicenessActivity extends SwipeBackActivity implements OnClickListener{
	
	private PullToRefreshListView listView;
	private List<String> urlsList;
	private List<ChoicenessBean> list;
	private ChoicenessAdapter adapter;
	private ImageLoader imageLoader;

	/**
	 * 
	 * 众筹
	 * 
	 * */
	private LinearLayout choiceness_crowdfundingLin;
	private TextView choiceness_crowdfundingText;
	private ImageView choiceness_crowdfundingImage;
	/**
	 * 
	 * 全价
	 * 
	 * */
	private LinearLayout choiceness_allLin;
	private TextView choiceness_allText;
	private ImageView choiceness_allImage;
	/**
	 * 
	 * 出租
	 * 
	 * */
	private LinearLayout choiceness_rent_outLin;
	private TextView choiceness_rent_outText;
	private ImageView choiceness_rent_outImage;
	/**
	 * 
	 * 拍卖
	 * 
	 * */
	private LinearLayout choiceness_auctionLin;
	private TextView choiceness_auctionText;
	private ImageView choiceness_auctionImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageLoader=ImageLoader.getInstance();
		setContentView(R.layout.choiceness);
		FinishActivity.finish(R.id.choiceness_returnRel,
				ChoicenessActivity.this);
		findID();
		isListView();
	}
	private void findID() {
		listView=(PullToRefreshListView) findViewById(R.id.choiceness_listview); 
		
		choiceness_crowdfundingLin = (LinearLayout) findViewById(R.id.choiceness_crowdfundingLin);
		choiceness_allLin = (LinearLayout) findViewById(R.id.choiceness_allLin);
		choiceness_rent_outLin = (LinearLayout) findViewById(R.id.choiceness_rent_outLin);
		choiceness_auctionLin = (LinearLayout) findViewById(R.id.choiceness_auctionLin);

		choiceness_crowdfundingLin.setOnClickListener(this);
		choiceness_allLin.setOnClickListener(this);
		choiceness_rent_outLin.setOnClickListener(this);
		choiceness_auctionLin.setOnClickListener(this);

		choiceness_crowdfundingText = (TextView) findViewById(R.id.choiceness_crowdfundingText);
		choiceness_allText = (TextView) findViewById(R.id.choiceness_allText);
		choiceness_rent_outText = (TextView) findViewById(R.id.choiceness_rent_outText);
		choiceness_auctionText = (TextView) findViewById(R.id.choiceness_auctionText);

		choiceness_crowdfundingImage = (ImageView) findViewById(R.id.choiceness_crowdfundingImage);
		choiceness_allImage = (ImageView) findViewById(R.id.choiceness_allImage);
		choiceness_rent_outImage = (ImageView) findViewById(R.id.choiceness_rent_outImage);
		choiceness_auctionImage = (ImageView) findViewById(R.id.choiceness_auctionImage);
	}
	public void onClick(View v) {
		init();
		switch (v.getId()) {
		case R.id.choiceness_crowdfundingLin:
			choiceness_crowdfundingText.setTextColor(this.getResources().getColor(R.color.淡红色));
			choiceness_crowdfundingImage.setVisibility(View.VISIBLE);
			break;
		case R.id.choiceness_allLin:
			choiceness_allText.setTextColor(this.getResources().getColor(R.color.淡红色));
			choiceness_allImage.setVisibility(View.VISIBLE);
			break;
		case R.id.choiceness_rent_outLin:
			choiceness_rent_outText.setTextColor(this.getResources().getColor(R.color.淡红色));
			choiceness_rent_outImage.setVisibility(View.VISIBLE);
			break;
		case R.id.choiceness_auctionLin:
			choiceness_auctionText.setTextColor(this.getResources().getColor(R.color.淡红色));
			choiceness_auctionImage.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
	private void init() {
		choiceness_crowdfundingText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		choiceness_allText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		choiceness_rent_outText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		choiceness_auctionText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		choiceness_crowdfundingImage.setVisibility(View.INVISIBLE);
		choiceness_allImage.setVisibility(View.INVISIBLE);
		choiceness_rent_outImage.setVisibility(View.INVISIBLE);
		choiceness_auctionImage.setVisibility(View.INVISIBLE);
	}
	private void isListView() {
		urlsList=new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			urlsList.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
		}
		list=new ArrayList<ChoicenessBean>();
		for (int i = 0; i < 20; i++) {
			list.add(new ChoicenessBean(urlsList, "http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg", "用户名称", "时间地区", "256", "商品名称", "商品描述", "12", "6"));
		}
		adapter=new ChoicenessAdapter(list, ChoicenessActivity.this, ChoicenessActivity.this,imageLoader);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}
}
