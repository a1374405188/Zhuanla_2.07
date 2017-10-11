package com.beikbank.android.data;

import java.io.Serializable;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-25
 */
public class Qianbao7 implements Serializable{
	/**
	 * 交易号
	 */
  public String tradeNo;
  /**
   * 活期金额
   */
  public String fundLimit;
  /**
   * 定期金额
   */
  public String bondLimit;
  /**
   * 充值金额
   */
  public String rechargeLimit;
  /**
   * 手续费
   */
  public String poundage;
}
