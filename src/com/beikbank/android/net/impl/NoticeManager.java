package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.Base_data;
import com.beikbank.android.data.Notice_data;
import com.beikbank.android.dataparam.UpdateTPasswdParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.ThreadManager;
import com.beikbank.android.utils.MD5;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-19
 * 公告管理
 */
public class NoticeManager extends ThreadManager{
	//private Activity act;
    private String TAG="NoticeManager";
   // ICallBack icb;

 
   public NoticeManager(Activity act,ICallBack icb)
   {   
	   this.act=act;
	   this.icb=icb;
	   init2();
   }
   public void start()
   {
	   ThreadManagerImpl.execute(act, icbn, icbh, error_code,false);
   }
   public void init2()
   {    
	    error_code=ErrorCodeInfo.e27;	
	    icbn=new ICallBackNet() {
			
			@Override
			public void back(Handler handler) throws Exception {
			     Message msg=new Message();
				 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
				 Object obj=im.getNotice(Notice_data.class,null,null);
				 ErrorMessage em= em=ErrorMessage.getEm(obj, error_code);
				 if(!em.isSuccess)
				 {
					 msg.what=HandlerBase.error2;
					 msg.obj=em;
					 handler.sendMessage(msg);
					 LogHandler.writeLogFromString(TAG, em.message);
					 return;
				 }
				 msg.what=HandlerBase.success1;
				 msg.obj=obj;
				 handler.sendMessage(msg);
			}
		};
		    icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) 
				{
					
					if(msg.what==HandlerBase.success1)
					{
						icb.back(msg.obj);
					}
				}
			};
   }


}
