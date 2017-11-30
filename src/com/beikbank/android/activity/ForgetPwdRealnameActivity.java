package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.authorize.a;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.data.RegisterCode;
import com.beikbank.android.data.SendCode_data;
import com.beikbank.android.data2.UserCheck3_data;
import com.beikbank.android.data2.checkYanZhenMa_data;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.UserCheckParam3;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.dataparam2.getYanZhenMaParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckCodeManager2;
import com.beikbank.android.net.impl.SendCodeManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.IdcardValidator;
import com.beikbank.android.utils.SmsContent;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.google.gson.Gson;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



//已经实名认证过的用户，忘记密码
public class ForgetPwdRealnameActivity extends BaseActivity1 implements OnClickListener,OnFocusChangeListener{

	private final String TAG="ForgetPwdRealnameActivity";
	private ClearableEditText clearedittext_realname,clearedittext_icnumber,
	clearedittext_phonenumber,clearedittext_identifying_code;
	private Button button_next;
	private TextView titleTv,textview_identifying_code_timer,textview_toast,
	textview_icnumber_enlarge,textview_get_identifying_code;
	private LinearLayout linear_reload_identify_code,linear_toast;
	private MyCount timer;
	private final int TOTALTIME=60*1000;//定时60秒
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒

	private AnimatorSet toastAnimSet;
	private LinearLayout linear_left,linear_bottom,linear_icnumber_enlarge;
	private String vertifyid;
	private Dialog dialog;
	private boolean isForgetTransactionPwd;
	private String phonenumber;
    public Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgetpwd_realname);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		isForgetTransactionPwd=getIntent().getBooleanExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("找回登录密码");
         if(isForgetTransactionPwd)
         {
        	 titleTv.setText("找回交易密码");
         }
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_realname=(ClearableEditText)findViewById(R.id.clearedittext_realname);
		clearedittext_realname.addTextChangedListener(new TextWatcherListener());
		clearedittext_icnumber=(ClearableEditText)findViewById(R.id.clearedittext_icnumber);
		clearedittext_icnumber.addTextChangedListener(new IcnumberTextWatcher());
		clearedittext_phonenumber=(ClearableEditText)findViewById(R.id.clearedittext_phonenumber);
		clearedittext_phonenumber.addTextChangedListener(new TextWatcherListener());
		clearedittext_identifying_code=(ClearableEditText)findViewById(R.id.clearedittext_identifying_code);
		clearedittext_identifying_code.addTextChangedListener(new TextWatcherListener());
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);

		textview_get_identifying_code=(TextView)findViewById(R.id.textview_get_identifying_code);
		textview_get_identifying_code.setOnClickListener(this);
		textview_identifying_code_timer=(TextView)findViewById(R.id.textview_identifying_code_timer);
		linear_reload_identify_code=(LinearLayout)findViewById(R.id.linear_reload_identify_code);
		linear_bottom=(LinearLayout)findViewById(R.id.linear_bottom);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		textview_icnumber_enlarge=(TextView)findViewById(R.id.textview_icnumber_enlarge);
		linear_icnumber_enlarge=(LinearLayout)findViewById(R.id.linear_icnumber_enlarge);

		clearedittext_realname.setOnFocusChangeListener(this);
		clearedittext_icnumber.setOnFocusChangeListener(this);
		clearedittext_phonenumber.setOnFocusChangeListener(this);
		clearedittext_identifying_code.setOnFocusChangeListener(this);

		
		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
		//phonenumber=BeikBankApplication.getPhoneNumber();
		if(phonenumber!=null&&!"".equals(phonenumber))
		{
		   clearedittext_phonenumber.setText(Utils.getEncryptedPhonenumber(phonenumber));
		   clearedittext_phonenumber.setEnabled(false);
		   clearedittext_phonenumber.removeClearButton();
		   //clearedittext_phonenumber.setText(phonenumber);
		}
		//new SendCodeManager(act,phonenumber,"3", icb0).start();
