package com.beikbank.android.widget3;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

import com.beikbank.android.activity.BandTestActivity;
import com.beikbank.android.activity.DingqiLicaiActivity;
import com.beikbank.android.activity.GengDuoActivity;
import com.beikbank.android.activity.GerenActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.LinHuoBaoActivity;
import com.beikbank.android.activity.MessageActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.activity.QianbaoActivity1;
import com.beikbank.android.activity.QianbaoActivity2;
import com.beikbank.android.activity.QianbaoActivity3;
import com.beikbank.android.activity.QianbaoActivity8;
import com.beikbank.android.activity.TransactionListActivity2;
import com.beikbank.android.activity.UserRecordActivity;
import com.beikbank.android.activity.UserRecordActivity2;
import com.beikbank.android.activity.YouHuiQuanActivity;
import com.beikbank.android.activity.ZhiChanActivity2;
import com.beikbank.android.activity.ZhicanFenbuActivity;
import com.beikbank.android.activity.ZuoriShouyiActivity;
import com.beikbank.android.activity.help.ActivitySwitchHelp;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.LiuChenSelect;
import com.beikbank.android.conmon.CacheIndex;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data.UserCapital2;
import com.beikbank.android.data.UserCapital2_data;
import com.beikbank.android.data.UserCapitalInfo;
import com.beikbank.android.data.UserCapitalInfo2;
import com.beikbank.android.data.UserCapitalInfo_data;
import com.beikbank.android.data.Yuer;
import com.beikbank.android.data.Yuer_data;
import com.beikbank.android.data.type.ZhiChan;
import com.beikbank.android.data2.GetUserZhiChan2;
import com.beikbank.android.data2.GetUserZhiChan2_data;
import com.beikbank.android.data2.GetUserZhiChan_data;
import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.data2.getAllYouHuiQuan_data;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.data2.getYouHuiQuan;
import com.beikbank.android.data2.getYouHuiQuan_data;
import com.beikbank.android.data2.getZuoRiShouYi_data;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.dataparam2.getAllYouhuiQuanParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.dataparam2.getUserZhiChanParam;
import com.beikbank.android.dataparam2.getUserZhiChanParam2;
import com.beikbank.android.dataparam2.getYouhuiQuanParam;
import com.beikbank.android.dataparam2.getZuoRiShouYiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.net.impl.UserCapital2Manager;
import com.beikbank.android.net.impl.UserCapitalInfoManager;
import com.beikbank.android.net.impl2.ZhiCanManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.text.TextUtil;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil2_2_v2;
import com.beikbank.android.utils.hongbao.HongbaoUtil_v2;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/***
 * 
 * @author Administrator
 *我的资产
 */
public class PageView3 extends LinearLayout implements OnClickListener {
    Activity act;
    private PullToRefreshScrollView prs;
    private ScrollView sv;
    /**
     * 昨日收益
     */
    private TextView tv1;
    /**
     * 活期累计收益
     */
    private TextView tv2;
    /**
     * 定期累计收益
     */
    private TextView tv3;
    /**
     * 钱包余额
     */
    private TextView tv4;
    /**
     * 我 的资产
     */
    private TextView tv5;
    /**
     * 我 的红包
     */
    private TextView tv6;
    /**
     * 手机号
     */
    private TextView tv7;
    private ImageView iv1;
    private ImageView iv2;
    
    private ImageView shouyi;
    private LinearLayout ll_jiaoyi;
    private LinearLayout ll_zhichan;
    private LinearLayout ll_hongbao;
    private LinearLayout ll_huoqishouyi;
    private LinearLayout ll_qianbao;
    private TextView tv_chongzhi;
    private TextView tv_tixian;
	public PageView3(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	public PageView3(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}
	/**
	 * 初始化,该方法必须调用
	 */
    private void init(Context context)
    {   
        act=(Activity) context;
    	//LinearLayout ll=new LinearLayout(act);
        LinearLayout ll=new LinearLayout(context);
       
		View view0 =LayoutInflater.from(context).inflate(R.layout.page_wealth11,ll,false);
    	View view=LayoutInflater.from(context).inflate(R.layout.view_pageview2_3,ll,false); 
    	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    	
    	view.setLayoutParams(lp);
    	view0.setLayoutParams(lp);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
    		
    	{
    	  LinearLayout ll_top=(LinearLayout) view.findViewById(R.id.ll_top);
    	  int h=StateBarColor.getStatusBarHeight(act);
    	  ll_top.setPadding(0,h,0,0);
    	}
    	prs=(PullToRefreshScrollView) view0.findViewById(R.id.prs);
		prs.setPullLoadEnabled(false);
		prs.setScrollLoadEnabled(false);
		prs.getRefreshableView();
		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
		{

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				    //prs.onPullDownRefreshComplete();
				addData();
				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				
			}
		});
		prs.setBackgroundResource(R.drawable.img_zhican_beijin);
		prs.setTextColor(0xffffffff);
		
