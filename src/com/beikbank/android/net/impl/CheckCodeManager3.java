package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.CheckCode2_data;
import com.beikbank.android.data.CheckCode_data;
import com.beikbank.android.dataparam.CheckCode2Param;
import com.beikbank.android.dataparam.CheckCodeParam;
import com.beikbank.android.dataparam.CheckCodeParam3;
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
 *2015-1-9
 * 验证银行预留手机号时检查验证码
 */
public class CheckCodeManager3 {
	public Activity act;
	ICallBack icb;
	String error_code;
	String userid;
	String phoneNumber;
	String verifiId;
	String verificode;
	/**
	 * 
	 * @param act
	 * @param index 1注册验证码，3 验证找回登录密码
	 * @param icb
	 */
   public CheckCodeManager3(Activity act,ICallBack icb)
   {
	   this.act=act;
	   this.icb=icb;
	 
   }
   /**
    * 
    * @param phoneNumber 手机号
    * @param verifiId  验证码id
    * @param verificode  验证码
    */
   public void init(String userid,String phoneNumber,String verifiId,String verificode)
   {   
	   this.userid=userid;
	   this.phoneNumber=phoneNumber;
	   this.verificode=verificode;
	   this.verifiId=verifiId;
   }
   public void start()
   {
	   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
   }
   ICallBackNet icbn=new ICallBackNet() {
	
	@Override
	public void back(Handler handler) throws Exception {
		 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
		  Message msg=new Message();
		  CheckCodeParam3 ccp=new CheckCodeParam3();
		  ccp.memberID=userid;
		  ccp.phoneNumber=phoneNumber;
		  ccp.verificode=verificode;
		  ccp.verifiId=verifiId;
// 	      ErrorMessage em=checkParam(ccp);
// 	      if(!em.isSuccess)
// 	      {
// 	    	  msg.obj=em;
// 			  msg.what=HandlerBase.success1;
// 			  handler.sendMessage(msg);
// 	    	  return;
// 	      }
		  Object obj=null;
		  obj=im.CheckCode3(CheckCode_data.class,null,ccp);
		  ErrorMessage em=check(obj);
		  if(!em.isSuccess)
		  {
				 msg.what=HandlerBase.error2;
				 msg.obj=em;
				 handler.sendMessage(msg);
				 return;
		  }
		  msg.obj=obj;
		  msg.what=HandlerBase.success1;
		  handler.sendMessage(msg);
	}
};
 ICallBackHnadler icbh=new ICallBackHnadler() {
	
	@Override
	public void back(Message msg) {
	     if(msg.what==HandlerBase.success1)
	     {
	    	 icb.back(1);
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
