package com.quwu.xinwo.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_RedpacketBean;
import com.quwu.xinwo.until.ScreenUtils;

public class My_RedpacketAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<My_RedpacketBean> list;
	private Activity activity;

	public My_RedpacketAdapter(Context context, List<My_RedpacketBean> list,
			Activity activity) {
		super();
		this.context = context;
		this.list = list;
		this.activity = activity;

		inflater = LayoutInflater.from(context);
	}

	public int getCount() {

		return list.size();
	}

	public Object getItem(int position) {

		return null;
	}

	public long getItemId(int position) {

		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.my_redpacket_item, null);
			holder.money = (TextView) convertView
					.findViewById(R.id.my_redpacket_item_moneyText);
			holder.num = (TextView) convertView
					.findViewById(R.id.my_redpacket_item_numText);
			holder.my_redpacket_itemNumLin = (LinearLayout) convertView
					.findViewById(R.id.my_redpacket_itemNumLin);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.money.setText(list.get(position).getMoney());
		holder.num.setText(list.get(position).getNum());
		if (list.get(position).getNum().equals("1")) {
			holder.my_redpacket_itemNumLin.setVisibility(View.INVISIBLE);
		}
		if (list.get(position).getRedexchange_state().equals("1")) {
			convertView.setBackgroundResource(R.drawable.red_bags_pre);
		}
		ScreenUtils.getScreenWidth(context);
		int h=(int) (ScreenUtils.getScreenWidth(context)/2-50);
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, h);
		convertView.setLayoutParams(lp);
		return convertView;
	}

	private class ViewHolder {
		private TextView money, num;
		private LinearLayout my_redpacket_itemNumLin;
	}
}