		DensityUtil du=new DensityUtil(act);
		LayoutParams lp3=new LayoutParams(LayoutParams.MATCH_PARENT,du.dip2px(60));
		//lp3.topMargin=0;
		//prs.mHeaderLayout.setLayoutParams(lp3);
		RelativeLayout rl=(RelativeLayout) prs.mHeaderLayout.findViewById(R.id.head_contentLayout);
		LinearLayout ll3=(LinearLayout) rl.findViewById(R.id.ll_head);
		ll3.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
		android.widget.RelativeLayout.LayoutParams lp4=new android.widget.RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp4.addRule(RelativeLayout.CENTER_IN_PARENT);
		ll3.setLayoutParams(lp4);
		rl.setLayoutParams(lp3);
		
		
		
		
		
		sv=prs.getRefreshableView();
		sv.addView(view);
		sv.setFillViewport(true);
		
		addView(view0);
		
		
		prs.doPullRefreshing(false,0);
    	
    	//View view=act.getLayoutInflater().inflate(R.layout.view_pageview1,ll,false);
    	//addView(view);
		initView(view);
		
		
		//加载缓存
//	    String c1=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache1);
//	    String c2=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache2);
//	    String c3=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache3);
//	    String c4=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache4);
//	    String c5=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache5);
//	    tv1.setText(c1);
//	    tv5.setText(c5);
//	    tv3.setText(c3);
//	    tv2.setText(c2);
//	    tv4.setText(c4);
    }
    private void initView(View view)
    {
    	tv1=(TextView)view.findViewById(R.id.tv1);
    	tv2=(TextView)view.findViewById(R.id.tv2);
    	tv3=(TextView)view.findViewById(R.id.tv3);
    	tv4=(TextView)view.findViewById(R.id.tv4);
    	tv5=(TextView)view.findViewById(R.id.tv5);
    	tv6=(TextView)view.findViewById(R.id.tv6);
    	tv7=(TextView)view.findViewById(R.id.tv7);
    	//tv7.setOnClickListener(this);
    	
    	iv1=(ImageView) view.findViewById(R.id.iv1);
    	iv2=(ImageView) view.findViewById(R.id.iv2);
    	
    	shouyi=(ImageView) view.findViewById(R.id.shouyi);
    	shouyi.setOnClickListener(this);
    	
    	iv1.setOnClickListener(this);
    	
    	
        SpannableStringBuilder builder = new SpannableStringBuilder(0+"张可用");
		  
		ForegroundColorSpan f2 = new ForegroundColorSpan(0xffe4393c);
		builder.setSpan(f2, 0,"0".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv6.setText(builder);
//    	iv1.setOnClickListener(this);
//    	iv2.setOnClickListener(this);
//    	shouyi.setOnClickListener(this);
//    	boolean islogin=BeikBankApplication.isLogin();
//    	if(islogin)
//    	{   
//    		String phone=Utils.getEncryptedPhonenumber(BeikBankApplication.getPhoneNumber());
//    		tv7.setText(phone);
//    	}
    	
    	
    	
    	
    	
    	
    	//LinearLayout ll_dingqilicia=(LinearLayout) findViewById(R.id.ll_dingqilicai);
    	//ll_dingqilicia.setOnClickListener(this);
    	
    	
    	LinearLayout ll_zongzhican=(LinearLayout) findViewById(R.id.ll_zongzhican);
    	ll_zongzhican.setOnClickListener(this);
    	
    	LinearLayout ll_zuorishouyi=(LinearLayout) findViewById(R.id.ll_zuorishouyi);
    	ll_zuorishouyi.setOnClickListener(this);
    	
    	LinearLayout ll_qianbao=(LinearLayout) findViewById(R.id.ll_qianbao);
    	ll_qianbao.setOnClickListener(this);
    	
    	
    	TextView tv_chongzhi=(TextView) view.findViewById(R.id.tv_chongzi);
    	TextView tv_tixian=(TextView) view.findViewById(R.id.tv_tixian);
    	tv_chongzhi.setOnClickListener(this);
    	tv_tixian.setOnClickListener(this);
    	ll_jiaoyi=(LinearLayout) findViewById(R.id.ll_jiaoyi);
    	ll_jiaoyi.setOnClickListener(this);
//    	tv_chongzhi=(TextView) findViewById(R.id.tv_chongzhi);
//    	tv_tixian=(TextView) findViewById(R.id.tv_tixian);
//    	ll_dingqishouyi=(LinearLayout) findViewById(R.id.ll_dingqishouyi);
//    	ll_huoqishouyi=(LinearLayout) findViewById(R.id.ll_huoqishouyi);
//    	ll_zhichan=(LinearLayout) findViewById(R.id.ll_zhichan);
    	ll_hongbao=(LinearLayout) findViewById(R.id.ll_hongbao);
//    	
//    	tv_chongzhi.setOnClickListener(this);
//    	tv_tixian.setOnClickListener(this);
//    	ll_dingqishouyi.setOnClickListener(this);
//    	ll_huoqishouyi.setOnClickListener(this);
//    	ll_zhichan.setOnClickListener(this);
    	ll_hongbao.setOnClickListener(this);
    	
    }
    /**
     * 初始化登录有关的数据
     */
    public void init2()
    {
    	boolean islogin=BeikBankApplication.isLogin();
    	if(islogin)
    	{   
    		String phone=Utils.getEncryptedPhonenumber(BeikBankApplication.getPhoneNumber());
    		//tv7.setText(phone);
    	}
    	addData();
    }
//    /**
//     * 总资产接口调用完成计数,等于3时3个接口全部完成
//     */
//    int money_count;
//    /**
//     * 总资产的值
//     */
//    String money="0";
//    Yuer ye;
//	private boolean isaddData=true;
//	private boolean intozc=false;
    GetUserZhiChan2_data g2d;
    GetUserZhiChan_data gd;
    getQianBao gqb;
    getQianBao_data gb;
    /**
	 * 加载数据
	 */
	public void addData()
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				prs.onPullDownRefreshComplete();
				if(obj!=null)
				{  
					// getQianBao_data gd=(getQianBao_data) obj;
					
					
					
					
					gb=(getQianBao_data) obj;
					
//					
					 getQianBao gb1=gb.body.card;
					 BeikBankApplication.setSharePref(BeikBankConstant.qianbao,gb1.acc_amount);
					 BeikBankApplication.setSharePref(BeikBankConstant.bank,gb1.acc_number);
					 BeikBankApplication.setSharePref(BeikBankConstant.bank_name,gb1.bank_name);
					 BeikBankApplication.setSharePref(BeikBankConstant.bank_max_amount,gb1.max_amount);
					 BeikBankApplication.setSharePref(BeikBankConstant.bank_min_amount,gb1.min_amount);
					 BeikBankApplication.setSharePref(BeikBankConstant.zhanghao,gb1.acc_id);
					 BeikBankApplication.setSharePref(BeikBankConstant.icon_url,gb1.icon_url);
					 BeikBankApplication.setSharePref(BeikBankConstant.logo_url,gb1.logo_url);
//					
					if("0000".equals(gb.header.re_code))
					{
						 gqb=gb.body.card;
						yingcang();
					}
					
				}
				
			}
		};

		
		String code=BeikBankApplication.getUserCode();
		if(code==null||"".equals(code))
		{
			return;
		}
		
		getQianBaoParam gqp=new getQianBaoParam();
		gqp.acc_type_id="1";
	    gqp.user_code=BeikBankApplication.getUserCode();
	    ManagerParam mp=new ManagerParam();
	    mp.isShowDialog=false;
	    
		TongYongManager2 tym2=new TongYongManager2(act, icb, gqp,mp);
		tym2.start();
		
		
		
		
		ICallBack icb3=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					TextView tv_zuori=(TextView) sv.findViewById(R.id.tv_zuori);
					TextView tv_zhichan=(TextView) sv.findViewById(R.id.tv_zhichan);
					gd=(GetUserZhiChan_data) obj;
					
