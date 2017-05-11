package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.gson_entity.Goods_Category_PopEntity;

public class Goods_Category_popAdapter extends BaseAdapter {

	private Context context;
	private List<Goods_Category_PopEntity> list;
	private LayoutInflater inflater;
	private ViewHolder holder = null;

	public Goods_Category_popAdapter(Context context,
			List<Goods_Category_PopEntity> list) {
		super();
		this.context = context;
		this.list = list;
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

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.city_wide_classify_pop_item, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.city_wide_classify_pop_item_Text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(list.get(position).getTwolevel_name());
		
		return convertView;
	}

	private class ViewHolder {
		private TextView textView;
	}

}
