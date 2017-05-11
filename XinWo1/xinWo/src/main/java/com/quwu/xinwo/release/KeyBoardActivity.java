package com.quwu.xinwo.release;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.KeyBoardData;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.KeyboardUtil;

public class KeyBoardActivity extends Activity implements OnClickListener {

	private Context ctx;
	private Activity act;
	private String state;

	private LinearLayout cordelesLin;
	private EditText keyboard_selling_priceEd;// 卖价
	private EditText keyboard_freightEd;// 运费
	private EditText keyboard_original_costEd;// 原价
	private CheckBox keyboard_checkbox;

	private LinearLayout auctionLin;
	private EditText auctionLin_retainPriceEd;
	private EditText auctionLin_original_costEd;

	private LinearLayout rent_outLin;
	private EditText rent_outLin_valueEd;
	private EditText rent_outLin_original_costEd;
	private EditText rent_outLin_freightEd;
	private CheckBox rent_outLin_checkbox;

	private TextView keyboard_freightText;
	private TextView rent_outLin_freightText;

	private List<KeyBoardData> list;// 存取用户输入信息

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard);
		list = new ArrayList<KeyBoardData>();
		findID();
		FinishActivity.finish(R.id.keyboardLin, KeyBoardActivity.this);
		ctx = this;
		act = this;
	}

	private void findID() {
		state = this.getIntent().getExtras().getString("state");
		if (state.equals("1")) {
			cordelesLin = (LinearLayout) findViewById(R.id.cordelesLin);
			cordelesLin.setVisibility(View.VISIBLE);

			keyboard_selling_priceEd = (EditText) findViewById(R.id.keyboard_selling_priceEd);
			keyboard_freightEd = (EditText) findViewById(R.id.keyboard_freightEd);
			keyboard_original_costEd = (EditText) findViewById(R.id.keyboard_original_costEd);
			keyboard_checkbox = (CheckBox) findViewById(R.id.keyboard_checkbox);
			keyboard_freightText = (TextView) findViewById(R.id.keyboard_freightText);
			keyboard_checkbox
					.setOnCheckedChangeListener(new MyCheckedChangeListener(
							keyboard_freightEd, keyboard_freightText));

			handler.sendMessage(handler.obtainMessage(0,
					keyboard_original_costEd));
			handler.sendMessage(handler.obtainMessage(0, keyboard_freightEd));
			handler.sendMessage(handler.obtainMessage(0,
					keyboard_selling_priceEd));

			isKeyBoard(keyboard_freightEd,keyboard_selling_priceEd,keyboard_original_costEd,keyboard_freightEd,null,null,keyboard_checkbox);
			isKeyBoard(keyboard_original_costEd,keyboard_selling_priceEd,keyboard_original_costEd,keyboard_freightEd,null,null,keyboard_checkbox);
			isKeyBoard(keyboard_selling_priceEd,keyboard_selling_priceEd,keyboard_original_costEd,keyboard_freightEd,null,null,keyboard_checkbox);

		} else if (state.equals("2")) {
			auctionLin = (LinearLayout) findViewById(R.id.auctionLin);
			auctionLin.setVisibility(View.VISIBLE);

			auctionLin_retainPriceEd = (EditText) findViewById(R.id.auctionLin_retainPriceEd);
			auctionLin_original_costEd = (EditText) findViewById(R.id.auctionLin_original_costEd);

			handler.sendMessage(handler.obtainMessage(0,
					auctionLin_original_costEd));
			handler.sendMessage(handler.obtainMessage(0,
					auctionLin_retainPriceEd));

			isKeyBoard(auctionLin_retainPriceEd,null,auctionLin_original_costEd,null,auctionLin_retainPriceEd,null,null);
			isKeyBoard(auctionLin_original_costEd,null,auctionLin_original_costEd,null,auctionLin_retainPriceEd,null,null);

		} else if (state.equals("3")) {
			rent_outLin = (LinearLayout) findViewById(R.id.rent_outLin);
			rent_outLin.setVisibility(View.VISIBLE);

			rent_outLin_freightEd = (EditText) findViewById(R.id.rent_outLin_freightEd);
			rent_outLin_original_costEd = (EditText) findViewById(R.id.rent_outLin_original_costEd);
			rent_outLin_valueEd = (EditText) findViewById(R.id.rent_outLin_valueEd);
			rent_outLin_checkbox = (CheckBox) findViewById(R.id.rent_outLin_checkbox);
			rent_outLin_freightText = (TextView) findViewById(R.id.rent_outLin_freightText);

			rent_outLin_checkbox
					.setOnCheckedChangeListener(new MyCheckedChangeListener(
							rent_outLin_freightEd, rent_outLin_freightText));
			handler.sendMessage(handler.obtainMessage(0, rent_outLin_freightEd));
			handler.sendMessage(handler.obtainMessage(0,
					rent_outLin_original_costEd));
			handler.sendMessage(handler.obtainMessage(0, rent_outLin_valueEd));

			isKeyBoard(rent_outLin_valueEd, null, rent_outLin_original_costEd, rent_outLin_freightEd, null, rent_outLin_valueEd, rent_outLin_checkbox);
			isKeyBoard(rent_outLin_original_costEd,null, rent_outLin_original_costEd, rent_outLin_freightEd, null, rent_outLin_valueEd, rent_outLin_checkbox);
			isKeyBoard(rent_outLin_freightEd,null, rent_outLin_original_costEd, rent_outLin_freightEd, null, rent_outLin_valueEd, rent_outLin_checkbox);

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}
	}

	private void isKeyBoard(final EditText editText, final EditText selling_price,
			final EditText original_price, final EditText freight, final EditText reserve_price,
			final EditText goods_value, final CheckBox box) {
		editText.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int inputback = editText.getInputType();
				editText.setInputType(InputType.TYPE_NULL);
				new KeyboardUtil(act, ctx, editText, selling_price, original_price, freight, reserve_price,
						goods_value, box).showKeyboard();
				editText.setInputType(inputback);
				editText.setSelection(editText.getText().toString().length());
				return false;
			}
		});
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				EditText editText = (EditText) msg.obj;
				int inputback = editText.getInputType();
				editText.setInputType(InputType.TYPE_NULL);
				new KeyboardUtil(act, ctx, editText, null, null, null, null,
						null, null).showKeyboard();
				editText.setInputType(inputback);
				break;

			default:
				break;
			}
		};
	};

	private class MyCheckedChangeListener implements OnCheckedChangeListener {

		private EditText editText;
		private TextView textView;

		public MyCheckedChangeListener(EditText editText, TextView textView) {
			super();
			this.editText = editText;
			this.textView = textView;
		}

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked == true) {
				editText.setVisibility(View.GONE);
				textView.setVisibility(View.VISIBLE);
			} else {
				editText.setVisibility(View.VISIBLE);
				textView.setVisibility(View.GONE);
			}
		}
	}
}
