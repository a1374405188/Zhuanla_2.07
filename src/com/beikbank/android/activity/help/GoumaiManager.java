package com.beikbank.android.activity.help;

import android.app.Activity;
import android.content.Intent;

import com.beikbank.android.activity.BandTestActivity;
import com.beikbank.android.activity.BankBindActivity2;
import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.DingqiGoumaiActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.PageUtil;

/**
 * 
 * @author Administrator
 *购买管理
 */
public class GoumaiManager 
{    
	
	private static Activity act;
	private static FundInfo fundInfo;
	/**
	 * 购买活期
	 */
     public static void goumaiHuoQi(Activity act1,FundInfo fundInfo1)
     {
    	 act=act1;
    	 fundInfo=fundInfo1;
    	 boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
    	 if(!do_success)
    	 {
				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
						2);
				Intent _Intent = new Intent();
				_Intent.setClass(act,LoginRegActivity.class);
				act.startActivity(_Intent);
	     }
    	 LiuChenManager.StartNext(act,icb4);
     }
 	//选择支付方式
		private static void selectPlay()
		{
			LiuChenManager.selectPay(icb2, act,false);
		}
		
		
	/**
	 * 检查是否需要从新绑卡	
	 */
	private static	   ICallBack icb2=new ICallBack() {
				
				@Override
				public void back(Object obj) {
				  if(obj==null)
				  { 
					  String pay=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
		 				 if(pay.equals("1")||pay.equals("2"))
		 				 {
		 					Intent intent =new Intent();
							  intent.setClass(act,PurchaseActivity.class);
							  intent.putExtra(PurchaseActivity.index,fundInfo);
							  act.startActivity(intent);
		 					 
		 					 
		 					 return;
		 				 }
					CheckBankParam cbp=new CheckBankParam();
					cbp.memberID=BeikBankApplication.getUserid();
					TongYongManager tym=new TongYongManager(act, icb3,cbp);
					tym.start();
					
					
				  }
				}
			};
		
		
		
		
		/**
		 * 回调到购买
		 */
		private static ICallBack icb3=new ICallBack() {
			
			@Override
			public void back(Object obj) {
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
					  Intent intent =new Intent();
					  intent.setClass(act,PurchaseActivity.class);
					  intent.putExtra(PurchaseActivity.index,fundInfo);
					  act.startActivity(intent);
					}
				}
			}
		};
		/**
		 * 回调到选择支付
		 */
      private static ICallBack icb4=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj==null)
				{
				  selectPlay();
				}
			}
		};
		
//-------------------------------------------------------------------------------------     
	 private static DingqiP2 dp2;
	 private static String userLevel;
	 /**
	  * 
	  * @param act1
	  * @param dp
	  * @param userLevel 0新手，1新手以外
	  */
     public static void goumaiDingQi(Activity act1,DingqiP2 dp,String uLevel)
     {
    	 act=act1;
    	 dp2=dp;
    	 userLevel=uLevel;
    	 BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.huo_ding,
					2);
			if(dp2!=null&&dp2.termbondCode!=null)
			{   
			   LiuChenManager.StartNext(act,icb5);
			}
    	 
    	 
    	 
     }
   //回调选择支付方式
   	static	ICallBack icb5=new ICallBack() {
   			
   			@Override
   			public void back(Object obj) {
   				 //LiuChenManager.StartNext(act,icb4);
   				if(obj==null)
   				{ 
   					  boolean b=doNew(dp2,userLevel);
   					   if(!b)
   					   {	
   				          selectPlay2();
   					   }
   				}
   			}
   		};
   		/**
   		 * 如果点击的是新手标，进行相应的处理 
   		 * @return true 不进行后续的代码
   		 */
   		public static boolean doNew(DingqiP2 dp2,String isNew)
   		{
   			
   			if(dp2.termbondType.equals("1"))
   			{
   				if(isNew.equals("0"))
   				{   
   					
   				}
   				else
   				{
   					PageUtil pu=new PageUtil(act,DingqiDetailActivity.countRate(dp2.yearRate,dp2.extraRate));
   					pu.showShapeDialog();
   					return true;
   				}
   			}
   			
   			return false;
   		} 		
//选择支付方式
	private static void selectPlay2()
	{
		LiuChenManager.selectPay(icb6, act,true);
	}
     
     private static ICallBack icb6=new ICallBack() {
 		
 		@Override
 		public void back(Object obj) {
 			 if(obj==null)
 			 {  
 				 String pay=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
 				 if(pay.equals("1")||pay.equals("2"))
 				 {  
 					 //if(HomeActivity3.ha.zc!=null&&HomeActivity3.ha.zc.uc!=null)
 					// {
 					 Intent intent=new Intent(act,DingqiGoumaiActivity.class);
		 			   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
		 			   intent.putExtra(TypeUtil.dingdi_data,dp2);
		 			  
		 			   //intent.putExtra("money",HomeActivity3.ha.zc.uc.fundAmount);
		 			   
		 			   act.startActivity(intent);
 					 //}
 					 return;
 				 }
 				 
 				CheckBankParam cbp=new CheckBankParam();
				cbp.memberID=BeikBankApplication.getUserid();
				TongYongManager tym=new TongYongManager(act, icb7,cbp);
				tym.start();
 			 }
 		}
 	};
 	
 	
 	/**
	 * 回调到购买
	 */
	private static ICallBack icb7=new ICallBack() {
		
		@Override
		public void back(Object obj) {
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
					//if(HomeActivity3.ha.zc!=null&&HomeActivity3.ha.zc.uc!=null)
					//{
					   Intent intent=new Intent(act,DingqiGoumaiActivity.class);
		 			   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
		 			   intent.putExtra(TypeUtil.dingdi_data,dp2);
		 			  
		 			   //intent.putExtra("money",HomeActivity3.ha.zc.uc.fundAmount);
		 			   
		 			   act.startActivity(intent);
					//}
				}
			}
		}
	};
}
