package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.help.DingdanHelp;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.ConfirmPay;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.PlayFund;
import com.beikbank.android.data.PlayFund_data;
import com.beikbank.android.data.PurchaseInfo;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.ChongZhi_data;
import com.beikbank.android.data2.CreateDingDan;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.dataparam.ConfirmPayforPParam;
import com.beikbank.android.dataparam2.ChongZhiParam;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.GouMaiParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfirmPayforPManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;
import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//购买确认
public class PurchaseConfirmActivity extends BaseActivity1 implements
		OnClickListener {
	private Activity act = this;
	private final String TAG = "PurchaseConfirmActivity";
	private TextView titleTv, textview_agreement, textview_agreement2,
			textview_agreement4, textview_forget_transaction_password,

			textview_toast, textview_money, textview_purchase_single,
			textview_purchase_bank;
	/**
	 * 用户协议
	 */
	private TextView textview_agreement3, textview_agreement5;
	private Button button_next;
	private CheckBox checkbox_agreement;
	private ClearableEditText clearedittext_transaction_password;
	private String money;
	private Dialog dialog;
	private AnimatorSet toastAnimSet;
	private LinearLayout linear_toast, linear_left, linear_credit_card_master;
	private PurchaseInfo purchaseInfo;
	private String sid;

	public static final String index = "index";
	 GetChanPin gcp;
     getQianBao gqb;
     CreateDingDan cdd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_confirm);
		StateBarColor.init(this, 0xffffffff);