//					tv_zuori.setText(NumberManager.getGeshiHua(gd.body.intrestYesterday,4));
//					tv_zhichan.setText(NumberManager.getGeshiHua(gd.body.assetsCurrentTotal,2));
//				    
//					tv_zuori.setTag(NumberManager.getGeshiHua(gd.body.intrestYesterday,4));
//					tv_zhichan.setTag(NumberManager.getGeshiHua(gd.body.assetsCurrentTotal,2));
					yingcang();
					
				}
				
			}
		};
		
		getUserZhiChanParam gp=new getUserZhiChanParam();
		gp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym3=new TongYongManager2(act, icb3, gp,mp);
		tym3.start();
//得到产品小类金额
		  
		 ICallBack icb_guc=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					g2d=(GetUserZhiChan2_data) obj;
					if("0000".equals(g2d.header.re_code))
					{   
						
						addChanPin(g2d.body);
					}
				}
			}
		};
		getUserZhiChanParam2 guc=new getUserZhiChanParam2();
		guc.user_id=BeikBankApplication.getUserCode();
		 
//		
//		  getZuoRiShouYiParam gzr=new getZuoRiShouYiParam();
//		  gzr.page_index="1";
//		  gzr.page_size="10";
//		  gzr.user_id=BeikBankApplication.getUserCode();
		  TongYongManager2 tym4=new TongYongManager2(act,icb_guc,guc,mp);
		  tym4.start();
		
