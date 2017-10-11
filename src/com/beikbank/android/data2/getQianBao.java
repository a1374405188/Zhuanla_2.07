package com.beikbank.android.data2;

import java.io.Serializable;

public class getQianBao implements Serializable{
	/**
	 * 钱包余额
	 */
  public String acc_amount ;
  /**
   * 账户id
   */
  public String  acc_id;
  /**
   * 银行账户
   */
  public String  acc_number;
  /**
   * 银行卡类型名
   */
  public String  bank_card_type_name;
  /**
   * 银行名称
   */
  public String   bank_name;
  /**
   * 默认账户 0默认；1非默认
   */
  public String    default_acc;
  public String     icon_url;
  public String     logo_url;
  /**
   * 
   */
  public String      max_amount;
  /**
   * 
   */
  public String      min_amount;
  
  /**
   * 当日限额
   */
  public String      simplet_date_amount;
}
