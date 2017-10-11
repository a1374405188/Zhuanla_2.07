package com.beikbank.android.utils;

import android.content.Context;

import com.beikbank.android.dao.ApplicationUpdate;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.Message;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.fragment.BeikBankApplication;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-15
 * 该类用于登录管理
 */
public class LoginManager {
	/**
	 * 
	 * @param context
	 * @param index 0只退出登录，1清除所有数据
	 */
   public static void outLogin(Context context,int index)
   {    
	    if(index==0)
	    {
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.FIRST_LOGIN,true);
			boolean do_success=BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
			
	    }
	    else if(index==1)
	    {		
	       ApplicationUpdate.deleteDb();
	       ApplicationUpdate.deletePer(context);
	    	return;
	    }
	   // TableDaoSimple.delete(UserInfo.class,null,null);
	   // TableDaoSimple.delete(Message.class,null,null);
	    //TableDaoSimple.delete(BankInfo.class,null,null);
	    BeikBankApplication.reUserSet();
		BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
				"");
		BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
				"");
		//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
		//false);
		//是否需要验证银行卡
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,false);
		
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_finrst_bank,false);
//		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_CREATE_GRESTURE, 
//				false);
		//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
		//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
		//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
		//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
   }
}
