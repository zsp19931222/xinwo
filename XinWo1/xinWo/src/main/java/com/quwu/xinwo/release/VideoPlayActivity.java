package com.quwu.xinwo.release;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.until.CameraUtils;

public class VideoPlayActivity extends Activity {

	private VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.videoplay);
		videoView = (VideoView) findViewById(R.id.videoplay);
		videoView.setVideoURI(Uri.parse(CameraUtils.CAMERA_VIDEO));
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		videoView.setLayoutParams(layoutParams);
		videoView.start();
	}
	 public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if (event.getAction() == KeyEvent.ACTION_DOWN  
	                && KeyEvent.KEYCODE_BACK == keyCode) {  
	            finish();
	            return true;  
	        }
	  
	        return super.onKeyDown(keyCode, event);  
	    }  
}
