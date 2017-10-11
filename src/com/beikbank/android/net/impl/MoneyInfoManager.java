package com.beikbank.android.net.impl;

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
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.MoneyInfo;
import com.beikbank.android.data.MoneyInfo_data;
import com.beikbank.android.data.TotalMoney;
import com.beikbank.android.data.TotalMoney_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.MoneyInfoParam;
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
public class MoneyInfoManager extends ThreadManager
{ 
	   private String TAG="MoneyInfoManager";
	   public MoneyInfoManager(Activity act,ICallBack icb)
	   {
		   this.act=act;
		   this.icb=icb;
		   init2();
	   }
	   private void init2()
	   {   
		   error_code=ErrorCodeInfo.e12;
		   icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) {
					MoneyInfo_data ud=null;
					if(msg.obj!=null&&msg.obj instanceof MoneyInfo_data)
					{
						ud=(MoneyInfo_data) msg.obj;
					}
					if(msg.what!=HandlerBase.success1)
					{
						 icb.back(ud);
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
			     		MoneyInfoParam mi=new MoneyInfoParam();
			     	    mi.memberID=id;
			 			Object obj=im.getMoneyInfo(MoneyInfo_data.class,null,mi);
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
//				MoneyInfo_data ud=null;
//				if(msg.obj!=null&&msg.obj instanceof MoneyInfo_data)
//				{
//					ud=(MoneyInfo_data) msg.obj;
//				}
//				if(msg.what!=success1)
//				{
//					 icb.back(ud);
//				}
//				super.handleMessage(msg);
//				switch (msg.what) {
//				case success1:
//					  icb.back(ud);
//					break;
//				}
//				isFinish=true;
//			}
//			      
//		   };
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
//		     		MoneyInfoParam mi=new MoneyInfoParam();
//		     	    mi.memberID=id;
//		 			Object obj=im.getMoneyInfo(MoneyInfo_data.class,null,mi);
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

	   private ErrorMessage check(Object obj) throws Exception
	   {
		   ErrorMessage em=new ErrorMessage();
		   if(obj instanceof ErrorMessage)
			{
				ErrorMessage rcr=(ErrorMessage) obj;
				return rcr;
			}
			MoneyInfo_data mid=(MoneyInfo_data) obj;
			   em=ErrorMessage.getEm(obj, error_code);
				if(!em.isSuccess)
				{
					return em;
				}
				else
				{
					em.isSuccess=false;
				}
			try
			{
				MoneyInfo t=mid.data;
				double d=Double.parseDouble(t.interestM);
				d=Double.parseDouble(t.interestT);
				d=Double.parseDouble(t.interestW);
				d=Double.parseDouble(t.interestY);
			}
			catch(Exception e)
			{    e.printStackTrace();
				 em=new ErrorMessage();
		    	 em.message=error_code+ErrorCodeInfo.ee9;
		    	 em.index=1;
				 return em;
			}
		   em.isSuccess=true;
		   return em;
	   }
	   public ErrorMessage getFundinfo(MoneyInfo t)
	   {     
		   double d=0;
		   int a=0;
		   ErrorMessage em=new ErrorMessage();
		      try
		     {    
		    	  d=Double.parseDouble(t.interestM);
		     }
		    catch(Exception e)
		     {  
		    	if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee9;
		    		 a=1;
		    	}
		    	t.interestM="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		     }
		   try
		     {
			   d=Double.parseDouble(t.interestT);
		     }
		   catch(Exception e)
		   {   
			   if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee10;
		    		 a=1;
		    	}
			   t.interestT="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		   }
		   try
		   {
			   d=Double.parseDouble(t.interestW);
		   }
		   catch(Exception e)
		   {   
			   if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee11;
		    		 a=1;
		    	}
			   t.interestW="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		   }
		   try
		   {
			   d=Double.parseDouble(t.interestY);
		   }
		   catch(Exception e)
		   {   
			   if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee12;
		    		 a=1;
		    	}
			   t.interestY="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		   }
		   em.isSuccess=true;
		   return em;
	   }
}
