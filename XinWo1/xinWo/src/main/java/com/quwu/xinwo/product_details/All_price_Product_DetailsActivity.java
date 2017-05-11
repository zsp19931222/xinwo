package com.quwu.xinwo.product_details;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Home_Page_Product_DetailsAdapter;
import com.quwu.xinwo.bean.Home_Page_Product_DetailsBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.mywight.MyViewPager;
import com.quwu.xinwo.popupwindow.Product_Detail_Pop;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

public class All_price_Product_DetailsActivity extends SwipeBackActivity implements OnClickListener{
	
	private MyListView listView;
	private List<Home_Page_Product_DetailsBean> list;
	private Home_Page_Product_DetailsAdapter adapter;
	private RelativeLayout viewpagerRelativeLayout;
	
	private LinearLayout moreRel;
	
	/**
	 * viewpager
	 * */
	private MyViewPager hp_pd_viewpager;
	private ImageView image1, image2, image3, image4, image5, image6, image7,
	image8, image9, image10,image11;
	private List<String> urlList;
	
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				image1.setVisibility(View.VISIBLE);
				break;
			case 2:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				break;
			case 3:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				break;
			case 4:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				break;
			case 5:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				break;
			case 6:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				break;
			case 7:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				break;
			case 8:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				break;
			case 9:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				image9.setVisibility(View.VISIBLE);
				break;
			case 10:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				image9.setVisibility(View.VISIBLE);
				image10.setVisibility(View.VISIBLE);
				break;
			case 11:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				image9.setVisibility(View.VISIBLE);
				image10.setVisibility(View.VISIBLE);
				image11.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
		};
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.used_product_details);
		FinishActivity.finish(R.id.hp_dp_returnRel, All_price_Product_DetailsActivity.this);
		findID();
		isListView();
		urlList=new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			urlList.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
		}
	isViewPager(urlList);
	}
	private void findID() {
		viewpagerRelativeLayout=(RelativeLayout) findViewById(R.id.hp_pd_ViewpagerRel);
		WindowManager wm = this.getWindowManager();
		 
	     @SuppressWarnings("deprecation")
		int width = wm.getDefaultDisplay().getWidth();
	     RelativeLayout.LayoutParams  rlp = new RelativeLayout.LayoutParams(width,width);
	     viewpagerRelativeLayout.setLayoutParams(rlp);
	   
	     hp_pd_viewpager=(MyViewPager) findViewById(R.id.hp_pd_viewpager);
	     image1=(ImageView) findViewById(R.id.home_page_product_details_point1);
	     image2=(ImageView) findViewById(R.id.home_page_product_details_point2);
	     image3=(ImageView) findViewById(R.id.home_page_product_details_point3);
	     image4=(ImageView) findViewById(R.id.home_page_product_details_point4);
	     image5=(ImageView) findViewById(R.id.home_page_product_details_point5);
	     image6=(ImageView) findViewById(R.id.home_page_product_details_point6);
	     image7=(ImageView) findViewById(R.id.home_page_product_details_point7);
	     image8=(ImageView) findViewById(R.id.home_page_product_details_point8);
	     image9=(ImageView) findViewById(R.id.home_page_product_details_point9);
	     image10=(ImageView) findViewById(R.id.home_page_product_details_point10);
	     image11=(ImageView) findViewById(R.id.home_page_product_details_point11);
	     
	     moreRel=(LinearLayout) findViewById(R.id.udd_moreRel);
	     moreRel.setOnClickListener(this);
	}
	private void isListView() {
		listView=(MyListView) findViewById(R.id.hp_pd_listview);
		list=new ArrayList<Home_Page_Product_DetailsBean>();
		list.add(new Home_Page_Product_DetailsBean("http://g.hiphotos.baidu.com/imgad/pic/item/f603918fa0ec08faffbf5de25eee3d6d55fbda3c.jpg", "用户名", "2", "时间"));
		list.add(new Home_Page_Product_DetailsBean("http://g.hiphotos.baidu.com/imgad/pic/item/f603918fa0ec08faffbf5de25eee3d6d55fbda3c.jpg", "用户名", "2", "时间"));
		list.add(new Home_Page_Product_DetailsBean("http://g.hiphotos.baidu.com/imgad/pic/item/f603918fa0ec08faffbf5de25eee3d6d55fbda3c.jpg", "用户名", "2", "时间"));
		list.add(new Home_Page_Product_DetailsBean("http://g.hiphotos.baidu.com/imgad/pic/item/f603918fa0ec08faffbf5de25eee3d6d55fbda3c.jpg", "用户名", "2", "时间"));
		list.add(new Home_Page_Product_DetailsBean("http://g.hiphotos.baidu.com/imgad/pic/item/f603918fa0ec08faffbf5de25eee3d6d55fbda3c.jpg", "用户名", "2", "时间"));
	adapter=new Home_Page_Product_DetailsAdapter(list, All_price_Product_DetailsActivity.this);
	listView.setAdapter(adapter);
	}
	private void isViewPager(final List<String> url) {

		Message message = new Message();// 根据图片张数来显示下标小圆（最多11张）
		message.what = url.size();
		handler.sendMessage(message);
		hp_pd_viewpager.setAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return url.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view1 = getLayoutInflater().inflate(
						R.layout.home_page_product_details_viewpager_bg, null);
				ImageView bg = (ImageView) view1
						.findViewById(R.id.home_page_product_details_viewpager_iamge);
				ImageloaderUtils.MyImageLoader2(url.get(position), bg,
						All_price_Product_DetailsActivity.this);
				container.addView(view1);
				return view1;
			}

		});
		hp_pd_viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Log.e("", arg0+"");
				ImageView[] imageViews = initializeIamgeBg();
				for (int j = 0; j < imageViews.length; j++) {
					imageViews[arg0]
							.setBackgroundResource(R.drawable.goods_details_point_yellow);
					if (arg0 != j) {
						imageViews[j].setBackgroundResource(R.drawable.goods_details_point_white);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
	/**
	 * 初始化banner标识圆
	 * */
	private ImageView[] initializeIamgeBg() {
		ImageView imageViews[] = { image1, image2, image3, image4, image5,
				image6, image7, image8, image9, image10,image11 };
		return imageViews;
	}
	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		}else {
			switch (v.getId()) {
			case R.id.udd_moreRel:
				Product_Detail_Pop pop=new Product_Detail_Pop(All_price_Product_DetailsActivity.this);
				pop.showPopupWindow(moreRel);
				break;

			default:
				break;
			}
		}
	}
}
