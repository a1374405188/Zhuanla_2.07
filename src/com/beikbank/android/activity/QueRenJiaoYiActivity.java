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

import com.beikbank.android.activity.ForgetPwdRealnameActivity.MyCount;
import com.beikbank.android.activity.help.DingdanHelp;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.activity.help.YanZhenMaHelp;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data2.ChongZhi_data;
import com.beikbank.android.data2.CreateDingDan;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.dataparam.QueRengJiaoYiParam;
import com.beikbank.android.dataparam.ReqPayforPParam2;
import com.beikbank.android.dataparam2.ChongZhiParam;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.GouMaiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
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
 * @author Administrator 短信确认交易
 */
public class QueRenJiaoYiActivity extends BaseActivity1 implements
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
	 * 订单号
	 */
	private String dingdan;
	private ReqPayforPParam2 rp;

	/**
	 * 发送验证码
	 */
	private TextView tv_fasong;
	/**
	 * 验证码倒计时
	 */
	private TextView tv_time;
	private LinearLayout linear_left;
	private TextView tv_congzhi;
	private Dialog dialog;
	CreateDingDan cdd;
	private final int TOTALTIME=60*1000;//定时60秒
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
	private MyCount timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_querenjiaoyi);
		StateBarColor.init(this, 0xffffffff);
		act = this;
		//rp = (ReqPayforPParam2) getIntent().getSerializableExtra(
//				TypeUtil.jiaoyi_qingqiu);
//		ReqPayforP pf = (ReqPayforP) getIntent().getSerializableExtra(
//				TypeUtil.jiaoyi_qingqiu_data);
//		dingdan = pf.tradeNo;
		initView();
