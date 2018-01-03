package com.beikbank.android.activity;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.DingqiP;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.FundLimit;
import com.beikbank.android.data.FundLimit_data;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data.HuoqiToR_data;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data.UserCapital2;
import com.beikbank.android.data.UserCapital2_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.CreateDingDan;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.HuoQiYiGou;
import com.beikbank.android.data2.HuoQiYiGou_data;
import com.beikbank.android.data2.MiMaOrDuanXin;
import com.beikbank.android.data2.MiMaOrDuanXin_data;
import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.data2.getAllYouHuiQuan_data;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.HuoqiToRParam;
import com.beikbank.android.dataparam.ReqPayforPParam;
import com.beikbank.android.dataparam.ReqPayforPParam2;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.HuoQiYiGouParam;
import com.beikbank.android.dataparam2.MiMaOrDuanXinParam;
import com.beikbank.android.dataparam2.getAllYouhuiQuanParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.FundLimitManager;
import com.beikbank.android.net.impl.Qianbao6Manager;
import com.beikbank.android.net.impl.ReqPayforPManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.net.impl.UserCapital2Manager;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil2_v2;
import com.beikbank.android.utils.hongbao.HongbaoUtil_v2;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



import android.R.mipmap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-25
 * 定期购买
 */
public class DingqiGoumaiActivity extends BaseActivity1 implements OnClickListener{

