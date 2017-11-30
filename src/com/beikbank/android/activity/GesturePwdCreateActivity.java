package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.dataparam.ShoushiCreParam;
import com.beikbank.android.dataparam2.ShouShiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.LockPatternUtils;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.CustomToast;
import com.beikbank.android.widget.LockPatternView;
import com.beikbank.android.widget.LockPatternView.Cell;
import com.beikbank.android.widget.LockPatternView.DisplayMode;
import coma.beikbank.android.R;



//创建手势密码
public class GesturePwdCreateActivity extends BaseActivity implements OnClickListener{
	private static final int ID_EMPTY_MESSAGE = -1;
	private static final String KEY_UI_STAGE = "uiStage";
	private static final String KEY_PATTERN_CHOICE = "chosenPattern";
	private LinearLayout linear_left;
	private LockPatternView mLockPatternView;
	protected TextView mHeaderText,gesturepwd_create_error,titleTv;
	protected List<LockPatternView.Cell> mChosenPattern = null;
	protected List<LockPatternView.Cell> mGesturePattern = null;
	private Stage mUiStage = Stage.Introduction;
	private ImageView mPreviewViews[][] = new ImageView[3][3];
	protected enum Stage {

		Introduction(R.string.lockpattern_recording_intro_header,
				ID_EMPTY_MESSAGE, true), 
	    HelpScreen(
			   R.string.lockpattern_settings_help_how_to_record,
			   ID_EMPTY_MESSAGE, false), 
		ChoiceTooShort(
			   R.string.lockpattern_recording_incorrect_too_short,
			   ID_EMPTY_MESSAGE, true),
	    FirstChoiceValid(
			   R.string.lockpattern_pattern_entered_header,
			   ID_EMPTY_MESSAGE, false), 
		NeedToConfirm(
			  R.string.lockpattern_need_to_confirm,
			  ID_EMPTY_MESSAGE, true),
	    ConfirmWrong(
			  R.string.lockpattern_need_to_unlock_wrong,
			  ID_EMPTY_MESSAGE, true), 
		ChoiceConfirmed(
			  R.string.lockpattern_pattern_confirmed_header,
			 ID_EMPTY_MESSAGE, false);

		Stage(int headerMessage, int footerMessage,
				boolean patternEnabled) {
			this.headerMessage = headerMessage;
			this.footerMessage = footerMessage;
			this.patternEnabled = patternEnabled;
		}

