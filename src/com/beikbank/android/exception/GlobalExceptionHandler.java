package com.beikbank.android.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import u.aly.ak.e;

import com.beikbank.android.activity.NoNetErrorActivity;
import com.beikbank.android.activity.WelcomeActivity;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.OneKeyParam;

import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.OneKeyManager;
import com.beikbank.android.utils.BKDes;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.Utils;
import coma.beikbank.android.R;


 
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.Build;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-5
 **/
public class GlobalExceptionHandler implements UncaughtExceptionHandler{
	static String TAG="GlobalExceptionHandler";
    private  Context context;
    /**
     * 
     */
    private String text;
	public GlobalExceptionHandler(Context content)
	{
		this.context=content;
	}
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		Exception e=new Exception(ex);
		e.printStackTrace();
		LogHandler.writeLogFromException(TAG,e);

		
//		PrintWriter pw=null;
//		StringWriter sw=new StringWriter();
//		pw = new PrintWriter(sw);
//		e.printStackTrace(pw);
//		StringBuffer sb=sw.getBuffer();
//		text=getAppInfo(context)+sb.toString();
	    //Log.d("mail",text);
		//restartApplication();
		                             Activity act=(Activity) context;
									 act.finish();
									 System.exit(0);
		
	}
	
public static String getAppInfo(Context context)
{  
	String info="";
	String space="\r\n";
    try
    {
    	String phone=BeikBankApplication.getPhoneNumber();
    	info+="phone:"+phone+space;
    	
    	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);   
        String imei= tm.getDeviceId();
        info+="imei:"+imei+space;
        
        String device_model = Build.MODEL; // 设备型号   
        
        
        String version_sdk = Build.VERSION.SDK; // 设备SDK版本   
        String version_release = Build.VERSION.RELEASE;
        String os_version=version_release+":"+version_sdk;
        
        String app_version=Utils.getVersion(context);
        
        
        
        info+="device_model:"+device_model+space;
        info+="os_version:"+os_version+space;
        info+="app_version:"+app_version+space;
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
	
	
	
   
   return info;
}


	/**
	 * 重启应用程序
	 */
	@SuppressWarnings("deprecation")
	private void restartApplication() {
		//System.exit(0);
		//ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);  
		//manager.restartPackage(context.getPackageName());
	  // try
	  // {
		//Intent intent=new Intent(context,GlobalErrorActivity.class);
		//context.startActivity(intent);
		   
		   //Intent intent=new Intent(context,ErrorActivity.class);
		  // intent.putExtra("global_error",true);
		  // context.startActivity(intent);
	
		           
		   
//		   
//		   new Thread() 
//			 {  
//		         @Override  
//		         public void run()
//		         {  
//		            Looper.prepare();  
//		             new AlertDialog.Builder(context).setTitle("程序异常").setCancelable(false)  
//	                   .setMessage("程序异常，点击确定将问题反馈给开发人员以便他们进行分析!").setNeutralButton("确定", new OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								
//								
//								try
//								{  
//									
//								   boolean b=MailManager.sendEmail(text);
//								   if(b)
//								   {
//									   Log.d("email","true");
//								   }
//								   else
//								   {
//									   Log.d("email","false");
//								   }
//								   
//								}
//								catch(Exception e)
//								{
//									e.printStackTrace();
//								}
//								finally
//								{
//									
//									 Activity act=(Activity) context;
//									 act.finish();
//									 System.exit(0);
//								}
//								
//								
//							}
//
//							 
//	                        
//	                   })  
//	                   .create().show();
//		             Looper.loop();
//		            
//		         }  
//		     }.start();
//	   }
//	   catch(Exception e)
//	   {
//		   e.printStackTrace();
//	   }
//	   
	}
	 

}
