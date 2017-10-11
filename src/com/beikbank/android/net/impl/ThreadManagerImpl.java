package com.beikbank.android.net.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.activity.BaseActivity1;

import com.beikbank.android.exception.GlobalExceptionHandler;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 
 */
public class ThreadManagerImpl 
{      
	   /**
	    * 是否显示进度条
	    */
	   boolean b=true;
	   private Activity act;
	   private  ICallBackNet icb;
	   private  Handler handler;
	   public String error_code;
       static String tag="ThreadManager";
       /**
        * 
        * @param act 
        * @param icbn
        * @param icbh
        * @param error_code
        */
   public static synchronized void execute(Activity act,ICallBackNet icbn,ICallBackHnadler icbh,String error_code,boolean isShow)
   {    
//	   if(!isFinish)
//	   {   
//		   Log.e(tag,icbn.getClass().getName()+":false");
//		   return;
//	   }
//	   else
//	   {
//		   Log.e(tag,icbn.getClass().getName()+":start");
//	   }
	  // isFinish=false;
	   ThreadManagerImpl tm= new ThreadManagerImpl(act,icbn,icbh,error_code,isShow);
	   tm.execute2();
	   tm.new LoadData().start();
   }
   public ThreadManagerImpl(Activity act,ICallBackNet icb,ICallBackHnadler icbh,String error_code,boolean isShow)
   {
	   this.act=act;
	   this.icb=icb;
	   this.icbh=icbh;
	   this.error_code=error_code;
	   if(this.error_code==null)
	   {
		   this.error_code="000";
	   }
	   b=isShow;
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
				   msg.what=HandlerBase.error1;
				   em.message=error_code+ErrorCodeInfo.ee61;
				   msg.obj=em;
				   handler.sendMessage(msg);
				   return ;
			    }
		        icb.back(handler);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   
			    PrintWriter pw=null;
				StringWriter sw=new StringWriter();
				pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				StringBuffer sb=sw.getBuffer();
			    String text=GlobalExceptionHandler.getAppInfo(act)+sb.toString();
			   if(ErrorCode.getFromException(e))
			   {
			     //MailManager.sendEmail2(text);
			   }
			   //Log.d("mail2",text);
			   
			   msg=new Message();
			   msg.what=HandlerBase.error1;
			   em=new ErrorMessage();
			   String code=ErrorCode.getCodeFromException(e);
			   em.message=error_code+code;
			   em.index=0;
			   
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
				 //isFinish=true;
				 Log.e(tag,icb.getClass().getName()+":finish");
			}
	   }
   }
   
  
   //----------------------------------------------------------------------------
   private Dialog dialog;
	/**
	 * 该次网络请求完成
	 */
  // private  static boolean isFinish=true;
   private ICallBackHnadler icbh;
   public void execute2()
   {   
	   if(b)
	   {
		   dialog=Utils.createPorgressDialog(act, null);
		   dialog.show();
	   }
	   handler=new HandlerBase(act)
	   {
		  

		@Override
		public void handleMessage(Message msg) {
			try
			{   
				  if(b&&dialog!=null)
				 {
						dialog.dismiss();
				}
				icbh.back(msg);
				if(msg.what==HandlerBase.error1)
				{   
					if(BaseActivity.isNetworkConnected(act))
					{
						return;
					}
					BeikBankApplication.showMsg(null);
				}
				else
				{
					 BeikBankApplication.hideMsg();
				}
			 
//			  while(!isFinish)
//			  {
//				 
//			  }
			
			
			 super.handleMessage(msg);
			 Log.e(tag,icb.getClass().getName()+":handler");
			 
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
			}
			catch(Exception e)
			{  
				  if(act instanceof BaseActivity1)
				   {
					  BaseActivity1 ba1=(BaseActivity1) act;
					  if(ba1!=null)
			     		{
			     			ba1.debug=ba1.debug_fail;
			     		}
				   }
				e.printStackTrace();
				String code=ErrorCode.getCodeFromException(e);
				ErrorMessage em=new ErrorMessage();
				em.message=code;
				msg.what=error2;
				msg.obj=em;
				super.handleMessage(msg);
				//HandlerBase.showMsg(act,"服务器繁忙:"+code,0);
//				LogHandler.writeLogFromException(tag+code, e);
//				
//				
//				    PrintWriter pw=null;
//					StringWriter sw=new StringWriter();
//					pw = new PrintWriter(sw);
//					e.printStackTrace(pw);
//					StringBuffer sb=sw.getBuffer();
//				    String text=GlobalExceptionHandler.getAppInfo(act)+sb.toString();
//				    if(ErrorCode.getFromException(e))
//					{
//					   //MailManager.sendEmail2(text);
//					}
//				    Log.d("mail2",text);
			}
			
		}
		     
	   };
   }
}
