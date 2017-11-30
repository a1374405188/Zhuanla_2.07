package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.data.CheckPhone;
import com.beikbank.android.data.CheckPhone_data;
import com.beikbank.android.data.LoginInfo;
import com.beikbank.android.data2.UserCheck_data;
import com.beikbank.android.dataparam2.UserCheckParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckPhoneManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DataCheck;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.google.gson.Gson;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



//登录页面
public class LoginRegActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="LoginRegActivity";
	private ClearableEditText clearedittext_phonenumber;
	private Button button_login;
	private TextView textview_phone_enlarge;
	private LinearLayout linear_phone_enlarge,linear_toast,linear_bottom,linear_left;
	private TextView titleTv,textview_agreement,textview_agreement2,textview_toast;
	private AnimatorSet toastAnimSet;
	private CheckBox checkbox_agreement;
	private Dialog dialog;
    boolean isOnclick=false;
    Activity act=this;
    public int testa=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		StateBarColor.init(this,0xffffffff);
		initView();
		HomeActivity4.index=1;

	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.inputnumber));
		String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);

		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		clearedittext_phonenumber=(ClearableEditText)findViewById(R.id.clearedittext_phonenumber);
		
		clearedittext_phonenumber.addTextChangedListener(new PhoneTextWatcher()); 
		button_login=(Button)findViewById(R.id.button_next);
		button_login.setOnClickListener(this);
		textview_phone_enlarge=(TextView)findViewById(R.id.textview_phone_enlarge);
		linear_phone_enlarge=(LinearLayout)findViewById(R.id.linear_phone_enlarge);
		
		textview_agreement=(TextView)findViewById(R.id.textview_agreement);
		textview_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
		textview_agreement.setText(getResources().getString(R.string.bk_agreement));
		textview_agreement.setOnClickListener(this);
		
		//textview_agreement2=(TextView)findViewById(R.id.textview_agreement2);
		//textview_agreement2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
	//	textview_agreement2.setText(getResources().getString(R.string.bk_agreement2));
	//	textview_agreement2.setOnClickListener(this);
		
		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		
		linear_bottom=(LinearLayout)findViewById(R.id.linear_bottom);
		checkbox_agreement=(CheckBox)findViewById(R.id.checkbox_agreement);	
		checkbox_agreement.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
				// TODO Auto-generated method stub
				if(!ischecked){
					button_login.setEnabled(false);
				}else {
					if(clearedittext_phonenumber.getText().toString().length()==11){
						button_login.setEnabled(true);
					}
				}
					
			}
		});
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			
			
			
			String phonenumber=clearedittext_phonenumber.getText().toString();
			//if(!Utils.isPhoneNumberValid(phonenumber))
			if(!DataCheck.checkPhone(phonenumber))
			{
				//textview_toast.setText("手机号码格式错误！");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
				showToast("手机号码格式错误！");
			}else {

//				  BeikBankApi.getInstance().checkPhoneInfo(LoginRegActivity.this,phonenumber,getLoginInfoHandler);
					//new CheckPhoneManager(act, phonenumber, icb).start();
				
				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT,phonenumber);
				UserCheckParam ucp=new UserCheckParam();
				ucp.phone_number=phonenumber;
				
				
				TongYongManager2 tym=new TongYongManager2(act, icb,ucp);
		    	tym.start();
			}
			break;
		case R.id.linear_left:
			HomeActivity3.index=1;
			finish();
			break;
		case R.id.textview_agreement:
			Intent intent=new Intent(this,AgreementPurchaseActivity3.class);
			intent.putExtra("path","4");
			startActivity(intent);
			//startAimActivity(AgreementPurchaseActivity3.class);
			break;
		case R.id.textview_agreement2:
			//startAimActivity(AgreementLogin2Activity.class);
			break;
		}
	}
  ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			UserCheck_data ud=(UserCheck_data) obj;
			
			if("9998".equals(ud.header.re_code))
			{
				Intent intent=new Intent(LoginRegActivity.this,LoginPwdInputActivity.class);
				intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, clearedittext_phonenumber.getText().toString());
				startActivity(intent);
			}
			else
			{
				Intent intent=new Intent(LoginRegActivity.this,RegisterCodeInputActivity.class);
				intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, clearedittext_phonenumber.getText().toString());
				startActivity(intent);
			}
		}
		
		
		
		
		
//		LoginRegActivity lr=(LoginRegActivity) act;
//		lr.testa=1;
//		isOnclick=false;
//		if(obj!=null&&obj instanceof CheckPhone_data)
//		{
//		  	
//			CheckPhone_data cd=(CheckPhone_data) obj;
//			CheckPhone cp=cd.data;
//		boolean registrationStatus = cp.registrationStatus;
//		if(registrationStatus){
//			Intent intent=new Intent(LoginRegActivity.this,LoginPwdInputActivity.class);
//			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, clearedittext_phonenumber.getText().toString());
//			startActivity(intent);
//		}else{
//			Intent intent=new Intent(LoginRegActivity.this,RegisterCodeInputActivity.class);
//			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, clearedittext_phonenumber.getText().toString());
//			startActivity(intent);
//		}
//		}
	}
};



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	
	
	
	
	class PhoneTextWatcher implements TextWatcher{
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
				location=clearedittext_phonenumber.getSelectionEnd();
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
					if((index==3||index==8)){
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
				
				textview_phone_enlarge.setText(str);
				isChanged=false;
				
			}
			if(beforeTextLength==0){
				textview_phone_enlarge.setText(editable.toString());
				Utils.performAnimateForDown(linear_bottom,linear_phone_enlarge);
			}else if(beforeTextLength>0&&editable.toString().length()==0){
				textview_phone_enlarge.setText("");
				Utils.performAnimateForUp(linear_bottom);
			}
			
			if(clearedittext_phonenumber.getText().toString().length()==11&&checkbox_agreement.isChecked()){
				button_login.setEnabled(true);
			}else{
				button_login.setEnabled(false);
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

			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			
			
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
	
//	JsonHttpResponseHandler getLoginInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialog(LoginRegActivity.this, null);
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
//			isOnclick=false;
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			Gson gson=new Gson();
//			LoginInfo loginInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//					BeikBankConstant.TYPE_JSONDATA), LoginInfo.class);
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				boolean registrationStatus = loginInfo.isRegistrationStatus();
//				if(registrationStatus){
//					Utils.log(TAG, "onSuccess()"+registrationStatus);	
//					Intent intent=new Intent(LoginRegActivity.this,LoginPwdInputActivity.class);
//					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, clearedittext_phonenumber.getText().toString());
//					startActivity(intent);
//				}else{
//					Utils.log(TAG, "onFailed()"+registrationStatus);
//					Intent intent=new Intent(LoginRegActivity.this,RegisterCodeInputActivity.class);
//					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, clearedittext_phonenumber.getText().toString());
//					startActivity(intent);
//				}
//				
//			}
//
//		}
//
//	};
//	
//	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			HomeActivity3.index=1;
			
	}
		return super.onKeyDown(keyCode, event);
	}
    
}
