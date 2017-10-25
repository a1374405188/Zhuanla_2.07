package com.beikbank.android.activity;

import java.util.ArrayList;

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

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.FundLimit;
import com.beikbank.android.data.FundLimit_data;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.HuoQiYiGou;
import com.beikbank.android.data2.HuoQiYiGou_data;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.ReqPayforPParam;
import com.beikbank.android.dataparam.ReqPayforPParam2;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam2.ChongZhiParam;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.HuoQiYiGouParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.jiaoyi.help.JiaoYiHelp1;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.FundLimitManager;
import com.beikbank.android.net.impl.Qianbao6Manager;
import com.beikbank.android.net.impl.ReqPayforPManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;
import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//活期购买
public class PurchaseActivity extends BaseActivity1 implements OnClickListener{
	
	private Activity act=this;
	private final String TAG="PurchaseActivity";
	private TextView titleTv,textview_toast;
	/**
	 * 银行卡名称
	 */
	private TextView tv3;
	private Button button_next;
	private ClearableEditText clearedittext_transaction_money;
	private LinearLayout linear_toast,linear_left;
	private AnimatorSet toastAnimSet;
	private String sid;
    public static String index="index";
    FundInfo fi;
    /**
     * 单笔限额
     */
    double singleLimit;
    BankList bl;
	 GetChanPin gcp;
	 HuoQiYiGou yg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_purchase);
		StateBarColor.init(this,0xffffffff);
		 gcp=(GetChanPin) getIntent().getSerializableExtra("gcp");
		initView();
		initData(); 
		
	}

	getQianBao gq;
    private void initData()
    {   
    	
    	ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				
				if(obj!=null)
				{   
					
					String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
					
					
					getQianBao_data gb=(getQianBao_data) obj;
					gq=gb.body.card;
					TextView tv_bank=(TextView) findViewById(R.id.tv_bank);
					TextView tv_single=(TextView) findViewById(R.id.tv_single);
					if("2".equals(pay))
					{
					
					
					tv_bank.setText(gq.bank_name+"(尾号"+gq.acc_number.substring(gq.acc_number.length()-4,gq.acc_number.length())+")");
					
					
					tv_single.setText("单笔最低限额"+gq.min_amount+"元,最高"+gq.max_amount+"万元");
					}
					else
					{
						//tv3.setText("钱包");
						//tv_single.setVisibility(View.GONE);
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
    }
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
        
        //clearedittext_transaction_money.setHint("上限"+NumberManager.getGeshiHua(gcp.pro_share,2)+"元");
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
    
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.purchase));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_transaction_money=(ClearableEditText)findViewById(R.id.clearedittext_transaction_money);
		clearedittext_transaction_money.addTextChangedListener(new TextWatcherListener());

		button_next=(Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		
		//sid=fi.sid;
		tv3=(TextView) findViewById(R.id.tv3);
		
		
		TextView textview_purchase_single=(TextView)findViewById(R.id.textview_purchase_single);
		TextView textview_purchase_bank=(TextView)findViewById(R.id.textview_purchase_bank);
	
		LinearLayout ll_ll1=(LinearLayout) findViewById(R.id.ll_ll1);
		TextView tv_tv1=(TextView)findViewById(R.id.tv_tv1);
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
		
		initView1();
		
		//CardInfo cardInfo=CardInfoDao.getInstance(PurchaseConfirmActivity.this).getCardInfo();
		//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
//		CardInfo cardInfo=CardInfoDao.getCardInfo();
//		//BankInfo bankInfo=BankInfoDao.getInstance(PurchaseConfirmActivity.this).getBankInfoByType(cardInfo.getType());
//		if(cardInfo!=null&&cardInfo.getType()!=null)
//		{
//		bl=BankListDao.getBankByType(cardInfo.getType());
//		  if(bl!=null)
//		 {
//	 	 String cardNumber=cardInfo.getCardNumber();
//		 textview_purchase_bank.setText(bl.bankName
//				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//		 textview_purchase_single.setText("单笔最低限额"+bl.minLimit+"元,最高"+bl.singleLimit+"万元");
//		 singleLimit= NumberManager.StoD(bl.singleLimit);
//		 singleLimit*=10000;
//		 }
//		 
//		}
//		String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
//		if("1".equals(play))
//		{
//			tv3.setText("钱包");
//			TotalMoneyParam tp=new TotalMoneyParam();
//			tp.memberID=BeikBankApplication.getUserid();
//			Qianbao6Manager qm=new Qianbao6Manager(this, icb2,tp);
//			qm.start();
//			textview_purchase_single.setVisibility(View.GONE);
//		}
//		
//		
//		//String fsid=BeikBankApplication.mSharedPref.getSharePrefString(index);
//		FundLimitManager fm=new FundLimitManager(act, icb);
//		fm.init(sid);
//		fm.start();
		
		
	}
	String qianbao;
 ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{  TextView textview_purchase_bank=(TextView)findViewById(R.id.textview_purchase_bank);
			   Qianbao4_data qd=(Qianbao4_data) obj;
			   money=qd.data.activeAmount;
			   qianbao=money;
			   String s1=NumberManager.getString(qd.data.activeAmount,"1",2);
			   textview_purchase_bank.setText(s1+"元");
			}
		}
	};
	FundLimit fl;
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				FundLimit_data fld=(FundLimit_data) obj;
			    fl=fld.data;
				//clearedittext_transaction_money.setHint(fl.limitAmount);
			}
			
		}
	};
	/**
	 * 判断最小支付限额
	 * @param money
	 * @param min
	 * @return true不能购买
	 */
	public static boolean minLimit(String money,String min)
	{
		boolean b=false;
		int dmoney=(int) NumberManager.StoD(money);
		int dmin=(int) NumberManager.StoD(min);
		if(dmoney<dmin)
		{
			b=true;
		}
		return b;
	}
	ReqPayforPParam2 rp;
	/**
	 * 选择后临时红包变量
	 */
	ArrayList<Hongbao>  list_temp;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
			
			