//		if(isaddData)
//		{
//			isaddData=false;
//		money_count=0;
//		money="0";
//		
//		TotalMoneyParam tmp=new TotalMoneyParam();
//	    tmp.memberID=BeikBankApplication.getUserid();
//	    if(tmp.memberID==null||"".equals(tmp.memberID))
//	    {
//	    	return;
//	    }
//	    
//	    
//	    intozc=false;
//		UserCapital2Manager usm=new UserCapital2Manager(act,icb4,tmp);
//		usm.start();
//		
//		YuerParam yp=new YuerParam();
//		yp.memberID=BeikBankApplication.getUserid();
//		TongYongManager tym=new TongYongManager(act, icb5,yp);
//		tym.start();
//		
//		
//		TotalMoneyParam tp=new TotalMoneyParam();
//    	tp.memberID=BeikBankApplication.getUserid();
//    	UserCapitalInfoManager ucm=new UserCapitalInfoManager(act, icb6, tp);
//    	ucm.start();
//		}
		 String id=BeikBankApplication.getUserid();
		    if(id==null||id.equals(""))
		    {   
		    	
		    	return;
		    }
//		zc=new ZhiCanManager(act, icb1);
//		zc.start();
//		//HomeActivity3.ha.zc=zc;
//		
//		
//		//得到几张可用
//		HongbaoParam hp=new HongbaoParam();
//		hp.userId=BeikBankApplication.getUserid();
//		if(hp!=null&&hp.userId!=null&&!hp.userId.equals(""))
//		{
//		ManagerParam mp=new ManagerParam();
//		mp.isShowDialog=false;
//		mp.isShowMsg=false;
//		TongYongManager tym=new TongYongManager(act, icb9,hp,mp);
//		tym.start();
//		}
		    
		    
	  //得到红包个数
		    ICallBack icb_ghb=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{
						getAllYouHuiQuan_data gd=(getAllYouHuiQuan_data) obj;
						ArrayList<getAllYouHuiQuan> list=gd.body;
						  
						    SpannableStringBuilder builder = new SpannableStringBuilder(list.size()+"张可用");
							  String size=list.size()+"";
							ForegroundColorSpan f2 = new ForegroundColorSpan(0xffe4393c);
							builder.setSpan(f2, 0,size.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							tv6.setText(builder);
					}
					
				}
			};
			getYouhuiQuanParam gap=new getYouhuiQuanParam();
			gap.user_code=BeikBankApplication.getUserCode();
			TongYongManager2 tym=new TongYongManager2(act, icb_ghb,gap);
			tym.start();
	}
	ArrayList<View> listView;
	/**
	 * 添加活期 定期产品
	 * @param list
	 */
	private void addChanPin(ArrayList<GetUserZhiChan2> list)
	{      
		   LinearLayout ll_parent=(LinearLayout) sv.findViewById(R.id.ll_parent); 
		   ll_parent.removeAllViews();
		   
		   LinearLayout ll=new LinearLayout(act);
		   listView=new ArrayList<View>();
		   View view=null;
		   LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
		   ImageView img;
		   TextView tv_name;
		   TextView tv_money;
		   View v;
		   LinearLayout ll0;
		   for(int i=0;i<list.size();i++)
		   {    
			    final GetUserZhiChan2 gz=list.get(i);
			    view=LayoutInflater.from(act).inflate(R.layout.view_pageview2_3_1_v2,ll,false);
			    ll0=(LinearLayout) view.findViewById(R.id.ll);
			    ll0.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						if("4".equals(gz.product_type_pid))
						{
							Intent intent=new Intent(act,LinHuoBaoActivity.class);
							intent.putExtra("name",gz.product_name);
							intent.putExtra("product_type_pid",gz.product_type_pid);
							intent.putExtra("gz",gz);
							act.startActivity(intent);
						}
						else
						{
							Intent intent=new Intent(act,DingqiLicaiActivity.class);
					    	intent.putExtra("id",gz.product_type_id);
					    	intent.putExtra("name",gz.product_name);
					    	intent.putExtra("product_type_pid",gz.product_type_pid);
							act.startActivity(intent);
						}
					}
				});
			 
			    img=(ImageView) view.findViewById(R.id.img);
			    tv_name=(TextView) view.findViewById(R.id.tv_name);
			    tv_money=(TextView) view.findViewById(R.id.tv_money2);
			    v=view.findViewById(R.id.v);
			    tv_name.setText(gz.product_name);
			    tv_money.setText(NumberManager.getGeshiHua(gz.currCapValue,2)+"元");
			    tv_money.setTag(NumberManager.getGeshiHua(gz.currCapValue,2)+"元");
			    listView.add(tv_money);
			    String s1=BeikBankApplication.getSharePref(BeikBankConstant.money_is_yincang);
			    if("1".equals(s1))
			    {
			    	tv_money.setText("****");
			    }
			    
			    
			    if("4".equals(gz.product_type_pid))
			    {
	                 img.setBackgroundResource(R.drawable.img_zhican_huo);
			    }
			    else if("3".equals(gz.product_type_pid))
			    {
			    	 img.setBackgroundResource(R.drawable.img_zhican_zhinengtou);
			    }
			    else if("2".equals(gz.product_type_pid))
			    {
			    	 img.setBackgroundResource(R.drawable.img_zhican_chanpinsanbiao);
			    }
			    else if("1".equals(gz.product_type_pid))
			    {
			    	 img.setBackgroundResource(R.drawable.img_zhican_sanbiao);
			    }
			    if(i==list.size()-1)
			    {   
			    	//DensityUtil du=new DensityUtil(act);
			    	 LinearLayout.LayoutParams paramTest = (LinearLayout.LayoutParams) v.getLayoutParams(); 
			    	 paramTest.leftMargin=0;
			    	 v.setLayoutParams(paramTest);
			    }
			    ll_parent.addView(view);
		   }
		
	}
	
	
	
	 /**
	  * 收益对话框
	  */
	 Dialog dialog1;
	 /**
	  * 显示提示对话框
	  */
	 private void showDialog()
	 {   
		 
		
		 
		 LinearLayout ll=new LinearLayout(act);
		   View v=LayoutInflater.from(act).inflate(
	  			   R.layout.redeem_dialog5,ll,false);
		   
	    	//标题
	    	TextView tv1=(TextView) v.findViewById(R.id.tv_tv1);
	    	//内容
	    	TextView tv2=(TextView) v.findViewById(R.id.tv_tv2);
	    	
	    	tv1.setText(act.getString(R.string.page6));
	    	tv2.setText(act.getString(R.string.page7));
	    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
	    	tv5.setText(act.getString(R.string.page8));
	    	tv5.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog1.dismiss();
				}
			});
	    	//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_finrst_bank,true);
	        dialog1=DialogManager.getDiaolg1(act, v);
	    	dialog1.show();
	 }
	
	/**
	 * 判断有没有可用红包回调
	 */
   private ICallBack icb9=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			//prs.onPullDownRefreshComplete();
			if(obj!=null)
			{   
				Hongbao_data hd=(Hongbao_data) obj;
				ArrayList<Hongbao> list=hd.data;
				String count=ZhiChanActivity2.select(list);
				//if(HongbaoUtil.compreS(count,"0")>0)
				//{
				    //tv6.setText("");
				    SpannableStringBuilder builder = new SpannableStringBuilder(count+"张可用");
					  
					ForegroundColorSpan f2 = new ForegroundColorSpan(0xffe4393c);
					builder.setSpan(f2, 0,count.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					tv6.setText(builder);
					
				//}
			}
		}
	};
	
	public UserCapitalInfo uci;
