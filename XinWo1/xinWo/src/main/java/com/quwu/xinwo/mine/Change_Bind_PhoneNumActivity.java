package com.quwu.xinwo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.IsCheckPhoneNum;
import com.quwu.xinwo.until.MyCountDownTimer;
import com.quwu.xinwo.until.MyTextWatcher;
import com.quwu.xinwo.until.Tool;

public class Change_Bind_PhoneNumActivity extends SwipeBackActivity implements
		OnClickListener {

	private EditText change_binding_phoneNum_oldEd;
	private EditText change_binding_phoneNum_verification_codeEd;
	private LinearLayout change_binding_phoneNum_oldDeleteLin;
	private LinearLayout change_binding_phoneNum_verification_codeDeleteLin;
	private TextView change_binding_phoneNum_verification_codeText;
	private Button change_binding_phonenum_confirmBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_binding_phonenum);
		FinishActivity.finish(R.id.change_binding_phoneNum_returnRel,
				Change_Bind_PhoneNumActivity.this);
		findID();
	}

	private void findID() {
		change_binding_phoneNum_oldEd = (EditText) findViewById(R.id.change_binding_phoneNum_oldEd);
		change_binding_phoneNum_verification_codeEd = (EditText) findViewById(R.id.change_binding_phoneNum_verification_codeEd);

		change_binding_phoneNum_oldEd.setText(this.getIntent().getExtras()
				.getString("oldNum"));

		change_binding_phoneNum_oldDeleteLin = (LinearLayout) findViewById(R.id.change_binding_phoneNum_oldDeleteLin);
		change_binding_phoneNum_verification_codeDeleteLin = (LinearLayout) findViewById(R.id.change_binding_phoneNum_verification_codeDeleteLin);

		change_binding_phoneNum_verification_codeText = (TextView) findViewById(R.id.change_binding_phoneNum_verification_codeText);

		change_binding_phonenum_confirmBtn = (Button) findViewById(R.id.change_binding_phonenum_confirmBtn);

		change_binding_phoneNum_oldEd.addTextChangedListener(new MyTextWatcher(
				change_binding_phoneNum_oldEd, 11,
				change_binding_phoneNum_oldDeleteLin));
		change_binding_phoneNum_verification_codeEd
				.addTextChangedListener(new MyTextWatcher(
						change_binding_phoneNum_verification_codeEd, 6,
						change_binding_phoneNum_verification_codeDeleteLin));

		change_binding_phoneNum_oldDeleteLin.setOnClickListener(this);
		change_binding_phoneNum_verification_codeDeleteLin
				.setOnClickListener(this);
		change_binding_phoneNum_verification_codeText.setOnClickListener(this);
		change_binding_phonenum_confirmBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.change_binding_phoneNum_oldDeleteLin:// 清空手机号
				change_binding_phoneNum_oldEd.setText("");
				break;
			case R.id.change_binding_phoneNum_verification_codeDeleteLin:// 清空验证码
				change_binding_phoneNum_verification_codeEd.setText("");
				break;
			case R.id.change_binding_phoneNum_verification_codeText:// 获取验证码
				if (IsCheckPhoneNum
						.judgePhoneNums(change_binding_phoneNum_oldEd.getText()
								.toString().trim())) {
					MyCountDownTimer countDownTimer = new MyCountDownTimer(
							60000, 1000,
							change_binding_phoneNum_verification_codeText);
					countDownTimer.start();
				} else {
					Toast.makeText(getApplicationContext(), "请输入正确的手机号", 10)
							.show();
				}
				break;
			case R.id.change_binding_phonenum_confirmBtn:// 确定
				if (change_binding_phoneNum_oldEd.getText().toString()
						.equals("")
						|| change_binding_phoneNum_verification_codeEd
								.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "请输入手机号或验证码", 10)
							.show();
				}else if (change_binding_phoneNum_verification_codeEd.length()<6) {
					Toast.makeText(getApplicationContext(), "请输入6位验证码", 10)
					.show();
				} 
				else {
					Intent intent = new Intent(
							Change_Bind_PhoneNumActivity.this,
							Binding_PhoneNumActivity.class);
					startActivity(intent);
					finish();
				}
				break;

			default:
				break;
			}
		}
	}
}
