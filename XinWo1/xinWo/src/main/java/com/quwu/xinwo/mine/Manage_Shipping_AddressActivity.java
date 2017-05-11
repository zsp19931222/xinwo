package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Manage_Shipping_AddressAdapter;
import com.quwu.xinwo.bean.Manage_Shipping_AddressBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Manage_Shipping_AddressEntity;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;

public class Manage_Shipping_AddressActivity extends SwipeBackActivity {

	private MyListView listView;
	private Button confirm;
	private Manage_Shipping_AddressAdapter adapter;
	private List<Manage_Shipping_AddressBean> list;

	private String address_id;// 地址标示id(这里没有用到，删除地址或编辑地址会用到)
	private String user_id;// 用户id
	private String receipt_address;// 地区
	private String receipt_name;// 收货人
	private String receipt_phone;// 电话号码
	private String receipt_area;// 详细地址
	private String state;// 是否是默认地址（0=默认，1=非默认）
	private List<Manage_Shipping_AddressEntity> json_list;

	private int j;// 判断谁被点击了

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_shipping_address);
		FinishActivity.finish(R.id.manage_shipping_address_returnRel,
				Manage_Shipping_AddressActivity.this);
		findID();

	}

	@Override
	protected void onResume() {
		super.onResume();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		listView = (MyListView) findViewById(R.id.manage_shipping_address_listview);
		confirm = (Button) findViewById(R.id.manage_shipping_address_confirmBtn);
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Manage_Shipping_AddressActivity.this,
						Add_Shipping_AddressActivity.class);
				intent.putExtra("state", "0");
				startActivity(intent);
			}
		});
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			list = new ArrayList<Manage_Shipping_AddressBean>();
			String result = OKHTTP_POST
					.doPost1(MyApp.base_address
							+ "usersaction/receiptaddress.do", "user_id",
							MySharePreferences.GetUser_ID(getApplicationContext()));
			Log.e("", result);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else {
						Gson gson = new Gson();
						json_list = gson
								.fromJson(
										string,
										new TypeToken<List<Manage_Shipping_AddressEntity>>() {
										}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							address_id = json_list.get(i).getAddress_id();
							user_id = json_list.get(i).getUser_id();
							receipt_address = json_list.get(i)
									.getReceipt_address();
							receipt_name = json_list.get(i).getReceipt_name();
							receipt_phone = json_list.get(i).getReceipt_phone();
							receipt_area = json_list.get(i).getReceipt_area();
							state = json_list.get(i).getState();
							if (state.equals("0")) {
								list.add(new Manage_Shipping_AddressBean(
										receipt_name, receipt_phone,
										receipt_address, true, address_id,
										user_id,receipt_area));
							} else {
								list.add(new Manage_Shipping_AddressBean(
										receipt_name, receipt_phone,
										receipt_address, false, address_id,
										user_id,receipt_area));
							}
						}
						handler.sendEmptyMessageDelayed(0, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
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
			case 10000:
				MyToast.Toast(getApplicationContext(),
						MyApp.Program_Exception_Prompt);
				break;
			case 10001:
				MyToast.Toast(getApplicationContext(), MyApp.NODATA_Prompt);
				break;
			case 0:
				adapter = new Manage_Shipping_AddressAdapter(
						getApplicationContext(), list,
						Manage_Shipping_AddressActivity.this);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						new ChangeTask().executeOnExecutor(Executors
								.newCachedThreadPool(), list.get(arg2)
								.getAddress_id());
						j = arg2;
					}
				});
			case 2:
				// Log.e("","进来了");
				// new LoadTask().executeOnExecutor(Executors
				// .newCachedThreadPool());
				break;
			default:
				break;
			}
		};
	};

	private class ChangeTask extends AsyncTask<String, Void, Void> {
		String string;

		@Override
		protected Void doInBackground(String... params) {
			String result = OKHTTP_POST.doPost3(MyApp.base_address
					+ "usersaction/updaddressstate.do", "address_id",
					params[0], "state", "0", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()));
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					string = jsonObject.getString("1");
					if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else if (string.equals("修改失败")) {
						handler.sendMessage(handler.obtainMessage(0, string));
					} else {
						handler.sendEmptyMessageDelayed(2, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (string != null) {
				if (string.equals("修改成功")) {
					for (int i = 0; i < list.size(); i++) {
						list.get(i).setChecked(false);
					}
					list.get(j).setChecked(true);
					adapter.notifyDataSetChanged();
				}
			}
		}
	}
}
