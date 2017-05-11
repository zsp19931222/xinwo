package com.quwu.xinwo.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.After_SaleBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;
import com.quwu.xinwo.imageloader.ImageloaderUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class After_SaleAdapter extends BaseAdapter{

	private Context context;
	private List<After_SaleBean> list;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	
	public After_SaleAdapter(Context context, List<After_SaleBean> list,ImageLoader imageLoader) {
		super();
		this.context = context;
		this.list = list;
		this.imageLoader=imageLoader;
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
			convertView=inflater.inflate(R.layout.after_sale_item, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.after_sale_item_image);
			
			holder.goodState=(TextView) convertView.findViewById(R.id.after_sale_item_goodsStateText);
			holder.time=(TextView) convertView.findViewById(R.id.after_sale_item_timeText);
			holder.name=(TextView) convertView.findViewById(R.id.after_sale_item_nameText);
			holder.price=(TextView) convertView.findViewById(R.id.after_sale_item_priceText);
			holder.relation=(TextView) convertView.findViewById(R.id.after_sale_item_relationText);
			holder.orderNumText=(TextView) convertView.findViewById(R.id.after_sale_item_orderNumText);
			
			holder.box=(CheckBox) convertView.findViewById(R.id.after_sale_item_Box);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getUrl(),  holder.imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holder.goodState.setText(list.get(position).getGoodState());
		holder.orderNumText.setText(list.get(position).getOrderNumText());
		holder.time.setText(list.get(position).getTime());
		holder.name.setText(list.get(position).getName());
		holder.price.setText(list.get(position).getPrice());
		holder.relation.setText(list.get(position).getRelation());
		holder.box.setChecked(list.get(position).isBox());
		holder.box.setText(list.get(position).getBoxText());
		if (list.get(position).isBox()==true) {
			holder.box.setBackground(context.getResources().getDrawable(R.drawable.btn_afterbuy_pink));
		}else {
			holder.box.setBackground(context.getResources().getDrawable(R.drawable.btn_afterbuy_gray));
		}
		return convertView;
	}
private class ViewHolder{
	private ImageView imageView;
	private TextView goodState,time,name,price,relation,orderNumText;
	private CheckBox box;
}
}
