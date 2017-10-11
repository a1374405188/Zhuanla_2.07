package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.data.CheckLoginPasswd_data;
import com.beikbank.android.data.CheckPhone_data;
import com.beikbank.android.dataparam.CheckLoginPasswdParam;
import com.beikbank.android.dataparam.CheckPhoneNumberParam;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.utils.MD5;

import comc.beikbank.android.R;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 验证是否有人注册过
 */
public class CheckPhoneManager {
	 String error_code=ErrorCodeInfo.e14;	
	  private Activity act;
	  private String TAG="CheckPhoneManager";
	  ICallBack icb;
	  String phonenumber;
	  //String lodPasswd;

	  public CheckPhoneManager(Activity act,String phonenumber,ICallBack icb)
	  {
		  this.act=act;
		  this.icb=icb;
		  this.phonenumber=phonenumber;
		 // this.lodPasswd=lodPasswd;
        if(phonenumber==null||"".equals(phonenumber))
        {
      	  HandlerBase.showMsg(act,act.getString(R.string.error_2),0);
        }
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
	    	 CheckPhoneNumberParam cpn=new CheckPhoneNumberParam();
	    	cpn.phoneNumber=phonenumber;
			Object obj=im.checkPhoneNumber(CheckPhone_data.class,null,cpn);
           ErrorMessage em=check(obj);
           if(!em.isSuccess)
           {
          	 msg.obj=em;
          	 msg.what=HandlerBase.error2;
          	 handler.sendMessage(msg);
          	 return;
           }
           msg.obj=obj;
      	   msg.what=HandlerBase.success1;
           handler.sendMessage(msg);
           //icb.back(obj);
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
	
	  private ErrorMessage check(Object obj) throws Exception
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
