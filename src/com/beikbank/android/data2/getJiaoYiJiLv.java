package com.beikbank.android.data2;

import java.io.Serializable;

public class getJiaoYiJiLv implements Serializable{
	/**
	 * 
	 */
  public String record_id ;
 
  /**
	 * 1：充值 2：银行卡购买 3：账户购买 4：赎回 5：提现 6:转让
	 */
public String trade_type ;
/**
	 * 
	 */
public String order_id ;
/**
 * //0：成功 1：失败 4：处理中 5：失效 2：订单生成状态
 */
public String order_status ;
/**
 * 
 */
public String create_time ;
/**
 * 
 */
public String product_id ;
/**
 * 4活期
 */
public String product_type_pid ;
/**
 * 
 */
public String amount ;
/**
 * 
 */
public String product_name ;
/**
 * 
 */
public String term ;

}
