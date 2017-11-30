package com.beikbank.android.activity;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.dataparam.ShoushiCreParam;
import com.beikbank.android.dataparam.ShoushiIsParam;
import com.beikbank.android.dataparam2.CheckShouShiParam;
import com.beikbank.android.dataparam2.ShouShiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.LockPatternUtils;
import com.beikbank.android.utils.LoginManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.LockPatternView;
import com.beikbank.android.widget.LockPatternView.Cell;
import coma.beikbank.android.R;



//手势解锁
public class GesturePwdUnlockActivity extends BaseActivity implements OnClickListener{
	private LockPatternView mLockPatternView;
	private int mFailedPatternAttemptsSinceLastTimeout = 0;
	private TextView textview_change_account,textview_welcomeback,
	textview_forget_password,textview_phonenumber;
	private String phonenumber;
	private long exitTime = 0;
	private int retry_total=0;
	private int retry;
	private Dialog change_account_dialog;
	private Dialog forget_password_dialog;
	private Dialog gesture_error_dialog;
    Activity act=this;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesturepwd_unlock);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		//retry_total=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.GESTURE_RETRY);
		retry_total=LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT2;
		mLockPatternView = (LockPatternView) this
				.findViewById(R.id.gesturepwd_unlock_lockview);
		mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
		mLockPatternView.setTactileFeedbackEnabled(true);

		textview_phonenumber=(TextView)findViewById(R.id.textview_phonenumber);
		textview_change_account=(TextView)findViewById(R.id.textview_change_account);
		textview_change_account.setOnClickListener(this);
		textview_forget_password=(TextView)findViewById(R.id.textview_forget_password);
		textview_forget_password.setOnClickListener(this);

		phonenumber=getIntent().getStringExtra(BeikBankConstant.INTENT_PHONENUMBER);
		textview_phonenumber.setText(Utils.getEncryptedPhonenumber(phonenumber));
		textview_welcomeback=(TextView)findViewById(R.id.textview_welcomeback);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	private Runnable mClearPatternRunnable = new Runnable() {
		public void run() {
			mLockPatternView.clearPattern();
		}
	};

	protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

		public void onPatternStart() {
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
			patternInProgress();
		}

		public void onPatternCleared() {
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
		}

		public void onPatternDetected(List<LockPatternView.Cell> pattern) {
			if (pattern == null)
				return;
			
			
			
			String passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(pattern);
			shoushiIs(passwd);
			return;
//			String id=BeikBankApplication.getUserid();
//			String passwd_old=BeikBankApplication.mSharedPref.getSharePrefString("passwd"+id);
//			
//			//BeikBankApplication.getInstance().getLockPatternUtils().checkPattern(pattern)
//				
//			if (passwd.equals(passwd_old)) {
//				//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//				//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//				//startAimActivity(HomeActivity2.class);
//				BaseActivity.isUnLock=true;
//				finish();
//			} else {
//				mFailedPatternAttemptsSinceLastTimeout++;
//				retry =retry_total- mFailedPatternAttemptsSinceLastTimeout;
//				if (retry >= 0) {
//					if (retry == 0){
//						mLockPatternView.postDelayed(mClearPatternRunnable, 0);
//						//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//						//		retry);
////						BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
////								false);
////						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
////								"");
////						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
////								"");
////						//UserInfoDao.getInstance(GesturePwdUnlockActivity.this).deleteAll();
////                        TableDaoSimple.delete(UserInfo.class,null,null);
//						//LoginManager.outLogin();
//						gesture_error_dialog=Utils.createConfirmDialog(GesturePwdUnlockActivity.this,new BeikBankDialogListener() {
//
//							@Override
//							public void onRightBtnClick() {
//								// TODO Auto-generated method stub
//								LoginManager.outLogin(act,0);
//								BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//								BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);
//								Intent intent=new Intent(GesturePwdUnlockActivity.this,GesturePwdLoginActivity.class);
//								intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
//								startActivity(intent);
//								finish();
//							}
//
//							@Override
//							public void onListItemLongClick(int position, String string) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onListItemClick(int position, String string) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onLeftBtnClick() {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onCancel() {
//								// TODO Auto-generated method stub
//
//							}
//						});
//						gesture_error_dialog.show();
//
//					}else{
//						//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
//						//		retry);
//						String pwderrorFormat = getResources().getString(R.string.password_error_times);
//						String error_times = String.format(pwderrorFormat, retry); 
//						textview_welcomeback.setText(error_times);
//						mLockPatternView.postDelayed(mClearPatternRunnable, 1800);
//					}
//				}
//
//
//			}
		}

		public void onPatternCellAdded(List<Cell> pattern) {

		}

		private void patternInProgress() {
		}
	};

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}
	
	/**
	 * 关闭手势密码
	 */
	private void colse()
	{
		
		
		
		
		
		
//		ShoushiCreParam scp=new ShoushiCreParam();
//		scp.passWord="1";
//		scp.type="2";
//		scp.userId=BeikBankApplication.getUserid();
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					LoginManager.outLogin(act,0);
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);
					Intent intent=new Intent(GesturePwdUnlockActivity.this,GesturePwdLoginActivity.class);
					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
					startActivity(intent);
					finish();
				}
			}
		};
