package com.beikbank.android.fragment;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.SupportBank;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.exception.GlobalExceptionHandler;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.sharedpref.SharedPref;
import com.beikbank.android.utils.ActivityManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.LockPatternUtils;
import com.beikbank.android.utils.Utils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BeikBankApplication extends Application{
    private static String userId;
    private static String phonenumber;
	private static UserInfo ui;
	/**
	 * 屏幕宽度
	 */
	private static int width;
	/**
	 * 屏幕高度
	 */
	private static int height;
	private static int staticHeight;
	
	public static LockPatternUtils mLockPatternUtils;
	public static Typeface fontFace;
	public static SharedPref mSharedPref = null;
	public static BeikBankApplication instance;
	private ActivityManager activityManager = null; 
	public static ArrayList<SupportBank> list;
	
	/**
	 * 有消息提示时为true,否则为false
	 */
	public static boolean msg_show=false;
	
	public static LinearLayout ll_notice;
	/**
	 * 网络提示父布局
	 */
	public static LinearLayout ll_msg_show;
	/**
	 *网络提示文本
	 */
	public static TextView tv_msg_show;
	
	
	
	public static BeikBankApplication getInstance(){
		if(instance==null){
			instance=new BeikBankApplication();
		}
		return instance;
	}	
	public ActivityManager getActivityManager() { 
        return activityManager; 
    } 
    public void setActivityManager(ActivityManager activityManager) { 
        this.activityManager = activityManager; 
    } 
	@Override
	public void onCreate() {
		super.onCreate();
		activityManager = ActivityManager.getScreenManager(); 
		initImageLoader(getApplicationContext());
		list=Utils.getSupportBankList(getApplicationContext());
		mSharedPref = SharedPref.getInstance(SystemConfig.PREF_NAME, this);
		mLockPatternUtils = new LockPatternUtils(this);
		fontFace = Typeface.createFromAsset(getAssets(),"Helvetica.ttf");
		BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.VERSION, "1.0.0");
		
		
		
		//JPushInterface.setDebugMode(true);
        //JPushInterface.init(this);
        
        
//        //极光推送
//        boolean isnet=BaseActivity.isNetworkConnected(this);
//        if(isnet)
//        {
//           // registerMessageReceiver();
//		   // jginit();
//		    //String s = getRegistrationID(this);
////		    if (s != null) 
////		    {
////			   Log.d("register_id", s);
////		    }
//		     String id=BeikBankApplication.getPhoneNumber();
//		    if(id==null)
//		    {
//		    	id="";
//		    }
//		    Set<String> list=new TreeSet<String>();
//		    list.add(id);
//
//		    JPushInterface.setAlias(this,id,new TagAliasCallback() {
//				
//				@Override
//				public void gotResult(int arg0, String arg1, Set<String> arg2) {
//					if(arg0==0)
//					{
//					   Log.d("alias","成功"+BeikBankApplication.getPhoneNumber());
//					}
//					else
//					{
//						Log.d("alias","失败");
//					}
//					
//				}
//			});
//        }
//        
//        
        
	}
	// 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
			public void jginit() {
				JPushInterface.init(getApplicationContext());
			}
    private void initHtpps()
    {
    	
    }
	
	
	
	public LockPatternUtils getLockPatternUtils() {
		return mLockPatternUtils;
	}
	/**
	 * 
	 * @param msg
	 * 显示网络异常
	 */
	public static void showMsg(String msg)
	{
		if(ll_msg_show!=null)
		{   
			if(msg!=null)
			{
			   tv_msg_show.setText(msg);
			}
			ll_msg_show.setVisibility(View.VISIBLE);
			BeikBankApplication.msg_show=true;
			if(ll_notice!=null)
			{
				ll_notice.setVisibility(View.GONE);
			}
		}
	}
	/**
	 * 隐藏网络提示
	 */
	public static void hideMsg()
	{
		if(ll_msg_show!=null)
		{
			BeikBankApplication.msg_show=false;
			ll_msg_show.setVisibility(View.GONE);
		}
	}
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024) // 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		System.exit(0);
	}
	
	
	public SharedPref getSharedPref()
	{
		if(mSharedPref==null)
		{
			mSharedPref = SharedPref.getInstance(SystemConfig.PREF_NAME, this);
		}
		return mSharedPref;
	}
	
    public static void reUserSet()
    {
    	userId=null;
    	phonenumber=null;
    	ui=null;
    }
    /**
     * 是否登录 true 登录 false 没有登录
     * @return
     */
    public static boolean isLogin()
    {
    	boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
        return do_success;
    }
	public static String getUserid()
	{  
		user_code=getSharePref(BeikBankConstant.user_code);
//		if(userId==null)
//		{
//			initData();
//		}
//		return userId;
		return user_code;
	}
	/**
	 * 得到账号id
	 * @return
	 */
	public static String getAccid()
	{   
		String id=getSharePref(BeikBankConstant.zhanghao);
		return id;
	}
	/**
	 * 订单id
	 * @return
	 */
	public static String getOrderId()
	{   
		String id=getSharePref(BeikBankConstant.order_id);
		return id;
	}

    public static String getPhoneNumber()
    {   
    	
    	 phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);

    	return phonenumber;
    }
    public static void setPhoneNumber(String phone)
    {
    	BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT,phone);
    	phonenumber=phone;
    }
    public static UserInfo getUserInfo()
    {
    	if(ui==null)
    	{
    		initData();
    	}
    	return ui;
    }
    
    private static String user_code;
    /**
     * 得到用户临时ID
     * @return
     */
    public static String getUserCode()
    {   
    	user_code=getSharePref(BeikBankConstant.user_code);
    	return user_code;
    }
    
    public static void setSharePref(String key,String value)
    {
    	BeikBankApplication.mSharedPref.putSharePrefString(key+getPhoneNumber(),value);
    	
    }
    public static String getSharePref(String key)
    {
    	return BeikBankApplication.mSharedPref.getSharePrefString(key+getPhoneNumber());
    }
    public static boolean initData()
    {   
    	try
    	{
//    	  ui=UserInfoDao.getUserInfo();
//    	  if(ui!=null)
//    	  {
//    	    userId=ui.id;
//    	  }
    	  phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
    	}
    	catch(Exception e)
    	{   
    		userId=null;
    		phonenumber=null;
    		e.printStackTrace();
    	}
    	return true;
    }
    /**
     * 得到屏幕宽度
     * @return
     */
    public static int getWidth(Activity act)
    {   
    	if(width==0)
    	{
    		initWidthAndHeight(act);
    	}
    	return width;
    }
    //得到状态栏的高度
    public static int getStaticHeight(Activity act)
    {
    	if(staticHeight==0)
    	{
    		staticHeight=DensityUtil.getStatusHeight(act);
    	}
    	return staticHeight;
    }
    /**
     * 得到屏幕高度
     * @return
     */
    public static int getHeight(Activity act)
    {   
    	if(height==0)
    	{
    		initWidthAndHeight(act);
    	}
    	return height;
    }
    private static void initWidthAndHeight(Activity act)
    {   
    	width=act.getWindowManager().getDefaultDisplay().getWidth();
    	height=act.getWindowManager().getDefaultDisplay().getHeight();
    }
}
