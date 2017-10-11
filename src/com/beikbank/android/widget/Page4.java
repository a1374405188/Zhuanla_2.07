//package com.beikbank.android.widget;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.beikbank.android.R;
//import com.beikbank.android.activity.BandTestActivity;
//import com.beikbank.android.activity.BankBindActivity;
//import com.beikbank.android.activity.BankBindActivity2;
//import com.beikbank.android.activity.DingqiDetailActivity;
//import com.beikbank.android.activity.DingqiGoumaiActivity;
//import com.beikbank.android.activity.HomeActivity2;
//import com.beikbank.android.activity.HuodongActivity2;
//import com.beikbank.android.activity.LoginRegActivity;
//import com.beikbank.android.activity.ProjectListActivity;
//import com.beikbank.android.activity.PurchaseActivity;
//import com.beikbank.android.activity.RealnameActivity;
//import com.beikbank.android.activity.RedeemActivity;
//import com.beikbank.android.activity.RiskControlActivity;
//import com.beikbank.android.activity.TransactionPwdSetActivity;
//import com.beikbank.android.activity.help.LiuChenManager;
//import com.beikbank.android.activity.help.TypeUtil;
//import com.beikbank.android.animation.AnimationManager;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.conmon.FinalIndex;
//import com.beikbank.android.conmon.MessageManger;
//import com.beikbank.android.dao.DingQiDao;
//import com.beikbank.android.data.Biaoshi;
//import com.beikbank.android.data.Biaoshi_data;
//import com.beikbank.android.data.CheckBank;
//import com.beikbank.android.data.CheckBank_data;
//import com.beikbank.android.data.DingqiP;
//import com.beikbank.android.data.DingqiP2;
//import com.beikbank.android.data.DingqiP_data;
//import com.beikbank.android.data.DingqiP_data2;
//import com.beikbank.android.data.FundInfo;
//import com.beikbank.android.data.IsCheckBank;
//import com.beikbank.android.data.IsCheckBank_data;
//import com.beikbank.android.data.TotalMoney_data;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.dataparam.BiaoshiParam;
//import com.beikbank.android.dataparam.CheckBankParam;
//import com.beikbank.android.dataparam.DingqiPIParam;
//import com.beikbank.android.dataparam.UserParam;
//import com.beikbank.android.fragment.BeikBankApplication;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.net.HandlerBase;
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.ImageUrl;
//import com.beikbank.android.net.ManagerParam;
//import com.beikbank.android.net.impl.DingqiPIManager;
//import com.beikbank.android.net.impl.DingqiPManager;
//import com.beikbank.android.net.impl.FundInfoManager;
//import com.beikbank.android.net.impl.IsCheckBankManager;
//import com.beikbank.android.net.impl.NoticeManager;
//import com.beikbank.android.net.impl.ThreadManagerSet;
//import com.beikbank.android.net.impl.TongYongManager;
//import com.beikbank.android.net.impl.TotalMoneyManager;
//import com.beikbank.android.pullrefresh.PullToRefreshBase;
//import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
//import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
//import com.beikbank.android.sharedpref.SharePrefConstant;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.DateManager;
//import com.beikbank.android.utils.NumberManager;
//import com.beikbank.android.utils.PageUtil;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.utils.ViewDataUtil;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.Handler;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
// /**
// *copyright 喻国合 
// *email: 1374405188@qq.com
// *2014-12-10
// **/
//public class Page4 extends LinearLayout implements OnClickListener{
//	private LinearLayout linear_projects,linear_riskcontrol;
//	//private MagicTextView magictextview_annual_rate;
//	private Dialog dialog;
//	private Timer timer = new Timer();
//	private int counter = 0;
//	private TextView textview_product_incomepercent,textview_totalInvestment,textview_totalIncome,
//	textview_totalInvestors,textview_totalProjects,textview_wanfen;
//	private String sid;
//	 private Context mcontext;
//	 private PullToRefreshScrollView prs;
//		private ScrollView sv;
//		HomeActivity2 act;
//	/**
//	 * 定期产品名称
//	 */
//    TextView tv_tv1;
//    /**
//	 * 年化收益率
//	 */
//    MagicTextView tv_tv2;
//    /**
//	 * 加真年化收益率
//	 */
//    TextView tv_tv3;
//    /**
//  	 * 万份收益率
//  	 */
//    TextView tv_tv4;
//    /**
//   	 * 理财期限
//   	 */
//     TextView tv_tv5;
//     /**
//       * 可购金额
//      */
//      TextView tv_tv6;
//      /**
//       * 资产安全
//      */
//      TextView tv_tv7;
//      /**
//       * 几个月
//      */
//      TextView tv_tv8;
//      /**
//       * 投资天数父布局
//       */
//       LinearLayout ll1;
//       
//       /**
//        * 选几个月父布局
//        */
//        LinearLayout ll2;
//        /**
//         * 加挣年化收益率
//         */
//        RelativeLayout rl0;
//        /**
//         * 加增图片
//         */
//        ImageView iv5;
//    /**
//     * 资产详情
//     */
//    Button bu_bu1;
//
//	public Page4(Context context, AttributeSet attrs) {
//		super(context, attrs);
//	
//	}
//	public Page4(Context context) {
//		super(context);
//
//	}
//	ICallBack next;
//	public void updateView(ICallBack next)
//	{   
//		//this.next=next;
//		//new FundInfoManager(act, icb).start();
//		addData2();
//		//act.addNotice();
//	}
////	public void addData()
////	{   
////		DingqiPManager dpm=new DingqiPManager(act, icb3);
////		dpm.start();
////	}
//	public void addData2()
//	{   
//		UserParam up=new UserParam();
//		String id=BeikBankApplication.getUserid();
//		if(id==null)
//		{
//			id="";
//		}
//		up.memberID=id;
//		ManagerParam mp=new ManagerParam();
//	
//		mp.isShowDialog=false;
//		TongYongManager tym=new TongYongManager(act, icb6,up,mp);
//		tym.start();
//		
//		
//		
//		BiaoshiParam bp=new BiaoshiParam();
//		bp.type="0";
//		ManagerParam mp2=new ManagerParam();
//		mp2.isShowMsg=false;
//		mp2.isShowDialog=false;
//		TongYongManager tym2=new TongYongManager(act, icb7,bp,mp2);
//		tym2.start();
//	}
//	/**
//	 * 加息标识回调
//	 */
//	ICallBack icb7=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			if(obj!=null)
//			{   
//				
//				Biaoshi_data bsd=(Biaoshi_data)obj;
//				if(bsd!=null)
//				{
//				  final Biaoshi bs=bsd.data;
//				  if(bs!=null&&bs.icon!=null&&!bs.icon.equals(""))
//				  { 
//					iv5.setVisibility(View.VISIBLE);
//					if(bs.linkUrl!=null&&!bs.linkUrl.equals(""))
//					{   
//						iv5.setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Intent intent=new Intent(act,HuodongActivity2.class);
//								intent.putExtra("url",bs.linkUrl);
//								intent.putExtra("title",bs.title);
//								act.startActivity(intent);
//							}
//						});
//						
//					}
//				    ImageUrl iu=new ImageUrl(iv5,bs.icon);
//				    iu.start();
//				  }
//				}
//			}
//		}
//	};
//	
//	DingqiP2 dp2;
//	/**
//	 * 所有定期产品
//	 */
//	List<DingqiP2> list2;
//	DingqiP_data2 dpd;
//	ICallBack icb6=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			   if(obj!=null)
//			   {
//			     dpd=(DingqiP_data2)obj;
//			     list2=dpd.data.productBond;
//			    
//			     DingQiDao.set(list2);
//			   }
//			   else
//			   {
//				   list2=DingQiDao.get();
//			   }
//			   if(list2==null)
//			   {
//				   return;
//			   }
//			   
//			   
//			     list2=newDpSort(list2);
//			     dp2=getDP2(dp2, list2);
//			     setViewData(dp2);
//			     setView();
//			   
//		}
//	};
//	private void test(List<DingqiP2> list2)
//	{
//		DingqiP2 dp=list2.get(2);
//		dpd.data.userLevel="1";
//		dp.termbondType="1";
//		
//	}
//	/**
//	 * 对标的进行排序，如果是新手，新手标放在前面，如果是老手，新手标放后面
//	 * @param list
//	 * @return
//	 */
//	private List<DingqiP2> newDpSort(List<DingqiP2> list)
//	{   
//		boolean do_success=BeikBankApplication.isLogin();
//		if(!do_success)
//		{
//			return list;
//		}
//		//new
//		List<DingqiP2> list2=new ArrayList<DingqiP2>();
//		//old
//		List<DingqiP2> list3=new ArrayList<DingqiP2>();
//		List<DingqiP2> list4=new ArrayList<DingqiP2>();
//		for(DingqiP2 dp2:list)
//		{
//			if("1".equals(dp2.termbondType))
//			{
//				list2.add(dp2);
//			}
//			else
//			{
//				list3.add(dp2);
//			}
//		}
//		String s1="1";
//		if(dpd!=null)
//		{
//			s1=dpd.data.userLevel;
//		}
//		if(s1.equals("0"))
//		{
//			for(DingqiP2 dp3:list2)
//			{
//				list4.add(dp3);
//			}
//			for(DingqiP2 dp3:list3)
//			{
//				list4.add(dp3);
//				
//				
//				
//				
//			}
//		}
//		else
//		{
//			for(DingqiP2 dp3:list3)
//			{
//				list4.add(dp3);
//			}
//			for(DingqiP2 dp3:list2)
//			{
//				list4.add(dp3);
//			}
//		}
//		return list4;
//	}
//	/**
//	 * 得到当前的的dp
//	 */
//	private DingqiP2 getDP2(DingqiP2 dp,List<DingqiP2> list)
//	{
//		if(dp==null)
//		{   
//			dp=list.get(0);
//			index=0;
//			return list.get(0);
//		}
//		else
//		{
//			for(int i=0;i<list.size();i++)
//			{   
//				DingqiP2 dp2=list.get(i);
//				if(dp.termbondCode.equals(dp2.termbondCode))
//				{   
//					index=i;
//					dp=dp2;
//					return dp2;
//				}
//			}
//		}
//		dp=list.get(0);
//		index=0;
//		return list.get(0);
//	}
//	//DingqiP dp;
//	//当前是第几项
//	int index;
//	/**
//	 * 所有定期产品
//	 */
//	//List<DingqiP> list;
//	/**
//	 * 得到当前的的dp
//	 */
//	private DingqiP getDP(DingqiP dp,List<DingqiP> list)
//	{
//		if(dp==null)
//		{   
//			dp=list.get(0);
//			index=0;
//			return list.get(0);
//		}
//		else
//		{
//			for(int i=0;i<list.size();i++)
//			{   
//				DingqiP dp2=list.get(i);
//				if(dp.termbondCode.equals(dp2.termbondCode))
//				{   
//					index=i;
//					dp=dp2;
//					return dp2;
//				}
//			}
//		}
//		dp=list.get(0);
//		index=0;
//		return list.get(0);
//	}
////	ICallBack icb3=new ICallBack() {
////		
////		@Override
////		public void back(Object obj) {
////		   prs.onPullDownRefreshComplete();
////		   if(obj!=null)
////		   {
////			   DingqiP_data dpd=(DingqiP_data)obj;
////			   list=dpd.data;
////			   dp=getDP(dp, list);
////			   //setViewData(dp);
////			   setView();
////		   }
////		}
////	};
//	
//	
//	ImageView iv1;
//	ImageView iv2;
//	ImageView iv3;
//	ImageView iv4;
//	RelativeLayout rl1;
//	RelativeLayout rl2;
//	RelativeLayout rl3;
//	RelativeLayout rl4;
//	TextView tv1;
//	TextView tv2;
//	TextView tv3;
//	TextView tv4;
//	private void setView()
//	{   
//		LayoutInflater li=act.getLayoutInflater();
//		LinearLayout ll=new LinearLayout(act);
//		View ll1=null;
//		if(list2.size()==1)
//		{
//			ll1=li.inflate(R.layout.page_dingqi1,ll,false);
//		}
//		else if(list2.size()==2)
//		{
//			ll1=li.inflate(R.layout.page_dingqi2,ll,false);
//		}
//		else if(list2.size()==3)
//		{
//			ll1=li.inflate(R.layout.page_dingqi3,ll,false);
//		}
//		else
//		{
//			ll1=li.inflate(R.layout.page_dingqi4,ll,false);
//		}
//		tv1=(TextView) ll1.findViewById(R.id.tv1);
//		tv2=(TextView) ll1.findViewById(R.id.tv2);
//		tv3=(TextView) ll1.findViewById(R.id.tv3);
//		tv4=(TextView) ll1.findViewById(R.id.tv4);
//		
//		iv1=(ImageView) ll1.findViewById(R.id.iv1);
//		iv2=(ImageView) ll1.findViewById(R.id.iv2);
//		iv3=(ImageView) ll1.findViewById(R.id.iv3);
//		iv4=(ImageView) ll1.findViewById(R.id.iv4);
//		rl1=(RelativeLayout) ll1.findViewById(R.id.rl1);
//		rl2=(RelativeLayout) ll1.findViewById(R.id.rl2);
//		rl3=(RelativeLayout) ll1.findViewById(R.id.rl3);
//		rl4=(RelativeLayout) ll1.findViewById(R.id.rl4);
//		ll2.removeAllViews();
//		ll2.addView(ll1);
//		setView2();
//		setView3();
//		if(list2.size()>1)
//		{  
//			if(index==3)
//			{
//				iv4.setVisibility(View.VISIBLE);
//				tv4.setBackgroundResource(R.drawable.bg_page_yue2);
//				tv4.setTextColor(color2);
//			}
//			else if(index==1)
//			{
//				iv2.setVisibility(View.VISIBLE);
//				tv2.setBackgroundResource(R.drawable.bg_page_yue2);
//				tv2.setTextColor(color2);
//			}
//			else if(index==2)
//			{
//				iv3.setVisibility(View.VISIBLE);
//				tv3.setBackgroundResource(R.drawable.bg_page_yue2);
//				tv3.setTextColor(color2);
//			}
//			else{
//				iv1.setVisibility(View.VISIBLE);
//				tv1.setBackgroundResource(R.drawable.bg_page_yue2);
//				tv1.setTextColor(color2);
//			}
//		}
//		else
//		{
//			
//		}
//	}
//
//	private void setView3()
//	{
//		if(rl1!=null)
//		{  
//			tv1.setText(list2.get(0).showName);
//			rl1.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					setView2();
//					if(list2!=null&&list2.size()>1)
//					{   
//						dp2=list2.get(0);
//						iv1.setVisibility(View.VISIBLE);
//						tv1.setTextColor(color2);
//						tv1.setBackgroundResource(R.drawable.bg_page_yue2);
//						setViewData(dp2);
//					}
//				}
//			});
//		}
//		if(rl2!=null)
//		{   
//			tv2.setText(list2.get(1).showName);
//			rl2.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					setView2();
//					if(list2!=null&&list2.size()>1)
//					{  
//					   dp2=list2.get(1);
//					   iv2.setVisibility(View.VISIBLE);
//					   tv2.setTextColor(color2);
//					   tv2.setBackgroundResource(R.drawable.bg_page_yue2);
//					   setViewData(dp2);
//					}
//				}
//			});
//		}
//		if(rl3!=null)
//		{   
//			tv3.setText(list2.get(2).showName);
//			rl3.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					setView2();
//					if(list2!=null&&list2.size()>1)
//					{ 
//						dp2=list2.get(2);
//					   iv3.setVisibility(View.VISIBLE);
//					    tv3.setTextColor(color2);
//						tv3.setBackgroundResource(R.drawable.bg_page_yue2);
//					   setViewData(dp2);
//					}
//				}
//			});
//		}
//		if(rl4!=null)
//		{   
//			tv4.setText(list2.get(3).showName);
//			rl4.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					setView2();
//					if(list2!=null&&list2.size()>1)
//					{   
//						dp2=list2.get(3);
//					    iv4.setVisibility(View.VISIBLE);
//					    tv4.setTextColor(color2);
//						tv4.setBackgroundResource(R.drawable.bg_page_yue2);
//					    setViewData(dp2);
//					}
//				}
//			});
//		}
//	}
//	final int color1=0xff747474;
//	final int color2=0xffdd2238; 
//	private void setView2()
//	{
//		if(iv1!=null)
//		{
//			iv1.setVisibility(View.GONE);
//			tv1.setTextColor(color1);
//			tv1.setBackgroundResource(R.drawable.bg_page_yue);
//		}
//		if(iv2!=null)
//		{
//			iv2.setVisibility(View.GONE);
//			tv2.setTextColor(color1);
//			tv2.setBackgroundResource(R.drawable.bg_page_yue);
//		}
//		if(iv3!=null)
//		{
//			iv3.setVisibility(View.GONE);
//			tv3.setTextColor(color1);
//			tv3.setBackgroundResource(R.drawable.bg_page_yue);
//		}
//		if(iv4!=null)
//		{
//			iv4.setVisibility(View.GONE);
//			tv4.setTextColor(color1);
//			tv4.setBackgroundResource(R.drawable.bg_page_yue);
//		}
//	}
//	Handler handler=new Handler();
//	Runnable run;
//	//开始倒计时计算
//	private void startDownTime()
//	{
//		  run=new Runnable() {
//			
//			@Override
//			public void run() {
//				time--;
//				if(time<=0)
//				{
//					  bu_bu1.setEnabled(true);
//					  bu_bu1.setText("立即购买");
//					  handler.removeCallbacks(run);
//				}
//				else
//				{  
//					bu_bu1.setEnabled(false);
//					bu_bu1.setText("距离开售还有    "+DateManager.countDateSub(time));
//				}
//				
//				handler.postDelayed(run,1000);
//				
//			}
//		};
//		
//		handler.postDelayed(run,1000);
//	}
//	//剩余时间的秒数
//	private long time;
//	private void setViewData(DingqiP2 dp)
//	{      
//		try
//		{  
//			 if(run!=null)
//			   {
//				   handler.removeCallbacks(run);
//			   }
//			 if(dp.categoryName!=null)
//			 {
//		        tv_tv1.setText(dp.categoryName);
//			 }
//			 
//		   double d=Double.parseDouble(ViewDataUtil.getD1(dp.yearRate));
//		   tv_tv2.setValue(d);
//		   mHandler.sendEmptyMessageDelayed(0, 0);
//		   String s2=NumberManager.getString(dp.extraRate,"100",2);
//		   
//		   tv_tv3.setText("+"+s2);
//		   double dou=Double.parseDouble(dp.extraRate);
//		   if(dou<=0)
//		   {
//			   tv_tv3.setVisibility(View.INVISIBLE);
//		   }
//		   else
//		   {
//			   tv_tv3.setVisibility(View.VISIBLE);
//		   }
//		   
//		   String s0=NumberManager.getAddString(dp.yearRate,dp.extraRate,4);
//		   String ss=NumberManager.getDivString(s0,"365",10);
//		   ss=NumberManager.getString(ss,"10000",2);
//		   tv_tv4.setText(ss);
//		   
//		   tv_tv5.setText(dp.termbondPeriod);
//		   
//		   String s3=NumberManager.getString(dp.remainAmount,"1",0);
//		   tv_tv6.setText(NumberManager.getGeshiHua(s3, 0));
//		   tv_tv7.setText(dp.security);
//		   //tv_tv8.setText(dp.showName);
//		   if(dp.status.equals(FinalIndex.dingqi1))
//		   {   
//			   double d2=Double.parseDouble(dp.remainAmount);
//			   if(d2<=0)
//			   {
//				   bu_bu1.setEnabled(false);
//				   bu_bu1.setText("售罄");
//				   tv_tv6.setText("0.00");
//				   return;
//			   }
//			   bu_bu1.setEnabled(true);
//			   bu_bu1.setText("立即购买");
//			   //倒计时
//			  
//			   
//			   time=Long.parseLong(dp.countdown);
//			   if(time>0)
//			   {
//				   bu_bu1.setEnabled(false);
//				   bu_bu1.setText("距离开售还有    "+DateManager.countDateSub(time));
//				  
//				   startDownTime();
//			   }
//			   else
//			   {
//				   bu_bu1.setEnabled(true);
//				   bu_bu1.setText("立即购买");
//			   }
//		   }
//		   else
//		   {
//			   bu_bu1.setEnabled(false);
//			   bu_bu1.setText("售罄");
//			   tv_tv6.setText("0.00");
//		   }
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//	private void init2(View view)
//	{
//		tv_tv1=(TextView) view.findViewById(R.id.tv_tv1);
//		tv_tv2=(MagicTextView) view.findViewById(R.id.tv_tv2);
//		
////		Typeface tf = Typeface.createFromAsset(act.getAssets(), "Helvetica2.ttf");  
////		if(tf!=null)
////		{
////			tv_tv2.setTypeface(tf);
////		}
//		
//		
//		
//		tv_tv3=(TextView) view.findViewById(R.id.tv_tv3);
//		tv_tv4=(TextView) view.findViewById(R.id.tv_tv4);
//		tv_tv5=(TextView) view.findViewById(R.id.tv_tv5);
//		tv_tv6=(TextView) view.findViewById(R.id.tv_tv6);
//		tv_tv7=(TextView) view.findViewById(R.id.tv_tv7);
//		
//		iv5=(ImageView)view.findViewById(R.id.iv5);
//		//tv_tv8=(TextView) view.findViewById(R.id.tv_tv8);
//		
//		bu_bu1=(Button) view.findViewById(R.id.bu_bu1);
//		bu_bu1.setOnClickListener(this);
//		//rl0=(RelativeLayout) view.findViewById(R.id.rl0);
//		
//		ll1=(LinearLayout)view.findViewById(R.id.ll1);
//		ll2=(LinearLayout)view.findViewById(R.id.ll2);
//		ll1.setOnClickListener(this);
//	}
//    public void init(Activity act1)
//    {   
//    	act=(HomeActivity2)act1;
//		LayoutInflater li=act.getLayoutInflater();
//		LinearLayout ll=new LinearLayout(act);
//		View view = li.inflate(R.layout.page_wealth11,ll,false);
//		View view2= li.inflate(R.layout.page_dingqi,ll,false);
//		init2(view2);
//		
//		prs=(PullToRefreshScrollView) view.findViewById(R.id.prs);
//		prs.setPullLoadEnabled(false);
//		prs.setScrollLoadEnabled(false);
//		prs.getRefreshableView();
//		sv=prs.getRefreshableView();
//		sv.addView(view2);
//        
//		
//
//		mcontext=act;
//
//		addView(view);
//		initOn();
//		//addData();
//		addData2();
//    }
//    protected void startAimActivity(Class<?> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(act, pActClassName);
//		act.startActivity(_Intent);
//	}
//
//
//	public Handler mHandler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			tv_tv2.doScroll(0);
//		};
//	};
//	   public ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//
//
//		}
//	};
//	/**
//	 * 刷新网络数据
//	 */
//	public void refresh()
//	{
//		prs.doPullRefreshing(false,0);
//	}
//	/**
//	 * 是下啦刷新
//	 */
//	boolean isOnrefresh=false;
//	   public void initOn()
//	    {
//	    	prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//
//				@Override
//				public void onPullDownToRefresh(
//						PullToRefreshBase<ScrollView> refreshView) {
////					index=1;
////					//BeikBankApi.getInstance().getProductFundInfo(act, getProductFundInfoHandler);
////					isOnrefresh=true;
////					ThreadManagerSet tms=new ThreadManagerSet(icb1);
////					FundInfoManager fm=new FundInfoManager(act, icb);
////					NoticeManager nm=new NoticeManager(act, icb2);
////					tms.add(fm);
////					tms.add(nm);
////					tms.start();
//					updateView(null);
//					
//				}
//
//				@Override
//				public void onPullUpToRefresh(
//						PullToRefreshBase<ScrollView> refreshView) {
//
//					
//				}
//			});
//	    	
//	    }
//		
//	ICallBack icb1=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(prs!=null)
//			{
//				prs.onPullDownRefreshComplete();
//			}
//		}
//	};
//	ICallBack icb2=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				act.showNotice(obj);
//			}
//		}
//	};
//	ICallBack icb4=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			 if(obj==null)
//			 {    
//				    String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
//					if(play.equals("0"))
//					{
//						    CheckBankParam cbp=new CheckBankParam();
//							cbp.memberID=BeikBankApplication.getUserid();
//							TongYongManager tym=new TongYongManager(act, icb8,cbp);
//							tym.start();
//					}
//					else
//					{
//						   Intent intent=new Intent(act,DingqiGoumaiActivity.class);
//						   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
//						  // intent.putExtra(DingqiDetailActivity.index,dp2);
//						   intent.putExtra("money",act.vpl.page1.uc.fundAmount);
//						   //intent.putExtra(TypeUtil.jiaoyi_money,act.vpl.page1.uc.fundAmount);
//						   intent.putExtra(TypeUtil.dingdi_data,dp2);
//						   act.startActivity(intent);
//						
//					}
//				   
//					
//			 }
//		}
//	};
//	ICallBack icb8=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				CheckBank_data cd=(CheckBank_data) obj;
//				CheckBank cb=cd.data;
//				if(cb.UserCardLimit.equals("0"))
//				{
//					Intent intent=new Intent(act,BandTestActivity.class);
//					intent.putExtra("is_nextpage",true);
//				    act.startActivity(intent);
//				}
//				else
//				{
//					  Intent intent=new Intent(act,DingqiGoumaiActivity.class);
//					   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
//					   //intent.putExtra(DingqiDetailActivity.index,dp2);
//					   intent.putExtra("money",act.vpl.page1.uc.fundAmount);
//					   //intent.putExtra(TypeUtil.jiaoyi_money,act.vpl.page1.uc.fundAmount);
//					   intent.putExtra(TypeUtil.dingdi_data,dp2);
//					   act.startActivity(intent);
//				}
//			}
//			
//		}
//	};
//	
//	//选择支付方式
//	private void selectPlay()
//	{
//		LiuChenManager.selectPay(icb4, act,true);
//	}
//	//回调选择支付方式
//	ICallBack icb5=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			 //LiuChenManager.StartNext(act,icb4);
//			if(obj==null)
//			{ 
//				  boolean b=doNew(dp2,dpd.data.userLevel);
//				   if(!b)
//				   {	
//			          selectPlay();
//				   }
//			}
//		}
//	};
//	/**
//	 * 如果点击的是新手标，进行相应的处理 
//	 * @return true 不进行后续的代码
//	 */
//	public  boolean doNew(DingqiP2 dp2,String isNew)
//	{
//		
//		if(dp2.termbondType.equals("1"))
//		{
//			if(isNew.equals("0"))
//			{   
//				
//			}
//			else
//			{
//				PageUtil pu=new PageUtil(act,DingqiDetailActivity.countRate(dp2.yearRate,dp2.extraRate));
//				pu.showShapeDialog();
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	@Override
//	public void onClick(View v) {
//		switch(v.getId()){
//		case R.id.bu_bu1:
//		  
////		   if(dp!=null&&dp.termbondCode!=null)
////		   {   
////			   //LiuChenManager.StartNext(act,icb4);
////			   selectPlay();
////		   }
//			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.huo_ding,
//					2);
//			if(dp2!=null&&dp2.termbondCode!=null)
//			   {   
//				   //LiuChenManager.StartNext(act,icb4);
////				   boolean b=doNew(dp2,dpd.data.userLevel);
////				   if(!b)
////				   {
//				     //selectPlay();
//					   LiuChenManager.StartNext(act,icb5);
//				  // }
//			   }
//			break;
//		case R.id.ll1:
//			  
//			   if(dp2!=null&&dp2.termbondCode!=null)
//			   {   
//				 
//				   Intent intent=new Intent(act,DingqiDetailActivity.class);
//				   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
//				   dp2.countdown=time+"";
//				   intent.putExtra(TypeUtil.dingdi_data,dp2);
//				   if(act.vpl!=null&&act.vpl.page1!=null&&act.vpl.page1.uc!=null)
//				   {
//				     intent.putExtra("money",act.vpl.page1.uc.fundAmount);
//				   }
//				   if(dpd!=null&&dpd.data!=null)
//				   {
//				   intent.putExtra("index3",dpd.data.userLevel);
//				   }
//				   act.startActivity(intent);
//			   }
//				break;
//		case R.id.bu_bu2:
//		
//			break;
//		}
//	}
//	
////	//水平进度条动画，每过1000/percent秒增加1%
////	public void timersch(final int percent){
////		timer = new Timer();
////		timer.schedule(new TimerTask() {
////			@Override
////			public void run() {
////				act.runOnUiThread(new Runnable() {
////					@Override
////					public void run() {						
////						if (counter == percent) {
////							timer.cancel();
////						}else{
////							//numberprogress_rate.incrementProgressBy(1);
////							counter ++;
////						}
////					}
////				});
////			}
////		}, 500, (int)(1000/percent));
////	}
//
//}
