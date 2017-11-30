package com.beikbank.android.activity;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao1Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * copyright yuguohe email: 1374405188@qq.com 2015-5-22 充值
 */
public class QianbaoActivity2 extends BaseActivity1 implements
		View.OnClickListener {
	TextView title;
	TextView right;
	private TextView textview_agreement;
	/**
	 * return
	 */
	LinearLayout ll;

	/**
	 * 下一步
	 */
	Button button_next;
	ClearableEditText et1;
	Activity act = this;
	BankList bl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianbao2);
		StateBarColor.init(this, 0xffffffff);
		initView();
	}

	/**
	 * 单笔限额
	 */
	double singleLimit;

	private void initView() {
		ll_error = (LinearLayout) findViewById(R.id.ll_error);
		tv_error = (TextView) findViewById(R.id.tv_error);
		// tv1=(TextView)findViewById(R.id.tv1);
		ll = (LinearLayout) findViewById(R.id.linear_left);
		title = (TextView) findViewById(R.id.titleTv);
		title.setText("充值");
		button_next = (Button) findViewById(R.id.button_next);
		et1 = (ClearableEditText) findViewById(R.id.et_et1);
		et1.addTextChangedListener(new TextWatcherListener());
		ll.setOnClickListener(this);
		button_next.setOnClickListener(this);

		// textview_agreement=(TextView)findViewById(R.id.textview_agreement);
		// textview_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
		// textview_agreement.setText(getResources().getString(R.string.pay_agreement_text));
		// textview_agreement.setOnClickListener(this);
		TextView textview_purchase_single = (TextView) findViewById(R.id.textview_purchase_single);
		TextView textview_purchase_bank = (TextView) findViewById(R.id.textview_purchase_bank);
//		CardInfo cardInfo = CardInfoDao.getCardInfo();
//
//		if (cardInfo != null && cardInfo.type != null) {
//			bl = BankListDao.getBankByType(cardInfo.getType());
//			if (bl != null) {
//				singleLimit = NumberManager.StoD(bl.singleLimit);
//				String cardNumber = cardInfo.getCardNumber();
//				textview_purchase_bank.setText(bl.bankName
//						+ "(尾号"
//						+ cardNumber.substring(cardNumber.length() - 4,
//								cardNumber.length()) + ")");
//				textview_purchase_single.setText("单笔最低限额" + bl.minLimit
//						+ "元,最高" + bl.singleLimit + "万元");
//				singleLimit *= 10000;
//			}
//		}
		String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
		String bank_name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
		String bank_min=BeikBankApplication.getSharePref(BeikBankConstant.bank_min_amount);
		String bank_max=BeikBankApplication.getSharePref(BeikBankConstant.bank_max_amount);
		textview_purchase_bank.setText(bank_name+"尾号("+bank.substring(bank.length()-4,bank.length())+")");
	
		textview_purchase_single.setText("单笔最低限额"+bank_min+"元,最高"+bank_max+"万元");
	}

	private void initData()
	{
		ICallBack icb_cdd=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					
				}
				
			}
		};
		
		 CreateDingDanParam cdp=new CreateDingDanParam();
		 cdp.acc_id="";
		 cdp.acc_number="";
		 cdp.amount="";
		
		 cdp.order_type="2";
		 cdp.pro_id="";
		
		 cdp.pro_type="";
		 cdp.user_code=BeikBankApplication.getUserCode();
		 
		 TongYongManager2 tym2=new TongYongManager2(act, icb_cdd,cdp);
		 tym2.start();
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:
//			int a = (int) NumberManager.StoD(et1.getText().toString());
//			String money = et1.getText().toString();
//			if (PurchaseActivity.minLimit(money, bl.minLimit)) {
//				showToast("超出银行卡支付限额");
//				return;
//			}
//			if (a > singleLimit && a > 0 && singleLimit > 0) {
//				showToast("超出银行卡支付限额");
//				return;
//			}

			addData(et1.getText().toString());
			break;
		case R.id.textview_agreement:
			Intent intent2 = getIntent();
			intent2.setClass(this, AgreementPurchaseActivity.class);
			intent2.putExtra("path", "1");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;
		default:
			break;
		}

	}

	private QianbaoParam1 qp1;

	/**
	 * 请求交易
	 */
	private void addData(final String money) {
//		qp1 = new QianbaoParam1(this);
//		qp1.memberID = BeikBankApplication.getUserid();
//		qp1.amount = money;
//		Qianbao1Manager qm = new Qianbao1Manager(this, icb1, qp1);
//		qm.start();
		
		
		ICallBack icb_cdd=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					CreateDingDan_data cdd=(CreateDingDan_data) obj;
					Intent intent=new Intent(act,QianbaoActivity6.class);
					String mima=BeikBankApplication.getSharePref(BeikBankConstant.mima_duanxin);
					if("0".equals(mima))
					{
						intent=new Intent(act,QueRenJiaoYiActivity2.class);
					}
					intent.putExtra("index","1");
					intent.putExtra(TypeUtil.jiaoyi_money,money);
					intent.putExtra("cdd",cdd.body);
					act.startActivity(intent);
				}
				
			}
		};
		
		CreateDingDanParam cdd=new CreateDingDanParam();
		cdd.acc_id=BeikBankApplication.getSharePref(BeikBankConstant.zhanghao);
		cdd.acc_number=BeikBankApplication.getSharePref(BeikBankConstant.bank);
		cdd.amount=money;
		cdd.order_type="1";
		cdd.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb_cdd,cdd);
		tym2.start();
		

	}

	private ICallBack icb1 = new ICallBack() {
		@Override
		public void back(Object obj) {
			if (obj != null) {
				Qianbao2_data qd = (Qianbao2_data) obj;
				String tradeNo = qd.data.tradeNo;
				String huyou = qd.data.fuiouPay;
				if (huyou != null && huyou.equals("1")) {
					Intent intent = new Intent(QianbaoActivity2.this,
							QueRenJiaoYiActivity2.class);
					intent.putExtra("index3", qp1);
					intent.putExtra("index4", qd.data);
					intent.putExtra(TypeUtil.jiaoyi_money, et1.getText()
							.toString());
					startActivity(intent);
				} else {
					finish();
					Intent intent = new Intent(QianbaoActivity2.this,
							QianbaoActivity6.class);
					intent.putExtra("index", "1");
					intent.putExtra("tradeNo", tradeNo);
					// 将充值金额写到文件
					// intent.putExtra(BeikBankConstant.INTENT_AMOUNT,et1.getText().toString());
					// BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.INTENT_AMOUNT,et1.getText().toString());
					// BeikBankApplication.mSharedPref.putSharePrefString("QianbaoActivity2",et1.getText().toString());
					intent.putExtra(TypeUtil.jiaoyi_money, et1.getText()
							.toString());
					startActivity(intent);
				}
                finish();
			}

		}
	};

	// 检查是否需要绑定银行卡
	private void checkBank() {
		CheckBankParam cbp = new CheckBankParam();
		cbp.memberID = BeikBankApplication.getUserid();
		TongYongManager tym = new TongYongManager(this, icb2, cbp);
		tym.start();

	}

	ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {

			if (obj != null) {

				CheckBank_data cd = (CheckBank_data) obj;
				CheckBank cb = cd.data;
				if (cb.UserCardLimit.equals("0")) {
					Intent intent = new Intent(act, BankBindActivity2.class);
					startActivity(intent);
					return;
				}

				Intent intent = new Intent(QianbaoActivity2.this,
						QianbaoActivity6.class);
				intent.putExtra("index", "1");
				// 将充值金额写到文件
				// intent.putExtra(BeikBankConstant.INTENT_AMOUNT,et1.getText().toString());
				// BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.INTENT_AMOUNT,et1.getText().toString());
				// BeikBankApplication.mSharedPref.putSharePrefString("QianbaoActivity2",et1.getText().toString());
				intent.putExtra(TypeUtil.jiaoyi_money, et1.getText().toString());
				startActivity(intent);

			}
		}
	};

	class TextWatcherListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s.length() == 1) {
				if (s.charAt(0) == '0' || s.charAt(0) == '.') {
					et1.setText("");
					return;
				}
			}
			if (s.toString().length() > 0) {
				button_next.setEnabled(true);
			} else {
				button_next.setEnabled(false);
			}
		}

	}
}
