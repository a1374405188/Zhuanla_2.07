package com.beikbank.android.net.impl;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.ThreadManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 多个线程同时进行工具类
 */
public class ThreadManagerImpls extends ThreadManager
{  
	private  Handler handler;
    static String tag="ThreadManagerImpls";
    ThreadManagerSet set;
    @Override
   public void start()
   {   
	   Log.e(tag,icbn.getClass().getName()+":start");
	   execute2();
	   new LoadData().start();
   }
   public ThreadManagerImpls(Activity act,ThreadManagerSet tmset,ICallBackNet icbn,ICallBackHnadler icbh,String error_code)
   {   
	   
	   this.act=act;
	   set=tmset;
	   this.icbn=icbn;
	   this.icbh=icbh;
	   this.error_code=error_code;
	   if(this.error_code==null)
	   {
		   this.error_code="000";
	   }
   }
   public class LoadData extends Thread
   {   

	   
	   public LoadData()
	   {   

	   }
	   @Override
	   public void run()
	   {   
		   Message msg;
		   ErrorMessage em=new ErrorMessage();
		   try
		   {  
			    BaseActivity ba=(BaseActivity) act;
				if(!ba.isNetworkConnected(act))
			    {  
				   msg=new Message();
				   msg.what=HandlerBase.error2;
				   em.message=error_code+ErrorCodeInfo.ee61;
				   msg.obj=em;
				   handler.sendMessage(msg);
				   return ;
			    }
		        icbn.back(handler);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   msg=new Message();
			   msg.what=HandlerBase.error2;
			   em=new ErrorMessage();
			   String code=ErrorCode.getCodeFromException(e);
			   em.message=error_code+code;
			  // int a=Integer.parseInt(code);
//			   if(a>=60)
//			   {
//				   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.net_error,false);
//			   }
			   msg.obj=em;
			   handler.sendMessage(msg);
			   LogHandler.writeLogFromException(tag,e);
		   }
		   finally
			{
				 Log.e(tag,icbn.getClass().getName()+":sucess");
			}
	   }
   }
   
   
   //----------------------------------------------------------------------------
   public void execute2()
   {   
	   handler=new HandlerBase(act)
	   {
		  

		@Override
		public void handleMessage(Message msg) {
			try
			{
			 super.handleMessage(msg);
			
			 icbh.back(msg);
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
			 Log.e(tag,icbn.getClass().getName()+":handler");
			}
			catch(Exception e)
			{  
				e.printStackTrace();
				HandlerBase.showMsg(act,error_code+ErrorCodeInfo.ee41,0);
				LogHandler.writeLogFromException(tag+error_code, e);
			}
			finally
			{
				set.finish();
			}
		}
		     
	   };
   }
}
