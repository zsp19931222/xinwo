package com.quwu.xinwo.imageloader;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.quwu.xinwo.mywight.CircleImageView;

public class ImageloaderUtils {

	public static void MyImageLoader(String url, ImageView imageView) {
		ImageLoader.getInstance().displayImage(url, imageView,
				DisplayImageOptionsUtils.ImageOptions());
	}

//	public static void MyImageLoader3(String url, final AdaptiveImageView  imageView,
//			Activity activity) {
//	ImageLoader.getInstance().displayImage(url, imageView,DisplayImageOptionsUtils.ImageOptions3());
//	}

	public static void MyImageLoader2(String url, ImageView imageView,
			Context context) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		// File cacheDir = StorageUtils
		// .getOwnCacheDirectory(context, "/Photo_LJ/");
		// @SuppressWarnings("deprecation")
		// ImageLoaderConfiguration configuration = new
		// ImageLoaderConfiguration.Builder(
		// context).diskCacheFileCount(1000)
		// .discCache(new UnlimitedDiskCache(cacheDir))
		// // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
		// .build();
		// imageLoader.init(configuration);
		imageLoader.displayImage(url,  imageView,
				DisplayImageOptionsUtils.ImageOptions());// 显示图片
	}
	public static void BannerImageLoader(String url, ImageView imageView,
			Context context) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		// File cacheDir = StorageUtils
		// .getOwnCacheDirectory(context, "/Photo_LJ/");
		// @SuppressWarnings("deprecation")
		// ImageLoaderConfiguration configuration = new
		// ImageLoaderConfiguration.Builder(
		// context).diskCacheFileCount(1000)
		// .discCache(new UnlimitedDiskCache(cacheDir))
		// // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
		// .build();
		// imageLoader.init(configuration);
		imageLoader.displayImage(url,  imageView,
				DisplayImageOptionsUtils.ImageOptions3());// 显示图片
	}

	public static void MyImageLoader3(String url, ImageView imageView,
			Context context) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		// File cacheDir = StorageUtils
		// .getOwnCacheDirectory(context, "/Photo_LJ/");
		// @SuppressWarnings("deprecation")
		// ImageLoaderConfiguration configuration = new
		// ImageLoaderConfiguration.Builder(
		// context).diskCacheFileCount(1000)
		// .discCache(new UnlimitedDiskCache(cacheDir))
		// // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
		// .build();
		// imageLoader.init(configuration);
		imageLoader.displayImage(url, imageView,
				DisplayImageOptionsUtils.ImageOptions1());// 显示图片
	}

	public static void MyImageLoader4(String url, ImageView imageView,
			Context context) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		// File cacheDir = StorageUtils
		// .getOwnCacheDirectory(context, "/Photo_LJ/");
		// @SuppressWarnings("deprecation")
		// ImageLoaderConfiguration configuration = new
		// ImageLoaderConfiguration.Builder(
		// context).diskCacheFileCount(1000)
		// .discCache(new UnlimitedDiskCache(cacheDir))
		// // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
		// .build();
		// imageLoader.init(configuration);
		imageLoader.displayImage(url, imageView,
				DisplayImageOptionsUtils.ImageOptions2());// 显示图片
	}
	public static void MyImageLoaderCircleImageView(String url, CircleImageView imageView,
			Context context) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		// File cacheDir = StorageUtils
		// .getOwnCacheDirectory(context, "/Photo_LJ/");
		// @SuppressWarnings("deprecation")
		// ImageLoaderConfiguration configuration = new
		// ImageLoaderConfiguration.Builder(
		// context).diskCacheFileCount(1000)
		// .discCache(new UnlimitedDiskCache(cacheDir))
		// // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
		// .build();
		// imageLoader.init(configuration);
		imageLoader.displayImage(url, imageView,
				DisplayImageOptionsUtils.ImageOptions2());// 显示图片
	}

}
