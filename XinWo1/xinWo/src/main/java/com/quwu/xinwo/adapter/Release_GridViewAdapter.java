package com.quwu.xinwo.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quwu.xinwo.R;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.newphoto.MyAdapter;
import com.quwu.xinwo.release.Release_Activity;
import com.quwu.xinwo.until.CameraUtils;
import com.quwu.xinwo.until.ScreenUtils;
import com.quwu.xinwo.until.Tool;

public class Release_GridViewAdapter extends BaseAdapter implements SurfaceHolder.Callback{

	private List<String> list;
	private Context context;
	private LayoutInflater inflater;
	private Activity activity;
	private List<Long> times;
	private MediaPlayer player;
	private  SurfaceHolder surfaceHolder;

	private ViewHolder holder = null;

	public Release_GridViewAdapter(List<String> list, Context context,
			Activity activity, List<Long> times) {
		super();
		this.list = list;
		this.context = context;
		this.activity = activity;
		this.times = times;
		
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater
					.inflate(R.layout.release_gridview_item, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.release_gridview_item_image);
			holder.button = (Button) convertView
					.findViewById(R.id.release_gridview_item_deleteBtn);
			holder.play = (ImageView) convertView
					.findViewById(R.id.release_gridview_item_play);
			holder.deleteLin=(LinearLayout) convertView.findViewById(R.id.release_gridview_item_deleteLin);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.button.setTag(position);
		holder.play.setTag(position);
		holder.deleteLin.setTag(position);
		int w= ScreenUtils.getScreenWidth(context);
		int i=w/5-10;
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(i, i);
		convertView.setLayoutParams(lp);
		convertView.setTag(holder);
		if (list.size() != 0) {
			if (!list.get(position).equals("")) { 
				if (list.get(position).equals("drawable://" + R.drawable.fb_icn_carema)) {//显示添加图片按钮
					ImageloaderUtils.MyImageLoader2("drawable://"+R.drawable.fb_icn_carema, holder.imageView,context);
					holder.button.setVisibility(View.GONE);
					holder.play.setVisibility(View.GONE);
					holder.deleteLin.setFocusable(false);
					holder.deleteLin.setClickable(false);
				} else if (list.get(position).equals("drawable://" + R.drawable.fb_icn_video)) {//显示添加视频按钮
					ImageloaderUtils.MyImageLoader2("drawable://"+R.drawable.fb_icn_video, holder.imageView,context);
					holder.button.setVisibility(View.GONE);
					holder.play.setVisibility(View.GONE);
					holder.deleteLin.setFocusable(false);
					holder.deleteLin.setClickable(false);
				} else if (list.get(position).equals(CameraUtils.CAMERA_VIDEO)) {
					ImageloaderUtils.MyImageLoader2("file:///" +CameraUtils.CAMERA_VIDEO, holder.imageView,context);
					holder.play.setVisibility(View.VISIBLE);
					holder.deleteLin.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (Tool.isFastDoubleClick()) {
								return;
							}else {
							int arg2 = (Integer) v.getTag();
							Log.e("", arg2+"");
							for (int i = 0; i < list.size(); i++) {
								if (list.get(arg2).equals(CameraUtils.CAMERA_VIDEO)) {
									list.remove(arg2);
									notifyDataSetChanged();
								}
							}
						}
						}
					});
				} else {
					ImageloaderUtils.MyImageLoader2("file:///" + list.get(position), holder.imageView,context);
					holder.play.setVisibility(View.GONE);
					holder.deleteLin.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (Tool.isFastDoubleClick()) {
								return;
							}else {
							int arg2 = (Integer) v.getTag();
							Log.e("", arg2+"");
							for (int i = 0; i < times.size(); i++) {
								if (!times.get(i).equals(Release_Activity.time)) {
									times.remove(times.get(i));
								}
							}
							Release_Activity.camearlist.remove(list.get(arg2));
							MyAdapter.mSelectedImage.remove(list.get(arg2));
							list.remove(arg2);
							for (int k = 0; k < Release_Activity.camearlist
									.size() - 1; k++) {
								for (int j = Release_Activity.camearlist.size() - 1; j > k; j--) {
									if (Release_Activity.camearlist.get(k)
											.equals(Release_Activity.camearlist
													.get(j))) {
										Release_Activity.camearlist.remove(j);
									}
								}
							}
							notifyDataSetChanged();
						}
						}
					});
				}
				
			}
		}
		return convertView;
	}

	 class ViewHolder {

		private ImageView imageView;
		private Button button;
		private ImageView play;
		private LinearLayout deleteLin;
	}

	  @Override
	    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	    }
	 
	    @Override
	    public void surfaceCreated(SurfaceHolder arg0) {
	//必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
	        player=new MediaPlayer();
	        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	        player.setDisplay(surfaceHolder);
	        //设置显示视频显示在SurfaceView上
	            try {
	                player.setDataSource(CameraUtils.CAMERA_VIDEO);
	                player.prepare();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	    }
	 
	    @Override
	    public void surfaceDestroyed(SurfaceHolder arg0) {
	        // TODO Auto-generated method stub
	 
	    }
	 
//	    @Override
//	    protected void onDestroy() {
//	        // TODO Auto-generated method stub
//	        super.onDestroy();
//	        if(player.isPlaying()){
//	        player.stop();
//	        }
//	        player.release();
//	        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
//	    }

}
