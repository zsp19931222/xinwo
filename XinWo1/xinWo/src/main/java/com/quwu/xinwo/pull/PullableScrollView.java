package com.quwu.xinwo.pull;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


public class PullableScrollView extends ScrollView implements Pullable{
	private OnScrollListener onScrollListener;
	private ScrollViewListener scrollViewListener = null;
	private boolean canScroll;
	View.OnTouchListener mGestureListener;
	private GestureDetector mGestureDetector;
	private List<View> views = new ArrayList<View>();
	

	public PullableScrollView(Context context) {
		super(context);
	}

	@SuppressWarnings("deprecation")
	public PullableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		canScroll = true;
	}

	public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown() {
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp() {
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}

	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		// l oldl 分别代表水平位移
		// t oldt 代表当前左上角距离Scrollview顶点的距离
		if (onScrollListener != null) {
			onScrollListener.onScroll(t);
		}
		// 监听到底部
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
		}
	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	public interface OnScrollListener {
		public void onScroll(int scrollY);
	}
	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if(canScroll)
				if (Math.abs(distanceY) >= Math.abs(distanceX)){
					canScroll = true;
					}
				else{
					canScroll = false;
					}
			return canScroll;
		}
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		   if (views != null && checkAllViews(views, ev)) {
	            return false;
	        }
//		if(ev.getAction() == MotionEvent.ACTION_UP)
//			canScroll = true;
//		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
		   return super.onInterceptTouchEvent(ev);
	}
	 public void addUnTouchableView(View view) {
	        try {
	            if (!views.contains(view)) {
	                views.add(view);
	            }
	        } catch (Exception e) {
	            if (e != null) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void delUnTouchableView(View view) {
	        try {
	            if (views.contains(view)) {
	                views.remove(view);
	            }

	        } catch (Exception e) {
	            if (e != null) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void delAllUnTouchableView() {
	        try {
	            if (views.size() > 0) {
	                views.clear();
	            }

	        } catch (Exception e) {
	            if (e != null) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private boolean checkAllViews(List<View> views, MotionEvent event) {
	        for (View view : views) {
	            if (checkInLvArea(view, event)) {
	                return true;
	            }
	        }
	        return false;
	    }


	    private boolean checkInLvArea(View v, MotionEvent event) {
	        try {
	            float x = event.getRawX();
	            float y = event.getRawY();
	            int[] locate = new int[2];
	            v.getLocationOnScreen(locate);
	            int l = locate[0];
	            int r = l + v.getWidth();
	            int t = locate[1];
	            int b = t + v.getHeight();
	            if (l < x && x < r && t < y && y < b) {
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            if (e != null) {
	                e.printStackTrace();
	            }
	        }
	        return false;

	    }


	    
}
