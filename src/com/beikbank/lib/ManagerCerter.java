package com.beikbank.lib;

import android.content.Context;

import com.beikbank.lib.activtiy.QianbaoActivity7;
import com.beikbank.lib.interfaces.LibInterface;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-24
 */
public class ManagerCerter implements LibInterface{

	@Override
	public void onCreate(Context con, int i) {
		switch(i)
		{
		   case 1:
			QianbaoActivity7 ab7=new QianbaoActivity7();
			ab7.onCreate(con, 0);
			break;
		}
		
	}

	@Override
	public void onStart(Context con, int i) {
		
		
	}

	@Override
	public void onRestart(Context con, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume(Context con, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause(Context con, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop(Context con, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy(Context con, int i) {
		// TODO Auto-generated method stub
		
	}

}
