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

import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.PurchaseInfo;
import com.beikbank.android.data.Remdom;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



//取现完成界面
public class RedeemFinishActivity extends BaseActivity implements OnClickListener{
	private Activity act=this;
	private TextView titleTv;
	private LinearLayout linear_left,linear_status;
	private TextView textview_status_ok,textview_status_fail,textview_money,textview_date,
	textview_purchase_bank,textview_ordernumber,textview_status_doing;
	//private PurchaseInfo purchaseInfo;
	Remdom rd;
	private String amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem_finish);
		StateBarColor.init(this,0xffffffff);
		initView();
	}

	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.transaction_detail));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		textview_status_ok=(TextView)findViewById(R.id.textview_status_ok);
		textview_status_fail=(TextView)findViewById(R.id.textview_status_fail);
		textview_status_doing=(TextView)findViewById(R.id.textview_status_doing);
		textview_money=(TextView)findViewById(R.id.textview_money);
		textview_date=(TextView)findViewById(R.id.textview_date);
		textview_purchase_bank=(TextView)findViewById(R.id.textview_purchase_bank);
		textview_ordernumber=(TextView)findViewById(R.id.textview_ordernumber);
		linear_status=(LinearLayout)findViewById(R.id.linear_status);

		rd=(Remdom)getIntent().getExtras().getSerializable(BeikBankConstant.INTENT_PURCHASESUCCESS);
		amount=getIntent().getStringExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT);
		if(rd.status.equals("0")){
			textview_status_ok.setVisibility(View.GONE);
			textview_status_fail.setVisibility(View.GONE);
			textview_status_doing.setVisibility(View.VISIBLE);
			textview_status_ok.setText("确认中");
			linear_status.setBackgroundResource(R.drawable.ic_purchase_doing);
		}else if(rd.status.equals("1")){
			textview_status_ok.setVisibility(View.GONE);
			textview_status_fail.setVisibility(View.GONE);
			textview_status_doing.setVisibility(View.VISIBLE);
			textview_status_ok.setText("确认中");			
			linear_status.setBackgroundResource(R.drawable.ic_purchase_doing);
		}else if(rd.status.equals("2")){
			textview_status_ok.setVisibility(View.VISIBLE);
			textview_status_fail.setVisibility(View.GONE);
			textview_status_doing.setVisibility(View.GONE);
			textview_status_ok.setText("成功");
			linear_status.setBackgroundResource(R.drawable.ic_purchase_ok);
		}else{
			textview_status_ok.setVisibility(View.GONE);
			textview_status_fail.setVisibility(View.VISIBLE);
			textview_status_doing.setVisibility(View.GONE);
			textview_status_fail.setText("失败");
			linear_status.setBackgroundResource(R.drawable.ic_purchase_fail);
		}
		textview_money.setText(amount+"元");
		textview_ordernumber.setText(rd.orderNumber);
		textview_date.setText(rd.dealTime);
		//CardInfo cardInfo=CardInfoDao.getInstance(act).getCardInfo();
		//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
		CardInfo cardInfo=CardInfoDao.getCardInfo();
		//BankInfo bankInfo=BankInfoDao.getInstance(act).getBankInfoByType(cardInfo.getType());
		if(cardInfo!=null&&cardInfo.type!=null)
		{
			
		
		  BankList bl=BankListDao.getBankByType(cardInfo.getType());
		  if(bl!=null)
		  {
		    String cardNumber=cardInfo.getCardNumber();
		    textview_purchase_bank.setText(bl.bankName
				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
		  }
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			backEvent();
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
		Intent intent3 = new Intent(act, HomeActivity3.class); 
		BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
				4);
		intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent3);
	}
}
