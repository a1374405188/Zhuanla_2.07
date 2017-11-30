package com.beikbank.android.activity.help;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.LinearGradient;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.beikbank.android.activity.BandTestActivity;
import com.beikbank.android.activity.BankBindActivity;
import com.beikbank.android.activity.BankBindActivity2;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.activity.RealnameActivity;
import com.beikbank.android.activity.RedeemActivity;
import com.beikbank.android.activity.TransactionPwdSetActivity;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.IsCheckBank;
import com.beikbank.android.data.IsCheckBank_data;
import com.beikbank.android.data.TotalMoney_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.IsCheckBankManager;
import com.beikbank.android.net.impl.TotalMoneyManager;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import coma.beikbank.android.R;



/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-25
 * 流程管理
 */
public class LiuChenManager {
	static UserInfo userInfo;
	static Activity act;
	/**
	 * 
	 */
	static ICallBack icb;
   /**
    * 购买
    * 实名认证，绑卡，设置交易密码，验证预留手机号都已完成时 return true
    * or return false 并启动相应的流程	
    * @return
    */
   public static void StartNext(Activity act0,ICallBack icb0)
   {   
	   act=act0;
	   boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		if(!do_success){
			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
					2);
			Intent intent=new Intent(act,LoginRegActivity.class);
			act.startActivity(intent);
			return;
		}
	   
	    boolean b=true;
	  
	    userInfo=BeikBankApplication.getUserInfo();
	    icb=icb0;
	    /**
		 * 检查是否需要验证银行卡
		 */
//		boolean isCheckBank=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
//		if(isCheckBank)
//		{
//			select();
//		}
//		else
//		{
			IsCheckBankManager icbm=new IsCheckBankManager(act, icb4);
   		    String uerid=userInfo.getId();
   		    icbm.init(uerid);
   		    icbm.start();
//		}
   }
  static ICallBack icb4=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		 {
		   IsCheckBank_data id=(IsCheckBank_data) obj;
		   IsCheckBank icb=id.data;
		   String s=icb.UserCardLimit;
		  //如果已经验证过
		   if("1".equals(s))
		   {
			   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,true);
			   
		   }
		   else
		   {    
			    BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,false);
		   }
		   select();
		}

		
	}
};
  /**
   * 第一次从实名认证跳到绑卡
   */
  public static boolean is_bank;
   private static void select()
   {   
	   boolean isCheckBank=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
	   if(!userInfo.isHasAuthenticated()){//没有实名认证
			Intent intent=new Intent(act,RealnameActivity.class);
			intent.putExtra(BeikBankConstant.INTENT_PURCHASE, true);
			intent.putExtra("is_nextpage", true);
			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
			act.startActivity(intent);
			
		}
	    else if(!userInfo.isHasBindCard())
		{//没有绑卡
			Intent intent=new Intent(act,BankBindActivity2.class);
			intent.putExtra("is_nextpage", true);
			if(is_bank)
			{
				intent.putExtra("is_bank", true);
				is_bank=false;
			}
			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
			act.startActivity(intent);
			
		}
		else if(userInfo.isHasBindCard())
		{   
			if(!isCheckBank)
			{
			  Intent intent=new Intent(act,BandTestActivity.class);
			  intent.putExtra("is_nextpage", true);
			  act.startActivity(intent);
			}
			else
			{
				//没有设置交易密码
				if(!userInfo.hasSetPaypassword)
				{
					Intent intent=new Intent(act,TransactionPwdSetActivity.class);
					intent.putExtra("is_nextpage", true);
					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
					act.startActivity(intent);
					
				}
				else
				{
					icb.back(null);
					
				}
	
			}
		}
	   //完成后必须调用
	    icb.back("finish");
		
   }
  
    static ICallBack icb11;
   /**
    * 取现
    * @param act0
    */
   public static void StartNext2(Activity act0,ICallBack icb1)
   {   
	   act=act0;
	   icb11=icb1;
	   new TotalMoneyManager(act, icb5).start();
   }
  static ICallBack icb5=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
		    	TotalMoney_data tm=(TotalMoney_data) obj;
		    	String s1=NumberManager.getString(tm.data.totalAmount,"1",2);
		    	double d=Double.parseDouble(s1);
		    	if(d>0)
		    	{
		    		icb11.back(null);
		    	}
		    	else
		    	{
		    		HandlerBase.showMsg(act,act.getString(R.string.error_3),0);
		    	}
			 
			}
		}
	};
	static Dialog dialog=null;
	/**
	 * 选择支付方式
	 * @param isshow 是否显示活期买定期
	 */
	public static void selectPay(final ICallBack icb,Activity act,boolean isshow)
	{   
		
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_bank_default)
		.showImageForEmptyUri(R.drawable.ic_bank_default)
		.showImageOnFail(R.drawable.ic_bank_default)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(100))
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		
		LinearLayout ll=new LinearLayout(act);
		
		View view=act.getLayoutInflater().inflate(R.layout.puy_select_dialog,ll,false);
		final RadioButton rb1=(RadioButton) view.findViewById(R.id.rb1);
		final RadioButton rb2=(RadioButton) view.findViewById(R.id.rb2);
		final RadioButton rb3=(RadioButton) view.findViewById(R.id.rb3);
		LinearLayout ll1=(LinearLayout) view.findViewById(R.id.ll1);
		LinearLayout ll2=(LinearLayout) view.findViewById(R.id.ll2);
		LinearLayout ll3=(LinearLayout) view.findViewById(R.id.ll3);
		if(!isshow)
		{
			ll3.setVisibility(View.GONE);
			View view1=view.findViewById(R.id.v1);
			view1.setVisibility(View.GONE);
		}
		else
		{
			ll2.setBackgroundResource(R.drawable.bg_selector_puy);
		}
		ll1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(rb1.isChecked())
				{
					rb1.setChecked(false);
				}
				else
				{
					rb1.setChecked(true);
				}
				BeikBankApplication.setSharePref(BeikBankConstant.pay_type,"2");
				//BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.pay_type,"2");
				icb.back(null);
				dialog.dismiss();
			}
		});
		ll2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(rb2.isChecked())
				{
					rb2.setChecked(false);
				}
				else
				{
					rb2.setChecked(true);
				}
				BeikBankApplication.setSharePref(BeikBankConstant.pay_type,"3");
				dialog.dismiss();
				icb.back(null);
				
			}
		});
