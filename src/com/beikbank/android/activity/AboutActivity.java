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

import comc.beikbank.android.R;

//关于我们2代替旧版本
public class AboutActivity extends BaseActivity1 implements OnClickListener{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ProgressBar pb;
    WebView wv;
    String url=SystemConfig.huodong_url+"#!"+"about";
	/*刷新
	 * 
	 */
    TextView tv1;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huodong);
		StateBarColor.init(this,0xffffffff);
		initView();
        String url=getIntent().getStringExtra("url");
        String title=getIntent().getStringExtra("title");
        if(url!=null&&!url.equals(""))
        {   
        	if(title!=null&&!title.equals(""))
            {
            	this.title.setText(title);
            }
        	this.url=url;
        }
       
        new WebWiewInface().setInface(wv,this);
        wv.loadUrl(this.url);
	}


	  @SuppressLint("SetJavaScriptEnabled") 
	  private void initView()
	    {
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("关于我们");
	        pb=(ProgressBar) findViewById(R.id.pb);
//	        tv1=(TextView) findViewById(R.id.right);
//			tv1.setVisibility(View.VISIBLE);
//			tv1.setText("刷新"); 
//	        tv1.setOnClickListener(this);
	        
	        wv=(WebView) findViewById(R.id.wv);
	        wv.setWebChromeClient(new WebChromeClient(){

				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					if(newProgress==100)
					{
						pb.setVisibility(View.GONE);
					}
					else
					{   pb.setVisibility(View.VISIBLE);
						pb.setProgress(newProgress);
					}
					super.onProgressChanged(view, newProgress);
				}});
	        
	        
	        
	        
	    }

	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	         case R.id.right:
	             wv.reload();
	          break;
	      }
	      
	   }
}