//		SmsContent content = new SmsContent(ForgetPwdRealnameActivity.this, new Handler(), clearedittext_identifying_code);
//		this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
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
			// TODO Auto-generated method stub
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);

			
			String realname=clearedittext_realname.getText().toString();
			String icnumber=clearedittext_icnumber.getText().toString();
			String code=clearedittext_identifying_code.getText().toString();
			if(realname.length()>0&&(icnumber.length()==18)&&(code.length()>0)){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}
	private void checkYanZhenMa(String vertifycode)
	{
		ICallBack icb_gyz=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{   
					checkYanZhenMa_data cd=(checkYanZhenMa_data) obj;
					if("0000".equals(cd.header.re_code))
					{
						
						if(isForgetTransactionPwd){
							Intent intent=new Intent(ForgetPwdRealnameActivity.this,TransactionPwdSetActivity.class);
							intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
							startActivity(intent);
							
						}
						else
						{
							Intent intent=new Intent(ForgetPwdRealnameActivity.this,RegisterPwdSetActivity.class);
							intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, true);
							intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
							startActivity(intent);
						}
						Toast.makeText(ForgetPwdRealnameActivity.this,"重置密码成功",Toast.LENGTH_LONG).show();
						finish();
						
					}
				}
			}
		};
		if(hp!=null)
		{
			
		checkYanZhenMaParam cyzm=new checkYanZhenMaParam();
		cyzm.generate_seq=hp.request_seq;
		cyzm.verification_code=vertifycode;
		cyzm.phone_number=phonenumber;
		TongYongManager2 tym2=new TongYongManager2(act, icb_gyz,cyzm);
		tym2.start();
		}
		else
		{
			showToast("请先请求验证码");
		}
	}
	HeadParam2 hp;
	TongYongManager2 tym2;
	private void sentYanZhenMa()
	{  
		timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
		timer.start();
      ICallBack icb_gyz=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					hp=tym2.hp;
				}
			}
		};
		getYanZhenMaParam gyz=new getYanZhenMaParam();
		gyz.generate_code_type="0";
		gyz.phone_number=phonenumber;
		tym2=new TongYongManager2(act, icb_gyz,gyz);
		tym2.start();
		
	}
    private  void checkUser(String id,String name,String phone)
    {   
    	ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					UserCheck3_data ud=(UserCheck3_data) obj;
					if("0000".equals(ud.header.re_code))
					{
						sentYanZhenMa();
						
					}
				}
			}
		};
    	UserCheckParam3 uc3=new UserCheckParam3();
    	uc3.id_number=id;
    	uc3.real_name=name;
    	uc3.phone_number=phone;
    	TongYongManager2 tym2=new TongYongManager2(act, icb,uc3);
    	tym2.start();
    	
    }
	@Override
	public void onClick(View v) {
		String verificode=clearedittext_identifying_code.getText().toString();
		String realname=clearedittext_realname.getText().toString();
		String icnumber=clearedittext_icnumber.getText().toString();
		switch(v.getId()){
		case R.id.button_next:
			IdcardValidator iv = new IdcardValidator();
			
			if(!Utils.isRealname(realname)){
				//textview_toast.setText("姓名格式不正确！");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
				showToast("姓名格式不正确！");
			}else if(!iv.isValidatedAllIdcard(icnumber)){
				//textview_toast.setText("身份证格式不正确！");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
				showToast("身份证格式不正确!");
			}else{
//				BeikBankApi.getInstance().checkCodeInfo(ForgetPwdRealnameActivity.this,phonenumber,realname,icnumber,
//						verificode,vertifyid, checkIdentifyCodeInfoHandler);
			
//				CheckCodeManager2 ccm2=new CheckCodeManager2(act,phonenumber,vertifyid,realname,icnumber, verificode, icb);
//				ccm2.init("1");
//				ccm2.start();
				checkYanZhenMa(verificode);
			}
			break;
		case R.id.textview_get_identifying_code:
			
			if(phonenumber==null||phonenumber.equals(""))
			{
				showToast("请输入手机号");
				return;
			}
			if(icnumber==null||icnumber.equals(""))
			{
				showToast("请输入身份证号");
				return;
			}
			if(realname==null||realname.equals(""))
			{
				showToast("请输入姓名");
				return;
			}
			clearedittext_identifying_code.requestFocus();
			//new SendCodeManager(act,phonenumber,"3", icb0).start();
			
			checkUser(icnumber,realname,phonenumber);
//			BeikBankApi.getInstance().getIdentifyCodeInfo(ForgetPwdRealnameActivity.this,phonenumber,"3",
//					getIdentifyCodeInfoHandler);
			
			break;
		case R.id.linear_left:
			finish();
			break;
		}
	}
	//检查验证码
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(isForgetTransactionPwd){
				Intent intent=new Intent(ForgetPwdRealnameActivity.this,TransactionPwdSetActivity.class);
				intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
				startActivity(intent);
				finish();
			}else{
				Intent intent=new Intent(ForgetPwdRealnameActivity.this,RegisterPwdSetActivity.class);
				intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, true);
				intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
				startActivity(intent);
				finish();
			}
			
		}
	};
	//发送验证码
	ICallBack icb0=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null&&obj instanceof SendCode_data)
			{
				SendCode_data sd=(SendCode_data) obj;
				vertifyid=sd.data.verificodeId;
			}
			
		}
	};
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer=null;
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	//实现计时功能的类  
	class MyCount extends AdvancedCountdownTimer {  

		public MyCount(long millisInFuture, long countDownInterval) {  
			super(millisInFuture, countDownInterval);  
			linear_reload_identify_code.setVisibility(View.VISIBLE);
			textview_get_identifying_code.setVisibility(View.GONE);
			ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
			SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME/COUNTDOWNINTERVAL));
			ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
			textview_identifying_code_timer.setText(ssb);
			textview_identifying_code_timer.append(getString(R.string.identify_code_reload));
		}  

		@Override  
		public void onFinish() {              
			linear_reload_identify_code.setVisibility(View.GONE);
			textview_get_identifying_code.setVisibility(View.VISIBLE);
			textview_get_identifying_code.setText(getString(R.string.identify_code_reloading));
			timer=null;
		}     
		//更新剩余时间  
		@Override  
		public void onTick(long millisUntilFinished, int percent) { 
			long time = (millisUntilFinished / 1000);  
			ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
			SpannableString ssb = new SpannableString(String.valueOf(time));
			ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
			textview_identifying_code_timer.setText(ssb);
			textview_identifying_code_timer.append(getString(R.string.identify_code_reload));           

		}  

	}  


	class IcnumberTextWatcher implements TextWatcher{
		int beforeTextLength=0;
		int onTextLength=0;
		boolean isChanged=false;

		int location=0;
		private char[] tempChar;
		private StringBuffer buffer=new StringBuffer();
		int blankspaceNumberB=0;

		@Override
		public void afterTextChanged(Editable editable) {
			// TODO Auto-generated method stub
			String str="";
			if(isChanged){
				location=clearedittext_icnumber.getSelectionEnd();
				int index=0;
				while(index<buffer.length()){
					if(buffer.charAt(index)==' '){
						buffer.deleteCharAt(index);
					}else{
						index++;
					}
				}
				index =0;
				int blankspaceNumberC=0;
				while(index<buffer.length()){
					if((index==6||index==15)){
						buffer.insert(index, ' ');
						blankspaceNumberC++;
					}
					index++;
				}
				if(blankspaceNumberC>blankspaceNumberB){
					location+=(blankspaceNumberC-blankspaceNumberB);
				}

				tempChar=new char[buffer.length()];
				buffer.getChars(0, buffer.length(), tempChar, 0);
				str=buffer.toString();
				if(location>str.length()){
					location=str.length();
				}else if(location<0){
					location=0;
				}

				textview_icnumber_enlarge.setText(str);
				isChanged=false;

			}
			if(beforeTextLength==0){
				textview_icnumber_enlarge.setText(editable.toString());
				Utils.performAnimateForDown(linear_bottom,linear_icnumber_enlarge);
			}else if(beforeTextLength>0&&editable.toString().length()==0){
				textview_icnumber_enlarge.setText("");
				Utils.performAnimateForUp(linear_bottom);
			}

			String realname=clearedittext_realname.getText().toString();
			String phone=clearedittext_phonenumber.getText().toString();
			String code=clearedittext_identifying_code.getText().toString();
			if(realname.length()>0&&(editable.toString().length()==18)&&(phone.length()==11)&&(code.length()>0)){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}

		}
		@Override
		public void beforeTextChanged(CharSequence text, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			beforeTextLength=text.length();
			if(buffer.length()>0){
				buffer.delete(0, buffer.length());
			}
			blankspaceNumberB=0;
			for(int i=0;i<text.length();i++){
				if(text.charAt(i)==' '){
					blankspaceNumberB++;
				}
			}

		}

		@Override
		public void onTextChanged(CharSequence text, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			onTextLength=text.length();
			buffer.append(text.toString());
			if(onTextLength==beforeTextLength||onTextLength<1||
					isChanged){
				isChanged=false;
				return;
			}
			isChanged=true;
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.clearedittext_icnumber:
			if(hasFocus){
				if(clearedittext_icnumber.getText().toString().length()>0){
					Utils.performAnimateForDown(linear_bottom,linear_icnumber_enlarge);
				}
			}
			break;
		case R.id.clearedittext_realname:
			if(hasFocus){
				if(clearedittext_icnumber.getText().toString().length()>0){
					Utils.performAnimateForUp(linear_bottom);
				}
			}
			break;
		case R.id.clearedittext_identifying_code:
			if(hasFocus){
				if(clearedittext_icnumber.getText().toString().length()>0){
					Utils.performAnimateForUp(linear_bottom);
				}
			}
			break;
		case R.id.clearedittext_phonenumber:
			if(hasFocus){
				if(clearedittext_icnumber.getText().toString().length()>0){
					Utils.performAnimateForUp(linear_bottom);
				}
			}
			break;


		}


	}

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
//			dialog=Utils.createPorgressDialog(ForgetPwdRealnameActivity.this, null);
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
//				if(isForgetTransactionPwd){
//					Intent intent=new Intent(ForgetPwdRealnameActivity.this,TransactionPwdSetActivity.class);
//					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
//					startActivity(intent);
//				}else{
//					Intent intent=new Intent(ForgetPwdRealnameActivity.this,RegisterPwdSetActivity.class);
//					intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, true);
//					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
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

}
