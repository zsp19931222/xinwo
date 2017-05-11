package com.quwu.xinwo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.bean.Recommend_Fragment2Bean;
import com.quwu.xinwo.mywight.MyGridView;

public class Recommend_Crowd_BundingAdapter extends BaseAdapter {

	private List<Recommend_Fragment2Bean> list;
	private Context context;
	private LayoutInflater inflater;
	private Activity activity;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private ImageLoader imageLoader;

	public Recommend_Crowd_BundingAdapter(
			List<Recommend_Fragment2Bean> list, Context context,
			Activity activity) {
		super();
		this.list = list;
		this.context = context;
		this.activity = activity;
imageLoader=ImageLoader.getInstance();
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
			convertView = inflater.inflate(
					R.layout.recommend_crowd_funding_item, null);
			holder.gridView = (MyGridView) convertView
					.findViewById(R.id.recommend_crowd_funding_gridview);

			holder.name = (TextView) convertView
					.findViewById(R.id.recommend_crowd_funding_nameText);
			holder.describe = (TextView) convertView
					.findViewById(R.id.recommend_crowd_funding_describeText);
			holder.area = (TextView) convertView
					.findViewById(R.id.recommend_crowd_funding_areaText);
			holder.price = (TextView) convertView
					.findViewById(R.id.recommend_crowd_funding_priceText);
			holder.residue = (TextView) convertView
					.findViewById(R.id.recommend_crowd_funding_residueText);
			holder.time = (TextView) convertView
					.findViewById(R.id.recommend_crowd_funding_timeText);

			holder.bar = (ProgressBar) convertView
					.findViewById(R.id.recommend_crowd_funding_progress);
			holder.button = (Button) convertView
					.findViewById(R.id.recommend_crowd_funding_Btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getName());
		holder.describe.setText(list.get(position).getDescribe());
		holder.area.setText(list.get(position).getArea());
		holder.price.setText(list.get(position).getPrice());
		holder.residue.setText(list.get(position).getResidue());
		holder.time.setText(list.get(position).getTime());
		holder.bar.setProgress(list.get(position).getBar());
		isGridView(list.get(position).getUrlList(), holder.gridView);
		holder.button.setOnClickListener(new MyClickListener());
		return convertView;
	}

	private class ViewHolder {
		private MyGridView gridView;
		private TextView name, describe, time, area, price, residue;
		private ProgressBar bar;
		private Button button;
	}

	private void isGridView(List<String> url, MyGridView gridView) {
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

		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
		gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
		gridView.setColumnWidth(itemWidth); // 设置列表项宽
		gridView.setHorizontalSpacing(20); // 设置列表项水平间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 设置列数量=列表集合数
		adapter = new My_Release_GridviewAdapter(context, gridviewlist,imageLoader);
		gridView.setAdapter(adapter);
		gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}

	private class MyClickListener implements OnClickListener {

		public void onClick(View v) {

		}
	}
}
