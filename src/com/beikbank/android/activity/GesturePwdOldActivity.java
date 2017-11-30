package com.beikbank.android.activity;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.UserInfo;
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



//旧的手势密码
public class GesturePwdOldActivity extends BaseActivity implements OnClickListener{

	private LockPatternView mLockPatternView;
	private int mFailedPatternAttemptsSinceLastTimeout = 0;
	private TextView titleTv,gesturepwd_create_text,gesturepwd_create_error;
	private LinearLayout linear_left;
	private String phonenumber;
	private int old_total=0;
	private int retry;
	private Dialog gesture_error_dialog;
	private boolean ischangeGesture;
    Activity act=this;
    String passwd;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesturepwd_old);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.gesture_password2));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		gesturepwd_create_text=(TextView)findViewById(R.id.gesturepwd_create_text);
		gesturepwd_create_error=(TextView)findViewById(R.id.gesturepwd_create_error);

		//old_total=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.GESTURE_OLD);
		old_total=LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT2;
		mLockPatternView = (LockPatternView)this.findViewById(R.id.gesturepwd_unlock_lockview);
		mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
		mLockPatternView.setTactileFeedbackEnabled(true);

		ischangeGesture=getIntent().getBooleanExtra(BeikBankConstant.INTENT_CHANGEGESTURE, false);

		phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);

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
			passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(pattern);
			shoushiIs(passwd);
//			
//			String passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(pattern);
//			String id=BeikBankApplication.getUserid();
//			String passwd_old=BeikBankApplication.mSharedPref.getSharePrefString("passwd"+id);
//			//BeikBankApplication.getInstance().getLockPatternUtils().checkPattern(pattern)
//			
//			if (passwd.equals(passwd_old)) {
//				mLockPatternView.postDelayed(mClearPatternRunnable, 0);
//				//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//				//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//				mFailedPatternAttemptsSinceLastTimeout=0;
//				//改变手势密码
//				if(ischangeGesture){
//					startAimActivity(GesturePwdCreateActivity.class);
//					finish();
//				}
//				//关闭手势密码
//				else
//				{
////					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
////							false);
////					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
////							false);
////					finish();
//					colse();
//				}
//
//			} else {
//				mFailedPatternAttemptsSinceLastTimeout++;
//				retry =old_total- mFailedPatternAttemptsSinceLastTimeout;
//				if (retry >= 0) {
//					if (retry == 0){
//						mLockPatternView.postDelayed(mClearPatternRunnable, 0);
//						//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//						//		retry);
//						BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//								false);
//						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
//								"");
//						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
//								"");
//						//UserInfoDao.getInstance(GesturePwdOldActivity.this).deleteAll();
//						TableDaoSimple.delete(UserInfo.class,null,null);
//						gesture_error_dialog=Utils.createConfirmDialog(GesturePwdOldActivity.this,new BeikBankDialogListener() {
//
//							@Override
//							public void onRightBtnClick() {
//								// TODO Auto-generated method stub
//								LoginManager.outLogin(act,0);
//								BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//								Intent intent=new Intent(GesturePwdOldActivity.this,GesturePwdLoginActivity.class);
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
//
//
//					}else{
//						//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
//						//		retry);
//						String pwderrorFormat = getResources().getString(R.string.password_error_times);
//						String error_times = String.format(pwderrorFormat, retry); 
//						gesturepwd_create_text.setVisibility(View.GONE);
//						gesturepwd_create_error.setVisibility(View.VISIBLE);
//						gesturepwd_create_error.setText(error_times);
//
//						mLockPatternView.postDelayed(mClearPatternRunnable, 1800);
//					}
//				}
//

//			}
		}
	

		public void onPatternCellAdded(List<Cell> pattern) {

		}

		private void patternInProgress() {

		}
	};
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
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
							false);
					finish();
				}
			}
		};
//		TongYongManager tym=new TongYongManager(this, icb,scp);
//		
//		tym.start();
		
		ShouShiParam sp=new ShouShiParam();
		
		
		sp.operation_type="1";
		sp.password_type="0";
		sp.start_fingerprint_password="0";
		sp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb,sp);
		tym2.start();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;

		}
	}

	private void set(boolean is)
	{
		//String passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(pattern);
		//String id=BeikBankApplication.getUserid();
		//String passwd_old=BeikBankApplication.mSharedPref.getSharePrefString("passwd"+id);
		
		//BeikBankApplication.getInstance().getLockPatternUtils().checkPattern(pattern)
			
		if (is) {
			mLockPatternView.postDelayed(mClearPatternRunnable, 0);
			//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
			//		LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
			mFailedPatternAttemptsSinceLastTimeout=0;
			//改变手势密码
			if(ischangeGesture){
				startAimActivity(GesturePwdCreateActivity.class);
				finish();
			}
			//关闭手势密码
			else
			{
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//						false);
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//						false);
//				finish();
				colse();
			}

		} else {
			mFailedPatternAttemptsSinceLastTimeout++;
			retry =old_total- mFailedPatternAttemptsSinceLastTimeout;
			if (retry >= 0) {
				if (retry == 0){
					mLockPatternView.postDelayed(mClearPatternRunnable, 0);
					//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
					//		retry);
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
							false);
					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
							"");
					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
							"");
					//UserInfoDao.getInstance(GesturePwdOldActivity.this).deleteAll();
					TableDaoSimple.delete(UserInfo.class,null,null);
					gesture_error_dialog=Utils.createConfirmDialog(GesturePwdOldActivity.this,new BeikBankDialogListener() {

						@Override
						public void onRightBtnClick() {
							// TODO Auto-generated method stub
							LoginManager.outLogin(act,0);
							BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
							Intent intent=new Intent(GesturePwdOldActivity.this,GesturePwdLoginActivity.class);
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



				}else{
					//BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
					//		retry);
					String pwderrorFormat = getResources().getString(R.string.password_error_times);
					String error_times = String.format(pwderrorFormat, retry); 
					gesturepwd_create_text.setVisibility(View.GONE);
					gesturepwd_create_error.setVisibility(View.VISIBLE);
					gesturepwd_create_error.setText(error_times);

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
