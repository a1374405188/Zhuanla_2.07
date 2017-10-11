package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *创建或者修改或者关闭手势密码
 */
public class ShouShiParam 
{  
	/**
	 *指纹密码
	 */
   public String fingerprint_password;
   /**
    *
   /**
    * 手势密码
    */
   public String gesture_password;
   
   /**
    *旧指纹密码
    */
   public String  old_fingerprint_password;
   
   /**
    * 旧手势密码
    */
   public String old_gesture_password;
   
   /**
    * 操作类型0修改； 1注销 2创建 
    */
   public String  operation_type;
   /**
    * 密码类型0手势密码； 1指纹密码
    */
   public String   password_type="0";
   /**
    * 手机类型v 0IOS；1android
    */
   public String phone_type="1";
   /**
    * 指纹密码是否开启 0关闭；1启动
    */
   public String start_fingerprint_password;
   /**
    * 0关闭；1启动
    */
   public String    start_gesture_password;
   /**
    * 用户代码
    */
   public String user_code;
   
}
