package com.quwu.xinwo.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_SaleBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;
import com.quwu.xinwo.mine.Fill_In_InformationActivity;

public class My_SaleAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<My_SaleBean> list;
	private int state;
	private Activity activity;
	private ImageLoader imageLoader;

	public My_SaleAdapter(Context context, List<My_SaleBean> list,int state,Activity activity,ImageLoader imageLoader) {
		super();
		this.context = context;
		this.list = list;
		this.state=state;
		this.activity=activity;
		this.imageLoader=imageLoader;
		
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.my_sale_item, null);
			
			holder.name=(TextView) convertView.findViewById(R.id.ms_nameText);
			holder.price=(TextView) convertView.findViewById(R.id.ms_priceText);
			holder.buyer=(TextView) convertView.findViewById(R.id.ms_buyerText);
			holder.putawayTime=(TextView) convertView.findViewById(R.id.ms_putawayTimeText);
			holder.soldoutTime=(TextView) convertView.findViewById(R.id.ms_soldoutTimeText);
			holder.ms_deliver_goods_stateText=(TextView) convertView.findViewById(R.id.ms_deliver_goods_stateText);
			holder.ms_fill_in_informationText=(TextView) convertView.findViewById(R.id.ms_fill_in_informationText);
			
			holder.ms_allLin=(LinearLayout) convertView.findViewById(R.id.ms_allLin);
			holder.ms_crowdfundingLin=(LinearLayout) convertView.findViewById(R.id.ms_crowdfundingLin);
			holder.ms_rent_outLin=(LinearLayout) convertView.findViewById(R.id.ms_rent_outLin);
			holder.ms_auctionLin=(LinearLayout) convertView.findViewById(R.id.ms_auctionLin);
			
			holder.ms_crowdfundingText=(TextView) convertView.findViewById(R.id.ms_crowdfundingText);
			holder.ms_rent_out_rentText=(TextView) convertView.findViewById(R.id.ms_rent_out_rentText);
			holder.ms_rent_out_tenancy_termText=(TextView) convertView.findViewById(R.id.ms_rent_out_tenancy_termText);
			holder.ms_auction_priceText=(TextView) convertView.findViewById(R.id.ms_auction_priceText);
			holder.ms_auction_participationText=(TextView) convertView.findViewById(R.id.ms_auction_participationText);
			
			holder.imageView=(ImageView) convertView.findViewById(R.id.ms_image);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getUrl(),  holder.imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holder.name.setText(list.get(position).getName());
		holder.buyer.setText(list.get(position).getBuyer());
		holder.putawayTime.setText(list.get(position).getPutawayTime());
		holder.soldoutTime.setText(list.get(position).getSoldoutTime());
		if (list.get(position).getDeliver_goodsState()!=null) {
			if (list.get(position).getDeliver_goodsState().equals("1")) {
				holder.ms_deliver_goods_stateText.setText("买家已付款");
			}else if (list.get(position).getDeliver_goodsState().equals("2")) {
				holder.ms_deliver_goods_stateText.setText("卖家已付款");
			}else if (list.get(position).getDeliver_goodsState().equals("3")) {
				holder.ms_deliver_goods_stateText.setText("交易成功");
			}
		}
		if (state==1) {
			holder.ms_allLin.setVisibility(View.VISIBLE);
			holder.price.setText(list.get(position).getPrice());
		}else if (state==2) {
			holder.ms_crowdfundingLin.setVisibility(View.VISIBLE);
			holder.ms_crowdfundingText.setText(list.get(position).getPrice());
		}else if (state==3) {
			holder.ms_rent_outLin.setVisibility(View.VISIBLE);
			holder.ms_rent_out_rentText.setText(list.get(position).getRent());
			holder.ms_rent_out_tenancy_termText.setText(list.get(position).getTenancy_term());
		}else if (state==4) {
			holder.ms_auctionLin.setVisibility(View.VISIBLE);
			holder.ms_auction_priceText.setText(list.get(position).getPrice());
			holder.ms_auction_participationText.setText(list.get(position).getParticipation());
		}
		holder.ms_fill_in_informationText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(activity,Fill_In_InformationActivity.class);
				activity.startActivity(intent);
			}
		});
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name,  buyer, putawayTime, soldoutTime;
		
		private LinearLayout ms_allLin;
		private TextView price;
		
		private LinearLayout ms_crowdfundingLin;
		private TextView ms_crowdfundingText;
		
		private LinearLayout ms_rent_outLin;
		private TextView ms_rent_out_rentText;
		private TextView ms_rent_out_tenancy_termText;
		
		private LinearLayout ms_auctionLin;
		private TextView ms_auction_priceText;
		private TextView ms_auction_participationText;
		
		private TextView ms_deliver_goods_stateText;
		private TextView ms_fill_in_informationText;
	}

  
}
