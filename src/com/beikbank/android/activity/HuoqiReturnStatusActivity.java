package com.beikbank.android.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.ConfirmPay;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.PurchaseInfo;
import com.beikbank.android.data.Remdom;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



//取现状态
public class HuoqiReturnStatusActivity extends BaseActivity implements OnClickListener{
	private Activity act=this;
	private TextView titleTv,textview_redeem_time,tv3,tv4;
	private LinearLayout linear_left;
   // FundInfo fi;
  //  String amount;
    //Remdom rd;
   // ConfirmPay cp;
    /**
     * 申请已提交
     */
    TextView tv1;
    /**
     * 提交后回款
     */
    TextView tv6;
    /**
     * 
     */
    TextView tv7;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem_status);
		StateBarColor.init(this,0xffffffff);
       // fi=(FundInfo) getIntent().getSerializableExtra(RedeemActivity.index);
       // amount=getIntent().getStringExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT);
       // cp=(ConfirmPay) getIntent().getSerializableExtra(BeikBankConstant.INTENT_PURCHASESUCCESS);
		initView();
		initOn();
	}

	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("赎回申请已提交");
		tv1 = (TextView) findViewById(R.id.tv_tv1);
		tv1.setText("申请已提交");
		tv3 = (TextView) findViewById(R.id.tv_tv3);
		tv4= (TextView) findViewById(R.id.tv_tv4);
		tv6= (TextView) findViewById(R.id.tv_tv6);
		tv7= (TextView) findViewById(R.id.tv_tv7);
		tv6.setText("确认后，回款至钱包");
		tv7.setVisibility(View.GONE);
		
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
//			TransactionInfo transactionInfo=new TransactionInfo();
//			transactionInfo.transactionAmount=amount;
//			transactionInfo.transactionStatus=cp.status;
//			transactionInfo.purchaseDate=cp.dealTime;
//			transactionInfo.productName=fi.name;
//			transactionInfo.orderNumber=cp.orderNumber;
//			transactionInfo.tradeType=FinalIndex.jiaoyi_type2;
//			intent.putExtra(BeikBankConstant.INTENT_TRANSACTION, transactionInfo);
			startActivity(intent);
			finish();
			break;
		case R.id.tv_tv4:
			Intent intent3 = new Intent(act, HomeActivity3.class); 
			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
					4);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			break;
		}

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
		Intent intent3 = new Intent(HuoqiReturnStatusActivity.this, HomeActivity3.class); 
		BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
				4);
		intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent3);
	}


}
