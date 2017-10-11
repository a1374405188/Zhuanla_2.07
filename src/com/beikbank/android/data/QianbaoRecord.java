package com.beikbank.android.data;
/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-7-13
 */
public class QianbaoRecord {
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
 *  交易类型0：购买，1：赎回2提现3充值4定期到期(回余额)5定期返款(回银行卡)

 */
public String tradeType;
/**
 * 金额
 */
public String amount;
/**
 * 0定期 1活期 空钱包
 */
public String productType;
/**
 * id
 */
public String productId;
/**
 * 名字
 */
public String productName;
}
