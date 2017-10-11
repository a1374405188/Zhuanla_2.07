package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.CheckAndSend_data;
import com.beikbank.android.data.FundLimit_data;
import com.beikbank.android.dataparam.CheckAndSendParam;
import com.beikbank.android.dataparam.FundLimitParam;
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
 *2015-1-30
 * 获取购买上限
 */
public class FundLimitManager extends ThreadManager{
String tag="CheckAndSendManager";
	
	String funid;
	public FundLimitManager(Activity act,ICallBack icb)
	{
		this.act=act;
		this.icb=icb;
		init2();
	}
    public void init(String fundid)
    {
    	this.funid=fundid;
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
			   	    FundLimitParam fp=new FundLimitParam();
			   	    fp.fundId=funid;
			   	  
			   	    
					Object obj=im.getFundLimit(FundLimit_data.class,null,fp);
					
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
