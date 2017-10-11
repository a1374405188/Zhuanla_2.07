//package com.beikbank.android.widget;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.beikbank.android.R;
//import com.beikbank.android.activity.BandTestActivity;
//import com.beikbank.android.activity.BankBindActivity;
//import com.beikbank.android.activity.BankBindActivity2;
//import com.beikbank.android.activity.HomeActivity2;
//import com.beikbank.android.activity.HuodongActivity2;
//import com.beikbank.android.activity.HuoqiActivity;
//import com.beikbank.android.activity.HuoqiDetailActivity;
//import com.beikbank.android.activity.HuoqiReturnIActivity;
//import com.beikbank.android.activity.LoginRegActivity;
//import com.beikbank.android.activity.ProjectListActivity;
//import com.beikbank.android.activity.PurchaseActivity;
//import com.beikbank.android.activity.RealnameActivity;
//import com.beikbank.android.activity.RedeemActivity;
//import com.beikbank.android.activity.RiskControlActivity;
//import com.beikbank.android.activity.TransactionPwdSetActivity;
//import com.beikbank.android.activity.ZhiChanActivity;
//import com.beikbank.android.activity.help.LiuChenManager;
//import com.beikbank.android.adapter.ZhiChanAdapter;
//import com.beikbank.android.animation.AnimationManager;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.conmon.MessageManger;
//import com.beikbank.android.dao.HuoqiDataDao;
//import com.beikbank.android.data.Biaoshi;
//import com.beikbank.android.data.Biaoshi_data;
//import com.beikbank.android.data.CheckBank;
//import com.beikbank.android.data.CheckBank_data;
//import com.beikbank.android.data.FundInfo;
//import com.beikbank.android.data.IsCheckBank;
//import com.beikbank.android.data.IsCheckBank_data;
//import com.beikbank.android.data.TotalMoney_data;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.dataparam.BiaoshiParam;
//import com.beikbank.android.dataparam.CheckBankParam;
//import com.beikbank.android.fragment.BeikBankApplication;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.net.HandlerBase;
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.ImageUrl;
//import com.beikbank.android.net.ManagerParam;
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
//import com.beikbank.android.utils.DialogManager;
//import com.beikbank.android.utils.NumberManager;
//import com.beikbank.android.utils.Utils;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.Handler;
//import android.text.InputFilter;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
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
//public class Page2 extends LinearLayout implements OnClickListener{
//	private LinearLayout linear_projects,linear_riskcontrol;
//	private NumberProgressBar numberprogress_rate;
//	private MagicTextView magictextview_annual_rate;
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
//	  * 产品名称
//	*/
//    TextView tv_tv0;
//	/**
//	 * 年化收益率
//	 */
//	MagicTextView tv_tv1;
//	/**
//	 * 加真年化收益率
//	 */
//	TextView tv_tv2;
//	/**
//	 * 万份收益
//	 */
//	TextView tv_tv3;
//	/**
//	 * 累计投资元
//	 */
//	TextView tv_tv4;
//	/**
//	 * 累计收益元
//	 */
//	TextView tv_tv5;
//	/**
//	 * 已投人数
//	 */
//	TextView tv_tv6;
//	/**
//	 * 分散债权
//	 */
//	TextView tv_tv7;
//	/**
//	 * 活期详情
//	 */
//	LinearLayout ll_ll1;
//	/**
//	 * 债权
//	 */
//	LinearLayout ll_ll2;
//	
//	RelativeLayout rl0;
//	/**
//	 * 计算器
//	 */
//	ImageView iv;
//    /**
//     * 取现
//     */
//    Button bu_bu1;
//    /**
//     * 购买
//     */
//    Button bu_bu2;
//    /**
//     * 赎回
//     */
//    Button bu_bu3;
//    /**
//     * 计算收益收益
//     */
//    TextView tv_tv9;
//    /**
//     * 计算收益时间
//     */
//    TextView tv_tv10;
//    /**
//     * 计算收益金额
//     */
//    EditText et_et1;
//    /**
//     * 理财天数
//     */
//    EditText et_et2;
//    /**
//     * 加增图片
//     */
//    ImageView iv5;
//	public Page2(Context context, AttributeSet attrs) {
//		super(context, attrs);
//	
//	}
//	public Page2(Context context) {
//		super(context);
//
//	}
//	ICallBack next;
//	public void updateView(ICallBack next)
//	{   
////		this.next=next;
////		new FundInfoManager(act, icb).start();
//		addData();
//		act.addNotice();
//	}
//	public void addData()
//	{
//		new FundInfoManager(act, icb).start();
//		BiaoshiParam bp=new BiaoshiParam();
//		bp.type="1";
//		ManagerParam mp=new ManagerParam();
//		mp.isShowMsg=false;
//		mp.isShowDialog=false;
//		TongYongManager tym2=new TongYongManager(act, icb7,bp,mp);
//		tym2.start();
//	}
//	
//	/**
//	 * 加息标识回调
//	 */
//	ICallBack icb7=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				Biaoshi_data bsd=(Biaoshi_data)obj;
//				if(bsd!=null)
//				{
//				  final Biaoshi bs=bsd.data;
//				  if(bs!=null&&bs.icon!=null&&!bs.icon.equals(""))
//				  { 
//					
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
//	Dialog dialog4;
//	/**
//	 * 计算器计算按钮
//	 */
//	Button bu_but3;
//	  /**
//     * 显示收益计算器
//     */
//   private void showCountDialog()
//   {
//	    LinearLayout ll=new LinearLayout(act);
//	    View v=LayoutInflater.from(act).inflate(
//  			   R.layout.count_shouyi2,ll,false);
//	    tv_tv9=(TextView) v.findViewById(R.id.tv_tv1);
//	    //tv_tv10=(TextView) v.findViewById(R.id.tv_tv2);
//	    //tv_tv10.setText("40");
////	    if(SystemConfig.isDebug())
////	    {
////	    	 tv_tv10.setText("30");
////	    }
//	    et_et1=(EditText)v.findViewById(R.id.et_et1);
//	    et_et2=(EditText)v.findViewById(R.id.et_et2);
//	    et_et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); 
//	    et_et2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); 
//	    bu_but3=(Button) v.findViewById(R.id.bu_bu1);
//	    bu_but3.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//			  String s=et_et1.getText().toString();
//			  String s2=et_et2.getText().toString();
//			  tv_tv9.setText(ZhiChanAdapter.getShouyi(s,s2,fundInfo.rate,"0"));
//			}
//		});
//	    
//        dialog4=DialogManager.getDiaolg1(act, v);
//        dialog4.setCanceledOnTouchOutside(true);
//    	dialog4.show();
//    	et_et2.setFocusable(true);
//   	    et_et2.setFocusableInTouchMode(true);
//   	    et_et2.requestFocus();
//    	Runnable run=new Runnable() {
//			
//			@Override
//			public void run() {	
//				InputMethodManager inputManager = (InputMethodManager)et_et2.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//				inputManager.showSoftInput(et_et2, 0);
//			}
//		};
//        Handler handler=new Handler();
//        handler.postDelayed(run, 500);
//   }
//	private void init2(View view)
//	{   
////		Typeface tf = Typeface.createFromAsset(act.getAssets(), "Helvetica2.ttf");  
//
//		iv5=(ImageView)view.findViewById(R.id.iv5);
//		tv_tv0=(TextView) view.findViewById(R.id.tv_tv0);
//		tv_tv1=(MagicTextView) view.findViewById(R.id.tv_tv1);
//		tv_tv2=(TextView) view.findViewById(R.id.tv_tv2);
//		tv_tv3=(TextView) view.findViewById(R.id.tv_tv3);
//		tv_tv4=(TextView) view.findViewById(R.id.tv_tv4);
//		iv=(ImageView)view.findViewById(R.id.iv);
//		iv.setOnClickListener(this);
////		if(tf!=null)
////		{
////			tv_tv1.setTypeface(tf);
////		}
//		
//		
//		tv_tv5=(TextView) view.findViewById(R.id.tv_tv5);
//		tv_tv6=(TextView) view.findViewById(R.id.tv_tv6);
//		tv_tv7=(TextView) view.findViewById(R.id.tv_tv7);
//		
//		//bu_bu1=(Button) view.findViewById(R.id.bu_bu1);
//		bu_bu2=(Button) view.findViewById(R.id.bu_bu2);
//		bu_bu3=(Button) view.findViewById(R.id.bu_bu3);
//		//rl0=(RelativeLayout) view.findViewById(R.id.rl0);
//		ll_ll1= (LinearLayout) view.findViewById(R.id.ll_ll1);
//		ll_ll2= (LinearLayout) view.findViewById(R.id.ll_ll2);
//		if(ll_ll1!=null)
//		{
//			ll_ll1.setOnClickListener(this);
//		}
//		
//		if(ll_ll2!=null)
//		{
//			ll_ll2.setOnClickListener(this);
//		}
//		
//		//bu_bu1.setOnClickListener(this);
//		bu_bu2.setOnClickListener(this);
//		bu_bu3.setOnClickListener(this);
//	}
//    public void init(Activity act1)
//    {   
//    	act=(HomeActivity2)act1;
//		LayoutInflater li=act.getLayoutInflater();
//		LinearLayout ll=new LinearLayout(act);
//		View view = li.inflate(R.layout.page_wealth11,ll,false);
//		View view2= li.inflate(R.layout.page_product3,ll,false);
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
////		linear_projects=(LinearLayout)view2.findViewById(R.id.linear_projects);
////		linear_projects.setOnClickListener(this);
////		linear_riskcontrol=(LinearLayout)view2.findViewById(R.id.linear_riskcontrol);
////		linear_riskcontrol.setOnClickListener(this);
////		numberprogress_rate=(NumberProgressBar)view2.findViewById(R.id.numberprogress_rate);
////		magictextview_annual_rate=(MagicTextView)view2.findViewById(R.id.magictextview_annual_rate);
////		textview_product_incomepercent=(TextView)view2.findViewById(R.id.textview_product_incomepercent);
////		textview_totalInvestment=(TextView)view2.findViewById(R.id.textview_totalInvestment);//累计投资
////		textview_totalIncome=(TextView)view2.findViewById(R.id.textview_totalIncome);//累计收益
////		textview_totalInvestors=(TextView)view2.findViewById(R.id.textview_totalInvestors);//投资人数
////		textview_totalProjects=(TextView)view2.findViewById(R.id.textview_totalProjects);//投资项目
////		textview_wanfen=(TextView)view2.findViewById(R.id.textview_wanfen);//万份收益
//		mcontext=act;
//
//		//BeikBankApi.getInstance().getProductFundInfo(act, getProductFundInfoHandler);
//		addView(view);
//		initOn();
//		addData();
//    }
//    protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(act, pActClassName);
//		act.startActivity(_Intent);
//	}
//	int index=-1;
////	JsonHttpResponseHandler getProductFundInfoHandler = new JsonHttpResponseHandler(){
////
////		@Override
////		public void onStart() {
////			super.onStart();
////			if(index!=1)
////			{
////				dialog=Utils.createPorgressDialogNoCancel(act, null);
////				dialog.show();
////			}
////			
////		}
////
////		@Override
////		public void onFinish() {
////			super.onFinish();
////			prs.onPullDownRefreshComplete();
////			
////			if(index!=1)
////			{
////			 dialog.dismiss();
////			}
////			index=-1;
////		}
////
////		@Override
////		public void onFailure(Throwable error, String content) {
////		    //AnimationManager.performToast(act,"网络异常");
////		    MessageManger.showMeg(act,act.getString(R.string.no_net),Toast.LENGTH_SHORT);
////		    if(index!=1)
////		    {
////		    	dialog.dismiss();
////		    }
////		    prs.onPullDownRefreshComplete();
////		    index=-1;
////				
////		}
////
////		@Override
////		public void onSuccess(Response response) {
////		
////			Gson gson=new Gson();
////			ArrayList<FundInfo> fundInfoList=gson.fromJson(Utils.getJsonResult(
////					response.getResponseString(), BeikBankConstant.TYPE_JSONDATA), new TypeToken<List<FundInfo>>(){  
////			}.getType());
////			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////			if(result.equals("success")){
////				if(fundInfoList!=null){
////					FundInfo fundInfo=fundInfoList.get(0);
////					BeikBankApplication.getInstance().getSharedPref().putSharePrefString(BeikBankConstant.FUNDINFO_SID,fundInfo.sid);
////					
////					String rate_info=fundInfo.getRate().equals("")?"0":fundInfo.getRate();
////					
////					BigDecimal bd_rate=new BigDecimal(Double.parseDouble(rate_info)*100);
////					bd_rate=bd_rate.setScale(2,BigDecimal.ROUND_UP);
////					//double bd_rate=Double.parseDouble(rate_info);
////					
////					
////					magictextview_annual_rate.setValue(bd_rate.doubleValue());
////					mHandler.sendEmptyMessageDelayed(0, 0);
////					timersch(980);
////					//BeikBankApplication.mSharedPref.putSharePrefFloat(BeikBankConstant.PRODUCT_RATE, 
////					//		Float.parseFloat(rate_info));
////					
////					BeikBankApplication.mSharedPref.putSharePrefFloat(BeikBankConstant.PRODUCT_RATE, 
////							bd_rate.floatValue());
////					
////					//ViewPageFragment.sid=fundInfo.getSid();
////					sid=fundInfo.getSid();
////					textview_product_incomepercent.setText(bd_rate.toString()+"%");
////					
////			
////					BigDecimal bd_totalinvestment=new BigDecimal(Double.parseDouble(fundInfo.getTotalInvestment()));
////					bd_totalinvestment=bd_totalinvestment.setScale(2,BigDecimal.ROUND_UP);
////					
////					NumberFormat usFormat0 = NumberFormat.getIntegerInstance(Locale.US);
////					usFormat0.setMinimumFractionDigits(2);
////					String s0=usFormat0.format(bd_totalinvestment.doubleValue());
////					
////					
////					textview_totalInvestment.setText(s0+"元");
////					BigDecimal bd_totalincome=null;
////					if("".equals(fundInfo.getTotalIncome()))
////					{
////					 
////					   bd_totalincome=new BigDecimal(Double.parseDouble("1"));
////					}
////					else
////					{
////						 bd_totalincome=new BigDecimal(Double.parseDouble(fundInfo.getTotalIncome()));
////					}
////					bd_totalincome=bd_totalincome.setScale(2,BigDecimal.ROUND_UP);
////					
////					NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
////					usFormat.setMinimumFractionDigits(2);
////					String s=usFormat.format(bd_totalincome.doubleValue());
////					
////					textview_totalIncome.setText(s+"元");
////					textview_totalInvestors.setText(fundInfo.getTotalInvestors()+"人");
////					textview_totalProjects.setText(fundInfo.getTotalProjects()+"个");
////					
////					Double rate_wanfen=Double.parseDouble(rate_info)*10000/365;
////					BigDecimal bd_wanfen=new BigDecimal(rate_wanfen);
////					bd_wanfen=bd_wanfen.setScale(2,BigDecimal.ROUND_UP);
////					
////					//double bd_wanfen=(bd_rate)*10000/365;
////					textview_wanfen.setText(bd_wanfen.toString());
////				}
////			}
////
////		}
////
////	};
//
//	public Handler mHandler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			if(tv_tv1!=null)
//			{
//				tv_tv1.doScroll(0);
//			}
//			
//		};
//	};
//	/**
//	 * 活期产品
//	 */
//	public FundInfo fundInfo;
//	   public ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			prs.onPullDownRefreshComplete();
//			
//			    
//			    if(obj!=null&&obj instanceof FundInfo)
//			    {
//			    	fundInfo=(FundInfo) obj;
//			    	HuoqiDataDao.set(fundInfo);
//			    	
//			    }
//			    //没有正常请求的时候从数据库中请缓存
//			    else
//			    {
//			    	fundInfo=HuoqiDataDao.get();
//			    	
//			    }
//			    if(fundInfo==null)
//			    {
//			    	return;
//			    }
//			    
//			    
//			    
//				BeikBankApplication.getInstance().getSharedPref().putSharePrefString(BeikBankConstant.FUNDINFO_SID,fundInfo.sid);
//				
//				String rate_info=fundInfo.getRate().equals("")?"0":fundInfo.getRate();
//				
//				BigDecimal bd_rate=new BigDecimal(Double.parseDouble(rate_info)*100);
//				bd_rate=bd_rate.setScale(2,BigDecimal.ROUND_UP);
//				//double bd_rate=Double.parseDouble(rate_info);
//				String s1=NumberManager.getString(rate_info,"100",2);
//				if(tv_tv1!=null)
//				{
//					tv_tv1.setValue(Double.parseDouble(s1));
//				}
//				
//				mHandler.sendEmptyMessageDelayed(0, 0);
//				
//				tv_tv0.setText(fundInfo.name);
//				//timersch(980);
//				//BeikBankApplication.mSharedPref.putSharePrefFloat(BeikBankConstant.PRODUCT_RATE, 
//				//		Float.parseFloat(rate_info));
//				
//				//BeikBankApplication.mSharedPref.putSharePrefFloat(BeikBankConstant.PRODUCT_RATE, 
//				//		bd_rate.floatValue());
//				
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.PRODUCT_RATE, 
//								s1);
//				//ViewPageFragment.sid=fundInfo.getSid();
//				sid=fundInfo.getSid();
//				//textview_product_incomepercent.setText(s1+"%");
//				
//		        //累计投资
//				
////				BigDecimal bd_totalinvestment=new BigDecimal(Double.parseDouble(fundInfo.getTotalInvestment()));
////				bd_totalinvestment=bd_totalinvestment.setScale(2,BigDecimal.ROUND_UP);
////				
////				NumberFormat usFormat0 = NumberFormat.getIntegerInstance(Locale.US);
////				usFormat0.setMinimumFractionDigits(2);
//				//String s0=usFormat0.format(bd_totalinvestment.doubleValue());
//				String s0=NumberManager.getString(fundInfo.totalInvestment,"1",0);
//				//NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
//				//usFormat.setMinimumFractionDigits(2);
//				//s0=usFormat.format(Double.parseDouble(s0));
//				if(tv_tv4!=null)
//				{
//				tv_tv4.setText(NumberManager.getGeshiHua(s0, 0));
//				}
//				//累计收益
////				BigDecimal bd_totalincome=null;
////				if("".equals(fundInfo.getTotalIncome()))
////				{
////				 
////				   bd_totalincome=new BigDecimal(Double.parseDouble("1"));
////				}
////				else
////				{
////					 bd_totalincome=new BigDecimal(Double.parseDouble(fundInfo.getTotalIncome()));
////				}
////				bd_totalincome=bd_totalincome.setScale(2,BigDecimal.ROUND_UP);
////				
////				NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
////				usFormat.setMinimumFractionDigits(2);
//				//String s=usFormat.format(bd_totalincome.doubleValue());
//				String s=NumberManager.getString(fundInfo.totalIncome,"1",0);
//				//NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
//				//usFormat.setMinimumFractionDigits(2);
//				//s=usFormat.format(Double.parseDouble(s));
//				if(tv_tv5!=null)
//				{
//				  tv_tv5.setText(NumberManager.getGeshiHua(s, 0));
//				}
//				
//				String s6=NumberManager.getString(fundInfo.totalInvestors,"1",0);
//				tv_tv6.setText(s6);
//				s6=NumberManager.getString(fundInfo.getTotalProjects(),"1",0);
//				tv_tv7.setText(s6);
//				
//				//万份收益
////				Double rate_wanfen=Double.parseDouble(rate_info)*10000/365;
////				BigDecimal bd_wanfen=new BigDecimal(rate_wanfen);
////				bd_wanfen=bd_wanfen.setScale(2,BigDecimal.ROUND_UP);
//				
//				String s3=NumberManager.getString(fundInfo.getRate(),"10000",4);
//				s3=NumberManager.getDivString(s3,"365",2);
//				//double bd_wanfen=(bd_rate)*10000/365;
//				if(tv_tv3!=null)
//				{
//					tv_tv3.setText(s3);
//				}
//				
//			
//				
////				if(act.vpl.page1!=null)
////				{
////					//act.vpl.page1.textview_rate.setText(s1+"%");
////				}
//			
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
//	Intent intent=new Intent();
//	@Override
//	public void onClick(View v) {
//		//intent.putExtra(RedeemActivity.index,fundInfo);
//		
//		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		switch(v.getId()){
//		case R.id.bu_bu1:
//			if(!do_success){
//			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
//					2);
//			startAimActivity(LoginRegActivity.class);
//		}else{
//
//			toTntent(0);
//		}
//			break;
//			//购买
//		case R.id.bu_bu2:
//			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.huo_ding,
//					1);
//			if(!do_success){
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
//						2);
//				startAimActivity(LoginRegActivity.class);
//			}else{
//				
//				if(act.vpl.page1!=null&&act.vpl.page1.uc!=null)
//				{
//					intent.putExtra(BeikBankConstant.INTENT_TOTALCAPITAL,act.vpl.page1.uc.fundAmount);
//					
//				}
//				else
//				{
//					return;
//				}	
//               if(fundInfo!=null)
//				toTntent(1);
//            	 // selectPlay();
//			}
//			break;
//			//赎回
//		case R.id.bu_bu3:
//			if(!do_success){
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
//						2);
//				startAimActivity(LoginRegActivity.class);
//			}
//			if(fundInfo!=null&&act.vpl.page1!=null&&act.vpl.page1.uc!=null)
//			{
//			   //LiuChenManager.StartNext2(act, icb5);
//				toTntent(0);
//			}
//			else
//			{
//				Log.e("fundInfo","fundInfo is null");
//			}
//			break;
//		case R.id.iv:
//			showCountDialog();
//			break;
//		case R.id.ll_ll1:
//			 Intent intent1=new Intent();
//			 intent1.setClass(act,RiskControlActivity.class);
//             act.startActivity(intent1);
//			
//			break;
//		case R.id.ll_ll2:
//			if(fundInfo!=null)
//			{
//			  intent.putExtra(HuoqiDetailActivity.index,fundInfo);
//			  intent.setClass(act,HuoqiDetailActivity.class);
//			  act.startActivity(intent);
//			}
//			break;	
//		}
//	}
//	//选择支付方式
//	private void selectPlay()
//	{
//		LiuChenManager.selectPay(icb3, act,false);
//	}
//	/**
//	 * 回调到赎回
//	 */
//	ICallBack icb5=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//		
//			if(obj==null)
//			{
//			 Intent intent=new Intent();
//			 intent.setClass(act,HuoqiReturnIActivity.class);
//			 intent.putExtra("index",fundInfo);
//			 intent.putExtra("index1",act.vpl.page1.uc.fundAmount);
//			 act.startActivity(intent);
//			}
//		}
//	};
//	/**
//	 * 回调到选择支付
//	 */
//	ICallBack icb4=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj==null)
//			{
//			//toTntent(1);
//			  selectPlay();
//			}
//		}
//	};
//	public void toTntent(int flag)
//	{  
//		BeikBankApplication.mSharedPref.putSharePrefString(PurchaseActivity.index,fundInfo.sid);
//		if(flag==0){//0跳到取现
//			//UserInfo userInfo=UserInfoDao.getInstance(act).getUserInfo();
//			//UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//			//BeikBankApi.getInstance().getTotalCapitalInfo(act,userInfo.getId(), getTotalCapitalInfoHandler);
//			//LiuChenManager.StartNext2(act,icb11);
//			LiuChenManager.StartNext(act,icb5);
//		}else{//1跳到购买
//			//checkIsBank();
//			LiuChenManager.StartNext(act,icb4);
//		}
//	}
//	ICallBack icb11=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			intent.setClass(act,RedeemActivity.class);
//			act.startActivity(intent);
//		}
//	};
//	/**
//	 * 回调到购买
//	 */
//	ICallBack icb3=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj==null)
//			{   
//				String play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
//				if(play.equals("0"))
//				{
//					CheckBankParam cbp=new CheckBankParam();
//					cbp.memberID=BeikBankApplication.getUserid();
//					TongYongManager tym=new TongYongManager(act, icb6,cbp);
//					tym.start();
//				}
//				else
//				{
//					  intent.setClass(act,PurchaseActivity.class);
//					  intent.putExtra(PurchaseActivity.index,fundInfo);
//					  act.startActivity(intent);
//				}
//			}
//		}
//	};
//
//	// 判断是否需要绑卡后回调
//	   ICallBack icb6=new ICallBack() {
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
//					Intent intent=new Intent(act,PurchaseActivity.class);
//					intent.putExtra(PurchaseActivity.index,fundInfo);
//					act.startActivity(intent);
//				}
//			}
//			
//		}
//	};
//
//	//水平进度条动画，每过1000/percent秒增加1%
//	public void timersch(final int percent){
//		timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				act.runOnUiThread(new Runnable() {
//					@Override
//					public void run() {						
//						if (counter == percent) {
//							timer.cancel();
//						}else{
//							numberprogress_rate.incrementProgressBy(1);
//							counter ++;
//						}
//					}
//				});
//			}
//		}, 500, (int)(1000/percent));
//	}
//
//}
