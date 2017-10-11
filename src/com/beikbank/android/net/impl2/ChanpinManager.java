package com.beikbank.android.net.impl2;

import java.util.List;

import android.app.Activity;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiP_data2;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.dataparam.UserParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.FundInfoManager;
import com.beikbank.android.net.impl.TongYongManager;

/**
 * 
 * @author Administrator
 *首页产品调用接口
 */
public class ChanpinManager {
   private Activity act;
   /**
    * 活期产品
    */
   public FundInfo fundInfo;
   /**
	 * 所有定期产品
	 */
	public List<DingqiP2> list2;
	public DingqiP_data2 dpd;
	private int count=0; 
	private ICallBack icb3;
   public ChanpinManager(Activity act,ICallBack icb3)
   {
	   this.act=act;
	   this.icb3=icb3;
	   count=0;
   }
   public void start()
   {
	   new FundInfoManager(act, icb).start();
	   
	    UserParam up=new UserParam();
		String id=BeikBankApplication.getUserid();
		if(id==null)
		{
			id="";
		}
		up.memberID=id;
		
		ManagerParam mp=new ManagerParam();
		mp.isShowDialog=false;
		
		TongYongManager tym=new TongYongManager(act, icb2,up,mp);
		tym.start();
   }
   private ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		count++;
		if(obj!=null)
		{
			fundInfo=(FundInfo) obj;
			
			
		}
		if(count==2)
		{   
		
			icb3.back(obj);
		}
	}
};

   private ICallBack icb2=new ICallBack() 
   {
	
	@Override
	public void back(Object obj) 
	{   
		count++;
		if(obj!=null)
		{
			 dpd=(DingqiP_data2)obj;
		     list2=dpd.data.productBond;
		     
				
		}
		if(count==2)
		 {
				icb3.back(obj);
		 }
	 }
   };
}
