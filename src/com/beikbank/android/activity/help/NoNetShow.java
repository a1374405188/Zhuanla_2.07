package com.beikbank.android.activity.help;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.net.ICallBack;
import com.tencent.mm.sdk.ConstantsUI.Contact;
import coma.beikbank.android.R;



/**
 * 
 * @author Administrator
 *没有网络提示
 */
public class NoNetShow 
{  
	
	/**
	 * 显示没有网络提示
	 * @param text 标题
	 * @param icb 不为null时 点击刷新回调icb.bak()
	 */
   public static void show(final Activity act,String text,final ICallBack icb)
   {   
	  
	   act.setContentView(R.layout.activity_error);
	   TextView	titleTv = (TextView) act.findViewById(R.id.titleTv);
	   titleTv.setText(text);
	   
	   
	    LinearLayout linear_left = (LinearLayout) act.findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				act.finish();
				
			}
		});
	   
	   
	   
	   
	   
	   if(icb!=null)
	   {
	       TextView tv_relod=(TextView) act.findViewById(R.id.tv_tv);
	       tv_relod.setVisibility(View.VISIBLE);
	       tv_relod.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				icb.back(null);
				
			}
		});
	   }
   }
}
