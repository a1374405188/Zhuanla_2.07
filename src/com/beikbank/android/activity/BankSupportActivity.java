package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beikbank.android.adapter.SupportBanksAdapter;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data2.getAllBank;
import com.beikbank.android.data2.getAllBank_data;
import com.beikbank.android.dataparam2.getAllBankParam;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.BankListManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//支持银行
public class BankSupportActivity extends BaseActivity implements OnClickListener{

	private LinearLayout linear_left;
	private TextView titleTv;
	private ListView listview_support_banks;
	private SupportBanksAdapter adapter;
	Activity act=this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support_banks);
		StateBarColor.init(this,0xffffffff);
		initView();
        initData();
	}
	private void initData()
	{
//		BankListManager bl=new BankListManager(this, icb2);
//		bl.start();
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				getAllBank_data gd=(getAllBank_data) obj;
				ArrayList<getAllBank> list=gd.body.result;
				adapter=new SupportBanksAdapter(act, list);
				 listview_support_banks.setAdapter(adapter);
			}
		};
		getAllBankParam gab=new getAllBankParam();
		TongYongManager2 tym2=new TongYongManager2(this, icb,gab);
		tym2.start();
		
	}
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj)
		{   
			if(obj!=null)
			{
			  ArrayList<BankList> list=BankListDao.getBankList();
			//  adapter=new SupportBanksAdapter(BankSupportActivity.this,list);
			  listview_support_banks.setAdapter(adapter);
			}
			
		}
	};
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		//titleTv.setText(getString(R.string.supported_bank));
		titleTv.setText("支持银行及限额");
		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		listview_support_banks=(ListView)findViewById(R.id.listview_support_banks);
		//final ArrayList<BankInfo> list=BankInfoDao.getInstance(BankSupportActivity.this).getBankInfoList();
//		ArrayList<BankList> list=BankListDao.getBankList();
//		adapter=new SupportBanksAdapter(this,list);
//		listview_support_banks.setAdapter(adapter);

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
