package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.BindBankCard_data;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.CheckAndSend_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.BindBankCardParam;
import com.beikbank.android.dataparam.CheckAndSendParam;
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

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-28
 * 绑定银行卡
 */
public class BindBankManager extends ThreadManager{
String tag="BindBankManager";
	String cardNumber;
	String id;
	String type;
	String name;
	//是否实名认证
	boolean isrealName;
	public BindBankManager(Activity act,ICallBack icb)
	{
		this.act=act;
		this.icb=icb;
		init2();
	}
	/**
	 * 
	 * @param cardNumber 银行卡号
	 * @param uerid 用户id
	 * @param type 银行类型
	 * @param name 银行名字
	 */
    public void init(String cardNumber,String uerid,String type,String name)
    
    {   
    	this.cardNumber=cardNumber;
    	id=uerid;
    	this.type=type;
    	this.name=name;
    }
    private void init2()
	   {    
		    
		    icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) {
					if(msg.what==HandlerBase.success1)
					{
						icb.back(isrealName);
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
			   	    IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			   	   BindBankCardParam bbc=new BindBankCardParam();
	    		   bbc.cardNumber=cardNumber;
	    		   bbc.memberID=id;
	    		   bbc.cardType=type;
			   	    
	    		   BindBankCard_data obj=(BindBankCard_data) im.bindBankCard(BindBankCard_data.class,null,bbc);
					
					ErrorMessage em=ErrorMessage.getEm(obj, error_code);
					if(!em.isSuccess)
					{
						msg=new Message();
						msg.what=HandlerBase.error2;
						msg.obj=em;
						handler.sendMessage(msg);
						LogHandler.writeLogFromString(tag,em.message);
						return;
					}
					synchronized (ThreadManagerSet.syn_obj) {
						
					
					 BindBankCard_data rnd=(BindBankCard_data)obj;
					 UserInfo userInfo=BeikBankApplication.getUserInfo();
						userInfo.setHasBindCard(true);
		              // isSetPayPasswd=userInfo.hasSetPaypassword;
						isrealName=userInfo.hasSetPaypassword;
						CardInfo cardInfo=new CardInfo();
						//String name=textview_bankname.getText().toString();
						//BankInfo bankInfo=BankInfoDao.getInstance(BankBindActivity.this).getBankInfoByName(name);
						BankList bl=BankListDao.getBankByName(name);
						cardInfo.setType(bl.type);
						//cardInfo.setCardNumber(clearedittext_cardnumber.getText().toString());
						cardInfo.setCardNumber(cardNumber);
						cardInfo.sid=rnd.data.cardId;
						
						TableDaoSimple.delete(CardInfo.class,null,null);
					 // int a=TableDaoSimple.insert(cardInfo);
						int a=CardInfoDao.setCardInfo(cardInfo);
						if(a<0)
						{
							msg.what=HandlerBase.error2;
							handler.sendMessage(msg);
							return;
						}

						TableDaoSimple.delete(UserInfo.class,null,null);
						int b=TableDaoSimple.insert(userInfo);
						if(b<0)
						{
							msg.what=HandlerBase.error2;
							handler.sendMessage(msg);
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
		  ThreadManagerImpl.execute(act,icbn,icbh,error_code,true);
	   }
}
