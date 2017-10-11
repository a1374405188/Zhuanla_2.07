package com.beikbank.android.activity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

/**定期债券明细
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-11
 */
public class DingqiZaiquanActivity extends BaseActivity1 implements View.OnClickListener
{

    TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ProgressBar pb;
    WebView wv;
    String url=SystemConfig.kHOST_URL8443_BASE+"beikbankapp/product/dq.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingqi_zaiquan);
        StateBarColor.init(this,0xffffffff);
        String s1=getIntent().getStringExtra("index1");
        if(s1!=null)
        {
          url+="?wd="+s1;
          Log.d("url",url);
        }
        initView();
    }
    @SuppressLint("SetJavaScriptEnabled") private void initView()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        ll1.setOnClickListener(this);
        title=(TextView)findViewById(R.id.titleTv);
        title.setText("项目信息");
        pb=(ProgressBar) findViewById(R.id.pb);
        
        wv=(WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
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
        wv.setWebViewClient(new WebViewClient(){

			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed(); 
				//super.onReceivedSslError(view, handler, error);
			}});
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
