package com.beikbank.android.data2;

import java.io.Serializable;

public class GetUserZhiChan2 implements Serializable
{
	/**
	 *当对应产品小类总资产
	 */
   public String  currCapValue;
   
   /**
    * 产品名称
    */
   public String      product_name;
   /**
    * 产品类别ID  14	零活赚 15	定易赚 16	散标产品 24	智投资
    */
   public String      product_type_id;
   /**
    * 产品大类ID   1	散标 2	产品化散标 3	智能定期 4	智能活期
    */
   public String     product_type_pid;
  
 
   
}
