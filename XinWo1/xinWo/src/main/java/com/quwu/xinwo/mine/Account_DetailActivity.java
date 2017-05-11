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
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class Account_DetailActivity extends SwipeBackActivity {

	private TextView account_detail_Text1;
	private TextView account_detail_Text2;
	private TextView account_detail_Text3;
	private Button account_detail_deleteBtn;

	private String account_name;
	private String transaction_id;
	private String transaction_num;
	private String transaction_phone;
	private String transaction_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_detail);
		FinishActivity.finish(R.id.account_detail_returnRel, this);
		findID();
	}

	private void findID() {
		account_detail_Text1 = (TextView) findViewById(R.id.account_detail_Text1);
		account_detail_Text2 = (TextView) findViewById(R.id.account_detail_Text2);
		account_detail_Text3 = (TextView) findViewById(R.id.account_detail_Text3);
		account_detail_deleteBtn = (Button) findViewById(R.id.account_detail_deleteBtn);
		account_name = this.getIntent().getExtras().getString("account_name");
		transaction_id = this.getIntent().getExtras()
				.getString("transaction_id");
		transaction_num = this.getIntent().getExtras()
				.getString("transaction_num");
		transaction_phone = this.getIntent().getExtras()
				.getString("transaction_phone");
		transaction_name = this.getIntent().getExtras()
				.getString("transaction_name");
		if (account_name.equals("支付宝")) {
			account_detail_Text1.setText("支付宝账户");
			account_detail_Text2.setText("真实姓名：" + transaction_name);
			account_detail_Text3.setText("支付宝账户：" + transaction_num);
		} else {
			account_detail_Text1.setText(account_name + "（" + transaction_num
					+ "）");
			account_detail_Text2.setText("持卡人姓名：" + transaction_name);
			account_detail_Text3.setText("银行预留手机号：" + transaction_phone);
		}
		account_detail_deleteBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				new DeleteTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			}
		});
	}

	private class DeleteTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "myAccount/removebinding.do", "transaction_id",
					transaction_id);
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(0,
								MyApp.Program_Exception_Prompt));
					} else if (string2.equals("解绑成功")) {
						handler.sendMessage(handler.obtainMessage(0, "解绑成功"));
					} else if (string2.equals("解绑失败")) {
						handler.sendMessage(handler.obtainMessage(0,
								"解绑失败，请稍后再试。"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendMessage(handler.obtainMessage(0,
						MyApp.Program_Exception_Prompt));
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				String string = (String) msg.obj;
				MyToast.Toast(Account_DetailActivity.this, string);
				if (string.equals("解绑成功")) {
					finish();
				}
				break;

			default:
				break;
			}
		};
	};
}
