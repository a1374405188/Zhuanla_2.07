package com.beikbank.android.utils2;

public class TextUtil {
	/**
	 * 判断字符串是null  ""  "null" 返回true否则返回false
	 * @param s
	 * @return
	 */
  public static boolean isNull(String s)
  {   
	  if(s==null||s.equals("")||s.equals("null"))
	  {
		  return true;
	  }
	  return false;
  }
}
