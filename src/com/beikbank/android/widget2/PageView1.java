package com.beikbank.android.widget2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.GengDuoActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.HuoqiDetailActivity;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.MessageActivity;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.NoticeHlep;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.Notice;
import com.beikbank.android.data.Notice_data;
import com.beikbank.android.data2.JinXuan;
import com.beikbank.android.data2.Jinxuan_data;
import com.beikbank.android.data2.Jinxuan_data2;
import com.beikbank.android.data2.LunBo;
import com.beikbank.android.data2.LunBo_data;
import com.beikbank.android.data2.LunBo_data2;
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
import com.beikbank.android.utils2.StateBarColor;
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
/**
 * 
 * @author Administrator
 *精选界面
 */
public class PageView1 extends LinearLayout implements OnClickListener {
	/**
	 * 登录
	 */
	TextView tv1;
    //ImageViewLunbo iml;
    private ImageView iv_xiaoxi;
    private Activity act;
    private PullToRefreshScrollView prs;
    private ScrollView sv;
    private Button bu_goumai;
    private TextView tv_jiaxi;
    private LinearLayout ll_gonggao;
    private LinearLayout ll_notice;
    private TextView tv_notice;
    
    /**
     * 
     */
    public LinearLayout ll_msg_show;
    
    
	public PageView1(Context context) {
		super(context);
		act=(Activity) context;
		init(context);
		
	}
	public PageView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		act=(Activity) context;
		
		init(context);
		
	}
	View view;
	LinearLayout ll_dingqi;
	LinearLayout ll_huoqi;
	LinearLayout ll_gotong;
	
	private ImageView iv_huodong;
	private ImageView iv_yaoqing;
	private ImageView iv_anquan;
	
	
	/**
	 * 初始化,该方法必须调用
	 */
    private void init(Context context)
    {   
    	
    	LinearLayout ll=new LinearLayout(act);
    	view= LayoutInflater.from(act).inflate(R.layout.view_pageview1,ll,false);
		View view0 =LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		view0.setLayoutParams(lp);
    	View view1=LayoutInflater.from(act).inflate(R.layout.view_pageview1_1,ll,false);
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
    	
    	
    	ll_notice=(LinearLayout) view1.findViewById(R.id.ll_notice);
    	tv_notice=(TextView) ll_notice.findViewById(R.id.tv_notice);
    	
    	iv_anquan=(ImageView) view1.findViewById(R.id.iv_anquan);
    	iv_yaoqing=(ImageView) view1.findViewById(R.id.iv_yaoqiu);
    	iv_huodong=(ImageView) view1.findViewById(R.id.iv_huodong);
    	
    	
    	
        bu_goumai=(Button) view1.findViewById(R.id.bu_goumai);
        bu_goumai.setOnClickListener(this);
        tv_jiaxi=(TextView) view1.findViewById(R.id.tv_jiaxi);
        iv_xiaoxi=(ImageView) view.findViewById(R.id.iv_xiaoxi);
        
        iv_xiaoxi.setOnClickListener(this);
        tv1=(TextView) view.findViewById(R.id.tv1);
        
        tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(act,LoginRegActivity.class);
				act.startActivity(intent);
				
			}
		});
      
        
        
        
        
        init2();
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
				if(BaseActivity.isNetworkConnected(act))
				{
					ll_msg_show.setVisibility(View.GONE);
				}
				else
				{
					ll_msg_show.setVisibility(View.VISIBLE);
				}
				
				initData();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sv=prs.getRefreshableView();
		sv.addView(view1);
		LinearLayout ll_paret=(LinearLayout) view.findViewById(R.id.ll_parent);
		ll_paret.addView(view0);
        addView(view);
        ll_dingqi=(LinearLayout) view.findViewById(R.id.ll_dingqi);
        ll_huoqi=(LinearLayout) view.findViewById(R.id.ll_huoqi);
        ll_gotong=(LinearLayout) view.findViewById(R.id.ll_gotong);
        ll_dingqi.setOnClickListener(this);
        ll_huoqi.setOnClickListener(this);
        ll_gotong.setOnClickListener(this);
        
        
        ll_msg_show=(LinearLayout) view.findViewById(R.id.ll_msg_show);
        
	    prs.doPullRefreshing(false,0);
    }
    /**
     * 初始化登录有关的信息
     */
    public void init2()
    {
    	ImageView iv1=(ImageView) view.findViewById(R.id.iv_top1);
        iv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ShateUtil su=new ShateUtil(act,view);
				su.share(act);
			}
		});
        boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
        if(do_success)
        {
        	iv1.setVisibility(View.VISIBLE);
        	tv1.setVisibility(View.GONE);
        }
        else
        {
        	tv1.setVisibility(View.VISIBLE);
        	iv1.setVisibility(View.GONE);
        }
    }
    /**
     * 设置活动事件监听器
     */
    private void SetOn(View view,final List<JinXuan> list)
    {
    	
    	LinearLayout ll_huodong=(LinearLayout) view.findViewById(R.id.ll_huodong);
    	LinearLayout ll_yaoqing=(LinearLayout) view.findViewById(R.id.ll_yaoqin);
    	LinearLayout ll_anquan=(LinearLayout) view.findViewById(R.id.ll_anquan);
    	LinearLayout ll_gengduo=(LinearLayout) view.findViewById(R.id.ll_gengduo);
    	
    	ll_gengduo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(act,GengDuoActivity.class);
				act.startActivity(intent);
			}
		});
    	ll_huodong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				JinXuan jx=list.get(0);
				
				Intent intent=new Intent(act,HuodongActivity2.class);
				intent.putExtra("title",jx.areatitle);
				intent.putExtra("url",jx.arealink);
				//intent.putExtra("url","http://www.beikbank.com:8081/lottery_admin/redPackageRain/");
				act.startActivity(intent);
			}
		});
    	ll_yaoqing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
