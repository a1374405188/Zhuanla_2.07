package com.beikbank.android.activity;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.UserCapital2;
import com.beikbank.android.data.UserCapitalInfo;
import com.beikbank.android.data.UserCapitalInfo2;
import com.beikbank.android.data.UserCapitalInfo_data;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao6Manager;
import com.beikbank.android.net.impl.UserCapitalInfoManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-18
 * 
 */
public class ZhiChanActivity extends BaseActivity1 implements View.OnClickListener{
	TextView title;
    /**
     * return
     */
    LinearLayout ll;
    /**
     * 募集中
     */
    LinearLayout ll1;
    /**
     * 计息，还款中
     */
    LinearLayout ll2;
    /**
     * 总资产
     */
    TextView tv1;
    /**
     * 活期总资产
     */
    TextView tv2;
    /**
     * 钱包余额
     */
    TextView tv6;
    /**
     * 定期总资产
     */
    TextView tv3;
    TextView tv4;
    TextView tv5;
    /**
     * 钱包明细
     */
    TextView tv7;
    TextView huoqi;
    ImageView iv1;
    public static final String index="index0";
    public static final String index2="index";
    UserCapital2 uc=new UserCapital2();
    FundInfo fi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhichan);
        StateBarColor.init(this,0xffffffff);
        uc=(UserCapital2) getIntent().getSerializableExtra(index);
        fi= (FundInfo) getIntent().getSerializableExtra(index2);
        init();
        addData();
    }
    private void test(ArrayList<UserCapitalInfo2>  list)
    {
    	UserCapitalInfo2 uc2=new UserCapitalInfo2();
    	uc2.status="S02";
    	uc2.termbondName="test1";
    	uc2.remainAmount="130";
    	
    	list.add(uc2);
    	uc2=new UserCapitalInfo2();
    	uc2.status="S04";
    	uc2.termbondName="test2";
    	uc2.remainAmount="140";
    	list.add(uc2);
    }
    private void addData()
    {   
    	TotalMoneyParam tp=new TotalMoneyParam();
    	tp.memberID=BeikBankApplication.getUserid();
    	UserCapitalInfoManager ucm=new UserCapitalInfoManager(this, icb, tp);
    	ucm.start();
    	
    	//加载钱包余额
    	
		Qianbao6Manager qm=new Qianbao6Manager(this, icb3,tp);
		qm.start();
    }
    ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj!=null)
			{
				 Qianbao4_data qd=(Qianbao4_data) obj;
				
				 String s1=NumberManager.getAddString(qd.data.activeAmount,qd.data.frozenAmountPurchase,8);
				 s1=NumberManager.getAddString(s1,qd.data.frozenAmountRedeem,8);
				 s1=NumberManager.getAddString(s1,qd.data.frozenAmountWithdraw,2);
				 s1=NumberManager.getGeshiHua(s1,2);
				 tv6.setText(s1+"元");
			}
		}
	};
    private void addData2(ArrayList<UserCapitalInfo2> list,int index)
    {   
//    	if(SystemConfig.isDebug())
//    	{
//    		test(list);
//    	}
    	LinearLayout ll=null;
    	if(index==1)
    	{
    		ll=ll1;
    	}
    	else
    	{
    		ll=ll2;
    		iv1=(ImageView)findViewById(R.id.iv1);
    		iv1.setImageResource(R.drawable.zhichan_img2);
    	}
    	if(list==null||list.size()==0)
    	{
    		return;
    	}
    	LayoutInflater li=getLayoutInflater();
    	LinearLayout ll1;
    	TextView tv1;
    	TextView tv2;
    	TextView tv3;
    	for(UserCapitalInfo2 uc:list)
    	{
    		ll1=(LinearLayout) li.inflate(R.layout.activity_zhichan3,null);
    		tv1=(TextView) ll1.findViewById(R.id.tv1);
    		tv2=(TextView) ll1.findViewById(R.id.tv2);
    		tv3=(TextView) ll1.findViewById(R.id.tv3);
            
    		String s2=NumberManager.getString(uc.amount,"1",2);
    		s2=NumberManager.getGeshiHua(s2, 2);
    		tv1.setText(uc.termbondName);
    		tv2.setText(s2);
    		if(uc.status.equals(FinalIndex.dingqi1))
    		{
    		
    			s2=NumberManager.getString(uc.remainAmount,"1",2);
    			s2=NumberManager.getGeshiHua(s2, 2);
    			tv3.setText("距离募集成功还有"+s2+"元");
    		}
    		else if(uc.status.equals(FinalIndex.dingqi2))
    		{
    			tv3.setText("满标审核中");
    		}
    		else if(uc.status.equals(FinalIndex.dingqi4))
    		{
    			tv3.setText("流标");
    		}
    		else if(uc.status.equals(FinalIndex.dingqi5))
    		{
    			tv3.setText("返款中");
    		}
    		else if(uc.status.equals(FinalIndex.dingqi3))
    		{
    			tv3.setText("计息中");
    		}
    		else if(uc.status.equals(FinalIndex.dingqi6))
    		{
    			tv3.setText("已还款");
    		}
    		
    		ll.addView(ll1);
    		if(uc!=list.get(list.size()-1))
    		{
    			View view=new View(this);
    			LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,1);
    			view.setBackgroundColor(0xffd6d6d6);
    			view.setLayoutParams(lp);
    			ll.addView(view);
    		}
    	}
    }
    ArrayList<UserCapitalInfo2> uc2;
    ArrayList<UserCapitalInfo2> uc3;
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				UserCapitalInfo_data ud=(UserCapitalInfo_data) obj;
				UserCapitalInfo uci=ud.data;
				uc2=uci.termbondUnconfirmedList;
				uc3=uci.termbondList;
				addData2(uci.termbondUnconfirmedList,1);
				huoqi.setText(NumberManager.getGeshiHua(countMuji(uci),2));
				tv3.setText(NumberManager.getGeshiHua(countTotal(uci),2)+"元");
			}
			
		}
	};
	/**
	 * 计算定期总资产
	 * @return
	 */
	private String countTotal(UserCapitalInfo uci)
	{   
		String s="0";
//		for(UserCapitalInfo2 uc2:uci.termbondUnconfirmedList)
//		{
//			s=NumberManager.getAddString(s,uc2.amount,2);
//		}
		for(UserCapitalInfo2 uc3:uci.termbondList)
		{
			s=NumberManager.getAddString(s,uc3.amount,2);
		}
		return s;
	}
	/**
	 * 计算募集中资产
	 */
	private String countMuji(UserCapitalInfo uci)
	{   
		String s="0";
		for(UserCapitalInfo2 uc2:uci.termbondUnconfirmedList)
		{
			s=NumberManager.getAddString(s,uc2.amount,2);
		}
		return s;
	}
    private void init()
    {
        ll=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        title.setText("总资产");
        
        ll1=(LinearLayout)findViewById(R.id.ll1);
        ll2=(LinearLayout)findViewById(R.id.ll2);
        
        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
        tv3=(TextView) findViewById(R.id.tv3);
        
        tv4=(TextView) findViewById(R.id.tv4);
        tv5=(TextView) findViewById(R.id.tv5);
        tv6=(TextView) findViewById(R.id.tv6);
        tv7=(TextView) findViewById(R.id.tv7);
        huoqi=(TextView) findViewById(R.id.tv_huoqi);
        
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        ll.setOnClickListener(this);
        
        if(uc!=null)
        {   
        	String s1=NumberManager.getAddString(uc.fundAmount,uc.bondAmount,4);
        	s1=NumberManager.getGeshiHua(s1,2);
        	tv1.setText(s1);
        	String s2=NumberManager.getString(uc.fundAmount,"1",2);
        	s2=NumberManager.getGeshiHua(s2,2);
        	tv2.setText(s2+"元");
        	String s3=NumberManager.getString(uc.bondAmount,"1",2);
        	s2=NumberManager.getGeshiHua(s3,2);
        	tv3.setText(s3+"元");
        }
        String total_money=getIntent().getStringExtra("total_money");
        tv1.setText(total_money);
    }
    ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		   
		    	if(uc!=null)
		    	{
		    		 Intent intent=new Intent();
					 intent.setClass(ZhiChanActivity.this,HuoqiReturnIActivity.class);
					 intent.putExtra("index",fi);
					 intent.putExtra("index1",uc.fundAmount);
					 startActivity(intent);
		    	}
			
		    
		   
		}
	};
   public void onClick(View view)
   {  
	   Intent intent=new Intent();
      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
         case R.id.tv4:
        	LiuChenManager.StartNext2(this, icb2);
          break;
         case R.id.tv5:
        	 intent.setClass(this,DingqiZhichanDetial.class);
        	 intent.putExtra(DingqiZhichanDetial.index,(Serializable)uc3);
             startActivity(intent);
             break;
         case R.id.tv7:
        	 intent.setClass(this,QianbaoActivity1.class);
        	 intent.putExtra(DingqiZhichanDetial.index,(Serializable)uc3);
             startActivity(intent);
             break;
      }
   }
}