//	ICallBack icb6=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			if(obj!=null)
//			{
//				synchronized (act) {
//					
//				
//			  UserCapitalInfo_data ud=(UserCapitalInfo_data) obj;
//			  uci=ud.data;
//			  String s=getMoney(uci);
//			  money=NumberManager.getAddString(money,s,2);
//			  money_count++;
//			  if(money_count==3)
//			  {
//				  tv2.setText(NumberManager.getGeshiHua(money, 2));
//				  isaddData=true;
//				  //TextUtil.setSize(act,tv5,NumberManager.getGeshiHua(money, 2));
//				  tv5.setText(NumberManager.getGeshiHua(money, 2));
//			  }
//			 }
//			}
//		}
//	};
	/**
	 * 得到募集中的总钱数
	 * @return
	 */
	private String getMoney(UserCapitalInfo uci)
	{  
		String s="0";
		ArrayList<UserCapitalInfo2> list=uci.termbondUnconfirmedList;
		for(UserCapitalInfo2 uc:list)
		{
			s=NumberManager.getAddString(s,uc.amount, 4);
		}
		for(UserCapitalInfo2 uc:uci.termbondList)
		{
			s=NumberManager.getAddString(s,uc.amount, 4);
		}
		return s;
	}
	/**
	 * 用户资产信息
	 */
	public UserCapital2 uc;
