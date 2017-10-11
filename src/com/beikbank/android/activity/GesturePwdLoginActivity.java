package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.LoginQian;
import com.beikbank.android.data2.LoginQian2;
import com.beikbank.android.data2.LoginQian_data;
import com.beikbank.android.data2.Login_data;
import com.beikbank.android.data2.UserCheck2;
import com.beikbank.android.data2.UserCheck2_data;
import com.beikbank.android.dataparam2.LoginQianParam;
import com.beikbank.android.dataparam2.UserCheckParam2;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.LoginManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.ActivityManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.LockPatternUtils;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;
import com.google.gson.Gson;
import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//手势密码错误五次，跳到登录
public class GesturePwdLoginActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="GesturePwdLoginActivity";
	private ClearableEditText clearedittext_password;
	private Button button_password;
	private TextView titleTv,textview_toast,textview_forgetpwd,textview_gesture_to_password,
					textview_change_account,textview_logout;
	private LinearLayout linear_toast;
	private AnimatorSet toastAnimSet;
	private String phonenumber;
	private Dialog dialog;
    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesturepwd_login);
		StateBarColor.init(this,0xffffffff);
		initView();
        initdata();
	}
	public void initView(){
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.login));

		clearedittext_password=(ClearableEditText)findViewById(R.id.clearedittext_password);
		clearedittext_password.addTextChangedListener(new TextWatcherListener());
		textview_forgetpwd=(TextView)findViewById(R.id.textview_forgetpwd);
		textview_forgetpwd.setOnClickListener(this);
		button_password=(Button)findViewById(R.id.button_password);
		button_password.setOnClickListener(this);
		textview_gesture_to_password=(TextView)findViewById(R.id.textview_gesture_to_password);
		textview_change_account=(TextView)findViewById(R.id.textview_change_account);
		textview_change_account.setOnClickListener(this);
		textview_logout=(TextView)findViewById(R.id.textview_logout);
		textview_logout.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);

		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
		textview_gesture_to_password.setText(Utils.getEncryptedPhonenumber(phonenumber)+"，欢迎回来！");
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
			if(s.toString().length()>=6){
				button_password.setEnabled(true);
			}else{
				button_password.setEnabled(false);
			}
		}

	}
	private LoginQian lq;
	/**
	 * 登录前置回调
	 */
	private ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		   if(obj!=null)
		   {
			   LoginQian_data ld=(LoginQian_data) obj;
			   LoginQian2 lq2=ld.body;
			   lq=lq2.data.get(0);
			 
		   }
		}
	};
	private ICallBack icb5=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				Login_data ld=(Login_data) obj;
				//Login l=ld.body;
				 if("0000".equals(ld.header.re_code))
				 {
				  BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,true);
				  BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT,phonenumber);
				  BeikBankApplication.setSharePref(BeikBankConstant.user_code,lq.user_code);
				  BeikBankApplication.setSharePref(BeikBankConstant.user_type,lq.user_type);
				  
				  
					int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
				    Intent intent = new Intent(act, HomeActivity4.class); 
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				  
					
					
					Log.d("code",lq.user_code);
				 }
			}
			
		}
	};
	private void initdata()
	{
		LoginQianParam pp=new LoginQianParam();
		pp.phone_number=phonenumber;
		
		TongYongManager2 tym=new TongYongManager2(act, icb3,pp);
    	tym.start();
	}
	@Override
	public void onClick(View v) {
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
		switch(v.getId()){
		case R.id.button_password:
			String password=clearedittext_password.getText().toString();
			//new LoginManager(act,phonenumber,MD5.md5s32(password),1, icb).start();
			//BeikBankApi.getInstance().checkLoginInfo(GesturePwdLoginActivity.this,
			//		phonenumber,password,getLoginInfoHandler);
			
			
			   com.beikbank.android.dataparam2.LoginParam lp=new com.beikbank.android.dataparam2.LoginParam();
			   lp.login_password=MD5.md5s32(password);
			   lp.login_type="0";
			   lp.phone_number=phonenumber;
			   lp.user_code=lq.user_code;
			   lp.user_type=lq.user_type;
			   
				TongYongManager2 tym=new TongYongManager2(act, icb5,lp);
		    	tym.start();
			break;
		case R.id.textview_forgetpwd:
			//匿名忘记密码
			//startAimActivity(ForgetPwdAnonymousActivity.class);
			checkUser(phonenumber);
			break;
		case R.id.textview_logout:
			//startAimActivity(HomeActivity2.class);
			finish();
			break;
		case R.id.textview_change_account:
			com.beikbank.android.utils.LoginManager.outLogin(this,1);
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
			startAimActivity(LoginRegActivity.class);
			finish();
			break;
			
		}
	}
	
	
	private void checkUser(String phone)
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					UserCheck2_data uc2d=(UserCheck2_data) obj;
				    UserCheck2 uc2=uc2d.body;
				    if("0".equals(uc2.is_real_name))
				    {
				    	Intent intent=new Intent(act,ForgetPwdAnonymousActivity.class);
						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
						startActivity(intent);
				    }
				    else 
				    {  
				    	Intent intent=new Intent(act,ForgetPwdRealnameActivity.class);
						intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
						startActivity(intent);
						
					}
				}
			}
		};
		UserCheckParam2 uc2=new UserCheckParam2();
    	uc2.check_type="1";
    	uc2.phone_number=phone;
    	
     	TongYongManager2 tym2=new TongYongManager2(act, icb,uc2);
    	tym2.start();
		
	}
   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
	   if(obj!=null)
	   {
		    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
			finish();
	   }
	}
};
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	
//	JsonHttpResponseHandler getLoginInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialog(GesturePwdLoginActivity.this, null);
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
//			Gson gson=new Gson();
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				UserInfo userInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//						BeikBankConstant.TYPE_JSONDATA), UserInfo.class);
//				TableDaoSimple.delete(UserInfo.class,null,null);
//				//UserInfoDao.getInstance(GesturePwdLoginActivity.this).deleteAll();
//				//UserInfoDao.getInstance(GesturePwdLoginActivity.this).insertUserInfo(userInfo);
//				if(userInfo.isHasBindCard()){
//					ArrayList<CardInfo> cardInfoList=userInfo.getCards();
//					//CardInfoDao.getInstance(GesturePwdLoginActivity.this).deleteAll();
//					//CardInfoDao.getInstance(GesturePwdLoginActivity.this).insertCardInfo(cardInfoList.get(0));
//					TableDaoSimple.delete(CardInfo.class,null,null);
//					TableDaoSimple.insert(cardInfoList.get(0));
//					
//				}				
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//						phonenumber);
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//						clearedittext_password.getText().toString());
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//						false);
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//						LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//						LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//				
//				Intent intent = new Intent(GesturePwdLoginActivity.this, HomeActivity2.class); 
//				startActivity(intent);
//				ActivityManager.getScreenManager().popAllActivityExceptOne(HomeActivity2.class);
//				CustomToast.makeText(GesturePwdLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//				
//				
//			}else{
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//			}
//
//		}
//
//	};

}
