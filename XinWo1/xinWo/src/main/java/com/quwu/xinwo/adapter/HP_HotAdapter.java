package com.quwu.xinwo.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.HP_HotBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HP_HotAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<HP_HotBean> list;
	private int i;

	public HP_HotAdapter(Context context, List<HP_HotBean> list, int i) {
		super();
		this.context = context;
		this.list = list;
		this.i = i;

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
			convertView = inflater.inflate(R.layout.hot_item, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.hot_item_image);
			holder.hot_item_zhongchouImage = (ImageView) convertView
					.findViewById(R.id.hot_item_zhongchouImage);
			holder.name = (TextView) convertView
					.findViewById(R.id.hot_item_nameText);
			holder.price = (TextView) convertView
					.findViewById(R.id.hot_item_priceText);
			holder.describe = (TextView) convertView
					.findViewById(R.id.hot_item_describeText);
			holder.hot_item_price1Text = (TextView) convertView
					.findViewById(R.id.hot_item_price1Text);
			holder.hot_item_nowpriceText = (TextView) convertView
					.findViewById(R.id.hot_item_nowpriceText);
			holder.button = (Button) convertView
					.findViewById(R.id.hot_item_Btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader3(list.get(position).getImageUrl(),  holder.imageView, context);
		holder.name.setText(list.get(position).getName());
		holder.price.setText(list.get(position).getPrice());
		holder.describe.setText(list.get(position).getDescribe());
		holder.button.setText(list.get(position).getBtn_text());
		if (i == 0) {
			holder.hot_item_nowpriceText.setVisibility(View.GONE);
			holder.hot_item_price1Text.setVisibility(View.GONE);
		} else if (i == 1) {
			holder.hot_item_nowpriceText.setVisibility(View.GONE);
			holder.hot_item_zhongchouImage.setVisibility(View.GONE);
		} else if (i == 2) {
			holder.price.setVisibility(View.GONE);
			holder.hot_item_price1Text.setVisibility(View.GONE);
			holder.hot_item_nowpriceText.setVisibility(View.GONE);
			holder.hot_item_zhongchouImage.setVisibility(View.GONE);
		} else if (i == 3) {
			holder.hot_item_zhongchouImage.setVisibility(View.GONE);
			holder.hot_item_price1Text.setVisibility(View.GONE);
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView, hot_item_zhongchouImage;
		private TextView name, price, describe, hot_item_price1Text,
				hot_item_nowpriceText;
		private Button button;

	}
}
