package com.beikbank.android.activity;

import com.beikbank.android.activity.help.BankHelp;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.data.Qianbao4;
import com.beikbank.android.data.Qianbao7_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data.Yuer;
import com.beikbank.android.data2.CreateDingDan;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.TiXianQingQiu_data;
import com.beikbank.android.data2.getTiXianQianZhi_data;
import com.beikbank.android.dataparam.QianbaoParam7;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.TiXianQingQiuParam;
import com.beikbank.android.dataparam2.getTiXianQianZhiParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.HandMoneyManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-22
 *提现
 */
public class QianbaoActivity3 extends BaseActivity1 implements View.OnClickListener
{   
	String TAG="QianbaoActivity3";

	//手续费提示
    ImageView iv_info;
	TextView title;
	TextView right;
	Activity act=this;
    /**
     * return
     */
    LinearLayout ll;
    /**
     * 手续费提示文本
     */
	private LinearLayout ll1;
    /**
     * 下一步
     */
    Button button_next;
    ClearableEditText et1;
    /**
     * 银行卡
     */
    TextView tv1;
    /**
     * 免费提现次数
     */
    TextView tv2;
    /**
     * 投资回款余额
     */
    TextView tv3;
    /**
     * 充值未使用余额
     */
    TextView tv4;
    /**
     * 手续费
     */
    TextView tv_tv1;
    private double totalcapital;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianbao3);
		StateBarColor.init(this,0xffffffff);
		initView();
       // initOn();
		initData();

	}
	/**
	 * 得到总钱包余额
	 */
	private void initData()
	{
		//String s=getIntent().getStringExtra("index");
		///totalcapital=Double.parseDouble(s);
//		new HandMoneyManager(this, icb1).start(BeikBankApplication.getUserid());
//		
//		
//		Yuer qb=(Yuer) getIntent().getSerializableExtra("qb");
//		double d=Double.parseDouble(qb.rechargeLimit);
//		if(d>0)
//		{   
//			tv4.setText(NumberManager.getGeshiHua(qb.rechargeLimit,2)+"元");
//		}
//		else
//		{
//			LinearLayout ll4=(LinearLayout) findViewById(R.id.ll4);
//			ll4.setVisibility(View.GONE);
//		}
//		
//		
//		String s2=NumberManager.getAddString(qb.fundLimit,qb.bondLimit,2);
//		s2=NumberManager.getGeshiHua(s2,2);
//		tv3.setText(s2+"元");
//		
//		//et1.setHint("余额"+NumberManager.getGeshiHua(qb.activeAmount,2));
//		et1.setHint(s2);
		
		ICallBack icb_tixian=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					getTiXianQianZhi_data gd=(getTiXianQianZhi_data) obj;
					String s3=NumberManager.getString(gd.body.repay_amt,"1",2);
					String s4=NumberManager.getString(gd.body.recharge_amt,"1",2);
					tv2.setText(gd.body.free_number);
					tv3.setText(s3+"元");
					tv4.setText(s4+"元");
					
				}
			}
		};
		getTiXianQianZhiParam gti=new getTiXianQianZhiParam();
		gti.acc_id=BeikBankApplication.getAccid();
		gti.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tmy2=new TongYongManager2(act, icb_tixian,gti);
		tmy2.start();
		
	}
	ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				 Poundage_data pd=(Poundage_data) obj;
				 p=pd.data;
				// tv2.setText(NumberManager.getAddString(p.freeChargeCount,p.freeWithdrawCount,0));
				 tv2.setText(p.freeChargeCount);
			}
			
		}
	};
	//是否有手续费
	boolean is_poundage=false;
	Poundage p;
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		  if(obj!=null)
		  {  
			  Poundage_data pd=(Poundage_data) obj;
			  p=pd.data;
			 double d= NumberManager.StoD(p.freeWithdrawCount);
			if(d<=0)
      		{  
				is_poundage=true;
      			tv_tv1.setText(p.poundage+"元");
      			//tv2.setVisibility(View.GONE);
      			iv_info.setVisibility(View.VISIBLE);
      		}
			else
			{
				is_poundage=false;
				tv_tv1.setText("免手续费");
			}
		  }
		}
	};
    private  void initView()
    {
    	 ll=(LinearLayout)findViewById(R.id.linear_left);
    	// ll1=(LinearLayout) findViewById(R.id.ll_ll1);
         title=(TextView)findViewById(R.id.titleTv);
         tv1=(TextView)findViewById(R.id.tv1);
         tv_tv1=(TextView)findViewById(R.id.tv_tv1);
         ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	 tv_error=(TextView) findViewById(R.id.tv_error);
         iv_info=(ImageView) findViewById(R.id.iv_info);
         title.setText("提现");
         et1=(ClearableEditText) findViewById(R.id.et_et1);
         et1.addTextChangedListener(new TextWatcherListener());
         tv2=(TextView) findViewById(R.id.tv2);
         tv3=(TextView) findViewById(R.id.tv3);
         tv4=(TextView) findViewById(R.id.tv4);
         //String s=getIntent().getStringExtra("index");
         
         //et1.setHint("可提现:"+NumberManager.getString("1",s,2));
         button_next=(Button) findViewById(R.id.button_next);
         ll.setOnClickListener(this);
        // ll1.setOnClickListener(this);
         
         button_next.setOnClickListener(this);
         
         
//         CardInfo cardInfo=CardInfoDao.getCardInfo();
//  		
//  		if(cardInfo!=null&&cardInfo.type!=null)
//  		{
//  		  BankList bl=BankListDao.getBankByType(cardInfo.getType());
//  		  if(bl!=null)
//  		 {
//  		  String cardNumber=cardInfo.getCardNumber();
//  		  tv1.setText(bl.bankName
//  				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//  		 }
//  		}
         
         
         BankHelp.setBnak(tv1,null);
         
         
         
    }
    Dialog dia=null;
	 private void initOn()
	    {
	    	ll1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(!is_poundage)
					{
						return;
					}
					if(dia==null)
					{
						dia=Utils.creadialog1(act, null, null);
					}
					if(!dia.isShowing())
					{
						dia.show();
					}
				}
			});
	    }
	 private Dialog dialog=null;
	 /**
		 * 点击取现提交后调用该方法
		 * @param money 取现金额
		 * @param money2 手续费
		 */
	    private void afterSubmit(String money,String money2)
	    {  
	    	final String  temp=money;
	    	//double f2;
	    	if(money2==null||"".equals(money2))
	    	{
	    		//f2=0;
	    		money2="0";
	    	}
//	    	double f1=Float.parseFloat(money);
	     	
	    	String residue=NumberManager.getSubString(money,money2,2);
	    	double f3=Double.parseDouble(residue);
	    	//BigDecimal bd=new BigDecimal(f3);
	    	//String residue=bd.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	    	//String residue=NumberManager.getString(f3,"1",2);
	    	//BigDecimal bd2=new BigDecimal(f1);
	    	//money=bd2.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	    	money=NumberManager.getString(money, "1",2);
	    	
	    	//BigDecimal bd3=new BigDecimal(f2);
	    	//money2=bd3.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	    	money2=NumberManager.getString(money2, "1",2);
	    	
	    	//String residue=f3+"";
//	        if(residue.endsWith("00"))
//	        {
//	        	money=(int)f1+"";
//	        	money2=(int)f2+"";
//	        	residue=(int)f3+"";
//	        }

	    	View v=null;
	    	TextView tv1=null;
	    	TextView tv2=null;
	    	TextView tv3=null;
	    	TextView tv4=null;
	    	TextView tv5=null;
	    	if(f3>0)
	    	{  
//	    		   v=LayoutInflater.from(this).inflate(
//	       			   R.layout.redeem_dialog1,null);
//	    	   tv1=(TextView)v.findViewById(R.id.tv_tv1);
//	    	   tv2=(TextView)v.findViewById(R.id.tv_tv2);
//	    	   tv3=(TextView)v.findViewById(R.id.tv_tv3);
//	    	   tv4=(TextView)v.findViewById(R.id.tv_tv4);
//	    	   tv5=(TextView)v.findViewById(R.id.tv_tv5);
//	    	   tv5.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					dialog.dismiss();
					Intent intent=new Intent(act,QianbaoActivity6.class);
					//String index=BeikBankApplication.mSharedPref.getSharePrefString(PurchaseActivity.index);
					//intent.putExtra(BeikBankConstant.INTENT_SID,index);
					//intent.putExtra(this.index,fi);
					intent.putExtra(BeikBankConstant.INTENT_AMOUNT,residue);
					//取现金额
					intent.putExtra("tv1",money);
					//手续费
					intent.putExtra("tv2",money2);
					intent.putExtra("index","2");
					
					
					
					finish();
		           
		            String s=NumberManager.getString(et1.getText().toString(),"1",2);
		            //将充值金额写到文件
		            BeikBankApplication.mSharedPref.putSharePrefString("QianbaoActivity2",s);
		            startActivity(intent);
					return;
//				}
//			});
	    	   
	    	}
	    	else
	    	{
	    	  
	    	   v=LayoutInflater.from(this).inflate(
	    			   R.layout.redeem_dialog2,null);
	    	   tv1=(TextView)v.findViewById(R.id.tv_tv1);
	    	   tv2=(TextView)v.findViewById(R.id.tv_tv2);
	    	   tv3=(TextView)v.findViewById(R.id.tv_tv3);
	    	   tv4=(TextView)v.findViewById(R.id.tv_tv4);
	    	}  
	    	   tv1.setText(money+"元");
	    	   tv2.setText(money2+"元");
	    	   tv3.setText(residue+"元");
	    	   tv4.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View v) {
	   				if(dialog!=null&&dialog.isShowing())
	   				{
	   					dialog.dismiss();
	   				}
	   				
	   			}
	   		  });

	        dialog=DialogManager.getDiaolg1(this, v);
	    	dialog.show();
	    }
	    
	private void tixian()
	{
		ICallBack icb_tixian=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					TiXianQingQiu_data td=(TiXianQingQiu_data) obj;
//					tiqq=td.body;
//					tv_tv2.setText(NumberManager.getGeshiHua(tiqq.deduct_service_amt,2)+"元");
//					String residue = NumberManager.getSubString(money,tiqq.deduct_service_amt, 2);
//				    tv_tv3.setText(NumberManager.getGeshiHua(residue, 2)+"元");
				
					Intent intent=new Intent(act,QianbaoActivity6.class);
					//String mima=BeikBankApplication.getSharePref(BeikBankConstant.mima_duanxin);
					intent.putExtra("tixian",td.body);
					intent.putExtra("index","0");
					intent.putExtra(TypeUtil.jiaoyi_money,money);
					intent.putExtra("cdd",cdd.body);
					act.startActivity(intent);
				}
				
			}
		};
		
		String name=BeikBankApplication.getSharePref(BeikBankConstant.real_name);
		String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
		TiXianQingQiuParam txp=new TiXianQingQiuParam();
		txp.acc_id=BeikBankApplication.getAccid();
		txp.amount=money;
		txp.order_id=cdd.body.order_id;
		txp.bank_name=bank;
		txp.user_code=BeikBankApplication.getUserCode();
		txp.phone_number=BeikBankApplication.getPhoneNumber();
		txp.real_name=name;
		txp.id_number=BeikBankApplication.getSharePref(BeikBankConstant.bank);
		TongYongManager2 tym2=new TongYongManager2(act, icb_tixian,txp);
		
        tym2.start();
	}
	String money;
	CreateDingDan_data cdd=null;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
            finish();
			break;
		case R.id.button_next:
		    money=et1.getText().toString();

			ICallBack icb_cdd=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{   
						cdd=(CreateDingDan_data) obj;
//						Intent intent=new Intent(act,QianbaoActivity6.class);
//						//String mima=BeikBankApplication.getSharePref(BeikBankConstant.mima_duanxin);
//						
//						intent.putExtra("index","0");
//						intent.putExtra(TypeUtil.jiaoyi_money,money);
//						intent.putExtra("cdd",cdd.body);
//						act.startActivity(intent);
						
						tixian();
					}
					
				}
			};
			
			CreateDingDanParam cdd=new CreateDingDanParam();
			cdd.acc_id=BeikBankApplication.getSharePref(BeikBankConstant.zhanghao);
			cdd.acc_number=BeikBankApplication.getSharePref(BeikBankConstant.bank);
			cdd.amount=money;
			cdd.order_type="5";
			cdd.user_code=BeikBankApplication.getUserCode();
			TongYongManager2 tym2=new TongYongManager2(act, icb_cdd,cdd);
			tym2.start();
			
			break;		
		default:
			break;
		}
		
	}
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
			Intent intent=new Intent(act,QianbaoActivity6.class);
			intent.putExtra(TypeUtil.jiaoyi_money,money);
			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT,money);
			 BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.INTENT_AMOUNT,money);
			intent.putExtra("index","2");
			intent.putExtra("qb7",((Qianbao7_data)obj).data);
			startActivity(intent);
			finish();
			}
		}
	};
	/**
	 * 
	 */
	private void donext()
	{
		
	}
	class TextWatcherListener implements TextWatcher{
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
//			if(s.length()==1)
//			{   
//				if(s.charAt(0)=='0'||s.charAt(0)=='.')
//				{
//				 et1.setText("");
//				 return;
//				}
//			}
			if(s.length()==2)
			{
				if(s.charAt(0)=='0'&&!(s.charAt(1)=='.'))
				{
					 et1.setText("0");
					 et1.setSelection(1);
					 return;
				}
			}
			if(s.toString().length()>0){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}
   
}
