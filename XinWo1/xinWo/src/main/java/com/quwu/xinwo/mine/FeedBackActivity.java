package com.quwu.xinwo.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.InputFilterSpace;

public class FeedBackActivity extends SwipeBackActivity {

	private EditText questionEd;
	private EditText emailEd;
	private TextView questionText;
	private Button confrimBtn;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		FinishActivity.finish(R.id.feedback_returnRel, FeedBackActivity.this);
		findID();
	}

	private void findID() {
		questionEd = (EditText) findViewById(R.id.feedback_questionEd);
		emailEd = (EditText) findViewById(R.id.feedback_emailEd);
		questionText = (TextView) findViewById(R.id.feedback_questionText);
		confrimBtn = (Button) findViewById(R.id.feedback_comfirBtn);
		InputFilterSpace.inputFilterSpace(questionEd, 100);
		InputFilterSpace.inputFilterSpace(emailEd, 30);
		questionEd.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				questionText.setText(s.length()+"/100");
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {

			}
		});
		confrimBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
			}
		});
	}
}
