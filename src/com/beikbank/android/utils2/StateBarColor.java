package com.beikbank.android.utils2;

import java.lang.reflect.Field;

import u.aly.co;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;



/**
 * 
 * @author Administrator
 *处理状态栏颜色
 */

public class StateBarColor {
    @TargetApi(Build.VERSION_CODES.KITKAT) 
    public static void init(Activity act,int color)
    {   
    	if(color==0xffffffff)
    	{
    		return;
    	}
    	Window window = act.getWindow();
    	//window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    	 window.setFlags(  
                 WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,  
                 WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
    	
    	
//    	 ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
//         View statusBarView = new View(window.getContext());
//         int statusBarHeight = getStatusBarHeight(window.getContext());
//         FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
//         params.gravity = Gravity.TOP;
//         statusBarView.setLayoutParams(params);
         //statusBarView.setBackgroundColor(color);
         //decorViewGroup.addView(statusBarView);
         
         
//         ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
//         View mChildView = mContentView.getChildAt(0);
//         if (mChildView != null) {
//             //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
//             mChildView.setFitsSystemWindows(true);
//         }
    }
    
    public static void init2(Activity act,int color)
    {   
    	
    	Window window = act.getWindow();
    	//window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    	 window.setFlags(  
                 WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,  
                 WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
    	
    	
//    	 ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
//         View statusBarView = new View(window.getContext());
//         int statusBarHeight = getStatusBarHeight(window.getContext());
//         FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
//         params.gravity = Gravity.TOP;
//         statusBarView.setLayoutParams(params);
         //statusBarView.setBackgroundColor(color);
         //decorViewGroup.addView(statusBarView);
         
         
//         ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
//         View mChildView = mContentView.getChildAt(0);
//         if (mChildView != null) {
//             //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
//             mChildView.setFitsSystemWindows(true);
//         }
    }
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
 

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }
}
