package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.setJiaoyiPasswd_data;
import com.beikbank.android.dataparam2.setJiaoyiPasswdParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.SetTPasswdManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;


//设置交易密码
public class TransactionPwdSetActivity extends BaseActivity1 implements OnClickListener{
	
	private final String TAG="TransactionPwdSetActivity";
	private ClearableEditText clearedittext_transactionpassword,clearedittext_repeat_password;
	private Button button_next;
	private TextView titleTv,textview_toast;
	private LinearLayout linear_toast;
	private LinearLayout linear_left;
	
	private AnimatorSet toastAnimSet;
	private Dialog dialog;
	private boolean isForgetTransactionPwd;
    Activity act=this;
    boolean is_nextpage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transactionpwd_set);
		StateBarColor.init(this,0xffffffff);
		initView();
	    is_nextpage=getIntent().getBooleanExtra("is_nextpage",false);
	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.setting_transaction_password));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		clearedittext_transactionpassword=(ClearableEditText)findViewById(R.id.clearedittext_transactionpassword);
		clearedittext_transactionpassword.addTextChangedListener(new TextWatcherListener());
		clearedittext_repeat_password=(ClearableEditText)findViewById(R.id.clearedittext_repeat_password);
		clearedittext_repeat_password.addTextChangedListener(new TextWatcherListener());
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		
		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		
		isForgetTransactionPwd=getIntent().getBooleanExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
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
			String transactionpassword=clearedittext_transactionpassword.getText().toString();
			String repeat_password=clearedittext_repeat_password.getText().toString();
			if(transactionpassword.length()>=6&&repeat_password.length()>=6
					&&transactionpassword.length()==repeat_password.length()){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}
    private void setOrUadate(String old,String passwd,String type)
    {
    	String code=BeikBankApplication.getSharePref(BeikBankConstant.user_code);
		
		
		setJiaoyiPasswdParam pp=new setJiaoyiPasswdParam();
		if("0".equals(type))
		{
			pp.new_password=MD5.md5s32(passwd);
			pp.type="0";
		}
		else if("2".equals(type))
		{
			pp.new_password=MD5.md5s32(passwd);
			pp.type="2";
		}
	
		pp.ori_password="";
		
		pp.user_code=code;
		TongYongManager2 tym2=new TongYongManager2(act, icb1,pp);
    	tym2.start();
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			String transactionpassword=clearedittext_transactionpassword.getText().toString();
			String repeat_password=clearedittext_repeat_password.getText().toString();
			if(!transactionpassword.equals(repeat_password)){
				clearedittext_repeat_password.setText("");
				clearedittext_repeat_password.requestFocus();
				showToast("密码不一致！");
				//textview_toast.setText("密码不一致！");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
			}else{
				
//				 UserInfo userInfo=BeikBankApplication.getUserInfo();
//				if(userInfo!=null)
//				{
//					new SetTPasswdManager(act, userInfo.getId(), transactionpassword, icb).start();
//				}
				if(isForgetTransactionPwd)
				{
					setOrUadate("",transactionpassword,"2");
				}
				else
				{
					setOrUadate("",transactionpassword,"0");
				}
				
			}
			break;
		case R.id.linear_left:
			if(is_nextpage)
			{
				createDialog2();
			}
			else
			{
				finish();
			}
			break;
		}
	}
  private ICallBack icb1=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			setJiaoyiPasswd_data sjp=(setJiaoyiPasswd_data) obj;
			if("0000".equals(sjp.header.re_code))
			{
//				    Intent  intent = new Intent(TransactionPwdSetActivity.this,HomeActivity4.class); 
//				    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
				BeikBankApplication.setSharePref(BeikBankConstant.is_jiaoyi,"1");
				CustomToast.makeText(TransactionPwdSetActivity.this, "设置成功", Toast.LENGTH_SHORT).show();  
				
				finish();
			}
			
		}
		
	}
};	
	
	
	
   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
	if(obj!=null)
	{
		if(isForgetTransactionPwd)
		{
//			CustomToast.makeText(TransactionPwdSetActivity.this, "设置成功", Toast.LENGTH_SHORT).show();  
//			Intent intent = new Intent(TransactionPwdSetActivity.this, PurchaseConfirmActivity.class); 
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
			finish();
		}else
		{
			//UserInfo userInfo=UserInfoDao.getInstance(TransactionPwdSetActivity.this).getUserInfo();
			 UserInfo userInfo=BeikBankApplication.getUserInfo();
			userInfo.setHasSetPaypassword(true);
		//	UserInfoDao.getInstance(TransactionPwdSetActivity.this).deleteAll();
			//UserInfoDao.getInstance(TransactionPwdSetActivity.this).insertUserInfo(userInfo);

           TableDaoSimple.delete(UserInfo.class,null,null);
		   //TableDaoSimple.insert(userInfo);
			UserInfoDao.setUserInfo(userInfo);
		    Intent intent=getIntent();
		    boolean is_nextpage=intent.getBooleanExtra("is_nextpage",false);
		    if(!is_nextpage)
		    {
		    	finish();
		    	return;
		    }
		    
		    intent = new Intent(TransactionPwdSetActivity.this,HomeActivity3.class); 
		    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		
		}
	    finish();
	}
	}
};  

Dialog dialog4;
//back
public void createDialog2()
{     
	   LinearLayout ll=new LinearLayout(this);
	   View v=LayoutInflater.from(this).inflate(
  			   R.layout.redeem_dialog4,ll,false);
	   TextView tv1=(TextView) v.findViewById(R.id.tv_tv1);
	   TextView tv2=(TextView) v.findViewById(R.id.tv_tv2);
	   
    	//cacle
    	TextView tv4=(TextView) v.findViewById(R.id.tv_tv4);
    	tv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog4.dismiss();
			}
		});
    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
    	tv5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        dialog4=DialogManager.getDiaolg1(this, v);
    	dialog4.show();
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
//	
//	JsonHttpResponseHandler handleTransactionPasswordInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialog(TransactionPwdSetActivity.this, null);
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
//				
//				if(isForgetTransactionPwd){
//					CustomToast.makeText(TransactionPwdSetActivity.this, "设置成功", Toast.LENGTH_SHORT).show();  
//					Intent intent = new Intent(TransactionPwdSetActivity.this, PurchaseConfirmActivity.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//				}else{
//					//UserInfo userInfo=UserInfoDao.getInstance(TransactionPwdSetActivity.this).getUserInfo();
//					UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//					userInfo.setHasSetPaypassword(true);
//				//	UserInfoDao.getInstance(TransactionPwdSetActivity.this).deleteAll();
//					//UserInfoDao.getInstance(TransactionPwdSetActivity.this).insertUserInfo(userInfo);
//
//                TableDaoSimple.delete(UserInfo.class,null,null);
//				TableDaoSimple.insert(userInfo);
//					
//					Intent intent = new Intent(TransactionPwdSetActivity.this, PurchaseActivity.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
