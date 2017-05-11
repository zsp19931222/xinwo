package com.quwu.xinwo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Crowd_FoundingBean;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.TextProgressBar;

public class Crowd_FoundingAdapter extends BaseAdapter {

	private List<Crowd_FoundingBean> list;
	private Context context;
	private LayoutInflater inflater;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private Activity activity;
	private ImageLoader imageLoader;

	public Crowd_FoundingAdapter(List<Crowd_FoundingBean> list,
			Context context, Activity activity,ImageLoader imageLoader) {
		super();
		this.list = list;
		this.context = context;
		this.activity = activity;
		this.imageLoader=imageLoader;
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.crowd_funding_item, null);
			holder.gridView = (MyGridView) convertView
					.findViewById(R.id.crowd_funding_item_gridview);
			holder.goodsNameText = (TextView) convertView
					.findViewById(R.id.crowd_funding_item_goods_nameText);
			holder.goodsDescribeText = (TextView) convertView
					.findViewById(R.id.crowd_funding_item_goods_describeText);
			holder.area_timeText = (TextView) convertView
					.findViewById(R.id.crowd_funding_item_area_timeText);
			holder.priceText = (TextView) convertView
					.findViewById(R.id.crowd_funding_item_priceText);
			holder.residueText = (TextView) convertView
					.findViewById(R.id.crowd_funding_item_residueText);
			holder.button = (Button) convertView
					.findViewById(R.id.crowd_funding_item_btn);
			holder.bar=(TextProgressBar) convertView.findViewById(R.id.crowd_funding_item_Bar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.goodsNameText.setText(list.get(position).getGoodsName());
		holder.goodsDescribeText.setText(list.get(position).getGoodsDescribe());
		holder.area_timeText.setText(list.get(position).getArea_time());
		holder.priceText.setText(list.get(position).getPrice());
		holder.residueText.setText("剩余："+list.get(position).getResidue());
		isGridView(list.get(position).getUrls(), holder.gridView,imageLoader);
		holder.bar.setMax(Integer.valueOf(list.get(position).getPrice()));
		holder.bar.setProgress(Integer.valueOf(list.get(position).getPrice())-Integer.valueOf(list.get(position).getResidue()));
		return convertView;
	}

	private class ViewHolder {
		private MyGridView gridView;
		private TextView goodsNameText;
		private TextView goodsDescribeText;
		private TextView area_timeText;
		private TextView priceText;
		private TextView residueText;
		private Button button;
		private TextProgressBar bar;
	}

	private void isGridView(List<String> url, MyGridView gridView,ImageLoader imageLoader) {
		gridviewlist = new ArrayList<My_Release_GridviewBean>();
		for (int i = 0; i < url.size(); i++) {
			gridviewlist.add(new My_Release_GridviewBean(url.get(i)));
		}
		int size = gridviewlist.size();
		int length = 100;
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, itemWidth);
		gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
		gridView.setColumnWidth(itemWidth); // 设置列表项宽
		gridView.setHorizontalSpacing(20); // 设置列表项水平间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 设置列数量=列表集合数
		adapter = new My_Release_GridviewAdapter(context, gridviewlist,imageLoader);
		gridView.setAdapter(adapter);
		gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}
}
