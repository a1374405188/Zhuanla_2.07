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
//import android.view.View.OnFocusChangeListener;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.beikbank.android.R;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.data.RegisterCode;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.utils.AdvancedCountdownTimer;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.IdcardValidator;
//import com.beikbank.android.utils.SmsContent;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.widget.ClearableEditText;
//import com.google.gson.Gson;
//import com.nineoldandroids.animation.AnimatorSet;
//
//public class ForgetPwdRealnameActivity23 extends BaseActivity1 implements OnClickListener,OnFocusChangeListener{
//
//	private final String TAG="ForgetPwdRealnameActivity";
//	private ClearableEditText clearedittext_realname,clearedittext_icnumber,
//	clearedittext_phonenumber,clearedittext_identifying_code;
//	private Button button_next;
//	private TextView titleTv,textview_identifying_code_timer,textview_toast,
//	textview_icnumber_enlarge,textview_get_identifying_code;
//	private LinearLayout linear_reload_identify_code,linear_toast;
//	private MyCount timer;
//	private final int TOTALTIME=60*1000;//定时60秒
//	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
//
//	private AnimatorSet toastAnimSet;
//	private LinearLayout linear_left,linear_bottom,linear_icnumber_enlarge;
//	private String vertifyid;
//	private Dialog dialog;
//	private String phonenumber;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_forgetpwd_realname);
//		initView();
//
//	}
//	public void initView(){
//		toastAnimSet = new AnimatorSet();
//		titleTv = (TextView) findViewById(R.id.titleTv);
//		titleTv.setText(getString(R.string.retrieve_password));
//
//		linear_left = (LinearLayout) findViewById(R.id.linear_left);
//		linear_left.setVisibility(View.VISIBLE);
//		linear_left.setOnClickListener(this);
//
//		clearedittext_realname=(ClearableEditText)findViewById(R.id.clearedittext_realname);
//		clearedittext_realname.addTextChangedListener(new TextWatcherListener());
//		clearedittext_icnumber=(ClearableEditText)findViewById(R.id.clearedittext_icnumber);
//		clearedittext_icnumber.addTextChangedListener(new IcnumberTextWatcher());
//		clearedittext_phonenumber=(ClearableEditText)findViewById(R.id.clearedittext_phonenumber);
//		clearedittext_phonenumber.addTextChangedListener(new TextWatcherListener());
//		clearedittext_identifying_code=(ClearableEditText)findViewById(R.id.clearedittext_identifying_code);
//		clearedittext_identifying_code.addTextChangedListener(new TextWatcherListener());
//		button_next=(Button)findViewById(R.id.button_next);
//		button_next.setOnClickListener(this);
//
//		textview_get_identifying_code=(TextView)findViewById(R.id.textview_get_identifying_code);
//		textview_get_identifying_code.setOnClickListener(this);
//		textview_identifying_code_timer=(TextView)findViewById(R.id.textview_identifying_code_timer);
//		linear_reload_identify_code=(LinearLayout)findViewById(R.id.linear_reload_identify_code);
//		linear_bottom=(LinearLayout)findViewById(R.id.linear_bottom);
//
//		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
//		textview_toast=(TextView)findViewById(R.id.textview_toast);
//		textview_icnumber_enlarge=(TextView)findViewById(R.id.textview_icnumber_enlarge);
//		linear_icnumber_enlarge=(LinearLayout)findViewById(R.id.linear_icnumber_enlarge);
//
//		clearedittext_realname.setOnFocusChangeListener(this);
//		clearedittext_icnumber.setOnFocusChangeListener(this);
//		clearedittext_phonenumber.setOnFocusChangeListener(this);
//		clearedittext_identifying_code.setOnFocusChangeListener(this);
//
//		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
//		clearedittext_phonenumber.setText(Utils.getEncryptedPhonenumber(phonenumber));
//		clearedittext_phonenumber.setEnabled(false);
//		clearedittext_phonenumber.removeClearButton();
//
//		SmsContent content = new SmsContent(ForgetPwdRealnameActivity2.this, new Handler(), clearedittext_identifying_code);
//		this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
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
//			String realname=clearedittext_realname.getText().toString();
//			String icnumber=clearedittext_icnumber.getText().toString();
//			String code=clearedittext_identifying_code.getText().toString();
//			if(realname.length()>0&&(icnumber.length()==18)&&(code.length()>0)){
//				button_next.setEnabled(true);
//			}else{
//				button_next.setEnabled(false);
//			}
//		}
//
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.button_next:
//			IdcardValidator iv = new IdcardValidator();
//			String verificode=clearedittext_identifying_code.getText().toString();
//			String realname=clearedittext_realname.getText().toString();
//			String icnumber=clearedittext_icnumber.getText().toString();
//			if(!Utils.isRealname(realname)){
//				textview_toast.setText("姓名格式不正确！");
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//			}else if(!iv.isValidatedAllIdcard(icnumber)){
//				textview_toast.setText("身份证格式不正确！");
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//			}else{
//				BeikBankApi.getInstance().checkCodeInfo(ForgetPwdRealnameActivity2.this,phonenumber,realname,icnumber,
//						verificode,vertifyid, checkIdentifyCodeInfoHandler);
//			}
//			break;
//		case R.id.textview_get_identifying_code:
//			clearedittext_identifying_code.requestFocus();
//			BeikBankApi.getInstance().getIdentifyCodeInfo(ForgetPwdRealnameActivity2.this,phonenumber,"3",
//					getIdentifyCodeInfoHandler);
//			timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
//			timer.start();
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
//			linear_reload_identify_code.setVisibility(View.VISIBLE);
//			textview_get_identifying_code.setVisibility(View.GONE);
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
//
//	class IcnumberTextWatcher implements TextWatcher{
//		int beforeTextLength=0;
//		int onTextLength=0;
//		boolean isChanged=false;
//
//		int location=0;
//		private char[] tempChar;
//		private StringBuffer buffer=new StringBuffer();
//		int blankspaceNumberB=0;
//
//		@Override
//		public void afterTextChanged(Editable editable) {
//			// TODO Auto-generated method stub
//			String str="";
//			if(isChanged){
//				location=clearedittext_icnumber.getSelectionEnd();
//				int index=0;
//				while(index<buffer.length()){
//					if(buffer.charAt(index)==' '){
//						buffer.deleteCharAt(index);
//					}else{
//						index++;
//					}
//				}
//				index =0;
//				int blankspaceNumberC=0;
//				while(index<buffer.length()){
//					if((index==6||index==15)){
//						buffer.insert(index, ' ');
//						blankspaceNumberC++;
//					}
//					index++;
//				}
//				if(blankspaceNumberC>blankspaceNumberB){
//					location+=(blankspaceNumberC-blankspaceNumberB);
//				}
//
//				tempChar=new char[buffer.length()];
//				buffer.getChars(0, buffer.length(), tempChar, 0);
//				str=buffer.toString();
//				if(location>str.length()){
//					location=str.length();
//				}else if(location<0){
//					location=0;
//				}
//
//				textview_icnumber_enlarge.setText(str);
//				isChanged=false;
//
//			}
//			if(beforeTextLength==0){
//				textview_icnumber_enlarge.setText(editable.toString());
//				Utils.performAnimateForDown(linear_bottom,linear_icnumber_enlarge);
//			}else if(beforeTextLength>0&&editable.toString().length()==0){
//				textview_icnumber_enlarge.setText("");
//				Utils.performAnimateForUp(linear_bottom);
//			}
//
//			String realname=clearedittext_realname.getText().toString();
//			String phone=clearedittext_phonenumber.getText().toString();
//			String code=clearedittext_identifying_code.getText().toString();
//			if(realname.length()>0&&(editable.toString().length()==18)&&(phone.length()==11)&&(code.length()>0)){
//				button_next.setEnabled(true);
//			}else{
//				button_next.setEnabled(false);
//			}
//
//		}
//		@Override
//		public void beforeTextChanged(CharSequence text, int start, int count,
//				int after) {
//			// TODO Auto-generated method stub
//			beforeTextLength=text.length();
//			if(buffer.length()>0){
//				buffer.delete(0, buffer.length());
//			}
//			blankspaceNumberB=0;
//			for(int i=0;i<text.length();i++){
//				if(text.charAt(i)==' '){
//					blankspaceNumberB++;
//				}
//			}
//
//		}
//
//		@Override
//		public void onTextChanged(CharSequence text, int start, int before,
//				int count) {
//			// TODO Auto-generated method stub
//			onTextLength=text.length();
//			buffer.append(text.toString());
//			if(onTextLength==beforeTextLength||onTextLength<1||
//					isChanged){
//				isChanged=false;
//				return;
//			}
//			isChanged=true;
//		}
//	}
//
//	@Override
//	public void onFocusChange(View v, boolean hasFocus) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.clearedittext_icnumber:
//			if(hasFocus){
//				if(clearedittext_icnumber.getText().toString().length()>0){
//					Utils.performAnimateForDown(linear_bottom,linear_icnumber_enlarge);
//				}
//			}
//			break;
//		case R.id.clearedittext_realname:
//			if(hasFocus){
//				if(clearedittext_icnumber.getText().toString().length()>0){
//					Utils.performAnimateForUp(linear_bottom);
//				}
//			}
//			break;
//		case R.id.clearedittext_identifying_code:
//			if(hasFocus){
//				if(clearedittext_icnumber.getText().toString().length()>0){
//					Utils.performAnimateForUp(linear_bottom);
//				}
//			}
//			break;
//		case R.id.clearedittext_phonenumber:
//			if(hasFocus){
//				if(clearedittext_icnumber.getText().toString().length()>0){
//					Utils.performAnimateForUp(linear_bottom);
//				}
//			}
//			break;
//
//
//		}
//
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
//	JsonHttpResponseHandler checkIdentifyCodeInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialog(ForgetPwdRealnameActivity2.this, null);
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
//				startAimActivity(TransactionPwdSetActivity2.class);
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
//}