		final int headerMessage;
		final int footerMessage;
		final boolean patternEnabled;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesturepwd_create);
		StateBarColor.init(this,0xffffffff);
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.gesture_password));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		mLockPatternView = (LockPatternView) this
				.findViewById(R.id.gesturepwd_create_lockview);
		mHeaderText = (TextView) findViewById(R.id.gesturepwd_create_text);
		gesturepwd_create_error = (TextView) findViewById(R.id.gesturepwd_create_error);
		mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
		mLockPatternView.setTactileFeedbackEnabled(true);

		initPreviewViews();
		if (savedInstanceState == null) {
			updateStage(Stage.Introduction);
		} else {
			// restore from previous state
			final String patternString = savedInstanceState
					.getString(KEY_PATTERN_CHOICE);
			if (patternString != null) {
				mChosenPattern = LockPatternUtils
						.stringToPattern(patternString);
			}
			updateStage(Stage.values()[savedInstanceState.getInt(KEY_UI_STAGE)]);
		}


	}

	//手势绘制上方九个小点
	private void initPreviewViews() {
		mPreviewViews = new ImageView[3][3];
		mPreviewViews[0][0] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_0);
		mPreviewViews[0][1] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_1);
		mPreviewViews[0][2] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_2);
		mPreviewViews[1][0] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_3);
		mPreviewViews[1][1] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_4);
		mPreviewViews[1][2] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_5);
		mPreviewViews[2][0] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_6);
		mPreviewViews[2][1] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_7);
		mPreviewViews[2][2] = (ImageView)findViewById(R.id.gesturepwd_setting_preview_8);
	}

	//动态更新九个小点的状态
	private void updatePreviewViews() {
		if (mGesturePattern == null)
			return;
		for (LockPatternView.Cell cell : mGesturePattern) {
			mPreviewViews[cell.getRow()][cell.getColumn()]
					.setBackgroundResource(R.drawable.ic_red_point);

		}
	}

	//九个小点复位
	private void resetPreviewViews() {
		for (LockPatternView.Cell cell : mGesturePattern) {
			mPreviewViews[cell.getRow()][cell.getColumn()]
					.setBackgroundResource(R.drawable.ic_gray_point);

		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_UI_STAGE, mUiStage.ordinal());
		if (mChosenPattern != null) {
			outState.putString(KEY_PATTERN_CHOICE,
					LockPatternUtils.patternToString(mChosenPattern));
		}
	}

	private Runnable mClearPatternRunnable = new Runnable() {
		public void run() {
			mLockPatternView.clearPattern();
		}
	};

	protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

		public void onPatternStart() {
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
			mHeaderText.setVisibility(View.VISIBLE);
			gesturepwd_create_error.setVisibility(View.GONE);
			patternInProgress();
		}

		public void onPatternCleared() {
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
			mHeaderText.setVisibility(View.VISIBLE);
			gesturepwd_create_error.setVisibility(View.GONE);
		}

		public void onPatternDetected(List<LockPatternView.Cell> pattern) {
			if (pattern == null)
				return;
			if (mUiStage == Stage.NeedToConfirm
					|| mUiStage == Stage.ConfirmWrong) {
				if (mChosenPattern == null)
					throw new IllegalStateException(
							"null chosen pattern in stage 'need to confirm");
				if (mChosenPattern.equals(pattern)) {
					updateStage(Stage.ChoiceConfirmed);
				} else {
					updateStage(Stage.ConfirmWrong);
				}
			} else if (mUiStage == Stage.Introduction
					|| mUiStage == Stage.ChoiceTooShort) {
				if (pattern.size() < LockPatternUtils.MIN_LOCK_PATTERN_SIZE) {
					updateStage(Stage.ChoiceTooShort);
				} else {
					mChosenPattern = new ArrayList<LockPatternView.Cell>(
							pattern);
					updateStage(Stage.FirstChoiceValid);
				}
			} else {
				throw new IllegalStateException("Unexpected stage " + mUiStage
						+ " when " + "entering the pattern.");
			}
		}

		public void onPatternCellAdded(List<Cell> pattern) {
			mGesturePattern = new ArrayList<LockPatternView.Cell>(
					pattern);
			updatePreviewViews();
		}

		private void patternInProgress() {
			mHeaderText.setText(R.string.lockpattern_recording_inprogress);
		}
	};

	private void updateStage(Stage stage) {
		mUiStage = stage;
		if (stage == Stage.ChoiceTooShort) {
			mHeaderText.setVisibility(View.GONE);
			gesturepwd_create_error.setVisibility(View.VISIBLE);
			gesturepwd_create_error.setText(getResources().getString(stage.headerMessage,
					LockPatternUtils.MIN_LOCK_PATTERN_SIZE));

		} else if(stage == Stage.ConfirmWrong){
			mHeaderText.setVisibility(View.GONE);
			gesturepwd_create_error.setVisibility(View.VISIBLE);
			gesturepwd_create_error.setText(R.string.lockpattern_need_to_unlock_wrong);
		}else {
			mHeaderText.setText(stage.headerMessage);
		}

		if (stage.patternEnabled) {
			mLockPatternView.enableInput();
		} else {
			mLockPatternView.disableInput();
		}

		mLockPatternView.setDisplayMode(DisplayMode.Correct);

		switch (mUiStage) {
		case Introduction:
			mLockPatternView.clearPattern();
			break;
		case ChoiceTooShort:
			mLockPatternView.setDisplayMode(DisplayMode.Wrong);
			resetPreviewViews();
			postClearPatternRunnable();
			break;
		case FirstChoiceValid:
			updateStage(Stage.NeedToConfirm);
			break;
		case NeedToConfirm:
			//需要再一次输入手势密码，也就是第二次
			mLockPatternView.clearPattern();
			resetPreviewViews();
			break;
		case ConfirmWrong:
			//第二次确认手势密码输错
			mLockPatternView.setDisplayMode(DisplayMode.Wrong);
			resetPreviewViews();
			postClearPatternRunnable();
			break;
		case ChoiceConfirmed:
			
			String passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(mChosenPattern);
			saveShoushi(passwd);
			//两次手势密码输入都是正确的
			//saveChosenPatternAndFinish();
			//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_CREATE_GRESTURE,true);
			break;
		}

	}
	/**
	 * 储存手势密码
	 */
    private void saveShoushi(String passwd)
    {   
              ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					
				
				// TODO Auto-generated method stub
				saveChosenPatternAndFinish();
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_CREATE_GRESTURE,true);
				}
				}
		};
    	
    	ShouShiParam sp=new ShouShiParam();
    	sp.gesture_password=MD5.md5s32(passwd);
    	sp.operation_type="2";
    	sp.password_type="0";
    	sp.start_gesture_password="1";
    	sp.user_code=BeikBankApplication.getUserCode();
    	TongYongManager2 tm2=new TongYongManager2(this, icb,sp);
    	tm2.start();
    	
    	
    	
//    	
//    	ICallBack icb=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				if(obj!=null)
//				{
//					
//				
//				// TODO Auto-generated method stub
//				saveChosenPatternAndFinish();
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_CREATE_GRESTURE,true);
//				}
//				}
//		};
//		ShoushiCreParam scp=new ShoushiCreParam();
//		scp.userId=BeikBankApplication.getUserid();
//		scp.type="2";
//		scp.passWord=passwd;
//    	TongYongManager tym=new TongYongManager(this, icb,scp);
//    	tym.start();
    }
	private void postClearPatternRunnable() {
		mLockPatternView.removeCallbacks(mClearPatternRunnable);
		mLockPatternView.postDelayed(mClearPatternRunnable, 1800);
	}

	//当两次手势密码输入都是一致后，在这里保存一些操作
	private void saveChosenPatternAndFinish() {
		BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
				5);
		//BeikBankApplication.getInstance().getLockPatternUtils().saveLockPattern(mChosenPattern);
		String passwd=BeikBankApplication.getInstance().getLockPatternUtils().patternToString(mChosenPattern);
		String id=BeikBankApplication.getUserCode();
		BeikBankApplication.mSharedPref.putSharePrefString("passwd"+id,passwd);
		
		
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
				true);
//		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_CREATE_GRESTURE, 
//				true);
		CustomToast.makeText(GesturePwdCreateActivity.this, "设置成功", Toast.LENGTH_SHORT).show();  
		new Handler().postDelayed(new Runnable() {  
			public void run() { 
				//Intent intent = new Intent(GesturePwdCreateActivity.this, HomeActivity2.class); 
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//startActivity(intent);
				finish();
			}  

		}, 1000); 
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

	@Override
	protected void onRestart() {
	
		super.onRestart();
		resetPreviewViews();
	}



}
