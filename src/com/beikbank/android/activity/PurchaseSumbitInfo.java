package com.beikbank.android.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.ConfirmPay;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.PlayFund;
import com.beikbank.android.data.PurchaseInfo;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 *购买提交页面
 **/
public class PurchaseSumbitInfo extends BaseActivity implements OnClickListener{
	private Activity act=this;
	private TextView titleTv,textview_redeem_time,tv3,tv4;
	private TextView tv_tv7;
	private TextView tv_tv6;
	private TextView tv_tv2;
	
	/**
	 * 
	 */
	private TextView tv1;
	private LinearLayout linear_left;
	private PlayFund pf;
	private String amount;
	private String name;
	ConfirmPay cp;
	public static final String index="index";
	public static final String index1="index1";
	public static final String index2="index2";
	private String tname="购买申请已提交";
	private String tname2="申请已提交";
	/**
	 * 稍后在交易记录查询
	 */
	private TextView tv8; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initIntent();

		setContentView(R.layout.activity_redeem_status);
		StateBarColor.init(this,0xffffffff);
        Intent intent=getIntent();
        FundInfo fi=(FundInfo) intent.getSerializableExtra(TypeUtil.huoqi_data);
		amount=getIntent().getStringExtra(index1);
		name=fi.name;
		
		initView();
		initOn();
		initData();
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_after_pay,true);
	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(tname);
        
		tv1=(TextView) findViewById(R.id.tv_tv1);
		
		tv1.setText(tname2);
		tv3 = (TextView) findViewById(R.id.tv_tv3);
		tv4= (TextView) findViewById(R.id.tv_tv4);
		tv_tv6=(TextView)findViewById(R.id.tv_tv6);
		tv_tv2=(TextView)findViewById(R.id.tv_tv2);
		tv_tv7=(TextView) findViewById(R.id.tv_tv7);
		tv8 = (TextView) findViewById(R.id.tv8);
		
		
		tv8.setVisibility(View.VISIBLE);
	
		if(tv_tv2!=null)
		{
			tv_tv2.setText("等待处理");
		}
		
		tv_tv6.setText(getString(R.string.page50));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.INVISIBLE);
		
		//linear_left.setVisibility(View.VISIBLE);
		//linear_left.setOnClickListener(this);	
		textview_redeem_time=(TextView)findViewById(R.id.textview_redeem_time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String t=sdf.format(new Date());
		textview_redeem_time.setText(t);

	}
    public void initOn()
    {
    	tv3.setOnClickListener(this);
    	tv4.setOnClickListener(this);
    }
    public void initData()
    {
//    	if(pf!=null)
//    	{
//    		String time=pf.preValueDate;
//    		
//    	}
        if(cp!=null&&tv_tv7!=null)
        {  
        	tv_tv7.setText(cp.dealTime);
        }
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			backEvent();
			
			break;
		case R.id.tv_tv3:
			Intent intent=getIntent();
			intent.setClass(act,TransactionDetailActivity.class);
			TransactionInfo transactionInfo=new TransactionInfo();
			transactionInfo.transactionAmount=cp.amount;
			transactionInfo.transactionStatus=cp.status;
			transactionInfo.planAmount=cp.planAmount;
			transactionInfo.purchaseDate=cp.dealTime;
			transactionInfo.productName=name;
			transactionInfo.orderNumber=cp.orderNumber;
			transactionInfo.tradeType=FinalIndex.jiaoyi_type1;
			String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
			
			transactionInfo.tradeMode=play;
			
			intent.putExtra(BeikBankConstant.INTENT_TRANSACTION, transactionInfo);
			intent.putExtra(BeikBankConstant.index7,"index7");
			startActivity(intent);
			finish();
			break;
		case R.id.tv_tv4:
			Intent intent3 = new Intent(act, HomeActivity3.class); 
			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
					4);
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			finish();
			break;
		}

	}
	public void initIntent()
	{   
		//pf=(PlayFund)getIntent().getExtras().getSerializable(BeikBankConstant.INTENT_PURCHASESUCCESS);
		amount=getIntent().getStringExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT);
		cp=(ConfirmPay) getIntent().getSerializableExtra(TypeUtil.jiaoyi_state);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK){
			backEvent();
			return true;
		}
		return false;
	}
	
	public void backEvent(){
		Intent intent3 = new Intent(act, HomeActivity3.class); 
		BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
				4);
		intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent3);
	}


}
