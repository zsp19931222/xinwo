package com.quwu.xinwo.log_in;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.InputFilterSpace;
import com.quwu.xinwo.until.IsCheckPhoneNum;
import com.quwu.xinwo.until.MyCountDownTimer;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;

public class RegisterActivity extends SwipeBackActivity implements
		OnClickListener {

	private LinearLayout linearLayout;

	private EditText register_phoneEdit;// 手机号输入框
	private EditText register_verification_codeEdit;// 验证码输入框
	private EditText register_passwordEd;// 密码输入框

	private TextView register_verification_codeText;// 获取验证码
	private CheckBox register_passwordBox;// 是否显示输入的密码
	private LinearLayout register_passwordBoxLin;// 是否显示输入的密码
	private Button register_confirmBtn;// 确定按钮

	private LinearLayout register_phoneDeleteLin;// 删除手机号按钮
	private LinearLayout register_verification_codeDeleteLin;// 删除验证码按钮
	private LinearLayout register_passwordDeleteLin;// 删除验证码按钮

	private int box = 0;// 判断是否显示
	private final HttpClient httpclient1 = new DefaultHttpClient();

	/**
	 * 需要传入的参数
	 * */
	private String phone = "";// 接收验证码的手机号码
	private String regcode = "";// 验证码
	private String password = "";// 密码
	private String user_ip =Tool.getPhoneIp();// 用户IP
	private String reg_machine =Tool.getInfo();// 注册设备

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		FinishActivity.finish(R.id.registerRel, RegisterActivity.this);
		findID();
	}

	private void findID() {
		linearLayout = (LinearLayout) findViewById(R.id.registerLin);
		register_phoneEdit = (EditText) findViewById(R.id.register_phoneEdit);
		register_verification_codeEdit = (EditText) findViewById(R.id.register_verification_codeEdit);
		register_verification_codeText = (TextView) findViewById(R.id.register_verification_codeText);
		register_passwordEd = (EditText) findViewById(R.id.register_passwordEd);
		register_passwordBox = (CheckBox) findViewById(R.id.register_passwordBox);
		register_confirmBtn = (Button) findViewById(R.id.register_confirmBtn);
		register_phoneDeleteLin = (LinearLayout) findViewById(R.id.register_phoneDeleteLin);
		register_verification_codeDeleteLin = (LinearLayout) findViewById(R.id.register_verification_codeDeleteLin);
		register_passwordDeleteLin = (LinearLayout) findViewById(R.id.register_passwordDeleteLin);

		register_passwordBoxLin = (LinearLayout) findViewById(R.id.register_passwordBoxLin);

		register_verification_codeText.setOnClickListener(this);
		register_confirmBtn.setOnClickListener(this);
		register_passwordBoxLin.setOnClickListener(this);
		register_phoneDeleteLin.setOnClickListener(this);
		register_verification_codeDeleteLin.setOnClickListener(this);
		register_passwordDeleteLin.setOnClickListener(this);

		InputFilterSpace.inputFilterSpace(register_phoneEdit, 11);
		InputFilterSpace.inputFilterSpace(register_verification_codeEdit, 6);
		InputFilterSpace.inputFilterSpace(register_passwordEd, 15);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_verification_codeText:// 获取验证码
			phone = register_phoneEdit.getText().toString().trim();
			if (IsCheckPhoneNum.judgePhoneNums(phone)) {
				if (register_verification_codeText.getText().toString()
						.equals("获取验证码")) {
					new MyCountDownTimer(60000, 1,
							register_verification_codeText).start();
					new Verification_codeTask().executeOnExecutor(Executors
							.newCachedThreadPool());
				}
			} else {
				MyToast.Toast(getApplicationContext(), "请输入正确的手机号!");
			}
			break;
		case R.id.register_confirmBtn:// 确定
			regcode = register_verification_codeEdit.getText().toString()
					.trim();
			password = register_passwordEd.getText().toString().trim();
			if (!IsCheckPhoneNum.judgePhoneNums(phone)) {
				MyToast.Toast(getApplicationContext(), "请输入正确的手机号码！");
			} else if (regcode.equals("")) {
				MyToast.Toast(getApplicationContext(), "请输入验证码！");
			} else if (regcode.length() < 6) {
				MyToast.Toast(getApplicationContext(), "请输入6位验证码！");
			} else if (password.equals("")) {
				MyToast.Toast(getApplicationContext(), "请输入密码！");
			} else if (password.length() < 8) {
				MyToast.Toast(getApplicationContext(), "请输入8-15位密码！");
			} else {
				new ConfirmTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
			break;
		case R.id.register_phoneDeleteLin:// 清空手机号
			register_phoneEdit.setText("");
			break;
		case R.id.register_verification_codeDeleteLin:// 清空验证码
			register_verification_codeEdit.setText("");
			break;
		case R.id.register_passwordDeleteLin:// 清空密码
			register_passwordEd.setText("");
			break;
		case R.id.register_passwordBoxLin:// 显示密码
			if (box % 2 == 0) {
				register_passwordBox.setChecked(false);
			} else {
				register_passwordBox.setChecked(true);
			}
			box++;
			if (register_passwordBox.isChecked()) {
				// 设置为明文显示
				RegisterActivity.this.register_passwordEd
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
			} else {
				// 设置为秘闻显示
				RegisterActivity.this.register_passwordEd
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 获取验证码
	 * */
	private class Verification_codeTask extends AsyncTask<Void, Void, Void> {
		String result;
		@Override
		protected Void doInBackground(Void... params) {
			try {
				result = Login(MyApp.base_address
						+ "loginandReg/getVerificationCode.do", "phone", phone,
						"", "", "", "", "", "", "", "");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("获取验证码成功")) {
						handler.sendMessage(handler.obtainMessage(1, "验证码获取成功，\n将在30秒内发送到您的手机。"));
					} else if (string.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1, "服务器异常，请稍后再试。"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendMessage(handler.obtainMessage(1, "服务器异常，请稍后再试。"));
			}
			return null;
		}
	}

	/**
	 * 确定
	 * */
	private class ConfirmTask extends AsyncTask<Void, Void, Void> {
		String result;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				result = Login(MyApp.base_address + "loginandReg/regphone.do",
						"phone", phone, "regcode", regcode, "password",
						password, "user_ip", user_ip, "reg_machine",
						reg_machine);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("注册失败")) {
						handler.sendMessage(handler.obtainMessage(1, string));
					} else if (string.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1,
								"服务器异常，请稍后重试。"));
					} else if (string.equals("该手机号码已经被注册过")) {
						handler.sendMessage(handler.obtainMessage(1,
								"该手机号码已经被注册过,请重新选择手机号。"));
					} else if (string.equals("验证码不正确")) {
						handler.sendMessage(handler.obtainMessage(1, string));
					} else if (string.equals("获取参数失败")) {
						handler.sendMessage(handler.obtainMessage(1, string));
					} else {
						handler.sendMessage(handler.obtainMessage(1, "注册成功"));
						MySharePreferences.Put(string, "0",
								RegisterActivity.this);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendMessage(handler.obtainMessage(1, "服务器异常，请稍后再试。"));
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				String string = (String) msg.obj;
				if (string.equals("注册成功")) {
					MyToast.Toast(getApplicationContext(), "注册成功");
					finish();
				}
				MyToast.Toast(getApplicationContext(), string);
				break;

			default:
				break;
			}
		};
	};

	public String Login(String uri, String canshu1, String id1, String canshu2,
			String id2, String canshu3, String id3, String canshu4, String id4,
			String canshu5, String id5) throws UnsupportedEncodingException {
		String result = null;
		HttpPost httpPost = new HttpPost(uri);
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();
		StringBody type1 = new StringBody(id1);
		reqEntity.addPart(canshu1, type1);
		StringBody type2 = new StringBody(id2);
		reqEntity.addPart(canshu2, type2);
		StringBody type3 = new StringBody(id3);
		reqEntity.addPart(canshu3, type3);
		StringBody type4 = new StringBody(id4);
		reqEntity.addPart(canshu4, type4);
		StringBody type5 = new StringBody(id5);
		reqEntity.addPart(canshu5, type5);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		// HttpClient httpclient = new DefaultHttpClient();
		// 请求超时
		httpclient1.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
		// 读取超时
		httpclient1.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				1000);

		// 取得HttpResponse
		if (httpPost != null) {
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpclient1.execute(httpPost);
			} catch (ClientProtocolException e) {
				// if (URLUtils.getUrl().equals(URLUtils.url1)) {
				// URLUtils.setUrl(URLUtils.url2);
				// } else {
				// URLUtils.setUrl(URLUtils.url1);
				// }
				e.printStackTrace();
			} catch (IOException e) {
				// if (URLUtils.getUrl().equals(URLUtils.url1)) {
				// URLUtils.setUrl(URLUtils.url2);
				// } else {
				// URLUtils.setUrl(URLUtils.url1);
				// }
				e.printStackTrace();
			}
			// HttpStatus.SC_OK表示连接成功
			if (httpResponse != null
					&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				try {
					result = EntityUtils.toString(httpResponse.getEntity());
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
			}
		}
		return result;
	}
}