JinXuan jx=list.get(1);
				
				Intent intent=new Intent(act,HuodongActivity2.class);
				intent.putExtra("title",jx.areatitle);
				intent.putExtra("url",jx.arealink);
				act.startActivity(intent);
			}
		});
    	ll_anquan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
JinXuan jx=list.get(2);
				
				Intent intent=new Intent(act,HuodongActivity2.class);
				intent.putExtra("title",jx.areatitle);
				intent.putExtra("url",jx.arealink);
				act.startActivity(intent);
			}
		});
    	
    	
    }
    ArrayList<LunBo> list2;
    private void initData()
    {
    	addNotice();
    	cm=new ChanpinManager(act, icb3);
    	cm.start();
    	
    	
    	ICallBack icb4=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				prs.onPullDownRefreshComplete();
				
				if(obj!=null)
				{
					
					LunBo_data ld=(LunBo_data) obj;
					LunBo_data2 ld2=ld.body;
					//ArrayList<JinXuan> list=ld2.arealist;
					
					
					
					   list2=ld2.imglist;
					   SlideShowView sli=(SlideShowView) view.findViewById(R.id.ll1);
				    	//View view=act.getLayoutInflater().inflate(R.layout.view_pageview1,ll,false);
				    	//addView(view);
				        
				       // List<String> list=new ArrayList<String>();
				       // list.add("http://g.hiphotos.baidu.com/imgad/pic/item/a8773912b31bb051be533b24317adab44aede043.jpg");
				       //  list.add("http://news.cnhubei.com/xw/yl/201603/W020160315318942970371.jpg");
				        String imgs[]=new String[list2.size()];
				        for(int i=0;i<imgs.length;i++)
				        {
				        	imgs[i]=list2.get(i).imgurl;
				        	
				        }
				        //String imgs2[]={"http://g.hiphotos.baidu.com/imgad/pic/item/a8773912b31bb051be533b24317adab44aede043.jpg","http://news.cnhubei.com/xw/yl/201603/W020160315318942970371.jpg"};
				        ICallBack icb=new ICallBack() {
							
							@Override
							public void back(Object obj) {
								LunBo lb=list2.get((Integer)obj);
								Intent intent=new Intent(act,HuodongActivity2.class);
								intent.putExtra("title",lb.imgtitle);
								intent.putExtra("url",lb.imglink);
								act.startActivity(intent);
							}
						};
					    sli.start(imgs, icb);
				       
				}
				
			}
		};
    	LunBoParam lbp=new LunBoParam();
    	lbp.req_time="1";
    	lbp.userid=BeikBankApplication.getUserCode();
    	ManagerParam mp=new ManagerParam();
    	mp.isShowDialog=false;
    	TongYongManager2 tym=new TongYongManager2(act, icb4,lbp,mp);
    	tym.start();
    	
    	
    	
    	
    	ICallBack icb2=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				
				if(obj!=null)
				{
					Jinxuan_data jd=(Jinxuan_data) obj;
					Jinxuan_data2 jd2=jd.body;
					ArrayList<JinXuan> list=jd2.arealist;
					setArea(list);
					SetOn(view, list);
				}
			}
		};
    	JinXuanParam jxp=new JinXuanParam();
    	jxp.userid=BeikBankApplication.getUserid();
    	jxp.req_time="1";
    	TongYongManager2 tym2=new TongYongManager2(act, icb2,jxp,mp);
    	tym2.start();
    }
    
    private void setArea(ArrayList<JinXuan> list)
    {
    	
    	
    	TextView tv_huodong=(TextView) findViewById(R.id.tv_huodong);
		TextView tv_huodong_text=(TextView) findViewById(R.id.tv_huodong_text);
		TextView tv_yaoqing=(TextView) findViewById(R.id.tv_yaoqing);
		TextView tv_yaoqing_text=(TextView) findViewById(R.id.tv_yaoqing_text);
		TextView tv_anquan=(TextView) findViewById(R.id.tv_anquan);
		TextView tv_anquan_text=(TextView) findViewById(R.id.tv_anquan_text);
		TextView tv_gengduo=(TextView) findViewById(R.id.tv_gengduo);
		TextView tv_gengduo_text=(TextView) findViewById(R.id.tv_gengduo_text);
		JinXuan jxh=list.get(0);
		tv_huodong.setText(jxh.areatitle);
		tv_huodong_text.setText(jxh.areacontext);
		
		
		 ImageUrl iu=new ImageUrl(iv_huodong,jxh.arealogourl);
		 iu.start();
		
		jxh=list.get(1);
		tv_yaoqing.setText(jxh.areatitle);
		tv_yaoqing_text.setText(jxh.areacontext);
		 iu=new ImageUrl(iv_yaoqing,jxh.arealogourl);
		 iu.start();
		
		
		jxh=list.get(2);
		tv_anquan.setText(jxh.areatitle);
		tv_anquan_text.setText(jxh.areacontext);
		 iu=new ImageUrl(iv_anquan,jxh.arealogourl);
		 iu.start();
		
		
		
		
		
    	
    	
    }
    
    
    
   
    
    private ChanpinManager cm;
    /**
     * true当前是定期，false当前是活期
     */
    private boolean isHasNew=true;
    private DingqiP2 dp2;
    private ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		   if(obj==null)
		   {
			   prs.onPullDownRefreshComplete();
			   return;
		   }
		   Log.e("p1","p1");
	       select(cm);
	       
	       setTuiJian();
	       
	       
	       
	       
