package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.beikbank.android.animation.AnimationManager;
import com.beikbank.android.animation.ToastAnimation;
import com.beikbank.android.exception.GlobalExceptionHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
/**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-11
 **/
public class BaseActivity1 extends BaseActivity{
	
	//错误提示父布局
	public LinearLayout ll_error;
	//错误提示布局
	public TextView   tv_error;
	public int debug=0;
	public static final int debug_ok1=2;
	public static final int debug_ok2=3;
	public static final int debug_fail=1;
	public PopupWindow window;
	protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler(this));
      
        
        //友盟统计
       // MobclickAgent.updateOnlineConfig(this);
        BeikBankApplication application = (BeikBankApplication) this.getApplication(); 
        application.getActivityManager().pushActivity(this); 
       
        //友盟推送
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.enable();
//        PushAgent.getInstance(this).onAppStart();
//        String s=UmengRegistrar.getRegistrationId(this);
//        if(s!=null)
//        {
//          Log.d("tok",s);
//        }
        
        //极光推送
        boolean isnet=isNetworkConnected(this);
        
        if(isnet)
        {
            registerMessageReceiver();
		    jginit();
//		    String s = getRegistrationID(this);
//		    if (s != null) 
//		    {
//			   Log.d("register_id", s);
//		    }
		     String id=BeikBankApplication.getPhoneNumber();
		    if(id!=null&&!id.equals(null))
		    {
		    	
		    
		    Set<String> list=new TreeSet<String>();
		    list.add(id);

		    JPushInterface.setAlias(this,id,new TagAliasCallback() {
				
				@Override
				public void gotResult(int arg0, String arg1, Set<String> arg2) {
					if(arg0==0)
					{
					   Log.d("alias","成功"+BeikBankApplication.getPhoneNumber());
					}
					else
					{
						Log.d("alias","失败");
					}
					
				}
			});
		    
		    
		    }
        }
        
        
        //设置字体大小不随系统字体大小改变
        Resources res = super.getResources();  
        Configuration config=new Configuration();  
        config.setToDefaults();  
        res.updateConfiguration(config,res.getDisplayMetrics() ); 
    } 
	 @Override 
	    public void onBackPressed() { 
	        super.onBackPressed(); 
	        BeikBankApplication application = (BeikBankApplication) getApplication(); 
	        application.getActivityManager().popActivity(this); 
	    }

		

		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			//MobclickAgent.onPause(this);
			//isForeground = false;
			JPushInterface.onPause(this);
		}

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			//MobclickAgent.onResume(this);
//			isForeground = true;
			JPushInterface.onResume(this);
		}
	
		
	   /**
	    * 显示界面消息
	    * @return
	    */
	   public boolean showToast(String text)
	   {
		   if(ll_error==null||tv_error==null)
		   {
			   return false;
		   }
		   ll_error.setVisibility(View.VISIBLE);
		   tv_error.setText(text);
		   ToastAnimation ta=(ToastAnimation) AnimationManager.getAnimation(AnimationManager.TOAST_ANIMATION1);
		   ta.performTextMove(ll_error,40,50);
		   return true;
	   }
	   
	   
	   
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		  //极光推送
        boolean isnet=isNetworkConnected(this);
        if(isnet)
        {
		  unregisterReceiver(mMessageReceiver);
        }
		BeikBankApplication.getInstance().getActivityManager().rmActivity(this);
	}
		// 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
		public void jginit() {
			JPushInterface.init(getApplicationContext());
		}

		public static String getDeviceId(Context context) {
			String deviceId = JPushInterface.getUdid(context);

			return deviceId;
		}

		public static String getRegistrationID(Context context) {
			String deviceId = JPushInterface.getRegistrationID(context);

			return deviceId;
		}

		// for receive customer msg from jpush server
		public static boolean isForeground = false;
		
		private MessageReceiver mMessageReceiver;
		public static final String MESSAGE_RECEIVED_ACTION = "com.beikbank.android.MESSAGE_RECEIVED_ACTION";
		public static final String KEY_TITLE = "title";
		public static final String KEY_MESSAGE = "message";
		public static final String KEY_EXTRAS = "extras";

		public void registerMessageReceiver() {
			mMessageReceiver = new MessageReceiver();
			IntentFilter filter = new IntentFilter();
			filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
			filter.addAction(MESSAGE_RECEIVED_ACTION);
			registerReceiver(mMessageReceiver, filter);
		}

		public class MessageReceiver extends BroadcastReceiver {

			@Override
			public void onReceive(Context context, Intent intent) {
				if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
					String messge = intent.getStringExtra(KEY_MESSAGE);
					String extras = intent.getStringExtra(KEY_EXTRAS);
					StringBuilder showMsg = new StringBuilder();
					showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
					if (extras != null && !extras.equals("")) {
						showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
					}
					//setCostomMsg(showMsg.toString());
				}
			}
		}

		private void setCostomMsg(String msg) {
			if (null != msg) {
				Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
			}
		}
}
