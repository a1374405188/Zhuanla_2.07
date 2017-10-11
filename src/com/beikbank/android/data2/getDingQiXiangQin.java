package com.beikbank.android.data2;

import java.io.Serializable;

public class getDingQiXiangQin implements Serializable{
	/**
	 * 资产ID
	 */
  public String assets_id ;
 
  /**
	 * 
	 */
public String create_time ;
/**
	 * 当前本金余额
	 */
public String   current_principal_balance ;
/**
 * 本息
 */
public String current_calculate_amount;
/**
 * 累计收益
 */
public String  intrest_total ;
/**
 * 是否复投 1 否 0 是
 */
public String is_reinvest ;
/**
 * 到期日期
 */
public String maturity_date ;
/**
 * 
 */
public String prod_Id ;
/**
 * 
 */
public String product_name ;
/**
 * 剩余到期天数
 */
public String remain_return_day ;

}
