package com.quwu.xinwo.mine;

import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.InputFilterSpace;
import com.quwu.xinwo.until.IsCheckPhoneNum;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;

public class Add_BankCardActivity extends Activity implements OnClickListener {

	private EditText nameEd;
	private EditText cardNumEd;
	private TextView cardText;
	private EditText phoneNumEd;

	private LinearLayout nameDeleteLin;
	private LinearLayout cardNumDeleteLin;
	private LinearLayout phoneNumDeleteLin;

	private LinearLayout add_bankcard_nameLin;
	private LinearLayout add_bankcard_phoneLin;

	private Button nextBtn;
	private RelativeLayout finishRel;

	private String bank_name;// 银行名称
	private String bank_card;// 银行名称

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_bankcard);
		findID();
	}

	private void findID() {
		nameEd = (EditText) findViewById(R.id.add_bankcard_nameEd);
		cardNumEd = (EditText) findViewById(R.id.add_bankcard_cardEd);
		phoneNumEd = (EditText) findViewById(R.id.add_bankcard_phone_numEd);
		cardText = (TextView) findViewById(R.id.add_bankcard_bankNameText);

		InputFilterSpace.inputFilterSpace(nameEd, 10);
		InputFilterSpace.inputFilterSpace(phoneNumEd, 11);

		nameDeleteLin = (LinearLayout) findViewById(R.id.add_bankcard_nameDeleteLin);
		cardNumDeleteLin = (LinearLayout) findViewById(R.id.add_bankcard_cardDeleteLin);
		phoneNumDeleteLin = (LinearLayout) findViewById(R.id.add_bankcard_phone_numDeleteLin);
		nameDeleteLin.setOnClickListener(this);
		cardNumDeleteLin.setOnClickListener(this);
		phoneNumDeleteLin.setOnClickListener(this);

		add_bankcard_nameLin = (LinearLayout) findViewById(R.id.add_bankcard_nameLin);
		add_bankcard_phoneLin = (LinearLayout) findViewById(R.id.add_bankcard_phoneLin);

		nextBtn = (Button) findViewById(R.id.add_bankcard_nextBtn);
		nextBtn.setOnClickListener(this);
		finishRel = (RelativeLayout) findViewById(R.id.add_bankcard_returnRel);
		finishRel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.add_bankcard_returnRel:
				if (nextBtn.getText().toString().equals("下一步")) {
					finish();
				} else {
					nextBtn.setText("下一步");
					add_bankcard_nameLin.setVisibility(View.VISIBLE);
					add_bankcard_phoneLin.setVisibility(View.GONE);
				}
				break;
			case R.id.add_bankcard_nextBtn:
				if (nextBtn.getText().toString().equals("下一步")) {
					if (nameEd.getText().toString().equals("")) {
						MyToast.Toast(Add_BankCardActivity.this, "请填写您的姓名！");
					} else if (cardNumEd.getText().toString().equals("")) {
						MyToast.Toast(Add_BankCardActivity.this, "请填写您的银行卡号！");
					} else {
						new IsCheckBank().executeOnExecutor(Executors
								.newCachedThreadPool());
					}
				} else if (nextBtn.getText().toString().equals("确定")) {
					if (phoneNumEd.getText().toString().equals("")) {
						MyToast.Toast(Add_BankCardActivity.this, "请填写您的手机号！");
					}else if (!IsCheckPhoneNum.judgePhoneNums(phoneNumEd.getText().toString())) {
						MyToast.Toast(Add_BankCardActivity.this, "请填写正确的手机号！");
					}else {
						new ConfirmTask().executeOnExecutor(Executors
								.newCachedThreadPool());
					}
				}
				break;
			case R.id.add_bankcard_nameDeleteLin:
				nameEd.setText("");
				break;
			case R.id.add_bankcard_cardDeleteLin:
				cardNumEd.setText("");
				break;
			case R.id.add_bankcard_phone_numDeleteLin:
				phoneNumEd.setText("");
				break;

			default:
				break;
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (nextBtn.getText().toString().equals("下一步")) {
				finish();
			} else {
				nextBtn.setText("下一步");
				add_bankcard_nameLin.setVisibility(View.VISIBLE);
				add_bankcard_phoneLin.setVisibility(View.GONE);
			}
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/**
	 * 根据银行卡前6位数字判断所属银行
	 * */
	private class IsCheckBank extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String s = (String) cardNumEd.getText().toString().trim()
					.subSequence(0, 7);
			String[] province_strings = s.split("");
			StringBuffer province_sb = new StringBuffer(256);
			for (int i = 0; i < province_strings.length; i++) {
				if (province_strings[i].equals(" ")) {
					province_strings[i] = "";
				}
				province_sb.append(province_strings[i]);
			}
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "myAccount/bankChina.do", "bin_number",
					province_sb.toString());
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("未查到该数字开头的银行")) {
						handler.sendMessage(handler.obtainMessage(1,
								"未查到该卡号所对应的银行，请核查之后重新输入。"));
					} else if (string2.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1,
								"服务器异常，请稍后再试。"));
					} else {
						JSONArray array = new JSONArray(string2);
						for (int i = 0; i < array.length(); i++) {
							JSONObject jsonObject2 = array.getJSONObject(i);
							bank_name = jsonObject2.getString("bank_name");
							bank_card = jsonObject2.getString("bank_card");
							handler.sendMessage(handler.obtainMessage(2,
									bank_name));
						}
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
			case 2:
				cardText.setText(bank_name);
				nextBtn.setText("确定");
				add_bankcard_nameLin.setVisibility(View.GONE);
				add_bankcard_phoneLin.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 确定
	 * */
	private class ConfirmTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String string = OKHTTP_POST.doPost8(MyApp.base_address
					+ "myAccount/addAccount.do", "transaction_name", nameEd
					.getText().toString(), "transaction_num", cardNumEd
					.getText().toString(), "transaction_class", "2",
					"transaction_phone", phoneNumEd.getText().toString(),
					"account_name", bank_name, "user_id", MySharePreferences
							.GetUser_ID(Add_BankCardActivity.this), "code", "",
					"bank_card", bank_card);
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("添加账户失败")) {
						handler.sendMessage(handler.obtainMessage(1,
								"添加账户失败，请稍后再试。"));
					} else if (string2.equals("不支持该提现方式")) {
						handler.sendMessage(handler.obtainMessage(1,
								"不支持该提现方式。"));
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
}
