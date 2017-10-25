package com.beikbank.android.activity;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankListInfo;
import com.beikbank.android.data.UserInfo_data;
import com.beikbank.android.data2.Register;
import com.beikbank.android.data2.Register_data;
import com.beikbank.android.data2.setLoginPasswd_data;
import com.beikbank.android.dataparam.RegsiterParam;
import com.beikbank.android.dataparam2.RegisterParam;
import com.beikbank.android.dataparam2.setLoginPasswdParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.SetLoginPasswdManager;
import com.beikbank.android.net.impl.LoginManager;
import com.beikbank.android.net.impl.RegisterManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.net.impl.UpdatePasswd;
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

//登录密码设置
public class RegisterPwdSetActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="RegisterPwdSetActivity";
	private ClearableEditText clearedittext_password,clearedittext_repeat_password;
	private Button button_next;
	private TextView titleTv,textview_toast;
	private LinearLayout linear_toast,linear_left;

	private AnimatorSet toastAnimSet;
	private String phonenumber;
	private Dialog dialog;
	private boolean isForgetLoginPassword;
    public RegisterPwdSetActivity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registerpwd_set);
		StateBarColor.init(this,0xffffffff);
		initView();
		initData();

	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.setting_password));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_password=(ClearableEditText)findViewById(R.id.clearedittext_password);
		clearedittext_password.addTextChangedListener(new TextWatcherListener());
		clearedittext_repeat_password=(ClearableEditText)findViewById(R.id.clearedittext_repeat_password);
		clearedittext_repeat_password.addTextChangedListener(new TextWatcherListener());
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);

		isForgetLoginPassword=getIntent().getBooleanExtra(BeikBankConstant.IS_FORGETLOGINPWD, false);
		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
	}
	Register reg;
	private void initData()
	{    
		reg=(Register) getIntent().getSerializableExtra("reg");
		if(isForgetLoginPassword)
		{
			return;
		}
//		ICallBack icb=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				if(obj!=null)
//				{
//					Register_data rd=(Register_data)obj;
//					reg=rd.body;
//					
//					 BeikBankApplication.setSharePref(BeikBankConstant.user_code,reg.user_code);
//					  BeikBankApplication.setSharePref(BeikBankConstant.user_type,reg.user_type);
//				}
//				
//			}
//		};
//		
//		RegisterParam pp=new RegisterParam();
//		pp.download=SystemConfig.SOURCES_CODE;
//		pp.ip=getIPAddress(act);
//		pp.phone_id="";
//		pp.phone_number=phonenumber;
//		//pp.register_time=new Date().toString();
//		
//		TongYongManager2 tym2=new TongYongManager2(act, icb,pp);
//		tym2.start();
		
	}
	public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        	
                        	InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
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
			
			String password=clearedittext_password.getText().toString();
			String repeat_password=clearedittext_repeat_password.getText().toString();
			if(password.length()>=6&&repeat_password.length()>=6&&password.length()==repeat_password.length()){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}
	/**
	 * 重置登录密码
	 * @param passwd
	 */
	private void setLogPasswd(String passwd)
	{
		
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					setLoginPasswd_data sd=(setLoginPasswd_data) obj;
					if("0000".equals(sd.header.re_code))
					{
					
//					Intent intent = new Intent(act, HomeActivity4.class); 
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,true);
//					//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,0);
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//							phonenumber);
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//							MD5.md5s32(clearedittext_password.getText().toString()));
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,true);
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
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
			//pp.old_password=MD5.md5s32(passwd);
			pp.set_type="1";
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
			String password=clearedittext_password.getText().toString();
			String repeat_password=clearedittext_repeat_password.getText().toString();
			if(!password.equals(repeat_password)){
				clearedittext_repeat_password.setText("");
				clearedittext_repeat_password.requestFocus();
				//textview_toast.setText("密码不一致！");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
				showToast("密码不一致！");
			}else{
				//忘记密码
				if(isForgetLoginPassword){
					//BeikBankApi.getInstance().setLoginPasswordInfo(RegisterPwdSetActivity.this,
						//	phonenumber,password,getPasswordInfoHandler);
					//new SetLoginPasswdManager(act,phonenumber,password, icb2).start();
				    setLogPasswd(password);
				}
				//注册
				else{
					//BeikBankApi.getInstance().checkPasswordInfo(RegisterPwdSetActivity.this,
					//		phonenumber,password,getPasswordInfoHandler);
					///dialog=Utils.createPorgressDialog(RegisterPwdSetActivity.this, null);
					//
					//dialog.show();
					//new RegLoadData(phonenumber,password).start();
					//new LoginManager(act,phonenumber,MD5.md5s32(password),2,icb).start();
					//new RegisterManager(act, icb).start(phonenumber,password);
				
				ICallBack icb=new ICallBack() {
					
					@Override
					public void back(Object obj) {
						if(obj!=null)
						{   
							setLoginPasswd_data sd=(setLoginPasswd_data) obj;
							if("0000".equals(sd.header.re_code))
							{
							
							Intent intent = new Intent(act, HomeActivity4.class); 
							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,true);
							//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,0);
							BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
									phonenumber);
							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
							BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
									MD5.md5s32(clearedittext_password.getText().toString()));
							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,true);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
							}
						}
						
					}
				};
					setLoginPasswdParam pp=new setLoginPasswdParam();
					pp.fingerprint_password="";
					pp.gesture_password="";
					pp.login_type="0";
					pp.new_password="";
					pp.old_password=MD5.md5s32(password);
					pp.set_type="0";
					pp.user_code=reg.user_code;
					pp.user_type="4";
					
				TongYongManager2 tym2=new TongYongManager2(act, icb,pp);
				tym2.start();
					
					
				}

			}
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
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}
//以下是网络部分   -------------------------------------------------------------------------------------------------------------------------------
	//注册并且登录
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj!=null&&obj instanceof UserInfo_data)
			{   
//				if(HomeActivity2.ha!=null)
//				{
//					HomeActivity2.ha.mSlidingMenu.toggle2(false);
//				}
				int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
				String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);			
				Intent intent = new Intent(act, HomeActivity3.class); 
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,true);
				//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,0);
				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
						phonenumber);
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
						MD5.md5s32(clearedittext_password.getText().toString()));
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,true);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
//				if(intflag==2||intflag==3){
//					//不需要toast
//				}else{
//					CustomToast.makeText(RegisterPwdSetActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//				}
			}
			
		}
	};
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
					phonenumber);
			Intent intent=new Intent(RegisterPwdSetActivity.this,LoginPwdInputActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	};
