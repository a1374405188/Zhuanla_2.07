package com.beikbank.android.data;

import java.io.Serializable;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *钱包可用余额
 */
public class Qianbao4 implements Serializable
{

	/**
	 * 可用余额
	 */
   public String activeAmount;
   /**
	 * 购买确认中金额
	 */
  public String frozenAmountPurchase;
  /**
	 * 提现确认中金额
	 */
  public String frozenAmountWithdraw;
 /**
	 * 活期余额
	 */
 public String fundLimit;
/**
 * 定期余额
 */
 public String bondLimit;
 /**
  * 充值余额
  */
  public String rechargeLimit;
  /**
   * 活期赎回
   */
  public String frozenAmountRedeem;


}
