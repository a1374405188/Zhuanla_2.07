package com.beikbank.android.activity;

import com.beikbank.android.activity.help.DingdanHelp;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.data.Remdom;
import com.beikbank.android.data.Remdom_data;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.HuoQiShuHui_data;
import com.beikbank.android.dataparam.HuoqiReturnParam;
import com.beikbank.android.dataparam2.HuoQiShuHuiParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.HandMoneyManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-7-3
 *活期赎回金额
 */
public class HuoqiReturnActivity extends BaseActivity2 implements OnClickListener
{   
	static String TAG="RedeemActivity";
    private Activity act=this;
	private TextView titleTv,textview_toast,textview_credit_card;
	private TextView textview_forget_transaction_password;


	private Button button_next;
	private LinearLayout linear_left,linear_toast;

	private ClearableEditText clearedittext_redeem_money;
	//手续费提示
	ImageView iv_info;
	  /**
     * 手续费提示文本
     */
	private LinearLayout ll;
	FundInfo fi;
	private Dialog dialog;
	public static String index="index";
	TextView tv_money;
	/**
	 * 订单id
	 */
	private String order_id;
	String money;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huoqi_return_confirm);
		StateBarColor.init(this,0xffffffff);
		 order_id=getIntent().getStringExtra("order_id");
		 money=getIntent().getStringExtra("money");
		 initView();
		
	}

	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		  if(obj!=null)
		  {   
//			  Remdom_data rd=(Remdom_data) obj;
//			  Remdom r=rd.data;
//			  TransactionInfo ti=new TransactionInfo();
//			  String money=(String) getIntent().getSerializableExtra("money");
//					  
//			  ti.tradeType="1";
//			  ti.productType="1";
//			  ti.orderNumber=r.orderNumber;
//			  ti.purchaseDate=r.dealTime;
//			  ti.transactionStatus=r.status;
//			  ti.transactionAmount=money;
//			  ti.planAmount=money;
//			  ti.productName="赚啦活期";
//			//  Intent intent=new Intent(HuoqiReturnActivity.this,TransactionDetailActivity.class);
//			  Intent intent=new Intent(HuoqiReturnActivity.this,HuoqiReturnStatusActivity.class);
//			  intent.putExtra(BeikBankConstant.INTENT_PURCHASESUCCESS,rd.data);
//			  intent.putExtra(BeikBankConstant.INTENT_TRANSACTION,ti);
//			  startActivity(intent);
//			  finish();
		  
			DingdanHelp ddh = new DingdanHelp();
			ddh.start(act,4, dialog);
		}
		else
		{
			dialog.dismiss();
		}
		}
	};

	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
    	AnimatorSet toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("转让确认");
 
		iv_info=(ImageView) findViewById(R.id.iv_info);
		
		tv_money=(TextView) findViewById(R.id.tv_money);
		tv_money.setText(money+"元");
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		ll=(LinearLayout) findViewById(R.id.ll_ll1);
		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		textview_credit_card=(TextView)findViewById(R.id.textview_credit_card);
		//CardInfo cardInfo=CardInfoDao.getInstance(RedeemActivity.this).getCardInfo();
		//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
		String s=getIntent().getStringExtra(BeikBankConstant.INTENT_TOTALCAPITAL);

		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		clearedittext_redeem_money=(ClearableEditText)findViewById(R.id.clearedittext_transaction_password);
		setSecondPoint(clearedittext_redeem_money);
		
		textview_forget_transaction_password=(TextView)findViewById(R.id.textview_forget_transaction_password);
		textview_forget_transaction_password.setOnClickListener(this);
		
	}
	private void addData()
	{
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					HuoQiShuHui_data hd=(HuoQiShuHui_data) obj;
					if("0000".contains(hd.header.re_code))
					{
						Intent intent=new Intent(act,HomeActivity4.class);
						startActivity(intent);
					}
				}
				
			}
		};
		HuoQiShuHuiParam hp=new HuoQiShuHuiParam();
		hp.order_id=order_id;
		hp.tra_password=MD5.md5s32(clearedittext_redeem_money.getText().toString());
		hp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb,hp);
		tym2.start();
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:	
			 addData();
			break;
		case R.id.textview_forget_transaction_password://忘记交易密码
			//clearedittext_transaction_password.setText("");
			String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
			Intent intent=new Intent(this,ForgetPwdRealnameActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
			startActivity(intent);
			break;	
		}

	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	
	public void setSecondPoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
//				if (s.toString().contains(".")) {
//					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
//						s = s.toString().subSequence(0,
//								s.toString().indexOf(".") + 3);
//						editText.setText(s);
//						editText.setSelection(s.length());
//					}
//				}
//				if (s.toString().trim().substring(0).equals(".")) {
//					s = "0" + s;
//					editText.setText(s);
//					editText.setSelection(2);
//				}
//
//				if (s.toString().startsWith("0")
//						&& s.toString().trim().length() > 1) {
//					if (!s.toString().substring(1, 2).equals(".")) {
//						editText.setText(s.subSequence(0, 1));
//						editText.setSelection(1);
//						return;
//					}
//				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				ll_error.clearAnimation();
				ll_error.setVisibility(View.INVISIBLE);
				String money=clearedittext_redeem_money.getText().toString();
				if(money.length()>0){
					button_next.setEnabled(true);
				}else{
					button_next.setEnabled(false);
				}
			}

		});

	}	
}
