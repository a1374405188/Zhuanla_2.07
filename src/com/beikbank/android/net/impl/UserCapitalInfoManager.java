package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.ActionInfo_data;
import com.beikbank.android.data.UserCapitalInfo_data;
import com.beikbank.android.dataparam.ActionInfoParam;
import com.beikbank.android.dataparam.TotalMoneyParam;
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
 *2015-2-28
 * 
 */
public class UserCapitalInfoManager extends ThreadManager{
	String tag="UserCapitalInfoManager";
	TotalMoneyParam tp;
	public UserCapitalInfoManager(Activity act,ICallBack icb,TotalMoneyParam tp)
	{
		this.act=act;
		this.icb=icb;
		this.tp=tp;
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

			   	    
					Object obj=im.getUserCapitalInfo(UserCapitalInfo_data.class,null,tp);
					
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
