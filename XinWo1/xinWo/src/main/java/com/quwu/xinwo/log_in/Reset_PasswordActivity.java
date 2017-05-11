package com.quwu.xinwo.log_in;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MyTextWatcher;
import com.quwu.xinwo.until.Tool;

public class Reset_PasswordActivity extends SwipeBackActivity implements OnClickListener{
	private EditText phoneEd, verification_codeEdit;
	private LinearLayout phoneDeleteLin;
	private LinearLayout verification_codeDeleteLin;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);
		FinishActivity.finish(R.id.reset_passwordRel,
				Reset_PasswordActivity.this);
		findID();
	}

	private void findID() {
		phoneEd = (EditText) findViewById(R.id.reset_password_phoneEdit);
		verification_codeEdit = (EditText) findViewById(R.id.reset_password_verification_codeEdit);

		phoneDeleteLin = (LinearLayout) findViewById(R.id.reset_password_phoneDeleteLin);
		verification_codeDeleteLin = (LinearLayout) findViewById(R.id.reset_password_verification_codeDeleteLin);


		button = (Button) findViewById(R.id.reset_password_confirmBtn);
		
		verification_codeEdit.addTextChangedListener(new MyTextWatcher(verification_codeEdit,6,verification_codeDeleteLin));
		phoneEd.addTextChangedListener(new MyTextWatcher(phoneEd,11,phoneDeleteLin));
		
		phoneDeleteLin.setOnClickListener(this);
		verification_codeDeleteLin.setOnClickListener(this);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		}else {
		switch (v.getId()) {
		case R.id.reset_password_phoneDeleteLin://清空手机号输入框
			phoneEd.setText("");
			break;
		case R.id.reset_password_verification_codeDeleteLin://清空验证码输入框
			verification_codeEdit.setText("");
			break;
		case R.id.reset_password_confirmBtn://确定按钮
			if (phoneEd.getText().toString().equals("")||verification_codeEdit.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "请输入手机号或验证码", 10).show();
			}
			break;

		default:
			break;
		}
		}
	}
	
}
