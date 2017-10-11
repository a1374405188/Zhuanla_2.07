package com.beikbank.android.data2;

public class getShouZhiMinXi 
{
	/**
	 * 操作金额
	 */
   public String amount;
   /**
    * 月份（比如9月，显示09）
    */
   public String month;
   /**
    *操作时间
    */
   public String operation_time;
   /**
    * 订单号
    */
   public String order_seq;
   /**
    * 充值（1：充值，11网银充值），提现（5：提现），还款（13还款（赎回）成功，15转让成功并还款），购买（2：银行卡购买，3：钱包购买，12网银购买）
    */
   public String order_type;
   /**
    * 操作后余额
    */
   public String remain_amount_after;
   /**
    * 产品大类
    */
   public String product_type_pid;
   /**
    * 手续费
    */
   public String  service_amt;
   /**
    * 年份
    */
   public String year;
}
