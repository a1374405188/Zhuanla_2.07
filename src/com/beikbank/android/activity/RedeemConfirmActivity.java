package com.beikbank.android.activity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.ConfirmPay_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.PurchaseInfo;
import com.beikbank.android.data.Remdom;
import com.beikbank.android.data.Remdom_data;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.ConfirmPayforPParam;
import com.beikbank.android.dataparam.ReqPayforPParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfirmPayforPManager;
import com.beikbank.android.net.impl.ConfirmPayforPManager2;
import com.beikbank.android.net.impl.RemdomManager;
import com.beikbank.android.net.impl.ReqPayforPManager;
import com.beikbank.android.net.impl.ReqPayforPManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast2;
import com.google.gson.Gson;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



//取现确认
public class RedeemConfirmActivity extends BaseActivity1 implements OnClickListener{
	private Activity act=this;
	private final String TAG="RedeemConfirmActivity";
	private TextView titleTv,textview_money,textview_credit_card,
	textview_forget_transaction_password,textview_toast;
	private Button button_confirm_redeem;
	private LinearLayout linear_left,linear_toast;
	private AnimatorSet toastAnimSet;
	private ClearableEditText clearedittext_transaction_password;	
	private String sid;
	private String amount;
	private Dialog dialog;
	//private PurchaseInfo purchaseInfo;
	Remdom_data rd;
	//取现金额，手续费，到帐金额
	TextView tv_tv1,tv_tv2,tv_tv3;
	boolean isOnlick=false;
	public static String index="index";
	FundInfo fi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fi=(FundInfo) getIntent().getSerializableExtra(index);
		setContentView(R.layout.activity_redeem_confirm);
		StateBarColor.init(this,0xffffffff);
		initView();
		ReqPayforPParam rp=new ReqPayforPParam();
		rp.memberID=BeikBankApplication.getUserid();
		rp.productId=fi.sid;
		rp.productName=fi.name;
		rp.productType=FinalIndex.cp_type2;
		rp.amount=tv1;
		ReqPayforPManager2 rpp=new ReqPayforPManager2(this, icb1, rp);
		rpp.start();
	}
	ReqPayforP rp;
    ICallBack icb1=new ICallBack() {
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				
				ReqPayforP_Data rd=(ReqPayforP_Data) obj;
				rp=rd.data;
			}
			
		}
	};
	//取现金额
	String tv1;
	//手续费
	String tv2;
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		sid=getIntent().getExtras().getString(BeikBankConstant.INTENT_SID);
		amount=getIntent().getStringExtra(BeikBankConstant.INTENT_AMOUNT);
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.redeem_confirm2));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		
		clearedittext_transaction_password=(ClearableEditText)findViewById(R.id.clearedittext_transaction_password);
		clearedittext_transaction_password.addTextChangedListener(new TextWatcherListener());
		button_confirm_redeem=(Button) findViewById(R.id.button_next);
		button_confirm_redeem.setOnClickListener(this);
		//textview_money=(TextView)findViewById(R.id.textview_money);
		
		tv_tv1=(TextView) findViewById(R.id.tv_tv1);
		tv_tv2=(TextView) findViewById(R.id.tv_tv2);
		tv_tv3=(TextView) findViewById(R.id.tv_tv3);
		
		DecimalFormat df=new DecimalFormat("0.00");
		double dou=Double.parseDouble(amount);
        tv1=getIntent().getStringExtra("tv1");
        tv2=getIntent().getStringExtra("tv2");
		tv_tv1.setText(tv1+"元");
		if("".equals(tv2)||"0".equals(tv2))
		{
			tv_tv2.setText("免手续费");
		}
		else
		{
			tv_tv2.setText(tv2+"元");
		}
		tv_tv3.setText(df.format(dou)+"元");
		//textview_money.setText(df.format(dou)+"元");
		//textview_credit_card=(TextView)findViewById(R.id.textview_credit_card);
		//CardInfo cardInfo=CardInfoDao.getInstance(RedeemConfirmActivity.this).getCardInfo();
		//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
		
		//BankInfo bankInfo=BankInfoDao.getInstance(RedeemConfirmActivity.this).getBankInfoByType(cardInfo.getType());
		//BankList bl=BankListDao.getBankByType(cardInfo.getType());
		//String cardNumber=cardInfo.getCardNumber();
		//textview_credit_card.setText(bl.bankName
		//		+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
		
		textview_forget_transaction_password=(TextView)findViewById(R.id.textview_forget_transaction_password);
		textview_forget_transaction_password.setOnClickListener(this);
		
		
	 	
	}
   
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:
			
	
				
				//UserInfo userInfo=UserInfoDao.getInstance(RedeemConfirmActivity.this).getUserInfo();
				
			   UserInfo userInfo=BeikBankApplication.getUserInfo();
				CardInfo cardinfo=null;
				if(userInfo!=null)
				{
					//cardinfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
					cardinfo=CardInfoDao.getCardInfo();
					String transactionPassword=clearedittext_transaction_password.getText().toString();
					String fund_sid=BeikBankApplication.getInstance().getSharedPref().getSharePrefString(BeikBankConstant.FUNDINFO_SID);
					 //new RemdomManager(act,icb).start(userInfo.getId(),tv1,fund_sid,cardinfo.sid,"1",transactionPassword);
					 
					   if(rp!=null)
					   {
					    ConfirmPayforPParam cpp=new ConfirmPayforPParam();
						cpp.memberID=BeikBankApplication.getUserid();
						cpp.tradeNo=rp.tradeNo;
						cpp.tradePassword=transactionPassword;
						ConfirmPayforPManager2 cpm=new ConfirmPayforPManager2(this, icb2, cpp);
						cpm.start();
					   }
				}
				//CardInfo cardinfo=CardInfoDao.getInstance(this).getCardInfo();
			
