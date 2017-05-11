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
import com.quwu.xinwo.bean.AnnounceBean2;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

public class AnnounceAdapter2 extends BaseAdapter {

	private List<AnnounceBean2> list;
	private Context context;
	private LayoutInflater inflater;

	public AnnounceAdapter2(List<AnnounceBean2> list, Context context) {
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

		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.announce_item2, null);
			holder.goodsIamge=(ImageView) convertView.findViewById(R.id.announce_item2_goodsImage);
			
			holder.name=(TextView) convertView.findViewById(R.id.announce_item2_nameText);
			holder.describe=(TextView) convertView.findViewById(R.id.announce_item2_describeText);
			holder.bid=(TextView) convertView.findViewById(R.id.announce_item2_bidText);
			holder.person=(TextView) convertView.findViewById(R.id.announce_item2_personText);
			holder.transaction_price=(TextView) convertView.findViewById(R.id.announce_item2_transaction_priceText);
			holder.check=(TextView) convertView.findViewById(R.id.announce_item2_checkText);
			convertView.setTag(holder);
		}else {
		holder=	(ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(), holder.goodsIamge, context);
		
		holder.name.setText(list.get(position).getName());
		holder.describe.setText(list.get(position).getDescribe());
		holder.bid.setText(list.get(position).getBid());
		holder.person.setText(list.get(position).getPerson());
		holder.transaction_price.setText(list.get(position).getTransaction_price());
		holder.check.setText(list.get(position).getCheck());
		
		return convertView;
	}
private class ViewHolder{
	private ImageView goodsIamge;
	private TextView name,describe,bid,person,transaction_price,check;
}
}
