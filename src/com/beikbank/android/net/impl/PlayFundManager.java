package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.data.PlayFund_data;
import com.beikbank.android.data.Remdom_data;
import com.beikbank.android.dataparam.RemmoneyParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.ThreadManager;
import com.beikbank.android.utils.MD5;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-9
 * 购买
 */
public class PlayFundManager extends ThreadManager{
		
		
		    private String TAG="UpdateTPasswdManager";
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
		   public PlayFundManager(Activity act,ICallBack icb)
		   {
	           this.act=act;
			   this.icb=icb;
			   init2();
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
		   public void init(String memberID,String amount,String fundId,String bandCardId,String verificode,String tradePassword)
		   {
			   this.memberID=memberID;
			   this.amount=amount;
			   this.bandCardId=bandCardId;
			   this.fundId=fundId;
			   this.verificode=verificode;
			   this.tradePassword=tradePassword;
		   }
		   private void init2()
		   {   
			   //error_code=ErrorCodeInfo.e6;
			   icbh=new ICallBackHnadler() {
					
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
				 icbn=new ICallBackNet() {
						
						@Override
						public void back(Handler handler) throws Exception {
							 Message msg=new Message();
						  try
						  {
						    
							 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
					 		 RemmoneyParam t=new RemmoneyParam();
				    		 t.memberID=memberID;
				    		 t.amount=amount;
				    		 t.fundId=fundId;
				    		 t.bandCardId=bandCardId;
				    		 t.verificode="1";
				    		 t.tradePassword=MD5.md5s32(tradePassword);
				    		 Object obj=im.playFund(PlayFund_data.class,null,t);
							 ErrorMessage em=check(obj);
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
						  catch(Exception e)
						  {  
							  msg.what=HandlerBase.error3;
							  handler.sendMessage(msg);
							  e.printStackTrace();
							  LogHandler.writeLogFromException(TAG,e);
						  }
						}
					};

		   }
		   public void start()
		   {   
			   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
		   }
		   
		  public ErrorMessage check(Object obj) throws Exception
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
