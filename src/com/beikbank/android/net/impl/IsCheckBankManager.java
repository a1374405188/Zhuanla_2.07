package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.HelpAndSafety_data;
import com.beikbank.android.data.IsCheckBank_data;
import com.beikbank.android.dataparam.HelpAndSafetyParam;
import com.beikbank.android.dataparam.IsCheckBankParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
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
 *2015-1-26
 * 判断是否需要验证银行卡
 */
public class IsCheckBankManager extends ThreadManager{
	String tag="IsCheckBankManager";
	
	String id;
	public IsCheckBankManager(Activity act,ICallBack icb)
	{
		this.act=act;
		this.icb=icb;
		init2();
	}
    public void init(String uerid)
    {
    	id=uerid;
    }
    private void init2()
	   {    
		    error_code=ErrorCodeInfo.e30;
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
			   	    
			   	    IsCheckBankParam cbp=new IsCheckBankParam();
			   	    cbp.memberID=id;
		            
					Object obj=im.IsChekBnak(IsCheckBank_data.class,null,cbp);
					
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
