package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Recommend_AllPriceBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Recommend_AllPriceAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<Recommend_AllPriceBean> list;

	public Recommend_AllPriceAdapter(Context context,
			List<Recommend_AllPriceBean> list) {
		super();
		this.context = context;
		this.list = list;

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
			convertView = inflater.inflate(R.layout.recommend_allprice_item,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.recommend_allprice_itemImage);
			holder.describe = (TextView) convertView
					.findViewById(R.id.recommend_allprice_item_describeText);
			holder.name = (TextView) convertView
					.findViewById(R.id.recommend_allprice_item_nameText);
			holder.price = (TextView) convertView
					.findViewById(R.id.recommend_allprice_item_priceText);
			holder.button = (Button) convertView
					.findViewById(R.id.recommend_allprice_item_Btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(),
				holder.imageView, context);
		holder.describe.setText(list.get(position).getDescribe());
		holder.name.setText(list.get(position).getName());
		holder.price.setText(list.get(position).getPrice());
		holder.button.setOnClickListener(new MyClickListener());
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView describe, name, price;
		private Button button;
	}

	private class MyClickListener implements OnClickListener {

		
		public void onClick(View v) {
			

		}
	}
}
