package com.quwu.xinwo.until;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/** 
 * dp、sp 转换为 px 的工具类 
 *  
 * @author fxsky 2012.11.12 
 * 
 */  
public class DisplayUtil {  
    /** 
     * 将px值转换为dip或dp值，保证尺寸大小不变 
     *  
     * @param pxValue 
     * @param scale 
     *            （DisplayMetrics类中属性density） 
     * @return 
     */  
    public static int px2dip(Activity activity,Context context, float pxValue) {  
    	WindowManager windowManager = activity.getWindowManager();    
        Display display = windowManager.getDefaultDisplay();    
        int screenWidth = screenWidth = display.getWidth(); 
        int screenHeight = screenHeight = display.getHeight();
        System.out.println(screenWidth+"222");
        if (screenWidth==720) {
        	return (int) (pxValue/2);
		}
        else if (screenWidth==1080) {
        	return (int) (pxValue/3);
        }
        else  if (screenWidth==1440) {
        	return (int) (pxValue/4);
        }else {
        	return (int) (pxValue/1.5);
		}
    }  
  
    /** 
     * 将dip或dp值转换为px值，保证尺寸大小不变 
     *  
     * @param dipValue 
     * @param scale 
     *            （DisplayMetrics类中属性density） 
     * @return 
     */  
    public static int dip2px(Context context, float dipValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dipValue * scale + 0.5f);  
    }  
  
    /** 
     * 将px值转换为sp值，保证文字大小不变 
     *  
     * @param pxValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     * @param spValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int sp2px(Context context, float spValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }  
}  