//	public  ICallBack icb4=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			if(obj!=null)
//			{  
//				synchronized (act) 
//				{
//				UserCapital2_data ucd=(UserCapital2_data) obj;
//			    uc=ucd.data;
//			      money_count++;
//			      //String s1=NumberManager.getAddString(uc.bondAmount,uc.fundAmount,2);
//			      money=NumberManager.getAddString(money,uc.fundAmount,2);
//			      if(money_count==3)
//			      {
//			    	  tv5.setText(NumberManager.getGeshiHua(money, 2));
//			    	  //TextUtil.setSize(act,tv5,NumberManager.getGeshiHua(money, 2));
//			    	  isaddData=true;
//			      }
////				  String s1=NumberManager.getAddString(uc.bondAmount,uc.fundAmount,4);
////				  String s22=QianbaoActivity1.countTotal(ye);
////				  s1=NumberManager.getAddString(s1,s22,4);
////				 //总资产
////				  magictextview_totalcapital.setText(NumberManager.getGeshiHua(s1, 2));
//				 
//			   
//				
//				String s2=NumberManager.getAddString(uc.bondInterestY,uc.fundInterestY,4);
//				//BigDecimal bd_income_y=new BigDecimal(mi.interestY);
//				//bd_income_y=bd_income_y.setScale(4,BigDecimal.ROUND_UP);
//				//昨日收益
//				tv1.setText(s2);
//				
////				String s2=NumberManager.getString(mi.interestM,"1",2);
////				//BigDecimal bd_income_m=new BigDecimal(mi.interestM);
////				//bd_income_m=bd_income_m.setScale(2,BigDecimal.ROUND_UP);
////				textview_interestM.setText(s2);
//				//定期累计收益
//				String s3=NumberManager.getString(uc.bondInterestT,"1",2);
//				tv3.setText(s3);
//				//BigDecimal bd_income_w=new BigDecimal(mi.interestW);
//				//bd_income_w=bd_income_w.setScale(2,BigDecimal.ROUND_UP);
//				//textview_interestW.setText(NumberManager.getGeshiHua(s3,2));
//				//活期累计收益
//				String s4=NumberManager.getString(uc.fundInterestT,"1",2);
//				//BigDecimal bd_income_t=new BigDecimal(mi.interestT);
//				//bd_income_t=bd_income_t.setScale(2,BigDecimal.ROUND_UP);
//				tv2.setText(NumberManager.getGeshiHua(s4,2));
//				//firstLogin();
//				}
//				
//			}
//			if(intozc)
//			{
//			  //intoZhancan();
//			}
//		}
//	};
	
	
//ICallBack icb5=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			if(obj!=null)
//			{
//				Yuer_data yd=(Yuer_data) obj;
//				ye=yd.data;
//				synchronized (act) 
//				{
//					
//					
//					String s3=QianbaoActivity1.countTotal(ye);
//					tv4.setText(NumberManager.getGeshiHua(s3, 2));
//					money=NumberManager.getAddString(money,s3,2);	
//					money_count++;
//					if(money_count==3)
//					{
//						tv5.setText(NumberManager.getGeshiHua(money, 2));
//						isaddData=true;
//					}
//					
//				}
//			}
//		}
//	};
	/**
	 * 1 眼睛显示资产2隐藏资产
	 */
 int index=1;	
	
