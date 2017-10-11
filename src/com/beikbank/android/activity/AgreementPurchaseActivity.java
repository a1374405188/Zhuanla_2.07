package com.beikbank.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//购买协议
public class AgreementPurchaseActivity extends BaseActivity implements OnClickListener{

	private TextView titleTv;
	private WebView webview_agreement;
	private LinearLayout linear_left;
	//private String path1="file:///android_asset/beikbank_pay.html";
	private String path1=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/xieyi/beikbank_pay.html";
	//private String path3="file:///android_asset/beikbank_service.html";
	private String path3=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/xieyi/beikbank_service.html";
    private String assetPath;
    private int title;
    private int title1=R.string.pay_agreement;
    private int title3=R.string.pay_agreement3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agreement);
		StateBarColor.init(this,0xffffffff);
		initIntent();
		initView();

	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(title));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		webview_agreement=(WebView)findViewById(R.id.webview_agreement);
		webview_agreement.loadUrl(assetPath);
        //Utils.loadHtml(webview_agreement,assetPath);
		//loadPDF(webview_agreement,assetPath);
	}
	
   
    private void initIntent()
    {
    	Intent intent=getIntent();
    	String path=intent.getStringExtra("path");
    	if("1".equals(path))
    	{
    		assetPath=path1;
    		title=title1;
    	}
    	else if("3".equals(path))
    	{
    		assetPath=path3;
    		title=title3;
    	}
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
