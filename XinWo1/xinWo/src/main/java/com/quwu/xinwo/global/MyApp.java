package com.quwu.xinwo.global;

import java.io.File;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.quwu.xinwo.baidumap.LocationService;
import com.quwu.xinwo.pickerview.DBManager;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class MyApp extends Application {

	private DBManager dbHelper;
	public LocationService locationService;
	public Vibrator mVibrator;
	public static IWXAPI api;
	
	/**
	 * 基地址
	 * */
	public static String base_address = "http://192.168.1.15:8080/Precordium/";
	/**
	 * 程序异常
	 * */
	public static int Program_Exception = 10000;
	/**
	 * 没有数据
	 * */
	public static int NODATA = 10001;
	/**
	 * 异常提示
	 * */
	public static String Program_Exception_Prompt = "服务器异常，请稍后重试...";
	/**
	 * 没有数据提示
	 * */
	public static String NODATA_Prompt = "没有数据了哦~";
	/**
	 * 定位省份
	 * */
	public static String Province;
	/**
	 * 定位城市
	 * */
	public static String City;
	/**
	 * 定位区县
	 * */
	public static String County;
	/**
	 * 定位成功失败(-1：正在定位中、0：定位成功、1定位失败)
	 * */
	public static int Location_Succeed=-1;
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());
		dbHelper = new DBManager(this);
		dbHelper.openDatabase();
		/***
		 * 初始化定位sdk，建议在Application中创建
		 */
		locationService = new LocationService(getApplicationContext());
		mVibrator = (Vibrator) getApplicationContext().getSystemService(
				Service.VIBRATOR_SERVICE);
		SDKInitializer.initialize(getApplicationContext());
		//微信
		api=WXAPIFactory.createWXAPI(this, "wxdb70678fd4306b57",true);
		api.registerApp("wxdb70678fd4306b57");
	}

	
	public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/心窝/");
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				context).diskCacheFileCount(100)
				.diskCacheExtraOptions(480, 320, null)
				.diskCache(new UnlimitedDiskCache(cacheDir)).threadPoolSize(5)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSize(50 * 1024 * 1024)
				// .memoryCache(new LargestLimitedMemoryCache(5))//空间占用最大的对象会被删除
				// .memoryCache(new FIFOLimitedMemoryCache(5))//根据先进先出的原则上删除多余对象
				// .memoryCache(new
				// UsingFreqLimitedMemoryCache(5))//最少被用到的对象会被删除
				// .memoryCache(
				// new LruMemoryCache(2 * 1024 * 1024))
				.build();
		ImageLoader.getInstance().init(configuration);
	}

}
