package com.beikbank.android.utils;


import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.fragment.BeikBankApplication;

import android.app.Activity;
import android.content.Intent;

public class ReturnHome {
	/**
	 * 
	 * @param act
	 * @param index 1跳转到资产页
	 */
   public static void toHome(Activity act,int index)
   {    if(index==1)
      {
	    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_after_pay,true);
      }
	    Intent intent3 = new Intent(act, HomeActivity3.class); 
		BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
				4);
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
		intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		act.startActivity(intent3);
		act.finish();
   }
}
