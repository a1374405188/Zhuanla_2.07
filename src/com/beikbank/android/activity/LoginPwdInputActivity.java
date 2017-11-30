package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.conmon.ShareParam;
import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.AuthInfo;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankListInfo;
import com.beikbank.android.data.IsReanlName;
import com.beikbank.android.data.IsReanlName_data;
import com.beikbank.android.data.LoginInfo;
import com.beikbank.android.data.UserInfo_data;
import com.beikbank.android.data2.Login;
import com.beikbank.android.data2.LoginQian;
import com.beikbank.android.data2.LoginQian2;
import com.beikbank.android.data2.LoginQian_data;
import com.beikbank.android.data2.Login_data;
import com.beikbank.android.data2.UserCheck2;
import com.beikbank.android.data2.UserCheck2_data;
import com.beikbank.android.dataparam.LoginParam;
import com.beikbank.android.dataparam.LoginParam2;
import com.beikbank.android.dataparam2.LoginQianParam;
import com.beikbank.android.dataparam2.UserCheckParam;
import com.beikbank.android.dataparam2.UserCheckParam2;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.BankListManager;
import com.beikbank.android.net.impl.IsRealNameManager;
import com.beikbank.android.net.impl.LoginManager;
import com.beikbank.android.net.impl.ThreadManagerSet;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.LockPatternUtils;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;
import com.google.gson.Gson;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



//输入登录密码
public class LoginPwdInputActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="LoginPwdInputActivity";
	private ClearableEditText clearedittext_password;
	private Button button_password;
	private TextView titleTv,textview_toast,textview_forgetpwd,textview_remind;
	private LinearLayout linear_toast,linear_left;
	private AnimatorSet toastAnimSet;
	private String phonenumber;
	private Dialog dialog;
    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpwd_input);
		StateBarColor.init(this,0xffffffff);
		initView();
		initdata();

	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.login));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_password=(ClearableEditText)findViewById(R.id.clearedittext_password);
		clearedittext_password.addTextChangedListener(new TextWatcherListener());
		textview_forgetpwd=(TextView)findViewById(R.id.textview_forgetpwd);
		textview_forgetpwd.setOnClickListener(this);

		textview_remind=(TextView)findViewById(R.id.textview_remind);
		button_password=(Button)findViewById(R.id.button_next);
		button_password.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);

		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
		if(!TextUtils.isEmpty(phonenumber)){
			textview_remind.setText(Utils.getEncryptedPhonenumber(phonenumber)+"，欢迎回来！");
		}

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
				    Intent intent = new Intent(LoginPwdInputActivity.this, HomeActivity4.class); 
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				  
					
					
					Log.d("code",lq.user_code);
				 }
			}
			
		}
	};
	public void initdata()
	{
		LoginQianParam pp=new LoginQianParam();
		pp.phone_number=phonenumber;
		
		TongYongManager2 tym=new TongYongManager2(act, icb3,pp);
    	tym.start();
	}
	String password;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			 password=clearedittext_password.getText().toString();
			//BeikBankApi.getInstance().checkLoginInfo(LoginPwdInputActivity.this,
			//		phonenumber,password,getLoginInfoHandler);

//		    	 dialog=Utils.createPorgressDialog(LoginPwdInputActivity.this, null);
//		         dialog.show();
//		         new LoadData().start();
				// new LoginManager(act,phonenumber,MD5.md5s32(password),1, icb).start();
                
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
			//BeikBankApi.getInstance().checkAuthenticatedInfo(LoginPwdInputActivity.this,
			//		phonenumber,checkAuthHandler);
			     //new IsRealNameManager(act, phonenumber, icb1).start();
			checkUser(phonenumber);
			break;
		case R.id.linear_left:			
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
				    	Intent intent=new Intent(LoginPwdInputActivity.this,ForgetPwdAnonymousActivity.class);
						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
						startActivity(intent);
				    }
				    else 
				    {  
				    	Intent intent=new Intent(LoginPwdInputActivity.this,ForgetPwdRealnameActivity.class);
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
	
	/**
	 * 登录加载和银行卡信息
	 */
	public void addData()
	{   
		dialog=Utils.createPorgressDialog(act, null);
	    dialog.show();
		ThreadManagerSet tms=new ThreadManagerSet(icb);
		LoginManager lm=new LoginManager(this,null,null,0, icb2);
		BankListManager bl=new BankListManager(this, icb2);
		tms.add(lm);
		tms.add(bl);
		tms.start();
	}
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
		}
	};
 ICallBack icb1=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null&&obj instanceof IsReanlName_data)
		{  
			IsReanlName_data data=(IsReanlName_data) obj;
			IsReanlName irn=data.data;
		   if(irn.hasAuthenticated)
		   {
			Intent intent=new Intent(LoginPwdInputActivity.this,ForgetPwdRealnameActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
		   }else
		   {
			Intent intent=new Intent(LoginPwdInputActivity.this,ForgetPwdAnonymousActivity.class);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
		   }
		}

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
//以下是网络部分--------------------------------------------------------------------------------------------------------
  ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
	  //String do_success=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.DO_SUCCESS1);
	  boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
	  if(dialog!=null)
	  {
		  dialog.dismiss();
	  }
	  if(do_success)
	  { 
//		if(HomeActivity2.ha!=null)
//		{
//			HomeActivity2.ha.mSlidingMenu.toggle2(false);
//		}
		int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
	    Intent intent = new Intent(LoginPwdInputActivity.this, HomeActivity4.class); 
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

//		if(intflag==2||intflag==3)
//		{
//			//不需要toast
//		}else
//		{
//			CustomToast.makeText(LoginPwdInputActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//		}
		
	  }
	}
};
	





}
