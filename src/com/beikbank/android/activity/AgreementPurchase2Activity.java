package com.beikbank.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//购买协议
public class AgreementPurchase2Activity extends BaseActivity implements OnClickListener{

	private TextView titleTv;
	private WebView webview_agreement;
	private LinearLayout linear_left;
   //private String assetPath="file:///android_asset/beikbank_entrust.html";
    private String assetPath=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/xieyi/beikbank_entrust.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agreement);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.pay_agreement2));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		webview_agreement=(WebView)findViewById(R.id.webview_agreement);
		webview_agreement.loadUrl(assetPath);
        //Utils.loadHtml(webview_agreement,assetPath);
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
