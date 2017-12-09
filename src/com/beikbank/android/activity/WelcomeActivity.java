package com.beikbank.android.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.test.RenamingDelegatingContext;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.conmon.Cancal;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.dao.ApplicationUpdate;
import com.beikbank.android.dao.DbManagerFactory;
import com.beikbank.android.dao.TableDao;
import com.beikbank.android.data.Action;
import com.beikbank.android.data.Action_data;
import com.beikbank.android.data.ShoushiIsSet_data;
import com.beikbank.android.data2.LoginQian;
import com.beikbank.android.data2.LoginQian2;
import com.beikbank.android.data2.LoginQian_data;
import com.beikbank.android.data2.UserCheck2;
import com.beikbank.android.data2.UserCheck2_data;
import com.beikbank.android.data2.UserCheck_data;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.data2.getUserOrXiuGai;
import com.beikbank.android.data2.getUserOrXiuGai_data;
import com.beikbank.android.dataparam.ShoushiCreParam;
import com.beikbank.android.dataparam.ShoushiIsParam;
import com.beikbank.android.dataparam.ShoushiIsSetParam;
import com.beikbank.android.dataparam.TuisongParam;
import com.beikbank.android.dataparam2.BindBankQinQiuParam;
import com.beikbank.android.dataparam2.BindBankQueQengParam;
import com.beikbank.android.dataparam2.GetCanPingLieBiaoParam;
import com.beikbank.android.dataparam2.GetChanPinParam;
import com.beikbank.android.dataparam2.JiaoYiJiLuParam;
import com.beikbank.android.dataparam2.LoginParam;
import com.beikbank.android.dataparam2.LoginQianParam;
import com.beikbank.android.dataparam2.LunBoParam;
import com.beikbank.android.dataparam2.NameRengzhenParam;
import com.beikbank.android.dataparam2.RegisterParam;
import com.beikbank.android.dataparam2.ShuJuQianYi1Param;
import com.beikbank.android.dataparam2.UserCheckParam;
import com.beikbank.android.dataparam2.UserCheckParam2;
import com.beikbank.android.dataparam2.XiaoxiLieBiaoParam;
import com.beikbank.android.dataparam2.XiaoxiParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.dataparam2.getUserOrXiuGaiParam;
import com.beikbank.android.dataparam2.getUserZhiChanParam;
import com.beikbank.android.dataparam2.getYiGouParam;
import com.beikbank.android.dataparam2.getZaiQuanParam;
import com.beikbank.android.dataparam2.setJiaoyiPasswdParam;
import com.beikbank.android.dataparam2.setLoginPasswdParam;
import com.beikbank.android.exception.GlobalExceptionHandler;
import com.beikbank.android.fragment.BeikBankApplication;

import com.beikbank.android.jni.AnquanManager;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.ActionManager;
import com.beikbank.android.net.impl.BankListManager;
import com.beikbank.android.net.impl.LoginManager;
import com.beikbank.android.net.impl.ThreadManagerSet;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;

import com.beikbank.android.utils.BadgeUtil;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.HongdianUtil;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.utils2.TimeUtil;
import com.umeng.message.UmengRegistrar;
import coma.beikbank.android.R;



//欢迎页
public class WelcomeActivity extends BaseActivity1{

	private TextView textview_version;
	/**
	 * 活动
	 */
	private Action action;
	/**
	 * 首发
	 */
	private RelativeLayout rl_shouhu;
	Activity act=this;
	long start;
	long end;
	
	ICallBack icb5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		this.setContentView(R.layout.activity_welcome);
		StateBarColor.init(this,0xffffffff);
		
	if(1==2)
	{
       ICallBack icb=new ICallBack() {
		   @Override
		   public void back(Object obj) {

		   }
	   };

		ShuJuQianYi1Param sp=new ShuJuQianYi1Param();
		sp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym=new TongYongManager2(this,icb,sp);
		tym.start();
		Intent intent=new Intent(this,ShuJuQianYiActivity3.class);
		startActivity(intent);
		return;
	}
		if(1==2)
		{   



//			String ip=RegisterCodeInputActivity.getIPAddress(act);
//			Log.e("ip",ip);
			
			ICallBack icb=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					
					if(obj!=null)
					{
//						   LoginQian_data ld=(LoginQian_data) obj;
//						   LoginQian2 lq2=ld.body;
//						   LoginQian lq=lq2.data.get(0);
//						   
//						   
//						   LoginParam lp=new LoginParam();
//						   lp.login_password=MD5.md5s32("123456");
//						   lp.login_type="0";
//						   lp.phone_number="15158157606";
//						   lp.user_code=lq.user_code;
//						   lp.user_type=lq.user_type;
//						   
//							TongYongManager2 tym=new TongYongManager2(act, icb5,lp);
//					    	tym.start();
						   
					}
				}
			};
			
		    
