package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

public class My_Release_GridviewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<My_Release_GridviewBean> list;
	private ImageLoader imageLoader;

	public My_Release_GridviewAdapter(Context context,
			List<My_Release_GridviewBean> list,ImageLoader imageLoader) {
		super();
		this.context = context;
		this.list = list;
		this.imageLoader=imageLoader;

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
			convertView = inflater.inflate(
					R.layout.my_release_item_gridview_item, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.my_release_item_gridview_item_iamge);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getUrl(),  holder.imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
	}
}
