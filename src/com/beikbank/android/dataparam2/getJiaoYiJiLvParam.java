package com.beikbank.android.dataparam2;

import java.util.ArrayList;

/**
 * 
 * @author Administrator
 *得到交易记录
 */
public class getJiaoYiJiLvParam 
{  
	/**
	 * 每页记录数
	 */
   public String  limit;
  
   /**
	 * 初始行号
	 */
  public String  offset;
  /**
	 *1.充值2.银行卡购买3.钱包购买5.提现6.转让 11.网银充值12.网银购买（app查询购买和充值时记得把网银充值和购买选上），啥不传显示全部
	 */

 public ArrayList<String>     trade_type=new ArrayList<String>();
/**
 * id
 */
  public String   user_code;
}
