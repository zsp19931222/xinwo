package com.quwu.xinwo.popupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.quwu.xinwo.R;

public class My_Release_Pop extends Activity {

	private TextView edit;
	private TextView off_the_shelf;
	private TextView select_cancel;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head_set_choice);
		edit = (TextView) findViewById(R.id.take_pictures);
		off_the_shelf = (TextView) findViewById(R.id.select_photo);
		select_cancel = (TextView) findViewById(R.id.select_cancel);

		edit.setText("编辑");
		off_the_shelf.setText("下架");
		select_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
	}
}
