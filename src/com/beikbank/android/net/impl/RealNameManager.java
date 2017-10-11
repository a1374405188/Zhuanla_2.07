package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.RealName_data;
import com.beikbank.android.data.TotalMoney_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.RealNameParam;
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
 *2015-1-9
 * 实名认证
 */
public class RealNameManager {
    String error_code;	
 
	private Activity act;
    private String TAG="RealNameManager";
    ICallBack icb;
    String userid;
    String name;
    String id;
    /**
     * 
     * @param act
     * @param userid 用户id
     * @param name 用户名字
     * @param id 用户身份证
     * @param icb
     */
   public RealNameManager(Activity act,String userid,String name,String id,ICallBack icb)
   {   
	   this.act=act;
	   this.userid=userid;
	   this.name=name;
	   this.id=id;
	   this.icb=icb;
   }
   public void start()
   {
	   ThreadManagerImpl.execute(act, icbn, icbh, error_code,true);
   }
   ICallBackNet icbn=new ICallBackNet() {
	
	@Override
	public void back(Handler handler) throws Exception {
	     Message msg=new Message();
		 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
		 RealNameParam rm=new RealNameParam();
		 rm.idNumber=id;
		 rm.memberID=userid;
		 rm.realName=name;
		 Object obj=im.authenticateRealName(RealName_data.class,null,rm);
		 ErrorMessage  em=ErrorMessage.getEm(obj, error_code);
		 if(!em.isSuccess)
		 {
			 msg.what=HandlerBase.error2;
			 msg.obj=em;
			 handler.sendMessage(msg);
			 return;
		 }
		
		        UserInfo userInfo=BeikBankApplication.getUserInfo();
				String realName=name;
				String idNumber=id;
				userInfo.setHasAuthenticated(true);
				userInfo.setRealName(realName);
				userInfo.setIdNumber(idNumber);
				TableDaoSimple.delete(UserInfo.class,null,null);
				//TableDaoSimple.insert(userInfo);
                UserInfoDao.setUserInfo(userInfo);
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
