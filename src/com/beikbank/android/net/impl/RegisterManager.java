package com.beikbank.android.net.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;


import com.beikbank.android.activity.RegisterPwdSetActivity;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data.UserInfo_data;
import com.beikbank.android.dataparam.RegsiterParam;
import com.beikbank.android.dataparam.UpdateTPasswdParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.HuanjingInfo;
import com.beikbank.android.utils.MD5;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-9
 * 注册新用户
 */
public class RegisterManager {
	String error_code=ErrorCodeInfo.e2;	
	private Activity act;
    private String TAG="RegisterManager";
    ICallBack icb;
    String phonenumber;
    String passwd;

    /**
     * 
     * @param act
     * @param icb
     */
   public RegisterManager(Activity act,ICallBack icb)
   {   
	   this.act=act;
	   this.icb=icb;
   }
   public void start(String phonenumber,String passwd)
   {   
	   this.passwd=passwd;
	   this.phonenumber=phonenumber;
	   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
   }
   ICallBackNet icbn=new ICallBackNet() {
	
	@Override
	public void back(Handler handler) throws Exception {
	     Message msg=new Message();
		 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
		 RegsiterParam rp=new RegsiterParam();
 		 rp.phoneNumber=phonenumber;
 	     rp.loginPassword=MD5.md5s32(passwd);
 		 HuanjingInfo hi=LoginManager.getHJ(act);
 		 rp.osVersion=hi.osVersion;
 		 rp.rasModel=hi.rasModel;
 		 rp.rasType=hi.rasType;
 		 rp.softVersion=hi.softVersion;
 		 rp.umDeviceToken=hi.umDeviceToken;
 	     
 	     
		 Object obj=im.register(UserInfo_data.class,null,rp);
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
   ICallBackHnadler icbh=new ICallBackHnadler() {
	
	@Override
	public void back(Message msg) {
		
		if(msg.what==HandlerBase.success1)
		{   
			 //设置常驻内存的数据
		    BeikBankApplication.initData();
			icb.back(msg.obj);
		}
		else
		{
			icb.back(null);
		}
	}
};

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
