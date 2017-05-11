package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.City_wide_classify_popBean;

public class City_wide_classify_popAdapter extends BaseAdapter {

	private Context context;
	private List<City_wide_classify_popBean> list;
	private LayoutInflater inflater;
	private ViewHolder holder = null;
	private int numItem=-1;
	private String sortorder_id;

	public City_wide_classify_popAdapter(Context context,
			List<City_wide_classify_popBean> list,String sortorder_id) {
		super();
		this.context = context;
		this.list = list;
		this.sortorder_id=sortorder_id;
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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.city_wide_classify_pop_item, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.city_wide_classify_pop_item_Text);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.city_wide_classify_pop_item_yesImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	
		if (numItem==-1) {
			if (position==0) {
				holder.textView.setTextColor(context.getResources().getColor(R.color.淡红色));
				holder.imageView.setVisibility(View.VISIBLE);
			}else {
				holder.textView.setTextColor(context.getResources().getColor(R.color.黑字体色));
				holder.imageView.setVisibility(View.INVISIBLE);
			}
		}else {
			if (position==numItem) {
				holder.textView.setTextColor(context.getResources().getColor(R.color.淡红色));
				holder.imageView.setVisibility(View.VISIBLE);
			}else {
				holder.textView.setTextColor(context.getResources().getColor(R.color.黑字体色));
				holder.imageView.setVisibility(View.INVISIBLE);
			}
		}
		if (sortorder_id.equals(list.get(position).getFirstlevel_id())) {
			holder.textView.setTextColor(context.getResources().getColor(R.color.淡红色));
			holder.imageView.setVisibility(View.VISIBLE);
		}else {
			holder.textView.setTextColor(context.getResources().getColor(R.color.黑字体色));
			holder.imageView.setVisibility(View.INVISIBLE);
		}
		holder.textView.setText(list.get(position).getFirstlevel_name());
		
		return convertView;
	}

	private class ViewHolder {
		private TextView textView;
		private ImageView imageView;
	}

	public void isChangeTextview(int numItem) {
		this.numItem=numItem;
	}
}