//	       iml=(ImageViewLunbo)view.findViewById(R.id.ll1);
	    	//View view=act.getLayoutInflater().inflate(R.layout.view_pageview1,ll,false);
	    	//addView(view);
	        
//	        List<String> list=new ArrayList<String>();
//	        list.add("http://g.hiphotos.baidu.com/imgad/pic/item/a8773912b31bb051be533b24317adab44aede043.jpg");
//	        list.add("http://news.cnhubei.com/xw/yl/201603/W020160315318942970371.jpg");
//	        iml.init(act,list);
	//        start();
		}
	};
	/**
	 * 选择默认的标的
	 * 
	 */
	private void select(ChanpinManager cm)
	{    
		 boolean islogin=BeikBankApplication.isLogin();
		 String newshou=cm.dpd.data.userLevel;
		 List<DingqiP2> list2=cm.list2;
		 List<DingqiP2> list3=new ArrayList<DingqiP2>();
		 for(DingqiP2 dp2:list2)
		 {   
			 double money=Double.parseDouble(dp2.remainAmount);
			 double time=Double.parseDouble(dp2.countdown);
			 
			 if(money>0&&time<=0&&dp2.status.equals(FinalIndex.dingqi1))
			 {   
				 if(dp2.termbondType.equals("1"))
				 {
					 if(islogin&&!newshou.equals("0"))
					 {
						 continue;
					 }
				 }
				 list3.add(dp2);
			 }
		 }
		 //没有定期返回活期
		 if(list3.size()==0)
		 {
			 isHasNew=false;
			 return;
		 }
		
		 
		 
		 
		 
		
		 //是新手并且有新手标
		 if(!islogin||newshou.equals("0"))
		 {
			 for(DingqiP2 dp2:list3)
			 {
				if(dp2.termbondType.equals("1"))
				{
					this.dp2=dp2;
					isHasNew=true;
					return;
				}
			 }
			 
			 
			 
			 for(DingqiP2 dp2:list3)
			 {
				 if(dp2.termbondType.equals("1"))
				 {
					 continue;
				 }
				 if(this.dp2==null)
				 {
					 this.dp2=dp2;
					 isHasNew=true;
				 }
				 else
				 {
					 String day=this.dp2.termbondPeriod;
					 String day2=dp2.termbondPeriod;
					 int iday1=Integer.parseInt(day);
					 int iday2=Integer.parseInt(day2);
					 if(iday2>iday1)
					 {
						 this.dp2=dp2;
						 isHasNew=true;
					 }
				 }
			 }
			 
		 }
		 else
		 {   
			 for(DingqiP2 dp2:list3)
			 {
				 if(dp2.termbondType.equals("1"))
				 {
					 continue;
				 }
				 if(this.dp2==null)
				 {
					 this.dp2=dp2;
					 isHasNew=true;
				 }
				 else
				 {
					 String day=this.dp2.termbondPeriod;
					 String day2=dp2.termbondPeriod;
					 int iday1=Integer.parseInt(day);
					 int iday2=Integer.parseInt(day2);
					 if(iday2>iday1)
					 {
						 this.dp2=dp2;
						 isHasNew=true;
					 }
				 }
			 }
			 
			 
		 }
		 
		 
	}
	/**
	 * 设置推荐标的
	 */
	private void setTuiJian()
	{   
		
		
		TextView  tv_title=(TextView) view.findViewById(R.id.tv_title);
		TextView  tv_shouyi=(TextView) view.findViewById(R.id.tv_shouyi);
		
		LinearLayout ll_huoqi=(LinearLayout) view.findViewById(R.id.ll_huoqi);
		LinearLayout ll_dingqi=(LinearLayout) view.findViewById(R.id.ll_dingqi);
		if(isHasNew)
		{   
//			ll_dingqi.setVisibility(view.VISIBLE);
//			ll_huoqi.setVisibility(view.GONE);
			
			TextView  tv_tianshu=(TextView) view.findViewById(R.id.tv_tianshu);
			TextView  tv_kegou=(TextView) view.findViewById(R.id.tv_kegou);
			TextView  tv_qigou=(TextView) view.findViewById(R.id.tv_qigou);
			TextView  tv_kegou_text=(TextView) view.findViewById(R.id.tv_kegou_text);
			tv_kegou_text.setText("剩余可购");
			
			tv_title.setText(dp2.termbondName);
			String s1=NumberManager.getString(dp2.yearRate,"100",2);
			tv_shouyi.setText(s1);
			tv_tianshu.setText(dp2.termbondPeriod+"天");
			tv_kegou.setText(dp2.remainAmount+"元");
			tv_qigou.setText("1元");
			
			String s2=NumberManager.getString(dp2.extraRate,"100",2);
			 double dou=Double.parseDouble(dp2.extraRate);
			 
			 if(dou<=0)
			   {
				  tv_jiaxi.setVisibility(View.INVISIBLE);
			   }
			   else
			   {
				   tv_jiaxi.setVisibility(View.VISIBLE);
			   }
			   tv_jiaxi.setText("+"+s2+"%");
		}
		//购买活期
		else
		{   
			
			TextView  tv_tianshu=(TextView) view.findViewById(R.id.tv_tianshu);
			TextView  tv_kegou=(TextView) view.findViewById(R.id.tv_kegou);
			TextView  tv_kegou_text=(TextView) view.findViewById(R.id.tv_kegou_text);
			TextView  tv_qigou=(TextView) view.findViewById(R.id.tv_qigou);
			
//			TextView  tv_leijishouyi=(TextView) view.findViewById(R.id.tv_leijishouyi);
//			TextView  tv_leijitouzhi=(TextView) view.findViewById(R.id.tv_leijitouzhi);
//			TextView  tv_yitourenshu=(TextView) view.findViewById(R.id.tv_yitourenhsu);
			
			
			
//			ll_dingqi.setVisibility(view.GONE);
//			ll_huoqi.setVisibility(view.VISIBLE);
			tv_title.setText(cm.fundInfo.name);
			String s1=NumberManager.getString(cm.fundInfo.rate,"100",2);
			tv_shouyi.setText(s1);
			
			
			tv_tianshu.setText("活期");
			tv_kegou.setText("10000元");
			tv_qigou.setText("1元");
			tv_kegou_text.setText("购买上限");
			//String s=NumberManager.getString(cm.fundInfo.totalIncome,"1",0);
			//tv_leijishouyi.setText(NumberManager.getGeshiHua(s, 0));
			
//			String s0=NumberManager.getString(cm.fundInfo.totalInvestment,"1",0);
//			tv_leijitouzhi.setText(NumberManager.getGeshiHua(s0, 0));
//			
//			tv_yitourenshu.setText(cm.fundInfo.totalInvestors);
	
			
			
		}
	}
	
	/**
	 * 加载公告
	 */
	public void addNotice()
	{   
		
		NoticeManager nm=new NoticeManager(act, icb5);
		nm.start();
	}
	
	private ICallBack icb5=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj==null)
			{
				ll_notice.setVisibility(View.GONE);
				return;
			}
			Notice_data nd=(Notice_data) obj;
			Notice n=nd.data;
			if(n.content==null||"".equals(n.content))
			{
				ll_notice.setVisibility(View.GONE);
			}
			else
			{
				ll_notice.setVisibility(View.VISIBLE);
				tv_notice.setText(n.content);
				tv_notice.setFocusable(true);
				tv_notice.setFocusableInTouchMode(true);
				handler.postDelayed(run,1000);
			}
		}
	};
	Runnable run=new Runnable() {
		
		@Override
		public void run() {
			tv_notice.requestFocus();
			handler.removeCallbacks(run);
		}
	};
	Handler handler=new Handler();
