package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.Base_data;
import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam.UpdateTPasswdParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.utils.MD5;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-12
 * 得到手续费
 */
public class HandMoneyManager {
	 String error_code=ErrorCodeInfo.e25;	
		private Activity act;
	    private String TAG="HandMoneyManager";
	    ICallBack icb;
	    String userid;

	    /**
	     * 
	     * @param act
	     * @param userid 用户id
	     * @param name 用户名字
	     * @param id 用户身份证
	     * @param icb
	     */
	   public HandMoneyManager(Activity act,ICallBack icb)
	   {   
		   this.act=act;
		   this.icb=icb;
	   }
	   public void start(String userid)
	   {   
		   this.userid=userid;
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,false);
	   }
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
		     Message msg=new Message();
			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			 TotalMoneyParam tmp=new TotalMoneyParam();
			 tmp.memberID=userid;
           
			 Object obj=im.getUserPoundage(Poundage_data.class,null,tmp);
			 ErrorMessage em=check(obj);
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
	   ICallBackHnadler icbh=new ICallBackHnadler() {
		
		@Override
		public void back(Message msg) {
			
			if(msg.what==HandlerBase.success1)
			{
				icb.back(msg.obj);
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
	 		Poundage_data pd=(Poundage_data) obj;
	 		if(pd==null)
	 		{
	 			em.message=error_code+ErrorCodeInfo.ee2;
	 			return em;
	 		}
	 		Poundage p=pd.data;
	 		if(p==null)
	 		{
	 			em.message=error_code+ErrorCodeInfo.ee3;
	 			return em;
	 		}
	 		try
	 		{
	 			int a=Integer.parseInt(p.freeChargeCount);
	 			double d=Double.parseDouble(p.poundage);
	 		}
	 		catch(Exception e)
	 		{
	 			e.printStackTrace();
	 			em.message=error_code+ErrorCodeInfo.ee9;
	 			LogHandler.writeLogFromException(TAG, e);
	 			return em;
	 		}
			em.isSuccess=true;
		  return em;
	  }
}
