package com.beikbank.android.widget3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.activity.ChanPinActivityV2;
import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.DingqiGoumaiActivity;
import com.beikbank.android.activity.GengDuoActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.HuoqiDetailActivity;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.MessageActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.LiuChenSelect;
import com.beikbank.android.activity.help.NoticeHlep;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.Notice;
import com.beikbank.android.data.Notice_data;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.GongGao;
import com.beikbank.android.data2.GongGao_data;
import com.beikbank.android.data2.JinXuan;
import com.beikbank.android.data2.Jinxuan_data;
import com.beikbank.android.data2.Jinxuan_data2;
import com.beikbank.android.data2.LunBo;
import com.beikbank.android.data2.LunBo_data;
import com.beikbank.android.data2.LunBo_data2;
import com.beikbank.android.data2.XiaoXiIs;
import com.beikbank.android.data2.XiaoXiIs_data;
import com.beikbank.android.data2.Xiaoxi;
import com.beikbank.android.data2.getQvKuai;
import com.beikbank.android.data2.getQvKuai_data;
import com.beikbank.android.dataparam2.JinXuanParam;
import com.beikbank.android.dataparam2.LunBoParam;
import com.beikbank.android.dataparam2.XiaoXiIsParam;
import com.beikbank.android.dataparam2.getGongGaoParam;
import com.beikbank.android.dataparam2.getQvKuaiParam;
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
import com.beikbank.android.widget2.SlideShowView;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
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
    private TextView iv_xiaoxi;
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
	LinearLayout ll_tuijian;
	
	private ImageView iv_huodong;
	private ImageView iv_yaoqing;
	private ImageView iv_anquan;
	private ImageView iv_gengduo;
	private ImageView iv_xiaoxi1;
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
    	View view1=LayoutInflater.from(act).inflate(R.layout.view_pageview1_2,ll,false);
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
    	iv_gengduo=(ImageView) view1.findViewById(R.id.iv_gengduo);
    	ll_tuijian=(LinearLayout) view1.findViewById(R.id.ll_tuijian);
    	ll_tuijian.setOnClickListener(this);
    	
//    	
//        bu_goumai=(Button) view1.findViewById(R.id.bu_goumai);
//        bu_goumai.setOnClickListener(this);
//        tv_jiaxi=(TextView) view1.findViewById(R.id.tv_jiaxi);
        iv_xiaoxi=(TextView) view.findViewById(R.id.iv_xiaoxi);
        iv_xiaoxi1=(ImageView) view.findViewById(R.id.iv_xiaoxi1);
        
        iv_xiaoxi.setOnClickListener(this);
//        tv1=(TextView) view.findViewById(R.id.tv1);
//        
//        tv1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent=new Intent(act,LoginRegActivity.class);
//				act.startActivity(intent);
//				
//			}
//		});
//      
        
        
        
        
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
//        ll_dingqi=(LinearLayout) view.findViewById(R.id.ll_dingqi);
//        ll_huoqi=(LinearLayout) view.findViewById(R.id.ll_huoqi);
//        ll_gotong=(LinearLayout) view.findViewById(R.id.ll_gotong);
//        ll_dingqi.setOnClickListener(this);
//        ll_huoqi.setOnClickListener(this);
//        ll_gotong.setOnClickListener(this);
        
        
        ll_msg_show=(LinearLayout) view.findViewById(R.id.ll_msg_show);
        bu_goumai=(Button) sv.findViewById(R.id.bu_goumai);
        bu_goumai.setOnClickListener(this);
        
	    prs.doPullRefreshing(false,0);
    }
    
   
    /**
     * 初始化登录有关的信息
     */
    public void init2()
    {   
    	//更多
    	LinearLayout ll_touxiang=(LinearLayout) view.findViewById(R.id.ll_touxiang);
    	//ImageView iv1=(ImageView) view.findViewById(R.id.iv_top1);
        ll_touxiang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//ShateUtil su=new ShateUtil(act);
				//su.share(act);
				Intent intent=new Intent(act,GengDuoActivity.class);
				act.startActivity(intent);
				
			}
		});
        boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//        if(do_success)
