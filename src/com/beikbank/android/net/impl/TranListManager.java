package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.activity.BaseActivity1;
import com.beikbank.android.data.Base_data;
import com.beikbank.android.data.TranList_Data;
import com.beikbank.android.dataparam.TranListParam;
import com.beikbank.android.dataparam.UpdateTPasswdParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-10
 * 交易记录
 */
public class TranListManager {
	 String error_code=ErrorCodeInfo.e24;	
		private Activity act;
	    private String TAG="TranListManager";
	    ICallBack icb;
	    TranListParam tl;
	    boolean isShow=false;
	    
        public void setShow(boolean b)
        {
        	isShow=b;
        }
	    /**
	     * 
	     * @param act
	     */
	   public TranListManager(Activity act,ICallBack icb,TranListParam tl)
	   {
		   this.tl=tl;
		   this.act=act;
		   this.icb=icb;
	   }
	   public void start()
	   {   
		
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,isShow);
	   }
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
			 Message msg=new Message();

		    
			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
		  
		     Object obj=im.TranList(TranList_Data.class,null,tl);
			 ErrorMessage em=check(obj);
			 if(!em.isSuccess)
			 {
				 msg.what=HandlerBase.error2;
				 msg.obj=em;
				 handler.sendMessage(msg);
				 return;
			 }
			 msg.what=HandlerBase.success1;
			 msg.obj=obj;
			 handler.sendMessage(msg);
		   }

		
	};
	   ICallBackHnadler icbh=new ICallBackHnadler() {
		
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
	  public ErrorMessage check(Object obj) throws Exception
	  {
		   ErrorMessage em=new ErrorMessage();
		   if(obj instanceof ErrorMessage)
			{
				ErrorMessage rcr=(ErrorMessage) obj;
				return rcr;
			}
		   em=ErrorMessage.getEm(obj, error_code);
	 		if(!em.isSuccess)
	 		{
	 			return em;
	 		}
	 		else
	 		{
	 			em.isSuccess=false;
	 		}
			em.isSuccess=true;
		  return em;
	  }
}
