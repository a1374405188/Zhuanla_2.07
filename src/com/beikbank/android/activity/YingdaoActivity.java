package com.beikbank.android.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import com.beikbank.android.widget2.SlideShowView2;
import coma.beikbank.android.R;



//引导程序
public class YingdaoActivity extends BaseActivity1 implements OnClickListener{
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yingdao);
		StateBarColor.init(this,0xffffffff);
		initView();
        
	}


	 
	  private void initView()
	    {
	        
		    SlideShowView2 ss=(SlideShowView2) findViewById(R.id.ll1);
	        ss.start(this);
	        
	        
	    }

	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	       
	      }
	      
	   }
}
