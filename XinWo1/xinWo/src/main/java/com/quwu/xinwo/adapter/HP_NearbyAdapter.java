package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.HP_NearbyBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.until.SetW_H;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HP_NearbyAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<HP_NearbyBean> list;

	public HP_NearbyAdapter(Context context, List<HP_NearbyBean> list) {
		super();
		this.context = context;
		this.list = list;

		inflater = LayoutInflater.from(context);

	}

	public int getCount() {
if (list!=null) {
	return list.size();
}
return 0;
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
			convertView = inflater.inflate(R.layout.nearby_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.nearby_item_Image);
			holder.textView = (TextView) convertView
					.findViewById(R.id.nearby_item_typeText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getImageUrl(),
				holder.imageView, context);
		holder.textView.setText(list.get(position).getType());
		SetW_H.setLinearLayout(context, holder.imageView, 0.08);
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView textView;
	}
}