//			String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
//			if(play.equals("0"))
//			{
//			  if(minLimit(money,bl.minLimit))
//			  {   
//				  showToast("超出银行卡支付限额");
//				  return;
//			  }
//				
//			
//			
//			int b=(int) NumberManager.StoD(money);
//			
//			int c=(int) NumberManager.StoD(fi.fundAmount);
//			if(b>c)
//			{
//				showToast("购买金额不能大于可购金额");
//				return;
//			}
//			if(singleLimit>0&&b>singleLimit)
//			{
//				showToast("超出银行卡支付限额");
//				return;
//			}
//		    }
////			if(play.equals("1"))
////			{
////				if(!DingqiGoumaiActivity.isCanMai(qianbao,money))
////				{
////					showToast("余额不足");
////					return;
////				}
////				
////			}
//			//boolean isCheckBank=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
//			//UserInfo userInfo=UserInfoDao.getInstance(PurchaseActivity.this).getUserInfo();
//			 UserInfo userInfo=BeikBankApplication.getUserInfo();
//			if(Utils.isPositiveInteger(money,1)==1){//购买金额小于起购金额，目前不用考虑，起购金额为1元
//				//textview_toast.setText("购买金额输入有误，请重新输入");
//				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				showToast("购买金额输入有误，请重新输入");
//			}else if(Utils.isPositiveInteger(money,1)==2){//不是起购金额的整数倍，目前不用考虑，起购金额为1元
//				String str="购买金额为"+1+"整数倍，请重新输入"; 
//				//textview_toast.setText(str);
//				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				showToast(str);
//			}
//			else if(!isCheckBank)
//			{
//				Intent intent=new Intent(PurchaseActivity.this,BandTestActivity.class);
//				startActivity(intent);
//			}
//			Intent mIntent=getIntent();
//			mIntent.putExtra(TypeUtil.jiaoyi_money, money);
//			mIntent.putExtra("gqb",gq);
//			mIntent.setClass(this,PurchaseConfirmActivity.class);
//			startActivity(mIntent);
			
			createDingdan(gq);
			
			break;
		}

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
					
					
				
					
					
					
					  String s1=BeikBankApplication.getSharePref(BeikBankConstant.mima_duanxin);
				       String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
				       if("1".equals(s1)||"3".equals(pay))
				       {
				    	   
				    	   
							
							Intent mIntent=getIntent();
							mIntent.putExtra(TypeUtil.jiaoyi_money, money);
							mIntent.putExtra("gqb",gq);
							mIntent.putExtra("cdd",cddd.body);
							mIntent.setClass(act,PurchaseConfirmActivity.class);
							startActivity(mIntent);
							finish();
				    	   return ;
				    	   
				       }
				        Intent mIntent=getIntent();
						mIntent.putExtra(TypeUtil.jiaoyi_money, money);
						mIntent.putExtra("gqb",gq);
						mIntent.putExtra("cdd",cddd.body);
						mIntent.setClass(act,QueRenJiaoYiActivity.class);
						startActivity(mIntent);
						
						finish();
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
	
	ReqPayforP rpp;
	ArrayList<Hongbao>  list;
	/**
	 * 购买后完成跳转
	 */
	private void toPuy()
	{
		synchronized (act) 
		{
			if(list_temp==null||rpp==null)
			{
				return;
			}
			BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,"");
			Intent mIntent=null;
			if(list_temp==null||list_temp.size()<=0)
			{
				if(rpp.fuiouPay==null||!rpp.fuiouPay.equals("1")||rp.tradeMode.equals("1"))
				{
			      mIntent=new Intent(PurchaseActivity.this,PurchaseConfirmActivity.class);
				}
				else
				{
					mIntent=new Intent(PurchaseActivity.this,QueRenJiaoYiActivity.class);
				}
			}
			else
			{   
				
				
			 mIntent=new Intent(PurchaseActivity.this,DingdanConfimActivity.class);
			 mIntent.putExtra("hongbao",list_temp);
			 //是否短信验证的判断传递到下一个页面
			 //mIntent.putExtra("huyou",rpp.fuiouPay);
				
			}
			Bundle bundle=new Bundle();
			//bundle.putString(BeikBankConstant.INTENT_AMOUNT, money);
			bundle.putString(TypeUtil.jiaoyi_money, money);
			bundle.putString(BeikBankConstant.INTENT_SID, sid);
			bundle.putSerializable(TypeUtil.jiaoyi_qingqiu,rp);
			bundle.putSerializable(TypeUtil.jiaoyi_qingqiu_data,rpp);
			bundle.putSerializable(TypeUtil.huoqi_data,fi);
			mIntent.putExtras(bundle);
			mIntent.putExtra(TypeUtil.jiaoyi_type,TypeUtil.jiaoyi_huoqi);
			startActivity(mIntent);
			finish();
		}
	}
	/**
	 * 请求交易后回调
	 */
	ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		  if(obj!=null)
		  {
			  ReqPayforP_Data rd=(ReqPayforP_Data) obj;
			  rpp=rd.data;
			  toPuy();
		  }
			
		}
	};
	/**
	 * 红包请求完成
	 */
	private boolean hongbao_finish;
	/**
	 * 选择红包后回调
	 */
	ICallBack icb4=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,"");
				Hongbao_data hd=(Hongbao_data) obj;
				list=hd.data;
				hongbao_finish=true;
				 
