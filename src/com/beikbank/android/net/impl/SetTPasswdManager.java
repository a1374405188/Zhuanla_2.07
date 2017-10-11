package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.Base_data;
import com.beikbank.android.data.RealName_data;
import com.beikbank.android.dataparam.RealNameParam;
import com.beikbank.android.dataparam.SetTPasswdParam;
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
 *2015-1-9
 * 设置交易密码
 */
public class SetTPasswdManager {
	 String error_code;	
		private Activity act;
	    private String TAG="SetTPasswdManager";
	    ICallBack icb;
	    String userid;
	    String passwd;

	    /**
	     * 
	     * @param act
	     * @param userid 用户id
	     * @param name 用户名字
	     * @param id 用户身份证
	     * @param icb
	     */
	   public SetTPasswdManager(Activity act,String userid,String passwd,ICallBack icb)
	   {   
		   this.act=act;
		   this.userid=userid;
		   this.passwd=passwd;
		   this.icb=icb;
	   }
	   public void start()
	   {
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
	   }
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
		     Message msg=new Message();
			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			 SetTPasswdParam stp=new SetTPasswdParam();
			 stp.memberID=userid;
			 stp.transactionPassword=MD5.md5s32(passwd);
			 Object obj=im.setTPasswd(Base_data.class,null,stp);
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
