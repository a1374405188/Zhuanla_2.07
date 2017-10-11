package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.adapter.HongbaoAdapter;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SelectHongbaoActivity extends BaseActivity1 implements OnClickListener
{
     ListView lv;
     LinearLayout linear_left;
     /**
      * 红包总金额
      */
     TextView tv1;
     /**
      * 共多少向
      */
     TextView tv2;
     private TextView right,titleTv,bu1;
     Activity act;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_select_hongbao);
	      StateBarColor.init(this,0xffffffff);
	      act=this;
	      initView();
	      initData();
	    }
	
	private void initView()
	{
		lv=(ListView) findViewById(R.id.lv);
		tv1=(TextView) findViewById(R.id.tv1);
		tv2=(TextView) findViewById(R.id.tv2);
		titleTv = (TextView) findViewById(R.id.titleTv);
		bu1= (TextView) findViewById(R.id.bu1);
		bu1.setOnClickListener(this);
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		right=(TextView) findViewById(R.id.right);
		right.setVisibility(View.VISIBLE);
		titleTv.setText("选择红包");
		right.setText("使用说明");
		right.setOnClickListener(this);
	}
	private void initData()
	{   
//		HongbaoParam hp=new HongbaoParam();
//		hp.userId=BeikBankApplication.getUserid();
//		//hp.userId="59";
//		TongYongManager tym=new TongYongManager(this, icb,hp);
//		tym.start();
		String s2=getIntent().getStringExtra(TypeUtil.jiaoyi_money);
		ArrayList<Hongbao> list=(ArrayList<Hongbao>) getIntent().getSerializableExtra("list");
		
		String hongbao2=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.hongbao2);
		String lists[]=hongbao2.split(",");
		ArrayList<String> list3=DingdanConfimActivity.list3;
		
		HongbaoAdapter ha=new HongbaoAdapter(SelectHongbaoActivity.this,s2,list,list3,false);
		lv.setAdapter(ha);
		
	    String money=HongbaoUtil.countMoney(list, list3);
		setCount(money,list3.size()+"");
	}
//	ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				Hongbao_data hd=(Hongbao_data) obj;
//				ArrayList<Hongbao> list=hd.data;
//				HongbaoUtil hbu=new HongbaoUtil(act);
//				
//				String s2=getIntent().getStringExtra(BeikBankConstant.INTENT_AMOUNT);
//				list=hbu.select(list,(DingqiP2) getIntent().getSerializableExtra("index1"),s2);
//				Collections.sort(list);
//				
//				ArrayList<Hongbao> list2=hbu.select2(list,s2);
//				ArrayList<String> list3=hbu.getSelectIndex(list,list2);
//				HongbaoAdapter ha=new HongbaoAdapter(SelectHongbaoActivity.this,s2,list,list3,false);
//				lv.setAdapter(ha);
//				//countMoney(list, list3);
//			    String money=HongbaoUtil.countMoney(list, list3);
//				setCount(money,list3.size()+"");
//			}
//			
//		}
//	};
	
	
	
	
	
	
//	/**
//	 * 计算优惠券金额
//	 */
//	public String countMoney(ArrayList<Hongbao> list,ArrayList list2)
//	{   
//		String money="0";
//		//优惠券标签
//		String tok="";
//		try
//		{
//		  Hongbao hb=null;
//		 for(int i=0;i<list2.size();i++)
//		 {
//			hb=list.get(Integer.parseInt((String) list2.get(i)));
//			String s1=hb.content;
//			JSONObject jo=null;
//			jo=new JSONObject(s1);
//			s1=jo.getString("couponAmont");
//			money=NumberManager.getAddString(money,s1,8);
//			if(tok.equals(""))
//			{
//				tok=hb.couponToken;
//			}
//			else
//			{
//				tok+=","+hb.couponToken;
//			}
//		 }
//		}
//        catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		money=NumberManager.getString(money,"1",0);
//		//tv1.setText(money+"元");
//		//tv2.setText("共"+list2.size()+"项");
//		BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao,money);
//		BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,tok);
//		return money;
//	}
	public void setCount(String money,String size)
	{
		tv1.setText(money+"元");
		tv2.setText("(共"+size+"张)");
	}
	@Override
	public void onClick(View v) {
		
		switch (v.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	         case R.id.bu1:
	             finish();
	          break;
	         case R.id.right:
	             startAct(YouHuiQuanActivity2.class);
	          break;
	      }
	}
	  private void startAct(Class cla)
	   {
		   Intent intent=new Intent(this, cla);
		   startActivity(intent);
	   }
}
