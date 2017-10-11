package com.beikbank.android.data2;

import java.io.Serializable;

public class Dingdan2 implements Serializable
{     
	  /**
	   * 交易类型
	   * 1购买活期
	   * 2购买定期
	   * 3活期赎回
	   * 4
	   */
      public int type;
      public static int type1=1;
      public static int type2=2;
      public static int type3=3;
     
      
      /**
       * 金额
       */
      public String money;
      
      /**
       * 交易账户
       * 1,银行卡
       * 2,钱包
       */
      public int zhanghu;
      
      /**
       * 订单
       */
      public String dingdan;
      
      /**
       * 交易时间
       */
      public String time;
      
      /**
       * 期限
       */
      public String qixian;
      
      /**
       * 收益率
       */
      public String shouyi;
      /**
       * 消息
       */
      public String msg;
      
      /**
       * 红包
       */
      public String hongbao;
      /**
       * 支付金额
       */
      public String zhifu;
      
      /**
       * 交易状态
       * 1处理中
       * 2成功
       * 3失败
       */
      public String state;
      public static String state1="1";
      public static String state2="2";
      public static String state3="3";
      
      
      
      
      
      public static int zhanghu1=1;
      public static int zhanghu2=2;
}
