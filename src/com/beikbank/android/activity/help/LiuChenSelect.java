package com.beikbank.android.activity.help;

import com.beikbank.android.activity.BankBindActivity;
import com.beikbank.android.activity.BankBindActivity2;
import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.DingqiGoumaiActivity;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.activity.QianbaoActivity2;
import com.beikbank.android.activity.QianbaoActivity3;
import com.beikbank.android.activity.RealnameActivity;
import com.beikbank.android.activity.TiXianActivity;
import com.beikbank.android.activity.TransactionPwdSetActivity;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.MiMaOrDuanXin;
import com.beikbank.android.data2.MiMaOrDuanXin_data;
import com.beikbank.android.data2.UserCheck2;
import com.beikbank.android.data2.UserCheck2_data;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.data2.getUserOrXiuGai;
import com.beikbank.android.data2.getUserOrXiuGai_data;
import com.beikbank.android.dataparam2.MiMaOrDuanXinParam;
import com.beikbank.android.dataparam2.UserCheckParam2;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.dataparam2.getUserOrXiuGaiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.PageUtil;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author Administrator
 *注册，实名，绑卡，设置密码等流程选择
 */
public class LiuChenSelect {
	/**
	 * 0提现1充值2购买活期3购买定期  4打开钱包
	 */
	int index;
	
	Activity act;
	Object obj;
	/**
	 * 
	 * @param act
	 * @param index 0提现1充值2购买活期3购买定期  4打开钱包
	 */
   public void startNext(Activity act,int index,Object obj)
   {   
	   this.act=act;
	   this.index=index;
	   this.obj=obj;
	   
	   boolean islogin=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
	   if(!islogin)
	   {
		  Intent intent=new Intent(act,LoginRegActivity.class);
			act.startActivity(intent);
	   }
	   else
	   {
	     initData(act);
	   }
	   
//	   String is_bindbank=BeikBankApplication.getSharePref(BeikBankConstant.is_bindbank);
//	   String is_shimin=BeikBankApplication.getSharePref(BeikBankConstant.is_shimin);
//	   String is_jiaoyi=BeikBankApplication.getSharePref(BeikBankConstant.is_jiaoyi);
	   
	   
	
   }
  
   private void select()
   {
	   Intent intent=new Intent();
	   if("0".equals(uc2d.body.is_real_name))
	   {
		    intent.setClass(act,RealnameActivity.class);
			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
			act.startActivity(intent);
	   }
	   else if("0".equals(uc2d.body.is_bind_card))
	   {
		    intent.setClass(act,BankBindActivity2.class);
			//intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
			act.startActivity(intent);
	   }
	   else if("0".equals(uc2d.body.is_tra_password))
	   {
		    intent.setClass(act,TransactionPwdSetActivity.class);
			//Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
			act.startActivity(intent);
	   }
	   else
		{
			
			if(index==0)
			{
				intent.setClass(act,TiXianActivity.class);
				act.startActivity(intent);
			}
			else if(index==1)
			{
				 intent.setClass(act,QianbaoActivity2.class);
				 act.startActivity(intent);
			}
			else if(index==2)
			{   
				GetChanPin gcp=(GetChanPin) obj;
				 if("0".equals(gcp.is_new_user_mark)&&"1".equals(uc2d.body.is_new_user))
				 {   
					 String s1=NumberManager.getAddString(gcp.increase_interest_return_rate,gcp.benchmark_return_rate, 6);
					 PageUtil pu=new PageUtil(act,NumberManager.getString(s1,"100",2));
				     pu.showShapeDialog();
				 }
				 else
				 {
					 LiuChenManager.selectPay(icb_lc, act,false);
				 }
				
			}
			else if(index==3)
			{
				 LiuChenManager.selectPay(icb_lc, act,false);
				
			}
			else if(index==4)
			{
				intent=(Intent) obj;
				act.startActivity(intent);
			}
			
		}
   }

	ICallBack icb_lc=new ICallBack() {
		
		@Override
		public void back(Object obj0) {
			GetChanPin gcp=(GetChanPin) obj;
			Intent intent;
			if("4".equals(gcp.product_type_pid))
			{
				 intent=new Intent(act,PurchaseActivity.class);
				 intent.putExtra("gcp",gcp);
				 act.startActivity(intent);

			}
			else
			{
				 intent=new Intent(act,DingqiGoumaiActivity.class);
				 intent.putExtra("gcp",gcp);
				 act.startActivity(intent);

			}
			
		}
	};
   
   private void initData(Activity act)
   {
		UserCheckParam2 uc2=new UserCheckParam2();
    	uc2.check_type="0";
    	uc2.user_code=BeikBankApplication.getUserCode();
    	
     	TongYongManager2 tym2=new TongYongManager2(act, icb,uc2);
    	tym2.start();
    	
    	
    	
	
    	
    	
    	
//      
    	String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
    	
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
			 }
			 else
			 {
				 
			 }
				
			}
		};
    	getQianBaoParam gqp=new getQianBaoParam();
		gqp.acc_type_id="1";
	    gqp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym3=new TongYongManager2(act, icb_qianbao, gqp);
		tym3.start();
    	
		
//得到密码或者短信支付
		ICallBack icb_mm=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					MiMaOrDuanXin_data md=(MiMaOrDuanXin_data) obj;
					if("0000".equals(md.header.re_code))
					{
					
					MiMaOrDuanXin mmd=md.body;
					BeikBankApplication.setSharePref(BeikBankConstant.mima_duanxin,mmd.status);
				    Log.d("mima",mmd.status);
					}
				}
				
			}
		};
		
		MiMaOrDuanXinParam mm=new MiMaOrDuanXinParam();
		mm.user_code=BeikBankApplication.getUserCode();
		ManagerParam mp=new ManagerParam();
		mp.isShowMsg=false;
		TongYongManager2 tym4=new TongYongManager2(act, icb_mm,mm,mp);
		tym4.start();
		
   }
   UserCheck2_data uc2d;
   /**
    * 得到用户实名等信息回调
    */
   private ICallBack icb=new ICallBack() {
		
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
				if("1".equals(uc2.is_real_name))
				{  
					String name=BeikBankApplication.getSharePref(BeikBankConstant.real_name);
					if(name==null||"".equals(name))
					{
						getName();
					}
					else
					{
						select();
					}
				}
				else
				{
					 select();
				}
			}
			
		}
	};
	
	
	
	
	private void getName()
	{
	 ICallBack icb_gu=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					 getUserOrXiuGai_data gd=(getUserOrXiuGai_data) obj;
					 getUserOrXiuGai gu=gd.body.get(0);
					 BeikBankApplication.setSharePref(BeikBankConstant.real_name,gu.real_name);
					 select();
				}
			}
		};
		
		
		  getUserOrXiuGaiParam gu=new getUserOrXiuGaiParam();
  		  gu.user_code=BeikBankApplication.getUserCode();
  		  gu.operation_type="1";
  		  TongYongManager2 tym4=new TongYongManager2(act, icb_gu,gu);
  		  tym4.start();
	}
}
