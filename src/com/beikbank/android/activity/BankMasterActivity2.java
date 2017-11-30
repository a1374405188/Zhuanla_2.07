package com.beikbank.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.IsCheckBank;
import com.beikbank.android.data.IsCheckBank_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.IsCheckBankManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NetDataManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;




//添加银行卡
public class BankMasterActivity2 extends BaseActivity implements OnClickListener{

	
	private LinearLayout linear_left;

	//验证银行卡
    TextView tv_tv1;
    Activity act=this;
    private LinearLayout ll_add_bank;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_master2);
		StateBarColor.init(this,0xffffffff);
		initView();
		
//		IsCheckBankManager icbm=new IsCheckBankManager(act, icb2);
//		String uerid=BeikBankApplication.getUserid();
//		icbm.init(uerid);
//		icbm.start();
	}
	
	private void initView()
	{   
		linear_left=(LinearLayout) findViewById(R.id.linear_left);
		tv_tv1=(TextView) findViewById(R.id.title2);
		tv_tv1.setVisibility(View.VISIBLE);
		ll_add_bank=(LinearLayout) findViewById(R.id.ll_add_bank);
		tv_tv1.setOnClickListener(this);
		ll_add_bank.setOnClickListener(this);
		linear_left.setOnClickListener(this);
		TextView title=(TextView) findViewById(R.id.titleTv);
		title.setText("银行卡");
	}
	

   
	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch(v.getId()){
		case R.id.linear_left:			
			finish();
			break;
		case R.id.title2:			
			intent=new Intent(this,BankSupportActivity.class);
			act.startActivity(intent);
			break;
			
		case R.id.ll_add_bank:			
			intent=new Intent(act,BankBindActivity2.class);
			act.startActivity(intent);
			break;
			
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}




}
