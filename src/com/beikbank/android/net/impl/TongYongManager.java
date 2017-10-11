package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.ConfirmPay_data;
import com.beikbank.android.dataparam.ConfirmPayforPParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.RequestUrl;
import com.beikbank.android.net.ThreadManager;
import com.beikbank.android.utils.MD5;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-10
 */
public class TongYongManager extends ThreadManager{
	String tag="TongYongManager";
	Object  params;
	ManagerParam mp;
	
	public TongYongManager(Activity act,ICallBack icb,Object  params)
	{
		this.act=act;
		this.icb=icb;
	    this.params=params;
		init2();
	}
	public TongYongManager(Activity act,ICallBack icb,Object  params,ManagerParam mp)
	{   
		this.mp=mp;
		if(mp==null)
		{
			this.mp=new ManagerParam();
		}
		this.act=act;
		this.icb=icb;
		this.params=params;
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
			   	    //IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
                    
			   	    
					//Object obj=im.confirmPayforP(ConfirmPay_data.class,url,cpp);
					IBusinessImpl ibi=new IBusinessImpl(act);
					Object obj=ibi.execute(params);
					ErrorMessage em=ErrorMessage.getEm(obj, error_code);
					if(!em.isSuccess)
					{
						msg=new Message();
						
						msg.what=HandlerBase.error2;
						if(mp!=null&&mp.isShowMsg==false)
						{
							msg.what=HandlerBase.error1;
						}
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
		  boolean b=true;
		  if(mp!=null)
		  {
			  b=mp.isShowDialog;
		  }
		  ThreadManagerImpl.execute(act,icbn,icbh,error_code,b);
	   }
}
