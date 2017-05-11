package com.quwu.xinwo.mine;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.fragment.Recommend_Fragment1;
import com.quwu.xinwo.fragment.Recommend_Fragment2;
import com.quwu.xinwo.until.FinishActivity;

public class RecommActivity extends Activity implements
		OnClickListener {

	private Recommend_Fragment1 fragment1;
	private Recommend_Fragment2 fragment2;

	private TextView recommend_allPriceText, recommend_crowdFundingText,
			recommend_auctionText, recommend_rent_outText;

	private ImageView recommend_allPriceImage, recommend_crowdFundingImage,
			recommend_auctionImage, recommend_rent_outImage;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommend);
		FinishActivity.finish(R.id.recommendRel, RecommActivity.this);
		findID();
		isFragment1();
	}

	private void findID() {
		recommend_allPriceText = (TextView) findViewById(R.id.recommend_allPriceText);
		recommend_crowdFundingText = (TextView) findViewById(R.id.recommend_crowdFundingText);
		recommend_auctionText = (TextView) findViewById(R.id.recommend_auctionText);
		recommend_rent_outText = (TextView) findViewById(R.id.recommend_rent_outText);

		recommend_allPriceText.setOnClickListener(this);
		recommend_crowdFundingText.setOnClickListener(this);
		recommend_auctionText.setOnClickListener(this);
		recommend_rent_outText.setOnClickListener(this);
		
		recommend_allPriceImage = (ImageView) findViewById(R.id.recommend_allPriceImage);
		recommend_crowdFundingImage = (ImageView) findViewById(R.id.recommend_crowdFundingImage);
		recommend_auctionImage = (ImageView) findViewById(R.id.recommend_auctionImage);
		recommend_rent_outImage = (ImageView) findViewById(R.id.recommend_rent_outImage);
	
	}

	public void onClick(View v) {
		isCheck();
		switch (v.getId()) {
		case R.id.recommend_allPriceText:// 全价
			recommend_allPriceText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			recommend_allPriceImage.setVisibility(View.VISIBLE);
			isFragment1();
			break;
		case R.id.recommend_crowdFundingText:// 众筹
			recommend_crowdFundingText.setTextColor(this.getResources()
					.getColor(R.color.淡红色));
			recommend_crowdFundingImage.setVisibility(View.VISIBLE);
			isFragment2(2);                                              
			break;
		case R.id.recommend_auctionText:// 拍卖
			recommend_auctionText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			recommend_auctionImage.setVisibility(View.VISIBLE);
			isFragment2(3); 
			break;
		case R.id.recommend_rent_outText:// 出租
			recommend_rent_outText.setTextColor(this.getResources().getColor(
					R.color.淡红色));
			recommend_rent_outImage.setVisibility(View.VISIBLE);
			isFragment2(4); 
			break;

		default:
			break;
		}
	}

	private void isCheck() {
		recommend_allPriceText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		recommend_crowdFundingText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		recommend_auctionText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));
		recommend_rent_outText.setTextColor(this.getResources().getColor(
				R.color.黑字体色));

		recommend_allPriceImage.setVisibility(View.INVISIBLE);
		recommend_crowdFundingImage.setVisibility(View.INVISIBLE);
		recommend_auctionImage.setVisibility(View.INVISIBLE);
		recommend_rent_outImage.setVisibility(View.INVISIBLE);
	}

	private void isFragment1() {
		fragment1 = new Recommend_Fragment1();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.recommend_frame, fragment1);
		transaction.commit();
	}
	private void isFragment2(int i) {
		fragment2 = new Recommend_Fragment2(i);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.recommend_frame, fragment2);
		transaction.commit();
	}
}
