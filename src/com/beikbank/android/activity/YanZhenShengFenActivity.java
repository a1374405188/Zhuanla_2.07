package com.beikbank.android.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beikbank.android.activity.QianbaoActivity2.TextWatcherListener;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.dataparam2.phoneChangeCheckParam;
import com.beikbank.android.dataparam2.phoneChangeParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

//验证身份信息
public class YanZhenShengFenActivity extends BaseActivity1 implements OnClickListener{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
  
    TextView tv1;
    String name;
    String tag;
    Button bu;
    ClearableEditText et1;
    ClearableEditText et2;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yanzhen_shengfen);
		StateBarColor.init(this,0xffffffff);
		name=getIntent().getStringExtra("name");
		tag=getIntent().getStringExtra("tag");
		initView();
        
	}


	  
	  private void initView()
	    {
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("验证身份");
            TextView tv_name=(TextView) findViewById(R.id.name);
	        tv_name.setText(tag);
	        bu=(Button) findViewById(R.id.button_next);
	        bu.setOnClickListener(this);
	        et1=(ClearableEditText) findViewById(R.id.et1);
	        et2=(ClearableEditText) findViewById(R.id.et2);
	        et1.addTextChangedListener(new TextWatcherListener());
	        et2.addTextChangedListener(new TextWatcherListener());
	    }
	  class TextWatcherListener implements TextWatcher {
			@Override
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String s1=et1.getText().toString();
				String s2=et2.getText().toString();
				if (s1.length()>0&&s2.length()>0) {
					bu.setEnabled(true);
				} else {
					bu.setEnabled(false);
				}
			}

		}
	 
	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	         case R.id.button_next:
	          
	           check();
	          break;
	      }
	      
	   }
	   private void check()
	   {  
		   ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Intent intent=new Intent(YanZhenShengFenActivity.this,PhoneChangeActivity.class);
					startActivity(intent);
					finish();
				}
				
			}
		};
		   phoneChangeCheckParam pp=new phoneChangeCheckParam();
		   pp.id_number=et1.getText().toString();
		   pp.id_type="0";
		   pp.login_password=MD5.md5s32(et2.getText().toString());
		   pp.real_name=name;
		   pp.phone_number=BeikBankApplication.getPhoneNumber();
		   TongYongManager2 tym2=new TongYongManager2(this, icb,pp);
		   tym2.start();
	   }
}