//			icb5=new ICallBack() {
//				
//				@Override
//				public void back(Object obj) {
//					
//				}
//			};
//			
//			Intent intent=new Intent(this,HuodongActivity2.class);
//			startActivity(intent);
			
		

//			
//			
//			
//			
//			
//			
//			
//			
//			
//			
			return;
//			ICallBack icb1=new ICallBack() {
//				
//				@Override
//				public void back(Object obj) {
//					
//					
//				}
//			};
//			
//		    XiaoxiParam xip=new XiaoxiParam();
//		    xip.userid=BeikBankApplication.getUserid();
//		    xip.type="-1";
//		    xip.req_time="";
//		    TongYongManager2 tym=new TongYongManager2(act, icb1,xip);
		    //tym.start();
		    //return;
		}
		//tuisong();
		ApplicationUpdate.doData(this);
		//souhu();
		initView();
		//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_after_pay,true);
	    initData();
	    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.one_open,true);
	    
	    
	  
	    BadgeUtil.setBadgeCount(act, 0);
	    
	}
	
	private void initData()
	{
		BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.is_win,"win");
		ShoushiIsSetParam  ssp=new ShoushiIsSetParam();
		String id=BeikBankApplication.getUserid();
		if(id==null||id.equals(""))
		{
			return;
		}
		ssp.type="2";
		ssp.userId=id;
    	ICallBack icb_shoushi=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					ShoushiIsSet_data sd=(ShoushiIsSet_data) obj;
					if(sd.data.equals("0"))
					{
						BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,true);
					}
				}
				
			}
		};
    	
