package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.activity.QueRenJiaoYiActivity.MyCount;
import com.beikbank.android.activity.help.DingdanHelp;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.activity.help.YanZhenMaHelp;
import com.beikbank.android.data.Qianbao2;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data.type.DingDan;
import com.beikbank.android.data2.ChongZhiQueReng_data;
import com.beikbank.android.data2.ChongZhi_data;
import com.beikbank.android.data2.CreateDingDan;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.QueRengJiaoYiParam2;
import com.beikbank.android.dataparam2.ChongZhiParam;
import com.beikbank.android.dataparam2.ChongZhiQueRengParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao1Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



/**
 * 
 * @author Administrator 充值短信确认交易
 */
public class QueRenJiaoYiActivity2 extends BaseActivity1 implements
		OnClickListener {
	TextView title;
	/**
	 * return
	 */
	LinearLayout ll1;
	/**
	 * 下一步
	 */
	private Button button_next;
	private TextView tv_phone;
	private TextView tv_money;
	private Activity act;
	/**
	 * 
	 * 短信密码
	 */
	private ClearableEditText ed_duanxin;
	private CheckBox cb_xieyi;
	

	/**
	 * 发送验证码
	 */
	private TextView tv_fasong;
	/**
	 * 验证码倒计时
	 */
	private TextView tv_time;
	private LinearLayout linear_left;
	private Dialog dialog;
    private String money;
    
	private final int TOTALTIME=60*1000;//定时60秒
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
	private MyCount timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_querenjiaoyi);
		StateBarColor.init(this, 0xffffffff);
		act = this;
		
		money = getIntent().getStringExtra(TypeUtil.jiaoyi_money);
		cdd=(CreateDingDan) getIntent().getSerializableExtra("cdd");
		initView();
		 addData();
