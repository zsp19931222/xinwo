package com.quwu.xinwo.newphoto;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.release.Release_Activity;

public class MyAdapter extends CommonAdapter<String> {

	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	public static List<String> mSelectedImage = new LinkedList<String>();

	/**
	 * 文件夹路径
	 */
	private String mDirPath;
	private TextView textView;

	public MyAdapter(Context context, List<String> mDatas, int itemLayoutId,
			String dirPath, TextView view) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		this.textView = view;
	}

	@Override
	public void convert(final ViewHolder helper, final String item) {
		// 设置no_pic
		helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
		// 设置no_selected
		helper.setImageResource(R.id.id_item_select,
				R.drawable.picture_unselected);
		// 设置图片
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		// 设置ImageView的点击事件
		mImageView.setOnClickListener(new OnClickListener() {
			// 选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v) {
				// 已经选择过该图片
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					System.out.println("mDirPath----------------------"
							+ mDirPath);
					mSelectedImage.remove(mDirPath + "/" + item);
					Release_Activity.photolist.remove(mDirPath + "/" + item);
					textView.setText((Release_Activity.camearlist.size() + mSelectedImage
							.size()) + "张");
					mSelect.setImageResource(R.drawable.picture_unselected);
					mImageView.setColorFilter(null);
				} else
				// 未选择该图片
				{
					if ((Release_Activity.camearlist.size() + mSelectedImage
							.size()) < 10) {
						mSelectedImage.add(mDirPath + "/" + item);
						Release_Activity.photolist.add(mDirPath + "/" + item);
						mSelect.setImageResource(R.drawable.pictures_selected);
						mImageView.setColorFilter(Color.parseColor("#77000000"));
						textView.setText((Release_Activity.camearlist.size() + mSelectedImage
								.size()) + "张");
					} else {
						Toast.makeText(mContext, "最多选择10张图片哦",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setImageResource(R.drawable.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
}
