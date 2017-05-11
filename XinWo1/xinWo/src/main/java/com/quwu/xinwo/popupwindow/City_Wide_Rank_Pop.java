package com.quwu.xinwo.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;

public class City_Wide_Rank_Pop extends PopupWindow implements OnClickListener{
	private View view;
	private Activity activity;
	private LayoutInflater inflater;
	private RelativeLayout relativeLayout;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private RelativeLayout layout1;
	private RelativeLayout layout2;
	private RelativeLayout layout3;
	private RelativeLayout layout4;
	public City_Wide_Rank_Pop(final Activity activity) {
		this.activity = activity;
		inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.city_wide_rank_pop, null);
		relativeLayout=(RelativeLayout) view.findViewById(R.id.city_wide_rank_popRel);
		textView1=(TextView) view.findViewById(R.id.city_wide_rank_pop_Text1);
		textView2=(TextView) view.findViewById(R.id.city_wide_rank_pop_Text2);
		textView3=(TextView) view.findViewById(R.id.city_wide_rank_pop_Text3);
		textView4=(TextView) view.findViewById(R.id.city_wide_rank_pop_Text4);
		imageView1=(ImageView) view.findViewById(R.id.city_wide_rank_pop_yesImage1);
		imageView2=(ImageView) view.findViewById(R.id.city_wide_rank_pop_yesImage2);
		imageView3=(ImageView) view.findViewById(R.id.city_wide_rank_pop_yesImage3);
		imageView4=(ImageView) view.findViewById(R.id.city_wide_rank_pop_yesImage4);
		layout1=(RelativeLayout) view.findViewById(R.id.city_wide_rank_pop_Rel1);
		layout2=(RelativeLayout) view.findViewById(R.id.city_wide_rank_pop_Rel2);
		layout3=(RelativeLayout) view.findViewById(R.id.city_wide_rank_pop_Rel3);
		layout4=(RelativeLayout) view.findViewById(R.id.city_wide_rank_pop_Rel4);
		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
		relativeLayout.setOnClickListener(this);
		
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
			view.setAlpha(0.95f);
		} else {
			this.dismiss();
			view.setAlpha(1f);
		}
	}

	public void onClick(View v) {
		initImage_Text();
		switch (v.getId()) {
		case R.id.city_wide_rank_pop_Rel1:
			imageView1.setVisibility(View.VISIBLE);
			textView1.setTextColor(activity.getResources().getColor(R.color.橘色));
			break;
		case R.id.city_wide_rank_pop_Rel2:
			imageView2.setVisibility(View.VISIBLE);
			textView2.setTextColor(activity.getResources().getColor(R.color.橘色));
			break;
		case R.id.city_wide_rank_pop_Rel3:
			imageView3.setVisibility(View.VISIBLE);
			textView3.setTextColor(activity.getResources().getColor(R.color.橘色));
			break;
		case R.id.city_wide_rank_pop_Rel4:
			imageView4.setVisibility(View.VISIBLE);
			textView4.setTextColor(activity.getResources().getColor(R.color.橘色));
			break;
		case R.id.city_wide_rank_popRel:
			this.dismiss();
			break;

		default:
			break;
		}
	}
	private void initImage_Text() {
imageView1.setVisibility(View.INVISIBLE);
imageView2.setVisibility(View.INVISIBLE);
imageView3.setVisibility(View.INVISIBLE);
imageView4.setVisibility(View.INVISIBLE);
textView1.setTextColor(activity.getResources().getColor(R.color.黑字体色));
textView2.setTextColor(activity.getResources().getColor(R.color.黑字体色));
textView3.setTextColor(activity.getResources().getColor(R.color.黑字体色));
textView4.setTextColor(activity.getResources().getColor(R.color.黑字体色));
	}
	
}
