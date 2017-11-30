package com.beikbank.android.net.impl;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.SendCode_data;
import com.beikbank.android.dataparam.SendCodeParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import coma.beikbank.android.R;



import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 
 */
public class SendCodeManager {
	public Activity act;
	ICallBack icb;
	String error_code=ErrorCodeInfo.e17;
	String phoneNumber;
	String checkCodeType;
	String tag="SendCodeManager";
	/**
	 * 
	 * @param act
	 * @param phoneNumber
	 * @param checkCodeType 1.注册 2.绑定手机 3.找回密码 4.额度提升预留手机号验证
	 * @param icb
	 */
   public SendCodeManager(Activity act,String phoneNumber,String checkCodeType,ICallBack icb)
   {
	   this.act=act;
	   this.icb=icb;
	   this.phoneNumber=phoneNumber;
	   this.checkCodeType=checkCodeType;
   }
   public void start()
   {
	   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
   }
   ICallBackHnadler icbh=new ICallBackHnadler() {
	
	@Override
	public void back(Message msg) {
		icb.back(msg.obj);
		if(msg.what==HandlerBase.success1)
		{
		  if(SystemConfig.isDebug())
		  {
			  SendCode_data sd=(SendCode_data) msg.obj;
			  Log.e("code",sd.data.code);
			  
		  }
		}
	}
};
   ICallBackNet icbn=new ICallBackNet() {
	
	@Override
	public void back(Handler handler) throws Exception {
		  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
		  Message msg=new Message();
		  SendCodeParam scp=new SendCodeParam();
  	      scp.phoneNumber=phoneNumber;
  	      scp.checkCodeType=checkCodeType;
  	      if(scp.phoneNumber==null||"".equals(scp.phoneNumber)||scp.checkCodeType==null||"".equals(checkCodeType))
  	      {
  	    	  ErrorMessage em=new ErrorMessage();
  	    	  em.message=error_code+":"+act.getString(R.string.error_1);
  	    	  msg.obj=em;
  	    	  handler.sendMessage(msg);
  	    	  return;
  	      }
		  Object obj=im.SendCode(SendCode_data.class,null,scp);
		  ErrorMessage em=check(obj);
		  if(!em.isSuccess)
		  {
			  msg.obj=em;
			  msg.what=HandlerBase.error2;
			  handler.sendMessage(msg);
			  LogHandler.writeLogFromString(tag,em.message);
			  return;
		  }
		  msg.obj=obj;
		  msg.what=HandlerBase.success1;
		  handler.sendMessage(msg);

	}
    };
    public ErrorMessage check(Object obj) throws Exception
    {   
    	ErrorMessage em=new ErrorMessage();
    	if(obj!=null&&obj instanceof ErrorMessage)
    	{   
    		em=(ErrorMessage) obj;
    		return em;
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
