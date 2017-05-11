package com.quwu.xinwo.imageloader;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.quwu.xinwo.R;

public class DisplayImageOptionsUtils {
	/**
	 * 解决图片过大
	 */
	public static DisplayImageOptions ImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pic_1_to_1)// 正在下载的时候显示的图片
				.showImageOnFail(R.drawable.pic_1_to_1)// 解析失败时显示的图片
				.showImageForEmptyUri(R.drawable.pic_1_to_1)// uri为空时显示的图片
				.cacheInMemory(false)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(false)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true)// 启用EXIF和JPEG图像格式
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				// .bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		return options;
	}

	public static DisplayImageOptions ImageOptions3() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pic_1_to_1)// 正在下载的时候显示的图片
				.showImageOnFail(R.drawable.pic_1_to_1)// 解析失败时显示的图片
				.showImageForEmptyUri(R.drawable.pic_1_to_1)// uri为空时显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true)// 启用EXIF和JPEG图像格式
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				// .bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		return options;
	}

	public static DisplayImageOptions ImageOptions1() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pic_1_to_1)
				// 正在下载的时候显示的图片
				.showImageOnFail(R.drawable.pic_1_to_1)
				// 解析失败时显示的图片
				.showImageForEmptyUri(R.drawable.pic_1_to_1)
				// uri为空时显示的图片
				.cacheInMemory(false)
				// //设置下载的图片是否缓存在内存中
				.cacheOnDisk(false)
				// 设置下载的图片是否缓存在SD卡中
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.resetViewBeforeLoading(false)
				.considerExifParams(true)// 启用EXIF和JPEG图像格式
				// .bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		return options;
	}

	public static DisplayImageOptions ImageOptions2() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pic_1_to_1)
				// 正在下载的时候显示的图片
				.showImageOnFail(R.drawable.pic_1_to_1)
				// 解析失败时显示的图片
				.showImageForEmptyUri(R.drawable.pic_1_to_1)
				// uri为空时显示的图片
				.cacheInMemory(true)
				// //设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)
				// 设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.considerExifParams(true)// 启用EXIF和JPEG图像格式
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		return options;
	}

}