//		fi = (FundInfo) getIntent().getSerializableExtra(TypeUtil.huoqi_data);
//		rp = (ReqPayforP) getIntent().getSerializableExtra(
//				TypeUtil.jiaoyi_qingqiu_data);
		
		  gcp=(GetChanPin) getIntent().getSerializableExtra("gcp");
	      gqb=(getQianBao) getIntent().getSerializableExtra("gqb");
	      money=getIntent().getStringExtra(TypeUtil.jiaoyi_money);
	      initView();
	      initData();
	

		// String
		// play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
		// String
		// tok=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao2);
		// ReqPayforPParam rp=new ReqPayforPParam();
		// ReqPayforPParam2 rp=new ReqPayforPParam2();
		// rp.memberID=BeikBankApplication.getUserid();
		// rp.productId=fi.sid;
		// rp.productName=fi.name;
		// rp.productType=FinalIndex.cp_type2;
		// rp.amount=amount;
		// rp.tradeMode=play;
		// rp.couponTokens=tok;
		// //ReqPayforPManager rpp=new ReqPayforPManager(this, icb1, rp);
		// //rpp.start();
		// TongYongManager tym=new TongYongManager(this, icb1,rp);
		// tym.start();

	}

   private void initData()
   {
	   
	   final ICallBack  icb0=new ICallBack() {
			
			@Override
			public void back(Object obj) {
			  if(obj!=null)
			  {
				  ChongZhi_data cd=(ChongZhi_data) obj;
				 
				  if("0000".equals( cd.header.re_code))
				  {   
					  if(clearedittext_transaction_password.length()>4)
					  {
					    button_next.setEnabled(true);
					  }
				  }
			  }
				
			}
		};
//		   ICallBack icb=new ICallBack() {
//				
//				@Override
//				public void back(Object obj) {
//					if(obj!=null)
//					{
//						CreateDingDan_data cddd=(CreateDingDan_data) obj;
//						cdd=cddd.body;
//						ChongZhiParam czp=new ChongZhiParam();
//						czp.order_id=cdd.order_id;
//						czp.user_code=BeikBankApplication.getUserCode();
//						TongYongManager2 tym2=new TongYongManager2(act, icb0, czp);
//						tym2.start();
//						
//					}
//					
//				}
//			};
//			 
//			 String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
//			 CreateDingDanParam cdp=new CreateDingDanParam();
//			 cdp.acc_id=gqb.acc_id;
//			 cdp.acc_number=gqb.acc_number;
//			 cdp.amount=money;
//			
//			 cdp.order_type=pay;
//			 cdp.pro_id=gcp.product_id;
//			 cdp.pro_type=gcp.type;
//			 cdp.user_code=BeikBankApplication.getUserCode();
//			 
//			 TongYongManager2 tym2=new TongYongManager2(act, icb,cdp);
//			 tym2.start();
	   
			 
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

	public void initView() {
		ll_error = (LinearLayout) findViewById(R.id.ll_error);
		tv_error = (TextView) findViewById(R.id.tv_error);

		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.purchase_confirm));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		textview_money = (TextView) findViewById(R.id.textview_money);

		clearedittext_transaction_password = (ClearableEditText) findViewById(R.id.clearedittext_transaction_password);
		clearedittext_transaction_password
				.addTextChangedListener(new TextWatcherListener());
		button_next = (Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		button_next.setEnabled(false);

		textview_agreement = (TextView) findViewById(R.id.textview_agreement);
		textview_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement.setText(getResources().getString(R.string.pay_agreement_text11));
		textview_agreement.setOnClickListener(this);

		textview_agreement2 = (TextView) findViewById(R.id.textview_agreement2);
		textview_agreement2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement2.setText(getResources().getString(R.string.pay_agreement_text12));
		textview_agreement2.setOnClickListener(this);

		textview_agreement3 = (TextView) findViewById(R.id.textview_agreement3);
		textview_agreement3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement3.setText(getResources().getString(R.string.pay_agreement_text13));
		textview_agreement3.setOnClickListener(this);

		textview_agreement4 = (TextView) findViewById(R.id.textview_agreement4);
		textview_agreement4.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement4.setText(getResources().getString(R.string.pay_agreement_text14));
		textview_agreement4.setOnClickListener(this);

		textview_agreement5 = (TextView) findViewById(R.id.textview_agreement5);
		textview_agreement5.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		// textview_agreement5.setText(getResources().getString(R.string.pay_agreement_text15));
		textview_agreement5.setOnClickListener(this);
        
		
		TextView tv_fenxian=(TextView) findViewById(R.id.tv_fenxian);
		tv_fenxian.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		tv_fenxian.setOnClickListener(this);
		
		// textview_purchase_single=(TextView)findViewById(R.id.textview_purchase_single);
		// /textview_purchase_bank=(TextView)findViewById(R.id.textview_purchase_bank);
		// //CardInfo
		// cardInfo=CardInfoDao.getInstance(PurchaseConfirmActivity.this).getCardInfo();
		// CardInfo cardInfo=(CardInfo)
		// TableDaoSimple.queryone(CardInfo.class,null,null);
		//
		// //BankInfo
		// bankInfo=BankInfoDao.getInstance(PurchaseConfirmActivity.this).getBankInfoByType(cardInfo.getType());
		// if(cardInfo!=null&&cardInfo.getType()!=null)
		// {
		// BankList bl=BankListDao.getBankByType(cardInfo.getType());
		//
		// String cardNumber=cardInfo.getCardNumber();
		// textview_purchase_bank.setText(bl.bankName
		// +"(尾号"+cardNumber.substring(cardNumber.length()-4,
		// cardNumber.length())+")");
		// textview_purchase_single.setText("单笔限额"+bl.singleLimit+"万元");
		// }
		textview_forget_transaction_password = (TextView) findViewById(R.id.textview_forget_transaction_password);
		textview_forget_transaction_password.setOnClickListener(this);

		// linear_credit_card_master=(LinearLayout)findViewById(R.id.linear_credit_card_master);
		// linear_credit_card_master.setOnClickListener(this);

		checkbox_agreement = (CheckBox) findViewById(R.id.checkbox_agreement); // 购买协议单选框
		checkbox_agreement
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						// TODO Auto-generated method stub
						if (!ischecked) {
							button_next.setEnabled(false);
						} else {
							if (clearedittext_transaction_password.getText()
									.toString().length() >= 6) {
								button_next.setEnabled(true);
							}
						}

					}
				});

		linear_toast = (LinearLayout) findViewById(R.id.linear_toast);
		textview_toast = (TextView) findViewById(R.id.textview_toast);

		//amount = getIntent().getExtras().getString(TypeUtil.jiaoyi_money);
		//sid = getIntent().getExtras().getString(BeikBankConstant.INTENT_SID);
		// DecimalFormat df=new DecimalFormat("0.00");
		// double dou=Double.parseDouble(amount);
