package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Home_Page_Product_DetailsBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Home_Page_Product_DetailsAdapter extends BaseAdapter{

	private List<Home_Page_Product_DetailsBean> list;
	private Context context;
	private LayoutInflater inflater;
	
	public Home_Page_Product_DetailsAdapter(
			List<Home_Page_Product_DetailsBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.home_page_product_details_item, null);
			holder.circleImageView=(CircleImageView) convertView.findViewById(R.id.hp_pd_item_CircleImageView);
			holder.user_name=(TextView) convertView.findViewById(R.id.hp_pd_item_userNameText);
			holder.participation_num=(TextView) convertView.findViewById(R.id.hp_pd_item_participationText2);
			holder.participation_time=(TextView) convertView.findViewById(R.id.hp_pd_item_participationText3);
		convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getImageurl(), holder.circleImageView, context);
		holder.user_name.setText(list.get(position).getUser_name());
		holder.participation_num.setText(list.get(position).getParticipation_num());
		holder.participation_time.setText("人次\t"+list.get(position).getParticipation_time());
		return convertView;
	}
private class ViewHolder{
	private CircleImageView circleImageView;
	private TextView user_name;
	private TextView participation_num;
	private TextView participation_time;
}
}