//		Qianbao2 q2 = (Qianbao2) getIntent().getSerializableExtra("index4");
//		dingdan = q2.tradeNo;
//		YanZhenMaHelp zh = new YanZhenMaHelp(act, tv_fasong, tv_time);
//		zh.start();
//		dialog = Utils.createPorgressDialog(this, null);
	}

	private void initView() {
		ll_error = (LinearLayout) findViewById(R.id.ll_error);
		tv_fasong = (TextView) findViewById(R.id.tv_fasong);
		tv_time = (TextView) findViewById(R.id.tv_time);
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		button_next = (Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		TextView titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("确认充值");

		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_money = (TextView) findViewById(R.id.tv_money);
		ed_duanxin = (ClearableEditText) findViewById(R.id.ed_ed2);
		ed_duanxin.addTextChangedListener(new TextWatcherListener());

		
			tv_money.setText(money + "元");
		
		String phone = BeikBankApplication.getPhoneNumber();
		tv_phone.setText(Utils.getEncryptedPhonenumber(phone));

		TextView textview_agreement = (TextView) findViewById(R.id.textview_agreement);
		textview_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		textview_agreement.setText(getResources().getString(
				R.string.pay_agreement_text));
		textview_agreement.setOnClickListener(this);

		TextView textview_agreement2 = (TextView) findViewById(R.id.textview_agreement2);
		textview_agreement2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		textview_agreement2.setText(getResources().getString(
				R.string.pay_agreement_text5));
		textview_agreement2.setOnClickListener(this);

		cb_xieyi = (CheckBox) findViewById(R.id.cb_xieyi);

		cb_xieyi.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (!isChecked) {
					button_next.setEnabled(false);
				} else {
					if (ed_duanxin.getText().toString().length() >= 4) {
						button_next.setEnabled(true);
					}
				}
			}
		});

		tv_fasong.setOnClickListener(this);
	}
	CreateDingDan cdd;
	//实现计时功能的类  
		class MyCount extends AdvancedCountdownTimer {  

			public MyCount(long millisInFuture, long countDownInterval) {  
				super(millisInFuture, countDownInterval);  
//				linear_reload_identify_code.setVisibility(View.VISIBLE);
//				textview_get_identifying_code.setVisibility(View.GONE);
				ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
				SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME/COUNTDOWNINTERVAL));
				ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
				tv_time.setText(ssb);
				tv_time.append(getString(R.string.identify_code_reload));
				
				
				tv_fasong.setVisibility(View.GONE);
				tv_time.setVisibility(View.VISIBLE);
			}  

			@Override  
			public void onFinish() {              
//				linear_reload_identify_code.setVisibility(View.GONE);
//				textview_get_identifying_code.setVisibility(View.VISIBLE);
//				textview_get_identifying_code.setText(getString(R.string.identify_code_reloading));
				
				
				tv_fasong.setVisibility(View.VISIBLE);
				tv_fasong.setText("重新发送验证码");
				tv_time.setVisibility(View.GONE);
				
				timer=null;
			}     
			//更新剩余时间  
			@Override  
			public void onTick(long millisUntilFinished, int percent) { 
				long time = (millisUntilFinished / 1000);  
				ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
				SpannableString ssb = new SpannableString(String.valueOf(time));
				ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
				tv_time.setText(ssb);
				tv_time.append(getString(R.string.identify_code_reload));           

			}  

		} 
	/**
	    * 
	    */
	private void addData() {
//		if (qp1 != null) {
//
//			Qianbao1Manager qm = new Qianbao1Manager(this, icb1, qp1);
//			qm.start();
//
//		}
		
		
      ICallBack icb_czp=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					 ChongZhi_data cd=(ChongZhi_data) obj;
					   if("0000".equals(cd.header.re_code))
					   {
					    timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
						timer.start();
					   }
				}
				
			}
		};
		
		
		ChongZhiParam czp=new ChongZhiParam();
		czp.order_id=cdd.order_id;
		czp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb_czp,czp);
		
		tym2.start();
	}

	
	/**
	 * 确认交易回调
	 */
	private ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				// Qianbao1_data qd=(Qianbao1_data) obj;
				// //Intent intent=new
				// Intent(QueRenJiaoYiActivity2.this,QianbaoActivity5.class);
				// Intent intent=getIntent();
				// intent.setClass(QueRenJiaoYiActivity2.this,QianbaoActivity5.class);
				// intent.putExtra("index",qd.data);
				// intent.putExtra("index2",2);
				// intent.putExtra("index1",1);
				// startActivity(intent);

				DingdanHelp ddh = new DingdanHelp();
				ddh.start(act, 3, dialog);
				//finish();
				BeikBankApplication.mSharedPref.putSharePrefBoolean(
						BeikBankConstant.re_home, true);
			} else {
				dialog.dismiss();
			}
		}
	};

	@Override
	public void onClick(View view) {
		Intent intent2 = getIntent();
		intent2.setClass(this, AgreementPurchaseActivity3.class);
		switch (view.getId()) {
		case R.id.button_next:
//			QueRengJiaoYiParam2 qp = new QueRengJiaoYiParam2();
//			qp.memberID = BeikBankApplication.getUserid();
//			qp.tradeNo = dingdan;
//			qp.tradePassword = ed_duanxin.getText().toString();
//			TongYongManager tym = new TongYongManager(act, icb2, qp);
//			tym.start();
//			dialog.show();
	    ICallBack icb_czq=new ICallBack() {
				
				@Override
				public void back(Object obj) {
				  if(obj!=null)
				  {
					  ChongZhiQueReng_data cd=(ChongZhiQueReng_data) obj;
					  if("0000".equals(cd.header.re_code))
					  {
//						    DingDan dd = new DingDan();
//							dd.amount = money;
//							dd.orderNumber =cdd.order_id;
//							dd.status = cdd.status;
//							dd.leixing = 3;
//							Intent intent = new Intent(act, JiaoYiXiangQingActivity.class);
//							intent.putExtra(TypeUtil.jiaoyi_state, dd);
//							act.startActivity(intent);
						  
						    Intent intent=getIntent();
							intent.setClass(act,JiaoYiXiangQingActivity.class);
							intent.putExtra("order_id",cdd.order_id);
							startActivity(intent);
					  }
				  }
					
				}
			};
			
			ChongZhiQueRengParam czq=new ChongZhiQueRengParam();
			czq.order_id=cdd.order_id;
			czq.tra_password="";
			czq.user_code=BeikBankApplication.getUserCode();
			czq.pay_platform_type="0";
			czq.vercd=ed_duanxin.getText().toString();
			TongYongManager2 tym2=new TongYongManager2(act, icb_czq,czq);
			tym2.start();
			
			break;
		case R.id.textview_agreement:
			intent2.putExtra("path", "1");
			startActivity(intent2);
			break;
		case R.id.textview_agreement2:
			intent2.putExtra("path", "2");
			startActivity(intent2);
			break;

		case R.id.tv_fasong:
			addData();
			break;
		case R.id.linear_left:
			finish();
			break;

		}
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
			String password = ed_duanxin.getText().toString();
			if (password.length() >= 4 && cb_xieyi.isChecked()) {
				button_next.setEnabled(true);
			} else {
				button_next.setEnabled(false);
			}
		}

	}

}
