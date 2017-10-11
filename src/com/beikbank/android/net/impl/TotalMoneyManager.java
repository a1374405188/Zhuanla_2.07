package com.beikbank.android.net.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.MoneyInfo;
import com.beikbank.android.data.TotalMoney;
import com.beikbank.android.data.TotalMoney_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.ThreadManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 
 */
public class TotalMoneyManager extends ThreadManager{
	   private String TAG="TotalMoneyManager";
	   public TotalMoneyManager(Activity act,ICallBack icb)
	   {
		   this.act=act;
		   this.icb=icb;
		   init2();
	   }
	   private void init2()
	   {    
		    error_code=ErrorCodeInfo.e11;
		    icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) {
					TotalMoney_data ud = null;
					if(msg.what!=HandlerBase.success1)
					{
						 icb.back(null);
					}
					if(msg.obj!=null&&msg.obj instanceof TotalMoney_data)
					{
						ud=(TotalMoney_data) msg.obj;
					}
					switch (msg.what) {
					case HandlerBase.success1:
						  icb.back(ud);
						break;
					}
					
				}
			};
			  icbn=new ICallBackNet() {
				
				@Override
				public void back(Handler handler) throws Exception {
					 Message msg=new Message();
			    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			    	  TotalMoneyParam tm=new TotalMoneyParam();
			    	  UserInfo ui=BeikBankApplication.getUserInfo();
			     		String id=ui.id;
			     		if(id==null||"".equals(id))
			     		{
			     			ErrorMessage em=new ErrorMessage();
			     			em.message=error_code+ErrorCodeInfo.ee3;
			     			msg=new Message();
				 			msg.what=HandlerBase.error2;
				 			msg.obj=em;
				 			handler.sendMessage(msg);
							LogHandler.writeLogFromString(TAG,em.message);
							return;
			     		}
			     		
			     	    tm.memberID=id;
			 			Object obj=im.getTotalMoney(TotalMoney_data.class,null,tm);
			 			ErrorMessage em=ErrorMessage.getEm(obj, error_code);
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
	   }
	   public void start()
	   {   
		   ThreadManagerImpl.execute(act,icbn,icbh,error_code,true);
	   }
//	   class LoadData extends Thread
//	   {      
//		     
//		      @Override
//		      public void run()
//		      {   
//		    	  Message msg=new Message();
//		    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//		     	  try {
//		     		TotalMoneyParam tm=new TotalMoneyParam();
//		     		TableDaoSimple tds=new TableDaoSimple();
//		     		UserInfo ui=(UserInfo) tds.queryone(UserInfo.class,null,null);
//		     		String id=ui.id;
//		     		if(id==null||"".equals(id))
//		     		{
//		     			ErrorMessage em=new ErrorMessage();
//		     			em.message=error_code+ErrorCodeInfo.ee3;
//		     			msg=new Message();
//			 			msg.what=HanderManager.error2;
//			 			msg.obj=em;
//			 			handler.sendMessage(msg);
//						LogHandler.writeLogFromString(TAG,em.message);
//						return;
//		     		}
//		     		
//		     	    tm.memberID=id;
//		 			Object obj=im.getTotalMoney(TotalMoney_data.class,null,tm);
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
//
//		 			msg=new Message();
//		 			msg.what=HanderManager.success1;
//		 			msg.obj=obj;
//					handler.sendMessage(msg);
//		 		 } catch (Exception e) {
//		 			e.printStackTrace();
//		 			msg=new Message();
//		 			msg.what=HanderManager.error2;
//		 			ErrorMessage em=new ErrorMessage();
//		 			em.message=error_code+ErrorCode.getCodeFromException(e);
//		 			msg.obj=em;
//					handler.sendMessage(msg);
//					LogHandler.writeLogFromException(TAG,e);
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
//			TotalMoney_data tm=(TotalMoney_data) obj;
//			 em=ErrorMessage.getEm(obj, error_code);
//		 		if(!em.isSuccess)
//		 		{
//		 			return em;
//		 		}
//		 		else
//		 		{
//		 			em.isSuccess=false;
//		 		}
//			try
//			{
//				TotalMoney t=tm.data;
//				double d=Double.parseDouble(t.totalAmount);
//			}
//			catch(Exception e)
//			{    
//				e.printStackTrace();
//				 em=new ErrorMessage();
//		    	 em.message=error_code+ErrorCodeInfo.ee9;
//				 return em;
//			}
//		   em.isSuccess=true;
//		   return em;
//	   }
}
