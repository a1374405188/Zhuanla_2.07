package com.beikbank.android.data;

import java.io.Serializable;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *钱包充值和提现交易记录
 */
public class Qianbao3 implements Serializable
{

/**
 * 订单号id
 */
public String tradingID;
/**
 * 交易金额
 */
public String transactionAmount;
/**
 * 交易状态
 */
public String transactionStatus;
/**
 * 交易时间
 */
public String purchaseDate;
/**
 * 订单号
 */
public String orderNumber;
/**
 * 
 * 交易类型0:购买，1:赎回,2:提现,3:充值，4:定期到期(回余额)，5:定期还款(回银行卡)
 */
public String tradeType;
/**
 * 0定期 1活期 空钱包
 */
public String productType;
/**
 * 交易错误信息
 */
public String retMsg;
}
