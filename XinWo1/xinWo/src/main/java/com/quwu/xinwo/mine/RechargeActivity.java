package com.quwu.xinwo.mine;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MyInputMethodManager;

public class RechargeActivity extends SwipeBackActivity implements OnClickListener{

	private CheckBox box1;
	private CheckBox box2;
	private CheckBox box3;
	private CheckBox box4;
	private CheckBox box5;
	private EditText editText;
	private Button pay1;
	private Button pay2;
	private Button pay3;
	private Button confirmBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recharge);
		FinishActivity.finish(R.id.recharge_returnRel, RechargeActivity.this);
		findID();
	}

	private void findID() {
		box1 = (CheckBox) findViewById(R.id.recharge_box1);
		box2 = (CheckBox) findViewById(R.id.recharge_box2);
		box3 = (CheckBox) findViewById(R.id.recharge_box3);
		box4 = (CheckBox) findViewById(R.id.recharge_box4);
		box5 = (CheckBox) findViewById(R.id.recharge_box5);

		box1.setOnCheckedChangeListener(new MyOnCheckedChangeListener(box1));
		box2.setOnCheckedChangeListener(new MyOnCheckedChangeListener(box2));
		box3.setOnCheckedChangeListener(new MyOnCheckedChangeListener(box3));
		box4.setOnCheckedChangeListener(new MyOnCheckedChangeListener(box4));
		box5.setOnCheckedChangeListener(new MyOnCheckedChangeListener(box5));

		editText = (EditText) findViewById(R.id.recharge_edit);

		pay1 = (Button) findViewById(R.id.recharge_pay_wayBtn1);
		pay2 = (Button) findViewById(R.id.recharge_pay_wayBtn2);
		pay3 = (Button) findViewById(R.id.recharge_pay_wayBtn3);

		confirmBtn = (Button) findViewById(R.id.recharge_confirmBtn);

		box1.setOnClickListener(this);
		box2.setOnClickListener(this);
		box3.setOnClickListener(this);
		box4.setOnClickListener(this);
		box5.setOnClickListener(this);

		pay1.setOnClickListener(this);
		pay2.setOnClickListener(this);
		pay3.setOnClickListener(this);
	}

	// 单选
	private class MyOnCheckedChangeListener implements OnCheckedChangeListener {

		private CheckBox box;

		public MyOnCheckedChangeListener(CheckBox box) {
			super();
			this.box = box;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			isCheckBox();
			box.setChecked(isChecked);
		}
	}

	private void isCheckBox() {
		box1.setChecked(false);
		box2.setChecked(false);
		box3.setChecked(false);
		box4.setChecked(false);
		box5.setChecked(false);
	}


	@Override
	public void onClick(View v) {
		MyInputMethodManager.MyInputMethodManager1(v,
				getApplicationContext());		
	}
}
