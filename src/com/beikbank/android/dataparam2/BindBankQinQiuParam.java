package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *创建账户请求，绑卡
 */
public class BindBankQinQiuParam 
{  
	/**
	 *预留手机号（必填）
	 */
   public String reserve_phone_number;
   /**
    *
   /**
    * 银行卡号（必填）
    */
   public String acc_number;
   
   /**
    *用户代码（必填）
    */
   public String user_code;
   
   
   public String acc_title;
   public String id_number;
   
   public String acc_tra_password;
   public String acc_type="1";
   public String bank_card_type_id="1";
   public String bank_name;
   public String city_id;
   public String create_branch_name;
   public String province_id;
   
}
