package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Intent;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.beikbank.android.R;
//import com.beikbank.android.conmon.DisplayManger;
//import com.beikbank.android.conmon.MessageManger;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.data.CommonInfo;
//import com.beikbank.android.data.CommonInfo_data;
//import com.beikbank.android.data.Huodong;
//import com.beikbank.android.data.Huodong_data;
//import com.beikbank.android.data.Notice;
//import com.beikbank.android.data.Notice_data;
//import com.beikbank.android.data.Win;
//import com.beikbank.android.data.Win_data;
//import com.beikbank.android.dataparam.HongbaoParam;
//import com.beikbank.android.dataparam.HuodongParam;
//import com.beikbank.android.dataparam.TotalMoneyParam;
//import com.beikbank.android.dataparam.WinParam;
//
//import com.beikbank.android.exception.GlobalExceptionHandler;
//import com.beikbank.android.exception.LogHandler;
//import com.beikbank.android.fragment.BeikBankApplication;
//
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.ImageUrl;
//import com.beikbank.android.net.ManagerParam;
//import com.beikbank.android.net.impl.BankListManager;
//import com.beikbank.android.net.impl.CheckUpdateManager;
//import com.beikbank.android.net.impl.CommonInfoManager;
//import com.beikbank.android.net.impl.FundInfoManager;
//import com.beikbank.android.net.impl.LoginManager;
//import com.beikbank.android.net.impl.MoneyInfoManager;
//import com.beikbank.android.net.impl.NoticeManager;
//import com.beikbank.android.net.impl.ThreadManagerSet;
//import com.beikbank.android.net.impl.TongYongManager;
//import com.beikbank.android.net.impl.TotalMoneyManager;
//import com.beikbank.android.net.impl.UserCapital2Manager;
//import com.beikbank.android.net.impl.UserCapitalManager;
//import com.beikbank.android.share.ShareMUtil;
//import com.beikbank.android.utils.BadgeUtil;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.Utils;
//import com.beikbank.android.utils2.MessageUtil;
//import com.beikbank.android.widget.FirstLoginWindows;
//import com.beikbank.android.widget.LeftMenuLinearLayout;
//import com.beikbank.android.widget.Page1;
//import com.beikbank.android.widget.ViewPageLinearLayout;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
//import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
//
//public class HomeActivity2 extends SlidingActivity {
//    
//	public static HomeActivity2 ha;
//	private final String TAG="HomeActivity2";
//	public SlidingMenu mSlidingMenu;
//	private long exitTime = 0;
//	Dialog dialog;
//	public LeftMenuLinearLayout lml;
//	public ViewPageLinearLayout vpl;
//	Activity act=this;
//	/**
//	 * page2是否被刷新过
//	 */
//    boolean rePage2=false; 
//    /**
//     * 第一次启动加载公告
//     */
//  public  boolean isAddNotice=true;
//  /**
//   * 公告是否是显示的
//   */
//  public boolean is_show_notice=false;
//  /**
//   * 程序启动时检查一次更新
//   */
//  public boolean is_first_start=true;
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		
//		
//        ha=this;
//		//setContentView(R.layout.frame_content);
//		//setBehindContentView(R.layout.frame_left_menu);
//
//		mSlidingMenu = getSlidingMenu();
//		mSlidingMenu.setMode(SlidingMenu.LEFT);
//		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset); 
//		mSlidingMenu.setFadeDegree(0.35f);
//		//mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//		//mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_NONE);
//		mSlidingMenu.toggle2(false);
//		mSlidingMenu.setOnOpenListener(new OnOpenListener() {
//			
//			@Override
//			public void onOpen() {
//				
//				
//				//mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_NONE);
//			}
//		});
//		mSlidingMenu.setOnCloseListener(new OnCloseListener() {
//			
//			@Override
//			public void onClose() {
//				lml.closeMenu();
//				//mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//			}
//		});
//		//设置 SlidingMenu 内容
//		//FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//		//fragmentTransaction.replace(R.id.left_menu, lmf,"left");
//		//fragmentTransaction.replace(R.id.content, vpf,"middle");
//
//		//fragmentTransaction.commit();
////		//是否登录
////		boolean is_login=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_log,false);
////		if(is_login)
////		{   
////			mSlidingMenu.showContent();
////			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,false);
////		}
//        lock();
//        Log.e("tag","oncleate");
//		initView();
//	    initIntent();
//		//String version=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.VERSION);
//		//BeikBankApi.getInstance().getBankListInfo(getApplicationContext(),version,
//		//		getBankListInfoHandler);
//		//String version2=Utils.getVersion(HomeActivity2.this);
//		//BeikBankApi.getInstance().checkUpdateInfo(HomeActivity2.this, version2, "1", getUpdateInfoHandler);
//		
//	    //updateView();
//         //new CheckUpdateManager(this,icb).start();
//	    
//	    //网络是否连接
//	    boolean is_net=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_net,false);
//	    if(is_net)
//	    {
//	    	//addData();
//	    	addMessageAndNotice();
//	    	addNotice();
//	    }
//	    else
//	    {
//	    	//vpl.doNoNet();
//	    }
//	    handler.postDelayed(run2,5000);
//	    initData();
//	    initData2();
//	}
//	
//	private void initData()
//	{  
//		String win=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.is_win);
//		if(win!=null&&!win.equals(""))
//		{
//		  WinParam wp=new WinParam();
//		  ManagerParam mp=new ManagerParam();
//		  mp.isShowDialog=false;
//		  mp.isShowMsg=false;
//		  TongYongManager tym=new TongYongManager(this,icb6,wp,mp);
//		  tym.start();
//		}
////		 String id=BeikBankApplication.getUserid();
////		 if(id!=null&&!id.equals(""))
////		 {
////	        HuodongParam hp=new HuodongParam();
////	        hp.userId=ShareMUtil.toSerialCode(Long.parseLong(id));
////	        TongYongManager tym=new TongYongManager(this,icb9,hp);
////			tym.start();
////		 }
//	   
//	}
//	/***
//	 * 每次回到首界面需要刷新的数据
//	 */
//	private void initData2()
//	{
//		String id=BeikBankApplication.getUserid();
//		HuodongParam hp=new HuodongParam();
//		 if(id!=null)
//		 {
//	        
//	        if(!id.equals(""))
//	        {
//	           hp.userId=ShareMUtil.toSerialCode(Long.parseLong(id));
//	        }
//	        else
//	        {
//	        	hp.userId="";
//	        }
////	        TongYongManager tym=new TongYongManager(this,icb9,hp);
////			tym.start();
//		 }
//		 else
//		 {
//			 hp.userId="";
//		 }
//		 TongYongManager tym=new TongYongManager(this,icb9,hp);
//		 tym.start();
//	}
//	/**
//	 * 获得活动
//	 */
//	ICallBack icb9=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				Huodong_data hd=(Huodong_data) obj;
//				ArrayList<Huodong> huo=hd.data;
//				int count=0;
//				for(Huodong h:huo)
//				{
//					if(h.isEnd.equals("0"))
//					{   
//						if(h.islook.equals("0"))
//						{
//						 count++;
//						}
//					}
//				}
//				showHongdong(count);
//			}
//		}
//	};
//	/**
//	 * 弹出窗口回调
//	 */
//	ICallBack icb6=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			
//			if(obj!=null)
//			{  
//				Win_data wd=(Win_data) obj;
//				final Win w=wd.data;
//				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.is_win,"");
//				final View view=FirstLoginWindows.addView2(act,R.layout.first_win_msg);
//				ImageView iv=(ImageView) view.findViewById(R.id.iv);
//				ImageView iv0=(ImageView) view.findViewById(R.id.iv0);
//				int width=DisplayManger.getWidth(act);
//				LayoutParams lp=new LayoutParams(width/5*4,width/5*4);
//				iv.setLayoutParams(lp);
//				ImageUrl iu=new ImageUrl(iv,w.icon);
//				iu.start();
//				iv0.setOnClickListener(new OnClickListener() 
//				{
//					
//					@Override
//					public void onClick(View v) 
//					{
//						 act.getWindowManager().removeView(view);
//					}
//				});
//				iv.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						
//					  if(w.linkUrl!=null&&!w.linkUrl.equals(""))
//					  {   
//						  
//						  Intent intent=new Intent(act,HuodongActivity.class);
//						  intent.putExtra("url",w.linkUrl);
//						  intent.putExtra("title",w.title);
//						  startActivity(intent);
//						  act.getWindowManager().removeView(view);
//					  }
//					  
//					}
//				});
//				
//			}
//		}
//	};
//	
//	
//	/**
//	 * 加载数据
//	 */
//	private void addData()
//	{   
//		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		ThreadManagerSet tms=null;
//		if(do_success)
//		{   
//			vpl.setLoginInfo();
//			
////			TotalMoneyManager tmm=new TotalMoneyManager(act, vpl.page1.icb1);
////			MoneyInfoManager mi=new MoneyInfoManager(act, vpl.page1.icb2);
////			tms.add(tmm);
////			tms.add(mi);
//			
////			tms=new ThreadManagerSet(icb4);
////			TotalMoneyParam tmp=new TotalMoneyParam();
////		    tmp.memberID=BeikBankApplication.getUserid();
////			UserCapital2Manager usm=new UserCapital2Manager(act,vpl.page1.icb4,tmp);
////			tms.add(usm);
//			
//			//vpl.page1.updateView();
//			icb4.back(null);
//			
//			
//			
//		}
//		else
//		{
//			 TableDaoSimple.delete(Message.class,null,null);
//		}
//		if(!rePage2)
//		{   
//			if(tms==null)
//			{
//				tms=new ThreadManagerSet(icb4);
//			}
//			//FundInfoManager fm=new FundInfoManager(act,vpl.page2.icb);
//			//tms.add(fm);
//			rePage2=true;
//		}
//		//第一次启动是加载公告
//		if(tms!=null&&isAddNotice)
//		{
//			if(tms!=null)
//			{
//				NoticeManager nm=new NoticeManager(act, icb5);
//				tms.add(nm);
//				isAddNotice=false;
//			}
//		}
//		if(tms!=null)
//		{   
//			dialog=Utils.createPorgressDialog(act, null);
//		    dialog.show();
//			tms.start();
//		}
//		
//		
//		
//		
//	}
//	/**
//	 * 显示活动的个数
//	 */
//	private void showHongdong(int count)
//	{
//		
//		    
//			
//			   TextView tv1=(TextView) findViewById(R.id.tv1);
//			   if(count>0)
//			   {
//			     
//			     tv1.setVisibility(View.VISIBLE);
//			     tv1.setText(count+"");
//			   }
//			   else
//			   {
//				   tv1.setVisibility(View.GONE);
//			   }
//			   
//		  
//		
//		
//	}
//	ICallBack icb5=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				showNotice(obj);
//				//showHongdong(obj);
//			}	
//		}
//	};
//	ICallBack icb4=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			
//			if(dialog!=null)
//			{   
//				dialog.dismiss();
//				if(is_first_start)
//				{   
//					addUpdateAndMsg();
//					is_first_start=false;
//				}
//			}
//		}
//	};
//	private void addUpdateAndMsg()
//	{  
//
//		CheckUpdateManager cum=new CheckUpdateManager(act,icb);
//		cum.start();
//		
//	}
//	/**
//	 * 获得消息，通知记数后更新视图
//	 */
//	public  int msgCount;
//	/**
//	 * 获得消息，通知记数后更新视图
//	 */
//	public void setMsgInfo(int count)
//	{   
//		if(count>0)
//		{
//			lml.tv_msgcount.setVisibility(View.VISIBLE);
//			lml.tv_msgcount.setText(count+"");
//			vpl.imageview_left.setBackgroundResource(R.drawable.left_return_msg);
//			
//		
//				BadgeUtil.setBadgeCount(getApplicationContext(),count);
//			
//		}
//		else
//		{   
//			if(count==0)
//			{
//				BadgeUtil.setBadgeCount(getApplicationContext(),count);
//			}
//			lml.tv_msgcount.setVisibility(View.INVISIBLE);
//			vpl.imageview_left.setBackgroundResource(R.drawable.ic_menu);
//		}
//	}
//	//设置消息数据
//private	ICallBack icb7=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//		   if(obj!=null)
//		   {
//			CommonInfo_data cd=(CommonInfo_data)obj;
//			CommonInfo ci=cd.data;
//			List<Message> lists=null;
//			int b=0;
////			try {
////			  // lists=(List<Message>) TableDaoSimple.query(com.beikbank.android.data.Message.class,null,null);
////			   b=lists.size();
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
//			//int a=Integer.parseInt(ci.noticeRecordCount)-b;
//			int a=0;
//			if(a>0)
//			{
//				msgCount=Integer.parseInt(ci.unreadedCount)+a;
//			}
//			else
//			{
//				msgCount=Integer.parseInt(ci.unreadedCount);
//			}
//			
//			
//			setMsgInfo(msgCount);
//			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,false);
//		   }
//		}
//	};
//
////	public  void updateView()
////	{    
////		//3更新银行卡信息
////		if(icb1==null)
////		{
////			 icb1=new ICallBack() {
////					
////					@Override
////					public void back(Object obj)
////					{    
////						 if(lml!=null)
////						 {
////							 lml.setMenuInfo();
////						 }
////						 if(vpl!=null)
////						 {
////							 vpl.updateView();
////						 }
////						 LogHandler.writeLogFromString(TAG,"icb1");
////						 Log.e("home","icb1");
////						 BankListManager bm=new BankListManager(act,icb2);
////						 bm.start();
////					}
////				};
////		}
////          if(icb2==null)
////          {
////  			//4更新基金信息
////  			icb2=new ICallBack() {
////  				
////  				@Override
////  				public void back(Object obj)
////  				{   
////  				    	 vpl.page2.updateView(icb3);
////  				         Log.e("home","icb2");
////  				    	 LogHandler.writeLogFromString(TAG,"icb2");
////  				}
////  			};
////          }
////          if(icb3==null)
////          {
////  			//5更新账户信息
//// 			 icb3=new ICallBack() {
//// 				
//// 				@Override
//// 				public void back(Object obj)
//// 				{   
//// 					boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//// 					Log.e("home","icb30");
//// 					if(BeikBankConstant.DO_SUCCESS1_VALUE.equals(do_success))
//// 					{
//// 		               vpl.page1.updateView();
//// 		               LogHandler.writeLogFromString(TAG,"icb3");
//// 		               Log.e("home","icb3");
//// 					}
//// 				}
//// 			};
////          }
////
////			
////	}
//	//检查更新
//	ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj)
//		{   
//			if(obj!=null)
//			{
//				lml.setNew();
//			}
//		
//		}
//	};
////	//3更新银行卡信息
////	public ICallBack icb1;
////	//4更新基金信息
////	public ICallBack icb2;
////	//5更新账户信息
////	public ICallBack icb3;
//    public void loginCheck()
//    {
//    	String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//    	if("".equals(phonenumber))
//    	{
//    		
//    	}
//    }
//	public void initIntent()
//	{
//		Intent intent=getIntent();
//		boolean error=intent.getBooleanExtra("global_error", false);
//		if(error)
//		{
//			MessageManger.showMeg(this,getString(R.string.app_error),Toast.LENGTH_LONG);
//		}
//	}
////	//支持银行接口
////	JsonHttpResponseHandler getBankListInfoHandler = new JsonHttpResponseHandler(){
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
////		}
////
////		@Override
////		public void onSuccess(Response response) {
////			Gson gson=new Gson();
////			BankListInfo bankListInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
////					BeikBankConstant.TYPE_JSONDATA), BankListInfo.class);
////			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////			if(result.equals("success")){
////				boolean hasUpdate=bankListInfo.isHasUpdate();
////				if(hasUpdate){
////				
////					ArrayList<BankInfo> list=bankListInfo.getBankList();
////					BankInfoDao.getInstance(getApplicationContext()).deleteAll();
////					BankInfoDao.getInstance(getApplicationContext()).insertBankInfoList(list);	
////					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.VERSION, 
////							bankListInfo.getVersion());
////					
////				}
////
////			}
////
////		}
////
////	};
//	
////	//检查版本更新接口	
////	JsonHttpResponseHandler getUpdateInfoHandler = new JsonHttpResponseHandler(){
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
////			Toast.makeText(HomeActivity2.this,"网络没有连接",Toast.LENGTH_SHORT).show();
////			Utils.log(TAG, "onFailure()"+content);			
////		}
////
////		@Override
////		public void onSuccess(Response response) {
////			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
////			Gson gson=new Gson();
////			UpdateInfo updateInfo=gson.fromJson(Utils.getJsonResult(response.getResponseString(), 
////					BeikBankConstant.TYPE_JSONDATA), UpdateInfo.class);
////			
////			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////			if(result.equals("success")){
////				boolean hasUpdate=updateInfo.isHasUpdate();
////				if(hasUpdate){
////					Message msg = new Message();  
////		            msg.what = UPDATA_CLIENT;
////		            msg.obj = updateInfo.getDownLoadUrl();
////		            handler.sendMessage(msg);
////				}
////				
////			}
////
////		}
////
////	};
//	
	//显示需要更新的dialog
