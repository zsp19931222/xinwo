package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Recommend_Fragment2Bean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

public class Recommend_Rent_OutAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<Recommend_Fragment2Bean> list;

	public Recommend_Rent_OutAdapter(Context context,
			List<Recommend_Fragment2Bean> list) {
		super();
		this.context = context;
		this.list = list;
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.recommend_rent_out_item,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.recommend_rent_out_item_iamge);
			holder.name = (TextView) convertView
					.findViewById(R.id.recommend_rent_out_item_nameText);
			holder.price = (TextView) convertView
					.findViewById(R.id.recommend_rent_out_item_priceText);
			holder.rent = (TextView) convertView
					.findViewById(R.id.recommend_rent_out_item_rentText);
			holder.tenancy_term = (TextView) convertView
					.findViewById(R.id.recommend_rent_out_item_tenancy_termText);
			holder.btnText = (TextView) convertView
					.findViewById(R.id.recommend_rent_out_item_btnText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(),
				holder.imageView, context);
		holder.name.setText(list.get(position).getName());
		holder.price.setText(list.get(position).getPrice());
		holder.rent.setText(list.get(position).getRent());
		holder.tenancy_term.setText(list.get(position).getTenancy_term());
		holder.btnText.setText(list.get(position).getBtnText());
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name, price, rent, tenancy_term, btnText;
	}
}
