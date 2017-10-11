package com.beikbank.android.net.impl;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.utils.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 *该类用于网络请求管理，同时只能执行一个子线程
 * 
 */
public class HandlerManager {
	private Dialog dialog;
	/**
	 * 该次网络请求完成
	 */
	private  static boolean isFinish=true;
	private Handler handler;
	private Activity act;
	private ICallBackHnadler icbh;
	private String error_code;
	String tag="HandlerManager";
   public static synchronized Handler execute(Activity act,ICallBackHnadler icbh,String error_code)
   {
          Handler h=new HandlerManager(act, icbh, error_code).execute2();
          return h;
   }
   public HandlerManager(Activity act,ICallBackHnadler icbh,String error_code)
   {
	     this.act=act;
	     this.icbh=icbh;
	     this.error_code=error_code;
   }
   public Handler execute2()
   {   
	   if(!isFinish)
	   {
		   return null;
	   }
	   isFinish=false;
	   dialog=Utils.createPorgressDialog(act, null);
	   dialog.show();
	   handler=new HandlerBase(act)
	   {
		  

		@Override
		public void handleMessage(Message msg) {
			try
			{
			if(dialog!=null)
			{
				dialog.dismiss();
			}
			super.handleMessage(msg);
			//icbh.back(msg);
//			TotalMoney_data ud=null;
//			if(msg.obj!=null&&msg.obj instanceof TotalMoney_data)
//			{
//				ud=(TotalMoney_data) msg.obj;
//			}
//			if(msg.what!=success1)
//			{
//				 icbh.back(ud);
//			}
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case success1:
//				  icbh.back(ud);
//				break;
//			}
			 isFinish=true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				LogHandler.writeLogFromException(tag+error_code, e);
			}
		}
		     
	   };
	   return handler;
   }
}
