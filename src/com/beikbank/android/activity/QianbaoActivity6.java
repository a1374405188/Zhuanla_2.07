package com.beikbank.android.activity;

import com.beikbank.android.activity.help.DingdanHelp;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.data.Qianbao1;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data.Qianbao7;
import com.beikbank.android.data.Qianbao7_data;
import com.beikbank.android.data.type.DingDan;
import com.beikbank.android.data2.ChongZhiQueReng_data;
import com.beikbank.android.data2.ChongZhi_data;
import com.beikbank.android.data2.CreateDingDan;
import com.beikbank.android.data2.TiXianQingQiu;
import com.beikbank.android.data2.TiXianQingQiu_data;
import com.beikbank.android.data2.TiXianQueReng_data;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.QianbaoParam2;
import com.beikbank.android.dataparam.QianbaoParam8;
import com.beikbank.android.dataparam2.ChongZhiParam;
import com.beikbank.android.dataparam2.ChongZhiQueRengParam;
import com.beikbank.android.dataparam2.TiXianQingQiuParam;
import com.beikbank.android.dataparam2.TiXianQueRengParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao2Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * copyright yuguohe email: 1374405188@qq.com 2015-5-22 钱包，充值，取现输入交易密码
 */
public class QianbaoActivity6 extends BaseActivity1 implements
		View.OnClickListener {
	private TextView textview_forget_transaction_password;
	private TextView textview_agreement;
	private CheckBox cb;
	// 取现金额，手续费，到帐金额
	TextView tv_tv1, tv_tv2, tv_tv3;
	TextView title;
	/**
	 * 手续费，到账金额父布局
	 */
	private LinearLayout ll2, ll3;
	/**
	 * 分割线
	 */
	private View view, view1;
	/**
	 * 充值金额或取现金额文本
	 */
	TextView tv0;
	/**
	 * 充值金额
	 */
	TextView tv1;
	/**
	 * return
	 */
	LinearLayout ll;
	/**
	 * 下一步
	 */
	Button button_next;
	/**
	 * 密码
	 */
	ClearableEditText et1;
	/**
	 * 充值金额
	 */
	String money;
	/**
	 * 请求订单号
	 */
	String tradeNo;
	/**
	 * 1充值确认，2取现确认
	 */
	private String index = "1";
	private Dialog dialog;
	Activity act;
	CreateDingDan cdd;
	TiXianQingQiu tiqq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		index = getIntent().getStringExtra("index");
		setContentView(R.layout.activity_qianbao6);
		StateBarColor.init(this, 0xffffffff);
		act = this;
		cdd=(CreateDingDan) getIntent().getSerializableExtra("cdd");
		initView();
		initData();
		//dialog = Utils.createPorgressDialog(this, null);
	}

	private void initData() {
		// money=
		// BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.INTENT_AMOUNT);
		money = getIntent().getStringExtra(TypeUtil.jiaoyi_money);
		tv1.setText(NumberManager.getString(money, "1", 2) + "元");
		//QianbaoParam1 qp1 = new QianbaoParam1(this);
		//qp1.memberID = BeikBankApplication.getUserid();
		//qp1.amount = money;
		if (index.equals("1")) {
			tv0.setText("充值金额");
			view.setVisibility(View.GONE);
			view1.setVisibility(View.GONE);
			ll2.setVisibility(View.GONE);
			ll3.setVisibility(View.GONE);
			// Qianbao1Manager qm=new Qianbao1Manager(this, icb1, qp1);
			// qm.start();
			Intent intent = getIntent();
			
			button_next.setText("确认充值");
			 ICallBack icb_czp=new ICallBack() {
					
					@Override
					public void back(Object obj) {
						if(obj!=null)
						{
							
						}
						
					}
				};
				
				
				ChongZhiParam czp=new ChongZhiParam();
				czp.order_id=cdd.order_id;
				czp.user_code=BeikBankApplication.getUserCode();
				TongYongManager2 tym2=new TongYongManager2(act, icb_czp,czp);
				
				tym2.start();
			
			
		} else {
			title.setText("确认提现");
			tv0.setText("提现金额");
	      
			TiXianQingQiu tixian=(TiXianQingQiu) getIntent().getSerializableExtra("tixian");
			tiqq=tixian;
			tv_tv2.setText(NumberManager.getGeshiHua(tiqq.deduct_service_amt,2)+"元");
			String residue = NumberManager.getSubString(money,tiqq.deduct_service_amt, 2);
		    tv_tv3.setText(NumberManager.getGeshiHua(residue, 2)+"元");
			
//			
//			ICallBack icb_tixian=new ICallBack() {
//				
//				@Override
//				public void back(Object obj) {
//					if(obj!=null)
//					{
//						TiXianQingQiu_data td=(TiXianQingQiu_data) obj;
//						tiqq=td.body;
//						tv_tv2.setText(NumberManager.getGeshiHua(tiqq.deduct_service_amt,2)+"元");
//						String residue = NumberManager.getSubString(money,tiqq.deduct_service_amt, 2);
//					    tv_tv3.setText(NumberManager.getGeshiHua(residue, 2)+"元");
//					}
//					
//				}
//			};
//			
//			String name=BeikBankApplication.getSharePref(BeikBankConstant.real_name);
//			String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
//			TiXianQingQiuParam txp=new TiXianQingQiuParam();
//			txp.acc_id=BeikBankApplication.getAccid();
//			txp.amount=money;
//			txp.order_id=cdd.order_id;
//			txp.bank_name=bank;
//			txp.user_code=BeikBankApplication.getUserCode();
//			txp.phone_number=BeikBankApplication.getPhoneNumber();
//			txp.real_name=name;
//			txp.id_number=BeikBankApplication.getSharePref(BeikBankConstant.bank);
//			TongYongManager2 tym2=new TongYongManager2(act, icb_tixian,txp);
//			
//            tym2.start();
		}
//充值请求
		
		
	}

	
	
	/**
	 * 是否能取现
	 */
	boolean isQvxian = true;
	Qianbao7 qb7;
	private ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				Qianbao7_data qb7d = (Qianbao7_data) obj;
				qb7 = qb7d.data;
				String s2 = NumberManager.getString(qb7.poundage, "1", 2);
				tv_tv2.setText(s2 + "元");
				String residue = NumberManager.getSubString(money, s2, 2);
				double f3 = Double.parseDouble(residue);
				if (f3 > 0) {
					tv_tv3.setText(residue + "元");
				} else {
					// tv_tv3.setText("0.00"+"元");
					tv_tv3.setText("免手续费");
				}

			}

		}
	};
	/**
	 * 1充值2提现
	 */
	private int index1;

	private void doNext() {
//		QianbaoParam2 qp = new QianbaoParam2();
//		qp.memberID = BeikBankApplication.getUserid();
//		qp.tradeNo = tradeNo;
//		qp.tradePassword = et1.getText().toString();
		if (index.equals("1")) {
			
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
							Intent intent = new Intent(act, JiaoYiXiangQingActivity.class);
							intent.putExtra("order_id",cdd.order_id);
							//intent.putExtra(TypeUtil.jiaoyi_state, dd);
							act.startActivity(intent);
						  
						  
					  }
				  }
					
				}
			};
			
			ChongZhiQueRengParam czq=new ChongZhiQueRengParam();
			czq.order_id=cdd.order_id;
			czq.tra_password=MD5.md5s32(et1.getText().toString());
			czq.user_code=BeikBankApplication.getUserCode();
			czq.pay_platform_type="1";
			TongYongManager2 tym2=new TongYongManager2(act, icb_czq,czq);
			tym2.start();
			
		} else {
			index1 = 2;
//			QianbaoParam8 qb8 = new QianbaoParam8();
//			qb8.memberID = BeikBankApplication.getUserid();
//			qb8.tradeNo = qb7.tradeNo;
//			qb8.tradePassword = et1.getText().toString();
//			TongYongManager tym = new TongYongManager(this, icb3, qb8);
//			// Qianbao4Manager qm=new Qianbao4Manager(this, icb, qp);
//			// qm.start();
//			tym.start();
			ICallBack icb_tx=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{
						  TiXianQueReng_data txqr=(TiXianQueReng_data) obj;
						  if("0000".equals(txqr.header.re_code))
						  {
//							    DingDan dd = new DingDan();
//								dd.amount = money;
//								dd.orderNumber =cdd.order_id;
//								dd.status = cdd.status;
//								dd.leixing = 3;
								Intent intent = new Intent(act, JiaoYiXiangQingActivity.class);
								intent.putExtra("order_id",cdd.order_id);
								//intent.putExtra(TypeUtil.jiaoyi_state, dd);
								act.startActivity(intent);
							  
							  
						  }
					}
					
				}
			};
			
			TiXianQueRengParam tiqr=new TiXianQueRengParam();
			tiqr.acc_id=BeikBankApplication.getAccid();
			//tiqr.id_number=BeikBankApplication.getSharePref(BeikBankConstant.bank);
			//tiqr.is_free=tiqq.deduct_free;
			tiqr.order_id=cdd.order_id;
			tiqr.phone_number=BeikBankApplication.getPhoneNumber();
			//tiqr.real_name="";
			tiqr.tra_password=MD5.md5s32(et1.getText().toString());
		   
			tiqr.user_code=BeikBankApplication.getUserCode();
			TongYongManager2 tym2=new TongYongManager2(act, icb_tx,tiqr);
			tym2.start();
			
		}
	}

	private void initView() {
		ll_error = (LinearLayout) findViewById(R.id.ll_error);
		tv_error = (TextView) findViewById(R.id.tv_error);
		tv_tv2 = (TextView) findViewById(R.id.tv_tv2);
		tv_tv3 = (TextView) findViewById(R.id.tv_tv3);
		ll = (LinearLayout) findViewById(R.id.linear_left);
		title = (TextView) findViewById(R.id.titleTv);
		title.setText("确认充值");
		et1 = (ClearableEditText) findViewById(R.id.et_et1);
		et1.addTextChangedListener(new TextWatcherListener());
		tv1 = (TextView) findViewById(R.id.tv1);
		tv0 = (TextView) findViewById(R.id.tv0);
		cb = (CheckBox) findViewById(R.id.checkbox_agreement);
		ll2 = (LinearLayout) findViewById(R.id.ll2);
		ll3 = (LinearLayout) findViewById(R.id.ll3);
		view = findViewById(R.id.view);
		view1 = findViewById(R.id.view1);
		cb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cb.isChecked() && et1.getText() != null
						&& et1.getText().toString().length() > 0) {
					button_next.setEnabled(true);
				} else {
					button_next.setEnabled(false);
				}

			}
		});

		ll.setOnClickListener(this);
		button_next = (Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		button_next.setText("确认提现");
		textview_agreement = (TextView) findViewById(R.id.textview_agreement);
		textview_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		textview_agreement.setText(getResources().getString(
				R.string.pay_agreement_text));
		textview_agreement.setOnClickListener(this);

		textview_forget_transaction_password = (TextView) findViewById(R.id.textview_forget_transaction_password);
		textview_forget_transaction_password.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
			finish();
			break;
		case R.id.textview_agreement:
			Intent intent2 = getIntent();
			intent2.setClass(this, AgreementPurchaseActivity.class);
			intent2.putExtra("path", "1");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;
		case R.id.button_next:
			doNext();
			break;
		case R.id.textview_forget_transaction_password:// 忘记交易密码
			// clearedittext_transaction_password.setText("");
			String phonenumber = BeikBankApplication.mSharedPref
					.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
			// Intent intent=new Intent(this,ForgetPwdRealnameActivity.class);
			Intent intent = getIntent();
			intent.setClass(this, ForgetPwdRealnameActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	ICallBack icb3 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				Qianbao1_data qd = (Qianbao1_data) obj;
				Qianbao1 qb = qd.data;
				DingDan dd = new DingDan();
				dd.amount = money;
				dd.orderNumber = qb.orderNumber;
				dd.status = qb.status;
				dd.leixing = 6;
				Intent intent = new Intent(act, JiaoYiXiangQingActivity.class);
				intent.putExtra(TypeUtil.jiaoyi_state, dd);
				act.startActivity(intent);
			}

		}
	};
	ICallBack icb = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {

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
	ICallBack icb1 = new ICallBack() {

		@Override
		public void back(Object obj) {

			if (obj != null) {
				Qianbao2_data qd = (Qianbao2_data) obj;
				tradeNo = qd.data.tradeNo;
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
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			if (s.toString().length() > 0 && cb.isChecked()) {
				button_next.setEnabled(true);
			} else {
				button_next.setEnabled(false);
			}
		}

	}

}