//	class RegLoadData extends Thread
//	{   
//		String phoneNumber;
//		String passwd;
//		public RegLoadData(String phoneNumber,String passwd)
//		{
//			this.passwd=passwd;
//			this.phoneNumber=phoneNumber;
//		}
//		@Override
//		public void run()
//		{
//			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//	    	 try {
//	    		RegsiterParam rp=new RegsiterParam();
//	    		rp.phoneNumber=phonenumber;
//	    		rp.loginPassword=passwd;
//	    		
//	    		Message msg=new Message();
//	    		 if(!isNetworkConnected(act))
//	 		    {  
//	 			   msg.what=HanderManager.nonet;
//	 			   handler.sendMessage(msg);
//	 			   return ;
//	 		    }
//	    		
//	    		 UserInfo_data uid= (UserInfo_data)im.register(UserInfo_data.class,null,rp);
//	    		if(!HanderManager.success.equals(uid.result))
//	    		{   
//	    			String s="服务器数据错误";
//			    	if(uid!=null&&uid.message!=null)
//			    	{
//			    		s+=uid.message;
//			    	}
//			    	LogHandler.writeLogFromString(TAG,s);
//	    			 msg.what=HanderManager.data_error;
//	    			 msg.obj=uid;
//		 			 handler.sendMessage(msg);
//		 			 return;
//	    		}
//	    		//数据库操作
//				TableDaoSimple.delete(UserInfo.class,null,null);
//				int re=TableDaoSimple.insert(uid.data);
//				if(re<0)
//				{   
//					LogHandler.writeLogFromString(TAG, "数据库操作错误");
//					msg.what=HanderManager.db_error;
//					handler.sendMessage(msg);
//					return;
//				}
//				
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//						phonenumber);
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//						MD5.md5s32(clearedittext_password.getText().toString()));
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//						false);
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//						LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//						LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//				
//				
//				String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//				BeikBankApi.getInstance().getBankListInfo(getApplicationContext(),version,
//						getBankListInfoHandler);
//				msg.what=HanderManager.success1;
//				handler.sendMessage(msg);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				LogHandler.writeLogFromException(TAG,e);
//				Message msg=new Message();
//				msg.what=HanderManager.error;
//				handler.sendMessage(msg);
//			}
//		}
//	}
//	
//	
//	
//	Handler handler=new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			dialog.dismiss();
//			switch (msg.what) {
//			case HanderManager.nonet:
//				textview_toast.setText(getString(R.string.no_net));
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HanderManager.data_error:
//				textview_toast.setText(getString(R.string.service_data_error));
//				if(msg.obj!=null)
//				{
//					textview_toast.setText(((UserInfo_data)msg.obj).message);
//				}
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HanderManager.db_error:
//				textview_toast.setText(getString(R.string.db_error));
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HanderManager.error:
//				textview_toast.setText(getString(R.string.no_error));
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HanderManager.success1:
//				int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
//				String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//				BeikBankApi.getInstance().getBankListInfo(getApplicationContext(),version,
//						getBankListInfoHandler);
//				
//				Intent intent = new Intent(RegisterPwdSetActivity.this, HomeActivity2.class); 
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.DO_SUCCESS1,BeikBankConstant.DO_SUCCESS1_VALUE);
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,0);
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//						phonenumber);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//				if(intflag==2||intflag==3){
//					//不需要toast
//				}else{
//					CustomToast.makeText(RegisterPwdSetActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//				}
//				break;	
//			}
//			
//		}	
//	};
//	JsonHttpResponseHandler getPasswordInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialog(RegisterPwdSetActivity.this, null);
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
//				if(isForgetLoginPassword){
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//							phonenumber);
//					Intent intent=new Intent(RegisterPwdSetActivity.this,LoginPwdInputActivity.class);
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//
//				}else{
//					String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//					BeikBankApi.getInstance().getBankListInfo(getApplicationContext(),version,
//							getBankListInfoHandler);
//					UserInfo userInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//							BeikBankConstant.TYPE_JSONDATA), UserInfo.class);
//					//UserInfoDao.getInstance(RegisterPwdSetActivity.this).deleteAll();
//					//UserInfoDao.getInstance(RegisterPwdSetActivity.this).insertUserInfo(userInfo);
//					TableDaoSimple.delete(UserInfo.class,null,null);
//					TableDaoSimple.insert(userInfo);
//					
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//							phonenumber);
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//							MD5.md5s32(clearedittext_password.getText().toString()));
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//							false);
//					BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//							LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//					BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//							LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//					
//					int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
//					
//					Intent intent = new Intent(RegisterPwdSetActivity.this, HomeActivity2.class); 
//					//BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//					//		phonenumber);
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//					if(intflag==2||intflag==3){
//						//不需要toast
//					}else{
//						CustomToast.makeText(RegisterPwdSetActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//					}
//				}
//			}else{
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//			}
//
//		}
//
//	};

//	JsonHttpResponseHandler getBankListInfoHandler = new JsonHttpResponseHandler(){
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
//
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Gson gson=new Gson();
//			BankListInfo bankListInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
//					BeikBankConstant.TYPE_JSONDATA), BankListInfo.class);
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				boolean hasUpdate=bankListInfo.isHasUpdate();
//				if(hasUpdate){
//					ArrayList<BankInfo> list=bankListInfo.getBankList();
//					BankInfoDao.getInstance(getApplicationContext()).deleteAll();
//					BankInfoDao.getInstance(getApplicationContext()).insertBankInfoList(list);	
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.VERSION, 
//							bankListInfo.getVersion());
//				}
//
//			}
//
//		}
//
//	};
//	

}
