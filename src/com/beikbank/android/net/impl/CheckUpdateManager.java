package com.beikbank.android.net.impl;

import android.app.Activity;
import android.app.Dialog;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.beikbank.android.activity.BaseActivity;

import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.CheckUpdate;
import com.beikbank.android.data.CheckUpdate_data;
import com.beikbank.android.dataparam.CheckUpdateParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.ThreadManager;
import com.beikbank.android.utils.BeikBankDialogListener;
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
public class CheckUpdateManager extends ThreadManager{
	static String tag="CheckUpdateManager";
	public CheckUpdateManager(Activity act,ICallBack icb)
	{
		this.act=act;
		this.icb=icb;
		init();
	}
	private void init()
	{    
		 error_code="";
		 icbh=new ICallBackHnadler() {
			
			@Override
			public void back(Message msg) {
				CheckUpdate_data cd=null;
				if(msg.obj!=null&&msg.obj instanceof CheckUpdate_data)
				{
					 cd=(CheckUpdate_data)msg.obj;
				}
				switch (msg.what) {
	            case HandlerBase.success1:
	              if(cd!=null)
	              {
					CheckUpdate cu=cd.data;
					if(cu.hasUpdate)
					{
						//showUpdataDialog(cu.downLoadUrl);
						icb.back(cu.downLoadUrl);
					}
					else
					{   
						icb.back(null);
					}
					
	              }
	              break;
	             
				}
			}
		};
		 icbn=new ICallBackNet() {
			
			@Override
			public void back(Handler handler)  throws Exception
			{     
				   Message msg=new Message();
				
					IBusiness ib=(IBusiness)NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
					CheckUpdateParam cup=new CheckUpdateParam();
					String version2=Utils.getVersion(act);
					cup.version=version2;
					cup.osType="1";
					Object obj=ib.checkUpdate(CheckUpdate_data.class,null,cup);
					ErrorMessage em=ErrorMessage.getEm(obj, error_code);
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
	}  
	  @Override
	   public void start()
	   {   
//		   if(!isFinish)
//		   {
//			   return;
//		   }
//		   isFinish=false;
//		   dialog=Utils.createPorgressDialog(act, null);
//		   dialog.show();
//		   handler3=new HandlerBase(act)
//			{
//				String error_code=ErrorCodeInfo.e1+"";
//				@Override
//				public void handleMessage(Message msg) {
//					if(dialog!=null)
//		            {
//		            	dialog.dismiss();
//		            }
//					super.handleMessage(msg);
//					CheckUpdate_data cd=null;
//					if(msg.obj!=null&&msg.obj instanceof CheckUpdate_data)
//					{
//						 cd=(CheckUpdate_data)msg.obj;
//					}
//					switch (msg.what) {
//		            case HandlerBase.success1:
//		              if(cd!=null)
//		              {
//						CheckUpdate cu=cd.data;
//						if(cu.hasUpdate)
//						{
//							showUpdataDialog(cu.downLoadUrl);
//						}
//						else
//						{   
//							
//						}
//						break;
//		              }
//					}
//					icb.back(2);
//					isFinish=true;
//				}
//				
//			};
		  
		   ThreadManagerImpl.execute(act,icbn,icbh,error_code,false);
	   }

	
//	//检查更新线程
//	class TCheckUpdate extends Thread
//	{   
//		
//		@Override
//		public void run()
//		{   
//			Message msg=new Message();
//			ErrorMessage em=new ErrorMessage();
//			try
//			{   BaseActivity ba=(BaseActivity) act;
//				if(!ba.isNetworkConnected(act))
//			    {  
//				   msg.what=HanderManager.nonet;
//				   handler3.sendMessage(msg);
//				   return ;
//			    }
//				IBusiness ib=(IBusiness)NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//				CheckUpdateParam cup=new CheckUpdateParam();
//				String version2=Utils.getVersion(act);
//				cup.version=version2;
//				cup.osType="1";
//				Object obj=ib.checkUpdate(CheckUpdate_data.class,null,cup);
//
////				else
////				{   
////					
////					ResponseCheck rc=new ResponseCheck();
////					ErrorMessage rcr=rc.check(error_code, cd.data);
////					if(rcr.isSuccess)
////					{
////						msg.what=HanderManager.error2;
////						msg.obj=rcr;
////						handler3.sendMessage(msg);
////						LogHandler.writeLogFromString(error_code,rcr.message);
////						return;
////					}
////				}
//				
//				
//				msg.what=HanderManager.success1;
//				msg.obj=obj;
//				handler3.sendMessage(msg);
//				
//			}
//			catch(Exception e)
//			{  
//			   e.printStackTrace();
//			   msg=new Message();
//			   msg.what=HanderManager.error2;
//			   em=new ErrorMessage();
//			   em.message=error_code+ErrorCode.getCodeFromException(e);
//			   msg.obj=em;
//			   handler3.sendMessage(msg);
//			   LogHandler.writeLogFromException(tag,e);
//			}
//		}
//	}
	private Dialog upgrade_version_dialog;	
	//显示需要更新的dialog
	public void showUpdataDialog(final String downloadUrl){
		upgrade_version_dialog=Utils.createSimpleDialog(act,
				act.getString(R.string.upgrade_version),act.getString(R.string.update),new BeikBankDialogListener() {

			@Override
			public void onRightBtnClick() {
				new LoadApkManager(act, downloadUrl).start();
			}

			@Override
			public void onListItemLongClick(int position, String string) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onListItemClick(int position, String string) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLeftBtnClick() {
				// TODO Auto-generated method stub
				//upgrade_version_dialog.dismiss();
				act.finish();
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});
		upgrade_version_dialog.show();
	}	
   public ErrorMessage check(Object obj)
   {
	   ErrorMessage em=new ErrorMessage();
	   try
	   {  
		   if(obj==null)
		   {
			   em.message=error_code+ErrorCodeInfo.ee2;
			   return em;
		   }
		 //检查返回信息
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
	   }
	   catch(Exception e)
	   {   
		   em.message=error_code+ErrorCodeInfo.ee0;
		   e.printStackTrace();
		   LogHandler.writeLogFromException(tag, e);
	   }
	   em.isSuccess=true;
	   return em;
   }
}
