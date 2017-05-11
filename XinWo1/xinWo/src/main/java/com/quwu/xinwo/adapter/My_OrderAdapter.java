package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_OrderBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;

public class My_OrderAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private LayoutInflater inflater;
	private List<My_OrderBean> list;
	private ImageLoader imageLoader;

	public My_OrderAdapter(Context context, List<My_OrderBean> list,
			ImageLoader imageLoader) {
		super();
		this.context = context;
		this.list = list;
		this.imageLoader = imageLoader;

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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.my_order_item, null);

			holder.startPriceLin = (LinearLayout) convertView
					.findViewById(R.id.my_order_item_startPriceLin);
			holder.tenancyLin = (LinearLayout) convertView
					.findViewById(R.id.my_order_item_tenancyLin);

			holder.imageView = (ImageView) convertView
					.findViewById(R.id.my_order_item_iamge);

			holder.name = (TextView) convertView
					.findViewById(R.id.my_order_item_nameText);
			holder.decribe = (TextView) convertView
					.findViewById(R.id.my_order_item_decribeText);
			holder.startprice = (TextView) convertView
					.findViewById(R.id.my_order_item_startPriceText);
			holder.price = (TextView) convertView
					.findViewById(R.id.my_order_item_priceText);
			holder.tenancy = (TextView) convertView
					.findViewById(R.id.my_order_item_tenancyText);
			holder.time = (TextView) convertView
					.findViewById(R.id.my_order_item_timeText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getUrl(), holder.imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holder.name.setText(list.get(position).getName());
		holder.decribe.setText(list.get(position).getDecribe());
		holder.startprice.setText(list.get(position).getStartprice());
		holder.price.setText(list.get(position).getPrice());
		holder.tenancy.setText(list.get(position).getTenancy());
		holder.time.setText(list.get(position).getTime());
		Log.e("1", list.get(position).getTenancy());
		if (list.get(position).getStartprice().equals("")) {
			holder.startPriceLin.setVisibility(View.GONE);
		} 
		if (list.get(position).getTenancy().equals("")) {
			holder.tenancyLin.setVisibility(View.GONE);
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name, decribe, startprice, price, tenancy, time;
		private LinearLayout startPriceLin, tenancyLin;
	}
}
