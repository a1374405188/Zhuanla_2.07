package com.beikbank.android.data2;

import java.io.Serializable;

public class getDingQiXiangQing implements Serializable{
	/**
	 * 购买时间
	 */
  public String create_time ;
 
  /**
	 * 当前本金余额
	 */
public String  current_principal_balance ;
/**
	 * 当前本息
	 */
public String  current_calculate_amount ;
/**
	 * 到期时间
	 */
public String expired_date ;
/**
 * 当前收益
 */
public String intrest_total ;
/**
 * 预期总收益
 */
public String intrest_total_estimate ;
/**
 * 预期年化收益率
 */
public String intrest_year ;
/**
 * 是否复投  1 否 0是
 */
public String is_reinvest ;
/**
 * 起息时间
 */
public String original_calculate_date ;
/**
 * 剩余到期天数
 */
public String remain_return_day ;
/**
 * 加息收益率
 */
public String  return_rate ;
/**
 * 加息收益率
 */
public String  return_rate_added ;
/**
 * 期限
 */
public String     term ;
/**
 * 
 */
public String     status ;
/**
 * 本金+当前收益
 */
public String     total_payable ;

}
