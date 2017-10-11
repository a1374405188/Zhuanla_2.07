package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *得到验证嘛
 */
public class getYanZhenMaParam 
{  
	/**
	 * 0注册验证码； 1找回密码； 2未知； 3其它；4绑卡验证码；5支付验证码；6找回账号；7解绑账号； 默认0
	 */
   public String generate_code_type;

   /**
	 * 选填与用户代码2选1
	 */
  public String  phone_number;
  /**
	 * 选填与用户代码2选1
	 */
 public String  user_code;
    
}