//       ll3.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				if(rb3.isChecked())
//				{
//					rb3.setChecked(false);
//				}
//				else
//				{
//					rb3.setChecked(true);
//				}
//				BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.play_select,"2");
//				icb.back(null);
//				dialog.dismiss();
//			}
//		});
		
		
		
		ImageView iv=(ImageView)view.findViewById(R.id.iv1);
		TextView tv=(TextView)view.findViewById(R.id.tv_tv2);
		dialog=DialogManager.getDiaolg2(act, view);
		dialog.show();
		
		String name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
		String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
		String icon=BeikBankApplication.getSharePref(BeikBankConstant.icon_url);
		
		tv.setText(name
				+"(尾号"+bank.substring(bank.length()-4, bank.length())+")");
		
		com.nostra13.universalimageloader.utils.L.disableLogging();
		ImageLoader.getInstance().displayImage(icon,iv, options);	
		
		
		
//		CardInfo cardInfo=CardInfoDao.getCardInfo();
//		BankList bl=null;
//		if(cardInfo!=null)
//		{
//		    bl=BankListDao.getBankByType(cardInfo.getType());
//			if(bl!=null)
//			{
//				String cardNumber=cardInfo.getCardNumber();
//				tv.setText(bl.bankName
//						+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//				com.nostra13.universalimageloader.utils.L.disableLogging();
//				ImageLoader.getInstance().displayImage(bl.iconUrl,iv, options);
//			}
//		}
		
	}
}
