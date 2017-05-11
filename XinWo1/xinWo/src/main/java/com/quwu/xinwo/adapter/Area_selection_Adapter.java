package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Area_selection_Bean;

public class Area_selection_Adapter extends BaseAdapter{

	private List<Area_selection_Bean> list;
	private Context context;
	private LayoutInflater inflater;
	
	public Area_selection_Adapter(List<Area_selection_Bean> list,
			Context contact) {
		super();
		this.list = list;
		this.context = contact;
		
		inflater=LayoutInflater.from(context);
	}

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
		ViewHoleder holeder=null;
		if (convertView==null) {
			holeder=new ViewHoleder();
			convertView=inflater.inflate(R.layout.area_selection_item, null);
			holeder.textView=(TextView) convertView.findViewById(R.id.area_selection_item_text);
			convertView.setTag(holeder);
		}else {
			holeder=(ViewHoleder) convertView.getTag();
		}
		holeder.textView.setText(list.get(position).getTwolevel_name());
		return convertView;
	}
private class ViewHoleder{
	private TextView textView;
}
}
