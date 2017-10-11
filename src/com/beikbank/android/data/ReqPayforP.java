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
public class ReqPayforP implements Serializable{
	/**
	 * 交易号 
	 */
  public String tradeNo;
  /**
   * 为1的时候需要输入短线验证嘛不为1的时候输入支付密码
   */
  public String fuiouPay;
}
