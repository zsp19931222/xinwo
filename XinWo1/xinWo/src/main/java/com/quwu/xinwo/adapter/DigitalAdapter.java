package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.DigitalBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;

public class DigitalAdapter extends BaseAdapter {

	private Context context;
	private List<DigitalBean> list;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	
	public DigitalAdapter(Context context, List<DigitalBean> list,ImageLoader imageLoader) {
		super();
		this.context = context;
		this.list = list;
		this.imageLoader=imageLoader;
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
			convertView=inflater.inflate(R.layout.digital_item, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.digital_item_image);
			holder.goods_name=(TextView) convertView.findViewById(R.id.digital_item_goods_nameText);
			holder.price=(TextView) convertView.findViewById(R.id.digital_item_priceText2);
			holder.browse=(TextView) convertView.findViewById(R.id.digital_item_browseText);
		convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getUrl(),  holder.imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holder.goods_name.setText(list.get(position).getName());
		holder.price.setText(list.get(position).getPrice());
		holder.browse.setText(list.get(position).getBrowse()+"次浏览");
		return convertView;
	}
private class ViewHolder {
	private ImageView imageView;
	private TextView goods_name;
	private TextView price;
	private TextView browse;
	
}
}
