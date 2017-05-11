package com.quwu.xinwo.adapter;

import java.util.List;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.AnnounceBean1;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AnnounceAdapter1 extends BaseAdapter {

	private List<AnnounceBean1> list;
	private Context context;
	private LayoutInflater inflater;

	public AnnounceAdapter1(List<AnnounceBean1> list, Context context) {
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
			convertView=inflater.inflate(R.layout.announce_item1, null);
			
			holder.goodsIamge=(ImageView) convertView.findViewById(R.id.announce_item1_goodsImage);
			holder.winnerImage=(ImageView) convertView.findViewById(R.id.announce_item1_winner_userImage);
			
			holder.goodsName=(TextView) convertView.findViewById(R.id.announce_item1_nameText);
			holder.describe=(TextView) convertView.findViewById(R.id.announce_item1_describeText);
			holder.alldemand=(TextView) convertView.findViewById(R.id.announce_item1_alldemandText);
			holder.person=(TextView) convertView.findViewById(R.id.announce_item1_personText);
			holder.check=(TextView) convertView.findViewById(R.id.announce_item1_checkText);
			
			holder.winnerName=(TextView) convertView.findViewById(R.id.announce_item1_winner_usernameText);
			holder.winnerLuckyNum=(TextView) convertView.findViewById(R.id.announce_item1_winner_luckynumText);
			holder.winnerPerson=(TextView) convertView.findViewById(R.id.announce_item1_winner_personText);
			holder.winnerTime=(TextView) convertView.findViewById(R.id.announce_item1_announceTimeText);
			
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		ImageloaderUtils.MyImageLoader2(list.get(position).getGoodsUrl(), holder.goodsIamge, context);
		ImageloaderUtils.MyImageLoader2(list.get(position).getWinnerImage(), holder.winnerImage, context);
		
		holder.goodsName.setText(list.get(position).getGoodsName());
		holder.describe.setText(list.get(position).getDescribe());
		holder.alldemand.setText(list.get(position).getAlldemand());
		holder.person.setText(list.get(position).getPerson());
		holder.check.setText(list.get(position).getCheck());
		
		holder.winnerName.setText(list.get(position).getWinnerName());
		holder.winnerLuckyNum.setText(list.get(position).getWinnerLuckyNum());
		holder.winnerPerson.setText(list.get(position).getWinnerPerson());
		holder.winnerTime.setText(list.get(position).getWinnerTime());
		return convertView;
	}
private class ViewHolder{
	private ImageView goodsIamge;
	private ImageView winnerImage;
	private TextView goodsName,describe,alldemand,person,check;
	private TextView winnerName,winnerLuckyNum,winnerPerson,winnerTime;
}
}
