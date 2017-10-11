package com.beikbank.android.activity;

import java.io.Serializable;
import java.util.ArrayList;

import android.R.interpolator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.UserCapital2;
import com.beikbank.android.data.UserCapitalInfo;
import com.beikbank.android.data.UserCapitalInfo2;
import com.beikbank.android.data.UserCapitalInfo_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao6Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.UserCapital2Manager;
import com.beikbank.android.net.impl.UserCapitalInfoManager;
import com.beikbank.android.net.impl2.ZhiCanManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.text.TextUtil;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-18
 * 资产2
 */
public class ZhiChanActivity2 extends BaseActivity1 implements View.OnClickListener{
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
     * 活期跳转
     */
    LinearLayout ll3;
    /**
     * 定期跳转
     */
    LinearLayout ll4;
    /**
     * 钱包跳转
     */
    LinearLayout ll5;
    /**
     * 红包跳转
     */
    LinearLayout ll6;
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
    /**
     * 几张可用
     */
    TextView tv7;
    /**
     * 活期赎回中
     */
    TextView tv8;
    /**
     * 定期募集中
     */
    TextView tv9;
    //TextView huoqi;
    ImageView iv1;
    //定期详情
    ImageView iv2;
    /**
     * 箭头图标
     */
    ImageView img1;
    /**
     * 活期赎回中父布局
     */
    LinearLayout ll7;
    /**
     * 定期募集中父布局
     */
    LinearLayout ll8;
    public static final String index="index0";
    public static final String index2="index";
    UserCapital2 uc=new UserCapital2();
    FundInfo fi;
    Activity act=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhichan_4);
        StateBarColor.init(this,0xffffffff);
        boolean isnet=isNetworkConnected(this);
        if(!isnet)
        {
        	NoNetShow.show(this,"资产详情",null);
        	return;
        }
        //uc=(UserCapital2) getIntent().getSerializableExtra(index);
        //fi= (FundInfo) getIntent().getSerializableExtra(index2);
        
        init();
        //addData();
        if(HomeActivity3.ha.zc!=null)
        {   
        	zc=HomeActivity3.ha.zc;
        	icb1.back("1");
        }
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
    ZhiCanManager zc;
    private void addData()
    {   
//    	TotalMoneyParam tp=new TotalMoneyParam();
//    	tp.memberID=BeikBankApplication.getUserid();
//    	UserCapitalInfoManager ucm=new UserCapitalInfoManager(this, icb, tp);
//    	ucm.start();
//    	
//    	//加载钱包余额
////    	
////		Qianbao6Manager qm=new Qianbao6Manager(this, icb3,tp);
////		qm.start();
//    	TotalMoneyParam tmp=new TotalMoneyParam();
//	    tmp.memberID=BeikBankApplication.getUserid();
//    	UserCapital2Manager usm=new UserCapital2Manager(this,icb4,tmp);
//		usm.start();
//		
//		YuerParam yp=new YuerParam();
//		yp.memberID=BeikBankApplication.getUserid();
//		TongYongManager tym=new TongYongManager(this, icb5,yp);
//		tym.start();
		
		
		//得到几张可用
//		HongbaoParam hp=new HongbaoParam();
//		hp.userId=BeikBankApplication.getUserid();
//		
//		TongYongManager tym=new TongYongManager(this, icb5,hp);
//		tym.start();
    	zc=new ZhiCanManager(this, icb1);
    	zc.start();
    	
    }
    private ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			prs.onPullDownRefreshComplete();
			if(obj!=null)
			{
				uc=zc.uc;
			        	//TextUtil.setSize(this, tv1,s1);
			        	String s2=NumberManager.getString(uc.fundAmount,"1",2);
			        	s2=NumberManager.getGeshiHua(s2,2);
			        	tv2.setText(s2);
//			        	String s3=NumberManager.getString(uc.bondAmount,"1",2);
//			        	s2=NumberManager.getGeshiHua(s3,2);
//			        	tv3.setText(s3);
			            tv1.setText(zc.money);
			            
			            
			            
			            uc2=zc.uci.termbondUnconfirmedList;
						uc3=zc.uci.termbondList;
						addData2(zc.uci.termbondUnconfirmedList,1);
						
						String s1=countMuji(zc.uci);
						if(HongbaoUtil.compreS(s1,"0")>0)
						{   
							s1=NumberManager.getGeshiHua(s1,2);
							RelativeLayout rl8=(RelativeLayout) findViewById(R.id.rl8);
							rl8.setVisibility(View.VISIBLE);
							ll8.setVisibility(View.VISIBLE);
							tv9.setText(s1);
						}
						tv3.setText(NumberManager.getGeshiHua(countTotal(zc.uci),2));
			             
						
						 tv6.setText(NumberManager.getGeshiHua(zc.qianbao,2));
							
						 int a=HongbaoUtil.compreS(zc.ye.frozenAmountRedeem,"0");
						 if(a>0)
						 {    String s4=NumberManager.getGeshiHua(zc.ye.frozenAmountRedeem,2);
							 tv8.setText(s4);
							 ll7.setVisibility(View.VISIBLE);
						 }
						 else
						 {
							 ll7.setVisibility(View.GONE);
						 }
						 tv6.setText(NumberManager.getGeshiHua(zc.qianbao,2));
			}
			
			else
			{
				Toast.makeText(act,"网络异常",Toast.LENGTH_LONG).show();
			}
		}
	};
    /**
     * 得到几张可用回调
     */
    ICallBack icb5=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			prs.onPullDownRefreshComplete();
			if(obj!=null)
			{
				Hongbao_data hd=(Hongbao_data) obj;
				ArrayList<Hongbao> list=hd.data;
				String count=select(list);
				tv7.setText(count);
			}
			
		}
	};
	/**
	 * 得到可用的红包 
	 * @return
	 */
	public static String select(ArrayList<Hongbao> list)
	{   
		if(list==null)
		{
			return ""+0;
		}
		int count=0;
		for(Hongbao hb:list)
		{
			if(hb.isOld.equals("0"))
			{
				int a=Integer.parseInt(hb.needCount);
				if(a>0)
				{
					count++;
				}
			}
			
			
		}
		return ""+count;
	}
   private ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj!=null)
			{
				 Qianbao4_data qd=(Qianbao4_data) obj;
				
//				 String s1=NumberManager.getAddString(qd.data.activeAmount,qd.data.frozenAmountPurchase,8);
//				 s1=NumberManager.getAddString(s1,qd.data.frozenAmountRedeem,8);
//				 s1=NumberManager.getAddString(s1,qd.data.frozenAmountWithdraw,2);
//				 s1=NumberManager.getGeshiHua(s1,2);
				 tv6.setText(NumberManager.getGeshiHua(qd.data.activeAmount,2));
				
				 int a=HongbaoUtil.compreS(qd.data.frozenAmountRedeem,"0");
				 if(a>0)
				 {    String s2=NumberManager.getGeshiHua(qd.data.frozenAmountRedeem,2);
					 tv8.setText(s2);
					 ll7.setVisibility(View.VISIBLE);
				 }
				 else
				 {
					 ll7.setVisibility(View.GONE);
				 }
				
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
    	ll.removeAllViews();
    	if(list==null||list.size()==0)
    	{   
    		img1.setVisibility(View.INVISIBLE);
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
				
				String s1=countMuji(uci);
				if(HongbaoUtil.compreS(s1,"0")>0)
				{   
					s1=NumberManager.getGeshiHua(s1,2);
					RelativeLayout rl8=(RelativeLayout) findViewById(R.id.rl8);
					rl8.setVisibility(View.VISIBLE);
					ll8.setVisibility(View.VISIBLE);
					tv9.setText(s1);
				}
				tv3.setText(NumberManager.getGeshiHua(countTotal(uci),2));
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
	private PullToRefreshScrollView prs;
	private ScrollView sv;
    private void init()
    {   
    	LayoutInflater li=getLayoutInflater();
		LinearLayout ll=new LinearLayout(this);
		View view = li.inflate(R.layout.activity_zhichan_2,ll,false);
    	prs=(PullToRefreshScrollView) findViewById(R.id.prs);
		prs.setPullLoadEnabled(false);
		prs.setScrollLoadEnabled(false);
		prs.getRefreshableView();
		sv=prs.getRefreshableView();
		sv.addView(view);
    	
    	
        ll=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        title.setText("资产详情");
        
        ll1=(LinearLayout)view.findViewById(R.id.ll1);
        ll2=(LinearLayout)view.findViewById(R.id.ll2);
        ll3=(LinearLayout)view.findViewById(R.id.ll3);
        ll4=(LinearLayout)view.findViewById(R.id.ll4);
        ll5=(LinearLayout)view.findViewById(R.id.ll5);
        ll6=(LinearLayout)view.findViewById(R.id.ll6);
        ll7=(LinearLayout)view.findViewById(R.id.ll7);
        ll8=(LinearLayout)view.findViewById(R.id.ll8);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        
        tv1=(TextView) view.findViewById(R.id.tv1);
        tv2=(TextView) view.findViewById(R.id.tv2);
        tv3=(TextView) view.findViewById(R.id.tv3);
        
        tv4=(TextView) view.findViewById(R.id.tv4);
        
        tv6=(TextView) view.findViewById(R.id.tv6);
        tv7=(TextView) view.findViewById(R.id.tv7);
        tv8=(TextView) view.findViewById(R.id.tv8);
        tv9=(TextView) view.findViewById(R.id.tv9);
        img1=(ImageView) view.findViewById(R.id.img1);
        iv2=(ImageView) view.findViewById(R.id.iv2);
        
        
        iv2.setOnClickListener(this);
       
        ll.setOnClickListener(this);
        
        if(uc!=null)
        {   
        	String s1=NumberManager.getAddString(uc.fundAmount,uc.bondAmount,4);
        	s1=NumberManager.getGeshiHua(s1,2);
        	tv1.setText(s1);
        	TextUtil.setSize(this, tv1,s1);
        	String s2=NumberManager.getString(uc.fundAmount,"1",2);
        	s2=NumberManager.getGeshiHua(s2,2);
        	tv2.setText(s2);
        	String s3=NumberManager.getString(uc.bondAmount,"1",2);
        	s2=NumberManager.getGeshiHua(s3,2);
        	tv3.setText(s3);
        }
        String total_money=getIntent().getStringExtra("total_money");
        tv1.setText(total_money);
        
        
        prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) 
			{
                       addData();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				
			}
		});
    }
    ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		   if(obj==null)
		   {	if(uc!=null)
		    	{
		    		 Intent intent=new Intent();
					 intent.setClass(ZhiChanActivity2.this,HuoqiReturnIActivity.class);
					 intent.putExtra("index",zc.fi);
					 intent.putExtra("index1",uc.fundAmount);
					 startActivity(intent);
		    	}
			
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
         case R.id.ll3:
        	LiuChenManager.StartNext(this, icb2);
          break;
         case R.id.ll4:
        	 intent.setClass(this,DingqiZhichanDetial.class);
        	 intent.putExtra(DingqiZhichanDetial.index,(Serializable)uc3);
             startActivity(intent);
             break;
         case R.id.ll5:
        	 LiuChenManager.StartNext(this, icb4);
        	 break;
         case R.id.ll6:
        	 intent.setClass(this,YouHuiQuanActivity.class);
             startActivity(intent);
             break;
      }
   }
   ICallBack icb4=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj==null)
		{   
			Intent intent=new Intent();
			intent.setClass(ZhiChanActivity2.this,QianbaoActivity8.class);
       	    intent.putExtra(DingqiZhichanDetial.index,(Serializable)uc3);
            startActivity(intent);
		}
		
	}
};
}
