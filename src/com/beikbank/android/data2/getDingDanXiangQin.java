package com.beikbank.android.data2;

import java.io.Serializable;

public class getDingDanXiangQin implements Serializable{
	/**
	 * 金额
	 */
  public String amount ;
  /**
   * 
   */
  public String  create_time;
  /**
   * 
   */
  public String  error_msg;
  /**
   * 
   */
  public String  order_id;
  /**
   * 0：成功 1：失败 4：处理中 5：失效 2：订单生成状态
   */
  public String   order_status;
  /**
   * 
   * 
   * 1.充值2.银行卡购买3.钱包购买5.提现6.转让 11.网银充值12.网银购买（app查询购买和充值时记得把网银充值和购买选上），啥不传显示全部 13 还款 15还款
 16未匹配还钱包,17未匹配还钱包成功,18未匹配还钱包失败
   */
  public String    order_type;
  /**
   * 
   */
  public String      product_name;
  /**
   * 金根起得名字。1.今日24点显示收益 2.明日开始计算收益 3.满标后次日开始计算收益
   */
  public String     qixi_type;
  /**
   * 
   */
  public String         term;
  /**
   * 基本收益
   */
  public String benchmark_return_rate;
  /**
   * 手续费
   */
  public String service_amt;
  /**
   * 加赠收益
   */
  public String increase_interest_return_rate;
  /**
   * 支付金额
   */
  public String real_amount;
  /**
   * 红包金额
   */
  public String coupon_amount;
}
