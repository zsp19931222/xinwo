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
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.ChoicenessBean;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.imageloader.DisplayImageOptionsUtils;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.CircleImageView;
import com.quwu.xinwo.mywight.MyGridView;

public class ChoicenessAdapter extends BaseAdapter{
	
	private List<ChoicenessBean> list;
	private Context context;
	private LayoutInflater inflater;
	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private Activity activity;
	private ImageLoader imageLoader;

	public ChoicenessAdapter(List<ChoicenessBean> list, Context context,
			Activity activity,ImageLoader imageLoader) {
		super();
		this.list = list;
		this.context = context;
		this.activity = activity;
		this.imageLoader=imageLoader;
		inflater=LayoutInflater.from(context);
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
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.choiceness_item, null);
			holder.image=(CircleImageView) convertView.findViewById(R.id.choiceness_item_head_portraitImage);
			holder.userNameText=(TextView) convertView.findViewById(R.id.choiceness_item_userNameText);
			holder.time_areaText=(TextView) convertView.findViewById(R.id.choiceness_item_time_areaText);
			holder.now_priceText=(TextView) convertView.findViewById(R.id.choiceness_item_now_priceText1);
			
			holder.goodsNameText=(TextView) convertView.findViewById(R.id.choiceness_item_goodsNameText);
			holder.goodsDescribeText=(TextView) convertView.findViewById(R.id.choiceness_item_goodsDescribeText);
			holder.participation=(TextView) convertView.findViewById(R.id.choiceness_item_participationText1);
			holder.delayed=(TextView) convertView.findViewById(R.id.choiceness_item_delayedText2);
			
			holder.gridView=(MyGridView) convertView.findViewById(R.id.choiceness_item_gridview);
	convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(list.get(position).getImageurl(),  holder.image,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
		holder.userNameText.setText(list.get(position).getUserName());
		holder.time_areaText.setText(list.get(position).getTime_area());
		holder.now_priceText.setText(list.get(position).getNowPrice());
		holder.goodsNameText.setText(list.get(position).getGoodsName());
		holder.goodsDescribeText.setText(list.get(position).getGoodsDescribe());
		holder.participation.setText(list.get(position).getParticipation());
		holder.delayed.setText(list.get(position).getDelayed());
		isGridView(list.get(position).getUrls(), holder.gridView);
		return convertView;
	}
private class ViewHolder{
	private CircleImageView image;
	private TextView userNameText;
	private TextView time_areaText;
	private TextView now_priceText;
	private MyGridView gridView;
	private TextView goodsNameText;
	private TextView goodsDescribeText;
	private TextView participation;
	private TextView delayed;
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
