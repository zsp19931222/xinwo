package com.quwu.xinwo.release;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Area_selection_Adapter;
import com.quwu.xinwo.bean.Area_selection_Bean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Area_selection_Entity;
import com.quwu.xinwo.until.HttpPostUnit;

public class Area_selection1Activity extends Activity {
	private List<Area_selection_Bean> list;
	private Area_selection_Adapter adapter;
	private ListView listView;
	private TextView sheng, shi;
	
	private String id1,id2;

	private String twolevel_id;// 分类ID
	private String twolevel_name;// 分类名称
	private String images_url;// 图片路径
	private int start = 0;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.area_selection);
		new Task().execute();
		this.setFinishOnTouchOutside(true);
		listView = (ListView) findViewById(R.id.area_selection_listview);
		sheng = (TextView) findViewById(R.id.area_selection_sheng);
		shi = (TextView) findViewById(R.id.area_selection_shi);
	}

	private class Task extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... params) {
			list = new ArrayList<Area_selection_Bean>();
			// String string = HttpGetUnit.HttpClientget(URLUtils.url
			// + "region_selectRegion");
			String string = null;
			string = HttpPostUnit.otherHttpPostString(MyApp.base_address
					+ "lableaction/selectgoodscategory.do", "", "");
			System.out.println("string=" + string);
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String result = jsonObject.getString("1");
					if (result.equals("当前还没有数据")) {

					} else if (result.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						List<Area_selection_Entity> ps = gson.fromJson(result,
								new TypeToken<List<Area_selection_Entity>>() {
								}.getType());
						for (int i = 0; i < ps.size(); i++) {
							twolevel_id = ps.get(i).getTwolevel_id();
							twolevel_name = ps.get(i).getTwolevel_name();
							images_url = ps.get(i).getImages_url();
							list.add(new Area_selection_Bean(twolevel_id,
									twolevel_name, images_url));
						}
						handler.sendEmptyMessageDelayed(2, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// Toast.makeText(Area_selection1Activity.this, "网络异常",
				// Toast.LENGTH_SHORT).show();
				break;
			case 2:
				adapter = new Area_selection_Adapter(list,
						getApplicationContext());
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						if (start == 0 && list.size() != 0) {
							start++;
							sheng.setText(list.get(arg2).getTwolevel_name());
							id1=list.get(arg2).getTwolevel_id();
							new Task1()
									.execute(list.get(arg2).getTwolevel_id());
						} else if (start == 1 && list.size() != 0) {
							shi.setText(list.get(arg2).getTwolevel_name());
							id2=list.get(arg2).getTwolevel_id();
							Intent data = new Intent(
									Area_selection1Activity.this,
									Release_Activity.class);
							data.putExtra("sheng", sheng.getText().toString()
									.trim());
							data.putExtra("shi", shi.getText().toString()
									.trim());
							data.putExtra("id1", id1);
							data.putExtra("id2", id2);
							setResult(20, data);
							finish();
						}
					}
				});
				break;
			default:
				break;
			}
		};
	};

	private class Task1 extends AsyncTask<String, String, Void> {
		protected Void doInBackground(String... params) {
			Message message = new Message();
			list = new ArrayList<Area_selection_Bean>();
			String string = null;
			string = HttpPostUnit.otherHttpPostString(MyApp.base_address
					+ "lableaction/selecThreegoodscategory.do", "parent_id",
					params[0]);
			System.out.println("string=" + string);
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String result = jsonObject.getString("1");
					if (result.equals("当前还没有数据")) {
					} else if (result.equals("程序异常")) {
					} else {
						Gson gson = new Gson();
						List<Area_selection_Entity> ps = gson.fromJson(result,
								new TypeToken<List<Area_selection_Entity>>() {
								}.getType());
						for (int i = 0; i < ps.size(); i++) {
							twolevel_id = ps.get(i).getTwolevel_id();
							twolevel_name = ps.get(i).getTwolevel_name();
							list.add(new Area_selection_Bean(twolevel_id,
									twolevel_name, ""));
							message.what = 2;
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				message.what = 1;
			}
			handler.sendMessage(message);
			return null;
		}
	}
}
