package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.ActionInfo_data;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data.Qianbao3_data;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.dataparam.ActionInfoParam;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.QianbaoParam2;
import com.beikbank.android.dataparam.QianbaoParam3;
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
 * 钱包余额
 */
public class Qianbao6Manager extends ThreadManager{
	String tag="Qianbao6Manager";
	TotalMoneyParam qp1;
	public Qianbao6Manager(Activity act,ICallBack icb,TotalMoneyParam qp1)
	{
		this.act=act;
		this.icb=icb;
		this.qp1=qp1;
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
				}
			};
			
			 icbn=new ICallBackNet() {
				
				@Override
				public void back(Handler handler) throws Exception {
					Message msg=new Message();
			   	    IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);

			   	    
					Object obj=im.qianbao6(Qianbao4_data.class,null,qp1);
					
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
		  ThreadManagerImpl.execute(act,icbn,icbh,error_code,true);
	   }
}
