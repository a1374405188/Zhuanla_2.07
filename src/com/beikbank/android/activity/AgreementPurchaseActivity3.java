package com.beikbank.android.activity;

import java.io.File;
import java.net.URL;

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
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.utils.NetDataManager2;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.joanzapata.pdfview.PDFView;
import coma.beikbank.android.R;



//购买协议
public class AgreementPurchaseActivity3 extends BaseActivity implements OnClickListener{

	PDFView pdfView;
	TextView tv1;
	private TextView titleTv;
	private LinearLayout linear_left;
	private String assetPath;
	//private String path1="file:///android_asset/beikbank_pay.html";
	private String path1=SystemConfig.huodong_url+"protocol/beikbank_pay.pdf";
	//private String path3="file:///android_asset/beikbank_service.html";
	private String path3=SystemConfig.huodong_url+"protocol/invest_service.pdf";
	private String path2=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/xieyi/beikbank_entrust.pdf";
	private String path4=SystemConfig.huodong_url+"protocol/beikbank_register.pdf";
	private String path5=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/xieyi/beikbank_zhuanrang.pdf";
	private String path6=SystemConfig.kHOST_URL_BASE+"beikbankapp/product/xieyi/beikbank_souquan.pdf";
	private String path7=SystemConfig.huodong_url+"protocol/risk_notification.pdf";
	private int title;
    private int title1=R.string.agreement11;
    private int title2=R.string.agreement12;
    private int title3=R.string.agreement13;
    private int title4=R.string.agreement14;
    private int title5=R.string.agreement15;
    private int title6=R.string.agreement16;
    private int title7=R.string.agreement17;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agreement3);
		StateBarColor.init(this,0xffffffff);
		initIntent();
		initView();
        initData();
	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(title));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		pdfView=(PDFView) findViewById(R.id.pdfView);
	    tv1=(TextView) findViewById(R.id.tv1);
	        //display("beikbank_pay.pdf");
		
        //Utils.loadHtml(webview_agreement,assetPath);
		//loadPDF(webview_agreement,assetPath);
	}
	String path;
	String url;
	private void initData()
	{  
	   url=assetPath;
	   File f1=getFilesDir();

	   File f2=new File(f1,"h1.pdf");
	   path=f2.getPath();
	   new NetDataManager2(icb1, url, path,this);
		
	}
	ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			boolean b=(Boolean)obj;
			if(b)
			{
				display(path);
			}
		}
	};
	 private void display(String assetFileName) {
	   
	        pdfView.fromFile(new File(assetFileName))
	                .defaultPage(1)
	                .onPageChange(new com.joanzapata.pdfview.listener.OnPageChangeListener() {
						
						@Override
						public void onPageChanged(int page, int pageCount) {
							
							tv1.setText(page+""+"/"+pageCount+"");
						}
					})
	                .load();
	       
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
    	else if("2".equals(path))
    	{
    		assetPath=path2;
    		title=title2;
    	}
    	else if("3".equals(path))
    	{
    		assetPath=path3;
    		title=title3;
    	}
    	else if("4".equals(path))
    	{
    		assetPath=path4;
    		title=title4;
    	}
    	else if("5".equals(path))
    	{
    		assetPath=path5;
    		title=title5;
    	}
    	else if("6".equals(path))
    	{
    		assetPath=path6;
    		title=title6;
    	}
    	else if("7".equals(path))
    	{
    		assetPath=path7;
    		title=title7;
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
