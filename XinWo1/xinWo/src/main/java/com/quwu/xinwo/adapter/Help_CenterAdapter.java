package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Help_CenterBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Help_CenterAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater inflater;
	private List<Help_CenterBean> list;
	
	public Help_CenterAdapter(Context context, List<Help_CenterBean> list) {
		super();
		this.context = context;
		this.list = list;
		inflater=LayoutInflater.from(context);
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
			convertView=inflater.inflate(R.layout.help_center_item, null);
			holder.textView=(TextView) convertView.findViewById(R.id.help_center_item_Text);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		holder.textView.setText(list.get(position).getCommonproblem_title());
		return convertView;
	}
private class ViewHolder{
	private TextView textView;
}
}
