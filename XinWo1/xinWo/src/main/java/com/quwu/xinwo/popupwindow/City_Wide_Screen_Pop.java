package com.quwu.xinwo.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.quwu.xinwo.R;
import com.quwu.xinwo.mywight.SeekBarPressure;
import com.quwu.xinwo.mywight.SeekBarPressure.OnSeekBarPressureClickListener;

public class City_Wide_Screen_Pop extends PopupWindow implements
		OnClickListener {
	private View view;
	private Activity activity;
	private LayoutInflater inflater;
	private RelativeLayout relativeLayout;
	private LinearLayout city_wide_screen_popLin;
	private SeekBarPressure barPressure;
	private Button resetBtn;
	private Button confirmBtn;
	private CheckBox box1;
	private CheckBox box2;
	private CheckBox box3;
	private CheckBox box4;
	private CheckBox box5;
	private CheckBox box6;
	private CheckBox box7;
	public OnScreenWindowClickListener lister;
	private String normal_use, warranty_period, no_repair, brand_new, jingdong,
			mainland_licensed, since, high = "10000000", low = "0";

	public City_Wide_Screen_Pop(final Activity activity
			,String normal_use
			,String warranty_period
			,String no_repair
			,String brand_new
			,String jingdong
			,String mainland_licensed
			,String since
			,String high
			,String low
			) {

		this.activity = activity;
		inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.city_wide_screen_pop, null);
		relativeLayout = (RelativeLayout) view
				.findViewById(R.id.city_wide_screen_popRel);
		relativeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		box1 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox1);
		box2 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox2);
		box3 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox3);
		box4 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox4);
		box5 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox5);
		box6 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox6);
		box7 = (CheckBox) view.findViewById(R.id.city_wide_screen_popBox7);

		resetBtn = (Button) view
				.findViewById(R.id.city_wide_screen_pop_resetBtn);
		confirmBtn = (Button) view
				.findViewById(R.id.city_wide_screen_pop_confirmBtn);
		resetBtn.setOnClickListener(this);
		confirmBtn.setOnClickListener(this);
		city_wide_screen_popLin = (LinearLayout) view
				.findViewById(R.id.city_wide_screen_popLin);
		barPressure = (SeekBarPressure) view.findViewById(R.id.seekBar_tg2);
		barPressure
				.setOnSeekBarPressureClickListener(new OnSeekBarPressureClickListener() {

					@Override
					public void send(int high, int low) {
						City_Wide_Screen_Pop.this.high = String.valueOf(high);
						City_Wide_Screen_Pop.this.low = String.valueOf(low);
						if (high == 999) {
							City_Wide_Screen_Pop.this.high = "10000000";
						}
					}
				});
		if (normal_use.equals("0")) {
			box1.setChecked(false);
		}else {
			box1.setChecked(true);
		}
		if (warranty_period.equals("0")) {
			box2.setChecked(false);
		}else {
			box2.setChecked(true);
		}
		if (no_repair.equals("0")) {
			box3.setChecked(false);
		}else {
			box3.setChecked(true);
		}
		if (brand_new.equals("0")) {
			box4.setChecked(false);
		}else {
			box4.setChecked(true);
		}
		if (jingdong.equals("0")) {
			box5.setChecked(false);
		}else {
			box5.setChecked(true);
		}
		if (mainland_licensed.equals("0")) {
			box6.setChecked(false);
		}else {
			box6.setChecked(true);
		}
		if (since.equals("0")) {
			box7.setChecked(false);
		}else {
			box7.setChecked(true);
		}
		// 自定义PopupWindow
		// int w = context.getWindowManager().getDefaultDisplay().getWidth();
		@SuppressWarnings({ "deprecation", "unused" })
		int h = activity.getWindowManager().getDefaultDisplay().getHeight();
		// 设置SelectPicPopupWindow的View
		this.setContentView(view);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.anim.activity_translate_in);
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent);
			city_wide_screen_popLin.setAlpha(0.95f);
		} else {
			this.dismiss();
			city_wide_screen_popLin.setAlpha(1f);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.city_wide_screen_pop_resetBtn:// 重置按钮
			box1.setChecked(false);
			box2.setChecked(false);
			box3.setChecked(false);
			box4.setChecked(false);
			box5.setChecked(false);
			box6.setChecked(false);
			box7.setChecked(false);
			break;
		case R.id.city_wide_screen_pop_confirmBtn:// 确定按钮
			if (box1.isChecked()) {
				normal_use = "1";
			} else {
				normal_use = "0";
			}
			if (box2.isChecked()) {
				warranty_period = "1";
			} else {
				warranty_period = "0";
			}
			if (box3.isChecked()) {
				no_repair = "1";
			} else {
				no_repair = "0";
			}
			if (box4.isChecked()) {
				brand_new = "1";
			} else {
				brand_new = "0";
			}
			if (box5.isChecked()) {
				jingdong = "1";
			} else {
				jingdong = "0";
			}
			if (box6.isChecked()) {
				mainland_licensed = "1";
			} else {
				mainland_licensed = "0";
			}
			if (box7.isChecked()) {
				since = "1";
			} else {
				since = "0";
			}
			lister.send(normal_use, warranty_period, no_repair, brand_new,
					jingdong, mainland_licensed, since, high, low);
			dismiss();
			break;

		default:
			break;
		}
	}

	public interface OnScreenWindowClickListener {
		void send(String normal_use, String warranty_period, String no_repair,
				String brand_new, String jingdong, String mainland_licensed,
				String since, String high, String low);
	}

	public void setOnScreenWindowClickListener(
			OnScreenWindowClickListener lister) {
		this.lister = lister;
	}
}
