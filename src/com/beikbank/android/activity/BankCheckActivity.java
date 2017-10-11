package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.beikbank.android.R;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.dao.BankListDao;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.data.BankInfo;
//import com.beikbank.android.data.BankList;
//import com.beikbank.android.data.CardInfo;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.widget.ClearableEditText;
//import com.nineoldandroids.animation.AnimatorSet;
//
////没有银行预留手机号码，小额打款
//public class BankCheckActivity extends BaseActivity1 implements OnClickListener{
//
//	private final String TAG="BankCheckActivity";
//	private ClearableEditText clearedittext_remittance;
//	private Button button_remittance;
//	private TextView titleTv,textview_toast,textview_remittance;
//	private LinearLayout linear_toast,linear_left;
//	private AnimatorSet toastAnimSet;
//	private Dialog dialog;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_bank_check);
//		initView();
//
//	}
//	public void initView(){
//		toastAnimSet = new AnimatorSet();
//		titleTv = (TextView) findViewById(R.id.titleTv);
//		titleTv.setText(getString(R.string.confirm_info));
//
//		linear_left = (LinearLayout) findViewById(R.id.linear_left);
//		linear_left.setVisibility(View.VISIBLE);
//		linear_left.setOnClickListener(this);
//
//		clearedittext_remittance=(ClearableEditText)findViewById(R.id.clearedittext_remittance);
//		clearedittext_remittance.addTextChangedListener(new TextWatcherListener());
//		button_remittance=(Button)findViewById(R.id.button_remittance);
//		button_remittance.setOnClickListener(this);
//
//		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
//		textview_toast=(TextView)findViewById(R.id.textview_toast);
//		textview_remittance=(TextView)findViewById(R.id.textview_remittance);
//
//		//CardInfo cardInfo=CardInfoDao.getInstance(BankCheckActivity.this).getCardInfo();
//		CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
//		//BankInfo bankInfo=BankInfoDao.getInstance(BankCheckActivity.this).getBankInfoByType(cardInfo.getType());
//		BankList bl=BankListDao.getBankByType(cardInfo.getType());
//		
//		String cardNumber=cardInfo.getCardNumber();
//		String remittanceTextFormat = getResources().getString(R.string.remittance_text1);
//		String remittanceText = String.format(remittanceTextFormat, bl.bankName
//				+"尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())); 
//		textview_remittance.setText(remittanceText);
//
//		//UserInfo userInfo=UserInfoDao.getInstance(BankCheckActivity.this).getUserInfo();
//		UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//		BeikBankApi.getInstance().smallRemittanceInfo(BankCheckActivity.this,userInfo.getId(),
//				smallRemittanceInfoHandler);	//小额打款到用户银行卡
//
//	}
//
//	class TextWatcherListener implements TextWatcher{
//		@Override
//		public void afterTextChanged(Editable s) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void beforeTextChanged(CharSequence s, int start, int count,
//				int after) {
//		}
//
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before,
//				int count) {
//			if(clearedittext_remittance.getText().toString().length()>0){
//				button_remittance.setEnabled(true);
//			}else{
//				button_remittance.setEnabled(false);
//			}
//		}
//
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.button_remittance:
//			//UserInfo userInfo=UserInfoDao.getInstance(BankCheckActivity.this).getUserInfo();
//			UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//			String amount=clearedittext_remittance.getText().toString();
//			BeikBankApi.getInstance().smallRemittanceLimitInfo(BankCheckActivity.this,userInfo.getId(),amount,
//					smallRemittanceLimitInfoHandler);	
//			break;
//		case R.id.linear_left:
//			finish();
//			break;
//		}
//	}
//
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//	}
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(this, pActClassName);
//		startActivity(_Intent);
//	}
//
//	JsonHttpResponseHandler smallRemittanceInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);			
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//
//		}
//
//	};
//
//	JsonHttpResponseHandler smallRemittanceLimitInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialogNoCancel(BankCheckActivity.this, null);
//			dialog.show();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//			dialog.dismiss();
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);			
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				
//				//UserInfo userInfo=UserInfoDao.getInstance(BankCheckActivity.this).getUserInfo();
//				UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//				userInfo.setHasUpgrade(true);
//				TableDaoSimple.delete(UserInfo.class,null,null);
//				TableDaoSimple.insert(userInfo);
//				//UserInfoDao.getInstance(BankCheckActivity.this).deleteAll();
//				//UserInfoDao.getInstance(BankCheckActivity.this).insertUserInfo(userInfo);
//
//				if(userInfo.isHasSetPaypassword()){
//					Intent intent = new Intent(BankCheckActivity.this, PurchaseActivity.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//				}else{
//					Intent intent=new Intent(BankCheckActivity.this,TransactionPwdSetActivity.class);
//					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
//					startActivity(intent);
//				}
//			}else{
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast, toastAnimSet);
//			}
//
//		}
//
//	};
//
//
//
//}