//		YanZhenMaHelp zh = new YanZhenMaHelp(act, tv_fasong, tv_time);
//		zh.start();
		// addData();
		//dialog = Utils.createPorgressDialog(this, null);
		 cdd=(CreateDingDan) getIntent().getSerializableExtra("cdd");
		 if(cdd!=null)
		   {  
			   //银行卡购买调充值
			   String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
			   if("2".equals(pay))
			   {
		       ChongZhiParam czp=new ChongZhiParam();
			   czp.order_id=cdd.order_id;
			czp.user_code=BeikBankApplication.getUserCode();
			TongYongManager2 tym2=new TongYongManager2(act, icb0, czp);
			tym2.start();
			   }
		   }
	}
   ICallBack icb0=new ICallBack() {
	
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
//实现计时功能的类  
	class MyCount extends AdvancedCountdownTimer {  

		public MyCount(long millisInFuture, long countDownInterval) {  
			super(millisInFuture, countDownInterval);  
//			linear_reload_identify_code.setVisibility(View.VISIBLE);
//			textview_get_identifying_code.setVisibility(View.GONE);
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
//			linear_reload_identify_code.setVisibility(View.GONE);
//			textview_get_identifying_code.setVisibility(View.VISIBLE);
//			textview_get_identifying_code.setText(getString(R.string.identify_code_reloading));
			
			
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
	private void initView() {
		ll_error = (LinearLayout) findViewById(R.id.ll_error);
		tv_fasong = (TextView) findViewById(R.id.tv_fasong);
		tv_time = (TextView) findViewById(R.id.tv_time);
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		tv_congzhi = (TextView) findViewById(R.id.tv_congzhi);
		tv_congzhi.setText("支付金额");

		button_next = (Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		TextView titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("确认购买");

		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_money = (TextView) findViewById(R.id.tv_money);
		ed_duanxin = (ClearableEditText) findViewById(R.id.ed_ed2);
		ed_duanxin.addTextChangedListener(new TextWatcherListener());

		String amount = getIntent().getExtras()
				.getString(TypeUtil.jiaoyi_money);

		tv_money.setText(amount + "元");

		String phone = BeikBankApplication.getPhoneNumber();
		tv_phone.setText(Utils.getEncryptedPhonenumber(phone));

		TextView textview_agreement = (TextView) findViewById(R.id.textview_agreement);
		textview_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement.setText(getResources().getString(R.string.pay_agreement_text11));
		textview_agreement.setOnClickListener(this);

		TextView textview_agreement2 = (TextView) findViewById(R.id.textview_agreement2);
		textview_agreement2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement2.setText(getResources().getString(R.string.pay_agreement_text12));
		textview_agreement2.setOnClickListener(this);

		TextView textview_agreement3 = (TextView) findViewById(R.id.textview_agreement3);
		textview_agreement3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement3.setText(getResources().getString(R.string.pay_agreement_text13));
		textview_agreement3.setOnClickListener(this);

		TextView textview_agreement4 = (TextView) findViewById(R.id.textview_agreement4);
		textview_agreement4.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement4.setText(getResources().getString(R.string.pay_agreement_text14));
		textview_agreement4.setOnClickListener(this);

		TextView textview_agreement5 = (TextView) findViewById(R.id.textview_agreement5);
		textview_agreement5.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement5.setText(getResources().getString(R.string.pay_agreement_text15));
		textview_agreement5.setOnClickListener(this);

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
	
	
	
	
	/**
	 * 请求交易回调
	 */
	private void addData() {
//		if (rp != null) {
//			TongYongManager tym = new TongYongManager(act, icb2, rp);
//			tym.start();
//			YanZhenMaHelp zh = new YanZhenMaHelp(act, tv_fasong, tv_time);
//			zh.start();
//		}
		  ChongZhiParam czp=new ChongZhiParam();
		   czp.order_id=cdd.order_id;
		czp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb0, czp);
		tym2.start();
	}

	/**
	 * 请求交易回调
	 */
	private ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				ReqPayforP_Data rd = (ReqPayforP_Data) obj;
				ReqPayforP pf = rd.data;
				dingdan = pf.tradeNo;

			}

		}
	};
	/**
	 * 确认交易回调
	 */
	private ICallBack icb1 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				// QueRenJiaoYi_data qd=(QueRenJiaoYi_data) obj;
				// QueRenJiaoYi qjy=qd.data;
				// int huoqi=getIntent().getIntExtra(TypeUtil.jiaoyi_type,0);
				// ConfirmPay cp=new ConfirmPay();
				// cp.dealTime=qjy.dealTime;
				// cp.orderNumber=qjy.orderNumber;
				// cp.status=qjy.status;
				// cp.amount=qjy.amount;
				// cp.planAmount=qjy.planAmount;
				// Intent intent=getIntent();
				// intent.putExtra(TypeUtil.jiaoyi_state,cp);
				// JiaoYiHelp1 jy=new JiaoYiHelp1(act);
				// jy.startTast();
				DingdanHelp ddh = new DingdanHelp();
				int type = getIntent().getIntExtra(TypeUtil.jiaoyi_type, -1);
				if (type == TypeUtil.jiaoyi_huoqi) {
					ddh.start(act, 2, dialog);
				} else {
					ddh.start(act, 1, dialog);
				}

			} else {
				dialog.dismiss();
			}
		}
	};
	  private ICallBack  icb_goumai=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Intent intent=getIntent();
					intent.setClass(act,JiaoYiXiangQingActivity.class);
					intent.putExtra("order_id",cdd.order_id);
					startActivity(intent);
					finish();
				}
				
			}
		};
	@Override
	public void onClick(View view) {
		Intent intent2 = getIntent();
		intent2.setClass(this, AgreementPurchaseActivity3.class);
		switch (view.getId()) {
		case R.id.button_next:
//			QueRengJiaoYiParam qp = new QueRengJiaoYiParam();
//			qp.memberID = BeikBankApplication.getUserid();
//			qp.tradeNo = dingdan;
//			qp.tradePassword = ed_duanxin.getText().toString();
//			TongYongManager tym = new TongYongManager(act, icb1, qp);
//			tym.start();
			//dialog.show();
			 GouMaiParam gmp=new GouMaiParam();
             gmp.order_id=cdd.order_id;
             gmp.order_type=cdd.order_type;
             gmp.pay_platform_type="0";
             gmp.vercd=ed_duanxin.getText().toString();
             gmp.user_code=BeikBankApplication.getUserCode();
             TongYongManager2 tym2=new TongYongManager2(act, icb_goumai,gmp);
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
		case R.id.textview_agreement3:
			intent2.putExtra("path", "3");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;
		case R.id.textview_agreement4:
			intent2.putExtra("path", "5");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;
		case R.id.textview_agreement5:
			intent2.putExtra("path", "6");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;
		case R.id.linear_left:
			finish();
			break;
		case R.id.tv_fasong:
			addData();
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
