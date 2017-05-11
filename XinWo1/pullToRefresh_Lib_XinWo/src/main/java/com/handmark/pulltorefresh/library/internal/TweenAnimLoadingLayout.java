package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

/** 
 * @date 2015/1/8 
 * @author wuwenjie 
 * @desc 帧动画加载布局 
 */  
public class TweenAnimLoadingLayout extends LoadingLayout {  
  
    private AnimationDrawable animationDrawable;  
  
    public TweenAnimLoadingLayout(Context context, Mode mode,  
            Orientation scrollDirection, TypedArray attrs) {  
        super(context, mode, scrollDirection, attrs);  
        // 初始化  
        mHeaderImage.setImageResource(R.drawable.loading1);  
        animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();  
    }  
    // 默认图片  
    protected int getDefaultDrawableResId() {  
        return R.drawable.pika1;  
    }  
      
    protected void onLoadingDrawableSet(Drawable imageDrawable) {  
        // NO-OP  
    }  
      
    protected void onPullImpl(float scaleOfLayout) {  
        // NO-OP  
    }  
    // 下拉以刷新  
    protected void pullToRefreshImpl() {  
        // NO-OP  
    }  
    // 正在刷新时回调  
    protected void refreshingImpl() {  
        // 播放帧动画  
        animationDrawable.start();  
    }  
    // 释放以刷新  
    protected void releaseToRefreshImpl() {  
        // NO-OP  
    }  
    // 重新设置  
    protected void resetImpl() {  
        mHeaderImage.setVisibility(View.VISIBLE);  
        mHeaderImage.clearAnimation();  
    }
	public void setPullLoadingDrawableVisibility(int visibility) {
		// TODO Auto-generated method stub
		
	}
	public void setRefreshingLoadingDrawableVisibility(int visibility) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setReleaseLoadingDrawableVisibility(int visibility) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPullImageResource(int resourceId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRefreshingImageResource(int resourceId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setReleaseImageResource(int resourceId) {
		// TODO Auto-generated method stub
		
	}  
  
}  