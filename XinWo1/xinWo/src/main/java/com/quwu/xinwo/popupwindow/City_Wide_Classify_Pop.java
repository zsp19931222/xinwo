package com.quwu.xinwo.popupwindow;

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.City_wide_classify_popAdapter;
import com.quwu.xinwo.bean.City_wide_classify_popBean;

public class City_Wide_Classify_Pop extends PopupWindow {
	public OnRankWindowClickListener lister;
	private View view;
	private Activity activity;
	private LayoutInflater inflater;
	private City_wide_classify_popAdapter adapter;
	private ListView listView;
	private List<City_wide_classify_popBean> list1;
	private String sortorder_id;
	public City_Wide_Classify_Pop(final Activity activity,List<City_wide_classify_popBean> list,String sortorder_id) {
		this.activity = activity;
		this.list1=list;
		this.sortorder_id=sortorder_id;
		inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.city_wide_classify_pop, null);
		listView=(ListView) view.findViewById(R.id.city_wide_classify_pop_listview);
		// 自定义PopupWindow
		// int w = context.getWindowManager().getDefaultDisplay().getWidth();
		@SuppressWarnings({ "deprecation", "unused" })
		int h = activity.getWindowManager().getDefaultDisplay().getHeight();
		// 设置SelectPicPopupWindow的View
		this.setContentView(view);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.anim.activity_translate_in);
		
		adapter=new City_wide_classify_popAdapter(activity, list,sortorder_id);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.isChangeTextview(position);
				adapter.notifyDataSetInvalidated();
				lister.send(list1.get(position).getFirstlevel_id(),list1.get(position).getFirstlevel_name());
				dismiss();
			}
		});
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent);
			view.setAlpha(0.95f);
		} else {
			this.dismiss();
			view.setAlpha(1f);
		}
	}

	public interface OnRankWindowClickListener {
		void send(String id,String name);
	}

	public void setOnRankWindowClickListener(OnRankWindowClickListener lister) {
		this.lister = lister;
	}

}
