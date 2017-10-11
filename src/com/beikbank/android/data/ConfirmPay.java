package com.beikbank.android.data;

import java.io.Serializable;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-21
 * 
 */
public class ConfirmPay implements Serializable{
	/**
	 * 交易时间
	 */
  public String dealTime;
  /**
   * 订单号
   */
  public String orderNumber;
  /**
   * 状态
   */
  public String status;
  /**
   * 实际扣款金额
   */
  public String amount;
  /**
   * 总金额
   */
  public String planAmount;
  /**
   *
   */
  public String preValueDate;
}
