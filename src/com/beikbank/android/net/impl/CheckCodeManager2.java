package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.CheckCode2_data;
import com.beikbank.android.data.CheckCode_data;
import com.beikbank.android.data.SendCode_data;
import com.beikbank.android.dataparam.CheckCode2Param;
import com.beikbank.android.dataparam.SendCodeParam;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import coma.beikbank.android.R;



/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 检查找回登录密码验证码是否正确
 */
public class CheckCodeManager2 {
	public Activity act;
	ICallBack icb;
	String error_code;
	String phoneNumber;
	String verifiId;
	String realName;
	String idNumber;
	String verificode;
	int index;
	/**
	 * 
	 * @param act
	 * @param index 1注册验证码，3 验证找回登录密码
	 * @param phoneNumber 手机号
	 * @param verifiId 验证码id
	 * @param realName 真实的名字
	 * @param idNumber 身份证号码
	 * @param verificode 验证码
	 * @param icb
	 */
   public CheckCodeManager2(Activity act,String phoneNumber,String verifiId,String realName,String idNumber,String verificode,ICallBack icb)
   {
	   this.act=act;
	   this.icb=icb;
	   this.phoneNumber=phoneNumber;
	   this.verificode=verificode;
	   this.idNumber=idNumber;
	   this.realName=realName;
	   this.verifiId=verifiId;
   }
   String isreanlName;
   public void init(String isreanlName)
   {
	   this.isreanlName=isreanlName;
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
		  CheckCode2Param ccp=new CheckCode2Param();
		  ccp.hasAuthenticated=isreanlName;
		  ccp.idNumber=idNumber;
		  ccp.phoneNumber=phoneNumber;
		  ccp.realName=realName;
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
		  obj=im.CheckCode2(CheckCode2_data.class,null,ccp);
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
	    	 icb.back(msg.what);
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
