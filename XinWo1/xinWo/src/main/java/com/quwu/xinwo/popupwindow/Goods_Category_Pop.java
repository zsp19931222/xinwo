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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.City_wide_classify_popAdapter;
import com.quwu.xinwo.adapter.Goods_Category_popAdapter;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Goods_Category_PopEntity;
import com.quwu.xinwo.okhttp.OKHTTP_POST;

public class Goods_Category_Pop extends PopupWindow {
	private View view;
	private LayoutInflater inflater;
	private Goods_Category_popAdapter adapter;
	private ListView listView;

	private String twolevel_id;// 分类ID
	private String twolevel_name;// 分类名称
	private String images_url;// 图片路径
	private List<Goods_Category_PopEntity> json_list;
	private List<Goods_Category_PopEntity> list;

	private Activity activity;

	public Goods_Category_Pop(final Activity activity) {
		this.activity = activity;
		inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.goods_category_pop, null);
		listView = (ListView) view
				.findViewById(R.id.goods_category_pop_listview);
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
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.CENTER_HORIZONTAL,
					Gravity.CENTER_HORIZONTAL, Gravity.CENTER_HORIZONTAL);
			view.setAlpha(0.95f);
		} else {
			this.dismiss();
			view.setAlpha(1f);
		}
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			list = new ArrayList<Goods_Category_PopEntity>();
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
										new TypeToken<List<Goods_Category_PopEntity>>() {
										}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							twolevel_id = json_list.get(i).getTwolevel_id();
							twolevel_name = json_list.get(i).getTwolevel_name();
							images_url = json_list.get(i).getImages_url();
							list.add(new Goods_Category_PopEntity(twolevel_id,
									twolevel_name, images_url));
						}
						handler.sendEmptyMessageDelayed(0, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
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
				adapter = new Goods_Category_popAdapter(activity, list);
				listView.setAdapter(adapter);
				break;

			default:
				break;
			}
		};
	};
}
