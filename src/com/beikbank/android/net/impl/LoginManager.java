package com.beikbank.android.net.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import cn.sharesdk.wechat.utils.WechatResp.ErrCode;

import com.beikbank.android.activity.BaseActivity;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.conmon.ShareParam;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankListOne;
import com.beikbank.android.data.BankList_data;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data.UserInfo_data;
import com.beikbank.android.dataparam.LoginParam;
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
import com.beikbank.android.utils.HuanjingInfo;
import com.beikbank.android.utils.LockPatternUtils;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.umeng.message.UmengRegistrar;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-5
 * 
 */
public class LoginManager extends ThreadManager{
	  private String TAG="LoginManager";
	  private int index;
	  //加密的
	  String password=null;
	  String phonenumber=null;
	  LoginParam lp=new LoginParam();
	  ICallBack icb;
	  /**
	   * 
	   * @param act
	   * @param phone
	   * @param passwd
	   * @param index 1 登录 2注册
	   */
	  public LoginManager(Activity act,String phone,String passwd,int index,ICallBack icb)
	  {
		  this.act=act;
		  this.index=index;
		  this.icb=icb;
		  phonenumber=phone;
		  password=passwd;
		  
		 
		  
	
		    HuanjingInfo hj=getHJ(act);
		    lp.osVersion=hj.osVersion;
		    lp.rasModel=hj.rasModel;
		    lp.rasType=hj.rasType;
		    lp.softVersion=hj.softVersion;
		    lp.umDeviceToken=hj.umDeviceToken;
		  init2();
		
		  
		  
		  System.out.println(hj.umDeviceToken);
	  }
	  private void init2()
	  {    
		  error_code=ErrorCodeInfo.e5;
		   icbh=new ICallBackHnadler() {
				
				@Override
				public void back(Message msg) {
					  try
					  { 
						//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
						UserInfo_data ud=null;
						if(msg.obj!=null&&msg.obj instanceof UserInfo_data)
						{
							ud=(UserInfo_data) msg.obj;
						} 
		                if(msg.what==HandlerBase.success1)
		                {
		                	 // String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
							//BeikBankApi.getInstance().getBankListInfo(getApplicationContext(),version,
							//		getBankListInfoHandler);
		                   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,true);
						   BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
									phonenumber);
						   BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
										password);
							//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
							//		false);
							//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
							//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
							//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
							//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT); 
							BeikBankApplication.getInstance().getSharedPref().putSharePrefString(phonenumber+BeikBankConstant.BUY_PASSWD,ShareParam.STRING_PARAM);
						    int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
		              
							//Intent intent = new Intent(LoginPwdInputActivity.this, HomeActivity2.class); 
							//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							//startActivity(intent);
						    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,true);
						    
						    BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.renalName,ud.data.countAuthLeft);
						    //设置常驻内存的数据
						    BeikBankApplication.initData();
							if(intflag==2||intflag==3){
								//不需要toast
							}else{
								//CustomToast.makeText(LoginPwdInputActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
							}
						   if(index==1)
						   {
							  
						   }
						   icb.back(ud);
		                }
		                else
		                {
		                	icb.back(null);
		                }
					  }
					  catch (Exception e) 
					  {
						 e.printStackTrace();
						 LogHandler.writeLogFromException(TAG,e);
						 HandlerBase.showMsg(act, error_code+ErrorCodeInfo.ee41, 0);
					  }
				}
			};
			
			  icbn=new ICallBackNet() {
					
					@Override
					public void back(Handler handler) throws Exception {
						Message msg=new Message();
			            if(phonenumber==null)
			            {
			            	phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
			            }
			            if(password==null)
			            {
			            	password=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_PASSWORD);
			            }
					    if("".equals(password)||"".equals(phonenumber))
					    {   
					    	LogHandler.writeLogFromException(TAG,new NullPointerException("phonenumber or passwd is null"));
					    	 msg.what=HandlerBase.success2;
							 handler.sendMessage(msg);
					    	return;
					    }
					    IBusiness ib=(IBusiness)NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
					    lp.loginPassword=password;
					    lp.phoneNumber=phonenumber;
					    Object obj=ib.login(UserInfo_data.class,null,lp);
					  //检查返回信息
					    ErrorMessage em=check(obj);
			            if(!em.isSuccess)
			            {
					    	 LogHandler.writeLogFromString(TAG,em.message);
					    	 msg.what=HandlerBase.error2;
							 msg.obj=em;
							 handler.sendMessage(msg);
							 return;
			            }
			            //com.beikbank.android.utils.LoginManager.outLogin(act,0);
					    
					    
					    int a=  doAfterMsg((UserInfo_data) obj);
					    if(a<0)
					    {     
					    	  LogHandler.writeLogFromString(TAG,"数据库操作错误");
					    	  ErrorMessage rcr=new ErrorMessage();
					 		  rcr.message=error_code+ErrorCodeInfo.ee700;
							  msg.obj=rcr;
							  msg.what=HandlerBase.error2;
					    }
					    else
					    {
					    	  msg.what=HandlerBase.success1;
							  msg.obj=obj;
					    }
						handler.sendMessage(msg);
					 }
					
				};
	  }
	  public void start()
	  {   
//		  if(!isFinish)
//		   {
//			   return;
//		   }
//		   isFinish=false;
//		  dialog=Utils.createPorgressDialog(act, null);
//		  dialog.show();
//		  handler2=new HandlerBase(act)
//		   {
//				@Override
//				public void handleMessage(Message msg) {
//					if(dialog!=null)
//	             {
//	             	dialog.dismiss();
//	             }
//					 BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.DO_SUCCESS1,BeikBankConstant.DO_ERROR1_VALUE);
//					super.handleMessage(msg);
//					UserInfo_data ud=null;
//					if(msg.obj!=null&&msg.obj instanceof UserInfo_data)
//					{
//						ud=(UserInfo_data) msg.obj;
//					}
//					switch(msg.what)
//					{  
//
//					   case HandlerBase.success1:
//						   String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//							//BeikBankApi.getInstance().getBankListInfo(getApplicationContext(),version,
//							//		getBankListInfoHandler);
//							
//						
//						   //BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//							//		phonenumber);
//							//BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//							//		password);
//							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//									false);
//							BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//									LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//							BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//									LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//			                
//							BeikBankApplication.getInstance().getSharedPref().putSharePrefString(phonenumber+BeikBankConstant.BUY_PASSWD,ShareParam.STRING_PARAM);
//						    int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
//	                     
//							//Intent intent = new Intent(LoginPwdInputActivity.this, HomeActivity2.class); 
//							//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//							//startActivity(intent);
//						    BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.DO_SUCCESS1,BeikBankConstant.DO_SUCCESS1_VALUE);
//							if(intflag==2||intflag==3){
//								//不需要toast
//							}else{
//								//CustomToast.makeText(LoginPwdInputActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//							}
//						   if(index==1)
//						   {
//							  
//						   }
//						   break;  
//					}
//					icb.back(null);
//					isFinish=true;
//				}
//		   };
		  ThreadManagerImpl.execute(act,icbn,icbh, error_code,true);
	  }
	  
	 
	 //线程加锁对象
		private  int doAfterMsg(UserInfo_data ud) throws Exception
		{    
			synchronized (ThreadManagerSet.syn_obj) {
			 int a=-1;
			 if(ud.result.equals("success"))
			 {   
				 UserInfo ui=ud.data;
				 if(ui!=null)
				 {  
					 
					 TableDao td=DbManagerFactory.getTableDao();
					 SQLiteDatabase sdb= td.opean();
					 sdb.beginTransaction();
					 td.deleteTable(UserInfo.class);
					 td.createTable(ui.getClass());
					 //UserInfoDao.entipy(ui);
					 td.insert(ui);
					
					 if(ui.isHasBindCard())
					 {
					   ArrayList<CardInfo> list=ui.cards;
					   td.deleteTable(CardInfo.class);
					   td.createTable(CardInfo.class);
					   for(int i=0;i<list.size();i++)
					   { 
						 CardInfoDao.entipy(list.get(i));
						 td.insert(list.get(i));
					   }
					 }
					 sdb.setTransactionSuccessful();
					 sdb.endTransaction();
					 td.close();
					 a=1;
				 }
			 }
			 return a;
			}
		}
		  

