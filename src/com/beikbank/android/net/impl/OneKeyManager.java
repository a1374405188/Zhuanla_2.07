package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.Base_data;
import com.beikbank.android.dataparam.OneKeyParam;
import com.beikbank.android.dataparam.OneKeyParam2;
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
 *2015-1-12
 * 新的意见反馈
 */
public class OneKeyManager {
	 String error_code;	
		private Activity act;
	    private String TAG="OneKeyManager2";
	    ICallBack icb;
         private boolean isshow=true;

	    OneKeyParam okp;
	   public OneKeyManager(Activity act,ICallBack icb)
	   {   
		   this.act=act;
		   this.icb=icb;
	   }
	   public void init(OneKeyParam okp)
	   {
		   this.okp=okp;
	   }
	   /**
	    * 
	    * @param okp
	    * @param isshow 是否显示进度条
	    */
	   public void init(OneKeyParam okp,boolean isshow)
	   {
		   this.okp=okp;
		   this.isshow=isshow;
	   }
	   /**
	    * 
	    */
	   public void start()
	   {   
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,isshow);
	   }
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
		     Message msg=new Message();
			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			 Object obj=im.oneKey(Base_data.class,null,okp);
			 ErrorMessage em=ErrorMessage.getEm(obj, error_code);
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
			else
			{
				icb.back(null);
			}
		}
	};
	 
}
