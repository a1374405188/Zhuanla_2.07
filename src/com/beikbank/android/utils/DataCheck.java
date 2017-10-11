package com.beikbank.android.utils;

import java.util.regex.Pattern;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-27
 * 检查数据
 */
public class DataCheck 
{  
	/**
	 * 检查手机格式是否正确
	 * @param phonenumber
	 * @return
	 */
   public static boolean checkPhone(String phonenumber)
   {   
	   boolean b=false;
	   if(phonenumber==null||"".equals(phonenumber))
	   {
		   return b;
	   }
	   if(phonenumber.length()!=11)
	   {
		   return b;
	   }
	   if(!phonenumber.startsWith("1"))
	   {
		   return b;
	   }
	   if(!isNumeric(phonenumber))
	   {
		   return b;
	   }
	   return true;
   }
   private static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 


}
