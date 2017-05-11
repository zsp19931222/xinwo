package com.quwu.xinwo.popupwindow;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.cropimage.CropHelper;
import com.quwu.xinwo.newphoto.MainActivity;

public class Release_Photo_Pop extends PopupWindow implements
		OnClickListener {
	private View view;
	private LinearLayout ll_popup;
	private TextView bt1, bt2, bt3;
	private Activity activity;
	private CropHelper mCropHelper = null;
	private String mTempPhotoPath;

	public Release_Photo_Pop(final Activity activity,CropHelper mCropHelper,String mTempPhotoPath) {
		this.activity = activity;
		this.mCropHelper=mCropHelper;
		this.mTempPhotoPath=mTempPhotoPath;
		LayoutInflater inflater = LayoutInflater.from(activity);
		view = inflater.inflate(R.layout.release_photo_pop, null);
		ll_popup = (LinearLayout) view.findViewById(R.id.release_photo_pop_Lin);
		bt1 = (TextView) view.findViewById(R.id.release_photo_pop_take_pictures);
		bt2 = (TextView) view.findViewById(R.id.release_photo_pop_select_photo);
		bt3 = (TextView) view.findViewById(R.id.release_photo_pop_select_cancel);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		ll_popup.setOnClickListener(this);
		// 自定义PopupWindow
		// int w = context.getWindowManager().getDefaultDisplay().getWidth();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.release_photo_pop_select_cancel:
			this.dismiss();
			break;
		case R.id.release_photo_pop_take_pictures:
//			ChooseDialog chooseDialog=new ChooseDialog(activity, mCropHelper);
//			chooseDialog.clickInCamera();
			Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// 下面这句指定调用相机拍照后的照片存储的路径
			File temp = new File(mTempPhotoPath);
			Log.e("2=",mTempPhotoPath);
			if (temp.exists()) {
				temp.delete();
				Log.e("创建文件=",temp.toString());
			}
			intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temp));
			Log.e("temp=",temp.toString());
			activity.startActivityForResult(intent1, 2017);
			dismiss();
			break;
		case R.id.release_photo_pop_select_photo:
			Intent intent = new Intent(activity, MainActivity.class);
			activity.startActivityForResult(intent, 10);
			dismiss();
			break;
		case R.id.release_photo_pop_Lin:
		dismiss();
			break;
		default:
			break;
		}
	}
	
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.CENTER_HORIZONTAL,
					Gravity.CENTER_HORIZONTAL, Gravity.CENTER_HORIZONTAL);
		} else {
			this.dismiss();
		}
	}

}
