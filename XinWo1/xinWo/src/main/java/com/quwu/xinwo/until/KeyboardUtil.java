package com.quwu.xinwo.until;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.KeyBoardData;

public class KeyboardUtil {
	private Context ctx;
	private Activity act;
	private KeyboardView keyboardView;
	private Keyboard k1;// 字母键盘
	private Keyboard k2;// 数字键盘
	public boolean isnun = true;// 是否数据键盘
	public boolean isupper = false;// 是否大写
	private List<KeyBoardData> list;

	private EditText selling_price;// 卖价
	private EditText original_price;// 原价
	private EditText freight;// 运费
	private EditText reserve_price;// 保留价
	private EditText goods_value;// 商品价值
	private CheckBox box;// 是否包邮

	private EditText ed;

	public KeyboardUtil(Activity act, Context ctx, EditText edit,
			EditText selling_price, EditText original_price, EditText freight,
			EditText reserve_price, EditText goods_value, CheckBox box) {
		this.act = act;
		this.ctx = ctx;
		this.ed = edit;
		this.selling_price = selling_price;
		this.original_price = original_price;
		this.freight = freight;
		this.reserve_price = reserve_price;
		this.goods_value = goods_value;
		this.box = box;
		k1 = new Keyboard(ctx, R.xml.symbols);
		k2 = new Keyboard(ctx, R.xml.symbols);
		keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(k1);
		keyboardView.setEnabled(true);
		keyboardView.setPreviewEnabled(true);
		keyboardView.setOnKeyboardActionListener(listener);
	}

	private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			Editable editable = ed.getText();
			int start = ed.getSelectionStart();
			if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
				// hideKeyboard();
				list = new ArrayList<KeyBoardData>();
				if (reserve_price == null && goods_value == null) {
					if (box == null && selling_price == null
							&& original_price == null && freight == null) {
						Toast.makeText(ctx, "请填写相关信息", 10).show();
					} else {
						if (box != null) {
							if (box.isChecked() == false) {
								if (selling_price.getText().toString()
										.equals("")
										|| original_price.getText().toString()
												.equals("")
										|| freight.getText().toString()
												.equals("")) {
									Toast.makeText(ctx, "请填写相关信息", 10).show();
								} else {
									list.add(new KeyBoardData(
											selling_price.getText().toString(),
											original_price.getText().toString(),
											freight.getText().toString(), null,
											null, box.isChecked()));
									// 数据是使用Intent返回
									Intent intent = new Intent();
									// 把返回数据存入Intent
									intent.putExtra("keyboard_data",
											(Serializable) list);
									// 设置返回数据
									act.setResult(2, intent);
									act.finish();
								}
							} else {
								if (selling_price.getText().toString()
										.equals("")
										|| original_price.getText().toString()
												.equals("")
								) {
									Toast.makeText(ctx, "请填写相关信息", 10).show();
								} else {
									list.add(new KeyBoardData(
											selling_price.getText().toString(),
											original_price.getText().toString(),
											freight.getText().toString(), null,
											null, box.isChecked()));
									// 数据是使用Intent返回
									Intent intent = new Intent();
									// 把返回数据存入Intent
									intent.putExtra("keyboard_data",
											(Serializable) list);
									// 设置返回数据
									act.setResult(2, intent);
									act.finish();
								}
							}
						}
					}
				} else if (selling_price == null && freight == null
						&& goods_value == null && box == null) {
					if (original_price==null||reserve_price==null) {
						Toast.makeText(ctx, "请填写相关信息", 10).show();
					}else {
						if (original_price.getText().toString().equals("")||reserve_price.getText().toString().equals("")) {
							Toast.makeText(ctx, "请填写相关信息", 10).show();
						}else {
							list.add(new KeyBoardData(null, original_price.getText()
									.toString(), null, reserve_price.getText()
									.toString(), null, false));
							// 数据是使用Intent返回
							Intent intent = new Intent();
							// 把返回数据存入Intent
							intent.putExtra("keyboard_data",
									(Serializable) list);
							// 设置返回数据
							act.setResult(2, intent);
							act.finish();
						}
					}
				} else if (selling_price == null && reserve_price == null) {
					if (original_price==null&&freight==null&&goods_value==null&&box==null) {
						Toast.makeText(ctx, "请填写相关信息", 10).show();
					}else {
						if (box.isChecked()==true) {
							if (original_price.getText().toString().equals("")||goods_value.getText().toString().equals("")) {
								Toast.makeText(ctx, "请填写相关信息", 10).show();
							}else {
								list.add(new KeyBoardData(null, original_price.getText()
										.toString(), freight.getText().toString(), null,
										goods_value.getText().toString(), box.isChecked()));
								// 数据是使用Intent返回
								Intent intent = new Intent();
								// 把返回数据存入Intent
								intent.putExtra("keyboard_data",
										(Serializable) list);
								// 设置返回数据
								act.setResult(2, intent);
								act.finish();
							}
						}else {
							if (original_price.getText().toString().equals("")||freight.getText().toString().equals("")||goods_value.getText().toString().equals("")) {
								Toast.makeText(ctx, "请填写相关信息", 10).show();
							}else {
								list.add(new KeyBoardData(null, original_price.getText()
										.toString(), freight.getText().toString(), null,
										goods_value.getText().toString(), box.isChecked()));
								// 数据是使用Intent返回
								Intent intent = new Intent();
								// 把返回数据存入Intent
								intent.putExtra("keyboard_data",
										(Serializable) list);
								// 设置返回数据
								act.setResult(2, intent);
								act.finish();
							}
						}
					}
			
					
				}

			} else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {// 回退
				if (editable != null && editable.length() > 0) {
					if (start > 0) {
						editable.delete(start - 1, start);
					}
				}
			} else if (primaryCode == Keyboard.KEYCODE_SHIFT) {// 大小写切换
				changeKey();
				keyboardView.setKeyboard(k1);

			} else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {// 数字键盘切换
				// if (isnun) {
				// isnun = false;
				// keyboardView.setKeyboard(k1);
				// } else {
				// isnun = true;
				// keyboardView.setKeyboard(k2);
				// }
				// ed.setText("");
			} else if (primaryCode == 57419) { // go left
				if (start > 0) {
					ed.setSelection(start - 1);
				}
			} else if (primaryCode == 57421) { // go right
				if (start < ed.length()) {
					ed.setSelection(start + 1);
				}
			} else {
				editable.insert(start, Character.toString((char) primaryCode));
			}
		}
	};

	/**
	 * 键盘大小写切换
	 */
	private void changeKey() {
		List<Key> keylist = k1.getKeys();
		if (isupper) {// 大写切换小写
			isupper = false;
			for (Key key : keylist) {
				if (key.label != null && isword(key.label.toString())) {
					key.label = key.label.toString().toLowerCase();
					key.codes[0] = key.codes[0] + 32;
				}
			}
		} else {// 小写切换大写
			isupper = true;
			for (Key key : keylist) {
				if (key.label != null && isword(key.label.toString())) {
					key.label = key.label.toString().toUpperCase();
					key.codes[0] = key.codes[0] - 32;
				}
			}
		}
	}

	public void showKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.GONE || visibility == View.INVISIBLE) {
			keyboardView.setVisibility(View.VISIBLE);
		}
	}

	public void hideKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			keyboardView.setVisibility(View.INVISIBLE);
		}
	}

	private boolean isword(String str) {
		String wordstr = "abcdefghijklmnopqrstuvwxyz";
		if (wordstr.indexOf(str.toLowerCase()) > -1) {
			return true;
		}
		return false;
	}

}
