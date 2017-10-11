package com.beikbank.android.exception;

import java.util.Timer;
import java.util.TimerTask;

import com.beikbank.android.activity.WelcomeActivity;
import com.beikbank.android.utils.DialogManager;

import comc.beikbank.android.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-10
 **/
public class GlobalErrorActivity extends Activity{
    TextView tv=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.global_error_page);
		tv=(TextView)findViewById(R.id.time);
		tast();
	}
	Handler h=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what==1)
			{
				tv.setText(time+"s");
				if(time==0)
				{
					Intent intent=new Intent(GlobalErrorActivity.this,WelcomeActivity.class);
					startActivity(intent);
				}
			}
			
		}
		
		
	};
	int time=10;
    private void tast()
    {
    	Timer timer=new Timer();
    	TimerTask tt=new TimerTask() {
			
			@Override
			public void run() {
				time--;
				Message msg=new Message();
				msg.what=1;
				h.sendMessage(msg);
			}
		};
		timer.schedule(tt,0,1000);
    }
}
