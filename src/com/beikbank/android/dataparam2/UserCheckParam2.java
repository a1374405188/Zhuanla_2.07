package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *得到用户是否绑卡，实名等信息
 */
public class UserCheckParam2 
{  
	/**
	 * 手机号
	 */
   public String      phone_number;
   /**
    * 默认0 0用户代码；1手机号码
    */
   public String      check_type;
   public String user_code;
   /**
    * 0ios;1安卓
    */
   public String phone_type="1";
    
}
