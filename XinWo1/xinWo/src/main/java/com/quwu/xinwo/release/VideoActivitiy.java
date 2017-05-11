package com.quwu.xinwo.release;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.OSUtils;

public class VideoActivitiy extends Activity implements OnClickListener,
		SurfaceHolder.Callback {

	private RelativeLayout video_flashRel;
	private RelativeLayout video_deleteRel;
	private RelativeLayout video_changeRel;
	private RelativeLayout video_confirmRel;

	private Button video_playBtn;
	private Button video_flashBtn;

	private SurfaceView video_surfaceview;

	private TextView video_timeText;

	private boolean isflash = true;
	private boolean start = true;

	private Camera camera;
	private Parameters parameters;

	private MediaRecorder mediarecorder;// 录制视频的类
	private SurfaceHolder surfaceHolder;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		FinishActivity.finish(R.id.video_returnRel, VideoActivitiy.this);

		findID();
		isStartVideo();
	}

	private void findID() {
		video_flashRel = (RelativeLayout) findViewById(R.id.video_flashRel);
		video_deleteRel = (RelativeLayout) findViewById(R.id.video_deleteRel);
		video_changeRel = (RelativeLayout) findViewById(R.id.video_changeRel);
		video_confirmRel = (RelativeLayout) findViewById(R.id.video_confirmRel);

		

		video_flashBtn = (Button) findViewById(R.id.video_flashBtn);

		video_flashRel.setOnClickListener(this);


		
		video_timeText = (TextView) findViewById(R.id.video_timeText);

	}

	public void onClick(View v) {
	
		switch (v.getId()) {
		case R.id.video_flashRel:// 打开和关闭闪光灯
			if (isflash == true) {
				if (camera==null) {
					camera = Camera.open();
					parameters = camera.getParameters();
				}
				parameters.setFlashMode("torch");
				video_flashBtn
						.setBackgroundResource(R.drawable.sp_photoflash_pre);
				isflash = false;
			} else {
				parameters.setFlashMode("off");
				video_flashBtn.setBackgroundResource(R.drawable.sp_photoflash);
				isflash = true;
			}
			camera.setParameters(parameters);
			camera.startPreview();
			break;

		default:
			break;
		}

	}

	protected void onDestroy() {
		super.onDestroy();
		camera.release();
	}

	@SuppressWarnings("deprecation")
	private void isStartVideo() {
		video_playBtn = (Button) findViewById(R.id.video_playBtn);
		video_playBtn.setOnClickListener(new TestVideoListener());
		video_surfaceview = (SurfaceView) findViewById(R.id.video_surfaceview);
		SurfaceHolder holder = video_surfaceview.getHolder();// 取得holder
		holder.addCallback(this); // holder加入回调接口
		// setType必须设置，要不出错.
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	class TestVideoListener implements OnClickListener {
		public void onClick(View v) {
			if (start == true) {
				mediarecorder = new MediaRecorder();// 创建mediarecorder对象
				// 设置视频源
				mediarecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
				// 设置音频源
				mediarecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
				// 相机参数配置类
				CamcorderProfile cProfile = CamcorderProfile
						.get(CamcorderProfile.QUALITY_HIGH);
				mediarecorder.setProfile(cProfile);
				// 设置录制的视频帧率,注意文档的说明:
//				mediarecorder.setVideoFrameRate(15);
				mediarecorder.setMaxDuration(10000); // 最大期限
				mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());
				// // 设置视频文件输出的路径
				mediarecorder.setOutputFile(OSUtils.getSdCardDirectory(getApplicationContext())
						+ "/xinwo.3gp");
				try {
					// 准备录制
					mediarecorder.prepare();
					// 开始录制
					mediarecorder.start();
				} catch (IllegalStateException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				video_playBtn.setBackgroundResource(R.drawable.sp_stop);
				start = false;
			}
			if (start = false) {
				if (mediarecorder != null) {
					// 停止录制
					mediarecorder.stop();
					// 释放资源
					mediarecorder.release();
					mediarecorder = null;
				}
				video_playBtn.setBackgroundResource(R.drawable.sp_continue);
				start = true;
			}

		}

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
		surfaceHolder = holder;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
		surfaceHolder = holder;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// surfaceDestroyed的时候同时对象设置为null
		video_surfaceview = null;
		surfaceHolder = null;
		mediarecorder = null;
	}
}
