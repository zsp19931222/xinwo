package com.quwu.xinwo.until;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

public class InputFilterSpace {
	/**
	 * 过滤空格
	 */
	public static void inputFilterSpace(final EditText edit,int lenth) {
		edit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(lenth),
				new InputFilter() {
					public CharSequence filter(CharSequence src, int start,
							int end, Spanned dst, int dstart, int dend) {
						if (src.length() < 1) {
							return null;
						} else {
							char temp[] = (src.toString()).toCharArray();
							char result[] = new char[temp.length];
							for (int i = 0, j = 0; i < temp.length; i++) {
								if (temp[i] == ' ') {
									continue;
								} else {
									result[j++] = temp[i];
								}
							}
							return String.valueOf(result).trim();
						}

					}
				} });
}
	}