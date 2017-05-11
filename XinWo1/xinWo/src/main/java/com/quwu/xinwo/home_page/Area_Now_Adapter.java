package com.quwu.xinwo.home_page;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quwu.xinwo.R;

public class Area_Now_Adapter extends BaseAdapter {
	List<Search_Bean> list;
	private Context context;
	private LayoutInflater inflater;
	private int click=-1;

	public Area_Now_Adapter(List<Search_Bean> list, Context context) {
		super();
		this.list = list;
		this.context = context;

		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.area_now_item, null);
			holder.name =  (TextView) view.findViewById(R.id.area_now_item_name);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.name.setText(list.get(arg0).getMessage());
		if (click == arg0) {
			holder.name.setTextColor(context.getResources().getColor(
					R.color.aree_ziti_selector));
			holder.name.setBackgroundResource(R.drawable.area_choose_select_bg);
		}else{
			holder.name.setTextColor(context.getResources().getColor(
					R.color.aree_ziti));
			holder.name.setBackgroundResource(R.drawable.area_choose_bg);
		}
		return view;
	}
	public void SetSeclection(int postion) {
		click=postion;
	}

private 	class ViewHolder {
		private TextView name;
	}
}
