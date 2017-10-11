package com.beikbank.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class DensityUtil {

    private  float dmDensityDpi = 0.0f;
    private  DisplayMetrics dm;
    private  float scale = 0.0f;
 
    
    public DensityUtil(Context context) {
        dm = new DisplayMetrics();
 
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        dmDensityDpi=dm.densityDpi;
        scale = getDmDensityDpi() / 160;

    }
    public  int getScreenWidth(){
    	return dm.widthPixels;
    }
    public  int getScreenHeight(){
    	return dm.heightPixels;
    }
    
    
    public  float getDmDensityDpi() {
        return dmDensityDpi;
    }
    
    public  int dip2px(float dipValue) {
 
        return (int) (dipValue * scale + 0.5f);
 
    }
 
    
    public int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }
 
    @Override
    public String toString() {
        return " dmDensityDpi:" + dmDensityDpi;
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
    /** 
     *  得到状态栏的高度
     * @param activity 
     * @return > 0 success; <= 0 fail 
     */  
    public static int getStatusHeight(Activity activity){  
        int statusHeight = 0;  
        Rect localRect = new Rect();  
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);  
        statusHeight = localRect.top;  
        if (0 == statusHeight){  
            Class<?> localClass;  
           try {  
                localClass = Class.forName("com.android.internal.R$dimen");  
                Object localObject = localClass.newInstance();  
               int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());  
                statusHeight = activity.getResources().getDimensionPixelSize(i5);  
            } catch (ClassNotFoundException e) {  
               e.printStackTrace();  
            } catch (IllegalAccessException e) {  
               e.printStackTrace();  
             }
           catch (Exception e) {
			  e.printStackTrace();
		    }
        }  
        return statusHeight;  
    }  
}
