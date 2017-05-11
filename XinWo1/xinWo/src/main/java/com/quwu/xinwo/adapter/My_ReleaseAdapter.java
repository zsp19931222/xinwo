package com.quwu.xinwo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_ReleaseBean;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.TextProgressBar;
import com.quwu.xinwo.popupwindow.My_Release_Pop;

public class My_ReleaseAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<My_ReleaseBean> list;
	private Activity activity;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private int state;
	private ViewHolder holder = null;
	private ImageLoader imageLoader;

	public My_ReleaseAdapter(Context context, List<My_ReleaseBean> list,
			Activity activity, int state) {
		super();
		this.context = context;
		this.list = list;
		this.activity = activity;
		this.state = state;
imageLoader=ImageLoader.getInstance();
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {

		return list.size();
	}

	public Object getItem(int position) {

		return null;
	}

	public long getItemId(int position) {

		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.my_release_item, null);
			holder.browse = (TextView) convertView
					.findViewById(R.id.mr_item_browseText);
			holder.describe = (TextView) convertView
					.findViewById(R.id.mr_item_describeText);
			holder.name = (TextView) convertView
					.findViewById(R.id.mr_item_nameText);
			holder.like = (TextView) convertView
					.findViewById(R.id.mr_item_likeText);
			holder.gridView = (MyGridView) convertView
					.findViewById(R.id.mr_item_gridview);
			holder.moreText = (TextView) convertView
					.findViewById(R.id.mr_item_moreText);
			holder.moreText.setOnClickListener(holder);

			holder.allLin = (LinearLayout) convertView
					.findViewById(R.id.my_release_allLin);
			holder.price = (TextView) convertView
					.findViewById(R.id.mr_item_priceText);

			holder.crowdfundingLin = (LinearLayout) convertView
					.findViewById(R.id.my_release_item_crowdfundingLin);
			holder.bar = (TextProgressBar) convertView
					.findViewById(R.id.my_release_item_crowdfundingBar);
			holder.crowdfundingPriceText = (TextView) convertView
					.findViewById(R.id.my_release_item_crowdfundingPriceText);
			holder.crowdfunding_residueText = (TextView) convertView
					.findViewById(R.id.my_release_item_crowdfunding_residueText);

			holder.rent_outLin = (LinearLayout) convertView
					.findViewById(R.id.my_release_item_rent_outLin);
			holder.rent_out_priceText = (TextView) convertView
					.findViewById(R.id.my_release_item_rent_out_priceText);
			holder.rent_out_tenancy_termText = (TextView) convertView
					.findViewById(R.id.my_release_item_rent_out_tenancy_termText);

			holder.auctionLin = (LinearLayout) convertView
					.findViewById(R.id.my_release_item_auctionLin);
			holder.auction_nowPriceText = (TextView) convertView
					.findViewById(R.id.my_release_item_auction_nowPriceText);
			holder.auction_participationText = (TextView) convertView
					.findViewById(R.id.my_release_item_auction_participationText);
			holder.my_release_item_auction_delayText = (TextView) convertView
					.findViewById(R.id.my_release_item_auction_delayText);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.setPosition(position);
		holder.name.setText(list.get(position).getName());
		holder.describe.setText(list.get(position).getDescribe());
		holder.browse.setText(list.get(position).getBrowse()+"浏览");
		holder.like.setText(list.get(position).getLike()+"喜欢");
//		holder.moreText.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				Intent intent = new Intent(activity, My_Release_Pop.class);
//				activity.startActivity(intent);
//			}
//		});

		holder.gridView.setFocusable(false);
		isGridView(list.get(position).getUrlList(), holder.gridView);

		if (state == 1) {// 全价
			holder.allLin.setVisibility(View.VISIBLE);
			holder.price.setText(list.get(position).getPrice());
		} else if (state == 2) {// 众筹
			holder.crowdfundingLin.setVisibility(View.VISIBLE);
			holder.bar.setMax(Integer.valueOf(list.get(position).getPrice()));
			holder.bar.setProgress(Integer.valueOf(list.get(position)
					.getPrice())
					- Integer.valueOf(list.get(position).getResidue()));
			holder.crowdfundingPriceText.setText(list.get(position).getPrice());
			holder.crowdfunding_residueText.setText("剩余："
					+ list.get(position).getResidue());
		} else if (state == 3) {// 出租
			holder.rent_outLin.setVisibility(View.VISIBLE);
			holder.rent_out_priceText.setText(list.get(position).getPrice());
			holder.rent_out_tenancy_termText.setText(list.get(position)
					.getTenancy_term());
		} else if (state == 4) {// 拍卖
			holder.auctionLin.setVisibility(View.VISIBLE);
			holder.auction_nowPriceText.setText(list.get(position).getPrice());
			holder.auction_participationText.setText(list.get(position)
					.getParticipation()+"人参与");
			holder.my_release_item_auction_delayText.setText(list.get(position)
					.getDelay());
		}
		return convertView;
	}

	private class ViewHolder implements OnClickListener{
		private int position;
		 public void setPosition(int position){
		        this.position = position;
		    }
		
		private TextView name, describe, browse, like;
		private MyGridView gridView;

		private LinearLayout allLin;
		private TextView price;

		private LinearLayout crowdfundingLin;
		private TextProgressBar bar;
		private TextView crowdfundingPriceText;
		private TextView crowdfunding_residueText;

		private LinearLayout rent_outLin;
		private TextView rent_out_priceText;
		private TextView rent_out_tenancy_termText;

		private LinearLayout auctionLin;
		private TextView auction_nowPriceText;
		private TextView auction_participationText;
		private TextView my_release_item_auction_delayText;

		private TextView moreText;

		@Override
		public void onClick(View v) {
			int vid=v.getId();
			if (vid==holder.moreText.getId()) {
				Log.e("", ""+position);
				Intent intent = new Intent(activity, My_Release_Pop.class);
				activity.startActivity(intent);
			}
		}
	}

	private void isGridView(List<String> url, MyGridView gridView) {
		gridviewlist = new ArrayList<My_Release_GridviewBean>();
		if (url.size()!=0) {
		for (int i = 0; i < url.size(); i++) {
			gridviewlist.add(new My_Release_GridviewBean(url.get(i)));
		}
		int size = gridviewlist.size();
		int length = 100;
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);

		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
		gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
		gridView.setColumnWidth(itemWidth); // 设置列表项宽
		gridView.setHorizontalSpacing(20); // 设置列表项水平间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 设置列数量=列表集合数
		adapter = new My_Release_GridviewAdapter(context, gridviewlist,imageLoader);
		gridView.setAdapter(adapter);
		gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}else {
		gridView.setVisibility(View.GONE);
	}
	}
}
