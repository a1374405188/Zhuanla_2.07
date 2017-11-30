package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.Collections;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.data2.getAllYouHuiQuan_data;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.ReqPayforPParam2;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.getAllYouhuiQuanParam;
import com.beikbank.android.dataparam2.getYouhuiQuanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.jiaoyi.help.JiaoYiHelp1;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil2_2_v2;
import com.beikbank.android.utils.hongbao.HongbaoUtil_v2;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DingdanConfimActivity2 extends BaseActivity1 implements OnClickListener
{    
	//优惠券
	 LinearLayout ll3;
	 LinearLayout linear_left;
	 //购买活期
	 TextView tv1;
	 
	 TextView tv2;
	 //银行卡
	 TextView tv3;
	 TextView tv4;
	 //优惠券
	 TextView tv5;
	 TextView tv6;
	 private Button button_next;
	 String s1;
	 /**
	  * 购买金额
	  */
	 String s2;
	 Activity act;
	 /**
	  * 选择的优惠券
	  */
	 ArrayList<getAllYouHuiQuan> list0;
     GetChanPin gcp;
     getQianBao gqb;
     String money;
     TextView tv_zhifu;
    public static getAllYouHuiQuan gyh;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_dingdan_confim2);
	      StateBarColor.init(this,0xffffffff);
	      gcp=(GetChanPin) getIntent().getSerializableExtra("gcp");
	      gqb=(getQianBao) getIntent().getSerializableExtra("gqp");
	      money=getIntent().getStringExtra(TypeUtil.jiaoyi_money);
	      initView();
	      initData();
	      act=this;
	    }
	 private void initData()
	 {   
		 
		 ICallBack icb=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{

						getAllYouHuiQuan_data gd=(getAllYouHuiQuan_data) obj;
						ArrayList<getAllYouHuiQuan> list=gd.body;
						//ArrayList<getAllYouHuiQuan> list1=HongbaoUtil_v2.select1(list);
						ArrayList<getAllYouHuiQuan> list2=HongbaoUtil_v2.select3(gcp.term,money, list); 
						list0=list2;
						gyh=HongbaoUtil_v2.select4(money, list2);
						if(gyh==null)
						{   list0=list;
							money2=money;
							tv_zhifu.setText(NumberManager.getString("1",money,2)+"元");
							return;
						}
						 BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,gyh.coupon_no);
						 BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao,gyh.coupon_value);
						
						 money2=NumberManager.getSubString(money,gyh.coupon_value,2);
						 hongbao2=gyh.coupon_no;
						 double d1=NumberManager.StoD(money2);
							if(d1<0)
							{
								tv_zhifu.setText("0.00"+"元");
							}
							else
							{
								tv_zhifu.setText(money2+"元");
							}
						 tv6.setText(NumberManager.getString("1",gyh.coupon_value,0)+"元抵扣金");
					}
					
				}
			};
			getYouhuiQuanParam gp=new getYouhuiQuanParam();
			gp.user_code=BeikBankApplication.getUserCode();
			TongYongManager2 tym=new TongYongManager2(this, icb,gp);
			tym.start();
		 
	 }
 public static	ArrayList<String> list3;

	 private void initView()
     {       
		   ll_error = (LinearLayout) findViewById(R.id.ll_error);
			tv_error = (TextView) findViewById(R.id.tv_error);
			ll3=(LinearLayout) findViewById(R.id.ll3);
			ll3.setOnClickListener(this);
			linear_left = (LinearLayout) findViewById(R.id.linear_left);
			linear_left.setVisibility(View.VISIBLE);
			linear_left.setOnClickListener(this);
			tv1=(TextView) findViewById(R.id.tv1);
			tv2=(TextView) findViewById(R.id.tv2);
			tv3=(TextView) findViewById(R.id.tv3);
			tv4=(TextView) findViewById(R.id.tv4);
			tv5=(TextView) findViewById(R.id.tv5);
			tv6=(TextView) findViewById(R.id.tv6);
			tv_zhifu=(TextView) findViewById(R.id.tv_zhifu);
			button_next=(Button) findViewById(R.id.button_next);
			button_next.setOnClickListener(this);
			button_next.setEnabled(true);
			button_next.setText("提交订单");
			TextView titleTv = (TextView) findViewById(R.id.titleTv);
			titleTv.setText("订单确认");
			
			
			tv2.setText(NumberManager.getString("1",money,2)+"元");
			
			 String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
			 if("3".equals(pay))
			 {
				 tv4.setText("钱包");
			 }
			 else
			 {  
				 String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
				 String bank_name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
				 tv4.setText(bank_name
							+"(尾号"+bank.substring(bank.length()-4,bank.length())+")");
			 }
			 
			 
			 BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao,"");
			 
	 }
	/**
	 * 支付金额
	 */
	 String money2;
	 String hongbao2;
	 @Override
    protected void onResume() 
	 {
			super.onResume();
			
//			if(hongbao==null||hongbao.equals(""))
//			{
//				tv6.setText("0元现金红包");
//				tv4.setText("支付0.00元");
//			}
//			else
//			{
//				tv6.setText(hongbao+"元现金红包");
//				money2=NumberManager.getSubString(s2,hongbao,2);
//				tv4.setText("支付"+money2+"元");
//			}
//			
//			
//			if(list==null)
//			{
//				tv4.setText("支付"+NumberManager.getString(s2,"1",2)+"元");
//				 tv6.setText("活期转定期不支持使用红包");
//			}
//			if(list!=null&&list.size()==0)
//			{
//				tv4.setText("支付"+NumberManager.getString(s2,"1",2)+"元");
//				 tv6.setText("无可用红包");
//			}
	}
	 
	 
	 
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		String hongbao=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao);
		money2=NumberManager.getSubString(money,hongbao,2);
		double d1=NumberManager.StoD(money2);
		if(d1<0)
		{
			tv_zhifu.setText("0.00"+"元");
		}
		else
		{
			tv_zhifu.setText(money2+"元");
		}
		
		tv6.setText(NumberManager.getString("1",hongbao,0)+"元抵扣金");
		if(NumberManager.StoD(hongbao)==0)
		{
			tv6.setText("无可用优惠券");
		}
		hongbao2=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao2);
	}
	@Override
	public void onClick(View v) {
		
		switch (v.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	         case R.id.ll3:
	        	 
//	        	 if(list==null||list.size()==0)
//	        	 {
//	        		 Intent intent=getIntent();
//	    	         intent.setClass(this,YouHuiQuanActivity4.class);
//	    	        
//	    	         startActivity(intent);
//	        		 
//	        		 return;
//	        	 }
	         Intent intent=getIntent();
	         intent.setClass(this,YouHuiQuanActivity5.class);
	         intent.putExtra("gyh",gyh);
	         intent.putExtra("money",money);
	         intent.putExtra("tianshu",gcp.term);
	         intent.putExtra("list",list0);
	         startActivity(intent);
	         break;
	         case R.id.button_next:
	        	 getQianBao  gqb=(getQianBao) getIntent().getSerializableExtra("gqb");
			     createDingdan(gqb);
	        	
	        	 
		         break;
	      }
	}
	private void createDingdan(final getQianBao  gqb)
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
					Intent mIntent=getIntent();
					mIntent.putExtra(TypeUtil.jiaoyi_money, money2);
					 mIntent.putExtra("gqb",gqb);
					 if(obj!=null)
					 {
					   mIntent.putExtra("cdd",cddd.body);
					 }
					
					   String s1=BeikBankApplication.getSharePref(BeikBankConstant.mima_duanxin);
				       String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
				       
				       if("1".equals(s1)||"3".equals(pay))
				       {
				    	   
				    	   
							
						
							mIntent.setClass(act,DingqiGouMaiConfirmActivity.class);
							startActivity(mIntent);
							
				    	   return ;
				    	   
				       }
				       
						mIntent.setClass(act,QueRenJiaoYiActivity.class);
						startActivity(mIntent);
				}
				
			}
		};
		 ArrayList<String> list=new ArrayList<String>();
		 if(hongbao2!=null&&!hongbao2.equals(""))
		 {
			
		 
		 String[] red=hongbao2.split(",");
	
		 for(int i=0;i<red.length;i++)
		 {
			 list.add(red[i]);
		 }
		 }
		 String pay=BeikBankApplication.getSharePref(BeikBankConstant.pay_type);
		 CreateDingDanParam cdp=new CreateDingDanParam();
		 cdp.acc_id=gqb.acc_id;
		 cdp.acc_number=gqb.acc_number;
		 cdp.amount=money;
		
		 cdp.order_type=pay;
		 cdp.pro_id=gcp.product_id;
		 cdp.pro_type=gcp.type;
		 cdp.user_code=BeikBankApplication.getUserCode();
		 
		 cdp.red_packet_list=list;
		 TongYongManager2 tym2=new TongYongManager2(act, icb,cdp);
		 tym2.start();
	}
    
}
