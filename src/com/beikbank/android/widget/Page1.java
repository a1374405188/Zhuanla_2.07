//package com.beikbank.android.widget;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//import com.beikbank.android.R;
//import com.beikbank.android.activity.HomeActivity2;
//import com.beikbank.android.activity.QianbaoActivity1;
//import com.beikbank.android.activity.QianbaoActivity8;
//import com.beikbank.android.activity.TransactionListActivity2;
//import com.beikbank.android.activity.UserRecordActivity;
//import com.beikbank.android.activity.UserRecordActivity2;
//import com.beikbank.android.activity.YouHuiQuanActivity;
//import com.beikbank.android.activity.ZhiChanActivity;
//import com.beikbank.android.activity.ZhiChanActivity2;
//import com.beikbank.android.activity.help.LiuChenManager;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.conmon.SystemConfig;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.dao.ZhiChanDao;
//import com.beikbank.android.data.Hongbao;
//import com.beikbank.android.data.Hongbao_data;
//import com.beikbank.android.data.IncomeInfo;
//import com.beikbank.android.data.MoneyInfo;
//import com.beikbank.android.data.MoneyInfo_data;
//import com.beikbank.android.data.Qianbao1_data;
//import com.beikbank.android.data.TotalCapitalInfo;
//import com.beikbank.android.data.TotalMoney;
//import com.beikbank.android.data.TotalMoney_data;
//import com.beikbank.android.data.UserCapital;
//import com.beikbank.android.data.UserCapital2;
//import com.beikbank.android.data.UserCapital2_data;
//import com.beikbank.android.data.UserCapitalInfo;
//import com.beikbank.android.data.UserCapitalInfo2;
//import com.beikbank.android.data.UserCapitalInfo_data;
//import com.beikbank.android.data.UserCapital_data;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.data.Yuer;
//import com.beikbank.android.data.Yuer_data;
//import com.beikbank.android.dataparam.HongbaoParam;
//import com.beikbank.android.dataparam.TotalMoneyParam;
//import com.beikbank.android.dataparam.YuerParam;
//import com.beikbank.android.fragment.BeikBankApplication;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.ManagerParam;
//import com.beikbank.android.net.impl.MoneyInfoManager;
//import com.beikbank.android.net.impl.NoticeManager;
//import com.beikbank.android.net.impl.ThreadManagerSet;
//import com.beikbank.android.net.impl.TongYongManager;
//import com.beikbank.android.net.impl.TotalMoneyManager;
//import com.beikbank.android.net.impl.UserCapital2Manager;
//import com.beikbank.android.net.impl.UserCapitalInfoManager;
//import com.beikbank.android.net.impl.UserCapitalManager;
//import com.beikbank.android.pullrefresh.PullToRefreshBase;
//import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
//import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
//import com.beikbank.android.sharedpref.SharedPref;
//import com.beikbank.android.text.TextUtil;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.DialogManager;
//import com.beikbank.android.utils.NumberManager;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.utils.hongbao.HongbaoUtil;
//import com.google.gson.Gson;
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
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.animation.LinearInterpolator;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
// /**
// *copyright 喻国合 
// *email: 1374405188@qq.com
// *2014-12-10
// **/
//public class Page1 extends LinearLayout implements OnClickListener{
//	private final String TAG="WealthFragment";
//    
//	private LinearLayout ll;
//	private TextView magictextview_yesterdayincome,magictextview_totalcapital;
//	private TextView textview_interestM,textview_interestW,textview_interestT;
//	//public  TextView textview_rate;
//	private PullToRefreshScrollView prs;
//	private ScrollView sv;
//	private UserInfo userInfo;
//	HomeActivity2 act;
//	Dialog dialog;
//	/**
//	 * 头部区域
//	 */
//	LinearLayout ll1;
//	/**
//	 * 累计收益
//	 */
//	LinearLayout ll_all;
//	/**
//	 * 定期累计收益
//	 */
//	LinearLayout ll_week;
//	
//	/**
//	 * 交易记录
//	 */
//	Button bu_bu1;
//	/**
//	 * 资产详情
//	 */
//	Button bu_bu2;
//    
//	/**
//	 * 提示图标
//	 */
//    ImageView iv_iv1;
//    /**
//     * 优惠券父布局
//     */
//    LinearLayout ll_yhq;
//    /**
//     * 总资产接口调用完成计数,等于3时3个接口全部完成
//     */
//    int money_count;
//    /**
//     * 总资产的值
//     */
//    String money="0";
//    /**
//     * 优惠券
//     */
//    private ImageView yhq;
//	public Page1(Context context, AttributeSet attrs) {
//		super(context, attrs);
//
//	}
//	public Page1(Context context) {
//		super(context);
//		
//	}
//	
//	Yuer ye;
//	private boolean isaddData=true;
//	/**
//	 * 加载数据
//	 */
//	public void addData()
//	{   
//		
//		if(isaddData)
//		{
//			isaddData=false;
//		money_count=0;
//		money="0";
//		
//		ManagerParam mp=new ManagerParam();
//		mp.isShowDialog=false;
//		TotalMoneyParam tmp=new TotalMoneyParam();
//	    tmp.memberID=BeikBankApplication.getUserid();
//	    intozc=false;
//		UserCapital2Manager usm=new UserCapital2Manager(act,icb4,tmp);
//		usm.start();
//		
//		YuerParam yp=new YuerParam();
//		yp.memberID=BeikBankApplication.getUserid();
//		TongYongManager tym=new TongYongManager(act, icb5,yp,mp);
//		tym.start();
//		
//		
//		TotalMoneyParam tp=new TotalMoneyParam();
//    	tp.memberID=BeikBankApplication.getUserid();
//    	UserCapitalInfoManager ucm=new UserCapitalInfoManager(act, icb6, tp);
//    	ucm.start();
//		}
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
//	}
//	
//	/**
//	 * 判断有没有可用红包回调
//	 */
//   private ICallBack icb9=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{   
//				Hongbao_data hd=(Hongbao_data) obj;
//				ArrayList<Hongbao> list=hd.data;
//				String count=ZhiChanActivity2.select(list);
//				if(HongbaoUtil.compreS(count,"0")>0)
//				{
//				   ll_yhq.setVisibility(View.VISIBLE);
//				   postDelayed(run,5000);
//				}
//			}
//		}
//	};
//	
//	public UserCapitalInfo uci;
//	ICallBack icb6=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
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
//				  magictextview_totalcapital.setText(NumberManager.getGeshiHua(money, 2));
//				  isaddData=true;
//				  TextUtil.setSize(act,magictextview_totalcapital,NumberManager.getGeshiHua(money, 2));
//				  doData(money);
//			  }
//				}
//			}
//		}
//	};
//	/**
//	 * 得到募集中的总钱数
//	 * @return
//	 */
//	private String getMoney(UserCapitalInfo uci)
//	{  
//		String s="0";
//		ArrayList<UserCapitalInfo2> list=uci.termbondUnconfirmedList;
//		for(UserCapitalInfo2 uc:list)
//		{
//			s=NumberManager.getAddString(s,uc.amount, 4);
//		}
//		for(UserCapitalInfo2 uc:uci.termbondList)
//		{
//			s=NumberManager.getAddString(s,uc.amount, 4);
//		}
//		return s;
//	}
//	public void updateView()
//	{
//		//new TotalMoneyManager(act, icb1).start();
//		//dialog=Utils.createPorgressDialog(act, null);
//		//dialog.show();
////		ThreadManagerSet tms=new ThreadManagerSet(icb);
////		
////		
////		//TotalMoneyManager tmm=new TotalMoneyManager(act, icb1);
////		//MoneyInfoManager mi=new MoneyInfoManager(act, icb2);
////		NoticeManager nm=new NoticeManager(act, icb3);
//		money_count=0;
//		money="0";
//		TotalMoneyParam tmp=new TotalMoneyParam();
//	    tmp.memberID=BeikBankApplication.getUserid();
//	    intozc=false;
//		UserCapital2Manager usm=new UserCapital2Manager(act,icb4,tmp);
//		usm.start();
//		act.addNotice();
//		
//		YuerParam yp=new YuerParam();
//		yp.memberID=BeikBankApplication.getUserid();
//		TongYongManager tym=new TongYongManager(act, icb5,yp);
//		tym.start();
//		
//		TotalMoneyParam tp=new TotalMoneyParam();
//    	tp.memberID=BeikBankApplication.getUserid();
//    	UserCapitalInfoManager ucm=new UserCapitalInfoManager(act, icb6, tp);
//    	ucm.start();
////		tms.add(usm);
////		//tms.add(tmm);
////		//tms.add(mi);
////		tms.add(nm);
////		tms.start();
//    	
//    	//得到几张可用
//    			HongbaoParam hp=new HongbaoParam();
//    			hp.userId=BeikBankApplication.getUserid();
//    			if(hp!=null&&hp.userId!=null&&!hp.userId.equals(""))
//    			{
//    			ManagerParam mp=new ManagerParam();
//    			mp.isShowDialog=false;
//    			mp.isShowMsg=false;
//    			TongYongManager tym2=new TongYongManager(act, icb9,hp,mp);
//    			tym2.start();
//    			}
//	}
//	ICallBack icb5=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				Yuer_data yd=(Yuer_data) obj;
//				ye=yd.data;
//				synchronized (act) 
//				{
//					
//					
//					String s3=QianbaoActivity1.countTotal(ye);
//					money=NumberManager.getAddString(money,s3,2);	
//					money_count++;
//					if(money_count==3)
//					{
//						magictextview_totalcapital.setText(NumberManager.getGeshiHua(money, 2));
//						isaddData=true;
//						doData(money);
//					}
//					
//				}
//			}
//		}
//	};
//	
//	/**
//	 * 没有网络时缓存数据
//	 */
//	private void doData(String money)
//	{
//		
//		if(uc!=null)
//		{
//			uc.allMoney=money;
//			ZhiChanDao.set(uc);
//		}
//		
//	}
//	
//	ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(prs!=null)
//			{
//			    prs.onPullDownRefreshComplete();
//			}
//		}
//	};
//  ICallBack icb3=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				act.showNotice(obj);
//			}
//		}
//	};
//	/**
//	 * 初始化背景颜色
//	 */
//	public void init1()
//	{   
//		if(ll1!=null)
//		{
//		 ll1.setBackgroundColor(0xfff9465b);
//		}
//	}
//	
//	Runnable run=new Runnable() {
//		
//		@Override
//		public void run() {
//			
//			hongbaoAni();
//			if(ll_yhq.getVisibility()==View.VISIBLE)
//			{
//			   postDelayed(run,5000);
//			}
//			
//		}
//	};
////	Hongbao_data hd;
////	ICallBack icb8=new ICallBack() {
////		
////		@Override
////		public void back(Object obj) {
////			if(obj!=null)
////			{
////				hd=(Hongbao_data) obj;
////				if(hd.data.size()>0)
////				{
////					postDelayed(run,5000);
////				}
////			}
////			
////		}
////	};
//	public void init(Activity act1)
//	{   
//		
//		
//		
//		
//		act=(HomeActivity2) act1;
//		LayoutInflater li=act.getLayoutInflater();
//		LinearLayout ll=new LinearLayout(act);
//		View view = li.inflate(R.layout.page_wealth11,ll,false);
//		View view2= li.inflate(R.layout.page_wealth3,ll,false);
//		prs=(PullToRefreshScrollView) view.findViewById(R.id.prs);
//		prs.setPullLoadEnabled(false);
//		prs.setScrollLoadEnabled(false);
//		prs.getRefreshableView();
//		sv=prs.getRefreshableView();
//		sv.addView(view2);
//		ll1=(LinearLayout) view.findViewById(R.id.ll1);
//		bu_bu1=(Button) view.findViewById(R.id.bu_bu1);
//		bu_bu2=(Button) view.findViewById(R.id.bu_bu2);
//		ll_yhq=(LinearLayout) view.findViewById(R.id.ll_yhq);
//		yhq=(ImageView) view.findViewById(R.id.yhq);
//		//ll_yhq.setOnClickListener(this);
//		
//		ll=(LinearLayout) view.findViewById(R.id.linear_total_capital);
//		iv_iv1=(ImageView) view.findViewById(R.id.iv_iv1);
//		
//		//relative_transaction_records=(RelativeLayout)view.findViewById(R.id.relative_transaction_records);
//		//relative_transaction_records.setOnClickListener(this);
//		magictextview_yesterdayincome=(TextView)view.findViewById(R.id.magictextview_yesterdayincome);//昨日收益
//		magictextview_totalcapital=(TextView)view.findViewById(R.id.magictextview_totalcapital);//总资产
//		//textview_interestM=(TextView)view.findViewById(R.id.textview_interestM);//近一月收益
//		textview_interestW=(TextView)view.findViewById(R.id.textview_interestW);//近一周收益
//		textview_interestT=(TextView)view.findViewById(R.id.textview_interestT);//累计收益
//		//textview_rate=(TextView)view.findViewById(R.id.textview_rate);
//		
//		magictextview_yesterdayincome.setText("0.0000");
//		magictextview_totalcapital.setText("0.00");
//		
//		
//		
//		//textview_interestM.setText("0.00");
//		textview_interestW.setText("0.00");
//		textview_interestT.setText("0.00");
//		
////		Typeface tf = Typeface.createFromAsset(act.getAssets(), "Helvetica2.ttf");  
////		if(tf!=null)
////		{
////			magictextview_yesterdayincome.setTypeface(tf);
////			magictextview_totalcapital.setTypeface(tf);
////		}
//		
//		
//		ll_all=(LinearLayout) view.findViewById(R.id.ll_all);
//		ll_week=(LinearLayout) view.findViewById(R.id.ll_week);
//		
//		iv_iv1.setOnClickListener(this);
//		ll_all.setOnClickListener(this);
//		ll_week.setOnClickListener(this);
//		bu_bu1.setOnClickListener(this);
//		bu_bu2.setOnClickListener(this);
//		ll.setOnClickListener(this);
////		String rate=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.PRODUCT_RATE);
////		//BigDecimal bd_rate=new BigDecimal(rate);
////		//bd_rate=bd_rate.setScale(2,BigDecimal.ROUND_UP);
////		if(!"".equals(rate))
////		{
////			textview_rate.setText(rate+"%");
////		}
//	   // userInfo=UserInfoDao.getInstance(act).getUserInfo();
////	    userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
////	    if( userInfo!=null)
////	    {
////	    	BeikBankApi.getInstance().getIncomeInfo(act,userInfo.getId(), getIncomeInfoHandler);
////			BeikBankApi.getInstance().getTotalCapitalInfo(act,userInfo.getId(), getTotalCapitalInfoHandler);
////	    }
//		addView(view);
//		addData();
//		initOn();
//	}
//	
//	/**
//	 * 用户资产信息
//	 */
//	public UserCapital2 uc;
//	public  ICallBack icb4=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			 
//				synchronized (act) 
//				{
//					if(obj!=null)
//					{
//						UserCapital2_data ucd=(UserCapital2_data) obj;
//					    uc=ucd.data;
//					}
//					else
//					{
//						uc=ZhiChanDao.get();
//						money=uc.allMoney;
//						magictextview_totalcapital.setText(NumberManager.getGeshiHua(money, 2));
//				    	TextUtil.setSize(act,magictextview_totalcapital,NumberManager.getGeshiHua(money, 2));
//				    	isaddData=true;
//					}
//				    
//					if(uc==null)
//					{
//						return;
//					}
//			      money_count++;
//			      //String s1=NumberManager.getAddString(uc.bondAmount,uc.fundAmount,2);
//			      money=NumberManager.getAddString(money,uc.fundAmount,2);
//			      if(money_count==3)
//			      {
//			    	  magictextview_totalcapital.setText(NumberManager.getGeshiHua(money, 2));
//			    	  TextUtil.setSize(act,magictextview_totalcapital,NumberManager.getGeshiHua(money, 2));
//			    	  isaddData=true;
//			    	  doData(money);
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
//				magictextview_yesterdayincome.setText(s2);
//				
////				String s2=NumberManager.getString(mi.interestM,"1",2);
////				//BigDecimal bd_income_m=new BigDecimal(mi.interestM);
////				//bd_income_m=bd_income_m.setScale(2,BigDecimal.ROUND_UP);
////				textview_interestM.setText(s2);
//				
//				String s3=NumberManager.getString(uc.bondInterestT,"1",2);
//				//BigDecimal bd_income_w=new BigDecimal(mi.interestW);
//				//bd_income_w=bd_income_w.setScale(2,BigDecimal.ROUND_UP);
//				textview_interestW.setText(NumberManager.getGeshiHua(s3,2));
//				//活期累计收益
//				String s4=NumberManager.getString(uc.fundInterestT,"1",2);
//				//BigDecimal bd_income_t=new BigDecimal(mi.interestT);
//				//bd_income_t=bd_income_t.setScale(2,BigDecimal.ROUND_UP);
//				textview_interestT.setText(NumberManager.getGeshiHua(s4,2));
//				firstLogin();
//				}
//				
//			
//			if(intozc)
//			{
//			  intoZhancan();
//			}
//		}
//	};
//	 public ICallBack icb1=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			   //new MoneyInfoManager(act, icb2).start();
//			   if(obj!=null)
//			   {
//				   TotalMoney_data tm=(TotalMoney_data) obj;
//			       String s1=NumberManager.getString(tm.data.totalAmount,"1",2);
//			       magictextview_totalcapital.setText(s1);
//			       TextUtil.setSize(act,magictextview_totalcapital,NumberManager.getGeshiHua(money, 2));
//			       
//                 
//				
//			   }
//			  // BigDecimal bd_totalcapital=new BigDecimal(tm.data.totalAmount);
//			 //  bd_totalcapital=bd_totalcapital.setScale(2,BigDecimal.ROUND_UP);
//		}
//	};
//	 public ICallBack icb2=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
////				if(prs!=null)
////				{
////				  prs.onPullDownRefreshComplete();
////				}
//				if(obj!=null)
//				{
//					MoneyInfo_data mid=(MoneyInfo_data) obj;
//					MoneyInfo mi=mid.data;
//					
//					String s1=NumberManager.getString(mi.interestY,"1",4);
//					//BigDecimal bd_income_y=new BigDecimal(mi.interestY);
//					//bd_income_y=bd_income_y.setScale(4,BigDecimal.ROUND_UP);
//					magictextview_yesterdayincome.setText(s1);
//					
////					String s2=NumberManager.getString(mi.interestM,"1",2);
////					//BigDecimal bd_income_m=new BigDecimal(mi.interestM);
////					//bd_income_m=bd_income_m.setScale(2,BigDecimal.ROUND_UP);
////					textview_interestM.setText(s2);
//					
//					String s3=NumberManager.getString(mi.interestW,"1",2);
//					//BigDecimal bd_income_w=new BigDecimal(mi.interestW);
//					//bd_income_w=bd_income_w.setScale(2,BigDecimal.ROUND_UP);
//					textview_interestW.setText(s3);
//					
//					String s4=NumberManager.getString(mi.interestT,"1",2);
//					//BigDecimal bd_income_t=new BigDecimal(mi.interestT);
//					//bd_income_t=bd_income_t.setScale(2,BigDecimal.ROUND_UP);
//					textview_interestT.setText(s4);
//					firstLogin();
//				}
//				
//			}
//		};
//		
//	    /**
//		 * 第一次登录的时候消息提示
//		 */
//	    private void firstLogin()
//	    {  
//	    	 SharedPref sp=BeikBankApplication.getInstance().getSharedPref();
//	    	boolean first_login=sp.getSharePrefBoolean(BeikBankConstant.FIRST_LOGIN,false);
//	        if(!first_login)
//	        {
//	        	sp.putSharePrefBoolean(BeikBankConstant.FIRST_LOGIN,true);
//	        	FirstLoginWindows.addView(act,R.layout.first_login_msg);
//	        }
//	    }
//	 public void initOn()
//	    {
//	    	prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//
//				@Override
//				public void onPullDownToRefresh(
//						PullToRefreshBase<ScrollView> refreshView) 
//				{
////					BeikBankApi.getInstance().getIncomeInfo(act,userInfo.getId(), getIncomeInfoHandler);
////					BeikBankApi.getInstance().getTotalCapitalInfo(act,userInfo.getId(), getTotalCapitalInfoHandler);
//			
//					//new TotalMoneyManager(act, icb1).start();
//					updateView();
//				}
//
//				@Override
//				public void onPullUpToRefresh(
//						PullToRefreshBase<ScrollView> refreshView) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//	    	
//	    }
////	 JsonHttpResponseHandler getTotalCapitalInfoHandler = new JsonHttpResponseHandler(){
////
////			@Override
////			public void onStart() {
////				super.onStart();	
////			}
////
////			@Override
////			public void onFinish() {
////				super.onFinish();
////				prs.onPullDownRefreshComplete();
////			}
////
////			@Override
////			public void onFailure(Throwable error, String content) {
////				Utils.log(TAG, "onFailure()"+content);
////				prs.onPullDownRefreshComplete();
////			}
////
////			@Override
////			public void onSuccess(Response response) {
////				Utils.log(TAG, "onSuccess()"+response.getResponseString());	
////				Gson gson=new Gson();
////				String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////				if(result.equals("success")){
////					TotalCapitalInfo totalCapitalInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
////							BeikBankConstant.TYPE_JSONDATA), TotalCapitalInfo.class);
////					BigDecimal bd_totalcapital=new BigDecimal(totalCapitalInfo.getTotalAmount());
////					bd_totalcapital=bd_totalcapital.setScale(2,BigDecimal.ROUND_UP);
////					magictextview_totalcapital.setText(bd_totalcapital.toString());
////				}
////
////			}
////
////		};
//		
////		JsonHttpResponseHandler getIncomeInfoHandler = new JsonHttpResponseHandler(){
////
////			@Override
////			public void onStart() {
////				super.onStart();	
////			}
////
////			@Override
////			public void onFinish() {
////				super.onFinish();
////				prs.onPullDownRefreshComplete();
////			}
////
////			@Override
////			public void onFailure(Throwable error, String content) {
////				Utils.log(TAG, "onFailure()"+content);			
////			}
////
////			@Override
////			public void onSuccess(Response response) {
////				Utils.log(TAG, "onSuccess()"+response.getResponseString());	
////				Gson gson=new Gson();
////				String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////				if(result.equals("success")){
////					IncomeInfo incomeInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
////							BeikBankConstant.TYPE_JSONDATA), IncomeInfo.class);
////					
////					BigDecimal bd_income_y=new BigDecimal(incomeInfo.getInterestY());
////					bd_income_y=bd_income_y.setScale(4,BigDecimal.ROUND_UP);
////					magictextview_yesterdayincome.setText(bd_income_y.toString());
////					BigDecimal bd_income_m=new BigDecimal(incomeInfo.getInterestM());
////					bd_income_m=bd_income_m.setScale(2,BigDecimal.ROUND_UP);
////					textview_interestM.setText(bd_income_m.toString());
////					BigDecimal bd_income_w=new BigDecimal(incomeInfo.getInterestW());
////					bd_income_w=bd_income_w.setScale(2,BigDecimal.ROUND_UP);
////					textview_interestW.setText(bd_income_w.toString());
////					BigDecimal bd_income_t=new BigDecimal(incomeInfo.getInterestT());
////					bd_income_t=bd_income_t.setScale(2,BigDecimal.ROUND_UP);
////					textview_interestT.setText(bd_income_t.toString());
////					
////				}
////
////			}
////
////		};
//	 /**
//	  * 收益对话框
//	  */
//	 Dialog dialog1;
//	 /**
//	  * 显示提示对话框
//	  */
//	 private void showDialog()
//	 {   
//		 
//		
//		 
//		 LinearLayout ll=new LinearLayout(act);
//		   View v=LayoutInflater.from(act).inflate(
//	  			   R.layout.redeem_dialog5,ll,false);
//		   
//	    	//标题
//	    	TextView tv1=(TextView) v.findViewById(R.id.tv_tv1);
//	    	//内容
//	    	TextView tv2=(TextView) v.findViewById(R.id.tv_tv2);
//	    	
//	    	tv1.setText(act.getString(R.string.page6));
//	    	tv2.setText(act.getString(R.string.page7));
//	    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
//	    	tv5.setText(act.getString(R.string.page8));
//	    	tv5.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					dialog1.dismiss();
//				}
//			});
//	    	//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_finrst_bank,true);
//	        dialog1=DialogManager.getDiaolg1(act, v);
//	    	dialog1.show();
//	 }
//	 private void intoZhancan2()
//	 {
//		  TotalMoneyParam tmp=new TotalMoneyParam();
//		  tmp.memberID=BeikBankApplication.getUserid();
//		  intozc=true;
//		  UserCapital2Manager usm=new UserCapital2Manager(act,icb4,tmp);
//		  usm.start();
//		  
//		  //TongYongManager tym=new TongYongManager(act, icb4,tmp);
//		  //tym.start();
//		  
//	 }
//	 private boolean intozc=false;
//	 public void intoZhancan()
//	 {    
//		 
//		  if(uc!=null)
//			{
//			  Intent 	intent=new Intent(act,ZhiChanActivity2.class);
//			  intent.putExtra(ZhiChanActivity2.index,uc);
//			  if(act.vpl.page2!=null&&act.vpl.page2.fundInfo!=null)
//			  {
//				  intent.putExtra(ZhiChanActivity2.index2,act.vpl.page2.fundInfo);
//			  }
//			  //传递总资产
//			  intent.putExtra("total_money",magictextview_totalcapital.getText().toString());
//			  act.startActivity(intent);
//			}
//	 }
//	   ICallBack icb7=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj==null)
//			{
//				Intent intent=new Intent(act,QianbaoActivity8.class);
//				act.startActivity(intent);
//			}
//			
//		}
//	};
//		@Override
//		public void onClick(View v) {
//			Intent intent=new Intent(act,UserRecordActivity.class);
//			String s4=NumberManager.getString(uc.fundInterestT,"1",4);
//			if(!isaddData)
//			{
//				return;
//			}
//			switch(v.getId()){
//			case R.id.iv_iv1:
//				showDialog();
//				break;
//			case R.id.bu_bu1:
//				startAimActivity(TransactionListActivity2.class);
//				break;
//			case R.id.bu_bu2:
//				//intoZhancan2();
//				LiuChenManager.StartNext(act,icb7);
//				break;
//			case R.id.linear_total_capital:
//				//intoZhancan2();
//				intoZhancan();
//				break;
//			case R.id.ll_all:
//				intent.putExtra("index","1");
//				intent.putExtra("money",s4);
//
//				act.startActivity(intent);
//				break;
//			case R.id.ll_week:
////				intent.putExtra("index","2");
////				intent.putExtra("money",s4);
//				intent.setClass(act,UserRecordActivity2.class);
//				intent.putExtra("money",uc.bondInterestT);
//				act.startActivity(intent);
//				break;
//			case R.id.ll_yhq:
//				
//				//startAimActivity(YouHuiQuanActivity.class);
//				break;
//				
//			}
//			
//		}
//		/*红包动画
//		 * 
//		 */
//		private void hongbaoAni()
//		{
//			Animation operatingAnim = AnimationUtils.loadAnimation(act, R.anim.hongbao);  
//			LinearInterpolator lin = new LinearInterpolator();  
//			operatingAnim.setInterpolator(lin);
//			yhq.startAnimation(operatingAnim);
//		}
//		protected <T> void startAimActivity(final Class<T> pActClassName) {
//			Intent _Intent = new Intent();
//			_Intent.setClass(act, pActClassName);
//			act.startActivity(_Intent);
//		}
//
//}
