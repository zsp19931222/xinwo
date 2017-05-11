package com.quwu.xinwo.mine;

import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.InputFilterSpace;
import com.quwu.xinwo.until.MySharePreferences;

public class Change_NickeNameActivity extends SwipeBackActivity implements
		OnClickListener {

	private EditText editText;
	private LinearLayout deleteLin;
	private Button exitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_nickname);
		FinishActivity.finish(R.id.change_nickname_returnRel,
				Change_NickeNameActivity.this);
		findID();
	}

	private void findID() {
		editText = (EditText) findViewById(R.id.change_nickname_edit);
		deleteLin = (LinearLayout) findViewById(R.id.change_nickname_deleteLin);
		exitBtn = (Button) findViewById(R.id.change_nickname_exitBtn);

		String string = this.getIntent().getExtras().getString("nickname");
		if (string != null) {
			editText.setHint(string);
		}
		// 限制输入
		editText.setFilters(new InputFilter[] { new InputFilter() {
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				for (int i = start; i < end; i++) {
					System.out.println(Character.toString(source.charAt(i)));
					if (!Character.isLetterOrDigit(source.charAt(i))
							&& !Character.toString(source.charAt(i))
									.equals("_")
							&& !Character.toString(source.charAt(i))
									.equals("-")) {
						return "";
					} else if (Character.toString(source.charAt(i)).equals("日")
							|| Character.toString(source.charAt(i)).equals("妈")
							|| Character.toString(source.charAt(i)).equals("逼")
							|| Character.toString(source.charAt(i)).equals("操")) {
						System.out.println(Character.toString(source.charAt(i)));
						return "";
					}
				}
				return null;
			}
		} });
		deleteLin.setOnClickListener(this);
		exitBtn.setOnClickListener(this);

		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				InputFilterSpace.inputFilterSpace(editText, 20);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_nickname_deleteLin:
			editText.setText("");
			break;
		case R.id.change_nickname_exitBtn:
			if (editText.length()<4) {
				MyToast.Toast(getApplicationContext(), "名字由4-20位中英文、数字、“-”、“_”组成哦~");
			}else if (!editText.getText().toString().trim().equals("")){
				new ConfirmTask().executeOnExecutor(Executors
						.newCachedThreadPool());
			} else {
				MyToast.Toast(getApplicationContext(), "请输入您的昵称");
			}
			break;

		default:
			break;
		}
	}

	private class ConfirmTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String result = OKHTTP_POST.doPost2(MyApp.base_address
					+ "usersaction/updusersname.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),
					"username", editText.getText().toString());
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
				break;
			default:
				break;
			}
		};
	};
}