//				String transactionPassword=clearedittext_transaction_password.getText().toString();
//				String fund_sid=BeikBankApplication.getInstance().getSharedPref().getSharePrefString(BeikBankConstant.FUNDINFO_SID);
////				BeikBankApi.getInstance().handleReturnInfo(RedeemConfirmActivity.this,
////						userInfo.getId(),amount,fund_sid,cardinfo.sid,"1",transactionPassword, getReturnMoneyInfoHandler);
//				    new RemdomManager(act,icb).start(userInfo.getId(),amount,fund_sid,cardinfo.sid,"1",transactionPassword);
				
		
			break;
		case R.id.textview_forget_transaction_password:
			clearedittext_transaction_password.setText("");
			String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
			Intent intent=new Intent(RedeemConfirmActivity.this,ForgetPwdRealnameActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);   
			break;
		}
		
	}
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		    if(obj!=null)
		    {   
		    	ConfirmPay_data cd=(ConfirmPay_data) obj;
		    	Intent intent=new Intent(act,RedeemStatusActivity.class);
				intent.putExtra(BeikBankConstant.INTENT_PURCHASESUCCESS,cd.data);
				intent.putExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT, amount);
				intent.putExtra(index,fi);
				startActivity(intent);
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
		    }
		}
	};
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			//purchaseInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
			//		BeikBankConstant.TYPE_JSONDATA), PurchaseInfo.class);
			if(obj!=null&&obj instanceof Remdom_data)
			{
				rd=(Remdom_data) obj;
				Remdom r=rd.data;
				Intent intent=new Intent(act,RedeemStatusActivity.class);
				intent.putExtra(BeikBankConstant.INTENT_PURCHASESUCCESS, r);
				intent.putExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT, amount);
				intent.putExtra(index,fi);
				startActivity(intent);
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
			}
	
	
		}
	};
	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}
	
	class TextWatcherListener implements TextWatcher{
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			String password=clearedittext_transaction_password.getText().toString();
			if(password.length()>=6){
				button_confirm_redeem.setEnabled(true);
			}else{
				button_confirm_redeem.setEnabled(false);
			}
		}

	}
	
//	JsonHttpResponseHandler getReturnMoneyInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialogNoCancel(RedeemConfirmActivity.this, null);
//			dialog.show();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//			dialog.dismiss();
//			isOnlick=false;
//			
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);
//			isOnlick=false;
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			Gson gson=new Gson();
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				purchaseInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//						BeikBankConstant.TYPE_JSONDATA), PurchaseInfo.class);
//				Intent intent=new Intent(act,RedeemStatusActivity.class);
//				intent.putExtra(BeikBankConstant.INTENT_PURCHASESUCCESS, purchaseInfo);
//				intent.putExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT, amount);
//				startActivity(intent);				
//			}else{
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				
//			}
//
//		}
//
//	};
//	

}