//		TongYongManager tym=new TongYongManager(this, icb,scp);
//		
//		tym.start();
		
		
		
		
		ShouShiParam sp=new ShouShiParam();
    	
    	sp.operation_type="2";
    	sp.password_type="0";
    	sp.start_gesture_password="0";
    	sp.user_code=BeikBankApplication.getUserCode();
    	TongYongManager2 tm2=new TongYongManager2(this, icb,sp);
    	tm2.start();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.textview_forget_password:	
			forget_password_dialog=Utils.createSimpleDialog(GesturePwdUnlockActivity.this,
					getString(R.string.forget_password_text),getString(R.string.reset_login),new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub
					//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
					//		retry);
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//							false);
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//							"");
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//							"");
//					//UserInfoDao.getInstance(GesturePwdUnlockActivity.this).deleteAll();
//					TableDaoSimple.delete(UserInfo.class,null,null);
//					LoginManager.outLogin(act,0);
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);
//					Intent intent=new Intent(GesturePwdUnlockActivity.this,GesturePwdLoginActivity.class);
//					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
//					startActivity(intent);
//					finish();
					colse();
				}

				@Override
				public void onListItemLongClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onLeftBtnClick() {
					// TODO Auto-generated method stub
					forget_password_dialog.dismiss();
				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub

				}
			});
			forget_password_dialog.show();
			
			
			break;
		case R.id.textview_change_account:
			change_account_dialog=Utils.createSimpleDialog(GesturePwdUnlockActivity.this,
					getString(R.string.change_account_text),getString(R.string.ok2),new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub
					startAimActivity(LoginRegActivity.class);
					LoginManager.outLogin(act,0);
					//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
					//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);
					finish();
				}

				@Override
				public void onListItemLongClick(int position, String string) {
					

				}

				@Override
				public void onListItemClick(int position, String string) {
					

				}

				@Override
				public void onLeftBtnClick() {
					change_account_dialog.dismiss();
				}

				@Override
				public void onCancel() {
				}
			});
			       change_account_dialog.show();

			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;   
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}

	}
	private void set(boolean is)
	{
		//String passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(pattern);
		//String id=BeikBankApplication.getUserid();
		//String passwd_old=BeikBankApplication.mSharedPref.getSharePrefString("passwd"+id);
		
		//BeikBankApplication.getInstance().getLockPatternUtils().checkPattern(pattern)
			
		if (is) 
		{
			//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
			//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
			//startAimActivity(HomeActivity2.class);
			BaseActivity.isUnLock=true;
			finish();
		} 
		else 
		{
			mFailedPatternAttemptsSinceLastTimeout++;
			retry =retry_total- mFailedPatternAttemptsSinceLastTimeout;
			if (retry >= 0) {
				if (retry == 0){
					mLockPatternView.postDelayed(mClearPatternRunnable, 0);
					//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
					//		retry);
//					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//							false);
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//							"");
//					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//							"");
//					//UserInfoDao.getInstance(GesturePwdUnlockActivity.this).deleteAll();
//                    TableDaoSimple.delete(UserInfo.class,null,null);
					//LoginManager.outLogin();
					gesture_error_dialog=Utils.createConfirmDialog(GesturePwdUnlockActivity.this,new BeikBankDialogListener() {

						@Override
						public void onRightBtnClick() {
							// TODO Auto-generated method stub
							LoginManager.outLogin(act,0);
							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);
							Intent intent=new Intent(GesturePwdUnlockActivity.this,GesturePwdLoginActivity.class);
							intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
							startActivity(intent);
							finish();
						}

						@Override
						public void onListItemLongClick(int position, String string) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onListItemClick(int position, String string) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onLeftBtnClick() {
							// TODO Auto-generated method stub

						}

						@Override
						public void onCancel() {
							// TODO Auto-generated method stub

						}
					});
					gesture_error_dialog.show();

				}else
				{
					//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
					//		retry);
					String pwderrorFormat = getResources().getString(R.string.password_error_times);
					String error_times = String.format(pwderrorFormat, retry); 
					textview_welcomeback.setText(error_times);
					mLockPatternView.postDelayed(mClearPatternRunnable, 1800);
				}
			}
		}
	}
	/**
	 * 手势密码是否正确
	 */
    private void shoushiIs(String passwd)
    {     
    	  ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					set(true);
				}
				else
				{
					set(false);
				}
			}
		};
//		ShoushiIsParam sp=new ShoushiIsParam();
//		sp.passWord=passwd;
//		sp.type="2";
//		sp.userId=BeikBankApplication.getUserid();
//    	  TongYongManager tym=new TongYongManager(act, icb,sp);
//    	  tym.start();
    	  
    	  
    	  CheckShouShiParam cp=new CheckShouShiParam();
    	  cp.user_code=BeikBankApplication.getUserCode();
    	  cp.gesture_password=MD5.md5s32(passwd);
    	  TongYongManager2 tym2=new TongYongManager2(act, icb,cp);
    	  tym2.start();
    }
}