//	public void showUpdataDialog(final String downloadUrl)
//	{
//		upgrade_version_dialog=Utils.createSimpleDialog(this,
//				getString(R.string.upgrade_version),getString(R.string.update),new BeikBankDialogListener() {
//
//			@Override
//			public void onRightBtnClick() {
//				// TODO Auto-generated method stub
//				downloadApk(downloadUrl);
//			}
//
//			@Override
//			public void onListItemLongClick(int position, String string) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onListItemClick(int position, String string) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onLeftBtnClick() {
//				// TODO Auto-generated method stub
//				//upgrade_version_dialog.dismiss();
//				finish();
//			}
//
//			@Override
//			public void onCancel() {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		upgrade_version_dialog.show();
//	}	
//
//
// 
//	
//
//
//
//	
//	private void initView()
//	{
//		if(lml==null||vpl==null)
//		{
//			 lml=new LeftMenuLinearLayout(this);
//			 setBehindContentView(lml);
//			 vpl=new ViewPageLinearLayout(this);
//			 setContentView(vpl);
//			 //lml.setMenuInfo();
//		}
//		else
//		{
//			 lml=new LeftMenuLinearLayout(this);
//			 setBehindContentView(lml);
//			 vpl=new ViewPageLinearLayout(this);
//			 setContentView(vpl);
//		}
//	}
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		// TODO Auto-generated method stub
//		super.onNewIntent(intent);
//		// dialog=Utils.createPorgressDialog(act, null);
//        // dialog.show();
//        // new LoadData().start();
//		int intflag=BeikBankApplication.mSharedPref.getSharePrefInteger(BeikBankConstant.HOME_TYPE);
//      //  initView();
//		if(intflag==5){
//			//用户设置手势密码后
//			//lml.setMenuInfo();
//			//lml.setGestureInfo();
//			vpl.setLoginInfo();
//		}else if(intflag==4){
//			//用户购买基金成功后跳到总资产页面
//			//lml.setMenuInfo();
//			//vpl.refreshContentInfo();//重新请求产品信息和资产信息接口
//			vpl.setLoginInfo();
//		}else if(intflag==3){
//			///当未登录用户点击购买，登录成功返回
//			//lml.setMenuInfo();
//			vpl.setLoginInfo();
//			//toggle();
//			//vpl.toTntent(1);
//		}else if(intflag==2){
//			//当未登录用户点击取现，登录成功返回
//			//lml.setMenuInfo();
//			vpl.setLoginInfo();
//			//toggle();
////			pageFragment.toTntent(0);
//		}else if(intflag==0){
//			//当未登录用户在menu页面点击登录，登录成功返回主页
//			//lml.setMenuInfo();
//			vpl.setLoginInfo();
//			//toggle();
//		}else if(intflag==1){
//			//退出登录
//			//lml.setMenuInfo();
//			//lml.setGestureInfo();
//			vpl.setLoginInfo();
//			//toggle();
//		}
//		
//	}
//	public void outLogin()
//	{
//		//退出登录
//		lml.setMenuInfo();
//		//lml.setGestureInfo();
//		vpl.mPager.setCurrentItem(0);
//		vpl.setLoginInfo();
//		toggle();
//	}
//	public PopupWindow window;
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN
//				&&!mSlidingMenu.isMenuShowing()){
//			//当有分享时候，点击返回键，先关闭分享
//			if(vpl.isShareShowing){
//				vpl.isShareShowing=false;
//				vpl.performAnimateForHide();
//			}
//			if(window!=null&&window.isShowing())
//			{
//				window.dismiss();
//			}
//			
//			else if((System.currentTimeMillis()-exitTime) > 2000){  
//				Toast.makeText(HomeActivity2.this, getString(R.string.second_time_exit), Toast.LENGTH_SHORT).show();                                
//				exitTime = System.currentTimeMillis();   
//			} else {
//				finish();
//				System.exit(0);
//			}
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		//initView();
////		lml.setGestureInfo();
////		vpl.setContentInfo();
//		lml.setMenuInfo();
//		setMsgInfo(msgCount);
//		initData2();
//		if(vpl.page1!=null)
//		{
//			vpl.page1.init1();
//			//vpl.page4.init(act);
//		}
//		vpl.setPage();
//	}
//	
//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//	}
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//	}
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		mSlidingMenu=null;
//		lml=null;
//		vpl=null;
//	}
//	//以下是网络部分--------------------------------------------------------------------------------------------------------
//	
//	
//	@Override
//	protected void onRestart() {
//		super.onRestart();
//		//initView();
////		//是否登录
////		boolean is_login=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_log,false);
////		if(is_login)
////		{   
////			
////			mSlidingMenu.showContent();
////			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,false);
////		}
//		//lml.setGestureInfo();
//		vpl.setContentInfo();
//		//lml.setMenuInfo();
//		//是否刷新界面
//		boolean re_home=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.re_home,false);
//		boolean is_notice=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_notice,false);
//		if(is_notice)
//		{   
//			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_notice,false);
//			new NoticeManager(act, icb5).start();
//		}
//		if(re_home)
//		{
//			vpl.setLoginInfo();
//			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,false);
//			//addData();
//			rehome();
//		}
//		
//	}
//	/**
//	 * 从新加载界面数据
//	 */
//	private void rehome()
//	{
////		if(vpl!=null)
////		{
////			if(vpl.page1!=null)
////			{
////				vpl.page1.addData();
////			}
////			if(vpl.page2!=null)
////			{
////				vpl.page2.addData();
////			}
////			if(vpl.page4!=null)
////			{
////				vpl.page4.addData2();
////				Log.e("rehome","rehome");
////			}
////			addNotice();
////		}
////		else
////		{
////			initView();
////		}
//		initView();
//	}
//	/**
//	 * 加载消息
//	 */
//	public void addMessageAndNotice()
//	{   
////		TotalMoneyParam tlp=new TotalMoneyParam();
////    	tlp.memberID=BeikBankApplication.getUserid();
////    	if(tlp.memberID==null)
////    	{
////    		tlp.memberID="";
////    	}
////    	CommonInfoManager cim=new CommonInfoManager(act,icb7,tlp);
////    	cim.start();
//		new MessageUtil(icb10, act);
//	}
//	/**
//	 * 加载未读消息和通知回调
//	 */
//	ICallBack icb10=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			msgCount=(Integer) obj;
//			setMsgInfo(msgCount);
//		}
//	};
//	@Override
//	protected void onStart() {
//		super.onStart();
//		boolean is_login=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_log,false);
//		if(is_login)
//		{
//			addMessageAndNotice();
//		}
////		//是否登录
////		boolean is_login=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_log,false);
////		if(is_login)
////		{   
////			boolean b=mSlidingMenu.isMenuShowing();
////			//mSlidingMenu.toggle();
////			//mSlidingMenu.showContent();
////			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_log,false);
////		}
//	}
//	Timer timer;
//	TimerTask tt;
//	/**
//	 * 加载公告
//	 */
//	public void addNotice()
//	{
//		NoticeManager nm=new NoticeManager(act, icb5);
//		nm.start();
//	}
//   //显示公告
//	/**
//	 * 如果obj==null 隐藏公告
//	 * @param obj
//	 */
//	public void showNotice(Object obj)
//	{   
//		if(obj==null)
//		{
//			vpl.ll_notice.setVisibility(View.GONE);
//			return;
//		}
//		Notice_data nd=(Notice_data) obj;
//		Notice n=nd.data;
//		if(n.content==null||"".equals(n.content))
//		{
//			vpl.ll_notice.setVisibility(View.GONE);
//		}
//		else
//		{
//			vpl.ll_notice.setVisibility(View.VISIBLE);
//			vpl.tv_notice.setText(n.content);
//			vpl.tv_notice.setFocusable(true);
//			vpl.tv_notice.setFocusableInTouchMode(true);
//			handler.postDelayed(run,1000);
//		}
//	}
//	Runnable run2=new Runnable() {
//		
//		@Override
//		public void run() {
//			if(lml!=null)
//			{
//				lml.checkUpdate();
//				 
//			}
//			
//		}
//	};
//    Runnable run=new Runnable() {
//		
//		@Override
//		public void run() {
//			vpl.tv_notice.requestFocus();
//			handler.removeCallbacks(run);
//		}
//	};
//	Handler handler=new Handler();
//}
