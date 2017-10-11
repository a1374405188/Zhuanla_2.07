package com.beikbank.android.data.type;

import java.io.Serializable;

/**
 * 
 * @author Administrator 订单状态类
 */
public class DingDan implements Serializable {
	/**
	 * 实际扣款金额
	 */
	public String amount;
	/**
	 * 0：成功 1：失败 4：处理中 5：失效 2：订单生成状态
	 */
	public String status;
	/**
	 * 订单号
	 */
	public String orderNumber;
	/**
	 * 交易模式0:银行卡，1:余额
	 */
	public String fangshi="";
	/**
	 * 支付类型1购买定期 2购买活期 3充值 4赎回 5活期转定期6提现
	 */
	public int leixing;

	/**
	 * 消息
	 */
	public String retMsg;
	public String time;
}
