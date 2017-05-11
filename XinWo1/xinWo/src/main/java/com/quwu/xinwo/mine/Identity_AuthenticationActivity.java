package com.quwu.xinwo.mine;

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
import com.quwu.xinwo.until.MyInputMethodManager;
import com.quwu.xinwo.until.MyTextWatcher;

public class Identity_AuthenticationActivity extends SwipeBackActivity
		implements OnClickListener {

	private LinearLayout layout1, layout2, layout3;
	private EditText phoneEd, verification_codeEd;
	private LinearLayout phoneDeleteLin, verification_codeDeleteLin;
	private TextView verification_codeText, exampleText;
	private Button nextBtn1, nextBtn2, confirmBtn, lastStepBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.identity_authentication);
		FinishActivity.finish(R.id.identity_authentication_returnRel,
				Identity_AuthenticationActivity.this);
		findID();
	}

	private void findID() {
		layout1 = (LinearLayout) findViewById(R.id.identity_authentication_include1Lin);
		layout2 = (LinearLayout) findViewById(R.id.identity_authentication_include2Lin);
		layout3 = (LinearLayout) findViewById(R.id.identity_authentication_include3Lin);

		phoneEd = (EditText) findViewById(R.id.identity_authentication_phoneNumEd);
		verification_codeEd = (EditText) findViewById(R.id.identity_authentication_verification_codeEd);

		phoneDeleteLin = (LinearLayout) findViewById(R.id.identity_authentication_phoneNumDeleteLin);
		verification_codeDeleteLin = (LinearLayout) findViewById(R.id.identity_authentication_verification_codeDeleteLin);

		verification_codeText = (TextView) findViewById(R.id.identity_authentication_verification_codeText);
		exampleText = (TextView) findViewById(R.id.identity_authentication_exampleText);

		nextBtn1 = (Button) findViewById(R.id.identity_authentication_nextBtn1);
		nextBtn2 = (Button) findViewById(R.id.identity_authentication_nextBtn2);
		lastStepBtn = (Button) findViewById(R.id.identity_authentication_lastStepBtn);
		confirmBtn = (Button) findViewById(R.id.identity_authentication_confirmBtn);

		phoneEd.addTextChangedListener(new MyTextWatcher(phoneEd, 11,
				phoneDeleteLin));
		verification_codeEd.addTextChangedListener(new MyTextWatcher(
				verification_codeEd, 6, verification_codeDeleteLin));

		phoneDeleteLin.setOnClickListener(this);
		verification_codeDeleteLin.setOnClickListener(this);
		verification_codeText.setOnClickListener(this);
		exampleText.setOnClickListener(this);
		nextBtn1.setOnClickListener(this);
		nextBtn2.setOnClickListener(this);
		lastStepBtn.setOnClickListener(this);
		confirmBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.identity_authentication_phoneNumDeleteLin:// 清空电话号码
			phoneEd.setText("");
			break;
		case R.id.identity_authentication_verification_codeDeleteLin:// 清空验证码
			verification_codeEd.setText("");
			break;
		case R.id.identity_authentication_verification_codeText:// 获取验证码
			MyCountDownTimer downTimer = new MyCountDownTimer(60000, 1000,
					verification_codeText);
			downTimer.start();
			break;
		case R.id.identity_authentication_exampleText:// 查看示例图片

			break;
		case R.id.identity_authentication_nextBtn1:// 第一个下一步按钮
			if (phoneEd.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "请填写手机号", 10).show();
			} else if (!IsCheckPhoneNum.judgePhoneNums(phoneEd.getText()
					.toString())) {
				Toast.makeText(getApplicationContext(), "请填写正确的手机号", 10).show();
			} else if (verification_codeEd.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "请填写验证码", 10).show();
			} else {
				layout1.setVisibility(View.GONE);
				layout2.setVisibility(View.VISIBLE);
				layout3.setVisibility(View.GONE);
				MyInputMethodManager.MyInputMethodManager1(nextBtn1,
						getApplicationContext());
			}
			break;
		case R.id.identity_authentication_nextBtn2:// 第二个下一步按钮
			layout1.setVisibility(View.GONE);
			layout2.setVisibility(View.GONE);
			layout3.setVisibility(View.VISIBLE);
			break;
		case R.id.identity_authentication_lastStepBtn:// 上一步
			layout1.setVisibility(View.VISIBLE);
			layout2.setVisibility(View.GONE);
			layout3.setVisibility(View.GONE);
			break;
		case R.id.identity_authentication_confirmBtn:// 确定
			finish();
			break;

		default:
			break;
		}
	}
}
