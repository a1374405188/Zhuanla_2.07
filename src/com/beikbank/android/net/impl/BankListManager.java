package com.beikbank.android.net.impl;

import java.util.ArrayList;


import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.UserInfo_data;
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

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 
 */
public class BankListManager extends ThreadManager{
   private String TAG="BankListManager";
   public BankListManager(Activity act,ICallBack icb)
   {
	   this.act=act;
	   this.icb=icb;
	   init2();
   }
   private void init2()
   {    
	    error_code=ErrorCodeInfo.e9;
	    icbh=new ICallBackHnadler() {
			
			@Override
			public void back(Message msg) {
				try
				{
			 	BankList_data ud=null;
				if(msg.obj!=null&&msg.obj instanceof BankList_data)
			    {
					ud=(BankList_data) msg.obj;
				}
				switch (msg.what) {
				case HandlerBase.success1:
					   BankListOne bo=ud.data;
						String ver=bo.version;
						if(ver!=null)
						{
							BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.VERSION,ver);
						}

					break;
				}
				}
				catch(Exception e)
				{
					 e.printStackTrace();
					 LogHandler.writeLogFromException(TAG,e);
					 HandlerBase.showMsg(act, error_code+ErrorCodeInfo.ee41, 0);
				}
				finally
				{   
					icb.back(msg.obj);
				}
				
			}
		};
		
		 icbn=new ICallBackNet() {
			
			@Override
			public void back(Handler handler) throws Exception {
				 Message msg=new Message();
		   	    IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
		   	 String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
			    BankListParam bl=new BankListParam();
			    bl.version="1";
				Object obj=im.getBankList(BankList_data.class,null,bl);
				
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
			   	BankList_data bld=(BankList_data) obj;
				 BankListOne bko=bld.data;
				  if(bko.hasUpdate)
				  {
				    int a=dbDo(bko.bankList);
				    if(a<0)
				    {
					  msg=new Message();
					  msg.what=HandlerBase.error2;
					  ErrorMessage rcr=new ErrorMessage();
			 		  rcr.message=error_code+ErrorCodeInfo.ee700;
			 		  msg.obj=rcr;
				      handler.sendMessage(msg);
					  LogHandler.writeLogFromString(TAG,rcr.message);
					 return;
				   }
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
//	   if(!isFinish)
//	   {
//		   return;
//	   }
//	   isFinish=false;
//	   dialog=Utils.createPorgressDialog(act, null);
//	   dialog.show();
//	   handler=new HandlerBase(act)
//	   {
//		  
//
//		@Override
//		public void handleMessage(Message msg) {
//			if(dialog!=null)
//			{
//				dialog.dismiss();
//			}
//			BankList_data ud=null;
//			if(msg.obj!=null&&msg.obj instanceof BankList_data)
//			{
//				ud=(BankList_data) msg.obj;
//			}
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case success1:
//				   BankListOne bo=ud.data;
//					String ver=bo.version;
//					if(ver!=null)
//					{
//						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.VERSION,ver);
//					}
//
//				break;
//			}
//			icb.back(null);
//			isFinish=true;
//		}
//		      
//	   };
	  ThreadManagerImpl.execute(act,icbn,icbh,error_code,true);
   }
 
  
   //HandlerBase handler;
   
//   class LoadData extends Thread
//   {      
//	     
//	      @Override
//	      public void run()
//	      {   
//	    	  Message msg=new Message();
//	    	  IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//	     	  try {
//	     		String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//	     	    BankListParam bl=new BankListParam();
//	     	    bl.version=version;
//	     	    if("".equals(version))
//	     	    {
//	     	    	bl.version="1";
//	     	    }
//	 			Object obj=im.getBankList(BankList_data.class,null,bl);
//	 			
//	 			ErrorMessage em=check(obj);
//	 			if(!em.isSuccess)
//	 			{
//	 				msg=new Message();
//		 			msg.what=HanderManager.error2;
//		 			msg.obj=em;
//		 			handler.sendMessage(msg);
//					LogHandler.writeLogFromString(TAG,em.message);
//					return;
//	 			}
//	 		   	BankList_data bld=(BankList_data) obj;
//	 			 BankListOne bko=bld.data;
//	 			  if(bko.hasUpdate)
//	 			  {
//	 			    int a=dbDo(bko.bankList);
//	 			    if(a<0)
//	 			    {
//	 				  msg=new Message();
//		 			  msg.what=HanderManager.error2;
//		 			  ErrorMessage rcr=new ErrorMessage();
//			 		  rcr.message=error_code+ErrorCodeInfo.ee1;
//			 		  msg.obj=rcr;
//				      handler.sendMessage(msg);
//					  LogHandler.writeLogFromString(TAG,rcr.message);
//					 return;
//	 			   }
//	 			   }
//	 			
//
//	 			msg=new Message();
//	 			msg.what=HanderManager.success1;
//	 			msg.obj=obj;
//				handler.sendMessage(msg);
//	 		 } catch (Exception e) {
//	 			e.printStackTrace();
//	 			msg=new Message();
//	 			msg.what=HanderManager.error2;
//	 			ErrorMessage em=new ErrorMessage();
//	 			 em.message=error_code+ErrorCode.getCodeFromException(e);
//	 			msg.obj=em;
//				handler.sendMessage(msg);
//				LogHandler.writeLogFromException(TAG,e);
//	 		 }
//	      }
//   }
   private int dbDo(ArrayList<BankList> list) throws Exception
   {  
	   
	   int a=-1;
	   synchronized(ThreadManagerSet.syn_obj)
	   {
	   if(list!=null)
	   {
		     TableDao td=DbManagerFactory.getTableDao(null);
		     SQLiteDatabase sdb= td.opean();
			 sdb.beginTransaction();
			 td.deleteTable(BankList.class);
			 td.createTable(BankList.class);
			 for(int i=0;i<list.size();i++)
			 {
				 td.insert(list.get(i));
			 }
			 sdb.setTransactionSuccessful();
			 sdb.endTransaction();
			td.close();
			a=0;
	   }
	   }
	   return a;
   }
   private ErrorMessage check(Object obj) throws Exception
   {
	   ErrorMessage em=new ErrorMessage();
	   if(obj instanceof ErrorMessage)
		{
			ErrorMessage rcr=(ErrorMessage) obj;
			return rcr;
		}
		BankList_data bld=(BankList_data) obj;
		em=ErrorMessage.getEm(obj, error_code);
		if(!em.isSuccess)
		{
			return em;
		}
		else
		{
			em.isSuccess=false;
		}
	   BankListOne bl=bld.data;
	   if(bl==null)
	   {
		   em.message=error_code+ErrorCodeInfo.ee2;
		   return em;
	   }
	   ArrayList list=bl.bankList;
	   if(list==null)
	   {
		   em.message=error_code+ErrorCodeInfo.ee3;
		   return em;
	   }
	   em.isSuccess=true;
	   return em;
   }
}
