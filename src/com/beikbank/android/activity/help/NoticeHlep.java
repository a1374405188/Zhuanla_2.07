package com.beikbank.android.activity.help;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.data.Notice;
import com.beikbank.android.data.Notice_data;
import com.beikbank.android.widget.FirstLoginWindows;
import coma.beikbank.android.R;



/**
 * 
 * @author Administrator
 *公告帮助
 */
public class NoticeHlep {
	
	public  LinearLayout ll_notice;
	public  TextView tv_notice;
	private Activity act;
	LinearLayout ll;
	/**
	 * 
	 * @param ll公告父布局
	 * @param act
	 */
	public NoticeHlep(LinearLayout ll,Activity act)
	{
		this.act=act;
		this.ll=ll;
	}
	/**
	 * 
	 * @param obj显示公告 为null时隐藏公告
	 */
	public void showNotice(Object obj)
	{
		if(ll_notice==null)
		{
		  LayoutInflater li=act.getLayoutInflater();
		  ll_notice=(LinearLayout) li.inflate(R.layout.notice,null);
		  tv_notice=(TextView) ll_notice.findViewById(R.id.tv_notice);
		  ll.addView(ll_notice);
		}
		if(obj==null)
		{
			ll_notice.setVisibility(View.GONE);
			return;
		}
		Notice_data nd=(Notice_data) obj;
		Notice n=nd.data;
		if(n.content==null||"".equals(n.content))
		{
			ll_notice.setVisibility(View.GONE);
		}
		else
		{
			ll_notice.setVisibility(View.VISIBLE);
			tv_notice.setText(n.content);
			tv_notice.setFocusable(true);
			tv_notice.setFocusableInTouchMode(true);
			handler.postDelayed(run,1000);
		}
	}
	 //显示公告
	/**
	 * 如果obj==null 隐藏公告
	 * @param obj
	 */
	public void showNotice2(Object obj)
	{   
		if(ll_notice==null)
		{
		  LayoutInflater li=act.getLayoutInflater();
		  ll_notice=(LinearLayout) li.inflate(R.layout.notice,null);
		  tv_notice=(TextView) ll_notice.findViewById(R.id.tv_notice);
		  FirstLoginWindows.addView3(act,ll_notice);
		}
		
		if(obj==null)
		{
			ll_notice.setVisibility(View.GONE);
			return;
		}
		Notice_data nd=(Notice_data) obj;
		Notice n=nd.data;
		if(n.content==null||"".equals(n.content))
		{
			ll_notice.setVisibility(View.GONE);
		}
		else
		{
			ll_notice.setVisibility(View.VISIBLE);
			tv_notice.setText(n.content);
			tv_notice.setFocusable(true);
			tv_notice.setFocusableInTouchMode(true);
			handler.postDelayed(run,1000);
		}
	}
	
	 Runnable run=new Runnable() {
			
			@Override
			public void run() {
				tv_notice.requestFocus();
				handler.removeCallbacks(run);
			}
		};
		Handler handler=new Handler();
}
