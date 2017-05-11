package com.quwu.xinwo.until;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MyTextWatcher implements TextWatcher{
	private EditText editText;
	private int lenth;
	private View view;

	public MyTextWatcher(EditText editText,int lenth,View view) {
		super();
		this.editText = editText;
		this.lenth = lenth;
		this.view = view;
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (view!=null) {
			if (s.length()>0) {
				view.setVisibility(View.VISIBLE);
			}else {
				view.setVisibility(View.INVISIBLE);
			}
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		InputFilterSpace.inputFilterSpace(editText, lenth);
	}

}
