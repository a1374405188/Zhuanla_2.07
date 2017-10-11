package com.beikbank.android.data;

import java.io.Serializable;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *钱包充值，提现请求返回数据
 */
public class Qianbao2 implements Serializable{

/**
 * 订单号
 */
public String tradeNo;
/**
 * 1短信验证，不为1支付密码
 */
public String fuiouPay;
}
