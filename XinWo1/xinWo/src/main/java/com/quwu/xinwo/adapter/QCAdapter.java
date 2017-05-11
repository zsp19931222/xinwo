package com.quwu.xinwo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.bean.QCBean;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.TextProgressBar;

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

public class QCAdapter extends BaseAdapter {

	private List<QCBean> list;
	private int state;
	private LayoutInflater inflater;
	private Context context;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private Activity activity;
	private ImageLoader imageLoader;
	private ViewHolder holder = null;

	public QCAdapter(List<QCBean> list, int state, Context context,
			Activity activity) {
		super();
		this.list = list;
		this.state = state;
		this.context = context;
		this.activity = activity;
		imageLoader=ImageLoader.getInstance();
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
			convertView = inflater.inflate(R.layout.official_qc_item, null);

			holder.gridView = (MyGridView) convertView
					.findViewById(R.id.qc_gridview);
			holder.goods_nameText = (TextView) convertView
					.findViewById(R.id.qc_goods_nameText);
			holder.goods_describeText = (TextView) convertView
					.findViewById(R.id.qc_goods_describeText);
			holder.area_timeText = (TextView) convertView
					.findViewById(R.id.qc_area_timeText);
			holder.button = (Button) convertView.findViewById(R.id.qc_btn);

			if (state == 0) {// 众筹
				holder.bar = (TextProgressBar) convertView
						.findViewById(R.id.qc_Bar);
				holder.RMBText = (TextView) convertView
						.findViewById(R.id.qc_RMBText);
				holder.priceText = (TextView) convertView
						.findViewById(R.id.qc_priceText);
				holder.residueText = (TextView) convertView
						.findViewById(R.id.qc_residueText);
			} else if (state == 1) {// 全价
				holder.all_RMBText = (TextView) convertView
						.findViewById(R.id.qc_all_RMBText1);
				holder.all_priceText = (TextView) convertView
						.findViewById(R.id.qc_all_priceText1);
			} else if (state == 2) {// 出租
				holder.rent_priceText1 = (TextView) convertView
						.findViewById(R.id.qc_priceText1);
				holder.rent_priceText2 = (TextView) convertView
						.findViewById(R.id.qc_priceText2);
				holder.rent_priceText3 = (TextView) convertView
						.findViewById(R.id.qc_priceText3);
				holder.rent_timeText1 = (TextView) convertView
						.findViewById(R.id.qc_residueText1);
				holder.rent_timeText2 = (TextView) convertView
						.findViewById(R.id.qc_residueText2);
				holder.rent_timeText3 = (TextView) convertView
						.findViewById(R.id.qc_residueText3);
			} else {// 拍卖
				holder.now_priceText1 = (TextView) convertView
						.findViewById(R.id.qc_now_priceText);
				holder.now_priceText2 = (TextView) convertView
						.findViewById(R.id.qc_now_priceText1);
				holder.now_priceText3 = (TextView) convertView
						.findViewById(R.id.qc_now_priceText2);
				holder.participationText = (TextView) convertView
						.findViewById(R.id.qc_participationText);
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		init();
		isGridView(list.get(position).getUrls(), holder.gridView);
		holder.goods_nameText.setText(list.get(position).getGoods_name());
		holder.goods_describeText.setText(list.get(position)
				.getGoods_describe());
		holder.area_timeText.setText(list.get(position).getArea_time());
		holder.button.setText(list.get(position).getBtnStr());
		if (state == 0) {
			isCrowd_funding();
			holder.bar.setMax(Integer.valueOf(list.get(position).getTow()));
			holder.bar.setProgress(Integer.valueOf(list.get(position).getTow())
					- Integer.valueOf(list.get(position).getThree()));
			holder.priceText.setText(list.get(position).getTow());
			holder.residueText.setText("剩余：" + list.get(position).getThree());
		} else if (state == 1) {
			isAll();
			holder.all_priceText.setText(list.get(position).getOne());
		} else if (state == 2) {
			isRent();
			holder.rent_priceText2.setText(list.get(position).getTow());
			holder.rent_timeText2.setText(list.get(position).getThree());
		} else if (state == 3) {
			isAuction();
			holder.now_priceText3.setText(list.get(position).getTow());
			holder.participationText.setText(list.get(position).getThree()+"人参与");
		}
		return convertView;
	}

	private class ViewHolder {
		private MyGridView gridView;
		private TextView goods_nameText;
		private TextView goods_describeText;
		private TextView area_timeText;
		private Button button;
		/**
		 * 众筹
		 * */
		private TextProgressBar bar;
		private TextView RMBText;
		private TextView priceText;
		private TextView residueText;
		/**
		 * 全价
		 * */
		private TextView all_RMBText;
		private TextView all_priceText;
		/**
		 * 出租
		 * */

		private TextView rent_priceText1;
		private TextView rent_priceText2;
		private TextView rent_priceText3;

		private TextView rent_timeText1;
		private TextView rent_timeText2;
		private TextView rent_timeText3;
		/**
		 * 拍卖
		 * */
		private TextView now_priceText1;
		private TextView now_priceText2;
		private TextView now_priceText3;

		private TextView participationText;
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

	private void init() {
		if (holder.bar != null) {
			holder.bar.setVisibility(View.GONE);
			holder.RMBText.setVisibility(View.GONE);
			holder.priceText.setVisibility(View.GONE);
			holder.residueText.setVisibility(View.GONE);
		} else if (holder.all_RMBText != null) {
			holder.all_RMBText.setVisibility(View.GONE);
			holder.all_priceText.setVisibility(View.GONE);
		} else if (holder.rent_priceText1 != null) {
			holder.rent_priceText1.setVisibility(View.GONE);
			holder.rent_priceText2.setVisibility(View.GONE);
			holder.rent_priceText3.setVisibility(View.GONE);
			holder.rent_timeText1.setVisibility(View.GONE);
			holder.rent_timeText2.setVisibility(View.GONE);
			holder.rent_timeText3.setVisibility(View.GONE);
		} else if (holder.now_priceText1 != null) {
			holder.now_priceText1.setVisibility(View.GONE);
			holder.now_priceText2.setVisibility(View.GONE);
			holder.now_priceText3.setVisibility(View.GONE);
			holder.participationText.setVisibility(View.GONE);
		}
	}

	private void isCrowd_funding() {
		holder.bar.setVisibility(View.VISIBLE);
		holder.RMBText.setVisibility(View.VISIBLE);
		holder.priceText.setVisibility(View.VISIBLE);
		holder.residueText.setVisibility(View.VISIBLE);
	}

	private void isAll() {
		holder.all_RMBText.setVisibility(View.VISIBLE);
		holder.all_priceText.setVisibility(View.VISIBLE);
	}

	private void isRent() {
		holder.rent_priceText1.setVisibility(View.VISIBLE);
		holder.rent_priceText2.setVisibility(View.VISIBLE);
		holder.rent_priceText3.setVisibility(View.VISIBLE);
		holder.rent_timeText1.setVisibility(View.VISIBLE);
		holder.rent_timeText2.setVisibility(View.VISIBLE);
		holder.rent_timeText3.setVisibility(View.VISIBLE);
	}

	private void isAuction() {
		holder.now_priceText1.setVisibility(View.VISIBLE);
		holder.now_priceText2.setVisibility(View.VISIBLE);
		holder.now_priceText3.setVisibility(View.VISIBLE);
		holder.participationText.setVisibility(View.VISIBLE);
	}
}
