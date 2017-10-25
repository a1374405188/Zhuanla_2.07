package com.beikbank.android.data2;

import java.io.Serializable;

public class getJiaoYiJiLv implements Serializable{
	/**
	 * 
	 */
  public String record_id ;
 
  /**
	 *
	 * 
	 * 1.充值2.银行卡购买3.钱包购买5.提现6.转让 11.网银充值12.网银购买（app查询购买和充值时记得把网银充值和购买选上），啥不传显示全部
     * 13 还款 15还款  16未匹配还钱包,17未匹配还钱包成功,18未匹配还钱包失败
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
