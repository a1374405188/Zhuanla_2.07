package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *
 * 用户身份信息修改/读取
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
    public String id_type="0";
}
