package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.UnderwayBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

public class UnderwayAdapter extends BaseAdapter {

	private List<UnderwayBean> list;
	private Context context;
	private LayoutInflater inflater;

	public UnderwayAdapter(List<UnderwayBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;

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
			convertView = inflater.inflate(R.layout.underway_item, null);
			holder.underway_item_announceLin = (LinearLayout) convertView
					.findViewById(R.id.underway_item_announceLin);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.underway_item_image);
			holder.name = (TextView) convertView
					.findViewById(R.id.underway_item_nameText);
			holder.alldemand = (TextView) convertView
					.findViewById(R.id.underway_item_alldemandText);
			holder.underway_item_describeText = (TextView) convertView
					.findViewById(R.id.underway_item_describeText);
			holder.residue = (TextView) convertView
					.findViewById(R.id.underway_item_residueText);
			holder.person = (TextView) convertView
					.findViewById(R.id.underway_item_personText);
			holder.check = (TextView) convertView
					.findViewById(R.id.underway_item_checkText);

			holder.bar = (ProgressBar) convertView
					.findViewById(R.id.underway_item_Bar);
			holder.button = (Button) convertView
					.findViewById(R.id.underway_item_Btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(),
				holder.imageView, context);
		holder.name.setText(list.get(position).getName());
		holder.underway_item_describeText.setText(list.get(position).getDescribe());
		holder.alldemand.setText("总需："+list.get(position).getAlldemand());
		holder.residue.setText("剩余："+list.get(position).getResidue());
		holder.person.setText(list.get(position).getPerson());
		holder.check.setText(list.get(position).getCheck());
		holder.bar.setMax(Integer.valueOf(list.get(position).getAlldemand()));
		holder.bar.setProgress(list.get(position).getBainum());
		holder.button.setText(list.get(position).getBtnText());
		if (list.get(position).getResidue().equals("0")) {
			holder.underway_item_announceLin.setVisibility(View.VISIBLE);
			holder.button.setVisibility(View.INVISIBLE);
		}else {
			holder.underway_item_announceLin.setVisibility(View.GONE);
			holder.button.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name, alldemand, residue, person, check;
		private ProgressBar bar;
		private Button button;
		private LinearLayout underway_item_announceLin;
		private TextView underway_item_describeText;
	}
}
