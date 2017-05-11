/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {
	public OnScrollListener OnScrollListener;
	  /** 
 * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较 
 */  
private int lastScrollY;
	public PullToRefreshScrollView(Context context) {
		
		super(context);
	}

	public PullToRefreshScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullToRefreshScrollView(Context context, Mode mode) {
		super(context, mode);
	}

	public PullToRefreshScrollView(Context context, Mode mode,
			AnimationStyle style) {
		super(context, mode, style);
	}

	@Override
	public final Orientation getPullToRefreshScrollDirection() {
		return Orientation.VERTICAL;
	}

	@Override
	protected ScrollView createRefreshableView(Context context,
			AttributeSet attrs) {
		ScrollView scrollView;
		if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
			scrollView = new InternalScrollViewSDK9(context, attrs);
		} else {
			scrollView = new ScrollView(context, attrs);
		}

		scrollView.setId(R.id.scrollview);
		return scrollView;
	}

	@Override
	protected boolean isReadyForPullStart() {
		return mRefreshableView.getScrollY() == 0;
	}

	@Override
	protected boolean isReadyForPullEnd() {
		View scrollViewChild = mRefreshableView.getChildAt(0);
		if (null != scrollViewChild) {
			return mRefreshableView.getScrollY() >= (scrollViewChild
					.getHeight() - getHeight());
		}
		return false;
	}

	@TargetApi(9)
	final class InternalScrollViewSDK9 extends ScrollView {
		
		// 滑动距离及坐标
		private float xDistance, yDistance, xLast, yLast;
	
		/** 
	     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中 
	     */  
		private Handler handler = new Handler() {  
	  
	        public void handleMessage(android.os.Message msg) {  
//	            int scrollY = PullToRefreshScrollView.this.getScrollY();  
//	            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息  
//	              if(lastScrollY != scrollY){  
//	                  lastScrollY = scrollY;  
//	                  handler.sendMessageDelayed(handler.obtainMessage(), 5);    
//	              }  
//	              if(OnScrollListener != null){  
//	            	  OnScrollListener.onScroll(scrollY);  
//	            	  Log.e("3", "3=="+scrollY);
//	              }  
	        	switch (msg.what) {
				case 0:
					int scrollY=(Integer) msg.obj;
		              if(lastScrollY != scrollY){  
	                  lastScrollY = scrollY;  
	                  handler.sendMessageDelayed(handler.obtainMessage(0,scrollY),5);
	              }
	              if(OnScrollListener != null){
	            	  OnScrollListener.onScroll(scrollY);  
	              }
					break;
				default:
					break;
				}
	        };  
	    };
	    /** 
	     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候， 
	     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候， 
	     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理 
	     * MyScrollView滑动的距离 
	     */ 
		public boolean onTouchEvent(MotionEvent ev) {
			if(OnScrollListener != null){  
	            OnScrollListener.onScroll(lastScrollY =this.getScrollY());  
	        }
	        switch(ev.getAction()){
	        case MotionEvent.ACTION_MOVE:
//	        	handler.sendMessageDelayed(handler.obtainMessage(0,this.getScrollY()),0);
	        	break;
	        case MotionEvent.ACTION_DOWN:
//	        	handler.sendMessageDelayed(handler.obtainMessage(0,this.getScrollY()),0);
	        	break;
	        case MotionEvent.ACTION_UP:
//	        	handler.sendMessageDelayed(handler.obtainMessage(0,this.getScrollY()),1000);
	        	break;
	        	default :
	        		break;
	        }  
			return super.onTouchEvent(ev);
		}
		public InternalScrollViewSDK9(Context context) {
			this(context, null);
		}

		public InternalScrollViewSDK9(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public InternalScrollViewSDK9(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
		}

		@Override
		protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
				int scrollY, int scrollRangeX, int scrollRangeY,
				int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

			final boolean returnValue = super.overScrollBy(deltaX, deltaY,
					scrollX, scrollY, scrollRangeX, scrollRangeY,
					maxOverScrollX, maxOverScrollY, isTouchEvent);

			// Does all of the hard work...
			OverscrollHelper.overScrollBy(PullToRefreshScrollView.this, deltaX,
					scrollX, deltaY, scrollY, getScrollRange(), isTouchEvent);

			return returnValue;
		}

		private int getScrollRange() {
			int scrollRange = 0;
			if (getChildCount() > 0) {
				View child = getChildAt(0);
				scrollRange = Math.max(0, child.getHeight()
						- (getHeight() - getPaddingBottom() - getPaddingTop()));
			}
			return scrollRange;
		}

		@Override
		public boolean onInterceptTouchEvent(MotionEvent ev) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_UP:
	            break;
			case MotionEvent.ACTION_DOWN:
				xDistance = yDistance = 0f;
				xLast = ev.getX();
				yLast = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				final float curX = ev.getX();
				final float curY = ev.getY();
				xDistance += Math.abs(curX - xLast);
				yDistance += Math.abs(curY - yLast);
				xLast = curX;
				yLast = curY;
				if (xDistance > yDistance) {
					return false;
				}
				break;
			}
			return super.onInterceptTouchEvent(ev);
		}
	}
	  @Override
	  protected void onScrollChanged(int l, int t, int oldl, int oldt) {//滑动改变就会实时调用
	    super.onScrollChanged(l, t, oldl, oldt);
	    if(OnScrollListener != null){
	    	OnScrollListener.onScroll(t);
	    }
	  }
	/** 
     * 设置滚动接口 
     * @param onScrollListener 
     */
	public void setOnScrollListener(OnScrollListener OnScrollListener){
		this.OnScrollListener = OnScrollListener;
	}
	/** 
     * 滚动的回调接口 
     */  
    public interface OnScrollListener{  
        /** 
         * 回调方法， 返回MyScrollView滑动的Y方向距离 
         */  
        public void onScroll(int scrollY);  
    }
}