//        {
//        	iv1.setVisibility(View.VISIBLE);
//        	tv1.setVisibility(View.GONE);
//        }
//        else
//        {
//        	tv1.setVisibility(View.VISIBLE);
//        	iv1.setVisibility(View.GONE);
//        }
    }
    /**
     * 设置活动事件监听器
     */
    private void SetOn(View view,final List<getQvKuai> list)
    {
    	
    	LinearLayout ll_huodong=(LinearLayout) view.findViewById(R.id.ll_huodong);
    	LinearLayout ll_yaoqing=(LinearLayout) view.findViewById(R.id.ll_yaoqin);
    	LinearLayout ll_anquan=(LinearLayout) view.findViewById(R.id.ll_anquan);
    	LinearLayout ll_gengduo=(LinearLayout) view.findViewById(R.id.ll_gengduo);
    	
    	ll_gengduo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getQvKuai jx=list.get(3);
				Intent intent=new Intent(act,HuodongActivity2.class);
				intent.putExtra("title",jx.areatitle);
				intent.putExtra("url",jx.arealink);
				act.startActivity(intent);
			}
		});
    	ll_huodong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getQvKuai jx=list.get(0);
				
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
				
				getQvKuai jx=list.get(1);
				
				Intent intent=new Intent(act,HuodongActivity2.class);
				intent.putExtra("title",jx.areatitle);
				intent.putExtra("url",jx.arealink);
				act.startActivity(intent);
			}
		});
    	ll_anquan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getQvKuai jx=list.get(2);
				
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
    	//cm=new ChanpinManager(act, icb3);
    	//cm.start();
    	
    	
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
    	lbp.userid=BeikBankApplication.getUserid();
    	ManagerParam mp=new ManagerParam();
    	mp.isShowDialog=false;
    	TongYongManager2 tym=new TongYongManager2(act, icb4,lbp,mp);
    	tym.start();
    	
    	
    	
    	
