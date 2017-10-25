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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.activity.ForgetPwdRealnameActivity.MyCount;
import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.data.RegisterCode;
import com.beikbank.android.data.SendCode_data;
import com.beikbank.android.data2.checkYanZhenMa_data;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.dataparam2.getYanZhenMaParam;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckCodeManager2;
import com.beikbank.android.net.impl.SendCodeManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.SmsContent;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.google.gson.Gson;
import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//没有实名认证的用户，忘记密码
public class ForgetPwdAnonymousActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="ForgetPwdAnonymousActivity";
	private ClearableEditText clearedittext_phonenumber,clearedittext_identifying_code;
	private Button button_next;
	private LinearLayout linear_reload_identify_code,linear_toast,linear_left;
	private TextView titleTv,textview_identifying_code_timer,textview_toast,textview_get_identifying_code;
	private MyCount timer;
	private final int TOTALTIME=60*1000;//定时60秒
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒

	private AnimatorSet toastAnimSet;
	private String vertifyid;
	private Dialog dialog;
	private String phonenumber;
    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgetpwd_anonymous);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("找回登录密码");

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

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

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);

		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
		if(phonenumber!=null&&!"".equals(phonenumber))
		{
		clearedittext_phonenumber.setText(Utils.getEncryptedPhonenumber(phonenumber));
		clearedittext_phonenumber.setEnabled(false);
		clearedittext_phonenumber.removeClearButton();
		}
//		SmsContent content = new SmsContent(ForgetPwdAnonymousActivity.this, new Handler(), clearedittext_identifying_code);
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
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);

			
			if(clearedittext_identifying_code.getText().toString().length()>=4){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}
	//检查验证码
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			Intent intent=new Intent(ForgetPwdAnonymousActivity.this,RegisterPwdSetActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, true);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
			finish();
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
						
					
						Intent intent=new Intent(ForgetPwdAnonymousActivity.this,RegisterPwdSetActivity.class);
						intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, true);
						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
						startActivity(intent);
						
						
					}
				}
			}
		};
		checkYanZhenMaParam cyzm=new checkYanZhenMaParam();
		cyzm.generate_seq=hp.request_seq;
		cyzm.verification_code=vertifycode;
		TongYongManager2 tym2=new TongYongManager2(act, icb_gyz,cyzm);
		tym2.start();
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			String verificode=clearedittext_identifying_code.getText().toString();
			
			//BeikBankApi.getInstance().checkCodeInfo(ForgetPwdAnonymousActivity.this,phonenumber,"","",
					//verificode,vertifyid, checkIdentifyCodeInfoHandler);
		    checkYanZhenMa(verificode);
			break;
		case R.id.textview_get_identifying_code:
			clearedittext_identifying_code.requestFocus();
//			BeikBankApi.getInstance().getIdentifyCodeInfo(ForgetPwdAnonymousActivity.this,phonenumber,"3",
//					getIdentifyCodeInfoHandler);
//			new SendCodeManager(act,phonenumber,"3", icb0).start();
//			timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
//			timer.start();
			sentYanZhenMa();
			break;
		case R.id.linear_left:
			finish();
			break;
		}
	}

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
//			dialog=Utils.createPorgressDialog(ForgetPwdAnonymousActivity.this, null);
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
//				Intent intent=new Intent(ForgetPwdAnonymousActivity.this,RegisterPwdSetActivity.class);
//				intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, true);
//				intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
//				startActivity(intent);
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

}
