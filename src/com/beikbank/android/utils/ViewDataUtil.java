package com.beikbank.android.utils;
/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-23
 * 
 */
public class ViewDataUtil {
	   /**
	    * 计算年化收益率
	    * @param str
	    * @return
	    */
	   public static String getD1(String str)
	   {
		   String s1=NumberManager.getString(str,"100",2);
		   return s1;
	   }
	   
	   /**
	    * 计算加真年化收益率
	    * @param str
	    * @return
	    */
	   public static String getD2(String str)
	   {
		   String s2=NumberManager.getString(str,"1",2);
		   return s2;
	   }
}
