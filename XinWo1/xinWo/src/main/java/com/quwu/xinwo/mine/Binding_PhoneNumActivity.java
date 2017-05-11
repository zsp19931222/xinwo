package com.quwu.xinwo.mine;

import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.IsCheckPhoneNum;
import com.quwu.xinwo.until.MyCountDownTimer;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.MyTextWatcher;
import com.quwu.xinwo.until.Tool;

public class Binding_PhoneNumActivity extends SwipeBackActivity implements
		OnClickListener {

	private EditText phoneEd, verification_codeEdit;
	private LinearLayout phoneDeleteLin;
	private LinearLayout verification_codeDeleteLin;
	private TextView verification_codeText;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.binding_phonenum);
		FinishActivity.finish(R.id.binding_phonenumRel,
				Binding_PhoneNumActivity.this);
		findID();
	}

	private void findID() {
		phoneEd = (EditText) findViewById(R.id.binding_phonenum_phoneEdit);
		verification_codeEdit = (EditText) findViewById(R.id.binding_phonenum_verification_codeEdit);

		phoneDeleteLin = (LinearLayout) findViewById(R.id.binding_phonenum_phoneDeleteLin);
		verification_codeDeleteLin = (LinearLayout) findViewById(R.id.binding_phonenum_verification_codeDeleteLin);

		verification_codeText = (TextView) findViewById(R.id.binding_phonenum_verification_codeText);

		button = (Button) findViewById(R.id.binding_phonenum_confirmBtn);

		verification_codeEdit.addTextChangedListener(new MyTextWatcher(
				verification_codeEdit, 6, verification_codeDeleteLin));
		phoneEd.addTextChangedListener(new MyTextWatcher(phoneEd, 11,
				phoneDeleteLin));

		phoneDeleteLin.setOnClickListener(this);
		verification_codeDeleteLin.setOnClickListener(this);
		verification_codeText.setOnClickListener(this);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.binding_phonenum_phoneDeleteLin:// 清空手机号输入框
				phoneEd.setText("");
				break;
			case R.id.binding_phonenum_verification_codeDeleteLin:// 清空验证码输入框
				verification_codeEdit.setText("");
				break;
			case R.id.binding_phonenum_verification_codeText:// 获取验证码
				if (IsCheckPhoneNum.judgePhoneNums(phoneEd.getText().toString()
						.trim())) {
					MyCountDownTimer countDownTimer = new MyCountDownTimer(
							60000, 1000, verification_codeText);
					countDownTimer.start();
				} else {
					Toast.makeText(getApplicationContext(), "请输入正确的手机号", 10)
							.show();
				}
				break;
			case R.id.binding_phonenum_confirmBtn:// 确定按钮
				if (phoneEd.getText().toString().equals("")
						|| verification_codeEdit.getText().toString()
								.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入手机号或验证码", 10)
							.show();
				}else if (IsCheckPhoneNum.judgePhoneNums(phoneEd.getText().toString()
						.trim())) {
					Toast.makeText(getApplicationContext(), "请输入正确的手机号", 10)
					.show();
				} else if (verification_codeEdit.length()<6) {
					Toast.makeText(getApplicationContext(), "请输入6位验证码", 10)
					.show();
				}
				else {
					new ConfirmTask().executeOnExecutor(Executors
							.newCachedThreadPool(), phoneEd.getText()
							.toString());
				}
				break;

			default:
				break;
			}
		}
	}

	private class ConfirmTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			String result = OKHTTP_POST.doPost2(MyApp.base_address
					+ "usersaction/updusersphone.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),
					"phone", params[0]);
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
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		finish();
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
			default:
				break;
			}
		};
	};
}
