package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.City_WideBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class City_WideAdapter extends BaseAdapter{

	private List<City_WideBean> list;
	private Context context;
	private LayoutInflater inflater;
	
	public City_WideAdapter(List<City_WideBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater=LayoutInflater.from(context);
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
		
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.city_wide_item, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.city_wide_item_image);
			holder.nameText=(TextView) convertView.findViewById(R.id.city_wide_item_nameText);
			holder.describeText=(TextView) convertView.findViewById(R.id.city_wide_item_describeText);
			holder.residueText=(TextView) convertView.findViewById(R.id.city_wide_item_residueText);
			holder.likeText=(TextView) convertView.findViewById(R.id.city_wide_item_likeText);
			holder.arear_timeText=(Button) convertView.findViewById(R.id.city_wide_item_area_timeBtn);
			holder.totalBtn=(Button) convertView.findViewById(R.id.city_wide_item_totalBtn);
			holder.bar=(ProgressBar) convertView.findViewById(R.id.city_wide_item_Bar);
			
			holder.crowd_fundingLin=(LinearLayout) convertView.findViewById(R.id.city_wide_item_crowd_fundingLin);
			holder.overhead_priceText=(TextView) convertView.findViewById(R.id.city_wide_item_overhead_priceText);
			holder.rentText=(TextView) convertView.findViewById(R.id.city_wide_item_rentText);
			holder.auctionText=(TextView) convertView.findViewById(R.id.city_wide_item_auctionText);
			holder.freeText=(TextView) convertView.findViewById(R.id.city_wide_item_freeText);
			
			convertView.setTag(holder);
		}else {
		holder=	(ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader3(list.get(position).getImageurl(), holder.imageView, context);
		
		holder.nameText.setText(list.get(position).getName());
		holder.describeText.setText(list.get(position).getDescribe());
		holder.residueText.setText("剩余："+list.get(position).getResidue()+"人次");
		holder.likeText.setText(list.get(position).getLike());
		holder.arear_timeText.setText(list.get(position).getArea_time());
		holder.totalBtn.setText("总需："+list.get(position).getTotal());
		
		holder.bar.setMax(Integer.valueOf(list.get(position).getTotal()));
		holder.bar.setProgress(Integer.valueOf(list.get(position).getTotal())-Integer.valueOf(list.get(position).getResidue()));
		return convertView;
	}
private class ViewHolder{
	private ImageView imageView;
	private TextView nameText;
	private TextView describeText;
	private Button arear_timeText;
	private Button totalBtn;
	private TextView residueText;
	private TextView likeText;
	private ProgressBar bar;
	
	private LinearLayout crowd_fundingLin;
	private TextView overhead_priceText;
	private TextView rentText;
	private TextView auctionText;
	private TextView freeText;
}
}
