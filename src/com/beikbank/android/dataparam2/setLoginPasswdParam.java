package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *设置登录密码
 */
public class setLoginPasswdParam 
{  
	/**
	 *N 0用户名、密码 1token验证 2第三方账号登录 3指纹登录 4手势登录
	 */
   public String login_type;

   /**
	 * 选填与用户代码2选1
	 */
  public String   new_password;
  /**
	 * Y User_type!=4或 Set_type=0，则必填； User_type=4或 Set_type=1，则可不填；
	 */
  public String     old_password;
  /**
	 * N 0设置登录密码 1重置登录密码
	 */
  public String   set_type;
  /**
	 * Y User_type!=4,则用户代码 User_type=4,则临时用户
	 */
  public String   user_code;
  /**
	 * N 0个人； 1企业； 2事业； 3政府； 4临时；
	 */
  public String   user_type;
  /**
	 * 选填与用户代码2选1
	 */
  public String   fingerprint_password;
  /**
	 * 选填与用户代码2选1
	 */
public String   gesture_password;
/**
 * 选填与用户代码2选1
 */
public String   third_bind_id;
/**
 * 选填与用户代码2选1
 */
public String    third_platform_id;

    
}
