package com.beikbank.android.net.impl;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.ConfirmPay_data;
import com.beikbank.android.data2.Head;
import com.beikbank.android.data2.Head2;
import com.beikbank.android.dataparam.ConfirmPayforPParam;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.UserCheckParam;
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
 *赚啦1.5以后版本通用请求借口
 */
public class TongYongManager2 extends ThreadManager{
	String tag="TongYongManager2";
	Object  params;
	ManagerParam mp;
	public HeadParam2 hp;
	public TongYongManager2(Activity act,ICallBack icb,Object  params)
	{
		this.act=act;
		this.icb=icb;
	    this.params=params;
		init2();
	}
	public TongYongManager2(Activity act,ICallBack icb,Object  params,ManagerParam mp)
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
					else if(msg.what==HandlerBase.success2)
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
					IBusinessImpl2 ibi=new IBusinessImpl2(act);
					Object obj=ibi.execute(params);
					hp=ibi.hp;
					//ErrorMessage em=ErrorMessage.getEm(obj, error_code);
					ErrorMessage em=new ErrorMessage();
					Class cla=obj.getClass();
		    		Field f1=cla.getDeclaredField("header");
		    		
		    		int i=0;
		    		Object obj1=f1.get(obj);
		    		if(obj1 instanceof Head2)
		    		{
		    			Head2 s1=(Head2)f1.get(obj);
		    			if(s1!=null)
		    			{
		    			   if("0000".equals(s1.re_code))
		    			   {
		    				   i=1;
		    				   
		    			   }
		    			   else if(params instanceof UserCheckParam&&"9998".equals(s1.re_code))
		    			   {
		    				  i=1;
		    			   }
//		    			   else if(params instanceof CheckJiaoYiMiMaParam)
//		    			   {
//		    				  i=1;
//		    			   }
		    			   else
		    			   {   
		    				   em.message=s1.re_msg;
		    				   em.index=1;
		    				   
		    				  
		    			   }
		    			}
		    			
		    		}
		    		else if(obj1 instanceof Head)
		    		{   
		    			Head s1=(Head)f1.get(obj);
		    			if(s1!=null)
		    			{
		    				i=1;
		    			}
		    			
		    		}
		    		
		    		if(i==1)
		    		{
		    			em.isSuccess=true;
		    		}
		    		else if(i==2)
		    		{
		    			em.isSuccess=true;
		    		}
		    		else if(i==0)
		    		{
		    			em.isSuccess=false;
		    		}
		    		
		    		
		    		
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
					if(i==1)
					{
					  msg.what=HandlerBase.success1;
					}
					else if(i==2)
					{
					  msg.what=HandlerBase.success2;
					}
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
