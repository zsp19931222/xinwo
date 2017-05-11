package com.quwu.xinwo.adapter;

import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.My_AttentionBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.MySharePreferences;

public class My_AttentionAdapter extends BaseSwipeAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<My_AttentionBean> list;

	public My_AttentionAdapter(Context context, List<My_AttentionBean> list) {
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

	private class ViewHolder {
		private ImageView my_attention_goods_image;
		private ImageView my_attention_goods_image1;
		private TextView my_attention_goodsNameText;
		private TextView my_attention_goodsDescribeText;
		private TextView my_attention_attentionText;// 关注人数
		private TextView my_attention_browseText;// 浏览次数

		private LinearLayout my_attention_allLin;// 全价商品
		private TextView my_attention_all_priceText;

		private LinearLayout my_attention_crowd_fundingLin;// 众筹商品
		private TextView my_attention_crowd_funding_totalText;// 总需
		private TextView my_attention_crowd_funding_residueText;// 剩余

		private LinearLayout my_attention_rent_outLin;// 出租商品
		private TextView my_attention_rent_out_priceText;// 租金
		private TextView my_attention_rent_out_tenancy_termText;// 租期

		private LinearLayout my_attention_auctionLin;// 拍卖商品
		private TextView my_attention_auction_priceText;// 当前价
		private TextView my_attention_auction_people_numText;// 竞拍人数

		private LinearLayout my_attention_freeLin;// 免费送
		private TextView my_attention_freeText;// 描述
	}

	@Override
	public int getSwipeLayoutResourceId(int position) {
		return R.id.my_attention_item_swipe;
	}

	@Override
	public View generateView(final int position, ViewGroup parent) {
		View convertView = inflater.inflate(R.layout.my_attention_item, null);
		// 滑动删除
		final SwipeLayout swipeLayout = (SwipeLayout) convertView
				.findViewById(getSwipeLayoutResourceId(position));
		// holder.ll_menu = (LinearLayout)
		// convertView.findViewById(R.id.ll_menu);
		convertView.findViewById(R.id.my_attention_item_ll_menu)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// 点击完成之后，关闭删除menu
						new DeleteTask().executeOnExecutor(Executors.newCachedThreadPool(), list.get(position).getGoods_id(),String.valueOf(position));
						swipeLayout.close();
					}
				});
		return convertView;
	}

	@Override
	public void fillValues(int position, View convertView) {
		ViewHolder holder = null;
		holder = new ViewHolder();
		holder.my_attention_goods_image = (ImageView) convertView
				.findViewById(R.id.my_attention_goods_image);
		holder.my_attention_goods_image1 = (ImageView) convertView
				.findViewById(R.id.my_attention_goods_image1);
		holder.my_attention_goodsNameText = (TextView) convertView
				.findViewById(R.id.my_attention_goodsNameText);
		holder.my_attention_goodsDescribeText = (TextView) convertView
				.findViewById(R.id.my_attention_goodsDescribeText);
		holder.my_attention_attentionText = (TextView) convertView
				.findViewById(R.id.my_attention_attentionText);
		holder.my_attention_browseText = (TextView) convertView
				.findViewById(R.id.my_attention_browseText);
//		if (list.get(position).getState().equals("5")) {// 全价
			holder.my_attention_allLin = (LinearLayout) convertView
					.findViewById(R.id.my_attention_allLin);
			holder.my_attention_all_priceText = (TextView) convertView
					.findViewById(R.id.my_attention_all_priceText);
//		} else if (list.get(position).getState().equals("1")) {// 众筹
			holder.my_attention_crowd_fundingLin = (LinearLayout) convertView
					.findViewById(R.id.my_attention_crowd_fundingLin);
			holder.my_attention_crowd_funding_totalText = (TextView) convertView
					.findViewById(R.id.my_attention_crowd_funding_totalText);
			holder.my_attention_crowd_funding_residueText = (TextView) convertView
					.findViewById(R.id.my_attention_crowd_funding_residueText);
//		} else if (list.get(position).getState().equals("2")) {// 出租
			holder.my_attention_rent_outLin = (LinearLayout) convertView
					.findViewById(R.id.my_attention_rent_outLin);
			holder.my_attention_rent_out_priceText = (TextView) convertView
					.findViewById(R.id.my_attention_rent_out_priceText);
			holder.my_attention_rent_out_tenancy_termText = (TextView) convertView
					.findViewById(R.id.my_attention_rent_out_tenancy_termText);
//		} else if (list.get(position).getState().equals("3")) {// 拍卖
			holder.my_attention_auctionLin = (LinearLayout) convertView
					.findViewById(R.id.my_attention_auctionLin);
			holder.my_attention_auction_priceText = (TextView) convertView
					.findViewById(R.id.my_attention_auction_priceText);
			holder.my_attention_auction_people_numText = (TextView) convertView
					.findViewById(R.id.my_attention_auction_people_numText);
//		} else if (list.get(position).getState().equals("4")) {// 免费
			holder.my_attention_freeLin = (LinearLayout) convertView
					.findViewById(R.id.my_attention_freeLin);
			holder.my_attention_freeText = (TextView) convertView
					.findViewById(R.id.my_attention_freeText);

//		}
		convertView.setTag(holder);
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(),
				holder.my_attention_goods_image, context);
		holder.my_attention_goodsNameText.setText(list.get(position)
				.getGoodsName());
		holder.my_attention_goodsDescribeText.setText(list.get(position)
				.getGoodsDescribe());
		holder.my_attention_attentionText.setText(list.get(position)
				.getAttention());
		holder.my_attention_browseText.setText(list.get(position).getBrowse());

		if (list.get(position).getState().equals("5")) {
			holder.my_attention_allLin.setVisibility(View.VISIBLE);
			holder.my_attention_crowd_fundingLin.setVisibility(View.GONE);
			holder.my_attention_rent_outLin.setVisibility(View.GONE);
			holder.my_attention_auctionLin.setVisibility(View.GONE);
			holder.my_attention_freeLin.setVisibility(View.GONE);
			holder.my_attention_all_priceText.setText(list.get(position)
					.getOne());
			holder.my_attention_goods_image1
					.setBackgroundResource(R.drawable.all);
		} else if (list.get(position).getState().equals("1")) {
			holder.my_attention_crowd_fundingLin.setVisibility(View.VISIBLE);
			holder.my_attention_allLin.setVisibility(View.GONE);
			holder.my_attention_rent_outLin.setVisibility(View.GONE);
			holder.my_attention_auctionLin.setVisibility(View.GONE);
			holder.my_attention_freeLin.setVisibility(View.GONE);
			holder.my_attention_crowd_funding_totalText.setText(list.get(
					position).getOne());
			holder.my_attention_crowd_funding_residueText.setText(list.get(
					position).getTow());
			holder.my_attention_goods_image1
					.setBackgroundResource(R.drawable.crowd_funding);
		} else if (list.get(position).getState().equals("2")) {
			holder.my_attention_rent_outLin.setVisibility(View.VISIBLE);
			holder.my_attention_allLin.setVisibility(View.GONE);
			holder.my_attention_crowd_fundingLin.setVisibility(View.GONE);
			holder.my_attention_auctionLin.setVisibility(View.GONE);
			holder.my_attention_freeLin.setVisibility(View.GONE);
			holder.my_attention_rent_out_priceText.setText(list.get(position)
					.getOne());
			holder.my_attention_rent_out_tenancy_termText.setText(list.get(
					position).getTow());
			holder.my_attention_goods_image1
					.setBackgroundResource(R.drawable.rent);
		} else if (list.get(position).getState().equals("3")) {
			holder.my_attention_auctionLin.setVisibility(View.VISIBLE);
			holder.my_attention_allLin.setVisibility(View.GONE);
			holder.my_attention_crowd_fundingLin.setVisibility(View.GONE);
			holder.my_attention_rent_outLin.setVisibility(View.GONE);
			holder.my_attention_freeLin.setVisibility(View.GONE);
			holder.my_attention_auction_priceText.setText(list.get(position)
					.getOne());
			holder.my_attention_auction_people_numText.setText(list.get(
					position).getTow());
			holder.my_attention_goods_image1
					.setBackgroundResource(R.drawable.auction);
		} else if (list.get(position).getState().equals("4")) {
			holder.my_attention_freeLin.setVisibility(View.VISIBLE);
			holder.my_attention_allLin.setVisibility(View.GONE);
			holder.my_attention_crowd_fundingLin.setVisibility(View.GONE);
			holder.my_attention_rent_outLin.setVisibility(View.GONE);
			holder.my_attention_auctionLin.setVisibility(View.GONE);
			holder.my_attention_freeText.setText(list.get(position).getOne());
			holder.my_attention_goods_image1
					.setBackgroundResource(R.drawable.free);
		}
	}

	private class DeleteTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			String result = OKHTTP_POST.doPost2(MyApp.base_address
					+ "usersaction/cancelcollection.do", "user_id", MySharePreferences.GetUser_ID(context),
					"goods_id", params[0]);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String result1 = jsonObject.getString("1");
				if (result1.equals("你已取消关注过此商品")) {
					handler.sendEmptyMessageDelayed(0, 10);
				} else if (result1.equals("取消商品关注失败")) {
					handler.sendEmptyMessageDelayed(1, 10);
				} else if (result1.equals("取消商品关注成功")) {
					handler.sendMessage(handler.obtainMessage(2, params[1]));
				} else if (result1.equals("程序异常")) {
					handler.sendEmptyMessageDelayed(3, 10);
				} else if (result1.equals("你还没有关注过此商品")) {
					handler.sendEmptyMessageDelayed(4, 10);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	};

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				MyToast.Toast(context, "你已取消关注过此商品哦~请刷新一下试试");
				break;
			case 1:
				MyToast.Toast(context, "取消商品关注失败了~请刷新一下试试");
				break;
			case 2:
				int position =Integer.valueOf((String)msg.obj);
				list.remove(position);
				notifyDataSetChanged();
				MyToast.Toast(context, "取消商品关注成功");
				break;
			case 3:
				MyToast.Toast(context, "程序异常~请刷新一下试试");
				break;
			case 4:
				MyToast.Toast(context, "你还没有关注过此商品哦~请先去关注一下");
				break;

			default:
				break;
			}
		};
	};
}
