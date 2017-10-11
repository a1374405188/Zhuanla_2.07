package com.beikbank.android.activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.GetUserZhiChan2;
import com.beikbank.android.data2.getLeiJiShouYi_data;
import com.beikbank.android.data2.getZuoRiShouYi;
import com.beikbank.android.data2.getZuoRiShouYi_data;
import com.beikbank.android.dataparam2.getLeiJiShouYiParam;
import com.beikbank.android.dataparam2.getZuoRiShouYiParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//收益详情
public class ZuoriShouyiActivity2 extends BaseActivity1 implements OnClickListener{
    private static Activity act;
	private TextView titleTv;
	private LinearLayout linear_left;
	private TextView tv_leiji;
	String name;
	String money;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zuorishouyi2);
		StateBarColor.init(this,0xffffffff);
		act=this;
		name=getIntent().getStringExtra("name");
		money=getIntent().getStringExtra("money");
		initView();
		
	}
	
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(name);
		
		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
            tv_leiji=(TextView) findViewById(R.id.tv_leiji);
	
		tv_leiji.setText(NumberManager.getGeshiHua(money, 2));
		
		
		LinearLayout ll_parent=(LinearLayout) findViewById(R.id.ll_parent);
		NoneData.setView(this,ll_parent, 10);
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

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(act, pActClassName);
		act.startActivity(_Intent);
	}
	
	
	
}
