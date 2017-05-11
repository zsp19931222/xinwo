package com.quwu.xinwo.mine;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

public class Bind_AlipayActivity extends SwipeBackActivity implements
		OnClickListener {

	private final HttpClient httpclient1 = new DefaultHttpClient();
	private String user_name = "";
	private String ailpay = "";
	private String phone = "";
	private String code = "";

	private EditText bind_alipay_user_nameEd;
	private EditText bind_alipay_numEd;
	private EditText bind_alipay_phone_numEd;
	private EditText bind_alipay_verification_codeEd;

	private LinearLayout bind_alipay_user_nameDeleteLin;
	private LinearLayout bind_alipay_numDeleteLin;
	private LinearLayout bind_alipay_phone_numDeleteLin;
	private LinearLayout bind_alipay_verification_codeDeleteLin;

	private TextView bind_alipay_verification_codeText;

	private Button bind_alipay_confirmBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bind_alipay);
		FinishActivity.finish(R.id.bind_alipay_returnRel, this);
		findID();
	}

	private void findID() {
		bind_alipay_user_nameEd = (EditText) findViewById(R.id.bind_alipay_user_nameEd);
		bind_alipay_numEd = (EditText) findViewById(R.id.bind_alipay_numEd);
		bind_alipay_phone_numEd = (EditText) findViewById(R.id.bind_alipay_phone_numEd);
		bind_alipay_verification_codeEd = (EditText) findViewById(R.id.bind_alipay_verification_codeEd);

		InputFilterSpace.inputFilterSpace(bind_alipay_user_nameEd, 50);
		InputFilterSpace.inputFilterSpace(bind_alipay_numEd, 50);
		InputFilterSpace.inputFilterSpace(bind_alipay_phone_numEd, 11);
		InputFilterSpace.inputFilterSpace(bind_alipay_verification_codeEd, 6);

		bind_alipay_user_nameDeleteLin = (LinearLayout) findViewById(R.id.bind_alipay_user_nameDeleteLin);
		bind_alipay_numDeleteLin = (LinearLayout) findViewById(R.id.bind_alipay_numDeleteLin);
		bind_alipay_phone_numDeleteLin = (LinearLayout) findViewById(R.id.bind_alipay_phone_numDeleteLin);
		bind_alipay_verification_codeDeleteLin = (LinearLayout) findViewById(R.id.bind_alipay_verification_codeDeleteLin);

		bind_alipay_user_nameDeleteLin.setOnClickListener(this);
		bind_alipay_numDeleteLin.setOnClickListener(this);
		bind_alipay_phone_numDeleteLin.setOnClickListener(this);
		bind_alipay_verification_codeDeleteLin.setOnClickListener(this);

		bind_alipay_verification_codeText = (TextView) findViewById(R.id.bind_alipay_verification_codeText);
		bind_alipay_verification_codeText.setOnClickListener(this);

		bind_alipay_confirmBtn = (Button) findViewById(R.id.bind_alipay_confirmBtn);
		bind_alipay_confirmBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.bind_alipay_user_nameDeleteLin:
				bind_alipay_user_nameEd.setText("");
				break;
			case R.id.bind_alipay_numDeleteLin:
				bind_alipay_numEd.setText("");
				break;
			case R.id.bind_alipay_phone_numDeleteLin:
				bind_alipay_phone_numEd.setText("");
				break;
			case R.id.bind_alipay_verification_codeDeleteLin:
				bind_alipay_verification_codeEd.setText("");
				break;
			case R.id.bind_alipay_verification_codeText:
				phone = bind_alipay_phone_numEd.getText().toString().trim();
				if (IsCheckPhoneNum.judgePhoneNums(phone)) {
					if (bind_alipay_verification_codeText.getText().toString()
							.equals("获取验证码")) {
						new MyCountDownTimer(60000, 1,
								bind_alipay_verification_codeText).start();
						new Verification_codeTask().executeOnExecutor(Executors
								.newCachedThreadPool());
					}
				} else {
					MyToast.Toast(getApplicationContext(), "请输入正确的手机号!");
				}
				break;
			case R.id.bind_alipay_confirmBtn:
				user_name = bind_alipay_user_nameEd.getText().toString().trim();
				ailpay = bind_alipay_numEd.getText().toString().trim();
				phone = bind_alipay_phone_numEd.getText().toString().trim();
				code = bind_alipay_verification_codeEd.getText().toString()
						.trim();
				if (user_name.equals("")) {
					MyToast.Toast(getApplicationContext(), "请输入用户名！");
				} else if (ailpay.equals("")) {
					MyToast.Toast(getApplicationContext(), "请输入支付宝账号！");
				} else if (!IsCheckPhoneNum.judgePhoneNums(phone)) {
					MyToast.Toast(getApplicationContext(), "请输入正确的手机号！");
				} else if (code.equals("")) {
					MyToast.Toast(getApplicationContext(), "请输入验证码！");
				} else if (code.length() < 6) {
					MyToast.Toast(getApplicationContext(), "请输入6位验证码！");
				} else {
					new ConfirmTask().executeOnExecutor(Executors
							.newCachedThreadPool());
				}
				break;

			default:
				break;
			}
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
						+ "myAccount/getVerificationCode.do", "phone", phone,
						"", "", "", "", "", "", "", "", "", "", "", "", "", "");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("发送验证码成功")) {
						handler.sendMessage(handler.obtainMessage(1,
								"验证码获取成功，\n将在30秒内发送到您的手机。"));
					} else if (string.equals("发送验证码失败")) {
						handler.sendMessage(handler.obtainMessage(1,
								"验证码获取失败，\n请稍后再试。"));
					} else if (string.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1,
								"服务器异常，请稍后再试。"));
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

		@Override
		protected Void doInBackground(Void... params) {
			String string=null;
			try {
				string = Login(MyApp.base_address
						+ "myAccount/addAccount.do", "transaction_name", user_name,
						"transaction_num", ailpay, "transaction_class", "1",
						"transaction_phone", phone, "account_name", "支付宝",
						"user_id",
						MySharePreferences.GetUser_ID(Bind_AlipayActivity.this),
						"code", code, "bank_card", "支付宝");
				System.out.println(code);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("添加账户失败")) {
						handler.sendMessage(handler.obtainMessage(1,
								"添加账户失败，请稍后再试。"));
					} else if (string2.equals("验证码不正确")) {
						handler.sendMessage(handler.obtainMessage(1,
								"验证码不正确，请核对后再试。"));
					} else if (string2.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1,
								"服务器异常，请稍后再试。"));
					} else if (string2.equals("添加账户成功")) {
						handler.sendMessage(handler.obtainMessage(1, "添加账户成功"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
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
				if (string.equals("添加账户成功")) {
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
			String id2, String canshu3, String id3, String canshu4, String id4
			,String canshu5, String id5
			,String canshu6, String id6
			,String canshu7, String id7
			,String canshu8, String id8
			) throws UnsupportedEncodingException {
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
		StringBody type6 = new StringBody(id6);
		reqEntity.addPart(canshu6, type6);
		StringBody type7 = new StringBody(id7);
		reqEntity.addPart(canshu7, type7);
		StringBody type8= new StringBody(id8);
		reqEntity.addPart(canshu8, type8);
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
