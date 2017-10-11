package com.beikbank.android.data2;

import java.io.Serializable;

public class getHuoQiXiangQin implements Serializable{
	/**
	 * 资产ID
	 */
  public String assetsId ;
 
  /**
	 * 当前资产价值
	 */
public String currCapValue ;
/**
	 * 累计收益
	 */
public String   intreTotal ;
/**
 * 昨日收益
 */
public String   intreYesterday ;
/**
 * 产品ID
 */
public String  prodId ;
/**
 * 赎回资金
 */
public String transCapValue;

}
