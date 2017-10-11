package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *创建订单
 */
public class CreateDingDanParam 
{  
	/**
	 *账户ID
	 */
   public String acc_id;
   /**
    *
   /**
    * 银行卡号
    */
   public String acc_number;
   
   /**
    *充值金额
    */
   public String amount;
   
   /**
    *资产id
    */
   public String assets_id;
   /**
    *订单来源0:移动客户端 1:pc端
    */
   public String order_source="0";
   /**
    *1：充值 2：银行卡购买 3：账户购买 4：赎回 5：提现 6:转让
    */
   public String order_type;
   /**
    * 产品ID
    */
   public String pro_id;
   //public int pro_num=0;
   /**
    * 0:智能化产品；1散标，产品化散标，银行卡购买和账户购买时需要(不写)
    */
   public String pro_type;
   /**
    * 红包组
    */
   //public Object []red_packet_list={1};
   /**
    * 用户id
    */
   public String user_code;
   
}
