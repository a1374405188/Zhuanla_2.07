package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *得到用户资产
 */
public class getUserOrXiuGaiParam
{  
	/**
	 * id
	 */
   public String  user_code;
   /**
    * 0修改，报文体内域必填 1读取，可只填user_code 默认值：0
    */
   public String operation_type;
}
