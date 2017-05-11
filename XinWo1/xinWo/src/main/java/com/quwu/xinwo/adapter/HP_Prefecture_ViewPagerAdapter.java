package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.HP_Prefecture_ViewPagerBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HP_Prefecture_ViewPagerAdapter extends BaseAdapter {

	private List<HP_Prefecture_ViewPagerBean> list;
	private Context context;
	private LayoutInflater inflater;

	public HP_Prefecture_ViewPagerAdapter(
			List<HP_Prefecture_ViewPagerBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.prefecture_viewpager_item,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.prefecture_viewpager_item_imageIma);
			holder.name = (TextView) convertView
					.findViewById(R.id.prefecture_viewpager_item_nameText);
			holder.message = (TextView) convertView
					.findViewById(R.id.prefecture_viewpager_item_messageText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getName());
		holder.message.setText(list.get(position).getMessage());
		ImageloaderUtils.MyImageLoader2(list.get(position).getImage(),
				holder.imageView, context);
		return convertView;
	}

	private class ViewHolder {
		private TextView name, message;
		private ImageView imageView;
	}
}
