package com.quwu.xinwo.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.DF2_ListViewBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DF2_ListViewAdapter extends BaseAdapter{

	private Context context;
	private List<DF2_ListViewBean> list;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	
	public DF2_ListViewAdapter(Context context, List<DF2_ListViewBean> list,ImageLoader imageLoader) {
		super();
		this.context = context;
		this.list = list;
		this.imageLoader=imageLoader;
		
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
		ViewHoler holer;
		if (convertView==null) {
			holer=new ViewHoler();
			convertView=inflater.inflate(R.layout.df2_listview_item, null);
			holer.imageView=(ImageView) convertView.findViewById(R.id.df2_item_iamge);
			holer.name=(TextView) convertView.findViewById(R.id.df2_item_nameText);
			holer.describe=(TextView) convertView.findViewById(R.id.df2_item_describeText);
			holer.price=(TextView) convertView.findViewById(R.id.df2_item_priceText);
			holer.participation=(TextView) convertView.findViewById(R.id.df2_item_participationText);
			convertView.setTag(holer);
		}else {
			holer=(ViewHoler) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getImageUrl(),  holer.imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holer.name.setText(list.get(position).getName());
		holer.describe.setText(list.get(position).getDescribe());
		holer.price.setText(list.get(position).getPrice());
		holer.participation.setText(list.get(position).getParticipation());
		return convertView;
	}
private class ViewHoler{
	private ImageView imageView;
	private TextView name, describe,price,participation;
}
}
