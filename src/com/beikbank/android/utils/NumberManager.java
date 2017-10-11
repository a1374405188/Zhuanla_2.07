package com.beikbank.android.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 
 */
public class NumberManager {
	/**
	 * 四色五入
	 * 将2个浮点数进行乘法运算 默认保留2位小数
	 * @param number double的字符串表示
	 * @param number2 double的字符串表示
	 *@param scale 保留几位小数
	 * @return 想乘后的结果
	 */
   public static String getString2(String number,String number2,int scale)
   {    
	   
	   BigDecimal bd4=null;
	   try{
		   
	   if(number==null||"".equals(number)||"null".equals(number))
	    {
	    	number="0";
	    }
	    if(number2==null||"".equals(number2)||"null".equals(number2))
	    {
	    	number2="0";
	    }
	    BigDecimal bd=new BigDecimal(number);
		BigDecimal bd2=new BigDecimal(number2);
		BigDecimal bd3=bd.multiply(bd2);
		bd4=bd3.setScale(scale,BigDecimal.ROUND_HALF_UP);
	   }
		 catch(Exception e)
		   {
			   e.printStackTrace();
			   return "0.00";
		   }
	    return bd4.toString();
   }
	/**
	 * 将2个浮点数进行乘法运算 默认保留2位小数
	 * @param number double的字符串表示
	 * @param number2 double的字符串表示
	 *@param scale 保留几位小数
	 * @return 想乘后的结果
	 */
   public static String getString(String number,String number2,int scale)
   {    
	   
	   BigDecimal bd4=null;
	   try{
		   
	   if(number==null||"".equals(number)||"null".equals(number))
	    {
	    	number="0";
	    }
	    if(number2==null||"".equals(number2)||"null".equals(number2))
	    {
	    	number2="0";
	    }
	    BigDecimal bd=new BigDecimal(number);
		BigDecimal bd2=new BigDecimal(number2);
		BigDecimal bd3=bd.multiply(bd2);
		bd4=bd3.setScale(scale,BigDecimal.ROUND_DOWN);
		String s0=bd4.toString();
		System.out.println(s0);
	   }
		 catch(Exception e)
		   {
			   e.printStackTrace();
			   return "0.00";
		   }
	    return bd4.toString();
   }
   /**
    * 进行浮点除法运算
    * @param number double的字符串表示
    * @param number2 double的字符串表示
    * @param scale  保留几位小数
    * @return
    */
   public static String getDivString(String number,String number2,int scale)
   {    
	   BigDecimal bd4=null;
	   try{
	   if(number==null||"".equals(number)||"null".equals(number))
	    {
	    	number="0";
	    }
	    if(number2==null||"".equals(number2)||"null".equals(number2))
	    {
	    	number2="0";
	    }
	    BigDecimal bd=new BigDecimal(number);
		BigDecimal bd2=new BigDecimal(number2);
		BigDecimal bd3=bd.divide(bd2,scale,BigDecimal.ROUND_DOWN);
	    bd4=bd3.setScale(scale,BigDecimal.ROUND_DOWN);
	   }
		 catch(Exception e)
		   {
			   e.printStackTrace();
			   return "0.00";
		   }
	    return bd4.toString();
   }
   /**
    * 减法
    * @param number
    * @param number2
    * @param scale
    * @return
    */
   public static String getSubString(String number,String number2,int scale)
   {   
	   BigDecimal bd4=null;
	   try{
	   if(number==null||"".equals(number)||"null".equals(number))
	    {
	    	number="0";
	    }
	    if(number2==null||"".equals(number2)||"null".equals(number2))
	    {
	    	number2="0";
	    }
	    BigDecimal bd=new BigDecimal(number);
		BigDecimal bd2=new BigDecimal(number2);
		BigDecimal bd3=bd.subtract(bd2);
		 bd4=bd3.setScale(scale,BigDecimal.ROUND_DOWN);
	   }
		 catch(Exception e)
		   {
			   e.printStackTrace();
			   return "0.00";
		   }
	    return bd4.toString();
   }
   /**
    * 加法
    * @param number
    * @param number2
    * @param scale
    * @return
    */
   public static String getAddString(String number,String number2,int scale)
   {    
	   BigDecimal bd4=null;
	   try{
		   
	   
	    if(number==null||"".equals(number)||"null".equals(number))
	    {
	    	number="0";
	    }
	    if(number2==null||"".equals(number2)||"null".equals(number2))
	    {
	    	number2="0";
	    }
	    BigDecimal bd=new BigDecimal(number);
		BigDecimal bd2=new BigDecimal(number2);
		BigDecimal bd3=bd.add(bd2);
	    bd4=bd3.setScale(scale,BigDecimal.ROUND_DOWN);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return "0.00";
	   }
	    return bd4.toString();
   }
   /**
    * 判断一个字符串是否全为数字
    * @param str
    * @return
    */
   public static boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}
   
   /**
    * 
    * @param str
    * @param str2  
    * @return 1 str 大于 str2    0等于  -1 小于
    */
   public static int isDaYu(String str,String str2)
   {   
	  int i=0;
	   try{
	  double d1=Double.parseDouble(str);
	  double d2=Double.parseDouble(str2);
	  if(d1>d2)
	  {
		  i=1;
	  }
	  else if(d1==d2)
	  {
	
		  i=0;
	  }
	  else
	  {
		  i=-1;
	  }
   }  
   catch(Exception e)
   {
	   e.printStackTrace();
	  
   }
	   return i;
   }
   
   
   /**
    * 得到格式化后的
    * @param str
    * @param scale 保留的位数
    * @return
    */
   public static String getGeshiHua(String str,int scale)
   {   String s0="0.00";
	   try{
		String s=NumberManager.getString(str,"1", scale);
		NumberFormat usFormat0 = NumberFormat.getIntegerInstance(Locale.US);
		usFormat0.setMinimumFractionDigits(scale);
		s0=usFormat0.format(Double.parseDouble(s));
   }  
   catch(Exception e)
   {
	   e.printStackTrace();
	   return "0.00";
   }
	   return s0;
   }
   /**
    * 将字符串转化为double 失败返回0
    * @param str
    * @return
    */
   public static double StoD(String str)
   {   
	   double d=0;
	   try
	   {
		   d=Double.parseDouble(str);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return d;
   }
}
