package com.beikbank.android.net.impl;

import java.util.ArrayList;

import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.Base_data;
import com.beikbank.android.dataparam.BankListParam;
import com.beikbank.android.dataparam.UpdateLoginPasswdParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;

import comc.beikbank.android.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 修改登录密码
 */
public class UpdatePasswd {
	  String error_code;	
	  private Activity act;
	  private String TAG="LoginManager";
	  ICallBack icb;
	  String userId;
	  String lodPasswd;
	  String newpasswd;
	  public UpdatePasswd(Activity act,ICallBack icb)
	  {
		  this.act=act;
		  this.icb=icb;
      
	  }
	  public void start(String memberID,String lodPasswd,String newPasswd)
	  {   
		  this.newpasswd=newPasswd;
		  this.lodPasswd=lodPasswd;
		  this.userId=memberID;
          ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
		
	  }
	  
	  ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
			 Message msg=new Message();
	    	 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
	    	 UpdateLoginPasswdParam lp=new UpdateLoginPasswdParam();
	    	 lp.memberID=userId;
	    	 lp.oldLoginPassword=MD5.md5s32(lodPasswd);
	       //  lp.loginPassword=lodPasswd;
	         lp.newLoginPassword=MD5.md5s32(newpasswd);
		     Object obj=im.updateLoginPasswd(Base_data.class, null, lp);
             ErrorMessage  em=ErrorMessage.getEm(obj, error_code);
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
		}
	};
	ICallBackHnadler icbh=new ICallBackHnadler() {
		
		@Override
		public void back(Message msg) {
			if(msg.what==HandlerBase.success1)
			{
				icb.back(null);
			}
		}
	};
//	  HandlerBase handler;
//	   class LoadData extends Thread
//	   {      
//		     
//		      @Override
//		      public void run()
//		      {   
//		    	  Message msg=new Message();
//		    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//		     	  try {
//		     		String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//		     	    BankListParam bl=new BankListParam();
//		     	    bl.version=version;
//		     	    if("".equals(version))
//		     	    {
//		     	    	bl.version="1";
//		     	    }
//		 			Object obj=im.getBankList(BankList_data.class,null,bl);
//		 			
//		 			ErrorMessage em=check(obj);
//		 			if(!em.isSuccess)
//		 			{
//		 				msg=new Message();
//			 			msg.what=HandlerBase.error2;
//			 			msg.obj=em;
//			 			handler.sendMessage(msg);
//						LogHandler.writeLogFromString(TAG,em.message);
//						return;
//		 			}
//
//		 			
//
//		 			msg=new Message();
//		 			msg.what=HandlerBase.success1;
//		 			msg.obj=obj;
//					handler.sendMessage(msg);
//		 		 } catch (Exception e) {
//		 			e.printStackTrace();
//		 			msg=new Message();
//		 			msg.what=HandlerBase.error2;
//		 			ErrorMessage rcr=new ErrorMessage();
//		 			rcr.message=error_code+ErrorCodeInfo.ee0;
//		 			msg.obj=rcr;
//					handler.sendMessage(msg);
//					LogHandler.writeLogFromException(rcr.message,e);
//		 		 }
//		      }
//	   }
//	   private ErrorMessage check(Object obj) throws Exception
//	   {   
//		   ErrorMessage em=new ErrorMessage();
//		   if(obj instanceof ErrorMessage)
//			{
//				ErrorMessage rcr=(ErrorMessage) obj;
//				return rcr;
//			}
//		   em=ErrorMessage.getEm(obj, error_code);
//	 		if(!em.isSuccess)
//	 		{
//	 			return em;
//	 		}
//	 		else
//	 		{
//	 			em.isSuccess=false;
//	 		}
//		   em.isSuccess=true;
//		   return em;
//	   }
}
