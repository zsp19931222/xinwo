package com.quwu.xinwo.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.bean.Personal_CenterBean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.CircleImageView;
import com.quwu.xinwo.mywight.MyGridView;

public class Personal_CenterAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Personal_CenterBean> list;
	private Activity context;
	private int state;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private HashMap<Integer, Integer> hashMap;
	private ImageLoader imageLoader;

	public Personal_CenterAdapter(List<Personal_CenterBean> list,
			Activity context, int state) {
		super();
		this.list = list;
		this.context = context;
		this.state = state;
		imageLoader=ImageLoader.getInstance();
		hashMap = new HashMap<Integer, Integer>();
		hashMap.put(0, 0);
		inflater = LayoutInflater.from(context);
	}

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
			convertView = inflater.inflate(R.layout.personal_center_item,
					parent, false);
			holder.pci_userImage = (CircleImageView) convertView
					.findViewById(R.id.pci_userImage);
			holder.pci_userNameText = (TextView) convertView
					.findViewById(R.id.pci_userNameText);
			holder.pic_areaText = (TextView) convertView
					.findViewById(R.id.pic_areaText);
			holder.pic_priceText1 = (TextView) convertView
					.findViewById(R.id.pic_priceText1);
			holder.pic_newPriceText = (TextView) convertView
					.findViewById(R.id.pic_newPriceText);
			holder.pic_oldPriceText = (TextView) convertView
					.findViewById(R.id.pic_oldPriceText);
			holder.pci_goodsNameText = (TextView) convertView
					.findViewById(R.id.pci_goodsNameText);
			holder.pci_goodsDescribeText = (TextView) convertView
					.findViewById(R.id.pci_goodsDescribeText);
			holder.personal_center_iamgeLin = (LinearLayout) convertView
					.findViewById(R.id.personal_center_iamgeLin);
			// holder.gridView=(MyGridView)
			// convertView.findViewById(R.id.pci_gridview);

			holder.pci_winerRel = (RelativeLayout) convertView
					.findViewById(R.id.pci_winerRel);
			holder.pci_winerImage = (CircleImageView) convertView
					.findViewById(R.id.pci_winerImage);
			holder.pci_winerNameText = (TextView) convertView
					.findViewById(R.id.pci_winerNameText);
			holder.pci_winer_participationText = (TextView) convertView
					.findViewById(R.id.pci_winer_participationText);
			holder.pci_winer_lucknumberText = (TextView) convertView
					.findViewById(R.id.pci_winer_lucknumberText);
			holder.pci_winer_announce_timeText = (TextView) convertView
					.findViewById(R.id.pci_winer_announce_timeText);
			holder.pic_crowd_fundingText = (TextView) convertView
					.findViewById(R.id.pic_crowd_fundingText);
			holder.pic_closeText = (TextView) convertView
					.findViewById(R.id.pic_closeText);
			holder.pci_rent_outLin = (LinearLayout) convertView
					.findViewById(R.id.pci_rent_outLin);
			holder.pci_rent_out_priceText = (TextView) convertView
					.findViewById(R.id.pci_rent_out_priceText);
			holder.pci_rent_out_timeText = (TextView) convertView
					.findViewById(R.id.pci_rent_out_timeText);

			holder.pci_freeText = (TextView) convertView
					.findViewById(R.id.pci_freeText);

			holder.pci_auctionLin = (LinearLayout) convertView
					.findViewById(R.id.pci_auctionLin);
			holder.pci_auction_participationText = (TextView) convertView
					.findViewById(R.id.pci_auction_participationText);
			holder.pci_auction_timeText = (TextView) convertView
					.findViewById(R.id.pci_auction_timeText);
			holder.personal_center_iamgeLin.setTag(position);
			// isGridView(list.get(position).getUrls(), holder.gridView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (state == 0) {// 二手
			holder.pic_priceText1.setVisibility(View.VISIBLE);
			holder.pic_newPriceText.setVisibility(View.VISIBLE);
			holder.pic_newPriceText.setText(list.get(position).getNewPrice());
			if (list.get(position).getWinner_state() == 0) {
				holder.pci_winerRel.setVisibility(View.VISIBLE);
				holder.pic_crowd_fundingText.setVisibility(View.VISIBLE);
				holder.pic_closeText.setVisibility(View.VISIBLE);
				holder.pic_oldPriceText.setVisibility(View.INVISIBLE);
				holder.pic_oldPriceText.setText("");
				ImageloaderUtils.MyImageLoader2(list.get(position)
						.getWinnerImage(), holder.pci_winerImage, context);
				holder.pci_winerNameText.setText(list.get(position)
						.getWinnerName());
				holder.pci_winer_participationText.setText(list.get(position)
						.getWinnerParticipation());
				holder.pci_winer_lucknumberText.setText(list.get(position)
						.getWinnerLucknum());
				holder.pci_winer_announce_timeText.setText(list.get(position)
						.getWinnerTime());
			} else if (list.get(position).getWinner_state() == 1) {
				holder.pic_oldPriceText.setVisibility(View.VISIBLE);
				holder.pci_winerRel.setVisibility(View.GONE);
				holder.pic_oldPriceText.setText("  ¥"
						+ list.get(position).getOldPrice());
				holder.pic_oldPriceText.getPaint().setFlags(
						Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
			}
		} else if (state == 1) {// 出租
			holder.pci_rent_outLin.setVisibility(View.VISIBLE);
			holder.pci_rent_out_priceText.setText(list.get(position).getRent());
			holder.pci_rent_out_timeText.setText(list.get(position)
					.getTenancy_term());
		} else if (state == 2) {// 免费送
			holder.pci_freeText.setVisibility(View.VISIBLE);
		} else if (state == 3) {// 拍卖
			holder.pic_priceText1.setVisibility(View.VISIBLE);
			holder.pic_newPriceText.setVisibility(View.VISIBLE);
			holder.pic_oldPriceText.setText("");
			if (list.get(position).getAuction_state() == 0) {
				holder.pic_priceText1.setText("当前价：¥");
				holder.pic_newPriceText.setText(list.get(position)
						.getAuction_nowPrice());
				holder.pci_auctionLin.setVisibility(View.VISIBLE);
				holder.pci_auction_participationText.setText(list.get(position)
						.getAuction_participation());
				holder.pci_auction_timeText.setText(list.get(position)
						.getAuction_delayed());
			} else if (list.get(position).getAuction_state() == 1) {

				holder.pic_priceText1.setText("成交价：¥");
				holder.pic_newPriceText.setText(list.get(position)
						.getAuction_transactionPrice());
			}
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUserImage(),
				holder.pci_userImage, context);
		holder.pci_userNameText.setText(list.get(position).getUserName());
		holder.pic_areaText.setText(list.get(position).getArea());
		holder.pci_goodsNameText.setText(list.get(position).getGoodsName());
		holder.pci_goodsDescribeText.setText(list.get(position)
				.getGoodsDescribe());
		hashMap.put(position, position);
		isAddImageView((Integer) holder.personal_center_iamgeLin.getTag(),
				holder.personal_center_iamgeLin);
		return convertView;
	}

	private class ViewHolder {
		private CircleImageView pci_userImage;
		private TextView pci_userNameText;
		private TextView pic_areaText;
		private TextView pic_priceText1;
		private TextView pic_newPriceText;
		private TextView pic_oldPriceText;
		private TextView pci_goodsNameText;
		private TextView pci_goodsDescribeText;
		private LinearLayout personal_center_iamgeLin;
		// private MyGridView gridView;

		private RelativeLayout pci_winerRel;
		private CircleImageView pci_winerImage;
		private TextView pci_winerNameText;
		private TextView pci_winer_participationText;
		private TextView pci_winer_lucknumberText;
		private TextView pci_winer_announce_timeText;
		private TextView pic_crowd_fundingText;
		private TextView pic_closeText;

		private LinearLayout pci_rent_outLin;
		private TextView pci_rent_out_priceText;
		private TextView pci_rent_out_timeText;

		private TextView pci_freeText;

		private LinearLayout pci_auctionLin;
		private TextView pci_auction_participationText;
		private TextView pci_auction_timeText;

	}

	private void isAddImageView(int position, LinearLayout linearLayout) {
			for (int i = 0; i < list.get(position).getUrls().size(); i++) {
				View view = inflater.inflate(
						R.layout.my_release_item_gridview_item, null);
				ImageView img = (ImageView) view
						.findViewById(R.id.my_release_item_gridview_item_iamge);
				ImageloaderUtils.MyImageLoader2(list.get(position)
						.getUrls().get(i), img, context);
				linearLayout.addView(view);
		}
	}

	private void isGridView(List<String> url, MyGridView gridView) {
		gridviewlist = new ArrayList<My_Release_GridviewBean>();
		for (int i = 0; i < url.size(); i++) {
			gridviewlist.add(new My_Release_GridviewBean(url.get(i)));
		}
		int size = gridviewlist.size();
		int length = 100;
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
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
		if (adapter == null) {
			adapter = new My_Release_GridviewAdapter(context, gridviewlist,imageLoader);
		}
		gridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}
}
