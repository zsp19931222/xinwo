package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Search_ListviewAdapter extends BaseAdapter {

	private List<String> list;
	private Context context;
	private LayoutInflater inflater;

	public Search_ListviewAdapter(List<String> list, Context context) {
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
			convertView = inflater.inflate(R.layout.search_listview_item, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.search_listview_item_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(list.get(position));
		return convertView;
	}

	private class ViewHolder {
		private TextView textView;
	}
}
