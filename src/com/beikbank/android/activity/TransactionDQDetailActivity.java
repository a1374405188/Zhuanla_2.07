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
import com.beikbank.android.data2.getDingDanXiangQin;
import com.beikbank.android.data2.getDingDanXiangQin_data;
import com.beikbank.android.dataparam2.getDingDanXiangQinParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ReturnHome;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;


 
  
//交易详情 定期
public class TransactionDQDetailActivity extends BaseActivity implements OnClickListener{
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
	private  TextView tv_error;
	/**
	 * 到期时间父布局
	 */
	//private RelativeLayout rl3;
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
	 * 订单号
	 */
	private TextView tv_tv5;
	/**
	 * 交易时间
	 */
	private TextView tv_tv6;
	
	/**
	 * 温馨提示
	 */
	private LinearLayout ll;
	
	
	/**
	 * 年化收益率
	 */
	private TextView tv12;
	/**
	 * 期限
	 */
	private TextView tv13;

	/**
	 * 银行卡
	 */
	private TextView tv10;

	/**
	 * 购买失败背景设色
	 */
	public  int color1=0xff747474;
	/**
	 * 购买失败背景设色2
	 */
	public  int color2=0xffb0b0b0;
	
	/**
	 * 购买处理中背景设色1
	 */
	public  int color3=0xff0976aa;
	/**
	 * 购买处理中背景设色2
	 */
	public  int color4=0xff22a7f0;
	
