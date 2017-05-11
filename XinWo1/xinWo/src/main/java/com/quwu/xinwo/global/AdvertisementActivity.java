package com.quwu.xinwo.global;

import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.gson_entity.AdvertisementEntity;
import com.quwu.xinwo.gson_entity.HP_BannerEntity;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.main.Main_Activity;
import com.quwu.xinwo.okhttp.OKHTTP_POST;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertisementActivity extends Activity {

	private ImageView imageView;
	private TextView textView;

	private String cover_time;// 广告时间
	private String cover_url;// 广告链接
	private String cover_pictrue;// 广告图片
	private List<AdvertisementEntity> json_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advertisement);
		imageView = (ImageView) findViewById(R.id.advertisement_Image);
		textView = (TextView) findViewById(R.id.advertisement_Text);
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdvertisementActivity.this,
						Main_Activity.class);
				startActivity(intent);
			}
		});
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "coveraction/cover.do", "", "");
			if (string != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(string);
					String result1 = jsonObject.getString("1");
					if (result1 != null) {
						if (result1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (result1.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(result1,
									new TypeToken<List<AdvertisementEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								cover_time = json_list.get(i).getCover_time();
								cover_url = json_list.get(i).getCover_url();
								cover_pictrue = json_list.get(i)
										.getCover_pictrue();
								handler.sendEmptyMessageDelayed(0, 10);
							}
						}
						handler.sendEmptyMessageAtTime(0, 10);
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				ImageloaderUtils.MyImageLoader3(MyApp.base_address
						+ cover_pictrue, imageView, AdvertisementActivity.this);
				break;

			default:
				break;
			}
		};
	};
}
