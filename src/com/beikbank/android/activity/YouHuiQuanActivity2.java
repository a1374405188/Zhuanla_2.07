package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.adapter.HongbaoAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;

import comc.beikbank.android.R;

/**红包使用说明
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-11
 */

public class YouHuiQuanActivity2 extends BaseActivity1 implements View.OnClickListener
{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ProgressBar pb;
    WebView wv;
    String url=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/yhq/yhq1.html";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huodong);
		StateBarColor.init(this,0xffffffff);
		initView();

	}

	  @SuppressLint("SetJavaScriptEnabled") 
	  private void initView()
	    {
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("使用说明");
	        pb=(ProgressBar) findViewById(R.id.pb);
	         
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
	        
	        
	        new WebWiewInface().setInface(wv,this);
	        wv.loadUrl(url);
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
