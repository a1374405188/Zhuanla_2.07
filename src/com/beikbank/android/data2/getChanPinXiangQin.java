package com.beikbank.android.data2;

import java.io.Serializable;
import java.util.ArrayList;

import android.R.integer;

public class getChanPinXiangQin implements Serializable
{
	/**
	 * 基准收益率
	 */
   public String benchmark_return_rate;
   
   /**
	 * 加息收益率
	 */
  public String    increase_interest_return_rate;
  /**
 	 * 起息方式
 	 */
   public String     interestType;
   /**
  	 * 是否新手标
  	 */
    public String      is_new_user_mark;
    /**
  	 * 可购份额
  	 */
    public String     pro_share;
    /**
  	 * 产品ID
  	 */
    public String     product_id;
    /**
  	 * 产品名称
  	 */
    public String     product_name;
    /**
  	 * 还款方式
  	 */
    public String     repayType;
    /**
   	 * 份额更新时间
   	 */
     public String     share_update_time;
     /**
    	 * 期限
    	 */
      public String      term;
      /**
  	 * 0:智能化产品；1散标，产品化散标
  	 */
    public String         type;
}
