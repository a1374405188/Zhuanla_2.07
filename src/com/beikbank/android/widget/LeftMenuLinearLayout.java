//package com.beikbank.android.widget;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import com.beikbank.android.R;
//import com.beikbank.android.activity.AboutActivity;
//import com.beikbank.android.activity.AboutActivity_bak;
//import com.beikbank.android.activity.ActionListActivity;
//import com.beikbank.android.activity.BankBindActivity;
//import com.beikbank.android.activity.BankBindActivity2;
//import com.beikbank.android.activity.BankMasterActivity;
//import com.beikbank.android.activity.BankSupportActivity;
//import com.beikbank.android.activity.FeedbackActivity;
//import com.beikbank.android.activity.GesturePwdGuideActivity2;
//import com.beikbank.android.activity.GesturePwdOldActivity;
//import com.beikbank.android.activity.HelpCenterActivity;
//import com.beikbank.android.activity.HomeActivity2;
//import com.beikbank.android.activity.HuodongActivity;
//import com.beikbank.android.activity.LoginPwdUpdateActivity;
//import com.beikbank.android.activity.LoginRegActivity;
//import com.beikbank.android.activity.MessageActivity;
//import com.beikbank.android.activity.QianbaoActivity1;
//import com.beikbank.android.activity.RealnameActivity;
//import com.beikbank.android.activity.TransactionPwdSetActivity;
//import com.beikbank.android.activity.TransactionPwdUpdateActivity;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.conmon.SystemConfig;
//import com.beikbank.android.dao.ApplicationUpdate;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.dao.UserInfoDao;
//import com.beikbank.android.data.BindBankCard;
//import com.beikbank.android.data.UpdateInfo;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.exception.LogHandler;
//import com.beikbank.android.fragment.BeikBankApplication;
//
//import com.beikbank.android.http.Response;
//import com.beikbank.android.net.HandlerBase;
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.impl.CheckUpdateManager;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.BeikBankDialogListener;
//import com.beikbank.android.utils.LockPatternUtils;
//import com.beikbank.android.utils.LoginManager;
//import com.beikbank.android.utils.Utils;
//import com.google.gson.Gson;
//import com.nineoldandroids.animation.Animator;
//import com.nineoldandroids.animation.ObjectAnimator;
//import com.nineoldandroids.animation.ValueAnimator;
//import com.nineoldandroids.animation.Animator.AnimatorListener;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.LinearInterpolator;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import  android.view.View.OnClickListener;
// /**
// *copyright 喻国合 
// *email: 1374405188@qq.com
// *2014-12-11
// **/
//public class LeftMenuLinearLayout extends LinearLayout implements OnClickListener{
//	
//	public LeftMenuLinearLayout(Context context){
//		super(context);
//		act=(HomeActivity2) context;
//		init();
//	}
//	public LeftMenuLinearLayout(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		act=(HomeActivity2) context;
//		init();
//	}
//	private HomeActivity2 act;
//	private final String TAG="LeftMenuLinearLayout";
//	private RelativeLayout relative_bank,relative_more;
//	//密码，银行卡，更多，版本,消息，活动
//	private LinearLayout linear_password_child,linear_bank_child,linear_more_child,relative_version,ll_xiaoxi,
//	linear_gesture_parent,linear_bankmaster_child;
//	
//	private RelativeLayout relative_zhichan;
//	private ImageView imageview_avarta,imageview_versionnew;
//	private TextView textview_loginname,textview_change_password,textview_change_transacpassword,
//	textview_switch_gesturepassword,textview_change_gesturepassword,textview_supportbank,
//	textview_masterbank,textview_helpcenter,textview_feedback,textview_aboutbk,
//	textview_enourage,textview_realname;
//	private TextView textview_versioncode,textview_logout;
//	private SwitchButton switch_gesture_unlock;
//	private Dialog upgrade_version_dialog,newest_version_dialog,change_account_dialog;
//	//private final int UPDATA_CLIENT=0;
//	//private final int DOWN_ERROR=1;
//	//private final int NEWEST_CLIENT=2;
//	//头部登录信息,实名认证
//	LinearLayout ll_head,ll_ll1;
//	LinearLayout relative_password;
//	//设置交易密码
//	TextView set_tpasswd;
//	//绑定银行卡
//	TextView tv_bindbank;
//	/**
//	 * 消息记数
//	 */
//	public TextView tv_msgcount;
//	/**
//	 * 是否设置交易密码
//	 */
//	private LinearLayout ll_is_buy;
//
//    private LinearLayout ll_huodong;
//    ImageView  img_passwd;
//	public void init() {
//        LinearLayout ll=new LinearLayout(act);
//		View rootView = act.getLayoutInflater().inflate(R.layout.fragment_menu,ll,false);
//		ll_is_buy=(LinearLayout) rootView.findViewById(R.id.linear_buy_parent);
//		ll_head=(LinearLayout) rootView.findViewById(R.id.ll_head);
//		
//		tv_msgcount= (TextView) rootView.findViewById(R.id.tv_msgcount);
//		tv_more=(TextView) rootView.findViewById(R.id.tv_more);
//		textview_bank=(TextView) rootView.findViewById(R.id.textview_bank);
//		img_passwd=(ImageView) rootView.findViewById(R.id.img_passwd);
//		set_tpasswd=(TextView)rootView.findViewById(R.id.tv_set_transacpassword);
//		tv_bindbank=(TextView)rootView.findViewById(R.id.tv_bindbank);
//		
//		imageview_avarta=(ImageView)rootView.findViewById(R.id.imageview_avarta);
//		imageview_avarta.setOnClickListener(this);
//		textview_loginname=(TextView)rootView.findViewById(R.id.textview_loginname);
//		textview_loginname.setOnClickListener(this);
//
//		textview_password=(TextView)rootView.findViewById(R.id.textview_password);
//		relative_password=(LinearLayout) rootView.findViewById(R.id.relative_password);
//		relative_bank=(RelativeLayout)rootView.findViewById(R.id.relative_bank);
//		relative_more=(RelativeLayout)rootView.findViewById(R.id.relative_more);
//		
//		relative_zhichan=(RelativeLayout)rootView.findViewById(R.id.relative_zhichan);
//		relative_version=(LinearLayout)rootView.findViewById(R.id.relative_version);
//
//		//image_arrow_password=(ImageView)rootView.findViewById(R.id.image_arrow_password);
//		//image_arrow_bank=(ImageView)rootView.findViewById(R.id.image_arrow_bank);
//		//image_arrow_more=(ImageView)rootView.findViewById(R.id.image_arrow_more);
//		imageview_versionnew=(ImageView)rootView.findViewById(R.id.imageview_versionnew);
//		relative_password.setOnClickListener(this);
//		relative_bank.setOnClickListener(this);
//		relative_more.setOnClickListener(this);
//		relative_version.setOnClickListener(this);
//		relative_zhichan.setOnClickListener(this);
//		
//		ll_xiaoxi=(LinearLayout)rootView.findViewById(R.id.ll_xiaoxi);
//		ll_huodong=(LinearLayout)rootView.findViewById(R.id.ll_huodong);
//		linear_password_child=(LinearLayout)rootView.findViewById(R.id.linear_password_child);
//		linear_bank_child=(LinearLayout)rootView.findViewById(R.id.linear_bank_child);
//		linear_more_child=(LinearLayout)rootView.findViewById(R.id.linear_more_child);
//		switch_gesture_unlock=(SwitchButton)rootView.findViewById(R.id.switch_gesture_unlock);
//		linear_gesture_parent=(LinearLayout)rootView.findViewById(R.id.linear_gesture_parent);
//		linear_bankmaster_child=(LinearLayout)rootView.findViewById(R.id.linear_bankmaster_child);
//
//		textview_change_password=(TextView)rootView.findViewById(R.id.textview_change_password);
//		textview_change_transacpassword=(TextView)rootView.findViewById(R.id.textview_change_transacpassword);
//		textview_switch_gesturepassword=(TextView)rootView.findViewById(R.id.textview_switch_gesturepassword);
//		textview_change_gesturepassword=(TextView)rootView.findViewById(R.id.textview_change_gesturepassword);
//		textview_supportbank=(TextView)rootView.findViewById(R.id.textview_supportbank);
//		textview_masterbank=(TextView)rootView.findViewById(R.id.textview_masterbank);
//		textview_helpcenter=(TextView)rootView.findViewById(R.id.textview_helpcenter);
//		textview_feedback=(TextView)rootView.findViewById(R.id.textview_feedback);
//		textview_aboutbk=(TextView)rootView.findViewById(R.id.textview_aboutbk);
//		textview_enourage=(TextView)rootView.findViewById(R.id.textview_enourage);
//		textview_versioncode=(TextView)rootView.findViewById(R.id.textview_versioncode);
//		textview_logout=(TextView)rootView.findViewById(R.id.textview_logout);
//		textview_realname=(TextView)rootView.findViewById(R.id.textview_realname);
//		
//		ll_xiaoxi=(LinearLayout)rootView.findViewById(R.id.ll_xiaoxi);
//		
//		ll_xiaoxi.setOnClickListener(this);
//        ll_huodong.setOnClickListener(this);
//		
//		textview_change_password.setOnClickListener(this);
//		textview_change_transacpassword.setOnClickListener(this);
//		textview_switch_gesturepassword.setOnClickListener(this);
//		textview_change_gesturepassword.setOnClickListener(this);
//		textview_supportbank.setOnClickListener(this);
//		textview_masterbank.setOnClickListener(this);
//		textview_helpcenter.setOnClickListener(this);
//		textview_feedback.setOnClickListener(this);
//		textview_aboutbk.setOnClickListener(this);
//		textview_enourage.setOnClickListener(this);
//		textview_logout.setOnClickListener(this);
//		textview_realname.setOnClickListener(this);
//		set_tpasswd.setOnClickListener(this);
//		tv_bindbank.setOnClickListener(this);
//		boolean flag=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
//				false);
//		if(!flag){
//			linear_gesture_parent.setVisibility(View.GONE);
//			switch_gesture_unlock.setChecked(false,false);
//			
//		}else{
//			linear_gesture_parent.setVisibility(View.VISIBLE);
//			switch_gesture_unlock.setChecked(true,false);
//		}
//	
//		switch_gesture_unlock.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
////				if(isChecked){
////					linear_gesture_parent.setVisibility(View.VISIBLE);
////					setGuese(isChecked);
////				}else{
////					linear_gesture_parent.setVisibility(View.GONE);
////					Intent intent=new Intent(act,GesturePwdOldActivity.class);
////					intent.putExtra(BeikBankConstant.INTENT_CHANGEGESTURE, false);//关闭手势密码
////					act.startActivity(intent);
////				}
//                setGuese(isChecked,switch_gesture_unlock.isOnlick);
//			}
//		});
//
//		//setMenuInfo();	//初始化信息
//		//String version=Utils.getVersion(act);
//		textview_versioncode.setVisibility(View.VISIBLE);
//		imageview_versionnew.setVisibility(View.GONE);
//		textview_versioncode.setText("v"+Utils.getVersion(act));
//		//BeikBankApi.getInstance().checkUpdateInfo(act, version, "1", getUpdateInfoHandler2);
//
//		addView(rootView);
//		
//	}
//
//	
//	
//	
//	/**
//	 * 关闭手势密码校检成功后进行处理
//	 */
//	public void setGueseImg()
//	{    
//		     boolean is_open=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);  
//			 linear_gesture_parent.setVisibility(View.GONE);
//			 if(is_open)
//			 {   linear_gesture_parent.setVisibility(View.VISIBLE);
//				 switch_gesture_unlock.setChecked(true,false);
//
//			 }
//			 else
//			 {
//				 switch_gesture_unlock.setChecked(false,false);
//				 linear_gesture_parent.setVisibility(View.GONE);
//			 }
//			 
//
//	}
//
//	/**
//	 * 设置手势密码的状态
//	 * @param b
//	 */
//	public void setGuese(boolean b,boolean isFounce)
//	{        
//			 if(b)
//			 {   
//				 if(isFounce)
//				 {
//					 startAimActivity(GesturePwdGuideActivity2.class);
//				 }
//				 //linear_gesture_parent.setVisibility(View.VISIBLE);
//			 }
//			 else
//			 {  
//				 if(isFounce)
//				 {
//					 Intent intent=new Intent(act,GesturePwdOldActivity.class);
//					 intent.putExtra(BeikBankConstant.INTENT_CHANGEGESTURE, false);//关闭手势密码
//					 act.startActivity(intent);
//				 }
//				 //linear_gesture_parent.setVisibility(View.GONE);
//			 }
//	}
//    public void setNew()
//    {
//    	if(imageview_versionnew!=null)
//    	{
//    		imageview_versionnew.setVisibility(View.VISIBLE);
//    	}
//    }
//    /**
//     * 设置登录信息
//     */
//    public void initView1(boolean islogin,boolean isreanl,String phonenumber)
//    {   
//    	LinearLayout ll=null;
//    	ll_head.removeAllViews();
//    	if(islogin)
//    	{
//    		if(isreanl)
//    		{
//    			ll=(LinearLayout) act.getLayoutInflater().inflate(R.layout.left_login_real,ll_head,true);
//    		}
//    		else
//    		{
//    			ll=(LinearLayout) act.getLayoutInflater().inflate(R.layout.left_login_unreal,ll_head,true);
//    		}
//    		
//    		
//    		textview_loginname=(TextView)ll.findViewById(R.id.textview_loginname);
//    		textview_loginname.setText(Utils.getEncryptedPhonenumber2(phonenumber));
//    		imageview_avarta=(ImageView)ll.findViewById(R.id.imageview_avarta);
//    		imageview_avarta.setBackgroundResource(R.drawable.login_user_img);
//    		
//    	}
//    	else
//    	{
//    		ll=(LinearLayout) act.getLayoutInflater().inflate(R.layout.left_unlogin,ll_head,true);
//    		textview_loginname=(TextView)ll.findViewById(R.id.textview_loginname);
//    		textview_loginname.setText("请登录/注册");
//    		textview_loginname.setOnClickListener(this);
//    		imageview_avarta=(ImageView)ll.findViewById(R.id.imageview_avarta);
//    		imageview_avarta.setBackgroundResource(R.drawable.unlogin_user_img);
//    	}
//    	ll_ll1=(LinearLayout)ll.findViewById(R.id.ll_ll1);
//    	ll_ll1.setOnClickListener(this);
//    }
//	//设置menu页面信息，分为未登录和已登录两种状态
//	public void setMenuInfo(){
//		setGueseImg();
//		String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		act.vpl.init2();
//		if(!do_success){
//			resetTextColor();
//			textview_loginname.setText("请登录/注册");
//			relative_password.setClickable(false);
//			textview_password.setTextColor(act.getResources().getColor(R.color.ziti5));
//			img_passwd.setImageResource(R.drawable.ic_password_un);
//			linear_bankmaster_child.setVisibility(View.GONE);
//			//image_arrow_password.setVisibility(View.INVISIBLE);
//			textview_logout.setVisibility(View.INVISIBLE);
//			textview_realname.setVisibility(View.INVISIBLE);
//			linear_password_child.setVisibility(View.GONE);
//			initView1(false,false,null);
//			
//		}else{
//			 UserInfo userInfo=BeikBankApplication.getUserInfo();
//			textview_loginname.setText(Utils.getEncryptedPhonenumber(phonenumber));
//			relative_password.setClickable(true);
//			textview_password.setTextColor(act.getResources().getColor(R.color.ziti4));
//			img_passwd.setImageResource(R.drawable.ic_password);
//			//image_arrow_password.setVisibility(View.VISIBLE);
//			linear_bankmaster_child.setVisibility(View.VISIBLE);
//			if(userInfo!=null&&userInfo.hasBindCard)
//			{
//				tv_bindbank.setVisibility(View.GONE);
//				textview_masterbank.setVisibility(View.VISIBLE);
//			}
//			else
//			{
//				tv_bindbank.setVisibility(View.VISIBLE);
//				textview_masterbank.setVisibility(View.GONE);
//			}
//			textview_logout.setVisibility(View.VISIBLE);
//			//UserInfo userInfo=UserInfoDao.getInstance(act).getUserInfo();
//			textview_realname.setVisibility(View.VISIBLE);
//			ll_is_buy.setVisibility(View.VISIBLE);
//			if(userInfo!=null&&userInfo.hasSetPaypassword)
//		    {
//				textview_change_transacpassword.setVisibility(View.VISIBLE);
//				set_tpasswd.setVisibility(View.GONE);
//		    }
//			else
//			{
//				set_tpasswd.setVisibility(View.VISIBLE);
//				textview_change_transacpassword.setVisibility(View.GONE);
//			}
//			if(userInfo!=null&&userInfo.isHasAuthenticated()){
//				//textview_realname.setText("已认证");
//				initView1(true,true,phonenumber);
//			}else{
//				//textview_realname.setText("未认证");
//				initView1(true,false,phonenumber);
//			}
//		}
//		
//		
//	}
//	
////	public void setGestureInfo(){
////		boolean flag=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
////				false);
////		if(!flag){
////			linear_gesture_parent.setVisibility(View.GONE);
////			switch_gesture_unlock.setChecked2(false);
////		}else{
////			linear_gesture_parent.setVisibility(View.VISIBLE);
////			switch_gesture_unlock.setChecked2(true);
////		}
////	}
//	TextView tv_more;
//	TextView textview_password;
//	TextView textview_bank;
//	/**
//	 * 设置文本颜色
//	 * @param index 0更多， 1密码 ，2银行，9其他
//	 */
//    private void setTextColor(int index)
//    {
//    	resetTextColor();
//    	if(index==0)
//    	{   
//    		tv_more.setTextColor(0xffe3dc3a);
//    		if(linear_more_child.getVisibility()==View.VISIBLE)
//    		{
//    			tv_more.setTextColor(0xffffffff);
//    		}
//    	}
//    	else if(index==1)
//    	{
//    		textview_password.setTextColor(0xff22a7f0);
//    		if(linear_password_child.getVisibility()==View.VISIBLE)
//    		{
//    			textview_password.setTextColor(0xffffffff);
//    		}
//    	}
//    	else if(index==2)
//    	{
//    		textview_bank.setTextColor(0xff8144ad);
//    		if(linear_bank_child.getVisibility()==View.VISIBLE)
//    		{
//    			textview_bank.setTextColor(0xffffffff);
//    		}
//    	}
//    }
//    /**
//     * 初始化文本颜色
//     */
//    private void resetTextColor()
//    {
//    	tv_more.setTextColor(0xffffffff);
//    	textview_password.setTextColor(0xffffffff);
//    	textview_bank.setTextColor(0xffffffff);
//    	boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//    	if(!do_success)
//    	{
//    		textview_password.setTextColor(act.getResources().getColor(R.color.ziti5));
//    		img_passwd.setImageResource(R.drawable.ic_password_un);
//    	}
//    }
//    /**
//     * 关闭展开的菜单
//     */
//    public void closeMenu()
//    {   
//    	resetTextColor();
//    	linear_more_child.setVisibility(View.GONE);
//    	linear_password_child.setVisibility(View.GONE);
//    	linear_bank_child.setVisibility(View.GONE);
//    }
//	@Override
//	public void onClick(View v) {
//		Intent intent=null;
//		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//		switch(v.getId()){
//		case R.id.relative_zhichan:
//			if(do_success)
//			{   
//				UserInfo ui=BeikBankApplication.getUserInfo();
//				if(ui!=null)
//				{
//					if(!ui.hasAuthenticated)
//					{
//						HandlerBase.showMsg(act,"请先实名认证",1);
//						return ;
//					}
//					else if(!ui.hasBindCard)
//					{
//						HandlerBase.showMsg(act,"请先绑卡",1);
//						return ;
//					}
//					else
//					{
//						startAimActivity(QianbaoActivity1.class);
//					}
//				}
//			
//			}
//			else
//			{
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
//						0);
//				startAimActivity(LoginRegActivity.class);
//			}
//			break;
//		
//		case R.id.ll_huodong:
//			//intent=new Intent(act,ActionListActivity.class);
//			intent=new Intent(act,HuodongActivity.class);
//			act.startActivity(intent);
//			break;
//		case R.id.ll_xiaoxi:
//			intent=new Intent(act,MessageActivity.class);
//			act.startActivity(intent);
//			break;
//		case R.id.imageview_avarta:
//			break;
//		case R.id.tv_bindbank:
//			UserInfo ui=BeikBankApplication.getUserInfo();
//			if(ui!=null)
//			{    
//				 if(ui.isHasAuthenticated())
//				 {   
//					 if(!ui.hasBindCard)
//					 {
//						 intent=new Intent(act,BankBindActivity2.class);
//						 act.startActivity(intent);
//					 }
//				 }
//				 else
//				 {
//					 Toast.makeText(act,"请先进行实名认证",Toast.LENGTH_SHORT).show();
//				 }
//			}
//			break;
//		case R.id.tv_set_transacpassword:
//		    intent=new Intent(act,TransactionPwdSetActivity.class);
//			act.startActivity(intent);
//			break;
//		case R.id.textview_loginname:
//			//String do_success=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.DO_SUCCESS1);
//			 do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
//			//String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//			if(!do_success){
//				//act.mSlidingMenu.toggle2(false);
//				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
//						0);
//				startAimActivity(LoginRegActivity.class);
//			}
//			break;
//		case R.id.relative_password:
//			setTextColor(1);
//			linear_bank_child.setVisibility(View.GONE);
//			linear_more_child.setVisibility(View.GONE);
//			//performAnimateForDown(image_arrow_bank);
//			//performAnimateForDown(image_arrow_more);
//			if(linear_password_child.getVisibility()==View.VISIBLE){
//				performAnimateForGone(linear_password_child);
//				//performAnimateForDown(image_arrow_password);
//			}else{
//				performAnimateForVisible(linear_password_child);
//				//performAnimateForUp(image_arrow_password);
//			}
//			break;
//		case R.id.relative_bank:
//			setTextColor(2);
//			linear_password_child.setVisibility(View.GONE);
//			linear_more_child.setVisibility(View.GONE);
//			//performAnimateForDown(image_arrow_password);
//			//performAnimateForDown(image_arrow_more);
//			if(linear_bank_child.getVisibility()==View.VISIBLE){
//				performAnimateForGone(linear_bank_child);
//				//performAnimateForDown(image_arrow_bank);
//			}else{
//				performAnimateForVisible(linear_bank_child);
//				//performAnimateForUp(image_arrow_bank);
//			}
//			break;
//		case R.id.relative_more:
//			setTextColor(0);
//			linear_password_child.setVisibility(View.GONE);
//			linear_bank_child.setVisibility(View.GONE);
//			//performAnimateForDown(image_arrow_password);
//			//performAnimateForDown(image_arrow_bank);
//			if(linear_more_child.getVisibility()==View.VISIBLE){
//				linear_more_child.setVisibility(View.GONE);
//				//performAnimateForGone(linear_more_child);
//				//performAnimateForDown(image_arrow_more);
//			}else{
//				linear_more_child.setVisibility(View.VISIBLE);
//				//performAnimateForVisible(linear_more_child);
//				//performAnimateForUp(image_arrow_more);
//			}
//			break;
//		case R.id.textview_change_password:
//			startAimActivity(LoginPwdUpdateActivity.class);
//			break;
//		case R.id.textview_change_transacpassword:
//			//UserInfo userInfo=UserInfoDao.getInstance(act).getUserInfo();
////			 UserInfo userInfo=BeikBankApplication.getUserInfo();
////			if(userInfo.isHasBindCard()){
//				startAimActivity(TransactionPwdUpdateActivity.class);
////			}else{
////				Toast.makeText(act, "请先完成绑定银行卡！", Toast.LENGTH_SHORT).show();
////			}
//			break;
//		case R.id.textview_change_gesturepassword:
//				Intent intent2=new Intent(act,GesturePwdOldActivity.class);
//				intent2.putExtra(BeikBankConstant.INTENT_CHANGEGESTURE, true);
//				act.startActivity(intent2);
//			break;
//		case R.id.textview_supportbank:
//			startAimActivity(BankSupportActivity.class);
//			break;
//		case R.id.textview_masterbank:
//			startAimActivity(BankMasterActivity.class);
//			break;
//		case R.id.textview_helpcenter:
//			startAimActivity(HelpCenterActivity.class);
//			break;
//		case R.id.textview_feedback:
//			startAimActivity(FeedbackActivity.class);
//			break;
//		case R.id.textview_aboutbk:
//			startAimActivity(AboutActivity.class);
//			break;
//		case R.id.textview_enourage:
//			shareToMarket();
//			break;
//		case R.id.relative_version:
//			String version=Utils.getVersion(act);
//			//BeikBankApi.getInstance().checkUpdateInfo(act, version, "1", getUpdateInfoHandler);
//			new CheckUpdateManager(act, icb).start();
//			break;
//		case R.id.textview_logout:
//			
//			change_account_dialog=Utils.createSimpleDialog(act,
//					act.getString(R.string.logout_text),act.getString(R.string.ok2),new BeikBankDialogListener() {
//
//				@Override
//				public void onRightBtnClick() {
//					// TODO Auto-generated method stub
//					//UserInfoDao.getInstance(act).deleteAll();
////					TableDaoSimple.delete(UserInfo.class,null,null);
////	
////					
////					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.DO_SUCCESS1, 
////							"");
////					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT, 
////							"");
////					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_PASSWORD, 
////							"");
////					//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE, 
////					//		false);
////					BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_RETRY, 
////							LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
////					BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.GESTURE_OLD, 
////							LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT);
//					LoginManager.outLogin(act,0);
//					
////					Intent intent3 = new Intent(act, HomeActivity2.class); 
////					BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
////							1);
////					intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////					act.startActivity(intent3);
//					act.outLogin();
//					act.addMessageAndNotice();
//				}
//
//				@Override
//				public void onListItemLongClick(int position, String string) {
//					
//
//				}
//
//				@Override
//				public void onListItemClick(int position, String string) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onLeftBtnClick() {
//					// TODO Auto-generated method stub
//					change_account_dialog.dismiss();
//				}
//
//				@Override
//				public void onCancel() {
//					// TODO Auto-generated method stub
//
//				}
//			});
//			change_account_dialog.show();
//			//performAnimateForDown(image_arrow_password);
//			//performAnimateForDown(image_arrow_bank);
//			//performAnimateForDown(image_arrow_more);
//			linear_password_child.setVisibility(View.GONE);
//			linear_bank_child.setVisibility(View.GONE);
//			linear_more_child.setVisibility(View.GONE);
//			break;
//		case R.id.ll_ll1:
//			String phonenumber2=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
//			if(!phonenumber2.equals("")){
//				//UserInfo userInfo2=UserInfoDao.getInstance(act).getUserInfo();
//				 UserInfo userInfo2=UserInfoDao.getUserInfo();
//				if(!userInfo2.isHasAuthenticated()){
//					BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE, 
//							0);
//					startAimActivity(RealnameActivity.class);
//				}
//			}
//			break;
//		}
//	}
//	/**
//	 * 检查更新
//	 */
//	public void checkUpdate()
//	{
//		new CheckUpdateManager(act, icb2).start();
//	}
//ICallBack icb2=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null){
// 
//				showUpdataDialog((String)obj);
//			}
//			else
//			{
//				//showNewDialog();
//			}
//			
//		}
//	};
//	ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null){
// 
//				showUpdataDialog((String)obj);
//			}
//			else
//			{
//				showNewDialog();
//			}
//			
//		}
//	};
//	//鼓励一下
//	private void shareToMarket(){
//		try {
//			String marketUri = "market://details?id=" + act.getPackageName();
//			Uri uri = Uri.parse(marketUri);
//			Intent intent  = new Intent(Intent.ACTION_VIEW,uri);
//			act.startActivity(intent);
//		}catch (Exception e) {
//			Toast.makeText(act, "搜索不到应用市场,请安装后再尝试", Toast.LENGTH_LONG).show();
//		}
//	}
//	
//	//展开子菜单
//	public static void performAnimateForVisible(final View target) {  
//		ValueAnimator alphaAnim=ObjectAnimator.ofFloat(target, 
//				"alpha", 0.5f, 1f);
//		alphaAnim.addListener(new AnimatorListener() {
//
//			@Override
//			public void onAnimationStart(Animator arg0) {
//				// TODO Auto-generated method stub
//				target.setVisibility(View.VISIBLE);
//			}
//
//			@Override
//			public void onAnimationRepeat(Animator arg0) {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void onAnimationEnd(Animator arg0) {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void onAnimationCancel(Animator arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		alphaAnim.setDuration(350);
//		alphaAnim.setInterpolator(new LinearInterpolator());
//		alphaAnim.start();
//	}
//
//	//缩回子菜单
//	public static void performAnimateForGone(final View target) {  
//		ValueAnimator alphaAnim=ObjectAnimator.ofFloat(target, 
//				"alpha", 1f, 0.5f);
//		alphaAnim.addListener(new AnimatorListener() {
//
//			@Override
//			public void onAnimationStart(Animator arg0) {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void onAnimationRepeat(Animator arg0) {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void onAnimationEnd(Animator arg0) {
//				// TODO Auto-generated method stub
//				target.setVisibility(View.GONE);
//			}
//
//			@Override
//			public void onAnimationCancel(Animator arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		alphaAnim.setDuration(350);
//		alphaAnim.setInterpolator(new LinearInterpolator());
//		alphaAnim.start();
//	}
//
//	//小三角向上转180度
//	public static void performAnimateForUp(final View target) {  
//		ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(target, "rotation", -180);
//		rotateAnimation.setDuration(350);
//		rotateAnimation.setInterpolator(new LinearInterpolator());
//		rotateAnimation.start();
//	}
//
//	//小三角复位
//	public static void performAnimateForDown(final View target) {  
//		ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(target, "rotation", 0);
//		rotateAnimation.setDuration(350);
//		rotateAnimation.setInterpolator(new LinearInterpolator());
//		rotateAnimation.start();
//	}
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(act, pActClassName);
//		act.startActivity(_Intent);
//	}
//	
////	//检查版本更新
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
////			Toast.makeText(act, "网络没有连接", Toast.LENGTH_SHORT).show();
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
////				}else{
////					Message msg = new Message();  
////		            msg.what = NEWEST_CLIENT;  
////		            handler.sendMessage(msg);
////					
////				}
////				
////			}
////
////		}
////
////	};
////	JsonHttpResponseHandler getUpdateInfoHandler2 = new JsonHttpResponseHandler(){
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
////			Toast.makeText(act, "网络没有连接", Toast.LENGTH_SHORT).show();
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
////					textview_versioncode.setVisibility(View.GONE);
////					imageview_versionnew.setVisibility(View.VISIBLE);
////				}else{
////					textview_versioncode.setVisibility(View.VISIBLE);
////					imageview_versionnew.setVisibility(View.GONE);
////					textview_versioncode.setText("v"+Utils.getVersion(act));
////					
////					
////				}
////				
////			}
////
////		}
////
////	};
////	
//	
//	public void showUpdataDialog(final String downloadUrl){
//		
//		if(upgrade_version_dialog!=null&&upgrade_version_dialog.isShowing())
//		{
//			return;
//		}
//		upgrade_version_dialog=Utils.createSimpleDialog(act,
//				act.getString(R.string.upgrade_version),act.getString(R.string.update),new BeikBankDialogListener() {
//
//			@Override
//			public void onRightBtnClick() {
//				// TODO Auto-generated method stub
////				if(SystemConfig.isDebug())
////				{
////					downloadApk("http://beikbank.com/androidAppClient/zhuanla.apk");
////					return;
////				}
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
//				upgrade_version_dialog.dismiss();
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
//	public void showNewDialog(){
//		newest_version_dialog=Utils.createRemindDialog(act,
//				act.getString(R.string.newest_version),new BeikBankDialogListener() {
//
//			@Override
//			public void onRightBtnClick() {
//				// TODO Auto-generated method stub
//				newest_version_dialog.dismiss();
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
//				newest_version_dialog.dismiss();
//			}
//
//			@Override
//			public void onCancel() {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		newest_version_dialog.show();
//	}
//	ProgressDialog pd;
//	protected void downloadApk(final String url) {  
//	    //进度条对话框  
//		pd = new ProgressDialog(act);  
//		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
//		pd.setMessage("正在下载更新");  
//		pd.show();  
//		new Thread(){  
//			@Override  
//			public void run() {  
//				try {  
//					File file = getFileFromServer(url, pd);  
//					sleep(3000);  
//					installApk(file);
//					Message msg = new Message();  
//					msg.what =HandlerBase.success1;  
//					handler.sendMessage(msg);  
//					//pd.dismiss(); //结束掉进度条对话框  
//				} catch (Exception e) {  
//					Message msg = new Message();  
//					msg.what =HandlerBase.error1;  
//					handler.sendMessage(msg);  
//					e.printStackTrace();
//					LogHandler.writeLogFromException(TAG, e);
//				}
//			
//			}}.start();  
//	}  
//
//	protected void installApk(File file) {  
//	    Intent intent = new Intent();  
//	    //执行动作  
//	    intent.setAction(Intent.ACTION_VIEW);  
//	    //执行的数据类型  
//	    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
//	    act.startActivity(intent);  
//	}  
//	
//	Handler handler = new Handler(){  
//	      
//	    @Override  
//	    public void handleMessage(Message msg) {  
//	        // TODO Auto-generated method stub 
//	    	if(pd!=null)
//	    	{
//	    		pd.dismiss();
//	    	}
//	        super.handleMessage(msg);  
//	        switch (msg.what) {  
////	        case UPDATA_CLIENT:  
////	            //对话框通知用户升级程序  
////	        	String downloadUrl=(String)msg.obj;
////	            showUpdataDialog(downloadUrl);  
////	            break;  
////	        case NEWEST_CLIENT:  
////	            //对话框通知用户升级程序   
////	            showNewDialog();  
////	            break; 
//	        case HandlerBase.error1:  
//	            //下载apk失败  
//	            Toast.makeText(act, "下载新版本失败", Toast.LENGTH_SHORT).show();  
//	            break;   
//	        case HandlerBase.success1:
//	        	break;
//	        }  
//	    }  
//	};  
//
//	public File getFileFromServer(String path, ProgressDialog pd) throws Exception{
//		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//			URL url = new URL(path);
//			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
//			conn.setConnectTimeout(5000);
//			//获取到文件的大小 
//			pd.setMax(conn.getContentLength());
//			InputStream is = conn.getInputStream();
//			File file = new File(Environment.getExternalStorageDirectory(), "zhuanla_android.apk");
//			FileOutputStream fos = new FileOutputStream(file);
//			BufferedInputStream bis = new BufferedInputStream(is);
//			byte[] buffer = new byte[1024];
//			int len ;
//			int total=0;
//			while((len =bis.read(buffer))!=-1){
//				fos.write(buffer, 0, len);
//				total+= len;
//				//获取当前下载量
//				pd.setProgress(total);
//			}
//			fos.close();
//			bis.close();
//			is.close();
//			return file;
//		}
//		else{
//			return null;
//		}
//	}
//
//
//}
