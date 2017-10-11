package com.beikbank.android.net.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.HelpAndSafety;
import com.beikbank.android.data.HelpAndSafety_data;
import com.beikbank.android.dataparam.BankListParam;
import com.beikbank.android.dataparam.HelpAndSafetyParam;
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

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-23
 * 帮助中心，安全保障
 */
public class HelpCenterManager extends ThreadManager{
	   private String TAG="HelpCenter";
	   String index="1";
	   public HelpCenterManager(Activity act,ICallBack icb)
	   {
		   this.act=act;
		   this.icb=icb;
		   init2();
	   }
	   /**
	    * 
	    * @param index 1帮助 中心 2安全保障
	    */
	   public void init(String index)
	   {
		   this.index=index;
		   if("1".equals(index))
		   {
			   error_code=ErrorCodeInfo.e28;
		   }
		   else
		   {
			   error_code=ErrorCodeInfo.e29;
		   }
	   }
	   private void init2()
	   {    
		    error_code=ErrorCodeInfo.e28;
		    icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) {
					if(msg.what==HandlerBase.success1)
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
			   	    HelpAndSafetyParam hs=new HelpAndSafetyParam();
			   	    hs.type=index;
					Object obj=im.helpandSafety(HelpAndSafety_data.class,null,hs);
					
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
}
