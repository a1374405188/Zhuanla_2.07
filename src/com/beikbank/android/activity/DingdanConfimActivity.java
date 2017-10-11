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
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.ReqPayforPParam2;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.jiaoyi.help.JiaoYiHelp1;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DingdanConfimActivity extends BaseActivity1 implements OnClickListener
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
	  * 选择的红包
	  */
	 ArrayList<Hongbao> list;
     GetChanPin gcp;
     getQianBao gqb;
     String money;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_dingdan_confim);
	      StateBarColor.init(this,0xffffffff);
	      gcp=(GetChanPin) getIntent().getSerializableExtra("gcp");
	      gqb=(getQianBao) getIntent().getSerializableExtra("gqp");
	      money=getIntent().getStringExtra("money");
	      initView();
	      initData();
	      act=this;
	    }
	 private void initData()
	 {   
		 
		 ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				
			}
		};
		 
//		 CreateDingDanParam cdp=new CreateDingDanParam();
//		 cdp.acc_id=gqb.acc_id;
//		 cdp.acc_number=gqb.acc_number;
//		 cdp.amount=money;
//		 cdp.order_source="0";
//		 cdp.order_type="2";
//		 cdp.pro_id=gcp.product_id;
//		 cdp.user_code=BeikBankApplication.getUserCode();
//		 
//		 TongYongManager2 tym2=new TongYongManager2(act, icb,cdp);
//		 tym2.start();
		 
	 }
 public static	ArrayList<String> list3;

	 private void initView()
     {
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
			button_next=(Button) findViewById(R.id.button_next);
			button_next.setOnClickListener(this);
			button_next.setEnabled(true);
			button_next.setText("提交订单");
			TextView titleTv = (TextView) findViewById(R.id.titleTv);
			titleTv.setText("订单确认");
	 }
	/**
	 * 支付金额
	 */
	 String money2;
	 @Override
    protected void onResume() 
	 {
			super.onResume();
			String hongbao=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao);
			String hongbao2=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao2);
			if(hongbao==null||hongbao.equals(""))
			{
				tv6.setText("0元现金红包");
				tv4.setText("支付0.00元");
			}
			else
			{
				tv6.setText(hongbao+"元现金红包");
				money2=NumberManager.getSubString(s2,hongbao,2);
				tv4.setText("支付"+money2+"元");
			}
			
			
			if(list==null)
			{
				tv4.setText("支付"+NumberManager.getString(s2,"1",2)+"元");
				 tv6.setText("活期转定期不支持使用红包");
			}
			if(list!=null&&list.size()==0)
			{
				tv4.setText("支付"+NumberManager.getString(s2,"1",2)+"元");
				 tv6.setText("无可用红包");
			}
	}
	@Override
	public void onClick(View v) {
		
		switch (v.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	         case R.id.ll3:
	        	 
	        	 if(list==null||list.size()==0)
	        	 {
	        		 Intent intent=getIntent();
	    	         intent.setClass(this,YouHuiQuanActivity4.class);
	    	        
	    	         startActivity(intent);
	        		 
	        		 return;
	        	 }
	         Intent intent=getIntent();
	         intent.setClass(this,SelectHongbaoActivity.class);
	         intent.putExtra("list",list);
	         startActivity(intent);
	         break;
	         case R.id.button_next:
//		         intent=getIntent();
//		         //为1的短信验证，不为1密码验证
//		         String huyou=intent.getStringExtra("huyou");
//		         if(huyou.equals("1"))
//		         {
//		        	 intent.setClass(this,QueRenJiaoYiActivity.class);
//		         }
//		         else
//		         {
//		            intent.setClass(this,PurchaseConfirmActivity.class);
//		         }
//		         intent.putExtra("money2",money2);
//		         if(dp!=null)
//		         {
//		        	 
//		        	 if(huyou.equals("1"))
//		        	 {
//		        		 intent.setClass(this,QueRenJiaoYiActivity.class);
//		        	 }
//		        	 else
//		        	 {
//		        		 intent.setClass(this,DingqiGouMaiConfirmActivity.class);
//		        	 }
//		         }
//		         startActivity(intent);
	        	 
	        	 if(list==null)
	        	 {
	        		 Intent mIntent=getIntent();
	        		 mIntent.setClass(act,DingqiGouMaiConfirmActivity.class);
	        		 startActivity(mIntent);
	        	 }
	        	 else
	        	 {
	        	    addData();
	        	 }
		         break;
	      }
	}
	ReqPayforPParam2 rp;
	/**
	 * 请求交易
	 */
    private void addData()
    {
    	
    	rp=(ReqPayforPParam2) getIntent().getSerializableExtra(TypeUtil.jiaoyi_qingqiu);
    	String tok=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao2);
    	rp.couponTokens=tok;
    	TongYongManager tym=new TongYongManager(this, icb1,rp);
		tym.start();
    }
    private ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				ReqPayforP_Data rd=(ReqPayforP_Data) obj;
				ReqPayforP rpf=rd.data;
				Intent intent=getIntent();
				int type=getIntent().getIntExtra(TypeUtil.jiaoyi_type,-1);
				intent.putExtra("money2",money2);
				
				if(rpf.fuiouPay==null||!rpf.fuiouPay.equals("1")||rp.tradeMode.equals("1"))
				{   
					if(type==TypeUtil.jiaoyi_huoqi)
					{
						intent.setClass(DingdanConfimActivity.this,PurchaseConfirmActivity.class);
					}
					else
					{
						intent.setClass(DingdanConfimActivity.this,DingqiGouMaiConfirmActivity.class);
					}
	
				}
				else
				{
					intent.setClass(DingdanConfimActivity.this,QueRenJiaoYiActivity.class);
				}
				intent.putExtra(TypeUtil.jiaoyi_qingqiu_data,rpf);
				startActivity(intent);
			}
			
		}
	};
}