	/**
	 * 赎回1
	 */
	public  int color5=0xff00901f;
	/**
	 * 赎回2
	 */
	public  int color6=0xff22dd4a;
	/**
	 * 购买成功状态
	 */
	public  int color7=0xffdd2233;
	/**
	 * 购买成功
	 */
	public  int color8=0xfff9465b;
	/**
	 * 成功附加信息
	 */
	private LinearLayout ll2;
	/**
	 * 红包金额
	 */
	private TextView tv7;
	/**
	 * 支付金额
	 */
	private TextView tv11;
	/**
	 * 红包父布局
	 */
	private RelativeLayout rl4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initIntent();
		setContentView(R.layout.activity_dingdan_dingqi);
		StateBarColor.init(this,0xffffffff);
		initView();
		transactionInfo=(TransactionInfo) getIntent().getSerializableExtra("ti");
		//initData();
		addData();
		//initData();
	}
	private void addData()
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
				 getDingDanXiangQin_data gd=(getDingDanXiangQin_data) obj;
				 getDingDanXiangQin gdd=gd.body;
				 transactionInfo=new TransactionInfo();
				 transactionInfo.extraRate=gdd.increase_interest_return_rate;
				 transactionInfo.orderNumber=gdd.order_id;
				 transactionInfo.planAmount=gdd.amount;
				 transactionInfo.productName=gdd.product_name;
				 transactionInfo.purchaseDate=gdd.create_time;
				 transactionInfo.termbondPeriod=gdd.term;
				 transactionInfo.tradeType=gdd.order_type;
				 transactionInfo.transactionStatus=gdd.order_status;
				 transactionInfo.transactionAmount=gdd.amount;
				 transactionInfo.yearRate=gdd.benchmark_return_rate;
				  
				}
				initData();
			}
		};
		String id=getIntent().getStringExtra("order_id");
		if(id!=null&&!"".equals(id))
		{
			
		
		getDingDanXiangQinParam gdd=new getDingDanXiangQinParam();
		gdd.order_id=getIntent().getStringExtra("order_id");
		TongYongManager2 tym=new TongYongManager2(this, icb,gdd);
		
		tym.start();
		}
		else
		{initData();
			
		}
		
		
	}
	
	
	
	/**
	 * 初始化数据
	 */
	private void initData()
	{   
		String s1="";
		if(transactionInfo==null)
		{
			return;
		}
		s1=NumberManager.getAddString(transactionInfo.yearRate,transactionInfo.extraRate,8);
		s1=NumberManager.getString(s1,"100",2);
		tv12.setText(s1+"%");
		String money=NumberManager.getGeshiHua(transactionInfo.planAmount,1);
		tv_tv3.setText(money+"元");
		tv11.setText(transactionInfo.transactionAmount+"元");
		tv_tv1.setText(transactionInfo.productName);
		tv_tv5.setText(transactionInfo.orderNumber);
		tv_tv6.setText(transactionInfo.purchaseDate);
		tv13.setText(transactionInfo.termbondPeriod+"天");
		LinearLayout ll_all=(LinearLayout) findViewById(R.id.ll_all);
		if("1".equals(transactionInfo.tradeType))
		{
			tv_tv1.setText("充值");
			ll_all.setVisibility(View.GONE);
		}
		else if("5".equals(transactionInfo.tradeType))
		{
			tv_tv1.setText("提现");
			ll_all.setVisibility(View.GONE);
		}
		
		
		if("3".equals(transactionInfo.tradeType))
		{
			tv10.setText("钱包");
		}
		else
		{
			String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
			String bank_name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
			
			 tv10.setText(bank_name
						+"(尾号"+bank.substring(bank.length()-4,bank.length())+")");
		}
		
		//购买
	  if(FinalIndex.jiaoyi_type1.equals(transactionInfo.tradeType))
	  {   
		  String s2=NumberManager.getSubString(transactionInfo.planAmount,transactionInfo.transactionAmount,0);
		  if(!s2.equals("0"))                       
		  {     
			    LinearLayout ll4=(LinearLayout) findViewById(R.id.ll4);
			    ll4.setVisibility(View.VISIBLE);
				rl4.setVisibility(View.VISIBLE);
				tv7.setText(s2+"元现金红包");
		  }
		  
	
		if(FinalIndex.type2.equals(transactionInfo.transactionStatus))
		{
			tv_tv2.setText("购买成功");
			ll2.setVisibility(View.VISIBLE);
		    s1=NumberManager.getString(transactionInfo.transactionAmount,"1",2)+"元";
			
			//s1=NumberManager.getString(transactionInfo.yearRate,"100",2);
			//tv12.setText(s1+"%");
			tv13.setText(transactionInfo.termbondPeriod+"天");
			iv_iv1.setBackgroundResource(R.drawable.dingdan_ok);
			rl1.setBackgroundColor(color7);
			rl2.setBackgroundColor(color8);
		}
		else if(FinalIndex.type3.equals(transactionInfo.transactionStatus))
		{   
			
			
			rl1.setBackgroundColor(color1);
			ll1.setBackgroundColor(color1);
			rl2.setBackgroundColor(color2);
			iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
			ll.setVisibility(View.GONE);
			tv_tv2.setText("购买失败");
			ll1.setVisibility(View.VISIBLE);
			tv_error.setText(transactionInfo.retMsg);
			//rl3.setVisibility(View.GONE);
		}
		else if(FinalIndex.type1.equals(transactionInfo.transactionStatus))
		{
			rl1.setBackgroundColor(color3);
			rl2.setBackgroundColor(color4);
			iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
			tv_tv2.setText("购买队列中");
			tv13.setText(transactionInfo.termbondPeriod+"天");
			//s1=NumberManager.getString(transactionInfo.yearRate,"100",2);
			//tv12.setText(s1+"%");
			//rl3.setVisibility(View.GONE);
		}
	  }
	  //钱包购买
	  else if("3".equals(transactionInfo.tradeType))
	  {

			if(FinalIndex.type2.equals(transactionInfo.transactionStatus))
			{
				tv_tv2.setText("购买成功");
				ll2.setVisibility(View.VISIBLE);
			    s1=NumberManager.getString(transactionInfo.transactionAmount,"1",2)+"元";
				
				//s1=NumberManager.getString(transactionInfo.yearRate,"100",2);
				//tv12.setText(s1+"%");
				tv13.setText(transactionInfo.termbondPeriod+"天");
				iv_iv1.setBackgroundResource(R.drawable.dingdan_ok);
				rl1.setBackgroundColor(color7);
				rl2.setBackgroundColor(color8);
			}
			else if(FinalIndex.type3.equals(transactionInfo.transactionStatus))
			{   
				
				
				rl1.setBackgroundColor(color1);
				ll1.setBackgroundColor(color1);
				rl2.setBackgroundColor(color2);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_fail);
				ll.setVisibility(View.GONE);
				tv_tv2.setText("购买失败");
				ll1.setVisibility(View.VISIBLE);
				tv_error.setText(transactionInfo.retMsg);
				//rl3.setVisibility(View.GONE);
			}
			else if(FinalIndex.type1.equals(transactionInfo.transactionStatus))
			{
				rl1.setBackgroundColor(color3);
				rl2.setBackgroundColor(color4);
				iv_iv1.setBackgroundResource(R.drawable.dingdan_doing);
				tv_tv2.setText("购买队列中");
				tv13.setText(transactionInfo.termbondPeriod+"天");
				//s1=NumberManager.getString(transactionInfo.yearRate,"100",2);
				//tv12.setText(s1+"%");
				//rl3.setVisibility(View.GONE);
			}
	  }
	  //赎回
	  else
	  {
		    rl1.setBackgroundColor(color5);
			rl2.setBackgroundColor(color6);
			tv_tv2.setText("取现申请已提交");
	  }
	  if("1".equals(transactionInfo.tradeMode))
	  {
		  tv10.setText("钱包");
	  }
	  if("4".equals(transactionInfo.tradeType))
	  {
		  tv10.setText("活期");
	  }
	}
	public void initView(){

		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("订单详情");
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
        
		tv_tv1=(TextView) findViewById(R.id.tv1);
		tv_tv2=(TextView) findViewById(R.id.tv2);
		tv_tv3=(TextView) findViewById(R.id.tv3);
		tv7= (TextView) findViewById(R.id.tv7);
		tv_tv5=(TextView) findViewById(R.id.tv5);
		tv_tv6=(TextView) findViewById(R.id.tv6);
		iv_iv1=(ImageView) findViewById(R.id.iv1);
		tv_error=(TextView)findViewById(R.id.tv_error);
		
	    ll2=(LinearLayout) findViewById(R.id.ll2);
	    tv10=(TextView) findViewById(R.id.tv10);
	    tv11=(TextView) findViewById(R.id.tv11);
	    tv12=(TextView) findViewById(R.id.tv12);
		tv13=(TextView) findViewById(R.id.tv13);
		rl4 = (RelativeLayout) findViewById(R.id.rl4);
		
		ll=(LinearLayout) findViewById(R.id.ll);
		rl1=(LinearLayout) findViewById(R.id.rl1);
		rl2=(RelativeLayout) findViewById(R.id.rl2);
		
		//rl3=(RelativeLayout) findViewById(R.id.rl3);
		ll1=(LinearLayout) findViewById(R.id.ll1);
		//tv_tv1.setText(transactionInfo.productName);
		
		//tv_tv1.setText(transactionInfo.productName);
//		String s0="";
//		if(transactionInfo.planAmount==null||transactionInfo.planAmount.equals(""))
//		{
//			s0=transactionInfo.transactionAmount;
//		}
//		else
//		{
//			s0=transactionInfo.planAmount;
//		}
//		String s=NumberManager.getString(s0+"","1",2)+"元";
//		tv_tv3.setText(s);
//		tv_tv5.setText(transactionInfo.orderNumber);
//		tv_tv6.setText(transactionInfo.purchaseDate);
//		if("0".equals(transactionInfo.tradeMode))
//		{
//		CardInfo cardInfo=CardInfoDao.getCardInfo();
//		//BankInfo bankInfo=BankInfoDao.getInstance(TransactionDetailActivity.this).getBankInfoByType(cardInfo.getType());
//		if(cardInfo!=null&&cardInfo.type!=null)
//		{
//		BankList bl=BankListDao.getBankByType(cardInfo.getType());
//		if(bl!=null)
//		{
//		 String cardNumber=cardInfo.getCardNumber();
//		 tv10.setText(bl.bankName
//				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//		}
//		}
//		}
//		else
//		{
//			tv10.setText("钱包");
//		}
	}
	
    @Override
	protected void onDestroy() {
	
		super.onDestroy();
		rl1=null;
		rl2=null;
	}
	public void initIntent()
    {
    	transactionInfo=(TransactionInfo)getIntent().getExtras().getSerializable(BeikBankConstant.INTENT_TRANSACTION);
    }
	@Override
	public void onClick(View v) {
		 String s1=getIntent().getStringExtra("index7");
		switch(v.getId()){
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
