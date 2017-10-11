package com.beikbank.android.net.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.IsReanlName_data;
import com.beikbank.android.dataparam.BankListParam;
import com.beikbank.android.dataparam.IsReanlNameParam;
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
import com.beikbank.android.utils.Utils;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 
 */
public class IsRealNameManager {
	 private static boolean isFinish=true;
	   private Activity act;
	   private Dialog dialog;
	   private String TAG="IsRealNameManager";
	   ICallBack icb;
	   String number;
	   public IsRealNameManager(Activity act,String number,ICallBack icb)
	   {   
		   if(act==null||number==null||icb==null)
		   {
			   throw new NullPointerException(TAG);
		   }
		   this.number=number;
		   this.act=act;
		   this.icb=icb;
	   }
	   public void start()
	   {   
//		   if(!isFinish)
//		   {
//			   return;
//		   }
//		   isFinish=false;
//		   dialog=Utils.createPorgressDialog(act, null);
//		   dialog.show();
//		   handler=new HandlerBase(act)
//		   {
//			  
//
//			@Override
//			public void handleMessage(Message msg) {
//				if(dialog!=null)
//				{
//					dialog.dismiss();
//				}
//				super.handleMessage(msg);
//				switch (msg.what) {
//				case success1:
//					break;
//				}
//				icb.back(msg.obj);
//				isFinish=true;
//			}
//			      
//		   };
		  // new LoadData().start();
		   ThreadManagerImpl.execute(act,icbn, icbh,error_code,true);
	   }
	   ICallBackHnadler icbh=new ICallBackHnadler() {
		
		@Override
		public void back(Message msg) {
			
			switch (msg.what) {
			case HandlerBase.success1:
				break;
			}
			icb.back(msg.obj);
		}
	};
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
			  Message msg=new Message();
			  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			  IsReanlNameParam ir=new IsReanlNameParam();
	     	    ir.phoneNumber=number;
	 			Object obj=im.isName(IsReanlName_data.class,null,ir);
	 			ErrorMessage em=check(obj);
	 			if(!em.isSuccess)
	 			{
	 				msg=new Message();
		 			msg.what=HandlerBase.error2;
		 			msg.obj=em;
		 			handler.sendMessage(msg);
					LogHandler.writeLogFromString(TAG,em.message);
					return;
	 			}
	 			msg=new Message();
	 			msg.what=HandlerBase.success1;
	 			msg.obj=obj;
				handler.sendMessage(msg);
		}
	};
	   HandlerBase handler;
	   String error_code=ErrorCodeInfo.e15;
//	   class LoadData extends Thread
//	   {      
//		     
//		      @Override
//		      public void run()
//		      {   
//		    	  Message msg=new Message();
//		    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//		     	  try {
//		     		 IsReanlNameParam ir=new IsReanlNameParam();
//		     	    ir.phoneNumber=number;
//		 			Object obj=im.isName(IsReanlName_data.class,null,ir);
//		 			ErrorMessage em=check(obj);
//		 			if(!em.isSuccess)
//		 			{
//		 				msg=new Message();
//			 			msg.what=HanderManager.error2;
//			 			msg.obj=em;
//			 			handler.sendMessage(msg);
//						LogHandler.writeLogFromString(TAG,em.message);
//						return;
//		 			}
//		 			msg=new Message();
//		 			msg.what=HanderManager.success1;
//		 			msg.obj=obj;
//					handler.sendMessage(msg);
//		 		 } catch (Exception e) {
//		 			e.printStackTrace();
//		 			msg=new Message();
//		 			msg.what=HanderManager.error2;
//		 			ErrorMessage em=new ErrorMessage();
//		 			 em.message=error_code+ErrorCode.getCodeFromException(e);
//		 			msg.obj=em;
//					handler.sendMessage(msg);
//					LogHandler.writeLogFromException(TAG,e);
//		 		 }
//		      }
//	   }
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
