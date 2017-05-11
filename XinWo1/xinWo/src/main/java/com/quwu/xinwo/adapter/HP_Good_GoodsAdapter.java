package com.quwu.xinwo.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.HP_Good_GoodsBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

public class HP_Good_GoodsAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<HP_Good_GoodsBean> list;

	public HP_Good_GoodsAdapter(Context context, List<HP_Good_GoodsBean> list) {
		super();
		this.context = context;
		this.list = list;

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
			convertView = inflater.inflate(
					R.layout.hp_good_goods_gridview_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.hp_good_goods_gridview_item_Image);
			holder.bar = (ProgressBar) convertView
					.findViewById(R.id.hp_good_goods_gridview_item_Bar);
			holder.name = (TextView) convertView
					.findViewById(R.id.hp_good_goods_gridview_item_nameText);
			holder.plan = (TextView) convertView
					.findViewById(R.id.hp_good_goods_gridview_item_planText);
			holder.describe = (TextView) convertView
					.findViewById(R.id.hp_good_goods_gridview_item_describeText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (list.get(position).getGood_description() != null
				&& list.get(position).getGoods_photo() != null
				&& list.get(position).getTotal_person() != null
				&& list.get(position).getParticipant_person() != null
				&& list.get(position).getGoods_name() != null) {
			ImageloaderUtils.MyImageLoader2(
					list.get(position).getGoods_photo(), holder.imageView,
					context);
			if (!list.get(position)
					.getTotal_person().equals("")) {
			holder.bar.setMax(Integer.valueOf(list.get(position)
					.getTotal_person()));
			holder.bar.setProgress(Integer.valueOf(list.get(position)
					.getParticipant_person()));
			holder.name.setText(list.get(position).getGoods_name());
			float num = (float) Integer.valueOf(list.get(position)
					.getParticipant_person())
					/ Integer.valueOf(list.get(position).getTotal_person());
			DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
			String s = df.format(num);// 返回的是String类型
			double d = Double.valueOf(s);
			int i = (int) (d * 100);
			holder.plan.setText(i + "%");
			holder.describe.setText(list.get(position).getGood_description());
			}
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		private TextView name;
		private TextView plan;
		private ProgressBar bar;
		private TextView describe;
	}
}
