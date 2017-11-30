package com.beikbank.android.activity;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.Qianbao1;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao3;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-22
 *充值，提现的状态
 */
public class QianbaoActivity5 extends BaseActivity1 implements View.OnClickListener
{
	TextView title;
	TextView right;
    /**
     * return
     */
    LinearLayout ll;
	/**
	 * 购买状态父布局
	 */
	private LinearLayout rl1;
	/**
	 * 购买金额父布局
	 */
	private RelativeLayout rl2;
	/**
	 * 交易账户
	 */
	private RelativeLayout rl3;
	/**
	 * 错误提示
	 */
	private LinearLayout ll1;
	/**
	 * 购买状态图标
	 */
	private ImageView iv_iv1;
	/**
	 * 错误提示
	 */
	private  TextView tv_error;
    /**
     * 充值或是取现
     */
   TextView tv1;
   /**
    * 充值状态文本
    */
   TextView tv2;
   /**
    * 充值金额文本
    */
   TextView tv_suhu;
   /**
    * 充值金额
    */
   TextView tv3;
   /**
    * 交易银行卡
    */
   TextView tv4;
   /**
    * 交易订单号
    */
   TextView tv5;
   /**
    * 交易订单号
    */
   TextView tv6;
   /**
    * 交易时间
    */
   TextView tv7;
   /**
	 * 购买失败背景设色
	 */
	public final int color1=0xff747474;
	/**
	 * 购买失败背景设色2
	 */
	public final int color2=0xffb0b0b0;
	
	/**
	 * 购买处理中背景设色1
	 */
	public final int color3=0xff0976aa;
	/**
	 * 购买处理中背景设色2
	 */
	public final int color4=0xff22a7f0;
	
