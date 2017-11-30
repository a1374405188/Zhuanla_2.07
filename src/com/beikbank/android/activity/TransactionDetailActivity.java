package com.beikbank.android.activity;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ReturnHome;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



//交易详情 活期
public class TransactionDetailActivity extends BaseActivity implements
		OnClickListener {
	private TextView titleTv;
	private LinearLayout linear_left;
	private TransactionInfo transactionInfo;
	/**
	 * 购买状态父布局
	 */
	private LinearLayout rl1;
	/**
	 * 购买金额父布局
	 */
	private RelativeLayout rl2;
	/**
	 * 银行卡
	 */
	private RelativeLayout rl3;
	/**
	 * 红包父布局
	 */
	private RelativeLayout rl4;
	/**
	 * 错误提示
	 */
	private LinearLayout ll1;
	/**
	 * 购买状态图标
	 */
	private ImageView iv_iv1;
	/**
	 * 产品名称
	 */
	private TextView tv_tv1;
	/**
	 * 购买状态文本
	 */
	private TextView tv_tv2;
	/**
	 * 购买金额
	 */
	private TextView tv_tv3;
	/**
	 * 交易银行卡
	 */
	private TextView tv_tv4;
	/**
	 * 订单号
	 */
	private TextView tv_tv5;
	/**
	 * 交易时间
	 */
	private TextView tv_tv6;
	/**
	 * 温馨提示文本
	 */
	private TextView tv14;
	private TextView tv_error;

	/**
	 * 温馨提示
	 */
	private LinearLayout ll;
	/**
	 * 购买失败背景设色
	 */
	public final int color1 = 0xff747474;
	/**
	 * 购买失败背景设色2
	 */
	public final int color2 = 0xffb0b0b0;

	/**
	 * 购买处理中背景设色1
	 */
	public final int color3 = 0xff0976aa;
	/**
	 * 购买处理中背景设色2
	 */
	public final int color4 = 0xff22a7f0;

	/**
	 * 赎回1
	 */
	public final int color5 = 0xff00901f;
	/**
	 * 赎回2
	 */
	public final int color6 = 0xff22dd4a;
	/**
	 * 购买成功状态
	 */
	public int color7 = 0xffdd2233;
	/**
	 * 购买成功
	 */
	public int color8 = 0xfff9465b;

	private TextView tv_suhu;
	/**
	 * 红包金额
	 */
	private TextView tv7;
	/**
	 * 支付金额
	 */
	private TextView tv10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initIntent();
		setContentView(R.layout.activity_dingdan_huoqi);
		StateBarColor.init(this,0xffffffff);
		initView();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		if (transactionInfo == null) {
			return;
		}
		tv10.setText(transactionInfo.transactionAmount+"元");
		// 购买
		if (FinalIndex.jiaoyi_type1.equals(transactionInfo.tradeType)) {
			
			String s1=NumberManager.getSubString(transactionInfo.planAmount,transactionInfo.transactionAmount,0);
			if(!s1.equals("0"))
			{   
				 View v1=findViewById(R.id.v1);
				    v1.setVisibility(View.VISIBLE);
				rl4.setVisibility(View.VISIBLE);
				tv7.setText(s1+"元现金红包");
			}
			
			if (FinalIndex.type2.equals(transactionInfo.transactionStatus)) {
				rl1.setBackgroundColor(color7);
				rl2.setBackgroundColor(color8);
				
			} else if (FinalIndex.type3
					.equals(transactionInfo.transactionStatus)) {
				rl1.setBackgroundColor(color1);
				ll1.setBackgroundColor(color1);
				rl2.setBackgroundColor(color2);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
				ll.setVisibility(View.GONE);
				ll1.setVisibility(View.VISIBLE);
				tv_tv2.setText("购买失败");
				tv_error.setText(transactionInfo.retMsg);

			} else if (FinalIndex.type1
					.equals(transactionInfo.transactionStatus)) {
				rl1.setBackgroundColor(color3);
				rl2.setBackgroundColor(color4);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
				tv_tv2.setText("购买处理中");
			}
		} else if ("3".equals(transactionInfo.tradeType)) {
			if (FinalIndex.type2.equals(transactionInfo.transactionStatus)) {
				rl1.setBackgroundColor(color7);
				rl2.setBackgroundColor(color8);
				tv_tv2.setText("充值成功");
			} else if (FinalIndex.type3
					.equals(transactionInfo.transactionStatus)) {
				rl1.setBackgroundColor(color1);
				ll1.setBackgroundColor(color1);
				rl2.setBackgroundColor(color2);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
				ll.setVisibility(View.GONE);
				ll1.setVisibility(View.VISIBLE);
				tv_tv2.setText("充值失败");
				tv_error.setText(transactionInfo.retMsg);

			} else if (FinalIndex.type1
					.equals(transactionInfo.transactionStatus)) {
				rl1.setBackgroundColor(color3);
				rl2.setBackgroundColor(color4);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
				tv_tv2.setText("充值处理中");
			}
		}
		// 赎回
		else {
			if (FinalIndex.type1.equals(transactionInfo.tradeType)) {
				tv_suhu.setText("金额");
				ll.setVisibility(View.GONE);
				if (FinalIndex.type1.equals(transactionInfo.transactionStatus)) {
					tv_tv2.setText("活期到钱包处理中");
					rl1.setBackgroundColor(color3);
					rl2.setBackgroundColor(color4);
					iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
				} else if (FinalIndex.type2
						.equals(transactionInfo.transactionStatus)) {
					tv_tv2.setText("活期到钱包成功");
					rl1.setBackgroundColor(color5);
					rl2.setBackgroundColor(color6);
				} else {
					tv_tv2.setText("活期到钱包失败");
				}

				// rl3.setVisibility(View.GONE);
			} else {
				tv_tv1.setText("提现");
				
				tv_suhu.setText("金额");
//				transactionInfo.transactionStatus="3";
//				transactionInfo.retMsg="错误测试";
				if (FinalIndex.type1.equals(transactionInfo.transactionStatus))
				{
					tv_tv2.setText("处理中");
					rl1.setBackgroundColor(color5);
					rl2.setBackgroundColor(color6);
					iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
				}
				 else if (FinalIndex.type2
							.equals(transactionInfo.transactionStatus)) 
				 {
					 tv_tv2.setText("成功");
					 rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
				 }
				 else
				 {
					 tv_tv2.setText("失败");
					 rl1.setBackgroundColor(color1);
					 rl2.setBackgroundColor(color1);
					 ll1.setVisibility(View.VISIBLE);
					 tv_error.setText(transactionInfo.retMsg);
					 iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
				 }
				//tv14.setText(getString(R.string.dingdan_huoqi22));
				
			}
			 ll1.setVisibility(View.GONE);

		}
		if ("1".equals(transactionInfo.tradeMode)) {
			tv_tv4.setText("钱包");
			 ll1.setVisibility(View.GONE);
		}

	}

	public void initView() {

		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("订单详情");

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		tv_tv1 = (TextView) findViewById(R.id.tv1);
		tv_tv2 = (TextView) findViewById(R.id.tv2);
		tv_tv3 = (TextView) findViewById(R.id.tv3);
		tv_tv4 = (TextView) findViewById(R.id.tv4);
		tv_tv5 = (TextView) findViewById(R.id.tv5);
		tv_tv6 = (TextView) findViewById(R.id.tv6);
		tv7= (TextView) findViewById(R.id.tv7);
		tv10= (TextView) findViewById(R.id.tv10);
		tv14 = (TextView) findViewById(R.id.tv14);
		iv_iv1 = (ImageView) findViewById(R.id.iv1);
		tv_error = (TextView) findViewById(R.id.tv_error);
		tv_suhu = (TextView) findViewById(R.id.tv_suhu);

		ll = (LinearLayout) findViewById(R.id.ll);
		rl1 = (LinearLayout) findViewById(R.id.rl1);
		rl2 = (RelativeLayout) findViewById(R.id.rl2);
		rl3 = (RelativeLayout) findViewById(R.id.rl3);
		rl4 = (RelativeLayout) findViewById(R.id.rl4);
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		tv_tv1.setText(transactionInfo.productName);

		// tv_tv1.setText(transactionInfo.productName);
		String s0="";
		if(transactionInfo.planAmount==null||transactionInfo.planAmount.equals(""))
		{
			s0=transactionInfo.transactionAmount;
		}
		else
		{
			s0=transactionInfo.planAmount;
		}
		String s = NumberManager.getString(s0
				+ "", "1", 2)
				+ "元";
		tv_tv3.setText(s);
		tv_tv5.setText(transactionInfo.orderNumber);
		tv_tv6.setText(transactionInfo.purchaseDate);
		if ("0".equals(transactionInfo.tradeMode)) {
			CardInfo cardInfo = CardInfoDao.getCardInfo();
			// BankInfo
			// bankInfo=BankInfoDao.getInstance(TransactionDetailActivity.this).getBankInfoByType(cardInfo.getType());
			if (cardInfo != null && cardInfo.type != null) {
				BankList bl = BankListDao.getBankByType(cardInfo.getType());
				if (bl != null) {
					String cardNumber = cardInfo.getCardNumber();
					tv_tv4.setText(bl.bankName
							+ "(尾号"
							+ cardNumber.substring(cardNumber.length() - 4,
									cardNumber.length()) + ")");
				}
			}
		} else {
			tv_tv4.setText("钱包");
		}
	}

	public void initIntent() {
		transactionInfo = (TransactionInfo) getIntent().getExtras()
				.getSerializable(BeikBankConstant.INTENT_TRANSACTION);
	}

	@Override
	public void onClick(View v) {
       String s1=getIntent().getStringExtra("index7");
		switch (v.getId()) {
		case R.id.linear_left:
		  if(s1==null||s1.equals(""))
		  {
			finish();
		  }
		  else
		  {
			  ReturnHome.toHome(this, 1);
		  }
			break;
		}

	}

}
