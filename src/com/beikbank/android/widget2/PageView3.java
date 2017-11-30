package com.beikbank.android.widget2;

import java.util.ArrayList;

import com.beikbank.android.activity.BandTestActivity;
import com.beikbank.android.activity.GerenActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.MessageActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.activity.QianbaoActivity1;
import com.beikbank.android.activity.QianbaoActivity2;
import com.beikbank.android.activity.QianbaoActivity3;
import com.beikbank.android.activity.TransactionListActivity2;
import com.beikbank.android.activity.UserRecordActivity;
import com.beikbank.android.activity.UserRecordActivity2;
import com.beikbank.android.activity.YouHuiQuanActivity;
import com.beikbank.android.activity.ZhiChanActivity2;
import com.beikbank.android.activity.help.ActivitySwitchHelp;
import com.beikbank.android.activity.help.LiuChenManager;
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
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.UserCapital2Manager;
import com.beikbank.android.net.impl.UserCapitalInfoManager;
import com.beikbank.android.net.impl2.ZhiCanManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.text.TextUtil;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
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
    private LinearLayout ll_dingqishouyi;
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
    	View view=LayoutInflater.from(context).inflate(R.layout.view_pageview3,ll,false); 
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
		prs.setBackgroundResource(R.drawable.img_jiazai_beiji);
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
	    String c1=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache1);
	    String c2=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache2);
	    String c3=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache3);
	    String c4=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache4);
	    String c5=BeikBankApplication.mSharedPref.getSharePrefString(CacheIndex.cache5);
	    tv1.setText(c1);
	    tv5.setText(c5);
	    tv3.setText(c3);
	    tv2.setText(c2);
	    tv4.setText(c4);
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
    	tv7.setOnClickListener(this);
    	
    	iv1=(ImageView) view.findViewById(R.id.iv1);
    	iv2=(ImageView) view.findViewById(R.id.iv2);
    	
    	shouyi=(ImageView) view.findViewById(R.id.shouyi);
    	iv1.setOnClickListener(this);
    	iv2.setOnClickListener(this);
    	shouyi.setOnClickListener(this);
    	boolean islogin=BeikBankApplication.isLogin();
    	if(islogin)
    	{   
    		String phone=Utils.getEncryptedPhonenumber(BeikBankApplication.getPhoneNumber());
    		tv7.setText(phone);
    	}
    	
    	ll_jiaoyi=(LinearLayout) findViewById(R.id.ll_jiaoyi);
    	ll_jiaoyi.setOnClickListener(this);
    	tv_chongzhi=(TextView) findViewById(R.id.tv_chongzhi);
    	tv_tixian=(TextView) findViewById(R.id.tv_tixian);
    	ll_dingqishouyi=(LinearLayout) findViewById(R.id.ll_dingqishouyi);
    	ll_huoqishouyi=(LinearLayout) findViewById(R.id.ll_huoqishouyi);
    	ll_zhichan=(LinearLayout) findViewById(R.id.ll_zhichan);
    	ll_hongbao=(LinearLayout) findViewById(R.id.ll_hongbao);
    	
    	tv_chongzhi.setOnClickListener(this);
    	tv_tixian.setOnClickListener(this);
    	ll_dingqishouyi.setOnClickListener(this);
    	ll_huoqishouyi.setOnClickListener(this);
    	ll_zhichan.setOnClickListener(this);
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
    		tv7.setText(phone);
    	}
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
	ZhiCanManager zc;
    /**
	 * 加载数据
	 */
	public void addData()
	{   
		
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
		zc=new ZhiCanManager(act, icb1);
		zc.start();
		HomeActivity3.ha.zc=zc;
		
		
		//得到几张可用
		HongbaoParam hp=new HongbaoParam();
		hp.userId=BeikBankApplication.getUserid();
		if(hp!=null&&hp.userId!=null&&!hp.userId.equals(""))
		{
		ManagerParam mp=new ManagerParam();
		mp.isShowDialog=false;
		mp.isShowMsg=false;
		TongYongManager tym=new TongYongManager(act, icb9,hp,mp);
		tym.start();
		}
	}
	
	private ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			prs.onPullDownRefreshComplete();
			if(obj!=null)
			{
				tv5.setText(NumberManager.getGeshiHua(zc.money, 2));
				
				
				
		 
			   
				
				String s2=NumberManager.getAddString(zc.uc.bondInterestY,zc.uc.fundInterestY,4);

				//昨日收益
				tv1.setText(s2);
				

				//定期累计收益
				String s3=NumberManager.getString(zc.uc.bondInterestT,"1",2);
				tv3.setText(s3);

				//活期累计收益
				String s4=NumberManager.getString(zc.uc.fundInterestT,"1",2);
				tv2.setText(NumberManager.getGeshiHua(s4,2));
				
				
				tv4.setText(NumberManager.getGeshiHua(zc.qianbao, 2));
				
				
				
				//将最新的数据写入缓存
				BeikBankApplication.mSharedPref.putSharePrefString(CacheIndex.cache1,s2);
				BeikBankApplication.mSharedPref.putSharePrefString(CacheIndex.cache2,NumberManager.getGeshiHua(s4,2));
				BeikBankApplication.mSharedPref.putSharePrefString(CacheIndex.cache3,s3);
				BeikBankApplication.mSharedPref.putSharePrefString(CacheIndex.cache4,NumberManager.getGeshiHua(zc.qianbao,2));
				BeikBankApplication.mSharedPref.putSharePrefString(CacheIndex.cache5,NumberManager.getGeshiHua(zc.money, 2));
				
			}
			
			else
			{
				Toast.makeText(act,"网络异常",Toast.LENGTH_LONG).show();
			}
		}
	};
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
		intent=new Intent(act,GerenActivity.class);
		ActivitySwitchHelp.start(act,intent,false);
		break;
    case R.id.iv2:
    	intent=new Intent(act,MessageActivity.class);
		act.startActivity(intent);
		break;
   case R.id.tv_chongzhi:
	    LiuChenManager.StartNext(act,icb8);
		break;
   case R.id.tv_tixian:
	    
	    intent=new Intent(act,QianbaoActivity3.class);
	    String s3=QianbaoActivity1.countTotal(zc.ye);
	    intent.putExtra("index",NumberManager.getString(s3,"1",2));
	    intent.putExtra("qb",zc.ye);
		act.startActivity(intent);
		break;	
   case R.id.ll_dingqishouyi:
	    intent=new Intent(act,UserRecordActivity2.class);
	    intent.putExtra("money",zc.uc.bondInterestT);
		act.startActivity(intent);
		break;	
   case R.id.ll_huoqishouyi:
	   intent=new Intent(act,UserRecordActivity.class);
	   intent.putExtra("money",zc.uc.fundInterestT);
	   intent.putExtra("index","1");
		act.startActivity(intent);
		break;
		
   case R.id.ll_hongbao:
	   intent=new Intent(act,YouHuiQuanActivity.class);
		act.startActivity(intent);
		break;	
   case R.id.ll_zhichan:
	   if(zc.uc==null)
	   {
		   return;
	   }
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
    	showDialog();
  		break;
	default:
		break;
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
