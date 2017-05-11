package com.quwu.xinwo.mine;

import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.WelfareEntity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;

public class WelfareActivity extends SwipeBackActivity implements
		OnClickListener {

	private Button sign_inBtn;
	private TextView at_presentText, todayText, totalText;
	private LinearLayout inviteLin, conversionLin;

	private List<WelfareEntity> json_list;
	private String total_integral;// 累计收获（int类型）
	private String integral;// 当前积分（int类型）
	private String sign_num;// 签到收获（int类型）
	private String sign_num1;// 今日收获（int类型）

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welfare);
		FinishActivity.finish(R.id.welfareRel, WelfareActivity.this);
		findID();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {

		sign_inBtn = (Button) findViewById(R.id.welfare_sign_inBtn);

		at_presentText = (TextView) findViewById(R.id.welfare_at_presentText);
		todayText = (TextView) findViewById(R.id.welfare_todayText);
		totalText = (TextView) findViewById(R.id.welfare_totalText);

		inviteLin = (LinearLayout) findViewById(R.id.welfare_inviteLin);
		conversionLin = (LinearLayout) findViewById(R.id.welfare_conversionLin);
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... params) {
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "usersaction/welfare.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()));
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
									new TypeToken<List<WelfareEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								total_integral = json_list.get(i)
										.getTotal_integral();
								integral = json_list.get(i).getIntegral();
								sign_num = json_list.get(i).getSign_num();
								sign_num1 = json_list.get(i).getSign_num1();
							}
							handler.sendEmptyMessageAtTime(0, 10);
						}
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
			case 10000:
				MyToast.Toast(getApplicationContext(), "服务器异常，正在处理中。。。");
				sign_inBtn.setText("无法签到");
				sign_inBtn.setTextColor(getResources().getColor(R.color.黑字体色));
				sign_inBtn.setBackgroundResource(R.drawable.publicbtn_gary_bg);
				break;
			case 10001:

				break;
			case 0:
				todayText.setText(sign_num1);
				totalText.setText(total_integral);
				at_presentText.setText(integral);
				if (!sign_num.equals("0")) {
					sign_inBtn.setText("已签到");
					sign_inBtn.setTextColor(getResources().getColor(
							R.color.黑字体色));
					sign_inBtn
							.setBackgroundResource(R.drawable.publicbtn_gary_bg);
				} else {
					sign_inBtn.setOnClickListener(WelfareActivity.this);
				}
				break;
			case 1:
				MyToast.Toast(getApplicationContext(), "签到成功");
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			case 2:
				MyToast.Toast(getApplicationContext(), "签到失败，请重试");
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			case 3:
				MyToast.Toast(getApplicationContext(), "你已经签到过了哦~");
				new LoadTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			default:
				break;
			}
		};
	};

	private class Sign_inTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "usersaction/usersign.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()));
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
						} else if (result1.equals("签到成功")) {
							handler.sendEmptyMessageAtTime(1, 10);
						} else if (result1.equals("签到失败")) {
							handler.sendEmptyMessageAtTime(2, 10);
						} else if (result1.equals("你已经签到过了")) {
							handler.sendEmptyMessageAtTime(3, 10);
						}
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.welfare_sign_inBtn:
			new Sign_inTask()
					.executeOnExecutor(Executors.newCachedThreadPool());
			break;

		default:
			break;
		}
	}
}
