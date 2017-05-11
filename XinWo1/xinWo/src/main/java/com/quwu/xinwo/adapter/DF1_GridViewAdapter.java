package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.DF1_GridViewBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.until.ScreenUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DF1_GridViewAdapter extends BaseAdapter {

	private List<DF1_GridViewBean> list;
	private Context context;
	private LayoutInflater inflater;

	public DF1_GridViewAdapter(List<DF1_GridViewBean> list, Context context) {
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
			convertView = inflater.inflate(R.layout.df1_gridview_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.df1_gridview_item_image);
			holder.name = (TextView) convertView
					.findViewById(R.id.df1_gridview_item_nameText);
			holder.message = (TextView) convertView
					.findViewById(R.id.df1_gridview_item_messageText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getImageUrl(),
				holder.imageView, context);
		holder.name.setText(list.get(position).getName());
		holder.message.setText(list.get(position).getMessage());
		int w = ScreenUtils.getScreenWidth(context);
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) holder.imageView
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.height = w/3-50;
		holder.imageView.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name, message;
	}
}
