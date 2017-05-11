package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quwu.xinwo.R;

public class Search_GridViewAdapter extends BaseAdapter{

	private List<String> list;
	private Context context;
	private LayoutInflater inflater;

	public Search_GridViewAdapter(List<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
	
		return list.size();
	}

	@Override
	public Object getItem(int position) {
	
		return null;
	}

	@Override
	public long getItemId(int position) {
	
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder=null;
	if (convertView==null) {
		holder=new ViewHolder();
		convertView=inflater.inflate(R.layout.search_gridview_item, null);
		holder.flowLayout=(TextView) convertView.findViewById(R.id.search_gridview_item_flowlayout);
		convertView.setTag(holder);
	}else {
		holder=(ViewHolder) convertView.getTag();
	}
	holder.flowLayout.setText(list.get(position));
		return convertView;
	}
	private class ViewHolder {
		private TextView flowLayout;
	}
}
