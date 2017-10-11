package com.beikbank.android.net.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.FundInfo_data;
import com.beikbank.android.dataparam.BankListParam;
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

import comc.beikbank.android.R;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 
 */
public class FundInfoManager extends ThreadManager{
	   private String TAG="FundInfoManager";
	   public FundInfoManager(Activity act,ICallBack icb)
	   {
		   this.act=act;
		   this.icb=icb;
		   init2();
	   }
	   public void start()
	   {   
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,false);
//		   if(!isFinish)
//		   {
//			   return;
//		   }
//		   dialog=Utils.createPorgressDialog(act, null);
//		   dialog.show();
//		   handler=new HandlerBase(act)
//		   {
//
//			@Override
//			public void handleMessage(Message msg) 
//			{
//				
//				   if(dialog!=null)
//					{
//						dialog.dismiss();
//					}
//					FundInfo_data ud=null;
//					  if(msg.obj!=null&&msg.obj instanceof FundInfo_data)
//					  {
//						ud=(FundInfo_data)msg.obj;
//					  }
//					if(msg.what!=success1)
//					{
//						icb.back(null);
//					}
//					super.handleMessage(msg);
//					
//					switch (msg.what) 
//					{
//					   case success1:
//					     FundInfo_data fd=(FundInfo_data)ud;
//				 		 ArrayList<FundInfo> list=(ArrayList<FundInfo>)fd.data;
//				         icb.back(list.get(0));
//						break;
//			           case error1:
//			        	   MessageManger.showMeg(act,ud.message,Toast.LENGTH_LONG);
//						 break;
//					}
//					isFinish=true;
//			 }  
//		   };
		  // new LoadData().start();
	   }
	   private void init2()
	   {    
		   error_code=ErrorCodeInfo.e9;
		    icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) {
					FundInfo fi=null;
				  try
				  {
					FundInfo_data ud=null;
					  if(msg.obj!=null&&msg.obj instanceof FundInfo_data)
					  {
						ud=(FundInfo_data)msg.obj;
					  }
//					if(msg.what!=HandlerBase.success1)
//					{
//						icb.back(null);
//					}			
					switch (msg.what) 
					{
					   case HandlerBase.success1:
					     FundInfo_data fd=(FundInfo_data)ud;
				 		 ArrayList<FundInfo> list=(ArrayList<FundInfo>)fd.data;
				         fi=list.get(0);
						break;
					}
				  }
				  catch (Exception e) {
					     e.printStackTrace();
						 LogHandler.writeLogFromException(TAG,e);
						 HandlerBase.showMsg(act, error_code+ErrorCodeInfo.ee41, 0);
				  }
				  finally
				  {
					  icb.back(fi);
				  }
				}
			};
			
			 icbn=new ICallBackNet() {
				
				@Override
				public void back(Handler handler) throws Exception {
					  Message msg=new Message();
			    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			    	  Object obj=im.getProductFundList(FundInfo_data.class,null,null);

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
//	   class LoadData extends Thread
//	   {   
//		   @Override
//		   public void run()
//		   {
//			   Message msg=new Message();
//		    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//		     	  try {
//		 			Object obj=im.getProductFundList(FundInfo_data.class,null,null);
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
//		 			msg=new Message();
//		 			msg.what=HandlerBase.success1;
//		 			msg.obj=obj;
//					handler.sendMessage(msg);
//		 		 } catch (Exception e) 
//		 		 {
//		 			e.printStackTrace();
//		 			msg=new Message();
//		 			msg.what=HandlerBase.error2;
//		 			ErrorMessage em=new ErrorMessage();
//		 			 em.message=error_code+ErrorCode.getCodeFromException(e);
//		 			msg.obj=em;
//					handler.sendMessage(msg);
//					LogHandler.writeLogFromException(TAG,e);
//		 		 
//		 		 }
//		   }
//	   }
//	   HandlerBase handler;
	   private ErrorMessage check(Object obj)
	   {  
		   ErrorMessage em=new ErrorMessage();
		   try
		   {   
			   if(obj instanceof ErrorMessage)
				{

					ErrorMessage rcr=(ErrorMessage) obj;
					return rcr;
				}
	 			FundInfo_data bld=(FundInfo_data) obj;
	 		   em=ErrorMessage.getEm(obj, error_code);
	 			if(!em.isSuccess)
	 			{
	 				return em;
	 			}
	 			else
	 			{
	 				em.isSuccess=false;
	 			}
			   ArrayList<FundInfo> list=bld.data;
			   if(list==null||list.size()==0)
			   {
				   em.message=error_code+ErrorCodeInfo.ee2;
				   return em;
			   }
//			   try
//			   {
//			     FundInfo fi=list.get(0);
//			     em.message=error_code+ErrorCodeInfo.ee9;
//			     double d=Double.parseDouble(fi.rate);
//			     em.message=error_code+ErrorCodeInfo.ee10;
//			     d=Double.parseDouble(fi.totalIncome);
//			     em.message=error_code+ErrorCodeInfo.ee11;
//			     d=Double.parseDouble(fi.totalInvestment);
//			     em.isSuccess=true;
//			   }
//			   catch(Exception e)
//			   {
//				   e.printStackTrace();
//				   LogHandler.writeLogFromException(TAG, e);
//			   }
			   FundInfo fi=list.get(0);
			   em=getFundinfo(fi);
			  if(!em.isSuccess)
			  {
				  return em;
			  }
		   }
		   catch (Exception e) 
		   {
			   em.message=error_code+ErrorCodeInfo.ee2;
			  e.printStackTrace();
			  LogHandler.writeLogFromException(TAG, e);
		   }
		   em.isSuccess=true;
		   return em;
	   } 
	   public ErrorMessage getFundinfo(FundInfo fi)
	   {     
		   double d=0;
		   int a=0;
		   ErrorMessage em=new ErrorMessage();
		      try
		     {    
		    	  d=Double.parseDouble(fi.rate);
		     }
		    catch(Exception e)
		     {  
		    	if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee9;
		    		 a=1;
		    	}
		       fi.rate="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		     }
		   try
		     {
			   d=Double.parseDouble(fi.totalIncome);
		     }
		   catch(Exception e)
		   {   
			   if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee10;
		    		 a=1;
		    	}
			   fi.totalIncome="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		   }
		   try
		   {
			   d=Double.parseDouble(fi.totalInvestment);
		   }
		   catch(Exception e)
		   {   
			   if(a==0)
		    	{
		    		 em.message=error_code+ErrorCodeInfo.ee11;
		    		 a=1;
		    	}
			   fi.totalInvestment="0";
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG, e);
		   }
		   em.isSuccess=true;
		   return em;
	   }
}
