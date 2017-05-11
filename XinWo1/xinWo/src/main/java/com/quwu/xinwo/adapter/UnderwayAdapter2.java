package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.UnderwayBean2;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

public class UnderwayAdapter2 extends BaseAdapter {

	private List<UnderwayBean2> list;
	private Context context;
	private LayoutInflater inflater;

	public UnderwayAdapter2(List<UnderwayBean2> list, Context context) {
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
			convertView = inflater.inflate(R.layout.underway_item2, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.underway_item2_image);
			holder.name=(TextView) convertView.findViewById(R.id.underway_item2_nameText);
			holder.underway_item2_describeText=(TextView) convertView.findViewById(R.id.underway_item2_describeText);
			holder.price=(TextView) convertView.findViewById(R.id.underway_item2_priceText);
			holder.nowprice=(TextView) convertView.findViewById(R.id.underway_item2_nowpriceText);
			holder.person=(TextView) convertView.findViewById(R.id.underway_item2_personText);
			holder.message=(TextView) convertView.findViewById(R.id.underway_item2_messageText);
			holder.button=(Button) convertView.findViewById(R.id.underway_item2_Btn);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(), holder.imageView, context);
		holder.name.setText(list.get(position).getName());
		holder.underway_item2_describeText.setText(list.get(position).getDescribe());
		holder.price.setText(list.get(position).getPrice());
		holder.nowprice.setText(list.get(position).getNowprice());
		holder.person.setText(list.get(position).getPerson());
		holder.message.setText(list.get(position).getMessage());
		if (list.get(position).getState().equals("0")) {
			holder.button.setBackgroundResource(R.drawable.btn_afterbuy_pink);
		}else {
			holder.button.setBackgroundResource(R.drawable.btn_afterbuy_gray);
			holder.button.setClickable(false);
			holder.button.setTextColor(context.getResources().getColor(R.color.white));
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name,price,nowprice,person,message;
		private Button button;
		private TextView underway_item2_describeText;
	}
}
