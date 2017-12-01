package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.Spannable;
//import android.text.SpannableString;
//import android.text.TextWatcher;
//import android.text.style.ForegroundColorSpan;
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
//import com.beikbank.android.data.RegisterCode;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.utils.AdvancedCountdownTimer;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.SmsContent;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.widget.ClearableEditText;
//import com.google.gson.Gson;
//import com.nineoldandroids.animation.AnimatorSet;
//
////有银行预留手机号码，验证手机
//public class BankConfirmActivity extends BaseActivity1 implements OnClickListener{
//
//	private final String TAG="BankConfirmActivity";
//	private ClearableEditText clearedittext_identify_code,clearedittext_phonenumber;
//	private Button button_next;
//	private TextView titleTv,textview_identifying_code_timer,textview_toast,
//	textview_bind_bank,textview_get_identifying_code;
//	private LinearLayout linear_reload_identify_code,linear_toast,linear_left;
//	private MyCount timer;
//	private final int TOTALTIME=60*1000;//定时60秒
//	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
//
//	private String vertifyid;
//	private Dialog dialog;
//	private AnimatorSet toastAnimSet;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_bank_confirm);
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
//		clearedittext_identify_code=(ClearableEditText)findViewById(R.id.clearedittext_identify_code);
//		clearedittext_identify_code.addTextChangedListener(new TextWatcherListener());
//		clearedittext_phonenumber=(ClearableEditText)findViewById(R.id.clearedittext_phonenumber);
//		clearedittext_phonenumber.addTextChangedListener(new TextWatcherListener());
//		button_next=(Button)findViewById(R.id.button_next);
//		button_next.setOnClickListener(this);
//
//		textview_get_identifying_code=(TextView)findViewById(R.id.textview_get_identifying_code);
//		textview_get_identifying_code.setOnClickListener(this);
//		textview_identifying_code_timer=(TextView)findViewById(R.id.textview_identifying_code_timer);
//		linear_reload_identify_code=(LinearLayout)findViewById(R.id.linear_reload_identify_code);
//		textview_bind_bank=(TextView)findViewById(R.id.textview_bind_bank);
//
//		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
//		textview_toast=(TextView)findViewById(R.id.textview_toast);
//
//		//CardInfo cardInfo=CardInfoDao.getInstance(BankConfirmActivity.this).getCardInfo();
//		CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
//		//BankInfo bankInfo=BankInfoDao.getInstance(BankConfirmActivity.this).getBankInfoByType(cardInfo.getType());
//		BankList bl=BankListDao.getBankByType(cardInfo.getType());
//		String cardNumber=cardInfo.getCardNumber();
//		textview_bind_bank.setText(bl.bankName
//				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//
//		SmsContent content = new SmsContent(BankConfirmActivity.this, new Handler(), clearedittext_identify_code);
//		this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
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
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before,
//				int count) {
//			// TODO Auto-generated method stub
//			if(clearedittext_identify_code.getText().toString().length()>0
//					&&clearedittext_phonenumber.getText().toString().length()==11){
//				button_next.setEnabled(true);
//			}else{
//				button_next.setEnabled(false);
//			}
//		}
//    、、
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.button_next:
//			
//			String vertifycode=clearedittext_identify_code.getText().toString();
//			String phonenumber=clearedittext_phonenumber.getText().toString();
//			if(!Utils.isPhoneNumberValid(phonenumber)){
//				textview_toast.setText("手机号码格式错误！");
//				Utils.performAnimateForToast(linear_toast, toastAnimSet);
//			}else{
//				//UserInfo userInfo=UserInfoDao.getInstance(BankConfirmActivity.this).getUserInfo();
//				UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//				BeikBankApi.getInstance().getPhoneVertifyInfo(BankConfirmActivity.this,
//						userInfo.getId(),vertifycode,vertifyid,phonenumber,checkVertifyCodeInfoHandler);
//			}
//			
//			break;
//		case R.id.textview_get_identifying_code:
//			//UserInfo userInfo2=UserInfoDao.getInstance(BankConfirmActivity.this).getUserInfo();
//			UserInfo userInfo2=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//			String phonenumber2=clearedittext_phonenumber.getText().toString();
//			BeikBankApi.getInstance().checkBankCardPhoneNumberInfo(BankConfirmActivity.this,
//					userInfo2.getId(),phonenumber2, checkBankCardPhoneNumberInfoHandler);
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
//		timer=null;
//	}
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(this, pActClassName);
//		startActivity(_Intent);
//	}
//
//	//实现计时功能的类  
//	class MyCount extends AdvancedCountdownTimer {  
//
//		public MyCount(long millisInFuture, long countDownInterval) {  
//			super(millisInFuture, countDownInterval);  
//			textview_get_identifying_code.setVisibility(View.GONE);
//			linear_reload_identify_code.setVisibility(View.VISIBLE);
//			ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
//			SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME/COUNTDOWNINTERVAL));
//			ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
//			textview_identifying_code_timer.setText(ssb);
//			textview_identifying_code_timer.append(getString(R.string.identify_code_reload));
//		}  
//
//		@Override  
//		public void onFinish() {              
//			linear_reload_identify_code.setVisibility(View.GONE);
//			textview_get_identifying_code.setVisibility(View.VISIBLE);
//			textview_get_identifying_code.setText(getString(R.string.identify_code_reloading));
//			timer=null;
//		}     
//		//更新剩余时间  
//		@Override  
//		public void onTick(long millisUntilFinished, int percent) { 
//			long time = (millisUntilFinished / 1000);  
//			ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
//			SpannableString ssb = new SpannableString(String.valueOf(time));
//			ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
//			textview_identifying_code_timer.setText(ssb);
//			textview_identifying_code_timer.append(getString(R.string.identify_code_reload));
//
//		}  
//
//	}  
//
//	JsonHttpResponseHandler getIdentifyCodeInfoHandler = new JsonHttpResponseHandler(){
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
//			Gson gson=new Gson();
//			RegisterCode registerCode=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//					BeikBankConstant.TYPE_JSONDATA), RegisterCode.class);
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				vertifyid=registerCode.getVerificodeId();
//			}
//
//		}
//
//	};
//
//
//	JsonHttpResponseHandler checkVertifyCodeInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialogNoCancel(BankConfirmActivity.this, null);
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
//				UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//				userInfo.setHasUpgrade(true);
//                TableDaoSimple.delete(UserInfo.class,null,null);
//				//UserInfoDao.getInstance(BankConfirmActivity.this).deleteAll();
//				//UserInfoDao.getInstance(BankConfirmActivity.this).insertUserInfo(userInfo);
//				
//				if(userInfo.isHasSetPaypassword()){
//					Intent intent = new Intent(BankConfirmActivity.this, PurchaseActivity.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//				}else{
//					Intent intent=new Intent(BankConfirmActivity.this,TransactionPwdSetActivity.class);
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
//	JsonHttpResponseHandler checkBankCardPhoneNumberInfoHandler = new JsonHttpResponseHandler(){
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
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				String phonenumber=clearedittext_phonenumber.getText().toString();
//				BeikBankApi.getInstance().getIdentifyCodeInfo(BankConfirmActivity.this,phonenumber,"4",
//						getIdentifyCodeInfoHandler);
//				timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
//				timer.start();
//			}else{
//				
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
//
//}