//		 //登录线程
//			class LoadData extends Thread
//		   {
//			
//			@Override
//			public void run() {
//				Message msg=new Message();
//				try
//				
//				{   
//					BaseActivity ba=(BaseActivity) act;
//					if(!ba.isNetworkConnected(act))
//				    {  
//					   msg.what=HanderManager.nonet;
//					   handler2.sendMessage(msg);
//					   return ;
//				    }
//		            if(password==null||phonenumber==null)
//		            {
//				     password=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_PASSWORD);
//				     phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//		            }
//				    if("".equals(password)||"".equals(phonenumber))
//				    {   
//				    	LogHandler.writeLogFromException(TAG,new NullPointerException());
//				    	 msg.what=HanderManager.success2;
//						 handler2.sendMessage(msg);
//				    	return;
//				    }
//				    IBusiness ib=(IBusiness)NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//				    LoginParam lp=new LoginParam();
//				    //lp.loginPassword=MD5.md5s32(password);;
//				    lp.loginPassword=password;;
//				    lp.phoneNumber=phonenumber;
//				    Object obj=ib.login(UserInfo_data.class,null,lp);
//				  //检查返回信息
//				    ErrorMessage em=check(obj);
//                    if(!em.isSuccess)
//                    {
//				    	 LogHandler.writeLogFromString(TAG,em.message);
//				    	 msg.what=HanderManager.error2;
//						 msg.obj=em;
//						 handler2.sendMessage(msg);
//						 return;
//                    }
//				    
//				    
//				    
//				    int a=  doAfterMsg((UserInfo_data) obj);
//				    if(a<0)
//				    {     
//				    	  LogHandler.writeLogFromString(TAG,"数据库操作错误");
//				    	  ErrorMessage rcr=new ErrorMessage();
//				 		  rcr.message=error_code+ErrorCodeInfo.ee1;
//						  msg.obj=rcr;
//				    }
//				    else
//				    {
//				    	  msg.what=HanderManager.success1;
//						  msg.obj=obj;
//				    }
//					handler2.sendMessage(msg);
//				}
//				catch(Exception e)
//				{   
//					msg.what=HanderManager.error2;
//		 			ErrorMessage em=new ErrorMessage();
//		 			 em.message=error_code+ErrorCode.getCodeFromException(e);
//		 			msg.obj=em;
//					handler2.sendMessage(msg);
//					e.printStackTrace();
//					LogHandler.writeLogFromException(TAG,e);
//				}
//			}
//			   
//		   }
		//
		public static HuanjingInfo getHJ(Activity act)
		{
			HuanjingInfo hj=new HuanjingInfo();
			hj.osVersion=Build.VERSION.RELEASE+":"+Build.VERSION.SDK;
			hj.softVersion=Utils.getVersion(act);
			String s=UmengRegistrar.getRegistrationId(act);
			String device_model = Build.MODEL;
			if(device_model!=null)
			{
				hj.rasModel=device_model;
			}
			if(s!=null)
			{
				hj.umDeviceToken=s;
			}
			else
			{
				LogHandler.writeLogFromString("token","token is null");
			}
			//检查参数
			if(hj.osVersion==null||hj.rasModel==null||hj.rasType==null||hj.softVersion==null||hj.umDeviceToken==null)
			{
				hj=new HuanjingInfo();
			}
			return hj;
		}
			 private ErrorMessage check(Object obj) throws Exception
			   {    
				    ErrorMessage em=new ErrorMessage();
				    if(obj instanceof ErrorMessage)
					{
						ErrorMessage rcr=(ErrorMessage) obj;
						return rcr;
					}
				    
				    UserInfo_data ud= (UserInfo_data) obj;
					   em=ErrorMessage.getEm(obj, error_code);
						if(!em.isSuccess)
						{
							return em;
						}
						else
						{
							em.isSuccess=false;
						}
				    UserInfo ui=ud.data;
				    if(ui==null)
				    {
				    	em=new ErrorMessage();
				    	em.message=error_code+":"+ErrorCodeInfo.ee2;
				    	return em;
				    }
				    String id=ui.id;
				    if(id==null||"".equals(id))
				    {
				    	em=new ErrorMessage();
				    	em.message=error_code+":"+ErrorCodeInfo.ee3;
				    	return em;
				    }
				   em.isSuccess=true;
				   return em;
			   }			
}
