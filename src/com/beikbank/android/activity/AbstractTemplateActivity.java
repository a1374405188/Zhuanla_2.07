package com.beikbank.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.beikbank.android.fragment.BeikBankApplication;
import com.umeng.analytics.MobclickAgent;

public abstract class AbstractTemplateActivity extends FragmentActivity { 
	protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        MobclickAgent.updateOnlineConfig(this);
        BeikBankApplication application = (BeikBankApplication) this.getApplication(); 
        application.getActivityManager().pushActivity(this); 
      
    } 
  
    @Override 
    public void onBackPressed() { 
        super.onBackPressed(); 
        BeikBankApplication application = (BeikBankApplication) getApplication(); 
        application.getActivityManager().popActivity(this); 
    }

	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
	} 
	
    
    
   
} 
