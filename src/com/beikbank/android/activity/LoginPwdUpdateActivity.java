package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.beikbank.android.data2.setLoginPasswd_data;
import com.beikbank.android.dataparam2.setLoginPasswdParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.net.impl.UpdatePasswd;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;
import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//修改登录密码
public class LoginPwdUpdateActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="LoginPwdUpdateActivity";
	private ClearableEditText clearedittext_old_password,clearedittext_new_password,
	clearedittext_repeat_password;
	private Button button_confirm;
	private TextView titleTv,textview_toast;
	private LinearLayout linear_toast,linear_left;

	private AnimatorSet toastAnimSet;
	private Dialog dialog;
    Activity act=this;
    /**
     * 忘记登录密码
     */
    private TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpwd_update);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		tv1=(TextView) findViewById(R.id.tv1);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.change_password));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		tv1.setOnClickListener(this);
		
		clearedittext_old_password=(ClearableEditText)findViewById(R.id.clearedittext_old_password);
		clearedittext_old_password.addTextChangedListener(new TextWatcherListener());
		clearedittext_new_password=(ClearableEditText)findViewById(R.id.clearedittext_new_password);
		clearedittext_new_password.addTextChangedListener(new TextWatcherListener());
		clearedittext_repeat_password=(ClearableEditText)findViewById(R.id.clearedittext_repeat_password);
		clearedittext_repeat_password.addTextChangedListener(new TextWatcherListener());
		button_confirm=(Button)findViewById(R.id.button_next);
		button_confirm.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
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
			String oldpwd=clearedittext_old_password.getText().toString();
			String newpwd=clearedittext_new_password.getText().toString();
			String repeatpwd=clearedittext_repeat_password.getText().toString();
			if(oldpwd.length()>=6&&(newpwd.length()>=6)&&(repeatpwd.length()>=6)&&(newpwd.length()==repeatpwd.length())){
				button_confirm.setEnabled(true);
			}else{
				button_confirm.setEnabled(false);
			}
		}

	}
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
					MD5.md5s32(clearedittext_new_password.getText().toString()));
			CustomToast.makeText(LoginPwdUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();  
			finish();
		}
	};
	
	private void updatePasswd(String passwd,String old)
	{
		
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					setLoginPasswd_data sd=(setLoginPasswd_data) obj;
					if("0000".equals(sd.header.re_code))
					{
//					
//					Intent intent = new Intent(act, HomeActivity4.class); 
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,true);
//
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,true);
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
						CustomToast.makeText(LoginPwdUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();  
						finish();
					}
				}
				
			}
		};
			setLoginPasswdParam pp=new setLoginPasswdParam();
			pp.fingerprint_password="";
			pp.gesture_password="";
			pp.login_type="0";
			pp.new_password=MD5.md5s32(passwd);
			pp.old_password=MD5.md5s32(old);
			pp.set_type="0";
			pp.user_code=BeikBankApplication.getUserCode();
			pp.user_type=BeikBankApplication.getSharePref(BeikBankConstant.user_type);
			
		TongYongManager2 tym2=new TongYongManager2(act, icb,pp);
		tym2.start();
		
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			String oldpwd=clearedittext_old_password.getText().toString();
			String newpwd=clearedittext_new_password.getText().toString();
			String repeatpwd=clearedittext_repeat_password.getText().toString();
			if(!newpwd.equals(repeatpwd)){
				//textview_toast.setText("密码不一致！");
				clearedittext_repeat_password.requestFocus();
				//Utils.performAnimateForToast(linear_toast, toastAnimSet);
				showToast("密码不一致！");
				
			}else if(oldpwd.equals(newpwd)){
				//textview_toast.setText("新密码不能与旧密码相同！");
				//Utils.performAnimateForToast(linear_toast, toastAnimSet);
				showToast("新密码不能与旧密码相同！");
			}else{
				//UserInfo userInfo=UserInfoDao.getInstance(LoginPwdUpdateActivity.this).getUserInfo();
//				 UserInfo userInfo=BeikBankApplication.getUserInfo();
//				if(userInfo!=null)
//				{   
//					
//					new UpdatePasswd(act,icb).start(userInfo.getId(),oldpwd,newpwd);
//				}
				updatePasswd(newpwd,oldpwd);
				//BeikBankApi.getInstance().updatePasswordInfo(LoginPwdUpdateActivity.this, userInfo.getId(), 
				//		oldpwd, newpwd, updatePasswordInfoHandler);
			}
			break;
		case R.id.linear_left:
			finish();
			break;
		case R.id.tv1:
			//UserInfo ui=BeikBankApplication.getUserInfo();
			Intent intent=null;
			String s=BeikBankApplication.getSharePref(BeikBankConstant.is_shimin);
			if("1".equals(s))
			{   
				
				intent=new Intent(this,ForgetPwdRealnameActivity.class);
				String phonenumber=BeikBankApplication.getPhoneNumber();
				intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			}
			else
			{   
				
				intent=new Intent(this,ForgetPwdAnonymousActivity.class);
				String phonenumber=BeikBankApplication.getPhoneNumber();
				intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			}
			startActivity(intent);
			break;	
		}
	}
  
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


//	JsonHttpResponseHandler updatePasswordInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialog(LoginPwdUpdateActivity.this, null);
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
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//						clearedittext_new_password.getText().toString());
//				CustomToast.makeText(LoginPwdUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();  
//				finish();
//			}else{
//				clearedittext_old_password.requestFocus();
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast, toastAnimSet);
//				
//
//			}
//
//		}
//
//	};

}