	private Activity act=this;
	private final String TAG="PurchaseActivity";
	private TextView titleTv,textview_toast;
	private Button button_next;
	private ClearableEditText clearedittext_transaction_money;
	private LinearLayout linear_toast,linear_left;
	private AnimatorSet toastAnimSet;
	private String sid;
    public static String index="index";
    /**
     * 银行卡，钱包余额付布局
     */
    LinearLayout ll1,ll2;
    /**
     * 钱包余额
     */
    TextView tv2;
    /**
     * 最多可购买金额父布局
     */
    LinearLayout ll_ll1;
    /**
     * 最多可购买金额
     */
    TextView tv_tv1;
    DingqiP2 dp;
    BankList bl;
    /**
	 * 单笔限额
	 */
	double singleLimit;
	 private  ReqPayforPParam2 rp;
	ArrayList<getAllYouHuiQuan> list_yhq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_purchase);
		StateBarColor.init(this,0xffffffff);
		 gcp=(GetChanPin) getIntent().getSerializableExtra("gcp");
		initView();
		initData();
		
         setZuiDuo();
	}
	getQianBao gq;
	 GetChanPin gcp;
	 HuoQiYiGou yg;
	//初始化红包数据
	private void initData()
	{

        ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{  
					getQianBao_data gb=(getQianBao_data) obj;
					gq=gb.body.card;
					String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
					Log.d("pay",pay);
					TextView tv_bank=(TextView) findViewById(R.id.tv_bank);
					TextView tv_single=(TextView) findViewById(R.id.tv_single);
					if("2".equals(pay))
					{
				
				
					tv_bank.setText(gq.bank_name+"(尾号"+gq.acc_number.substring(gq.acc_number.length()-4,gq.acc_number.length())+")");
					tv_single.setText("单笔最低限额"+gq.min_amount+"元,最高"+gq.max_amount+"万元");
				    
					}
					else
					{  
//						TextView tv3=(TextView) findViewById(R.id.tv3);
//						tv3.setText("钱包");
//						tv_single.setVisibility(View.GONE);
//						tv_bank.setText(NumberManager.getGeshiHua(gq.acc_amount,2)+"元");
						
						
						tv_single.setText(NumberManager.getGeshiHua(gq.acc_amount,2)+"元");
						tv_bank.setText("钱包");
					}
				}
			}
		};
    	getQianBaoParam gqp=new getQianBaoParam();
		gqp.acc_type_id="1";
	    gqp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb, gqp);
		tym2.start();
		
		
		
		

		ICallBack icb_hp=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					 HuoQiYiGou_data hd=(HuoQiYiGou_data) obj;
					 yg=hd.body;
					 
					 setZuiDuo();
				}
			}
		};
		
		HuoQiYiGouParam hp=new HuoQiYiGouParam();
		hp.product_id=gcp.product_id;
		hp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym3=new TongYongManager2(act, icb_hp,hp);
		tym3.start();
		
		
		
		
		//初始化红包
      ICallBack icb_yhq=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					getAllYouHuiQuan_data gd=(getAllYouHuiQuan_data) obj;
					list_yhq=gd.body;
					  
				}
				
			}
		};
		getAllYouhuiQuanParam gp=new getAllYouhuiQuanParam();
		gp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym=new TongYongManager2(this, icb_yhq,gp);
		tym.start();
	}
	private void getyzm()
	{
		//得到密码或者短信支付
				ICallBack icb_mm=new ICallBack() {
					
					@Override
					public void back(Object obj) {
						if(obj!=null)
						{   
							MiMaOrDuanXin_data md=(MiMaOrDuanXin_data) obj;
							if("0000".equals(md.header.re_code))
							{
							
							MiMaOrDuanXin mmd=md.body;
							BeikBankApplication.setSharePref(BeikBankConstant.mima_duanxin,mmd.status);
						    Log.d("mima",mmd.status);
							}
							
							
							
						}
						
					}
				};
				
				MiMaOrDuanXinParam mm=new MiMaOrDuanXinParam();
				mm.user_code=BeikBankApplication.getUserCode();
				TongYongManager2 tym4=new TongYongManager2(act, icb_mm,mm);
				tym4.start();
	}
	  private void initView1()
	    {
	    	TextView tv_name=(TextView) findViewById(R.id.tv_name);
	    	TextView tv_shouyi=(TextView) findViewById(R.id.tv_shouyi);
	    	TextView tv_qixian=(TextView) findViewById(R.id.tv_qixian);
	    	if(gcp!=null)
	    	{
	    		tv_name.setText(gcp.product_name);
	    		
	    		if("4".equals(gcp.product_type_pid))
	    		{
	    			tv_qixian.setText("灵活存取");
	    		}
	    		else
	    		{
	    			tv_qixian.setText(gcp.term+"天");
	    		}
	    		String shouyi=NumberManager.getAddString(gcp.increase_interest_return_rate,gcp.benchmark_return_rate,6);
	    	    shouyi=NumberManager.getString(shouyi,"100",2)+"%";
	    		tv_shouyi.setText(shouyi);
	    	}
	    }
	private void next(CreateDingDan obj)
	{
		 String s1=BeikBankApplication.getSharePref(BeikBankConstant.mima_duanxin);
	       String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
	    	Intent mIntent=getIntent();
		 mIntent.putExtra(TypeUtil.jiaoyi_money, money);
		 mIntent.putExtra("gqb",gq);
		 if(obj!=null)
		 {
		   mIntent.putExtra("cdd",obj);
		 }
		ArrayList<getAllYouHuiQuan> list2= HongbaoUtil_v2.select1(list_yhq);
		if(list2.size()>0)
		 {
			    mIntent.setClass(act,DingdanConfimActivity2.class);
				startActivity(mIntent);
				finish();
	    	   return ;
		 }
	       if("1".equals(s1)||"3".equals(pay))
	       {
	    	   
	    	   
				
			
				mIntent.setClass(act,DingqiGouMaiConfirmActivity.class);
				startActivity(mIntent);
				finish();
	    	   return ;
	    	   
	       }
	       
			mIntent.setClass(act,QueRenJiaoYiActivity.class);
			startActivity(mIntent);
			finish();
	}
	private void createDingdan(getQianBao  gqb)
	{
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					CreateDingDan_data cddd=(CreateDingDan_data) obj;
//					cdd=cddd.body;
//					ChongZhiParam czp=new ChongZhiParam();
//					czp.order_id=cddd.body.order_id;
//					czp.user_code=BeikBankApplication.getUserCode();
//					TongYongManager2 tym2=new TongYongManager2(act, icb0, czp);
//					tym2.start();
				  next(cddd.body);
				}
				
			}
		};
		 
		 String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
		 CreateDingDanParam cdp=new CreateDingDanParam();
		 cdp.acc_id=gqb.acc_id;
		 cdp.acc_number=gqb.acc_number;
		 cdp.amount=money;
		
		 cdp.order_type=pay;
		 cdp.pro_id=gcp.product_id;
		 cdp.pro_type=gcp.type;
		 cdp.user_code=BeikBankApplication.getUserCode();
		 
		 TongYongManager2 tym2=new TongYongManager2(act, icb,cdp);
		 tym2.start();
	}
	String play;
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.purchase));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
        
		ll_ll1=(LinearLayout) findViewById(R.id.ll_ll1);
		tv_tv1=(TextView)findViewById(R.id.tv_tv1);
		ll_ll1.setVisibility(View.VISIBLE);
		if(gcp!=null)
		{
			String s=NumberManager.getString(gcp.pro_share,"1",2);
//			double s1=NumberManager.StoD(dp.remainAmount);
//			double s2=NumberManager.StoD(dp.singleAmountLimit);
//			if(s2>0)
//			{
//				if(s2<s1)
//				{
//					s=dp.singleAmountLimit;
//				}
//			}
			tv_tv1.setText(NumberManager.getString("1",s,2)+"元");
		}
		
		
		clearedittext_transaction_money=(ClearableEditText)findViewById(R.id.clearedittext_transaction_money);
		clearedittext_transaction_money.addTextChangedListener(new TextWatcherListener());

		button_next=(Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		initView1();
//		sid=getIntent().getStringExtra(BeikBankConstant.INTENT_SID);
//		
//		
//		
//		TextView textview_purchase_single=(TextView)findViewById(R.id.textview_purchase_single);
//		TextView textview_purchase_bank=(TextView)findViewById(R.id.textview_purchase_bank);
//		//CardInfo cardInfo=CardInfoDao.getInstance(PurchaseConfirmActivity.this).getCardInfo();
//		//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
//		
//	    play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
//		if("0".equals(play))
//		{
//		CardInfo cardInfo=CardInfoDao.getCardInfo();
//		//BankInfo bankInfo=BankInfoDao.getInstance(PurchaseConfirmActivity.this).getBankInfoByType(cardInfo.getType());
//		if(cardInfo!=null&&cardInfo.getType()!=null)
//		{
//		 bl=BankListDao.getBankByType(cardInfo.getType());
//		 if(bl!=null)
//		 {
//		  String cardNumber=cardInfo.getCardNumber();
//		  textview_purchase_bank.setText(bl.bankName
//				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//		  textview_purchase_single.setText("单笔最低限额"+bl.minLimit+"元,最高"+bl.singleLimit+"万元");
//		  singleLimit= NumberManager.StoD(bl.singleLimit);
//		  singleLimit*=10000;
//		 }
//		}
//		}
		
		clearedittext_transaction_money.setHint(getString(R.string.dingqi18));
	}

	/**
	 * 设置最多可购
	 */
	 private void setZuiDuo()
	    {
	    	TextView tv_tv1=(TextView)findViewById(R.id.tv_tv1);
	    
	    	
	    	
	    	tv_tv1.setText(NumberManager.getString("1",getZuiDuo(),2)+"元");
	    }
	 
	 private String getZuiDuo()
	 {
		 double d=0;
		    
	    	double d1=NumberManager.StoD(gcp.single_purchase_amount);
	        double d2=NumberManager.StoD(gcp.pro_share);
	       
	        double d3=0;
	    	if(yg!=null)
	    	{  
	    		if(NumberManager.StoD(gcp.cumulative_purchase_amount)!=0)
	    		{
	    		String s3=NumberManager.getSubString(gcp.cumulative_purchase_amount,NumberManager.getString(yg.totalAmount,"1",0), 8);
	    		d3=NumberManager.StoD(s3);
	    		}
	    	}
	    	d=getMin(d1, d2);
	    	d=getMin(d, d3);
	    	return d+"";
	 }
	 private double getMin(double d1,double d2)
	    {   
	    	if(d1==0&&d2!=0)
	    	{
	    		return d2;
	    	}
	    	else if(d1==0&&d2==0)
	    	{
	    		return d1;
	    	}
	    	else if(d1!=0&&d2==0)
	    	{
	    		return d1;
	    	}
	    	else
	    	{
	    		if(d1<=d2)
	    		{
	    			return d1;
	    		}
	    		else
	    		{
	    			return d2;
	    		}
	    	}
	    
	    }
	
	@Override
	public void onClick(View v) {
		String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:
			money=clearedittext_transaction_money.getText().toString();
			
			String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
			//钱包
			if("3".equals(pay))
			{
				int b1=NumberManager.isDaYu(money,getZuiDuo());
				if(b1>0)
				{
					showToast("购买金额不能大于可购金额");
					return;
				}
			}
			//银行卡
			else
			{
				int b1=NumberManager.isDaYu(money,gq.min_amount);
				if(b1<0)
				{
					showToast("低于单笔最低限额");
					return;
				}
				int b2=NumberManager.isDaYu(money,NumberManager.getString(gq.max_amount,"10000",2));
				if(b2>0)
				{
					showToast("超过单笔最高限额");
					return;
				}
				int b3=NumberManager.isDaYu(money,getZuiDuo());
				if(b3>0)
				{
					showToast("购买金额不能大于可购金额");
					return;
				}
			}




			int b1=NumberManager.isDaYu(money,gcp.purchase_amount);
			if(b1<0)
			{
				showToast("购买金额必须大于起购金额");
				return;
			}
//			Intent mIntent=getIntent();
//			mIntent.putExtra("money", money);
//			mIntent.putExtra("gqb",gq);
//			mIntent.setClass(act,DingqiGouMaiConfirmActivity.class);
//			startActivity(mIntent);
			ArrayList<getAllYouHuiQuan> list2= HongbaoUtil_v2.select1(list_yhq);
			if(list2.size()>0)
			{
			    next(null);
			}
			else
			{
				 createDingdan(gq);
			}
			break;
		}

	}
	/**
	 * 红包请求完成
	 */
	private boolean hongbao_finish=false;
	/**
	 * 选择红包后回调
	 */
	private ICallBack icb4=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{    
				//String money=clearedittext_transaction_money.getText().toString();
				//BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,"");
				Hongbao_data hd=(Hongbao_data) obj;
				list=hd.data;
				hongbao_finish=true;
				//Intent mIntent=null;
//				HongbaoUtil hbu=new HongbaoUtil(act);
//				list=hbu.select(list,dp,money);
//				toPuy();
//				if(list==null||list.size()<=0)
//				{
//				    mIntent=new Intent(act,DingqiGouMaiConfirmActivity.class);
//				}
//				else
//				{   
//					
//					mIntent=new Intent(act,DingdanConfimActivity.class);
//					mIntent.putExtra("hongbao",list);
//					
//				}
				
		
//				  Bundle bundle=new Bundle();
//				  bundle.putString(BeikBankConstant.INTENT_AMOUNT,money);
//				  bundle.putString("index2","index2");
//				  bundle.putSerializable("index1",dp);
//				  mIntent.putExtras(bundle);
//				  startActivity(mIntent);
			}
			
		}
	};
	ArrayList<Hongbao>  list;
	/**
	 * 选择后临时红包变量
	 */
	ArrayList<Hongbao>  list_temp;
	private ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{  
				HongbaoUtil hbu=new HongbaoUtil(act);
				
				ArrayList<Hongbao> list2=null;
				if(list!=null)
				{
				   list2=hbu.select8(list);
				}
				
				HuoqiToR_data hr=(HuoqiToR_data) obj;
				//Intent mIntent=new Intent(act,DingqiGouMaiConfirmActivity.class);
				Intent mIntent=getIntent();
				if(list2==null||list2.size()==0)
				{
					mIntent.setClass(act,DingqiGouMaiConfirmActivity.class);
				}
				else
				{
					mIntent.setClass(act,DingdanConfimActivity.class);
				}
				
				
				
				mIntent.putExtra("huoqi_no",hr.data.tradeNo);
				//mIntent.putExtra(BeikBankConstant.INTENT_AMOUNT,money);
				mIntent.putExtra(TypeUtil.jiaoyi_money,money);
				//mIntent.putExtra("index1",dp);
				startActivity(mIntent);
				finish();
			}
		}
	}; 
	/**
	 * 购买后完成跳转
	 */
	private void toPuy()
	{  
		synchronized (act) {
		if(list==null||rpp==null)
		{
			return;
		}
//		if(list.size()<=0)
//		{
//		 if(play.equals("1"))
//		 {
//			if(!isCanMai(qianbao,money))
//			{
//				showToast("余额不足");
//				return;
//			}
//		 }
//		}
		
		String money=clearedittext_transaction_money.getText().toString();
		BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,"");
		Intent mIntent=getIntent();
  
			if(rpp.fuiouPay==null||!rpp.fuiouPay.equals("1")||rp.tradeMode.equals("1"))
			{
		      //mIntent=new Intent(act,DingqiGouMaiConfirmActivity.class);
				mIntent.setClass(act,DingqiGouMaiConfirmActivity.class);
				
			}
			else
			{
			    //mIntent=new Intent(act,QueRenJiaoYiActivity.class);
				mIntent.setClass(act,QueRenJiaoYiActivity.class);
			}
		

		 Bundle bundle=new Bundle();
		 bundle.putString(TypeUtil.jiaoyi_money,money);
		 bundle.putSerializable(TypeUtil.jiaoyi_qingqiu_data,rpp);
		 //bundle.putSerializable("index1",dp);
		 
		 bundle.putSerializable(TypeUtil.jiaoyi_qingqiu,rp);
		 mIntent.putExtras(bundle);
		 startActivity(mIntent);

		 
		}
	}
	ReqPayforP rpp;
	String money;
	ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{  
			  ReqPayforP_Data rd=(ReqPayforP_Data) obj;
			  rpp=rd.data;
			  //Intent mIntent=new Intent(act,DingqiGouMaiConfirmActivity.class);
//			  Intent mIntent=new Intent(act,SelectHongbaoActivity.class);
//			  Bundle bundle=new Bundle();
//			  bundle.putString(BeikBankConstant.INTENT_AMOUNT,money);
//			  bundle.putSerializable(DingqiGouMaiConfirmActivity.index,rpp);
//			  bundle.putSerializable("index1",dp);
//			  mIntent.putExtras(bundle);
//			  startActivity(mIntent);
//			  finish();
			  toPuy();
			}
		}
	};
	/**
	 * 
	 * @param s1 最多可购买
	 * @param s2 用户输入金额
	 * @return
	 */
    public static boolean isCanMai(String s1,String s2)
    {
    	double d1=Double.parseDouble(s1);
    	double d2=Double.parseDouble(s2);
    	if(d1<d2)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
//		Intent mIntent=new Intent(this, PurchaseConfirmActivity.class);
//		Bundle bundle=new Bundle();
//		bundle.putString(BeikBankConstant.INTENT_AMOUNT, clearedittext_transaction_money.getText().toString());
//		bundle.putString(BeikBankConstant.INTENT_SID, sid);
//		mIntent.putExtras(bundle);
//		startActivity(mIntent);

	}

	class TextWatcherListener implements TextWatcher{
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}
        //
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			String money=clearedittext_transaction_money.getText().toString();
			if(money.length()==1&&"0".equals(money))
			{
				clearedittext_transaction_money.setText("");
				return;
			}
			if(money.length()>0){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}

}
