package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.DoubleListviewBean;

public class DoubleListviewAdapter1 extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<DoubleListviewBean> list;
	private ViewHolder holder = null;
	private int selectItem = -1;
	private String area;

	public DoubleListviewAdapter1(Context context,
			List<DoubleListviewBean> list, String area) {
		super();
		this.context = context;
		this.list = list;
		this.area = area;

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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.doublelistview_item1, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.doublelistview_item1_text);
			holder.doublelistview_item1_rel = (RelativeLayout) convertView
					.findViewById(R.id.doublelistview_item1_rel);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(list.get(position).getRegion_name());
		if (selectItem == -1) {
			if (area.equals(list.get(position).getRegion_name())) {
				holder.doublelistview_item1_rel.setBackgroundColor(context
						.getResources().getColor(R.color.white));
			} else {
				holder.doublelistview_item1_rel.setBackgroundColor(context
						.getResources().getColor(R.color.bg));
			}
		}else {
		if (position == selectItem) {
			holder.doublelistview_item1_rel.setBackgroundColor(context
					.getResources().getColor(R.color.white));
		} else {
			holder.doublelistview_item1_rel.setBackgroundColor(context
					.getResources().getColor(R.color.bg));
		}
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView textView;
		private RelativeLayout doublelistview_item1_rel;
	}

	public void isSelect(int selectItem) {
		this.selectItem = selectItem;
	}
}
