package com.beikbank.android.activity;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.CheckAndSend;
import com.beikbank.android.data.CheckAndSend_data;
import com.beikbank.android.data.PhoneQinqiu_Data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.PhoneQinqiuParam;
import com.beikbank.android.dataparam.PhoneQuerenParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckAndSendManager;
import com.beikbank.android.net.impl.CheckCodeManager3;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DataCheck;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author yuguohe
 *         <p>
 *         email:1374405188@qq.com
 *         </p>
 * 
 *         修改银行预留手机号
 */
public class UpdatePhoneActivity extends BaseActivity1 implements
		OnClickListener, OnFocusChangeListener {
	TextView tv;
	// 返回
	LinearLayout ll, linear_reload_identify_code;
	// 手机号，验证码
	private ClearableEditText cet1, cet2;
	// 下一步
	Button button;
	// 验证码
	private String code;
	// 手机号，验证码编号，
	private String phonenumber, vertifyid;
	private final int TOTALTIME = 60 * 1000;
	private final int COUNTDOWNINTERVAL = 1000;// 间隔1秒
	TextView textview_get_identifying_code, textview_identifying_code_timer;

	private MyCount timer;
	Activity act = this;
	boolean is_nextpage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_phone);
		StateBarColor.init(this, 0xffffffff);
		init();
		is_nextpage = getIntent().getBooleanExtra("is_nextpage", false);
	}

	private void init() {
		ll_error = (LinearLayout) findViewById(R.id.ll_error);
		tv_error = (TextView) findViewById(R.id.tv_error);

		ll = (LinearLayout) findViewById(R.id.linear_left);
		tv = (TextView) findViewById(R.id.titleTv);
		tv.setText(getString(R.string.test10));
		cet1 = (ClearableEditText) findViewById(R.id.ed_ed1);
		cet2 = (ClearableEditText) findViewById(R.id.ed_ed2);
		button = (Button) findViewById(R.id.button_next);
		button.setText("确定");
		linear_reload_identify_code = (LinearLayout) findViewById(R.id.linear_reload_identify_code);
		textview_get_identifying_code = (TextView) findViewById(R.id.textview_get_identifying_code);
		textview_identifying_code_timer = (TextView) findViewById(R.id.textview_identifying_code_timer);

		cet1.setOnFocusChangeListener(this);
		cet2.setOnFocusChangeListener(this);
		cet1.addTextChangedListener(new TextWatcherListener());
		cet2.addTextChangedListener(new TextWatcherListener());

		button.setOnClickListener(this);
		ll.setOnClickListener(this);
		textview_get_identifying_code.setOnClickListener(this);
		
		
		TextView tv_bank=(TextView) findViewById(R.id.tv_bank);
		
		 UserInfo userInfo=BeikBankApplication.getUserInfo();
			if(userInfo.isHasBindCard()){
		cardInfo=CardInfoDao.getCardInfo();
		//BankInfo bankInfo=BankInfoDao.getInstance(PurchaseSuccessActivity.this).getBankInfoByType(cardInfo.getType());
        if(cardInfo!=null&&cardInfo.type!=null)
        {
		   BankList bl=BankListDao.getBankByType(cardInfo.getType());
		   if(bl!=null)
		   {
	    	String cardNumber=cardInfo.getCardNumber();
		    tv_bank.setText(bl.bankName
				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
		   }
        }
			}
	}
	CardInfo cardInfo;
	@Override
	public void onClick(View v) {
		UserInfo ui = BeikBankApplication.getUserInfo();
		switch (v.getId()) {
		case R.id.button_next:
			phonenumber = cet1.getText().toString();
			String code2 = cet2.getText().toString();
//			if (code == null) {
//				showToast("验证码错误");
//				return;
//			}

			// UserInfo ui=(UserInfo)
			// TableDaoSimple.queryone(UserInfo.class,null,null);
//			CheckCodeManager3 ccm = new CheckCodeManager3(act, icb2);
//			ccm.init(ui.id, phonenumber, vertifyid, code2);
//			ccm.start();
			PhoneQuerenParam pq=new PhoneQuerenParam();
			pq.memberID=BeikBankApplication.getUserid();
			pq.verifiId=vertifyid;
			pq.verifiCode=code2;
			TongYongManager tym=new TongYongManager(act, icb2,pq);
			tym.start();
			break;

		case R.id.linear_left:
			if (is_nextpage) {
				createDialog2();
			} else {
				finish();
			}
			break;
		case R.id.textview_get_identifying_code:
			phonenumber = cet1.getText().toString();
			if (phonenumber == null || "".equals(phonenumber)) {
				showToast("手机号不能为空");
				return;
			}
			boolean is = DataCheck.checkPhone(phonenumber);
			if (!is) {
				showToast(getString(R.string.error_6));
				return;
			}
//			CheckAndSendManager casm = new CheckAndSendManager(act, icb);
//			// String id=BeikBankApplication.getUserid();
//			// String phone=BeikBankApplication.getPhoneNumber();
//			casm.init(ui.id, phonenumber);
//			casm.start();
			if(cardInfo!=null)
			{
				PhoneQinqiuParam pqp=new PhoneQinqiuParam();
				pqp.memberID=ui.id;
				pqp.phonenumber=phonenumber;
				pqp.cardNumber=cardInfo.cardNumber;
				TongYongManager tym1=new TongYongManager(act, icb,pqp);
				tym1.start();
			}
		
			
			break;

		}

	}

	ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				finish();
			}
		}
	};
	ICallBack icb = new ICallBack() {
		@Override
		public void back(Object obj) {
			if (obj != null) {
                PhoneQinqiu_Data pd=(PhoneQinqiu_Data) obj;
                
                
//				CheckAndSend_data casd = (CheckAndSend_data) obj;
//				CheckAndSend cas = casd.data;
//				code = cas.code;
				vertifyid = pd.data.verificodeId;
				if (SystemConfig.isDebug()) {
					//Log.e("code", code);
					Log.e("vertifyid", vertifyid);
				}
				timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
				timer.start();
			}

		}
	};

	Dialog dialog4;

	// back
	public void createDialog2() {
		LinearLayout ll = new LinearLayout(this);
		View v = LayoutInflater.from(this).inflate(R.layout.redeem_dialog4, ll,
				false);
		TextView tv1 = (TextView) v.findViewById(R.id.tv_tv1);
		TextView tv2 = (TextView) v.findViewById(R.id.tv_tv2);

		// cacle
		TextView tv4 = (TextView) v.findViewById(R.id.tv_tv4);
		tv4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog4.dismiss();
			}
		});
		TextView tv5 = (TextView) v.findViewById(R.id.tv_tv5);
		tv5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		dialog4 = DialogManager.getDiaolg1(this, v);
		dialog4.show();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {

	}

	class TextWatcherListener implements TextWatcher {
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
			String number = cet1.getText().toString();
			String code = cet2.getText().toString();
			if (number.length() == 11 && code.length() > 0) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		}

	}

	// 实现计时功能的类
	class MyCount extends AdvancedCountdownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			textview_get_identifying_code.setVisibility(View.GONE);
			linear_reload_identify_code.setVisibility(View.VISIBLE);
			ForegroundColorSpan fcs = new ForegroundColorSpan(getResources()
					.getColor(R.color.red1));
			SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME
					/ COUNTDOWNINTERVAL));
			ssb.setSpan(fcs, 0, ssb.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			textview_identifying_code_timer.setText(ssb);
			textview_identifying_code_timer
					.append(getString(R.string.identify_code_reload));
		}

		@Override
		public void onFinish() {

			linear_reload_identify_code.setVisibility(View.GONE);
			textview_get_identifying_code.setVisibility(View.VISIBLE);
			textview_get_identifying_code
					.setText(getString(R.string.identify_code_reloading));
			timer = null;
		}

		// 更新剩余时间
		@Override
		public void onTick(long millisUntilFinished, int percent) {
			long time = (millisUntilFinished / 1000);
			ForegroundColorSpan fcs = new ForegroundColorSpan(getResources()
					.getColor(R.color.red1));
			SpannableString ssb = new SpannableString(String.valueOf(time));
			ssb.setSpan(fcs, 0, ssb.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			textview_identifying_code_timer.setText(ssb);
			textview_identifying_code_timer
					.append(getString(R.string.identify_code_reload));
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (is_nextpage) {
				createDialog2();
			} else {
				finish();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
