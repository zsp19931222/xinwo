package com.quwu.xinwo.log_in;

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

public class ForgetPasswardActivity extends SwipeBackActivity implements OnClickListener{

	private EditText phoneEd, verification_codeEdit;
	private LinearLayout phoneDeleteLin;
	private LinearLayout verification_codeDeleteLin;
	private TextView verification_codeText;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		FinishActivity.finish(R.id.forgetpasswordRel,
				ForgetPasswardActivity.this);
		findID();
	}

	private void findID() {
		phoneEd = (EditText) findViewById(R.id.forgetpassword_phoneEdit);
		verification_codeEdit = (EditText) findViewById(R.id.forgetpassword_verification_codeEdit);

		phoneDeleteLin = (LinearLayout) findViewById(R.id.forgetpassword_phoneDeleteLin);
		verification_codeDeleteLin = (LinearLayout) findViewById(R.id.forgetpassword_verification_codeDeleteLin);

		verification_codeText = (TextView) findViewById(R.id.forgetpassword_verification_codeText);

		button = (Button) findViewById(R.id.forgetpassword_confirmBtn);
		
		verification_codeEdit.addTextChangedListener(new MyTextWatcher(verification_codeEdit,6,verification_codeDeleteLin));
		phoneEd.addTextChangedListener(new MyTextWatcher(phoneEd,11,phoneDeleteLin));
		
		phoneDeleteLin.setOnClickListener(this);
		verification_codeDeleteLin.setOnClickListener(this);
		verification_codeText.setOnClickListener(this);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		}else {
		switch (v.getId()) {
		case R.id.forgetpassword_phoneDeleteLin://清空手机号输入框
			phoneEd.setText("");
			break;
		case R.id.forgetpassword_verification_codeDeleteLin://清空验证码输入框
			verification_codeEdit.setText("");
			break;
		case R.id.forgetpassword_verification_codeText://获取验证码
			if (IsCheckPhoneNum.judgePhoneNums(phoneEd.getText().toString().trim())) {
				
				MyCountDownTimer countDownTimer=new MyCountDownTimer(60000, 1000,verification_codeText);
				countDownTimer.start();
			
			}else {
				Toast.makeText(getApplicationContext(), "请输入正确的手机号", 10).show();
			}
			break;
		case R.id.forgetpassword_confirmBtn://确定按钮
			if (phoneEd.getText().toString().equals("")||verification_codeEdit.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "请输入手机号或验证码", 10).show();
			}
			Intent intent=new Intent(ForgetPasswardActivity.this,Reset_PasswordActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		}
	}
	
}
