package com.quwu.xinwo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

public class Add_AccountActivity extends SwipeBackActivity implements
		OnClickListener {

	private TextView bank;
	private TextView alipay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_account);
		FinishActivity.finish(R.id.add_account_returnRel, this);
		findID();
	}

	private void findID() {
		bank = (TextView) findViewById(R.id.add_account_bankText);
		alipay = (TextView) findViewById(R.id.add_account_alipayText);
		bank.setOnClickListener(this);
		alipay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.add_account_bankText:
				intent = new Intent(Add_AccountActivity.this,
						Add_BankCardActivity.class);
				break;
			case R.id.add_account_alipayText:
				intent = new Intent(Add_AccountActivity.this,
						Bind_AlipayActivity.class);
				break;

			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}
}
