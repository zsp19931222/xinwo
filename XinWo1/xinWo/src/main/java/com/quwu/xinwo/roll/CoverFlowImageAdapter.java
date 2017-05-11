package com.quwu.xinwo.roll;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.quwu.xinwo.bean.AccountBean;
import com.quwu.xinwo.until.ScreenUtils;

public class CoverFlowImageAdapter extends BaseAdapter {
	private Context mContext;
	private Integer[] mImageIds = null;
	private Class<?>[] target = null;
	private Activity activity;
	private List<AccountBean> list;

	public Integer[] getmImageIds() {
		return mImageIds;
	}

	public Class<?>[] getTarget() {
		return target;
	}

	public CoverFlowImageAdapter(Context c, Integer[] mImageIds,
			Class<?>[] target, Activity activity, List<AccountBean> list) {
		mContext = c;
		this.mImageIds = mImageIds;
		this.target = target;
		this.activity = activity;
		this.list = list;
	}

	@Override
	public int getCount() {
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageView i = createReflectedImages(mContext, mImageIds[position],
				position);
		WindowManager wm = activity.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		i.setLayoutParams(new CoverFlow.LayoutParams(width / 4, width / 2));
		i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		// 设置的抗锯齿,防止图像在旋转的时候出现锯齿
		BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
		drawable.setAntiAlias(true);
//		lister.send(position);
		return i;
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}
	
	/**
	 * 为图片添加倒影
	 * 
	 * @param mContext
	 * @param imageId
	 * @return
	 */
	public ImageView createReflectedImages(Context mContext, int imageId,
			int position) {
		Bitmap originalImage = BitmapFactory.decodeResource(
				mContext.getResources(), imageId);
		final int reflectionGap = 4;
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(originalImage, 0, 0, null);

		Paint deafaultPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.MIRROR);

		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		if (ScreenUtils.getScreenWidth(mContext)==720) {
			Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setColor(Color.parseColor("#ffffff"));
			mPaint.setTextSize(60f);
			canvas.drawText(
					"\t\t" + list.get(position).getAccount_name(),0,
					120, mPaint);
			
			Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint1.setColor(Color.parseColor("#ffffff"));
			mPaint1.setTextSize(90f);
			canvas.drawText("\t\t" + list.get(position).getTransaction_num(), 0, 360,
					mPaint1);
			
//			Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//			mPaint2.setColor(Color.parseColor("#ffffff"));
//			mPaint2.setTextSize(60f);
//			canvas.drawText("\t" + list.get(position).getBank_card(), (int)(ScreenUtils.getScreenWidth(mContext)/5), (int)(ScreenUtils.getScreenHeight(mContext)/3),
//					mPaint2);
			
		}else if (ScreenUtils.getScreenWidth(mContext)==1080) {
			
			Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setColor(Color.parseColor("#ffffff"));
			mPaint.setTextSize(60f);
			canvas.drawText(
					"\t\t"+list.get(position).getAccount_name(),0,
					120, mPaint);
			
			Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint1.setColor(Color.parseColor("#ffffff"));
			mPaint1.setTextSize(90f);
			canvas.drawText("\t" + list.get(position).getTransaction_num(), 0, 300,
					mPaint1);
			
//			Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//			mPaint2.setColor(Color.parseColor("#ffffff"));
//			mPaint2.setTextSize(60f);
//			canvas.drawText("\t" + list.get(position).getBank_card(), (int)(ScreenUtils.getScreenWidth(mContext)/5), (int)(ScreenUtils.getScreenHeight(mContext)/5),
//					mPaint2);
			
		}else if (ScreenUtils.getScreenWidth(mContext)==1440) {
			
			Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setColor(Color.parseColor("#ffffff"));
			mPaint.setTextSize(60f);
			canvas.drawText(
"\t\t"+ list.get(position).getAccount_name(),0,
					120, mPaint);
			
			Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint1.setColor(Color.parseColor("#ffffff"));
			mPaint1.setTextSize(90f);
			canvas.drawText("\t" + list.get(position).getTransaction_num(), 0, 300,
					mPaint1);
			
//			Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//			mPaint2.setColor(Color.parseColor("#ffffff"));
//			mPaint2.setTextSize(60f);
//			canvas.drawText("\t" + list.get(position).getBank_card(), (int)(ScreenUtils.getScreenWidth(mContext)/5), (int)(ScreenUtils.getScreenHeight(mContext)/5),
//					mPaint2);
			
		}
		

		ImageView imageView = new ImageView(mContext);
		imageView.setImageBitmap(bitmapWithReflection);

		return imageView;
	}
}
