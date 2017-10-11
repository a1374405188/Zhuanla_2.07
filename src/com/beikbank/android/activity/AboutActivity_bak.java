package com.beikbank.android.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;

import comc.beikbank.android.R;

//关于我们
public class AboutActivity_bak extends BaseActivity1 implements OnClickListener{

	private TextView titleTv,textview_telephone,textview_email,textview_website;
	private LinearLayout linear_left;
	private Dialog dialog;
    private TextView tv_qq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about2);
		initView();
        ConfigManager cm=new ConfigManager(this, icb);
        cm.start();
	}
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				Config_data cd=(Config_data) obj;
				Confing cf=cd.data;
				if(cf.customerQQ!=null)
				{
					tv_qq.setText(cf.customerQQ);
				}
			}
		}
	};
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.about_bk));
		tv_qq = (TextView) findViewById(R.id.tv_qq);
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		textview_telephone=(TextView)findViewById(R.id.textview_telephone);
		textview_email=(TextView)findViewById(R.id.textview_email);
		textview_website=(TextView)findViewById(R.id.textview_website);

		textview_telephone.setOnClickListener(this);
		textview_email.setOnClickListener(this);
		textview_website.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.textview_telephone:
			//拨打贝壳热线
			dialog=Utils.createSimpleDialog(AboutActivity_bak.this, 
					getString(R.string.beike_telephone), getString(R.string.dial), new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub
					String phoneStr=getString(R.string.beike_telephone);
					phoneStr=phoneStr.substring(0,phoneStr.indexOf("("));
					Intent telephoneIntent = new Intent();
					telephoneIntent.setAction(Intent.ACTION_CALL);
					telephoneIntent.setData(Uri.parse("tel:"+phoneStr.replaceAll("-", "")));
					startActivity(telephoneIntent);
				}

				@Override
				public void onListItemLongClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onLeftBtnClick() {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			dialog.show();
			break;
		case R.id.textview_email:
			//email邮件
			Intent emailIntent=new Intent(Intent.ACTION_SEND);  
			String subject = "";  
			String body = "";  
			String[] extra = new String[]{"service@beikbank.com"};
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);  
			emailIntent.putExtra(Intent.EXTRA_TEXT, body);  
			emailIntent.putExtra(Intent.EXTRA_EMAIL, extra);  
			emailIntent.setType("message/rfc822");  

			startActivity(emailIntent);  
			break;
		case R.id.textview_website:
			//贝壳官网
			Intent websiteIntent = new Intent();        
			websiteIntent.setAction("android.intent.action.VIEW");    
			Uri content_url = Uri.parse("http://"+getString(R.string.beike_website));   
			websiteIntent.setData(content_url);  
			startActivity(websiteIntent);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


}
