//package com.beikbank.android.widget;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.framework.Platform.ShareParams;
//import cn.sharesdk.framework.utils.UIHandler;
//import cn.sharesdk.sina.weibo.SinaWeibo;
//import cn.sharesdk.system.text.ShortMessage;
//import cn.sharesdk.tencent.qq.QQ;
//import cn.sharesdk.tencent.qzone.QZone;
//import cn.sharesdk.wechat.friends.Wechat;
//import cn.sharesdk.wechat.moments.WechatMoments;
//
//import com.beikbank.android.R;
//import com.beikbank.android.activity.BandTestActivity;
//import com.beikbank.android.activity.BankBindActivity;
//import com.beikbank.android.activity.HomeActivity2;
//import com.beikbank.android.activity.LoginRegActivity;
//import com.beikbank.android.activity.PurchaseActivity;
//import com.beikbank.android.activity.RealnameActivity;
//import com.beikbank.android.activity.RedeemActivity;
//import com.beikbank.android.activity.TransactionPwdSetActivity;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.conmon.SystemConfig;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.dao.UserInfoDao;
//import com.beikbank.android.data.IsCheckBank;
//import com.beikbank.android.data.IsCheckBank_data;
//import com.beikbank.android.data.TotalCapitalInfo;
//import com.beikbank.android.data.TotalMoney_data;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.exception.LogHandler;
//import com.beikbank.android.fragment.BeikBankApplication;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.net.HandlerBase;
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.impl.IsCheckBankManager;
//import com.beikbank.android.net.impl.MoneyInfoManager;
//import com.beikbank.android.net.impl.TotalMoneyManager;
//import com.beikbank.android.share.ShateUtil;
//import com.beikbank.android.sharedpref.SharedPref;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.DensityUtil;
//import com.beikbank.android.utils.NumberManager;
//import com.beikbank.android.utils.SecretUtils;
//import com.beikbank.android.utils.Utils;
//import com.google.gson.Gson;
//import com.nineoldandroids.animation.Animator;
//import com.nineoldandroids.animation.AnimatorSet;
//import com.nineoldandroids.animation.ObjectAnimator;
//import com.nineoldandroids.animation.ValueAnimator;
//import com.nineoldandroids.animation.Animator.AnimatorListener;
//import com.umeng.analytics.social.UMSocialService;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v4.view.ViewPager.OnPageChangeListener;
//import android.support.v4.widget.EdgeEffectCompat;
//import android.util.AttributeSet;
//import android.util.Base64;
//import android.util.Base64DataException;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.view.animation.LinearInterpolator;
//import android.view.animation.OvershootInterpolator;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
// /**
// *copyright 喻国合 
// *email: 1374405188@qq.com
// *2014-12-11
// **/
//public class ViewPageLinearLayout extends LinearLayout implements OnClickListener{
//	   
//	    private boolean debug=true;
//		private final String TAG="ViewPageLinearLayout";
//		/**
//		 *左边菜单
//		 */
//		public ImageView imageview_left;
//		private ImageView imageview_right2;
//		private LinearLayout linear_redeemm,linear_putmoney;
//		private MyAdapter mAdapter;
//		public MyViewPager mPager;
//		private ArrayList<View> pagerItemList = new ArrayList<View>();
//		//private ProductFragment page1 = new ProductFragment();
//		//private WealthFragment page2 = new WealthFragment();
//		public Page1 page1;
//		public Page2 page2;
//		public Page4 page4;
//		//公告
//		public LinearLayout ll_notice;
//		public TextView tv_notice;
//		/**
//		 * 登录
//		 */
//		private TextView tv_right2;
//		
//		/**
//		 * 处理没有网络时的布局
//		 */
//	    private RelativeLayout rl1;
//	    private LinearLayout ll1;
//		/**
//		 * 没有网络时提示页
//		 */
//		public Page3 page3;
//		public static String rate="";
//		public static String sid="";
//		public static boolean isShareShowing=true;
//
//		private LinearLayout linear_right;
//		private ImageView imageview_shadow;
//		private AnimatorSet animatorSet1,animatorSet2;
//		private ImageView imageview_weixinmoment,imageview_weixinfriend,
//		imageview_weibo,imageview_qq,imageview_qqzone,imageview_message;
//		private final String share_title="赚啦理财！赚啦理财！";//分享标题
//		private final String share_text="我刚把余额宝的钱存了赚啦理财，最高15%年化收益率！4倍哟！赶紧看看";//分享内容
//		//private final String share_url="http://beikbank.com/AppDownLoad/appDownLoad.html";//分享url
//		private  String share_url="http://www.beikbank.com/beikbankapp/appDownLoad.jsp";
//		private  String share_url2="http://www.beikbank.com/beikbankapp/appDownLoad.jsp";
//		
//		private HomeActivity2 act;
//		
//		TextView tv_title;
//		/**
//		 * 用于显示手机号的文本
//		 */
//		private TextView tv1;
//		
//		private static String tags="理财精选";
//	    public ViewPageLinearLayout(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//		act=(HomeActivity2) context;
//		ShareSDK.initSDK(act);
//		init();
//
//	}
//	public ViewPageLinearLayout(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		act=(HomeActivity2) context;
//		ShareSDK.initSDK(act);
//		init();
//		
//		
//	}
//	/**
//	 * 初始化分享图标
//	 */
//	public void init2()
//	{
//	    do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		if(do_success)
//		{
//			imageview_right2.setVisibility(View.VISIBLE);
//			tv_right2.setVisibility(View.GONE);
//		}
//		else
//		{
//			imageview_right2.setVisibility(View.GONE);
//			tv_right2.setVisibility(View.VISIBLE);
//		}
//	}
//	View view ;
//	/**
//	 * 是否登录
//	 */
//	boolean do_success;
//    private void init()
//    {  
//    	//login success
//         do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//        view = act.getLayoutInflater().inflate(R.layout.view_pager, null);
//    	ll_notice=(LinearLayout) view.findViewById(R.id.ll_notice);
//    	BeikBankApplication.ll_notice=ll_notice;
//    	tv_notice=(TextView)view.findViewById(R.id.tv_notice);
//    	
//    	tv_title=(TextView) view.findViewById(R.id.titleTv);
//
//    	
//    	tv1=(TextView) view.findViewById(R.id.tv10);
//		imageview_left = (ImageView) view.findViewById(R.id.imageview_left);//标题栏左边
//		
//		imageview_right2 = (ImageView) view.findViewById(R.id.imageview_right2);//标题栏右边
//		tv_right2=(TextView)view.findViewById(R.id.tv_right2);
//		
//		
//		BeikBankApplication.ll_msg_show=(LinearLayout) view.findViewById(R.id.ll_msg_show);
//		BeikBankApplication.tv_msg_show=(TextView) view.findViewById(R.id.tv_msg_show);
//		
//		
//		
////		if(do_success)
////		{
////			imageview_right2.setVisibility(View.VISIBLE);
////			tv_right2.setVisibility(View.GONE);
////		}
////		else
////		{
////			imageview_right2.setVisibility(View.GONE);
////			tv_right2.setVisibility(View.VISIBLE);
////		}
//		init2();
//		
//		
//		
//		//linear_redeemm = (LinearLayout) view.findViewById(R.id.linear_redeem);//取现
//		//linear_putmoney = (LinearLayout) view.findViewById(R.id.linear_putmoney);//购买
//        
//		tv_right2.setOnClickListener(this);
//		imageview_right2.setOnClickListener(this);
//		imageview_left.setOnClickListener(this);
//		//linear_redeemm.setOnClickListener(this);
//		//linear_putmoney.setOnClickListener(this);
//
//		mPager = (MyViewPager) view.findViewById(R.id.pager);
//		
//		//mAdapter = new MyAdapter();
//		//mPager.setAdapter(mAdapter);
//
//		if(do_success)
//		{
//			initPage(1,1);
//			
//			tv_title.setText(tags);
//			initLogin(true);
//		    mPager.setpagerCount(3);
//			//initYuan(true,2);
//		  
//		}
//		else
//		{
//			initPage(-1,1);
//			tv_title.setText(tags);
//			initLogin(false);
//			mPager.setpagerCount(2);
//		}
//			
//			mPager.setCurrentItem(0);
//			//setPage();
//			mAdapter.notifyDataSetChanged();
//			
//
//		
//
//		linear_right=(LinearLayout)view.findViewById(R.id.linear_right);
//		imageview_shadow=(ImageView)view.findViewById(R.id.imageview_shadow);
//		imageview_shadow.setOnClickListener(this);
//		animatorSet1 = new AnimatorSet();
//		animatorSet2 = new AnimatorSet();
//
//		imageview_weixinmoment=(ImageView)view.findViewById(R.id.imageview_weixinmoment);
//		imageview_weixinfriend=(ImageView)view.findViewById(R.id.imageview_weixinfriend);
//		imageview_weibo=(ImageView)view.findViewById(R.id.imageview_weibo);
//		imageview_qq=(ImageView)view.findViewById(R.id.imageview_qq);
//		imageview_qqzone=(ImageView)view.findViewById(R.id.imageview_qqzone);
//		imageview_message=(ImageView)view.findViewById(R.id.imageview_message);
//
//		imageview_weixinmoment.setOnClickListener(this);
//		imageview_weixinfriend.setOnClickListener(this);
//		imageview_weibo.setOnClickListener(this);
//		imageview_qq.setOnClickListener(this);
//		imageview_qqzone.setOnClickListener(this);
//		imageview_message.setOnClickListener(this);
//        
//		class Pageon implements OnPageChangeListener
//		{
//			 private EdgeEffectCompat leftEdge;  
//			 private EdgeEffectCompat rightEdge; 
//
//            public Pageon()
//            {
//            	 try {   
//            		 
//            		 Field leftEdgeField = mPager.getClass().getSuperclass().getDeclaredField("mLeftEdge");             
//            		 Field rightEdgeField = mPager.getClass().getSuperclass().getDeclaredField("mRightEdge");     
//            		 if(leftEdgeField != null && rightEdgeField != null){    
//            			 leftEdgeField.setAccessible(true);              
//            			 rightEdgeField.setAccessible(true);               
//            			 leftEdge = (EdgeEffectCompat) leftEdgeField.get(mPager);    
//            			 rightEdge = (EdgeEffectCompat) rightEdgeField.get(mPager);       
//            			 }       
//            		 } catch (Exception e) {     
//            			 e.printStackTrace();      
//            			 } 
//
//
//            }
//			@Override
//			public void onPageSelected(int position) {
//
//				if (myPageChangeListener != null)
//					myPageChangeListener.onPageSelected(position);
//
//				if(position==0){
////					if(do_success)
////					{
////						tv_title.setText("我的资产");
////					}
////					else
////					{
////						tv_title.setText(tags);
////					}
//					tv_title.setText(tags);
//					initYuan(do_success,0);
//					mPager.setCurrentIndex(0);
//				}
//				else if(position==1)
//				{
//					
//					tv_title.setText(tags);
//					initYuan(do_success,1);
//					mPager.setCurrentIndex(1);
//				}
//				else if(position==2)
//				{   
//					if(do_success)
//					{
//						tv_title.setText("我的资产");
//					}
//					initYuan(do_success,2);
//					mPager.setCurrentIndex(2);
//				}
//               
//				 if(leftEdge != null && rightEdge != null) 
//				 {             
//					 leftEdge.finish();     
//					 rightEdge.finish();    
//					 leftEdge.setSize(0, 0);  
//					 rightEdge.setSize(0, 0);    
//				  }
//			}
//
//			@Override
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//				 if(leftEdge != null && rightEdge != null) 
//				 {             
//					 leftEdge.finish();     
//					 rightEdge.finish();    
//					 leftEdge.setSize(0, 0);  
//					 rightEdge.setSize(0, 0);    
//				  }
//			}
//
//			@Override
//			public void onPageScrollStateChanged(int position) {
//
//			}
//		}
//		mPager.setOnPageChangeListener(new Pageon());
//		addView(view);
//		
//		
//		
//		firstEnter();
//    }
//    /**
//     * 处理没有网络
//     */
//    private void doNoNet()
//    {   
//    	boolean isnet=act.isNetworkConnected(act);
//    	if(!isnet)
//    	{ 
//    	  if(rl1!=null)
//    	  {
//    		  return ;
//    	  }
//    	  rl1=(RelativeLayout) view.findViewById(R.id.rl1);
//          ll1=(LinearLayout) view.findViewById(R.id.ll1);
//         rl1.setVisibility(View.GONE);
//          ll1.setVisibility(View.VISIBLE);
//    	 TextView tv=null;
//    
//    		tv=(TextView)ll1.findViewById(R.id.tv_tv);
//    		tv.setOnClickListener(new OnClickListener() {
//    			
//    			@Override
//    			public void onClick(View v) {
//    				
//    				boolean isnet=act.isNetworkConnected(act);
//    				if(isnet)
//    				{   
//    					rl1.setVisibility(View.VISIBLE);
//    			        ll1.setVisibility(View.GONE);
//    					act.vpl.setLoginInfo();
//    					act.vpl.page2.addData();
//    					act.vpl.page4.addData2();
//    					
//    				}
//    				else
//    				{   
//    					rl1.setVisibility(View.GONE);
//    			        ll1.setVisibility(View.VISIBLE);
//    					HandlerBase.showMsg(act,act.getString(R.string.error_5),0);
//    				}
//    			}
//    		});
//    	}
//    	
//    }
//    /**
//     * 设置登录状态
//     */
//    private void initLogin(boolean isLogin)
//    {
//    	initTitle(isLogin);
//    	initYuan(isLogin, 0);
//    }
//    /**
//     * 
//     * @param isCaihu 当前页面是否是我的资产
//     */
//    private void initTitle(boolean isCaihu)
//    {
//    	if(isCaihu)
//    	{
//    		tv1.setVisibility(view.VISIBLE);
//    		tv1.setText(Utils.getEncryptedPhonenumber(BeikBankApplication.getPhoneNumber()));
//    	}
//    	else
//    	{
//    		tv1.setVisibility(view.GONE);
//    	}
//    }
//    
//    
//    /**
//     * 底部圆
//     */
//    ImageView iv_iv11,iv_iv12,iv_iv21,iv_iv22,iv_iv31,iv_iv32;
//    LinearLayout ll_iv1;
//    
//    /**
//     * 
//     * @param islogin 是否登录
//     * @param index  当前选择是第几项
//     */
//    private void initYuan(boolean islogin,int index)
//    {   
//    	if(islogin&&index==2)
//    	{
//    		initTitle(islogin);
//    	}
//    	else
//    	{
//    		initTitle(false);
//    	}
//    	if(iv_iv11==null)
//    	{
//    		iv_iv11=(ImageView) view.findViewById(R.id.iv_iv11);
//    		iv_iv12=(ImageView) view.findViewById(R.id.iv_iv12);
//    		iv_iv21=(ImageView) view.findViewById(R.id.iv_iv21);
//    		iv_iv22=(ImageView) view.findViewById(R.id.iv_iv22);
//    		iv_iv31=(ImageView) view.findViewById(R.id.iv_iv31);
//    		iv_iv32=(ImageView) view.findViewById(R.id.iv_iv32);
//    		ll_iv1=(LinearLayout) view.findViewById(R.id.ll_iv1);
//    	}
//    	if(islogin)
//    	{
//    		ll_iv1.setVisibility(View.VISIBLE);
//    	}
//    	else
//    	{
//    		ll_iv1.setVisibility(View.GONE);
//    	}
//    	initYuan2(islogin,index);
//    }
//    private void initYuan2(boolean islogin,int index)
//    {
//    	iv_iv11.setVisibility(View.GONE);
//    	iv_iv21.setVisibility(View.GONE);
//    	iv_iv31.setVisibility(View.GONE);
//    	
//    	iv_iv12.setVisibility(View.VISIBLE);
//    	iv_iv22.setVisibility(View.VISIBLE);
//    	iv_iv32.setVisibility(View.VISIBLE);
//    	
//    	if(!islogin)
//    	{
//    		index++;
//    	}
//    	if(index==0)
//    	{
//    		iv_iv12.setVisibility(View.GONE);
//    		iv_iv11.setVisibility(View.VISIBLE);
//    	}
//    	else if(index==1)
//    	{
//    		iv_iv22.setVisibility(View.GONE);
//    		iv_iv21.setVisibility(View.VISIBLE);
//    	}
//    	else if(index==2)
//    	{
//    		iv_iv32.setVisibility(View.GONE);
//    		iv_iv31.setVisibility(View.VISIBLE);
//    	}
//    }
//    public void updateView()
//    {
//    	String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//        do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		if(phonenumber.equals("")||do_success){
//			initPage(-1,1);
//			
//			mPager.setCurrentItem(0);
//			mAdapter.notifyDataSetChanged();
//			tv_title.setText(tags);
//			initLogin(true);
//
//		}else{
//			initPage(1,1);
//			mPager.setCurrentItem(0);
//			mAdapter.notifyDataSetChanged();
//
//			tv_title.setText("我的资产");
//			initLogin(false);
//			//firstLogin();
//		}	
//    }
////    /**
////	 * 第一次登录的时候消息提示
////	 */
////    private void firstLogin()
////    {  
////    	 SharedPref sp=BeikBankApplication.getInstance().getSharedPref();
////    	boolean first_login=sp.getSharePrefBoolean(BeikBankConstant.FIRST_LOGIN,false);
////        if(!first_login||SystemConfig.isDebug())
////        {
////        	sp.putSharePrefBoolean(BeikBankConstant.FIRST_LOGIN,true);
////        	FirstLoginWindows.addView(act,R.layout.first_login_msg);
////        }
////    }
//	//public void onActivityCreated(Bundle savedInstanceState) {
//	//	super.onActivityCreated(savedInstanceState);
//	//	ShareSDK.initSDK(act);
//		
//	//}
//	private void initPage(int a,int b)
//	{
//		pagerItemList.clear();
//		
//		if(b>0)
//		{       	
//              if(page2==null)
//              {
//				page2=new Page2(act);
//				((Page2) page2).init(act);
//				
//              }      
//              if(page4==null)
//              {
//				page4=new Page4(act);
//				((Page4) page4).init(act);
//              } 
//				
//			pagerItemList.add(page2);
//			pagerItemList.add(page4);
//		}
//		if(a>0)
//		{   
//			page1=new Page1(act);
//		    ((Page1) page1).init(act);
//	     	pagerItemList.add(page1);
//		}
//		else
//		{
//			
//		}
//		
//		
//		
//		mAdapter = new MyAdapter();
//		mPager.setAdapter(mAdapter);
//	}
//	/**
//	 * 没有网络时显示信息
//	 */
//    private void setNoNet()
//    {
//    	pagerItemList=new ArrayList<View>();
//    	page3=new Page3(act);
//    	pagerItemList.add(page3);
//    	mAdapter = new MyAdapter();
//		mPager.setAdapter(mAdapter);
//    }
//	public void setLoginInfo(){
//		//pagerItemList.clear();
//		String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//	    do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		if(!phonenumber.equals("")&&do_success){//登录用户，有产品和资产信息
//			initPage(1,1);
//			mPager.setCurrentItem(0);
//			mAdapter.notifyDataSetChanged();
//
//			tv_title.setText(tags);
//			//firstLogin();
//			//act.updateView();
//			//act.icb1.back(null);
//			mPager.setpagerCount(3);
//		}else{//未登录用户，只有产品信息
//			initPage(-1,1);
//			tv_title.setText(tags);
//			mPager.setCurrentItem(0);
//			mAdapter.notifyDataSetChanged();
//			mPager.setpagerCount(2);
//			//act.updateView();
//			//act.icb2.back(null);
//		}
//		initYuan(do_success,0);
//	}
//
//
//	public void setContentInfo(){
//		if(pagerItemList.size()==0){//如果list没有数据，重新加载
//			initPage(1,1);
//			mPager.setCurrentItem(0);
//			mAdapter.notifyDataSetChanged();
//		}
//	}
//
//	private void refreshContentInfo(){//重新请求
//		pagerItemList.clear();
//		initPage(1,1);
//		mPager.setCurrentItem(0);
//		mAdapter.notifyDataSetChanged();
//		if(page1!=null)
//		{
//			page1.updateView();
//		}
//		
//	}
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(act, pActClassName);
//		act.startActivity(_Intent);
//	}
//
//
//	public class MyAdapter extends PagerAdapter
//	{
//
//		@Override
//		public int getCount() {
//			return pagerItemList.size();
//		}
//
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			// TODO Auto-generated method stub
//			container.removeView((View)object);
//		}
//
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
//			container.addView(pagerItemList.get(position));
//			return pagerItemList.get(position);
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			// TODO Auto-generated method stub
//			return  arg0==arg1;
//		}
//
//		@Override
//		public int getItemPosition(Object object) {
//			// TODO Auto-generated method stub
//			return POSITION_NONE;
//		}
//		
//	}
//
////	public class MyAdapter extends FragmentPagerAdapter {
////	     
////		  private final FragmentManager mFragmentManager;
////		    private FragmentTransaction mCurTransaction = null;
////		    private Fragment mCurrentPrimaryItem = null;
////
////		 public MyAdapter(FragmentManager fm) 
////		 {      
////			    super(fm);
////		        mFragmentManager = fm;
////		 }
////
////
////		@Override
////		public int getCount() {
////			return  pagerItemList.size();
////		}
////	
////		 @Override
////		    public Object instantiateItem(View container, int position) {
////		        if (mCurTransaction == null) {
////		            mCurTransaction = mFragmentManager.beginTransaction();
////		        }
////		        
////		        // Do we already have this fragment?
////		        String name = makeFragmentName(container.getId(), position);
////		        Fragment fragment = mFragmentManager.findFragmentByTag(name);
////		        if (fragment != null) {
////		        	mCurTransaction.remove(fragment);
////		           // mCurTransaction.attach(fragment);
////		        } 
////		           
////		        fragment = getItem(position);
////		           
////		       mCurTransaction.add(container.getId(), fragment,
////		                    makeFragmentName(container.getId(), position));
////		        
////		        if (fragment != mCurrentPrimaryItem) {
////		            fragment.setMenuVisibility(false);
////		        }
////
////		        return fragment;
////		    }
////
////		@Override
////		public void finishUpdate(ViewGroup container) {
////			
////			super.finishUpdate(container);
////		}
////		private  String makeFragmentName(int viewId, int index) {
////	        return "android:switcher:" + viewId + ":" + index;
////	    }
////		@Override
////		public Fragment getItem(int position) {
////
////			return  pagerItemList.get(position);
////		}
////
////		@Override
////		public void destroyItem(ViewGroup container, int position, Object object) {
////			// TODO Auto-generated method stub
////			super.destroyItem(container, position, object);
////
////		}
////
////		@Override
////		public int getItemPosition(Object object) {
////			// TODO Auto-generated method stub
////			return POSITION_NONE;
////		}
////
////
////
////	}
//	  /**
//	    * 第一次进入程序
//	    */
//       private void firstEnter()
//		  {  
//		    	 SharedPref sp=BeikBankApplication.getInstance().getSharedPref();
//		    	 boolean first_login=sp.getSharePrefBoolean(BeikBankConstant.first_enter,false);
//		         if(!first_login)
//		         {
//		        	sp.putSharePrefBoolean(BeikBankConstant.first_enter,true);
//		        	FirstLoginWindows.addView(act,R.layout.first_enter_msg);
//		         }
//		  }
//	private MyPageChangeListener myPageChangeListener;
//
//	private void setMyPageChangeListener(MyPageChangeListener l) {
//
//		myPageChangeListener = l;
//
//	}
//
//	private interface MyPageChangeListener {
//		public void onPageSelected(int position);
//	}
//    private void initUid()
//    {
//		//UserInfo userInfo=UserInfoDao.getInstance(act).getUserInfo();
//    	 UserInfo userInfo=BeikBankApplication.getUserInfo();
//		if( userInfo!=null)
//	    {
//	    	String uid=userInfo.id;
//	    	byte array[]=SecretUtils.encryptMode(uid.getBytes());
//	    	String base64=Base64.encodeToString(array,Base64.DEFAULT);
//	    	share_url+="?wd="+base64;
//	    	if(debug)
//	    	{
//	    		Log.e(TAG, share_url);
//	    		LogHandler.writeLogFromString(TAG,share_url);
//	    	}
//	    }
//    }
//	@Override
//	public void onClick(View v) {
//	    do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		switch(v.getId()){
//		case R.id.tv_right2:
//			Intent intent=new Intent(act, LoginRegActivity.class);
//			act.startActivity(intent);
//			break;	
//		case R.id.imageview_left://点击标题栏左边
//			((HomeActivity2)act).toggle();
//			//((HomeActivity2)act).lml.closeMenu();
//			break;		
////		case R.id.linear_redeem:
////			//String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
////			if(!do_success){
////				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
////						2);
////				startAimActivity(LoginRegActivity.class);
////			}else{
////
////				toTntent(0);
////			}
////			break;
////	    //购买
////		case R.id.linear_putmoney:
////			//String phonenumber2=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
////			if(!do_success){
////
////				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
////						3);
////				startAimActivity(LoginRegActivity.class);
////			}else{
////
////				toTntent(1);
////
////			}
////			break;
//		case R.id.imageview_right2:
//			//isShareShowing=true;
//			//performAnimateForShow();
//			ShateUtil su=new ShateUtil(act);
//			su.share(act);
//			break;
//		case R.id.imageview_shadow:
//			isShareShowing=false;
//			performAnimateForHide();
//			break;
//		case R.id.imageview_weibo://新浪微博
//			ShareParams sp_weibo = new ShareParams();
//			sp_weibo.setText(share_text+share_url);
//            
//			Platform weibo = ShareSDK.getPlatform(act,SinaWeibo.NAME);
//			//weibo.removeAccount();
//		    weibo.SSOSetting(false);
//			weibo.setPlatformActionListener(pa); // 设置分享事件回调
//			weibo.share(sp_weibo);
//			
//			break;
//		case R.id.imageview_qq://qq
//			ShareParams sp_qq = new ShareParams();
//			sp_qq.setTitle(share_title);
//			sp_qq.setText(share_text+share_url);
//			Platform qq = ShareSDK.getPlatform (QQ.NAME);
//			
//			qq.setPlatformActionListener (pa);
//			qq.share(sp_qq);
//			break;
//		case R.id.imageview_qqzone://QQ空间
//			ShareParams sp_qqzone = new ShareParams();
//			sp_qqzone.setTitle(share_title);
//			sp_qqzone.setTitleUrl(share_url);
//			sp_qqzone.setText(share_text);
//			sp_qqzone.setSite("");
//			sp_qqzone.setSiteUrl("");
//
//			Platform qzone = ShareSDK.getPlatform (QZone.NAME);
//			qzone. setPlatformActionListener (pa); // 设置分享事件回调
//			// 执行图文分享
//			qzone.share(sp_qqzone);
//			break;
//		case R.id.imageview_weixinmoment://微信朋友圈
//			Platform plat_weixin = ShareSDK.getPlatform(act, WechatMoments.NAME);
//			WechatMoments.ShareParams sp_weixin = new WechatMoments.ShareParams();
//			sp_weixin.text = share_text;
//			sp_weixin.title = share_title;
//			sp_weixin.url = share_url;
//			sp_weixin.shareType = Platform.SHARE_WEBPAGE;
//			Resources res=getResources();			   
//			Bitmap bitmap=BitmapFactory.decodeResource(res, R.drawable.app_logo);
//			sp_weixin.setImageData(bitmap);
//			plat_weixin.setPlatformActionListener(pa);
//			plat_weixin.share(sp_weixin);
//			break;
//		case R.id.imageview_weixinfriend://微信好友
//			Platform plat_weixinfriend = ShareSDK.getPlatform(act, Wechat.NAME);
//
//			Wechat.ShareParams sp_weixinfriend = new Wechat.ShareParams();
//			sp_weixinfriend.title = share_title;
//			sp_weixinfriend.text = share_text;
//			sp_weixinfriend.url = share_url;
//			sp_weixinfriend.shareType = Platform.SHARE_WEBPAGE;
//			Resources res2=getResources();			   
//			Bitmap bitmap2=BitmapFactory.decodeResource(res2, R.drawable.app_logo);
//			sp_weixinfriend.setImageData(bitmap2);
//			plat_weixinfriend.setPlatformActionListener(pa);
//			plat_weixinfriend.share(sp_weixinfriend);
//			break;
//		case R.id.imageview_message://短信
//			ShareParams sp_message = new ShareParams();
//			sp_message.setTitle("");
//			sp_message.setText(share_text+share_url);
//			Platform message = ShareSDK.getPlatform(ShortMessage.NAME);
//			message.setPlatformActionListener(pa); 
//			message.share(sp_message);
//			break;
//		}
//	}
//	private int getdpWidth()
//	{   
//		DensityUtil du=new DensityUtil(act);
//		return du.dip2px(44);
//	}
//	public void performAnimateForShow() {  //展开分享
//		int wid=getdpWidth();
//		if(share_url2.equals(share_url))
//		{
//			initUid();
//		}
//		if(!animatorSet1.isStarted()){
//			ValueAnimator transAnim1=ObjectAnimator.ofFloat(imageview_weixinfriend, 
//					"translationX", -imageview_weixinfriend.getWidth()-wid);
//			transAnim1.setDuration(300);
//			transAnim1.setInterpolator(new OvershootInterpolator());
//			ValueAnimator transAnim2=ObjectAnimator.ofFloat(imageview_weixinmoment, 
//					"translationX", -imageview_weixinmoment.getWidth()-wid);
//			transAnim2.setDuration(300);
//			transAnim2.setInterpolator(new OvershootInterpolator());
//			ValueAnimator transAnim3=ObjectAnimator.ofFloat(imageview_weibo, 
//					"translationX", -imageview_weibo.getWidth()-wid);
//			transAnim3.setDuration(300);
//			transAnim3.setInterpolator(new OvershootInterpolator());
//			ValueAnimator transAnim4=ObjectAnimator.ofFloat(imageview_qq, 
//					"translationX", -imageview_qq.getWidth()-wid);
//			transAnim4.setDuration(300);
//			transAnim4.setInterpolator(new OvershootInterpolator());
//			ValueAnimator transAnim5=ObjectAnimator.ofFloat(imageview_qqzone, 
//					"translationX", -imageview_qqzone.getWidth()-wid);
//			transAnim5.setDuration(300);
//			transAnim5.setInterpolator(new OvershootInterpolator());
//			ValueAnimator transAnim6=ObjectAnimator.ofFloat(imageview_message, 
//					"translationX", -imageview_message.getWidth()-wid);
//			transAnim6.setDuration(300);
//			transAnim6.setInterpolator(new OvershootInterpolator());
//
//			ValueAnimator alphaAnim1=ObjectAnimator.ofFloat(imageview_shadow, 
//					"alpha", 0.5f, 1f);
//			alphaAnim1.setDuration(300);
//			alphaAnim1.setInterpolator(new LinearInterpolator());
//
//			animatorSet1.play(alphaAnim1).with(transAnim1).with(transAnim2).with(transAnim3).with(transAnim4).with(transAnim5).with(transAnim6);
//			transAnim2.setStartDelay(100);
//			transAnim3.setStartDelay(200);
//			transAnim4.setStartDelay(300);
//			transAnim5.setStartDelay(400);
//			transAnim6.setStartDelay(500);
//			UMSocialService  u;
//
//			animatorSet1.addListener(new AnimatorListener() {
//
//				@Override
//				public void onAnimationStart(Animator arg0) {
//					// TODO Auto-generated method stub
//					linear_right.setVisibility(View.VISIBLE);
//					imageview_shadow.setVisibility(View.VISIBLE);
//				}
//
//				@Override
//				public void onAnimationRepeat(Animator arg0) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onAnimationEnd(Animator arg0) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onAnimationCancel(Animator arg0) {
//					// TODO Auto-generated method stub
//
//				}
//			});
//			animatorSet1.start();
//		}
//	}
//
//	public void performAnimateForHide() {  //隐藏分享
//		if(!animatorSet2.isStarted()){
//			ValueAnimator transAnim1=ObjectAnimator.ofFloat(imageview_weixinfriend, 
//					"translationX", 0);
//			transAnim1.setDuration(200);
//			transAnim1.setInterpolator(new LinearInterpolator());
//			ValueAnimator transAnim2=ObjectAnimator.ofFloat(imageview_weixinmoment, 
//					"translationX", 0);
//			transAnim2.setDuration(200);
//			transAnim2.setInterpolator(new LinearInterpolator());
//			ValueAnimator transAnim3=ObjectAnimator.ofFloat(imageview_weibo, 
//					"translationX", 0);
//			transAnim3.setDuration(200);
//			transAnim3.setInterpolator(new LinearInterpolator());
//			ValueAnimator transAnim4=ObjectAnimator.ofFloat(imageview_qq, 
//					"translationX", 0);
//			transAnim4.setDuration(200);
//			transAnim4.setInterpolator(new LinearInterpolator());
//			ValueAnimator transAnim5=ObjectAnimator.ofFloat(imageview_qqzone, 
//					"translationX", 0);
//			transAnim5.setDuration(200);
//			transAnim5.setInterpolator(new LinearInterpolator());
//			ValueAnimator transAnim6=ObjectAnimator.ofFloat(imageview_message, 
//					"translationX", 0);
//			transAnim6.setDuration(200);
//			transAnim6.setInterpolator(new LinearInterpolator());
//
//			ValueAnimator alphaAnim1=ObjectAnimator.ofFloat(imageview_shadow, 
//					"alpha", 1f, 0.5f);
//			alphaAnim1.setDuration(200);
//			alphaAnim1.setInterpolator(new LinearInterpolator());
//
//			animatorSet2.play(alphaAnim1).with(transAnim1).with(transAnim2).with(transAnim3).with(transAnim4)
//			.with(transAnim5).with(transAnim6);
//
//			animatorSet2.addListener(new AnimatorListener() {
//
//				@Override
//				public void onAnimationStart(Animator arg0) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onAnimationRepeat(Animator arg0) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onAnimationEnd(Animator arg0) {
//					// TODO Auto-generated method stub
//					linear_right.setVisibility(View.INVISIBLE);
//					imageview_shadow.setVisibility(View.INVISIBLE);
//				}
//
//				@Override
//				public void onAnimationCancel(Animator arg0) {
//					// TODO Auto-generated method stub
//
//				}
//			});
//			animatorSet2.start();
//		}
//	}
//	/**
//	 * 启动到其他到后续流程
//	 */
//	private void selectAct()
//	{   
//		
//		 UserInfo userInfo=BeikBankApplication.getUserInfo();
//		boolean isCheckBank=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
//		 if(!userInfo.isHasAuthenticated()){//没有实名认证
//			Intent intent=new Intent(act,RealnameActivity.class);
//			intent.putExtra(BeikBankConstant.INTENT_PURCHASE, true);
//			intent.putExtra("is_nextpage", true);
//			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
//			act.startActivity(intent);
//		}else if(!userInfo.isHasBindCard()){//没有绑卡
//			Intent intent=new Intent(act,BankBindActivity.class);
//			intent.putExtra("is_nextpage", true);
//			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
//			act.startActivity(intent);
//		}
//		else if(!isCheckBank)
//		{
//			Intent intent=new Intent(act,BandTestActivity.class);
//			intent.putExtra("is_nextpage", true);
//			act.startActivity(intent);
//		}
//		//没有设置交易密码
//		else if(!userInfo.hasSetPaypassword)
//		{
//			Intent intent=new Intent(act,TransactionPwdSetActivity.class);
//			intent.putExtra("is_nextpage", true);
//			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
//			act.startActivity(intent);
//		}
//		else
//		{
//			Intent intent=new Intent(act,PurchaseActivity.class);
//			act.startActivity(intent);
//		}
//	}
//	/**
//	 * 检查是否需要验证银行卡
//	 */
//    private void checkIsBank()
//    {   
//    	 UserInfo ui=BeikBankApplication.getUserInfo();
//    	 boolean isCheckBank=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
//    	 if(isCheckBank)
//    	 {
//    		 //startAct();
//    		 selectAct();
//    	 }
//    	 else
//    	 {
//    		 IsCheckBankManager icbm=new IsCheckBankManager(act, icb2);
//    		 String uerid=ui.getId();
//    		 icbm.init(uerid);
//    		 icbm.start();
//    	 }
//    }
//    private void startAct()
//    {
//    	    Intent intent2=new Intent(act,PurchaseActivity.class);
//			intent2.putExtra(BeikBankConstant.INTENT_SID, sid);
//			act.startActivity(intent2);
//    }
//	public void toTntent(int flag)
//	{
//		if(flag==0){//0跳到取现
//			//UserInfo userInfo=UserInfoDao.getInstance(act).getUserInfo();
//			//UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//			//BeikBankApi.getInstance().getTotalCapitalInfo(act,userInfo.getId(), getTotalCapitalInfoHandler);
//			new TotalMoneyManager(act, icb1).start();
//		}else{//1跳到购买
//			checkIsBank();
//		}
//	}
//	ICallBack icb2=new ICallBack() {
//		@Override
//		public void back(Object obj) {
//		  if(obj!=null)
//		  {
//			IsCheckBank_data id=(IsCheckBank_data) obj;
//			IsCheckBank icb=id.data;
//			String s=icb.UserCardLimit;
//			//如果已经验证过
//			if("1".equals(s))
//			{
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,true);
//			}
//		  }
//		  selectAct();
//		}
//	};
//	 ICallBack icb1=new ICallBack() {
//			
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//		    	TotalMoney_data tm=(TotalMoney_data) obj;
//		    	String s1=NumberManager.getString(tm.data.totalAmount,"1",2);
//		    	double d=Double.parseDouble(s1);
//		    	if(d>0)
//		    	{
//		    		Intent intent=new Intent(act,RedeemActivity.class);
//					intent.putExtra(BeikBankConstant.INTENT_SID, sid);
//					intent.putExtra(BeikBankConstant.INTENT_TOTALCAPITAL,d);
//					act.startActivity(intent);
//		    	}
//		    	else
//		    	{
//		    		HandlerBase.showMsg(act,act.getString(R.string.error_3),0);
//		    	}
//			 
//			}
//		}
//	};
//	final static int success=1;
//	final static int faild=2;
//	final static int cacle=3;
//	
//    Handler shareHand=new Handler()
//    {
//
//		@Override
//		public void handleMessage(Message msg) 
//		{
//			super.handleMessage(msg);
//			switch (msg.what) 
//			{
//			  case success:
//				  Toast.makeText(act, "分享成功",Toast.LENGTH_SHORT).show();
//				break;
//
//			  case faild:
//				  String s="不用重复分享，稍后再试。";
//				  Toast.makeText(act,s,Toast.LENGTH_SHORT).show();
//				break;
//				
//			  case cacle:
//				  Toast.makeText(act, "分享取消",Toast.LENGTH_SHORT).show();
//				break;		
//			}
//			
//		}
//    	
//    };
//    PlatformActionListener pa=new PlatformActionListener() {
//		
//		@Override
//		public void onError(Platform arg0, int arg1, Throwable arg2) {
//			Message msg=new Message();
//			String s=arg2.getMessage();
//			msg.obj=s;
//			msg.what=faild;
//			shareHand.sendMessage(msg);
//			LogHandler.writeLogFromException(TAG, new Exception(arg2));
//		}
//		
//		@Override
//		public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
//			Message msg=new Message();
//			msg.what=success;
//			shareHand.sendMessage(msg);
//			
//		}
//		
//		@Override
//		public void onCancel(Platform arg0, int arg1) {
//			Message msg=new Message();
//			msg.what=cacle;
//			shareHand.sendMessage(msg);
//			
//		}
//	};
////	//总资产接口
////	JsonHttpResponseHandler getTotalCapitalInfoHandler = new JsonHttpResponseHandler(){
////
////		@Override
////		public void onStart() {
////			super.onStart();	
////		}
////
////		@Override
////		public void onFinish() {
////			super.onFinish();
////		}
////
////		@Override
////		public void onFailure(Throwable error, String content) {
////			
////			Utils.log(TAG, "onFailure()"+content);			
////		}
////
////		@Override
////		public void onSuccess(Response response) {
////			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
////			Gson gson=new Gson();
////			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////			if(result.equals("success")){
////				TotalCapitalInfo totalCapitalInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
////						BeikBankConstant.TYPE_JSONDATA), TotalCapitalInfo.class);
////				if(totalCapitalInfo.getTotalAmount()>0){
////					Intent intent=new Intent(act,RedeemActivity.class);
////					intent.putExtra(BeikBankConstant.INTENT_SID, sid);
////					intent.putExtra(BeikBankConstant.INTENT_TOTALCAPITAL, totalCapitalInfo.getTotalAmount());
////					act.startActivity(intent);
////				}else{
////					Toast.makeText(act, "总资产为零，不能取现", Toast.LENGTH_SHORT).show();
////				}				
////			}
////
////		}
////
////	};
//	/**
//	 * 购买后设置当前页为资产页
//	 */
//	public void setPage()
//	{   
//		boolean b=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_after_pay,false);
//		do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		if(b&&do_success)
//		{
//			initYuan(do_success,2);
//			mPager.setCurrentItem(2,true);
//			 BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_after_pay,false);
//			 tv_title.setText("我的资产");
//		}
//		else
//		{   
//			
////			  initYuan(do_success,0);
////			  mPager.setCurrentIndex(0);
////			  tv_title.setText(tags);
////			 
//			
//		}
//		String page=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.is_after_hongbao);
//		
//		if(page!=null)
//		{
//			if(page.equals("page4"))
//			{   
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.is_after_hongbao,"");
//				mPager.setCurrentItem(1,true);
//			}
//		}
//		mAdapter.notifyDataSetChanged();
//	}
//	
//}
