package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.DoubleListviewBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DoubleListviewAdapter2 extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<DoubleListviewBean> list;

	public DoubleListviewAdapter2(Context context, List<DoubleListviewBean> list) {
		super();
		this.context = context;
		this.list = list;

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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.doublelistview_item2, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.doublelistview_item2_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(list.get(position).getRegion_name());
		return convertView;
	}

	private class ViewHolder {
		private TextView textView;
	}
}
