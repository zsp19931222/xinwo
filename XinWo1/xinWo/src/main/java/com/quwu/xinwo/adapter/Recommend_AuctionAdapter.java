package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Recommend_Fragment2Bean;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Recommend_AuctionAdapter extends BaseAdapter{

	private Context context;
	private List<Recommend_Fragment2Bean> list;
	private LayoutInflater inflater;
	
	public Recommend_AuctionAdapter(Context context,
			List<Recommend_Fragment2Bean> list) {
		super();
		this.context = context;
		this.list = list;
		inflater=LayoutInflater.from(context);
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
		convertView=inflater.inflate(R.layout.recommend_auction_item, null);
		holder.imageView=(ImageView) convertView.findViewById(R.id.recommend_auction_item_iamge);
		holder.name=(TextView) convertView.findViewById(R.id.recommend_auction_item_nameText);
		holder.describe=(TextView) convertView.findViewById(R.id.recommend_auction_item_describeText);
		holder.price=(TextView) convertView.findViewById(R.id.recommend_auction_item_priceText);
		holder.participation=(TextView) convertView.findViewById(R.id.recommend_auction_item_participationText);
		holder.btnText=(TextView) convertView.findViewById(R.id.recommend_auction_item_btnText);
		convertView.setTag(holder);
	}else {
		holder=(ViewHolder) convertView.getTag();
	}
		ImageloaderUtils.MyImageLoader2(list.get(position).getUrl(), holder.imageView, context);
		holder.name.setText(list.get(position).getName());
		holder.describe.setText(list.get(position).getDescribe());
		holder.price.setText(list.get(position).getPrice());
		holder.participation.setText(list.get(position).getParticipation());
		
		holder.btnText.setOnClickListener(new MyClickListener());
		return convertView;
	}
private class ViewHolder{
	private ImageView imageView;
	private TextView name,describe,price,participation,btnText;
}
private class MyClickListener implements OnClickListener {

	
	public void onClick(View v) {
		

	}
}
}
