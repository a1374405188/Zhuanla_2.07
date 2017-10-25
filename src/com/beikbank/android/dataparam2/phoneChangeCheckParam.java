package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *检查手势密码
 */
public class phoneChangeCheckParam 
{  
	/**
	 * 默认为身份证号
	 */
  public String   id_number;

   /**
    * 0身份证； 1驾驶证； 2军官证； 3士兵证； 4学生证； 5未知； 6组织机构代码证；
    */
   public String id_type;
   
   /**
    * 登录密码（md5加密）
    */
   public String login_password;
   /**
    * 姓名
    */
   public String real_name;
   /**
    * 手机号码
    */
   public String phone_number;
}
