package com.quwu.xinwo.popupwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.DoubleListviewAdapter1;
import com.quwu.xinwo.adapter.DoubleListviewAdapter2;
import com.quwu.xinwo.bean.DoubleListviewBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.City_Wide_Classes_PopEntity;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.ScreenUtils;

public class City_Wide_Classes_Pop extends PopupWindow {
	public OnClassesWindowClickListener lister;
	private View view;
	private Activity activity;
	private LayoutInflater inflater;

	private ListView listView1;
	private ListView listView2;
	private DoubleListviewAdapter1 adapter1;
	private DoubleListviewAdapter2 adapter2;
	private List<DoubleListviewBean> list1;
	private List<DoubleListviewBean> list2;

	private String twolevel_id;// 分类ID
	private String twolevel_name;// 分类名称
	private String images_url;// 图片路径

	private String twolevel_id1;
	private String name;

	private List<City_Wide_Classes_PopEntity> json_list;

	public City_Wide_Classes_Pop(final Activity activity) {
		this.activity = activity;
		inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.doublelistview, null);
		listView1 = (ListView) view.findViewById(R.id.doublelistview_listview1);
		listView2 = (ListView) view.findViewById(R.id.doublelistview_listview2);
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
		// 自定义PopupWindow
		// int w = context.getWindowManager().getDefaultDisplay().getWidth();
		@SuppressWarnings({ "deprecation", "unused" })
		int h = activity.getWindowManager().getDefaultDisplay().getHeight();
		// 设置SelectPicPopupWindow的View
		this.setContentView(view);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(ScreenUtils.getScreenHeight(activity) / 2);
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
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent);
			view.setAlpha(0.9f);
		} else {
			this.dismiss();
			view.setAlpha(1f);
		}
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			list1 = new ArrayList<DoubleListviewBean>();
			list1.add(new DoubleListviewBean("0", "所有分类"));
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selectgoodscategory.do", "", "");
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						json_list = gson
								.fromJson(
										string,
										new TypeToken<List<City_Wide_Classes_PopEntity>>() {
										}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							twolevel_id = json_list.get(i).getTwolevel_id();
							twolevel_name = json_list.get(i).getTwolevel_name();
							list1.add(new DoubleListviewBean(twolevel_id,
									twolevel_name));
						}
						handler.sendEmptyMessageDelayed(0, 10);
					}
				} catch (JSONException e) {

					e.printStackTrace();
				}
			}
			return null;
		}
	}

	private class LoadTask1 extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selecThreegoodscategory.do", "parent_id",
					params[0]);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						json_list = gson
								.fromJson(
										string,
										new TypeToken<List<City_Wide_Classes_PopEntity>>() {
										}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							twolevel_id = json_list.get(i).getTwolevel_id();
							twolevel_name = json_list.get(i).getTwolevel_name();
							list2.add(new DoubleListviewBean(twolevel_id,
									twolevel_name));
						}
						handler.sendEmptyMessageDelayed(1, 10);
					}
				} catch (JSONException e) {

					e.printStackTrace();
				}
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				adapter1 = new DoubleListviewAdapter1(activity, list1, "");
				listView1.setAdapter(adapter1);
				listView1.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (position == 0) {
							lister.send(list1.get(position).getRegion_name(),
									list1.get(position).getRegion_id(), "0");
							dismiss();
						}
						adapter1.isSelect(position);
						adapter1.notifyDataSetInvalidated();
						twolevel_id1 = list1.get(position).getRegion_id();
						name = list1.get(position).getRegion_name();
						list2 = new ArrayList<DoubleListviewBean>();
						list2.add(new DoubleListviewBean(twolevel_id1, "所有"+list1
								.get(position).getRegion_name()));
						new LoadTask1().executeOnExecutor(
								Executors.newCachedThreadPool(),
								list1.get(position).getRegion_id());
					}
				});
				break;
			case 1:
				adapter2 = new DoubleListviewAdapter2(activity, list2);
				listView2.setAdapter(adapter2);
				listView2.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (position == 0) {
							lister.send(name, twolevel_id1, "0");
							dismiss();
						} else {
							lister.send(list2.get(position)
									.getRegion_name(), twolevel_id1, list2.get(position)
									.getRegion_id());
							dismiss();
						}
					}
				});
				break;

			default:
				break;
			}
		};
	};

	public interface OnClassesWindowClickListener {
		void send(String name, String twolevel_id, String three_id);
	}

	public void setOnClassesWindowClickListener(
			OnClassesWindowClickListener lister) {
		this.lister = lister;
	}
}
