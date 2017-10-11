package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.Base_data;
import com.beikbank.android.dataparam.SetTPasswdParam;
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
 *2015-1-9
 * 更新交易密码
 */
public class UpdateTPasswdManager {
	 String error_code;	
		private Activity act;
	    private String TAG="UpdateTPasswdManager";
	    ICallBack icb;
	    String userid;
	    String lodPasswd;
	    String passwd;

	    /**
	     * 
	     * @param act
	     * @param userid 用户id
	     * @param name 用户名字
	     * @param id 用户身份证
	     * @param icb
	     */
	   public UpdateTPasswdManager(Activity act,String userid,String lodPasswd,String passwd,ICallBack icb)
	   {   
		   this.act=act;
		   this.userid=userid;
		   this.passwd=passwd;
		   this.lodPasswd=lodPasswd;
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
			 UpdateTPasswdParam utp=new UpdateTPasswdParam();
			 utp.memberID=userid;
			 utp.oldTransactionPassword=MD5.md5s32(lodPasswd);
			 utp.newTransactionPassword=MD5.md5s32(passwd);
			 Object obj=im.updateTPasswd(Base_data.class,null,utp);
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
			em.isSuccess=true;
		  return em;
	  }
}
