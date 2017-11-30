package com.beikbank.android.widget3;


import com.beikbank.android.activity.ChanPinActivityV2;

import com.beikbank.android.activity.HuodongActivity2;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data2.GetChanPin;

import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;

import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.utils.BeikBankConstant;

import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;





import android.content.Context;
import android.content.Intent;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.view.View.OnClickListener;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * 
 * @author Administrator
 *活期
 */
public class Pview1 extends LinearLayout implements OnClickListener{
	private PullToRefreshScrollView prs;
    private ScrollView sv;
    private ChanPinActivityV2 act;

	public Pview1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	TextView tv_bianhao;
	TextView tv_qigiou;
	TextView tv_genxin;
	TextView tv_qixi;
	TextView tv_fenxian_tishi;
	TextView tv_fankuan;
	TextView tv_fankuan_text;
	LinearLayout ll_fenxiantishi;
	LinearLayout ll_chanpinjiesao;
	LinearLayout ll_fenxianfensan;
	LinearLayout ll_goumaixiane;
	
	
	
	public Pview1(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	private void init(Context context)
	{   
		act=(ChanPinActivityV2) context;
		LinearLayout ll=new LinearLayout(context);
	
		View view0 =LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		view0.setLayoutParams(lp);
		final ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				prs.onPullDownRefreshComplete();
				
			}
		};
		
		prs=(PullToRefreshScrollView)view0.findViewById(R.id.prs);
		prs.setPullLoadEnabled(false);
		prs.setScrollLoadEnabled(false);
		prs.getRefreshableView();
		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
		{

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				    //prs.onPullDownRefreshComplete();
				    act.initData2(icb);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sv=prs.getRefreshableView();
		
		
		
		
		
		View view= LayoutInflater.from(context).inflate(R.layout.pview1_v2,ll,false);
		tv_bianhao=(TextView) view.findViewById(R.id.tv_bianhao);
		tv_qigiou=(TextView) view.findViewById(R.id.tv_qigou);
		tv_genxin=(TextView) view.findViewById(R.id.tv_gengxin);
		tv_qixi=(TextView) view.findViewById(R.id.tv_qixi);
		tv_fankuan=(TextView) view.findViewById(R.id.tv_fankuan);
		tv_fankuan_text=(TextView) view.findViewById(R.id.tv_fankuan_text);
		ll_chanpinjiesao=(LinearLayout) view.findViewById(R.id.ll_chanpinjiesao);
		ll_fenxiantishi=(LinearLayout) view.findViewById(R.id.ll_fenxiantishi);
		ll_fenxianfensan=(LinearLayout) view.findViewById(R.id.ll_fenxianfensan);
		ll_goumaixiane=(LinearLayout) view.findViewById(R.id.ll_goumaixiane);
		ll_chanpinjiesao.setOnClickListener(this);
		ll_fenxiantishi.setOnClickListener(this);
		ll_fenxianfensan.setOnClickListener(this);
		
		sv.addView(view);
		//tv_fenxian_tishi.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		addView(view0);
		
	}
	

	GetChanPin gcp;
	public void addData(GetChanPin gcp)
	{   
		this.gcp=gcp;
		tv_bianhao.setText(gcp.product_id);
		tv_qigiou.setText(gcp.purchase_amount+"元");
	 
	    	if("1".equals(gcp.start_calculating_interest_time))
	    	{
	    		tv_qixi.setText("购买后当日起息");
	    	}
	    	else if("2".equals(gcp.start_calculating_interest_time))
	    	{
	    		tv_qixi.setText("T+1(购买后次日起息)");
	    	}
	    	else
	    	{
	    		tv_qixi.setText("满标后次日日起息");
	    		
	    	}
	    	String genxin="每天";
	    	for(int i=0;i<gcp.share_update_time.size();i++)
	    	{
	    		genxin=genxin+gcp.share_update_time.get(i);
	    		if(i!=gcp.share_update_time.size()-1)
	    		{
	    			genxin+="、";
	    		}
	    		
	    	}
	       tv_genxin.setText(genxin);
		   
	       
	       
		   if("4".equals(gcp.product_type_pid))
		   {
			  LinearLayout ll_zhuanrangshuoming=(LinearLayout) findViewById(R.id.ll_zhuanrangshuoming);
		      View v_zhuanrangshuoming=findViewById(R.id.v_zhuanrangshuoming);
		      
		      LinearLayout ll_goumaixiane=(LinearLayout) findViewById(R.id.ll_goumaixiane);
		      View v_goumaixiane=findViewById(R.id.v_goumaixiane);
		      
		      
		      ll_zhuanrangshuoming.setVisibility(View.VISIBLE);
		      v_zhuanrangshuoming.setVisibility(View.VISIBLE);
		      ll_goumaixiane.setVisibility(View.VISIBLE);
		      //v_goumaixiane.setVisibility(View.VISIBLE);
		      
		      
		      
			  LinearLayout ll_fankuan=(LinearLayout) findViewById(R.id.ll_fankuan);
		      View v_fankuan=findViewById(R.id.v_fankuan);
		    
		     // v_fankuan.setVisibility(View.GONE);
		      ll_fankuan.setVisibility(View.GONE);
		      
		      
		      
		      ll_goumaixiane.setVisibility(View.VISIBLE);
		      
		      TextView tv_goumaixiane=(TextView) findViewById(R.id.tv_goumaixiane);
		      String s1="每个用户限购"+gcp.cumulative_purchase_amount+"元";
		      
		      tv_goumaixiane.setText(s1);
		      if(NumberManager.isDaYu("0",gcp.cumulative_purchase_amount)>=0)
		      {
		    	  tv_goumaixiane.setText("不限制购买总额");
		      }
		      
		   }
		   else
		   {
			   
		   }
		   

	}
	
	

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(act,HuodongActivity2.class);
		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		switch(v.getId())
		{
		//购买
		case R.id.bu_goumai:
		
			break;
		case R.id.ll_chanpinjiesao:
			
			intent.putExtra("title","产品介绍");
			intent.putExtra("url",SystemConfig.huodong_url+"#!/product/productJs");
			act.startActivity(intent);
			break;	
         case R.id.ll_fenxiantishi:
        	 intent.putExtra("title","风险提示");
 			intent.putExtra("url",SystemConfig.huodong_url+"#!/product/productFx");
 			act.startActivity(intent);
			break;		
         case R.id.ll_fenxianfensan:
        	 intent.putExtra("title","风险分散");
 			intent.putExtra("url",SystemConfig.huodong_url+"#!/CpFxfs/"+gcp.product_id);
 			act.startActivity(intent);
			break;	
		}
		
	}
	
		/**
		 * 回调到赎回
		 */
		ICallBack icb5=new ICallBack() {
			
			@Override
			public void back(Object obj) {
			
				if(obj==null)
				{
				
				}
			}
		};
		/**
		 * 回调到选择支付
		 */
		ICallBack icb4=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj==null)
				{
				
				}
			}
		};
	
	
}
