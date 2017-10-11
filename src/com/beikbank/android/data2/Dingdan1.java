package com.beikbank.android.data2;

import java.io.Serializable;

public class Dingdan1 implements Serializable{
	
  /**
	 * 购买类型1 购买活期2购买定期3充值4赎回
   */
  public int index;

  /**
   * 支付类型1银行卡 2余额
   */
  public int type;
  /**
   * 金额
   */
  public String money;
  /**
   * 订单号
   */
  public String dingdan;
  /**
   * 消息
   */
  public String msg;
  /**
   * 1成功2失败3处理中
   */
  public String state;
  
  
  /**
   * 购买活期
   */
  public static int index1=1;
  /**
   * 购买定期
   */
  public static int index2=2;
  /**
   * 充值
   */
  public static int index3=3;
  /**
   * 赎回
   */
  public static int index4=4;
  
  /**
   * 银行卡
   */
  public static int type1=1;
  /**
   * 余额
   */
  public static int type2=2;
  
  
  
  
  /**
   * 成功
   */
  public static String state1="1";
  /***
   * 失败
   */
  public static String state2="2";
  /**
   * 处理中
   */
  public static String state3="3";
}
