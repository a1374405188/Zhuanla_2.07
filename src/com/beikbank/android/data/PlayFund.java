package com.beikbank.android.data;

import java.io.Serializable;


/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-9
 * 购买返回信息
 */
public class PlayFund implements Serializable{
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
   * 预计收益时间
   */
  public String preValueDate;
  /**
   * 购买金额
   */
  public String planAmount;
}
