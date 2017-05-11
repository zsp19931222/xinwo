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
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Lucky_RecordBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;

public class Lucky_RecordAdapter extends BaseAdapter {

	private List<Lucky_RecordBean> list;
	private Context context;
	private LayoutInflater inflater;
	private ImageLoader imageLoader ;

	public Lucky_RecordAdapter(List<Lucky_RecordBean> list, Context context,ImageLoader imageLoader) {
		super();
		this.list = list;
		this.context = context;
		this.imageLoader=imageLoader;

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
			convertView = inflater.inflate(R.layout.lucky_record_item, null);

			holder.goodsIamge = (ImageView) convertView
					.findViewById(R.id.lucky_record_item_image);
			holder.name = (TextView) convertView
					.findViewById(R.id.lucky_record_item_nameText);
			holder.describe = (TextView) convertView
					.findViewById(R.id.lucky_record_item_describeText);
			holder.text1 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_Text1);
			holder.text1_1 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_Text1_1);
			holder.text2 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_Text2);
			holder.text2_1 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_Text2_1);
			holder.text3 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_Text3);
			holder.text3_1 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_Text3_1);
			holder.time1 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_time1Text);
			holder.time2 = (TextView) convertView
					.findViewById(R.id.lucky_record_item_time2Text);

			holder.button1 = (Button) convertView
					.findViewById(R.id.lucky_record_item_btn1);
			holder.button2 = (Button) convertView
					.findViewById(R.id.lucky_record_item_btn2);

			holder.text1Lin = (LinearLayout) convertView
					.findViewById(R.id.lucky_record_item_Text1Lin);
			holder.text2Lin = (LinearLayout) convertView
					.findViewById(R.id.lucky_record_item_Text2Lin);
			holder.text3Lin = (LinearLayout) convertView
					.findViewById(R.id.lucky_record_item_Text3Lin);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getUrl(),  holder.goodsIamge,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holder.name.setText(list.get(position).getName());
		holder.describe.setText(list.get(position).getName());
		holder.text1.setText(list.get(position).getText1());
		holder.text1_1.setText(list.get(position).getText1_1());
		holder.text2.setText(list.get(position).getText2());
		holder.text2_1.setText(list.get(position).getText2_1());
		holder.text3.setText(list.get(position).getText3());
		holder.text3_1.setText(list.get(position).getText3_1());
		holder.time1.setText(list.get(position).getTime1());
		holder.time2.setText(list.get(position).getTime2());
		if (list.get(position).getState().equals("0")) {
			holder.button1.setText("确认收货");
			holder.button2.setText("查看状态");

		} else if (list.get(position).getState().equals("1")) {
			holder.text1Lin.setVisibility(View.INVISIBLE);
			holder.button1.setText("添加评论");
			holder.button2.setVisibility(View.INVISIBLE);
		} else if (list.get(position).getState().equals("2")) {
			holder.text1Lin.setVisibility(View.INVISIBLE);
			holder.text2Lin.setVisibility(View.INVISIBLE);
			holder.button1.setText("添加评论");
			holder.button2.setVisibility(View.INVISIBLE);
		} else if (list.get(position).getState().equals("3")) {
			holder.button1.setText("查看评价");
			holder.button1.setTextColor(context.getResources().getColor(
					R.color.white));
			holder.button1.setBackground(context.getResources().getDrawable(R.drawable.btn_afterbuy_gray));
			holder.button2.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView goodsIamge;
		private TextView name, describe, text1, text1_1, text2, text2_1, text3,
				text3_1, time1, time2;
		private Button button1, button2;
		private LinearLayout text1Lin, text2Lin, text3Lin;
	}
	
}
