package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import com.beikbank.android.data.Remdom;
import com.beikbank.android.data.Remdom_data;
import com.beikbank.android.dataparam.RemmoneyParam;
import com.beikbank.android.dataparam.UpdateTPasswdParam;
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
 *2015-1-9
 * 取现
 */
public class RemdomManager {
	    String error_code;	
		private Activity act;
	    private String TAG="RemdomManager";
	    ICallBack icb;
	    String memberID;
	    String amount;
	    String fundId;
	    String bandCardId;
	    String verificode;
	    String tradePassword;
	    /**
	     * 
	     * @param act
	     * @param icb
	     */
	   public RemdomManager(Activity act,ICallBack icb)
	   {
           this.act=act;
		   this.icb=icb;
	   }
	   /**
	    * @see RemmoneyParam
	    * @param memberID
	    * @param amount
	    * @param fundId
	    * @param bandCardId
	    * @param verificode
	    * @param tradePassword
	    */
	   public void start(String memberID,String amount,String fundId,String bandCardId,String verificode,String tradePassword)
	   {   
		   this.memberID=memberID;
		   this.amount=amount;
		   this.bandCardId=bandCardId;
		   this.fundId=fundId;
		   this.verificode=verificode;
		   this.tradePassword=tradePassword;
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
	   }
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
		     Message msg=new Message();
			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
	 		 RemmoneyParam t=new RemmoneyParam();
    		 t.memberID=memberID;
    		 t.amount=amount;
    		 t.fundId=fundId;
    		 t.bandCardId=bandCardId;
    		 t.verificode=verificode;
    		 t.tradePassword=MD5.md5s32(tradePassword);
    		 Object obj=im.redeemMoney(Remdom_data.class,null,t);
			 ErrorMessage  em=ErrorMessage.getEm(obj, error_code);
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
	   ICallBackHnadler icbh=new ICallBackHnadler() {
		
		@Override
		public void back(Message msg) {
			
			if(msg.what==HandlerBase.success1)
			{
				icb.back(msg.obj);
			}
		}
	};
	
}
