package com.beikbank.android.data2;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *判断用户是否注册
 */
public class TiXianQingQiu implements Serializable
{
	/**
	 * 实际消费金额
	 */
   public String  actual_cash;
   /**
	 * 扣除免费次数
	 */
  public String  deduct_free;
  /**
	 *扣除充值未投资账户金额 
	 */
 public String  deduct_recharge_amt;
 /**
	 * 扣除投资回款账户金额
	 */
public String  deduct_repay_amt;
/**
	 * 扣除手续费
	 */
public String  deduct_service_amt;
   
}
