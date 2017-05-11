package com.quwu.xinwo.mine;

import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
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
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;

public class WithdrawActivity extends SwipeBackActivity implements
		OnClickListener {

	private RelativeLayout withdrawRel;
	private EditText editText;
	private Button button;
	private TextView withdrawText;
	private String money_ed;
	
	
	//上个界面传过来的参数
private String account_name;
private String transaction_num;
private String transaction_id;
private String money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.withdraw);
		FinishActivity.finish(R.id.withdraw_returnRel, this);
		findID();
	}

	private void findID() {
		withdrawRel = (RelativeLayout) findViewById(R.id.withdrawRel);
		editText = (EditText) findViewById(R.id.withdrawEd);
		InputFilterSpace.inputFilterSpace(editText, 15);
		button = (Button) findViewById(R.id.withdrawBtn);
		withdrawText=(TextView) findViewById(R.id.withdrawText);
		withdrawRel.setOnClickListener(this);
		button.setOnClickListener(this);
		
		
		
			account_name=this.getIntent().getExtras().getString("account_name");
			transaction_num=this.getIntent().getExtras().getString("transaction_num");
			transaction_id=this.getIntent().getExtras().getString("transaction_id");
			money=this.getIntent().getExtras().getString("money");
			withdrawText.setText(account_name+"（"+transaction_num+"）");
			editText.setHint("可提现金额"+money+"元");
		
			
			editText.addTextChangedListener(new TextWatcher() {
				private boolean isChanged = false;

				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {

				}

				@Override
				public void afterTextChanged(final Editable s) {
					if (isChanged) {
						return;
					}
					if (s.toString().equals("") != true) {
						// isChanged = true;
						// isChanged = false;
						CharSequence sequence = editText.getText();
						if (sequence instanceof Spannable) {
							Spannable spannable = (Spannable) sequence;
							Selection.setSelection(spannable, sequence.length());
						}
						
						int i=Integer.valueOf(s.toString());
						int j=Integer.valueOf(money);
						if (i>j) {
							editText.setText(String.valueOf(j));
						}
					}
					// else {
					// // if (prefecture.equals("1")) {
					// // editText.setText("10");
					// // }else {
					// // editText.setText("1");
					// // }
					// }
				}
			});
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.withdrawRel:
				Intent intent = new Intent(WithdrawActivity.this,
						Account_ManagementActivity.class);
				intent.putExtra("state", "1");
				intent.putExtra("money", money);
				startActivityForResult(intent, 1);
				break;
			case R.id.withdrawBtn:
				isCheckFirst();
				money_ed=editText.getText().toString().trim();
				if (money_ed.equals("")) {
					MyToast.Toast(WithdrawActivity.this, "请输入提现金额");
				}else	if (Integer.valueOf(money_ed)>Integer.valueOf(money)) {
					MyToast.Toast(WithdrawActivity.this, "您输入的金额超出了可提现金额，请重新输入金额");
					editText.setText("");
				}else {
					new ConfirmTask().executeOnExecutor(Executors.newCachedThreadPool());
				}
				break;

			default:
				break;
			}
		}
	}
	/**
	 * 依次判读最前面输入的数字是否为0，为0 给去掉
	 */
	private void isCheckFirst() {
		String str = editText.getText().toString();
		for (int i = 0; i < str.length(); i++) {
			if (str.startsWith("0") == true) {
				str = str.substring(1, str.length());
			} else {
				str = str.substring(0, str.length());
			}
			editText.setText(str);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	if (resultCode==1) {
		if (data!=null) {
			account_name=data.getExtras().getString("account_name");
			transaction_num=data.getExtras().getString("transaction_num");
			transaction_id=data.getExtras().getString("transaction_id");
			money=data.getExtras().getString("money");
			withdrawText.setText(account_name+"（"+transaction_num+"）");
			editText.setHint("可提现金额"+money+"元");
		}
	}
	}
	
	private class ConfirmTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			
			String string=OKHTTP_POST.doPost3(MyApp.base_address+"myAccount/confirmationwithdrawal.do", "user_id", MySharePreferences.GetUser_ID(WithdrawActivity.this), "amount_num", editText.getText().toString().trim(), "transaction_id", transaction_id);
			if (string!=null) {
				try {
					JSONObject jsonObject=new JSONObject(string);
				String string2=jsonObject.getString("1");
				if (string2.equals("申请成功")) {
					handler.sendMessage(handler.obtainMessage(0,"申请成功"));
				}else if(string2.equals("申请失败")){
					handler.sendMessage(handler.obtainMessage(1,"申请失败，请稍后重试。"));
				}else if (string2.equals("你申请提现金额大于了可提现金额")) {
					handler.sendMessage(handler.obtainMessage(1,"您的提现金额超出了可提现金额，请重新输入金额"));
				}else if (string2.equals("程序异常")) {
					handler.sendMessage(handler.obtainMessage(1, MyApp.Program_Exception_Prompt));
				}
				}
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				handler.sendMessage(handler.obtainMessage(1, MyApp.Program_Exception_Prompt));
			}
			return null;
		}
		
	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				String string1 = (String) msg.obj;
				MyToast.Toast(WithdrawActivity.this, string1);
				finish();
				break;
			case 1:
				String string = (String) msg.obj;
				MyToast.Toast(WithdrawActivity.this, string);
				break;

			default:
				break;
			}
		};
	};
}