//    public void start()
//    {
//    	iml.start();
//    }
//    public  void  stop()
//    {
//    	iml.stop();
//    }
  
	@Override
	public void onClick(View v) {
		Intent intent=null;
		if(cm==null||cm.fundInfo==null||cm.dpd==null)
		   {
			   return;
		   }
		switch (v.getId()) {
		
		case R.id.iv_xiaoxi:
			//Intent intent=new Intent(act,XiaoXiActivity.class);
			//act.startActivity(intent);
	        intent=new Intent(act,MessageActivity.class);
			act.startActivity(intent);
			break;
		case R.id.ll_dingqi:
			   if(dp2==null||cm.dpd==null)
			   {
				   return;
			   }
			   intent=new Intent(act,DingqiDetailActivity.class);
			  
			   intent.putExtra(TypeUtil.dingdi_data,dp2);
			   intent.putExtra("index3",cm.dpd.data.userLevel);
			   act.startActivity(intent);
			break;
        case R.id.ll_huoqi:
        	if(cm.fundInfo==null)
			   {
				   return;
			   }
        	  intent=new Intent();
			  intent.putExtra(HuoqiDetailActivity.index,cm.fundInfo);
			  intent.setClass(act,HuoqiDetailActivity.class);
			  act.startActivity(intent);
			break;
        case R.id.ll_gotong:
        	
        	  if(ll_huoqi.getVisibility()==View.VISIBLE)
        	  {
        		  intent=new Intent();
    			  intent.putExtra(HuoqiDetailActivity.index,cm.fundInfo);
    			  intent.setClass(act,HuoqiDetailActivity.class);
    			  act.startActivity(intent);
        	  }
        	  else
        	  {
        	   if(cm!=null&&cm.dpd!=null)
        	   {
        		   
        	   
        		  intent=new Intent(act,DingqiDetailActivity.class);
    			  
   			      intent.putExtra(TypeUtil.dingdi_data,dp2);
   			      intent.putExtra("index3",cm.dpd.data.userLevel);
   			      act.startActivity(intent);
        	   }
        	  }
      	     
			break;
		case R.id.bu_goumai:
			 
			if(isHasNew)
			{
				GoumaiManager.goumaiDingQi(act,dp2,cm.dpd.data.userLevel);
			}
			else
			{
				GoumaiManager.goumaiHuoQi(act,cm.fundInfo);
			}
			break;

		default:
			break;
		}
		
	}
    
}
