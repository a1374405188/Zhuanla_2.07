package com.beikbank.android.data2;
/**
 * 
 * @author Administrator
 *判断用户是否注册
 */
public class UserCheck2 
{

    /**
     *0未绑卡（默认），1绑卡，3手机号处理解绑（只用3）
     */
    public String  card_bind_status;
	/**
	 *0未绑卡；1已绑卡
	 */
   public String  is_bind_card;
   /**
    *0是新用户；1老用户
    */
   public String      is_new_user;
   /**
    * 0非实名；1实名
    */
   public String      is_real_name;
   /**
    * 0未设置；1已设置
    */
   public String       is_tra_password;
   /**
    * 是否有手势密码 0没有；1有
    */
   public String       is_gesture_password;
   /**
    * 是否有指纹密码0没有；1有
    */
   public String        is_fingerprint_password;
   
}
