package com.beikbank.android.utils2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Administrator
 *时间工具
 */
public class TimeUtil {
	/**
	 * yyyy-MM-dd hh:mm:ss
	 * 得到这种格式的当前时间
	 * @return
	 */
   public static String getTime()
   {
	   SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	   String date = sDateFormat.format(new java.util.Date());
	   
	   
	   
	   
	   return date;
   }
	/**
	 * yyyyHHddHHmmss
	 * 得到这种格式的当前时间
	 * @return
	 */
  public static String getTime2()
  {
	   SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 
	   String date = sDateFormat.format(new java.util.Date());
	   
	   
	   date="sn"+date+"00000";
	   
	   return date;
  }
	/**
	 * yyyyHHddHHmmss
	 * 得到这种格式的当前时间
	 * @return
	 */
public static String getTime3()
{
	   SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 
	   String date = sDateFormat.format(new java.util.Date());
	
	   
	   date="sn"+date+"00000";
	   
	   return date;
}
  
  
   /**
    * 将yyyy-MM-dd的字符串转化成时间
    * @return
    */
   public static Date getDate(String time)
   {   
	   Date date=null;
	   SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   try {
		date=sDateFormat.parse(time);
	  } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	   return date;
   }
}
