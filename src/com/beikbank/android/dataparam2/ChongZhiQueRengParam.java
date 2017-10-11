package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *充值确认
 */
public class ChongZhiQueRengParam 
{  
	/**
	 *订单id
	 */
   public String order_id;
   public String order_type="1";
   /**
    * 1代表跳转交易密码页面0跳转验证码页面
    */
   public String  pay_platform_type;
   /**
    * 交易密码
    */
   public String tra_password;
   public String user_code;
   /**
    * 验证码
    */
   public String vercd;
}
