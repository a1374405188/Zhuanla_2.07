package com.beikbank.android.widget3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.GengDuoActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HomeActivity4;
import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.HuoqiDetailActivity;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.MessageActivity;
import com.beikbank.android.activity.UserRecordActivity_v2;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.NoticeHlep;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.Notice;
import com.beikbank.android.data.Notice_data;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.GetChanPin_data;
import com.beikbank.android.data2.JinXuan;
import com.beikbank.android.data2.Jinxuan_data;
import com.beikbank.android.data2.Jinxuan_data2;
import com.beikbank.android.data2.LiCai;
import com.beikbank.android.data2.LunBo;
import com.beikbank.android.data2.LunBo_data;
import com.beikbank.android.data2.LunBo_data2;
import com.beikbank.android.dataparam2.GetChanPinParam;
import com.beikbank.android.dataparam2.JinXuanParam;
import com.beikbank.android.dataparam2.LunBoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ImageUrl;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.NoticeManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.net.impl2.ChanpinManager;
import com.beikbank.android.net.impl2.ZhiCanManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.share.ShateUtil;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.hongbao.LicaiUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget2.SlideShowView;
import coma.beikbank.android.R;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
/**
 * 
 * @author Administrator
 *理财
 */
public class PageView2 extends LinearLayout implements OnClickListener {
	
    

    Activity act;
    View view;
    private PullToRefreshScrollView prs;
    private ScrollView sv;
    /**
     * 产品父布局
     */
    private LinearLayout ll_canpin;
    