//			    HongbaoUtil hbu=new HongbaoUtil(act);
//			    list=hbu.select(list,null,money);
			    //toPuy();
//				if(list==null||list.size()<=0)
//				{
//				  mIntent=new Intent(PurchaseActivity.this,PurchaseConfirmActivity.class);
//				}
//				else
//				{   
//					
//					
//					mIntent=new Intent(PurchaseActivity.this,DingdanConfimActivity.class);
//					mIntent.putExtra("hongbao",list);
//					
//				}
//				
//				Bundle bundle=new Bundle();
//				bundle.putString(BeikBankConstant.INTENT_AMOUNT, money);
//				bundle.putString(BeikBankConstant.INTENT_SID, sid);
//				//bundle.putSerializable("index3",rp);
//				bundle.putSerializable(PurchaseConfirmActivity.index,fi);
//				mIntent.putExtras(bundle);
//				startActivity(mIntent);
//				finish();
			}
			
		}
	};
//	ReqPayforP rp;
	String money;
//    ICallBack icb1=new ICallBack() {
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{   
//				
//				ReqPayforP_Data rd=(ReqPayforP_Data) obj;
//				rp=rd.data;
//				Intent mIntent=new Intent(PurchaseActivity.this, PurchaseConfirmActivity.class);
//				Bundle bundle=new Bundle();
//				bundle.putString(BeikBankConstant.INTENT_AMOUNT, money);
//				bundle.putString(BeikBankConstant.INTENT_SID, sid);
//				bundle.putSerializable("index3",rp);
//				bundle.putSerializable(PurchaseConfirmActivity.index,fi);
//				mIntent.putExtras(bundle);
//				startActivity(mIntent);
//			}
//			
//		}
//	};
	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
//		Intent mIntent=new Intent(PurchaseActivity.this, PurchaseConfirmActivity.class);
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

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
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