	/**
	 * 赎回1
	 */
	public final int color5=0xff00901f;
	/**
	 * 赎回2
	 */
	public final int color6=0xff22dd4a;
	/**
	 * 购买成功状态
	 */
	public  int color7=0xffdd2233;
	/**
	 * 购买成功
	 */
	public  int color8=0xfff9465b;
   private Qianbao1 qb1;
   private Qianbao3 qb3;
   /**
    * 1交易记录跳过来，2充值成功后跳过来
    */
   private int index2=2;
   /**
    * 1充值2提现
    */
   private int index1=1;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		index1=getIntent().getIntExtra("index1",2);
		index2=getIntent().getIntExtra("index2",2);
		setContentView(R.layout.activity_qianbao5);
		StateBarColor.init(this,0xffffffff);
		initView();
		initData();
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_after_pay,true);
	}
	private  void initData()
	{  
	  if(index2==2)
	  {
		qb1=(Qianbao1) getIntent().getSerializableExtra("index");
		if(qb1!=null)
		{   
			//处理中
			if(FinalIndex.qianbao_type1.equals(qb1.status))
			{   
				
				rl1.setBackgroundColor(color3);
				rl2.setBackgroundColor(color4);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
			
				if(index1==2)
				{
					tv2.setText("处理中");
					tv1.setText("提现");
					tv_suhu.setText("金额");
					rl1.setBackgroundColor(color5);
					rl2.setBackgroundColor(color6);
				}
				else
				{
					tv2.setText("处理中");
					tv1.setText("充值");
					tv_suhu.setText("金额");
				}
				
			}
			//成功
			else if(FinalIndex.qianbao_type2.equals(qb1.status))
			{   
				rl1.setBackgroundColor(color7);
				rl2.setBackgroundColor(color8);
				
				
				if(index1==2)
				{
					tv2.setText("成功");
					tv1.setText("提现");
					tv_suhu.setText("金额");
					rl1.setBackgroundColor(color5);
					rl2.setBackgroundColor(color6);
				}
				else
				{
					tv2.setText("成功");
					tv1.setText("充值");
					tv_suhu.setText("金额");
				}
			}
			//失败
			else
			{   
				rl1.setBackgroundColor(color1);
				ll1.setBackgroundColor(color1);
				rl2.setBackgroundColor(color2);
				if(index1==2)
				{
					tv2.setText("失败");
					tv1.setText("提现");
					tv_suhu.setText("金额");
					rl1.setBackgroundColor(color5);
					rl2.setBackgroundColor(color6);
				}
				else
				{
					tv2.setText("失败");
					tv1.setText("充值");
					tv_suhu.setText("金额");
				}
				
				iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
				ll.setVisibility(View.GONE);
				ll1.setVisibility(View.VISIBLE);
				//tv2.setText("充值失败");
			}
		}
		//String s3=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.INTENT_AMOUNT);
		String s3=getIntent().getStringExtra(TypeUtil.jiaoyi_money);
		s3=NumberManager.getString(s3,"1",2);
		tv3.setText(s3+"元");
		tv5.setText(qb1.orderNumber);
		tv6.setText(qb1.dealTime);
	  }
		else
		{
			qb3=(Qianbao3)getIntent().getSerializableExtra("index");
			if(qb3!=null)
			{   
				//处理中
				if(FinalIndex.qianbao_type1.equals(qb3.transactionStatus))
				{   
					rl1.setBackgroundColor(color3);
					rl2.setBackgroundColor(color4);
					iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
					if(qb3.tradeType.equals("2"))
					{
						tv2.setText("处理中");
						tv1.setText("提现");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("0"))
					{
						tv2.setText("处理中");
						tv1.setText("购买");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("1"))
					{
						tv2.setText("处理中");
						tv1.setText("活期赎回");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("4"))
					{
						tv2.setText("处理中");
						tv1.setText("定期到期");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color7);
						rl2.setBackgroundColor(color8);
					}
					else
					{
						tv2.setText("处理中");
						tv1.setText("充值");
						tv_suhu.setText("充值金额");
					}
				}
				//成功
				else if(FinalIndex.qianbao_type2.equals(qb3.transactionStatus))
				{   
					rl1.setBackgroundColor(color7);
					rl2.setBackgroundColor(color8);
					tv2.setText("充值成功");
					if(qb3.tradeType.equals("2"))
					{
						tv2.setText("成功");
						tv1.setText("提现");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("0"))
					{
						tv2.setText("成功");
						tv1.setText("购买");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("1"))
					{
						tv2.setText("成功");
						tv1.setText("活期赎回");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color7);
						rl2.setBackgroundColor(color8);
					}
					else if(qb3.tradeType.equals("4"))
					{
						tv2.setText("成功");
						tv1.setText("定期到期");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color7);
						rl2.setBackgroundColor(color8);
					}
					else
					{
						tv2.setText("成功");
						tv1.setText("充值");
						tv_suhu.setText("金额");
					}
					
					
				}
				//失败
				else
				{   
					if(qb3.tradeType.equals("2"))
					{
						tv2.setText("失败");
						tv1.setText("提现");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("0"))
					{
						tv2.setText("失败");
						tv1.setText("购买");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("1"))
					{
						tv2.setText("失败");
						tv1.setText("活期赎回");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else if(qb3.tradeType.equals("4"))
					{
						tv2.setText("购买失败");
						tv1.setText("定期到期");
						tv_suhu.setText("金额");
						rl1.setBackgroundColor(color5);
						rl2.setBackgroundColor(color6);
					}
					else
					{
						tv2.setText("失败");
						tv1.setText("充值");
						tv_suhu.setText("金额");
					}
					tv_error.setText(qb3.retMsg);
					rl1.setBackgroundColor(color1);
					ll1.setBackgroundColor(color1);
					rl2.setBackgroundColor(color2);
					iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
					ll1.setVisibility(View.VISIBLE);
				}
				String s3=NumberManager.getString(qb3.transactionAmount,"1",2);
				tv3.setText(s3+"元");
				tv5.setText(qb3.orderNumber);
				tv6.setText(qb3.purchaseDate);
			}
			 if(qb3.tradeType.equals("0"))
			  {
				  rl3.setVisibility(View.GONE);
			  }
			 else if(qb3.tradeType.equals("1")||qb3.tradeType.equals("4")||qb3.tradeType.equals("5"))
			 {
				 tv4.setText("钱包");
			 }
		}
	  
	 
	}
	private void setColor(String type)
	{
		
	}
    private  void initView()
    {    
    	ll1=(LinearLayout) findViewById(R.id.ll1);
    	tv_error=(TextView)findViewById(R.id.tv_error);
    	 ll=(LinearLayout)findViewById(R.id.linear_left);
         title=(TextView)findViewById(R.id.titleTv);
         title.setText("订单详情");
         tv1=(TextView) findViewById(R.id.tv1);
         tv2=(TextView) findViewById(R.id.tv2);
         tv3=(TextView) findViewById(R.id.tv3);
         tv4=(TextView) findViewById(R.id.tv4);
         tv5=(TextView) findViewById(R.id.tv5);
         tv6=(TextView) findViewById(R.id.tv6);
         tv7=(TextView) findViewById(R.id.tv7);
         tv7=(TextView) findViewById(R.id.tv7);
         tv_suhu=(TextView) findViewById(R.id.tv_suhu);
          rl1=(LinearLayout) findViewById(R.id.rl1);
 		 rl2=(RelativeLayout) findViewById(R.id.rl2);
 		 rl3=(RelativeLayout) findViewById(R.id.rl3);
 		 ll1=(LinearLayout) findViewById(R.id.ll1);
 		 iv_iv1=(ImageView) findViewById(R.id.iv1);
         ll.setOnClickListener(this);
         
         CardInfo cardInfo=CardInfoDao.getCardInfo();
 		
 		if(cardInfo!=null&&cardInfo.type!=null)
 		{
 		  BankList bl=BankListDao.getBankByType(cardInfo.getType());
 		  if(bl!=null)
 		 {
 		  String cardNumber=cardInfo.getCardNumber();
 		  tv4.setText(bl.bankName
 				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
 		 }
 		}
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
            finish();
			break;
		default:
			break;
		}
		
	}
	
   
}
