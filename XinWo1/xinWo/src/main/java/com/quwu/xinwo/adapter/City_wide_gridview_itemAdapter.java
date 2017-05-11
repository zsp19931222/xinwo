package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.City_wide_classify_popBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class City_wide_gridview_itemAdapter extends BaseAdapter{
	
	private List<City_wide_classify_popBean> list;
	private Context context;
	private LayoutInflater inflater;
	private int numItem=-1;

	public City_wide_gridview_itemAdapter(
			List<City_wide_classify_popBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
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
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.city_wide_gridview_item, null);
			holder.box=(CheckBox) convertView.findViewById(R.id.city_wide_gridview_item_box);
			holder.layout=(LinearLayout) convertView.findViewById(R.id.city_wide_gridview_item_Lin);
			convertView.setTag(holder);
		}else {
			holder= (ViewHolder) convertView.getTag();
		}
		holder.box.setText(list.get(position).getFirstlevel_name());
		if (numItem==-1) {
			if (position==0) {
				holder.box.setChecked(true);
			}else {
				holder.box.setChecked(false);
			}
		}else {
			if (numItem==position) {
				holder.box.setChecked(true);
			}else {
				holder.box.setChecked(false);
			}
		}
		return convertView;
	}
private class ViewHolder{
	private CheckBox box;
	private LinearLayout layout;
}
public void isSelect(int numItem){
	this.numItem=numItem;
}
}
