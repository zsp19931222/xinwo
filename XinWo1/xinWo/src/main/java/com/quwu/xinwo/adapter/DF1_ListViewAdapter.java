package com.quwu.xinwo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
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
import com.quwu.xinwo.bean.DF1_ListViewBean;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.CircleImageView;
import com.quwu.xinwo.mywight.MyGridView;

public class DF1_ListViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<DF1_ListViewBean> list;
	private Activity activity;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private ImageLoader imageLoader;

	public DF1_ListViewAdapter(Context context, List<DF1_ListViewBean> list,Activity activity,ImageLoader imageLoader) {
		super();
		this.context = context;
		this.imageLoader=imageLoader;
		this.list = list;
		this.activity=activity;
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
			convertView = inflater.inflate(R.layout.df1_listview_item, null);
			holder.imageView = (CircleImageView) convertView
					.findViewById(R.id.df1_listview_item_image);

			holder.name = (TextView) convertView
					.findViewById(R.id.df1_listview_item_nameText);
			holder.time_area = (TextView) convertView
					.findViewById(R.id.df1_listview_item_TimeAndAreaText);
			holder.original_price = (TextView) convertView
					.findViewById(R.id.df1_listview_item_original_priceText);
			holder.goods_name = (TextView) convertView
					.findViewById(R.id.df1_listview_item_goodsNameText);
			holder.goods_describe = (TextView) convertView
					.findViewById(R.id.df1_listview_item_goodsDescribeText);
			holder.residue = (TextView) convertView
					.findViewById(R.id.df1_listview_item_residueText);
			holder.like = (TextView) convertView
					.findViewById(R.id.df1_listview_item_likeText);

			holder.gridView = (MyGridView) convertView
					.findViewById(R.id.df1_listview_item_gridview);

			holder.totalBtn = (Button) convertView
					.findViewById(R.id.df1_listview_item_totalBtn);

			holder.bar = (ProgressBar) convertView
					.findViewById(R.id.df1_listview_item_Bar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getImageUrl(),
				holder.imageView, context);

		
		holder.name.setText(list.get(position).getName());
		holder.time_area.setText(list.get(position).getTime_area());
		holder.original_price.setText("¥："+list.get(position).getOriginal_price());
		holder.goods_name.setText(list.get(position).getGoods_name());
		holder.goods_describe.setText(list.get(position).getGoods_describe());
		holder.residue.setText("剩余："+list.get(position).getResidue()+"人次");
		holder.like.setText(list.get(position).getLiske());

		holder.bar.setMax(Integer.valueOf(list.get(position).getTotle()));
		holder.bar.setProgress(Integer.valueOf(list.get(position).getTotle())
				- Integer.valueOf(list.get(position).getResidue()));

		holder.totalBtn.setText("总需：¥"+list.get(position).getTotle());

		holder.gridView.setFocusable(false);
		isGridView(list.get(position).getUrlList(), holder.gridView);
		
		holder.original_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
		return convertView;
	}

	private class ViewHolder {
		private CircleImageView imageView;
		private TextView name;
		private TextView time_area;
		private TextView original_price;
		private MyGridView gridView;
		private TextView goods_name;
		private TextView goods_describe;
		private Button totalBtn;
		private ProgressBar bar;
		private TextView residue;
		private TextView like;
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
}