//    	ICallBack icb2=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				
//				if(obj!=null)
//				{
//					Jinxuan_data jd=(Jinxuan_data) obj;
//					Jinxuan_data2 jd2=jd.body;
//					ArrayList<JinXuan> list=jd2.arealist;
//					setArea(list);
//					SetOn(view, list);
//				}
//			}
//		};
//    	JinXuanParam jxp=new JinXuanParam();
//    	jxp.userid=BeikBankApplication.getUserid();
//    	jxp.req_time="1";
//    	TongYongManager2 tym2=new TongYongManager2(act, icb2,jxp,mp);
//    	tym2.start();
    	ICallBack icb_gqp=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					getQvKuai_data gd=(getQvKuai_data) obj;
					if("0000".equals(gd.header.re_code))
					{
						ArrayList<getQvKuai>  list=gd.body.arealist;
						setArea(list);
						SetOn(view, list);
					}
				}
			}
		};
    	getQvKuaiParam gqp=new getQvKuaiParam();
    	TongYongManager2 tym2=new TongYongManager2(act, icb_gqp,gqp);
    	tym2.start();
    	
    	
    	
    	String phone=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
		if(phone==null||"".equals(phone))
		{
			iv_xiaoxi1.setVisibility(view.VISIBLE);
		}
		else
		{
			
		
    	
    	ICallBack icb_xiaoxi=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					XiaoXiIs_data xd=(XiaoXiIs_data) obj;
					XiaoXiIs xi=xd.body;
					double d=Double.parseDouble(xi.count);
					if(d>0)
					{
						iv_xiaoxi1.setVisibility(view.VISIBLE);
					}
				}
				
			}
		};
    	
    	//加载消息
    	XiaoXiIsParam xp=new XiaoXiIsParam();
    	xp.phone_number=BeikBankApplication.getPhoneNumber();
    	 TongYongManager2 tym3=new TongYongManager2(act, icb_xiaoxi,xp);
    	 tym3.start();
		}
    }
    
    private void setArea(ArrayList<getQvKuai> list)
    {
    	
    	
    	TextView tv_huodong=(TextView) findViewById(R.id.tv_huodong);
		TextView tv_huodong_text=(TextView) findViewById(R.id.tv_huodong_text);
		TextView tv_yaoqing=(TextView) findViewById(R.id.tv_yaoqing);
		TextView tv_yaoqing_text=(TextView) findViewById(R.id.tv_yaoqing_text);
		TextView tv_anquan=(TextView) findViewById(R.id.tv_anquan);
		TextView tv_anquan_text=(TextView) findViewById(R.id.tv_anquan_text);
		TextView tv_gengduo=(TextView) findViewById(R.id.tv_gengduo);
		TextView tv_gengduo_text=(TextView) findViewById(R.id.tv_gengduo_text);
		getQvKuai jxh=list.get(0);
		tv_huodong.setText(jxh.areatitle);
		tv_huodong_text.setText(jxh.areacontext);
		
		
		 ImageUrl iu=new ImageUrl(iv_huodong,jxh.arealogourl);
		 iu.start();
		
		jxh=list.get(1);
		tv_yaoqing.setText(jxh.areatitle);
		tv_yaoqing_text.setText(jxh.areacontext);
		ImageUrl iu2=new ImageUrl(iv_yaoqing,jxh.arealogourl);
		 iu2.start();
		
		
		jxh=list.get(2);
		tv_anquan.setText(jxh.areatitle);
		tv_anquan_text.setText(jxh.areacontext);
		ImageUrl iu3=new ImageUrl(iv_anquan,jxh.arealogourl);
		 iu3.start();
		
		
		 jxh=list.get(3);
			tv_gengduo.setText(jxh.areatitle);
			tv_gengduo_text.setText(jxh.areacontext);
			ImageUrl iu4=new ImageUrl(iv_gengduo,jxh.arealogourl);
			 iu4.start();
		
		
    	
    	
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
	       
	      // setTuiJian();
	       
	       
	       
	       
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
	private GetChanPin gcp;
	/**
	 * 设置推荐标的
	 */
	public void setTuiJian(GetChanPin gcp)
	{   
		if(gcp==null)
		{
			return;
		}
		this.gcp=gcp;
		TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
		TextView tv_nianhua=(TextView) view.findViewById(R.id.tv_nianhua);
		TextView tv_tianshu=(TextView) view.findViewById(R.id.tv_tianshu);
	    TextView tv_qigou=(TextView) view.findViewById(R.id.tv_qigou);
	    TextView tv_shengyu=(TextView) view.findViewById(R.id.tv_shengyu);	
	    TextView tv_jiaxi=(TextView) view.findViewById(R.id.tv_jiaxi);	
	    
	    Button bu_goumai=(Button) view.findViewById(R.id.bu_goumai);
	    if(gcp!=null)
	    {
	    	bu_goumai.setEnabled(true);
	    }
			tv_name.setText(gcp.product_name);
			String s=NumberManager.getString(gcp.benchmark_return_rate,"100",2);
			
			
			   SpannableStringBuilder sb = new SpannableStringBuilder(s+"%"); // 包装字体内容  
		        //ForegroundColorSpan fcs = new ForegroundColorSpan(0xff333333); // 设置字体颜色  
		       // StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // 设置字体样式  
		        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(DensityUtil.sp2px(act, 16));  // 设置字体大小  
		        //sb.setSpan(fcs, 2, gdq.current_principal_balance.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
		        //sb.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
		        sb.setSpan(ass,0,s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
			
			
			
			tv_nianhua.setText(sb);
			
			String s1=NumberManager.getString(gcp.increase_interest_return_rate,"100",2);
			tv_jiaxi.setText("+"+NumberManager.getGeshiHua(s1, 2)+"%");
			double jiaxi=Double.parseDouble(s1);
			if(jiaxi<=0)
			{
				tv_jiaxi.setVisibility(view.GONE);
			}
			
			tv_tianshu.setText(gcp.term+"天");
			tv_qigou.setText(gcp.purchase_amount+"元");
			tv_shengyu.setText("剩余可购"+NumberManager.getGeshiHua(gcp.pro_share, 2)+"元");
			
			if("4".equals(gcp.product_type_pid))
			{
				tv_tianshu.setText("灵活存取");
			}
	}
	
	/**
	 * 加载公告
	 */
	private void addNotice()
	{   
		
//		NoticeManager nm=new NoticeManager(act, icb5);
//		nm.start();
		getGongGaoParam gg=new getGongGaoParam();
		 ManagerParam mp=new ManagerParam();
		  mp.isShowDialog=false;
		  mp.isShowMsg=false;
		TongYongManager2 tym2=new TongYongManager2(act, icb5,gg,mp);
		tym2.start();
	}
	
	private ICallBack icb5=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj==null)
			{
				ll_notice.setVisibility(View.GONE);
				return;
			}
			GongGao_data gd=(GongGao_data) obj;
			GongGao gg=gd.body;
			if(gg.content==null||"".equals(gg.content))
			{
				ll_notice.setVisibility(View.GONE);
			}
			else
			{
				ll_notice.setVisibility(View.VISIBLE);
				tv_notice.setText(gg.content);
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
  
	ICallBack icb_lc=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			Intent intent;
			if("0".equals(gcp.term))
			{
//				 intent=new Intent(act,PurchaseActivity.class);
//				 intent.putExtra("gcp",gcp);
//				 act.startActivity(intent);
				LiuChenSelect ls=new LiuChenSelect();
				ls.startNext(act,2,gcp);
			}
			else
			{
//				 intent=new Intent(act,DingqiGoumaiActivity.class);
//				 intent.putExtra("gcp",gcp);
//				 act.startActivity(intent);
				LiuChenSelect ls=new LiuChenSelect();
				ls.startNext(act,3,gcp);
			}
			
		}
	};
	@Override
	public void onClick(View v) {
		Intent intent=null;
	
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
			
//			if(gcp!=null)
//			{   
//				LiuChenManager.selectPay(icb_lc, act,false);
//				
//			}
			LiuChenSelect ls=new LiuChenSelect();
			ls.startNext(act, 2,gcp);
			break;
		case R.id.ll_tuijian:
			 intent=new Intent(act,ChanPinActivityV2.class);
			 intent.putExtra("index1",gcp);
			 act.startActivity(intent);

			break;
		default:
			break;
		}
		
	}
    
}