@Override
public void onClick(View v) {
	Intent intent=null;
	switch (v.getId()) 
	{
	
	case R.id.tv7:
		intent=new Intent(act,GerenActivity.class);
		
		ActivitySwitchHelp.start(act,intent,false);
		break;
	case R.id.iv1:
		intent=new Intent(act,GengDuoActivity.class);
		ActivitySwitchHelp.start(act,intent,false);
		break;
    case R.id.iv2:
    	intent=new Intent(act,MessageActivity.class);
		act.startActivity(intent);
		break;
//    case R.id.ll_linhuobao:
//    	intent=new Intent(act,LinHuoBaoActivity.class);
//		act.startActivity(intent);
//		break;
    case R.id.ll_zongzhican:
    	
    	
    	
    	
    	intent=new Intent(act,ZhicanFenbuActivity.class);
    	
    	
    	if(gd!=null&&gqb!=null&&g2d!=null)
    	{    
    		 ArrayList<GetUserZhiChan2> list0=g2d.body;
    		 ArrayList<GetUserZhiChan2> list1=new ArrayList<GetUserZhiChan2>();
    		 //ArrayList<ZhiChan> list=new ArrayList<ZhiChan>();
    		 //ZhiChan zc=new ZhiChan();
    		 //zc.money=gqb.acc_amount;
    		 //zc.name="钱包";
    		 //list.add(zc);
    		 GetUserZhiChan2 gz=new GetUserZhiChan2();
    		 gz.currCapValue=gqb.acc_amount;
    		 gz.product_name="钱包";
//    		 for(int i=0;i<list0.size();i++)
//    		 {
//    			 GetUserZhiChan2 gz2= list0.get(i);
//    			 zc=new ZhiChan();
//    			 zc.name=gz2.product_name;
//    			 zc.money=gz2.currCapValue;
//    			 list.add(zc);
//    			 
//    		 }
    		 list1.add(gz);
    		 list1.addAll(list0);
    		 intent.putExtra("list",list1);
    		 intent.putExtra("money",gd.body.assetsCurrentTotal);
    		 
    	}
		//act.startActivity(intent);
    	 LiuChenSelect ls=new LiuChenSelect();
		    ls.startNext(act,4,intent);
		break;
    case R.id.ll_zuorishouyi:
    	intent=new Intent(act,ZuoriShouyiActivity.class);
		act.startActivity(intent);
		break;
    case R.id.ll_qianbao:
    	intent=new Intent(act,QianbaoActivity8.class);
    	if(gqb!=null)
    	{
    	   intent.putExtra("money",gqb.acc_amount);
    	}
//		act.startActivity(intent);
		 ls=new LiuChenSelect();
		    ls.startNext(act,4,intent);
		break;		
//    case R.id.ll_dingqilicai:
//    	intent=new Intent(act,DingqiLicaiActivity.class);
//		act.startActivity(intent);
//		break;		
   case R.id.tv_chongzi:
	    //LiuChenManager.StartNext(act,icb8);
	  
	    ls=new LiuChenSelect();
	    ls.startNext(act,1,null);
	   
		break;
   case R.id.tv_tixian:
	    ls=new LiuChenSelect();
	    ls.startNext(act,0,null);
	  
		break;	
   case R.id.ll_dingqishouyi:
	    intent=new Intent(act,UserRecordActivity2.class);
	   // intent.putExtra("money",zc.uc.bondInterestT);
		act.startActivity(intent);
		break;	
   case R.id.ll_huoqishouyi:
	   intent=new Intent(act,UserRecordActivity.class);
	 //  intent.putExtra("money",zc.uc.fundInterestT);
	   intent.putExtra("index","1");
		act.startActivity(intent);
		break;
		
   case R.id.ll_hongbao:
	   intent=new Intent(act,YouHuiQuanActivity.class);
		act.startActivity(intent);
		break;	
   case R.id.ll_zhichan:
	  
	   intent=new Intent(act,ZhiChanActivity2.class);
	   //intent.putExtra("index0",uc);
	   //intent.putExtra("index",HomeActivity3.ha.fi);
	   //intent.putExtra("total_money",money);
	   
		act.startActivity(intent);
		break;	
		
    case R.id.ll_jiaoyi:
    	intent=new Intent(act,TransactionListActivity2.class);
		act.startActivity(intent);
  		break;
    case R.id.shouyi:
    	//showDialog();
    	String s1=BeikBankApplication.getSharePref(BeikBankConstant.money_is_yincang);
    	if(!"1".equals(s1))
    	{
    		BeikBankApplication.setSharePref(BeikBankConstant.money_is_yincang,"1");
    		yingcang();
    	}
    	else
    	{
    		BeikBankApplication.setSharePref(BeikBankConstant.money_is_yincang,"0");
    		yingcang();
    	}
    	
  		break;
	default:
		break;
	}
	
}
/**
 * 隐藏或者显示资产1显示2隐藏
 * @param index
 */
