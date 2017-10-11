package com.beikbank.android.activity;

import com.beikbank.lib.interfaces.LibInterface;

import android.app.Activity;
import android.os.Bundle;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-24
 */
public class BaseActivity2 extends BaseActivity1
{
    public int i;
    private LibInterface lf;
    public Activity act;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//lf.onCreate(act,i);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//lf.onPause(act,i);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//lf.onResume(act,i);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//lf.onStart(act, i);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		//lf.onRestart(act, i);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//lf.onStop(act,i);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//lf.onDestroy(act, i);
	}
     
}