	public PageView2(Context context) {
		super(context);
		act=(Activity) context;
		init();
		
	}
	public PageView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		act=(Activity) context;
		init();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public void init()
	{
		LinearLayout ll=new LinearLayout(act);
    	view= LayoutInflater.from(act).inflate(R.layout.view_pageview2_1,ll,false);
		View view0 =LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		view0.setLayoutParams(lp);
    	View view1=LayoutInflater.from(act).inflate(R.layout.view_pageview2_2,ll,false);
    	view1.setLayoutParams(lp);
    	view.setLayoutParams(lp);
    	
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
    		
    	{
    		LinearLayout ll_top=(LinearLayout) view.findViewById(R.id.ll_top);
    	   int h=StateBarColor.getStatusBarHeight(act);
    	   DensityUtil du=new DensityUtil(act);
    	
    	ll_top.setPadding(du.dip2px(16),h,du.dip2px(16),0);
    	LayoutParams lp_top=new LayoutParams(LayoutParams.MATCH_PARENT,du.dip2px(44)+h);
    	ll_top.setLayoutParams(lp_top);
    	}
    	
    	
    	
    	 prs=(PullToRefreshScrollView)view0.findViewById(R.id.prs);
 		prs.setPullLoadEnabled(false);
 		prs.setScrollLoadEnabled(false);
 		prs.getRefreshableView();
 		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
 				{

 					@Override
 					public void onPullDownToRefresh(
 							PullToRefreshBase<ScrollView> refreshView) {
 					
 						initData();
 					}

 					@Override
 					public void onPullUpToRefresh(
 							PullToRefreshBase<ScrollView> refreshView) {
 						
 						
 					}
 				});
 		
 		
 		
 		
 		sv=prs.getRefreshableView();
		sv.addView(view1);
		LinearLayout ll_paret=(LinearLayout) view.findViewById(R.id.ll_parent);
		ll_paret.addView(view0);
        addView(view);
        ll_canpin=(LinearLayout) findViewById(R.id.ll_canpin);
        prs.doPullRefreshing(true,200);
        //initData();
        
	}
	ArrayList<GetChanPin> list;
	public void initData()
	{
		
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				prs.onPullDownRefreshComplete();
				if(obj!=null)
				{   
					
					ll_canpin.removeAllViews();
					GetChanPin_data gd=(GetChanPin_data) obj;
					list=gd.body.productInfoList;
					list=soft(list);
					LicaiUtil lu=new LicaiUtil(act);
					lu.addView(ll_canpin, list);
					if(list==null||list.size()==0)
					{
						return;
					}
					setTuiJian(selectTuiJian(list));
					
				}
				
			}
		};
		
		GetChanPinParam pp=new GetChanPinParam();
		 ManagerParam mp=new ManagerParam();
		 mp.isShowDialog=false;
    	TongYongManager2 tym2=new TongYongManager2(act, icb,pp,mp);
    	tym2.start();
		
	}
	/**
	 * 对产品进行排序 
	 * 未登录 新用户
	 *    新手标   活期   定期(短---长)
	 * 登录   老用户
	 *    定期(短---长) 活期   新手标
	 *  
	 * @param list
	 * @return
	 */
	private ArrayList<GetChanPin> soft(ArrayList<GetChanPin> list)
	{
		ArrayList<GetChanPin> list0=new ArrayList<GetChanPin>();
		//新手标
		ArrayList<GetChanPin> list1=new ArrayList<GetChanPin>();
		//活期
		ArrayList<GetChanPin> list2=new ArrayList<GetChanPin>();
        //定期
		ArrayList<GetChanPin> list3=new ArrayList<GetChanPin>();
		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		String is_new=BeikBankApplication.getSharePref(BeikBankConstant.is_olduser);
		GetChanPin gcp=null;
		for(int i=0;i<list.size();i++)
		{   
			
			gcp=list.get(i);
			if("0".equals(gcp.is_new_user_mark))
			{
				list1.add(gcp);
			}
			else if("4".equals(gcp.product_type_pid))
			{
				list2.add(gcp);
			}
			else
			{
				list3.add(gcp);
			}
			
			
		}
		Collections.sort(list3);
		if(!do_success)
		{
			list0.addAll(list1);
			list0.addAll(list2);
			list0.addAll(list3);
			
		}
		else if(do_success&&"0".equals(is_new))
		{
			list0.addAll(list1);
			list0.addAll(list2);
			list0.addAll(list3);
			
		}
		else
		{   list0.addAll(list3);
			list0.addAll(list2);
			
			list0.addAll(list1);
			
		}
		
		return list0;
	}
	/**
	 * 选出推荐的标的
	 */
	private GetChanPin selectTuiJian(ArrayList<GetChanPin> list)
	{
		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		String is_new=BeikBankApplication.getSharePref(BeikBankConstant.is_olduser);
		       //新手标
				ArrayList<GetChanPin> list1=new ArrayList<GetChanPin>();
				//活期
				ArrayList<GetChanPin> list2=new ArrayList<GetChanPin>();
		        //定期
				ArrayList<GetChanPin> list3=new ArrayList<GetChanPin>();
				GetChanPin gcp=null;
				for(int i=0;i<list.size();i++)
				{   
					gcp=list.get(i);
					int a=NumberManager.isDaYu(gcp.pro_share,"0");
					if(a<=0)
					{
						continue;
					}
					
					
					if("0".equals(gcp.is_new_user_mark))
					{
						list1.add(gcp);
					}
					else if("4".equals(gcp.product_type_pid))
					{
						list2.add(gcp);
					}
					else
					{
						list3.add(gcp);
					}
				}
				Collections.sort(list3);
				gcp=null;
				if(list1.size()==0)
				{
					if(list3.size()>0)
					{
						gcp=list3.get(list3.size()-1);
					}
					else
					{  
						if(list2.size()>0)
						{
						  gcp=list2.get(0);
						}
					}
				}
				else
				{
				   if(!do_success||"0".equals(is_new))
				   {
					   gcp=list1.get(0);
				   }
				   else
				   {   
					   if(list3.size()>0)
					   {
					      gcp=list3.get(list3.size()-1);
					   }
				   }
				}
				return gcp;
	}
	
	/**
	 * 设置推荐标的
	 */
	public void setTuiJian(GetChanPin gcp)
	{
		
		HomeActivity4.pv1.setTuiJian(gcp);
		 
	}
}