//    	TongYongManager tym=new TongYongManager(this, icb_shoushi,ssp);
//    	tym.start();
	
	}
	//第一次打开时加载推送信息
	private void tuisong()
	{
	    boolean first_open=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.first_open,false);
		if(!first_open)
		 {   
			 TuisongParam tp=new TuisongParam();
			 tp.rasType="2";
			 tp.umDeviceToken=UmengRegistrar.getRegistrationId(act);
			 if(tp.umDeviceToken==null||"".equals(tp.umDeviceToken))
			 {
				 return;
			 }
			 if(tp.umDeviceToken==null)
			 {
				 tp.umDeviceToken="";
			 }
			 TongYongManager tym=new TongYongManager(act, icb4,tp);
			 tym.start();
		 }
	}
	ICallBack icb4=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.first_open,true);
			}
			
		}
	};
	public boolean souhu;
    private void souhu()
    {
    	if(SystemConfig.SOURCES_CODE.equals(Cancal.CODE_360))
    	{   
    		souhu=true;
    		start=System.currentTimeMillis();
    		rl_shouhu=(RelativeLayout) findViewById(R.id.rl_shouhu);
    		rl_shouhu.setVisibility(View.VISIBLE);
    	}
    }
	public void initView(){
		textview_version=(TextView)findViewById(R.id.textview_version);
		String version=Utils.getVersion(WelcomeActivity.this);
		textview_version.setText(BeikBankApplication.getVersion(this));//版本号角标
		
		
//		new Handler().postDelayed(new Runnable() {  
//			public void run() {  
////				boolean isBindGesture=BeikBankApplication.mSharedPref.getSharePrefBoolean(
////						BeikBankConstant.IS_BINDGESTURE, false);
////				String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
////				if(isBindGesture&&!phonenumber.equals("")){
////					Intent intent=new Intent(WelcomeActivity.this,GesturePwdUnlockActivity.class);
////					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,phonenumber);
////					startActivity(intent);
////					finish();
////
////				}else{
////					startAimActivity(HomeActivity2.class);
////					finish();
////				}
//				startAimActivity(HomeActivity2.class);
//				finish();
//			}  
//
//		}, 1000);
		boolean b=AnquanManager.isZhengBan(this);
		if(!b&&!SystemConfig.isDebug())
		{   
		    TextView tv=(TextView)findViewById(R.id.tv);
			tv.setVisibility(View.VISIBLE);
			return;
		}
		checkNet();

	}
	/**
	 * 检查网络是否连接
	 */
	public void checkNet()
	{
		boolean isNet=isNetworkConnected(this);
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_net,isNet);
		//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		if(isNet)
		{
			addData();
		}
		else
		{
			startAimActivity(HomeActivity4.class);
			finish();
		}
	}
	UserCheck2_data uc2d;
	/**
	 * 登录加载和银行卡信息
	 */
	public void addData()
	{
//		ThreadManagerSet tms=new ThreadManagerSet(icb);
//	//	LoginManager lm=new LoginManager(this,null,null,0, icb2);
//		BankListManager bl=new BankListManager(this, icb2);
//		ActionManager am=new ActionManager(this, icb3);
//		
//		
//		//tms.add(lm);
//		tms.add(bl);
//		tms.add(am);
//		tms.start();
		String phone=BeikBankApplication.getPhoneNumber();
    	if(phone==null||"".equals(phone))
    	{
    		 Intent intent=new Intent(this,HomeActivity4.class);
			 startActivity(intent);
			 finish();
			 return;
    	}
	    /**
	     * 得到用户实名等信息回调
	     */
	   ICallBack icb_uc2=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					uc2d=(UserCheck2_data) obj;
					UserCheck2 uc2=uc2d.body;
					BeikBankApplication.setSharePref(BeikBankConstant.is_bindbank,uc2.is_bind_card);
					BeikBankApplication.setSharePref(BeikBankConstant.is_shimin,uc2.is_real_name);
					BeikBankApplication.setSharePref(BeikBankConstant.is_olduser,uc2.is_new_user);
					BeikBankApplication.setSharePref(BeikBankConstant.is_jiaoyi,uc2.is_tra_password);
					
				}
				
			}
		};
		
		UserCheckParam2 uc2=new UserCheckParam2();
    	uc2.check_type="1";
    	uc2.phone_number=BeikBankApplication.getPhoneNumber();
    	
     	TongYongManager2 tym2=new TongYongManager2(act, icb_uc2,uc2);
    	tym2.start();
		
		
		
		 String code=BeikBankApplication.getUserCode();
		 if(code==null||"".equals(code))
		 {
			 Intent intent=new Intent(this,HomeActivity4.class);
			 startActivity(intent);
			 return;
		 }
         ICallBack icb_qianbao=new ICallBack() {
			
			@Override
			public void back(Object obj) {
			 if(obj!=null)
			 {
				 getQianBao_data gd=(getQianBao_data) obj;
				 getQianBao gb=gd.body.card;
				 BeikBankApplication.setSharePref(BeikBankConstant.qianbao,gb.acc_amount);
				 BeikBankApplication.setSharePref(BeikBankConstant.bank,gb.acc_number);
				 BeikBankApplication.setSharePref(BeikBankConstant.bank_name,gb.bank_name);
				 BeikBankApplication.setSharePref(BeikBankConstant.bank_max_amount,gb.max_amount);
				 BeikBankApplication.setSharePref(BeikBankConstant.bank_min_amount,gb.min_amount);
				 BeikBankApplication.setSharePref(BeikBankConstant.zhanghao,gb.acc_id);
				 BeikBankApplication.setSharePref(BeikBankConstant.icon_url,gb.icon_url);
				 BeikBankApplication.setSharePref(BeikBankConstant.logo_url,gb.logo_url);
				 Intent intent=new Intent(act,HomeActivity4.class);
				 startActivity(intent);
			 }
			 else
			 {
				 Intent intent=new Intent(act,HomeActivity4.class);
				 startActivity(intent);
			 }
				finish();
			}
		};
    	getQianBaoParam gqp=new getQianBaoParam();
		gqp.acc_type_id="1";
	    gqp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym3=new TongYongManager2(act, icb_qianbao, gqp);
		tym3.start();
		
		
		
		
		
		ICallBack icb_gu=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					 getUserOrXiuGai_data gd=(getUserOrXiuGai_data) obj;
					 getUserOrXiuGai gu=gd.body.get(0);
					 BeikBankApplication.setSharePref(BeikBankConstant.real_name,gu.real_name);
				}
			}
		};
		
		
		  getUserOrXiuGaiParam gu=new getUserOrXiuGaiParam();
  		  gu.user_code=BeikBankApplication.getUserCode();
  		  gu.operation_type="1";
  		  TongYongManager2 tym4=new TongYongManager2(this, icb_gu,gu);
  		  tym4.start();
	    
	
	}
	/**
	 * 数据全部加载完成调用该方法
	 */
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(souhu)
			{
		    	while(true)
			 {   
				end=System.currentTimeMillis();
				long time=end-start;
				if(time>2000)
				{
					
					break;
				}
			 }
			}
			if(action!=null)
			{
				Intent intent=new Intent(act,ActionActivity.class);
				intent.putExtra("action",action);
				startActivity(intent);
			}
			else
			{
				boolean first=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.first_open,true);
				if(first)
				{
					startAimActivity(YingdaoActivity.class);
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.first_open,false);
				}
				else
				{ 
					startAimActivity(HomeActivity4.class);
				}
			}
			finish();
		}
	};
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			
		}
	};
	/**
	 * 加载广告页
	 */
	ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
			   Action_data ad=(Action_data) obj;
			   action=ad.data;
			}
		}
	};
	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				if(HomeActivity3.ha!=null)
				{
					HomeActivity3.ha.finish();
				}
			}
		return super.onKeyDown(keyCode, event);
	}
//------------------------------------------------------------------------

}
