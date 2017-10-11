package com.beikbank.android.net;

import android.app.Activity;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-21
 * 
 */
public abstract class ThreadManager {
	public Activity act;
	public String error_code="";
	public ICallBackNet icbn;
	public ICallBackHnadler icbh;
	public ICallBack icb;
	/**
	 * 
	 */
   public void start(){};
   /**
    * 该次请求完成
    */
   public void finish(){};
}
