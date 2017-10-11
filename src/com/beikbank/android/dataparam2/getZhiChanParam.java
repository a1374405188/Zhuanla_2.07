package com.beikbank.android.dataparam2;
/**
 * 
 * @author Administrator
 *得到定期或者活期资产信息
 */
public class getZhiChanParam 
{  
	/**
	 *活期时到期传 1-转让中 ，未到期传0-计息中；定期到期未到期都传1
	 */
   public String  is_reinvest;
   /**
    *
   /**
    * 
    */
   public String page_index;
   
   /**
    *每页显示数量
    */
   public String  page_size;
   
   /**
    * 3-定期 4-活期
    */
   public String  prod_type;
   
   /**
    * 状态，到期未到期 0 - 未到期 1- 已到期
    */
   public String status;
   /**
    * 
    */
   public String user_id;
 
   
}