private void yingcang()
{   
	String s1=BeikBankApplication.getSharePref(BeikBankConstant.money_is_yincang);
	
	TextView tv_zuori=(TextView) sv.findViewById(R.id.tv_zuori);
	TextView tv_zhichan=(TextView) sv.findViewById(R.id.tv_zhichan);
	
	if(!"1".equals(s1))
	{   
		
		if(gd==null||gb==null)
		{
			tv_zuori.setText("0.0000");
			tv_zhichan.setText("0.00");
			tv4.setText("0.00");
		}
		else
		{
			tv_zuori.setText(NumberManager.getGeshiHua(gd.body.intrestYesterday,2));
			tv_zhichan.setText(NumberManager.getGeshiHua(gd.body.assetsCurrentTotal,2));
			tv4.setText(NumberManager.getGeshiHua(gb.body.card.acc_amount,2));
		}
		shouyi.setImageResource(R.drawable.img_zhican_shouyi_v2);
		if(listView!=null)
		{
		for(int i=0;i<listView.size();i++)
		{
			TextView tv=(TextView) listView.get(i);
			tv.setText(tv.getTag().toString());
		}
		}
	}
	else
	{
		tv_zuori.setText("****");
		tv_zhichan.setText("****");
		tv4.setText("****");
		shouyi.setImageResource(R.drawable.img_zhican_shouyi2_v2);
		if(listView!=null)
		{
		for(int i=0;i<listView.size();i++)
		{
			TextView tv=(TextView) listView.get(i);
			tv.setText("****");
		}
		}
	}
	
	
	
}



/**
 * 检查是否需要从新验证银行卡
 */
   private ICallBack icb8=new ICallBack() {
	
	@Override
	public void back(Object obj) 
	{
		CheckBankParam cbp=new CheckBankParam();
		cbp.memberID=BeikBankApplication.getUserid();
		ManagerParam mp=new ManagerParam();
		mp.isShowDialog=false;
		TongYongManager tym=new TongYongManager(act, icb7,cbp,mp);
		tym.start();
}};




/**
 * 充值回调
 */
   private ICallBack icb7=new ICallBack() {
	
	@Override
	public void back(Object obj) 
	{    
		if(obj!=null)
		{     
			CheckBank_data cd=(CheckBank_data) obj;
			CheckBank cb=cd.data;
			if(cb.UserCardLimit.equals("0"))
			{
				Intent intent=new Intent(act,BandTestActivity.class);
			    act.startActivity(intent);
			}
			else
			{
				 Intent intent=new Intent(act,QianbaoActivity2.class);
					act.startActivity(intent);
			}
		}
		
		
		
}};
}
