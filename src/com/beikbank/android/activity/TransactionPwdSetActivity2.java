package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.beikbank.android.R;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.widget.ClearableEditText;
//import com.beikbank.android.widget.CustomToast;
//import com.nineoldandroids.animation.AnimatorSet;
//
////设置交易密码
//public class TransactionPwdSetActivity2 extends BaseActivity1 implements OnClickListener{
//
//	private final String TAG="TransactionPwdSetActivity";
//	private ClearableEditText clearedittext_transactionpassword,clearedittext_repeat_password;
//	private Button button_next;
//	private TextView titleTv,textview_toast;
//	private LinearLayout linear_toast;
//	private LinearLayout linear_left;
//
//	private AnimatorSet toastAnimSet;
//	private Dialog dialog;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_transactionpwd_set);
//		initView();
//
//	}
//	public void initView(){
//		toastAnimSet = new AnimatorSet();
//		titleTv = (TextView) findViewById(R.id.titleTv);
//		titleTv.setText(getString(R.string.setting_transaction_password));
//
//		linear_left = (LinearLayout) findViewById(R.id.linear_left);
//		linear_left.setVisibility(View.VISIBLE);
//		linear_left.setOnClickListener(this);
//
//		clearedittext_transactionpassword=(ClearableEditText)findViewById(R.id.clearedittext_transactionpassword);
//		clearedittext_transactionpassword.addTextChangedListener(new TextWatcherListener());
//		clearedittext_repeat_password=(ClearableEditText)findViewById(R.id.clearedittext_repeat_password);
//		clearedittext_repeat_password.addTextChangedListener(new TextWatcherListener());
//		button_next=(Button)findViewById(R.id.button_next);
//		button_next.setOnClickListener(this);
//
//		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
//		textview_toast=(TextView)findViewById(R.id.textview_toast);
//
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
//		}
//
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before,
//				int count) {
//			// TODO Auto-generated method stub
//			String transactionpassword=clearedittext_transactionpassword.getText().toString();
//			String repeat_password=clearedittext_repeat_password.getText().toString();
//			if(transactionpassword.length()>=6&&repeat_password.length()>=6
//					&&transactionpassword.length()==repeat_password.length()){
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
//			String transactionpassword=clearedittext_transactionpassword.getText().toString();
//			String repeat_password=clearedittext_repeat_password.getText().toString();
//			if(!transactionpassword.equals(repeat_password)){
//				clearedittext_repeat_password.setText("");
//				clearedittext_repeat_password.requestFocus();
//				textview_toast.setText("密码不一致！");
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//			}else{
//				//UserInfo userInfo=UserInfoDao.getInstance(TransactionPwdSetActivity2.this).getUserInfo();
//
//                UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//				BeikBankApi.getInstance().setTransactionPasswordInfo(TransactionPwdSetActivity2.this, 
//						userInfo.getId(), transactionpassword, handleTransactionPasswordInfoHandler);
//			}
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
//	}
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(this, pActClassName);
//		startActivity(_Intent);
//	}
//
//	JsonHttpResponseHandler handleTransactionPasswordInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//			dialog=Utils.createPorgressDialog(TransactionPwdSetActivity2.this, null);
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
//				CustomToast.makeText(TransactionPwdSetActivity2.this, "设置成功", Toast.LENGTH_SHORT).show();  
//				Intent intent = new Intent(TransactionPwdSetActivity2.this, RedeemConfirmActivity.class); 
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//
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
//}
