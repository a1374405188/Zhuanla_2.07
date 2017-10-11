package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.Action_data;
import com.beikbank.android.data.UserRecord_data;
import com.beikbank.android.dataparam.UserRecordParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.ThreadManager;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 * 
 */
public class UserRecordManager extends ThreadManager{
	 String tag="UserRecordManager";
	 UserRecordParam urp;
		public UserRecordManager(Activity act,ICallBack icb,UserRecordParam urp)
		{
			this.act=act;
			this.icb=icb;
			this.urp=urp;
			init2();
		}
	    private void init2()
		   {    
			   //error_code=ErrorCodeInfo.e31;
			    icbh=new ICallBackHnadler() {
					
					@Override
					public void back(Message msg) {
						if(msg.what==HandlerBase.success1)
						{
							icb.back(msg.obj);
						}
						else
						{
							icb.back(null);
						}
					}
				};
				
				 icbn=new ICallBackNet() {
					
					@Override
					public void back(Handler handler) throws Exception {
						Message msg=new Message();
				   	    IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
						Object obj=im.getUserRecord(UserRecord_data.class,null,urp);
						
						ErrorMessage em=ErrorMessage.getEm(obj, error_code);
						if(!em.isSuccess)
						{
							msg=new Message();
							msg.what=HandlerBase.error2;
							msg.obj=em;
							handler.sendMessage(msg);
							LogHandler.writeLogFromString(tag,em.message);
							return;
						}
						msg=new Message();
						msg.what=HandlerBase.success1;
						msg.obj=obj;
						handler.sendMessage(msg);
					}
				};
		   }
		   public void start()
		   {   
			  ThreadManagerImpl.execute(act,icbn,icbh,error_code,false);
		   }
}
