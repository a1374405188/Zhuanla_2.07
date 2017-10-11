package com.beikbank.android.widget;

import com.beikbank.android.conmon.DisplayManger;
import com.beikbank.android.utils.DensityUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-4
 **/
public class FirstLoginWindows {
	/**
	 * 添加一个窗口在当前的activity上
	 * @param act
	 * @param resource
	 */
	@SuppressLint("NewApi") public static View addView(final Activity act,int resource)
	{   
		LayoutInflater lf=act.getLayoutInflater();
		final View view=lf.inflate(resource,null);
		LayoutParams lp=new LayoutParams();
		lp.x=0;
		lp.y=-20;
		lp.width=DisplayManger.getWidth(act);
		lp.height=DisplayManger.getHeight(act)+20;
		lp.format=PixelFormat.RGBA_8888;
		lp.systemUiVisibility=View.STATUS_BAR_HIDDEN;
		lp.flags=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        act.getWindowManager().addView(view,lp);
        
        view.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) {
				act.getWindowManager().removeView(view);
			}
		});
        return view;
	}
	/**
	 * 添加一个窗口在当前的activity上
	 * @param act
	 * @param resource
	 */
	public static View addView2(final Activity act,int resource)
	{   
		LayoutInflater lf=act.getLayoutInflater();
		final View view=lf.inflate(resource,null);
		LayoutParams lp=new LayoutParams();
		lp.x=0;
		lp.y=0;
		lp.width=DisplayManger.getWidth(act);
		lp.height=DisplayManger.getHeight(act);
		lp.format=PixelFormat.RGBA_8888;
		
        act.getWindowManager().addView(view,lp);
       
        return view;
	}
	
	/**
	 * 添加一个窗口在当前的activity上
	 * @param act
	 * @param resource
	 */
	public static View addView3(final Activity act,View view)
	{   
		
		DensityUtil du=new DensityUtil(act);
		int height=du.dip2px(30);
		int height2=du.dip2px(44);
		LayoutParams lp=new LayoutParams();
		lp.x=0;
		lp.y=height2;
		lp.width=DisplayManger.getWidth(act);
		lp.height=height;
		lp.format=PixelFormat.RGBA_8888;
		lp.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
		lp.gravity=Gravity.TOP;
        act.getWindowManager().addView(view,lp);
       
        return view;
	}
}
