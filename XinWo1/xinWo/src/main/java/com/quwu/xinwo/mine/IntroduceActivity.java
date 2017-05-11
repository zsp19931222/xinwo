package com.quwu.xinwo.mine;

import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.InputFilterSpace;
import com.quwu.xinwo.until.MyInputMethodManager;
import com.quwu.xinwo.until.MySharePreferences;

public class IntroduceActivity extends SwipeBackActivity {

	private EditText editText;
	private TextView textView;
	private Button confire;
	private RelativeLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.introduce);
		FinishActivity.finish(R.id.introduce_returnRel, IntroduceActivity.this);
		findID();
	}

	private void findID() {
		confire=(Button) findViewById(R.id.introduce_confirmBtn);
		layout=(RelativeLayout) findViewById(R.id.introduce_Rel);
		editText = (EditText) findViewById(R.id.introduce_editText);
		textView = (TextView) findViewById(R.id.introduce_Text);
		InputFilterSpace.inputFilterSpace(editText, 25);
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				textView.setText(s.length()+"/25");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		layout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				MyInputMethodManager.MyInputMethodManager1(layout, getApplicationContext());
			}
		});
		
		confire.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (editText.getText().toString().trim().equals("")) {
					MyToast.Toast(getApplicationContext(), "请说点什么吧~");
				}else {
					new ConfirmTask().executeOnExecutor(Executors.newCachedThreadPool(), editText.getText().toString());
				}
			}
		});
	}
	private class ConfirmTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			String result=OKHTTP_POST.doPost2(MyApp.base_address+"usersaction/updusersintroduce.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()),"introduce",params[0]);
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
}
