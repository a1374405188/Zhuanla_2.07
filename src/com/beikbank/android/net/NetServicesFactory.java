package com.beikbank.android.net;

import android.content.Context;

import com.beikbank.android.net.impl.IBusinessImpl;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 **/
public class NetServicesFactory {
	/**
	 * 交易接口
	 */
   public static final int BUSINESS=1;
   /**
    * 得到具体服务
    * @param index
    * @return
    */
   public static Object getNetServices(Context con,int index)
   {
	   Object obj=null;
	   switch(index)
	   {
	       case  BUSINESS:
	    	   obj=new IBusinessImpl(con);
		   break;
	   }
	   return obj;
   }
}
