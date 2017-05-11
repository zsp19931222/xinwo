package com.quwu.xinwo.until;

import java.io.File;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;

import com.nostra13.universalimageloader.utils.StorageUtils;

public class CameraUtils {
	public static final String CAMERA_IMAGE = "/sdcard/xinwo.jpg";

	public static final String CAMERA_VIDEO = "/sdcard/心窝/xinwo.mp4";

	public static void pickImageFromSystem(Activity activity) {
		/**
		 * 参数一:打开系统相册的ACTION 参数二:返回数据的方式(从系统相册的数据库获取)
		 */
		Intent intent = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		activity.startActivityForResult(intent,
				RequestCode.FLAG_REQUEST_SYSTEM_IMAGE);
	}

	public static String getImagePathFromSystem(Activity activity, Intent data) {
		// TODO 处理从相册返回的图片数据
		Uri uri = data.getData();// 使用getData方法获取要调用的接口
		// 第二个参数表示要查询的数据的字段名
		Cursor c = activity.getContentResolver()
				.query(uri, new String[] { MediaStore.Images.Media.DATA },
						null, null, null);
		if (c != null) {
			c.moveToFirst();
			// 通过游标来获取名为MediaStore.Images.Media.DATA字段的值
			String path = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.DATA));
			return path;
		}
		return null;
	}

	public static void openCameraForImage(Activity activity) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 仅当设置了MediaStore.EXTRA_OUTPUT参数时,系统将不再返回缩略图,而是会被完整保存以下路径
		intent.putExtra(MediaStore.EXTRA_OUTPUT,
				Uri.fromFile(new File(StorageUtils
						.getOwnCacheDirectory(activity, "/心窝/"), CAMERA_VIDEO)));
		activity.startActivityForResult(intent,
				CameraUtils.RequestCode.FLAG_REQUEST_CAMERA_IMAGE);
	}

	public static void openCameraForVideo(Activity activity) {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		// 仅当设置了MediaStore.EXTRA_OUTPUT参数时,系统将不再返回缩略图,而是会被完整保存以下路径
		intent.putExtra(MediaStore.EXTRA_OUTPUT,
				Uri.fromFile(new File(CAMERA_VIDEO)));
		intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
		activity.startActivityForResult(intent,
				RequestCode.FLAG_REQUEST_CAMERA_VIDEO);
	}
	public static String getRealFilePath( final Context context, final Uri uri ) {
	    if ( null == uri ) return null;
	    final String scheme = uri.getScheme();
	    String data = null;
	    if ( scheme == null )
	        data = uri.getPath();
	    else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
	        data = uri.getPath();
	    } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
	        Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
	        if ( null != cursor ) {
	            if ( cursor.moveToFirst() ) {
	                int index = cursor.getColumnIndex( ImageColumns.DATA );
	                if ( index > -1 ) {
	                    data = cursor.getString( index );
	                }
	            }
	            cursor.close();
	        }
	    }
	    return data;
	}
	public interface RequestCode {
		int FLAG_REQUEST_SYSTEM_IMAGE = 3;

		int FLAG_REQUEST_CAMERA_IMAGE = 4;

		int FLAG_REQUEST_CAMERA_VIDEO = 5;
	}
}