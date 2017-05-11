package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Deal_RecordBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Deal_RecordAdapter extends BaseAdapter {

	private List<Deal_RecordBean> list;
	@SuppressWarnings("unused")
	private Context context;
	private LayoutInflater inflater;

	public Deal_RecordAdapter(List<Deal_RecordBean> list, Context context) {
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
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.deal_record_item, null);
			
			holder.time1=(TextView) convertView.findViewById(R.id.deal_record_item_time1);
			holder.time2=(TextView) convertView.findViewById(R.id.deal_record_item_time2);
			holder.message=(TextView) convertView.findViewById(R.id.deal_record_item_message);
			holder.money=(TextView) convertView.findViewById(R.id.deal_record_item_money);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		holder.time1.setText(list.get(position).getTime1());
		holder.time2.setText(list.get(position).getTime2());
		holder.message.setText(list.get(position).getMessage());
		holder.money.setText(list.get(position).getMoney());
		return convertView;
	}
private class ViewHolder{
	private TextView time1,time2,message,money;
}
}
