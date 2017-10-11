package com.beikbank.android.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.ConfirmPay;
import com.beikbank.android.data.DingqiP;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.PlayFund;
import com.beikbank.android.data.PurchaseInfo;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 *购买提交页面
 **/
public class DingqiGouMaiState extends BaseActivity implements OnClickListener{
	private Activity act=this;
	private TextView titleTv,textview_redeem_time,tv3,tv4,tv5;

	/**
	 * 
	 */
	private TextView tv1;
	private LinearLayout linear_left;
	private PlayFund pf;
	private String amount;
	
	/**
	 * 支付排队中
	 */
	TextView tv_tv2;
	/**
	 * 交易提示信息1
	 */
	TextView tv_tv6;
	/**
	 * 交易提示信息2
	 */
	TextView tv_tv7;
	/**
	 * 稍后在交易记录查询
	 */
	private TextView tv8; 
	ConfirmPay cp;
	String name;
	DingqiP2 dp;
	public static String index="index";
	public static String index1="index1";
	public static String index2="index2";
	public static String index3="index3";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		cp=(ConfirmPay) getIntent().getSerializableExtra(TypeUtil.jiaoyi_state);
		//name=getIntent().getStringExtra(index1);
		dp=(DingqiP2) getIntent().getSerializableExtra(TypeUtil.dingdi_data);
		name=dp.categoryName;
		setContentView(R.layout.activity_redeem_status);
		StateBarColor.init(this,0xffffffff);
		initIntent();
		initView();
		initOn();
		initData();
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_after_pay,true);
	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("购买申请已提交");
        
		tv1=(TextView) findViewById(R.id.tv_tv1);
		
		ImageView iv=(ImageView) findViewById(R.id.iv);
		iv.setBackgroundResource(R.drawable.ic_receive_redeem2);
		
		tv1.setText("申请已提交");
		tv3 = (TextView) findViewById(R.id.tv_tv3);
		tv4= (TextView) findViewById(R.id.tv_tv4);
		tv5=(TextView)findViewById(R.id.textview_receive_redeem_time);
		
		tv_tv2=(TextView) findViewById(R.id.tv_tv2);
		tv_tv6=(TextView) findViewById(R.id.tv_tv6);
		tv_tv7=(TextView) findViewById(R.id.tv_tv7);
		
		tv_tv6.setText("预计收益时间");
		tv_tv7.setText("满标次日");
		
		if(tv_tv2!=null)
		{
			tv_tv2.setText("支付排队中");
		}
       tv8 = (TextView) findViewById(R.id.tv8);
		
		
		tv8.setVisibility(View.VISIBLE);
		
		
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
    	if(pf!=null)
    	{
    		String time=pf.preValueDate;
    		tv5.setText(time);
    	}
    	//tv6.setText(getString(R.string.purchase_info_text2));
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			backEvent();
			
			break;
		case R.id.tv_tv3:
			String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
			Intent intent=getIntent();
		
			intent.setClass(act,TransactionDQDetailActivity.class);
		    TransactionInfo ti=new TransactionInfo();
		    ti.orderNumber=cp.orderNumber;
		    ti.productName=dp.termbondName;
		    ti.productType=FinalIndex.cp_type1;
		    ti.tradeType=FinalIndex.jiaoyi_type1;
		    //是否是是活期买定期
			boolean ishuoqito=intent.getBooleanExtra(index3, false);
		    if(ishuoqito)
		    {
		    	 ti.tradeType="4";
		    	 ti.transactionAmount=amount;
		    }
		    else
		    {
		    	 ti.transactionAmount=cp.amount;
		    	 ti.planAmount=cp.planAmount;
		    }
		    ti.purchaseDate=cp.dealTime;
		    ti.transactionStatus=cp.status;
		   
		    ti.tradeMode=play;
		    ti.termbondPeriod=dp.termbondPeriod;
		    String year=NumberManager.getAddString(dp.yearRate,dp.extraRate,4);
		    ti.yearRate=year;
		    intent.putExtra(BeikBankConstant.INTENT_TRANSACTION,ti);
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
		amount=getIntent().getStringExtra(TypeUtil.jiaoyi_money);
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
