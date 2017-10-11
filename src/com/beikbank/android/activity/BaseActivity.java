package com.beikbank.android.activity;

import com.beikbank.android.component.HomeKeyEventBroadCastReceiver;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-11
 **/
public class BaseActivity extends Activity{
	private Activity act=this;
	// true 不需要开启手势密码 false 需要开启手势密码
	public static boolean isUnLock=true;
	BroadcastReceiver  receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    receiver = new HomeKeyEventBroadCastReceiver();  
		registerReceiver(receiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));  
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(!isUnLock)
		{   
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_notice,true);
			lock();
		}
		else
		{
			
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	protected void lock()
	{   
		boolean isBindGesture=BeikBankApplication.mSharedPref.getSharePrefBoolean(
				BeikBankConstant.IS_BINDGESTURE, false);
		boolean is_create=BeikBankApplication.mSharedPref.getSharePrefBoolean(
				BeikBankConstant.IS_CREATE_GRESTURE, false);
		
		
		
		String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
		if(isBindGesture&&!phonenumber.equals("")){
//		  if(is_create)
//		  {
//			  BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_CREATE_GRESTURE,false);
//		  }
//		  else
//		  {
			   Intent intent=new Intent(act,GesturePwdUnlockActivity.class);
			   intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,phonenumber);
			   startActivity(intent);
//		  }
		}
	}
	/**
	 * 判断网络是否连接
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
			if (mNetworkInfo != null) {  
				return mNetworkInfo.isAvailable();  
			}  
		}  
		return false;  
	}
}
