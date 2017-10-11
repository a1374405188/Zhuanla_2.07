package com.beikbank.android.net.impl2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.beikbank.android.activity.QianbaoActivity1;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiP_data2;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.FundInfo_data;
import com.beikbank.android.data.UserCapital2;
import com.beikbank.android.data.UserCapital2_data;
import com.beikbank.android.data.UserCapitalInfo;
import com.beikbank.android.data.UserCapitalInfo2;
import com.beikbank.android.data.UserCapitalInfo_data;
import com.beikbank.android.data.Yuer;
import com.beikbank.android.data.Yuer_data;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam.UserParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.FundInfoManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.UserCapital2Manager;
import com.beikbank.android.net.impl.UserCapitalInfoManager;
import com.beikbank.android.utils.NumberManager;

/**
 * 
 * @author Administrator
 *用户资产调用接口
 */
public class ZhiCanManager {
   private Activity act;
   public boolean isfinish;
   /**
    * 一次调用是否完成
    */
   private static boolean isEnd=true;
   public UserCapital2 uc;
   public  Yuer ye;
   public UserCapitalInfo uci;
   /**
    * 总金额
    */
   public String money;
   /**
    * 钱包余额
    */
   public String qianbao;
   private ICallBack icb;
   public  FundInfo fi;
   private Object obj=new Object();
   public ZhiCanManager(Activity act,ICallBack icb)
   {
	   this.act=act;
	   this.icb=icb;
   }
   private static int count=-1;
   public void start()
   {    
//	   synchronized(obj)
//	   {
//	   if(count!=0&&count!=3)
//	   {
//		   return;
//	   }
		
//		if(isEnd==true)
//		{
//			isEnd=false;
//		}
//		else
//		{
//			return;
//		}
	    count=0;
	    String id=BeikBankApplication.getUserid();
	    if(id==null||id.equals(""))
	    {   
	    	icb.back(null);
	    	return;
	    }
	   
	    TotalMoneyParam tmp=new TotalMoneyParam();
	    tmp.memberID=BeikBankApplication.getUserid();
		UserCapital2Manager usm=new UserCapital2Manager(act,icb1,tmp);
		usm.start();
		
		YuerParam yp=new YuerParam();
		yp.memberID=BeikBankApplication.getUserid();
		ManagerParam mp=new ManagerParam();
		mp.isShowDialog=false;
		TongYongManager tym=new TongYongManager(act, icb2,yp,mp);
		tym.start();
		
		

		TotalMoneyParam tp=new TotalMoneyParam();
    	tp.memberID=BeikBankApplication.getUserid();
    	UserCapitalInfoManager ucm=new UserCapitalInfoManager(act, icb3, tp);
    	ucm.start();
    	
    	
	   
    	if(fi==null)
    	{
    	   new FundInfoManager(act, icb5).start();
    	}
	  // }
   }
   private ICallBack icb5=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			fi=(FundInfo) obj;
		}
	}
};
   private ICallBack icb1=new ICallBack() {
	
	@Override
	public void back(Object obj) {
	   count++;
	   if(obj!=null)
	   {
		   UserCapital2_data ucd=(UserCapital2_data) obj;
		   uc=ucd.data;
	   }
	   if(count==3)
	   {   
		   if(obj!=null)
		   {   
			   finish();
			   icb.back(obj);
		   }
		   else
		   {
			   icb.back(null);
		   }
		   isEnd=true;
	   }
	}
 };
 private ICallBack icb2=new ICallBack() {
		
	@Override
	public void back(Object obj) {
		count++;
	    if(obj!=null)
	    {
	    	Yuer_data yd=(Yuer_data) obj;
			ye=yd.data;
			
			
	    }
	    if(count==3)
		   {   
			   if(obj!=null)
			   {   
				   finish();
				   icb.back(obj);
				   
			   }
			   else
			   {
				   icb.back(null);
			   }
			   isEnd=true;
		   }
	}
 };

 private ICallBack icb3=new ICallBack() {
		
	@Override
	public void back(Object obj) {
		count++;
		if(obj!=null)
		{
			   UserCapitalInfo_data ud=(UserCapitalInfo_data) obj;
			   uci=ud.data;
			  
		
		}
		  if(count==3)
		   {   
			   if(obj!=null)
			   {   
				   finish();
				   icb.back(obj);
				  
			   }
			   else
			   {
				   icb.back(null);
			   }
			   isEnd=true;
		   }
	}
 };
 /**
	 * 得到募集中的总钱数
	 * @return
	 */
	private String getMoney(UserCapitalInfo uci)
	{  
		String s="0";
		ArrayList<UserCapitalInfo2> list=uci.termbondUnconfirmedList;
		for(UserCapitalInfo2 uc:list)
		{
			s=NumberManager.getAddString(s,uc.amount, 4);
		}
		for(UserCapitalInfo2 uc:uci.termbondList)
		{
			s=NumberManager.getAddString(s,uc.amount, 4);
		}
		return s;
	}
 private void finish()
 {
	 
	 String s=getMoney(uci);
	 String s3=QianbaoActivity1.countTotal(ye);
	 qianbao=ye.activeAmount;
	 money=NumberManager.getAddString(s,s3,4);
	 money=NumberManager.getAddString(money,uc.fundAmount,2);
	 isfinish=true;
 }
}
