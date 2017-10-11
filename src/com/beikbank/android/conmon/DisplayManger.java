package com.beikbank.android.conmon;

import android.app.Activity;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-4
 **/
public class DisplayManger {
    /**
     * 屏幕宽度
     */
	private static int width;
	/**
	 * 屏幕高度
	 */
	private static int height;

    public static int getWidth(Activity act)
    {
    	if(width!=0)
    	{
    		return width;
    	}
    	else
    	{
    		init(act);
    	}
    	return width;
    }
    public static int getHeight(Activity act)
    {
    	if(height!=0)
    	{
    		return height;
    	}
    	else
    	{
    		init(act);
    	}
    	return height;
    }
   public static void init(Activity act)
   {
	   int temp=0;
	   width=act.getWindowManager().getDefaultDisplay().getWidth();
	   height=act.getWindowManager().getDefaultDisplay().getHeight();
   }
}
