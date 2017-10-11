package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.RegisterCode;
import com.beikbank.android.data.SendCode;
import com.beikbank.android.data.SendCode_data;
import com.beikbank.android.data2.checkYanZhenMa_data;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.dataparam2.getYanZhenMaParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckCodeManager;
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

//注册验证码输入
public class RegisterCodeInputActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="RegisterCodeInputActivity";
	private ClearableEditText clearedittext_identify_code;
	private Button button_next;
	private TextView titleTv,textview_identifying_code_timer,textview_phonenumber,
			textview_toast,textview_get_identifying_code;
	private LinearLayout linear_reload_identify_code,linear_toast,linear_left;
	private MyCount timer;

	private String phonenumber,vertifyid;
	private final int TOTALTIME=60*1000;
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒

	private AnimatorSet toastAnimSet;
	private Dialog dialog;
	
    Activity act=this;
    HeadParam2 hp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_code);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.register));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_identify_code=(ClearableEditText)findViewById(R.id.clearedittext_identify_code);
		clearedittext_identify_code.addTextChangedListener(new TextWatcherListener());
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);

		textview_phonenumber=(TextView)findViewById(R.id.textview_phonenumber);
		textview_get_identifying_code=(TextView)findViewById(R.id.textview_get_identifying_code);
		textview_get_identifying_code.setOnClickListener(this);
		textview_identifying_code_timer=(TextView)findViewById(R.id.textview_identifying_code_timer);
		linear_reload_identify_code=(LinearLayout)findViewById(R.id.linear_reload_identify_code);

		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);

		textview_phonenumber.setText(Utils.getEncryptedPhonenumber(phonenumber));
		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		
		//BeikBankApi.getInstance().getIdentifyCodeInfo(RegisterCodeInputActivity.this,phonenumber,"1",
		//		getIdentifyCodeInfoHandler);
		//new SendCodeManager(act,phonenumber,"1", icb).start();
		
		timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
		timer.start();
		
		//SmsContent content = new SmsContent(RegisterCodeInputActivity.this, new Handler(), clearedittext_identify_code);
		//this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
	    sent();
	}
	TongYongManager2 tym2;
	private void sent()
	{
		
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
	
	
	class TextWatcherListener implements TextWatcher{
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			
			
			if(s.toString().length()>0){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}
	
   boolean isOnclick=false;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
				String vertifycode=clearedittext_identify_code.getText().toString();
				//BeikBankApi.getInstance().checkIdentifyInfo(RegisterCodeInputActivity.this,vertifycode,vertifyid,
				//		phonenumber,getIdentifyInfoHandler);
				
//				if(vertifycode==null||"".equals(vertifycode))
//				{
//					showToast("验证码错误");
//					return;
//				}
//				new CheckCodeManager(act,phonenumber,vertifyid,vertifycode, icb1).start();
				ICallBack icb_gyz=new ICallBack() {
					
					@Override
					public void back(Object obj) {
						// TODO Auto-generated method stub
						if(obj!=null)
						{   
							checkYanZhenMa_data cd=(checkYanZhenMa_data) obj;
							if("0000".equals(cd.header.re_code))
							{
								
							
							Intent intent=new Intent(RegisterCodeInputActivity.this,RegisterPwdSetActivity.class);
							intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, false);
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
				
			break;
		case R.id.linear_left:
			finish();
			break;
		case R.id.textview_get_identifying_code:	
			//BeikBankApi.getInstance().getIdentifyCodeInfo(RegisterCodeInputActivity.this,phonenumber,"1",
			//		getIdentifyCodeInfoHandler);
			//new SendCodeManager(act,phonenumber,"1", icb).start();
			sent();
			
			timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
			timer.start();			
			break;
		}
	}
	//得到验证码
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null&&obj instanceof SendCode_data)
			{
				SendCode_data sd=(SendCode_data) obj;
				SendCode sc=sd.data;
				vertifyid=sc.verificodeId;
				if(SystemConfig.isDebug())
				{
					
					Log.e("code",sc.code);
					Log.e("vertifyid",vertifyid);
				}
			}
			isOnclick=false;
		}
	};
	//检查验证码
    ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		  if(obj!=null)
		  {
			Intent intent=new Intent(RegisterCodeInputActivity.this,RegisterPwdSetActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, false);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
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
			textview_get_identifying_code.setVisibility(View.GONE);
			linear_reload_identify_code.setVisibility(View.VISIBLE);
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
	
//	JsonHttpResponseHandler getIdentifyInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialog(RegisterCodeInputActivity.this, null);
//			dialog.show();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//			dialog.dismiss();
//			isOnclick=false;
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);
//			dialog.dismiss();
//			isOnclick=false;
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				
//				Intent intent=new Intent(RegisterCodeInputActivity.this,RegisterPwdSetActivity.class);
//				intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, false);
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
//			Utils.log(TAG, "onSuccess()1111"+response.getResponseString());	
//			Gson gson=new Gson();
//			RegisterCode registerCode=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//					BeikBankConstant.TYPE_JSONDATA), RegisterCode.class);
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				vertifyid=registerCode.getVerificodeId();
//				if(SystemConfig.isDebug())
//				{
//					Log.e(TAG,vertifyid);
//				}
//			}
//
//		}
//
//	};
	
}