//		String money2 = getIntent().getStringExtra("money2");
//		if (money2 == null || money2.equals("")) {
//			money2 = NumberManager.getGeshiHua(amount, 2);
//		} else {
//			money2 = NumberManager.getGeshiHua(money2, 2);
//		}
		textview_money.setText(money + "元");

		//dialog = Utils.createPorgressDialog(act, null);
	}
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent2 = getIntent();
		intent2.setClass(this, AgreementPurchaseActivity3.class);
		switch (v.getId()) {
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:// 确认购买
		         GouMaiParam gmp=new GouMaiParam();
	             gmp.order_id=cdd.order_id;
	             gmp.order_type=cdd.order_type;
	             gmp.pay_platform_type="1";
	             gmp.tra_password=MD5.md5s32(clearedittext_transaction_password.getText().toString());
	             gmp.user_code=BeikBankApplication.getUserCode();
	             TongYongManager2 tym2=new TongYongManager2(act, icb_goumai,gmp);
	             tym2.start();
			// UserInfo
			// userInfo=UserInfoDao.getInstance(PurchaseConfirmActivity.this).getUserInfo();
//			UserInfo userInfo = BeikBankApplication.getUserInfo();
//			String fund_sid = BeikBankApplication.getInstance().getSharedPref()
//					.getSharePrefString(BeikBankConstant.FUNDINFO_SID);
//			// CardInfo cardinfo=CardInfoDao.getInstance(this).getCardInfo();
//			// CardInfo cardinfo=(CardInfo)
//			// TableDaoSimple.queryone(CardInfo.class,null,null);
//			// CardInfo cardinfo=CardInfoDao.getCardInfo();
//			String transactionPassword = clearedittext_transaction_password
//					.getText().toString();
//			if (userInfo == null || userInfo.getId() == null) {
//				LogHandler.writeLogFromString(TAG, "userInfo is null");
//				return;
//			}
//			// if(cardinfo==null)
//			// {
//			// LogHandler.writeLogFromString(TAG,"cardinfo is null");
//			// return;
//			// }
//			// BeikBankApi.getInstance().handlePurchaseInfo(PurchaseConfirmActivity.this,
//			// userInfo.getId(),amount,fund_sid,cardinfo.sid,"",transactionPassword,
//			// getPayForProductInfoHandler);
//			// PlayFundManager pfm=new PlayFundManager(act, icb);
//			// pfm.init(userInfo.getId(),amount,fund_sid,cardinfo.sid,"",transactionPassword);
//			// pfm.start();
//			ConfirmPayforPParam cpp = new ConfirmPayforPParam();
//			cpp.memberID = BeikBankApplication.getUserid();
//			cpp.tradeNo = rp.tradeNo;
//			cpp.tradePassword = transactionPassword;
//			ConfirmPayforPManager cpm = new ConfirmPayforPManager(this, icb2,
//					cpp);
//			cpm.start();
//			button_next.setEnabled(false);
//			dialog.show();
			break;
		case R.id.textview_agreement:
			intent2.putExtra("path", "1");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;
		case R.id.textview_agreement2:
			// startAimActivity(AgreementPurchase2Activity.class);
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
			
		case R.id.tv_fenxian:
			intent2.putExtra("path", "7");
			startActivity(intent2);
			// startAimActivity(AgreementPurchaseActivity.class);
			break;			
		case R.id.textview_forget_transaction_password:// 忘记交易密码
			clearedittext_transaction_password.setText("");
			String phonenumber = BeikBankApplication.mSharedPref
					.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
			Intent intent = new Intent(PurchaseConfirmActivity.this,
					ForgetPwdRealnameActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
			break;
		case R.id.linear_credit_card_master:
			startAimActivity(BankMasterActivity.class);
			break;
		}

	}

	ConfirmPay cp;

	/**
	 * 启动定时任务
	 */
	private void startTast() {
		Runnable rn = new Runnable() {

			@Override
			public void run() {
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(
//						BeikBankConstant.re_home, true);
//				Intent intent = new Intent(PurchaseConfirmActivity.this,
//						PurchaseSumbitInfo.class);
//				intent.putExtra(PurchaseSumbitInfo.index, cp);
//				intent.putExtra(PurchaseSumbitInfo.index1, cp.amount);
//				intent.putExtra(PurchaseSumbitInfo.index2, fi.name);
//				startActivity(intent);
//				finish();
			}
		};
		String s = "购买成功";
		if (cp.status.equals(FinalIndex.type1)) {
			s = "申请已提交";
		} else if (cp.status.equals(FinalIndex.type2)) {
			s = "购买成功";
		} else {
			s = "购买失败";
		}
		CustomToast.makeText(act, s, Toast.LENGTH_SHORT).show();
		Handler handler = new Handler();
		handler.postDelayed(rn, 1000);
	}

	ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				// ConfirmPay_data cd=(ConfirmPay_data) obj;
				// cp=cd.data;
				// //startTast();
				// Intent intent=getIntent();
				// // intent.putExtra(TypeUtil.jiaoyi_state,cp);
				// // JiaoYiHelp1 jy=new JiaoYiHelp1(act);
				// // jy.startTast();
				//
				// intent.setClass(act,JiaoYiXiangQingActivity.class);
				//
				// act.startActivity(intent);
				DingdanHelp ddh = new DingdanHelp();
				ddh.start(act, 2, dialog);

			} else {
				button_next.setEnabled(true);
				createDialog2();
			}
		}
	};

	ICallBack icb = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null && obj instanceof PlayFund_data) {
				PlayFund_data pd = (PlayFund_data) obj;
				PlayFund pf = pd.data;
				// CustomToast.makeText(act,getString(R.string.purchase_info_text4),
				// Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(PurchaseConfirmActivity.this,
						PurchaseSumbitInfo.class);
				intent.putExtra(BeikBankConstant.INTENT_PURCHASESUCCESS, pf);
				intent.putExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT,
						pf.planAmount);
				startActivity(intent);
				BeikBankApplication.mSharedPref.putSharePrefBoolean(
						BeikBankConstant.re_home, true);
			} else {
				createDialog2();
			}
		}
	};
	Dialog dialog4;

	// back
	public void createDialog2() {

		LinearLayout ll = new LinearLayout(this);
		View v = LayoutInflater.from(this).inflate(R.layout.redeem_dialog5, ll,
				false);

		// cacle
		TextView tv4 = (TextView) v.findViewById(R.id.tv_tv5);
		tv4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog4.dismiss();
			}
		});
		// BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_finrst_bank,true);
		dialog4 = DialogManager.getDiaolg1(this, v);
		dialog4.show();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
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
			String password = clearedittext_transaction_password.getText()
					.toString();
			if (password.length() >= 6 && checkbox_agreement.isChecked()
					) {
				button_next.setEnabled(true);
			} else {
				button_next.setEnabled(false);
			}
		}

	}

	// JsonHttpResponseHandler getPayForProductInfoHandler = new
	// JsonHttpResponseHandler(){
	//
	// @Override
	// public void onStart() {
	// super.onStart();
	// dialog=Utils.createPorgressDialogNoCancel(PurchaseConfirmActivity.this,
	// null);
	// dialog.show();
	// }
	//
	// @Override
	// public void onFinish() {
	// super.onFinish();
	// dialog.dismiss();
	// isOnclick=false;
	// }
	//
	// @Override
	// public void onFailure(Throwable error, String content) {
	// Utils.log(TAG, "onFailure()"+content);
	// isOnclick=false;
	// }
	//
	// @Override
	// public void onSuccess(Response response) {
	// Utils.log(TAG, "onSuccess()"+response.getResponseString());
	// Gson gson=new Gson();
	//
	// String
	// result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
	// if(result.equals("success")){
	// CustomToast.makeText(act,getString(R.string.purchase_info_text4),
	// Toast.LENGTH_SHORT).show();
	// purchaseInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(),
	// BeikBankConstant.TYPE_JSONDATA), PurchaseInfo.class);
	// Intent intent=new
	// Intent(PurchaseConfirmActivity.this,PurchaseSumbitInfo.class);
	// intent.putExtra(BeikBankConstant.INTENT_PURCHASESUCCESS, purchaseInfo);
	// intent.putExtra(BeikBankConstant.INTENT_PURCHASEAMOUNT, amount);
	// startActivity(intent);
	//
	// }else{
	// String
	// msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
	// textview_toast.setText(msg);
	// Utils.performAnimateForToast(linear_toast,toastAnimSet);
	// }
	//
	// }
	//
	// };

}
