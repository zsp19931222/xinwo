package com.quwu.xinwo.mine;

import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;

public class Change_SexActivity extends Activity implements OnClickListener {
	private TextView boy, girl, secrecy;
	private Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_sex);
		FinishActivity.finish(R.id.change_sex_Lin, this);
		findID();
	}

	private void findID() {
		boy = (TextView) findViewById(R.id.change_sex_boyText);
		girl = (TextView) findViewById(R.id.change_sex_girlText);
		secrecy = (TextView) findViewById(R.id.change_sex_secrecyText);
		boy.setOnClickListener(this);
		girl.setOnClickListener(this);
		secrecy.setOnClickListener(this);
		cancel = (Button) findViewById(R.id.change_sex_cancelBtn);
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
	}

	private class SexTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			String result = OKHTTP_POST.doPost2(MyApp.base_address
					+ "usersaction/upduserssex.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),
					"sex", params[0]);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else {
						handler.sendMessage(handler.obtainMessage(0, string));
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
				String string = (String) msg.obj;
				MyToast.Toast(getApplicationContext(), string);
				finish();
			default:
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_sex_boyText:
			new SexTask().executeOnExecutor(Executors.newCachedThreadPool(),
					"1");
			break;
		case R.id.change_sex_girlText:
			new SexTask().executeOnExecutor(Executors.newCachedThreadPool(),
					"2");
			break;
		case R.id.change_sex_secrecyText:
			new SexTask().executeOnExecutor(Executors.newCachedThreadPool(),
					"0");
			break;

		default:
			break;
		}
	}
}
