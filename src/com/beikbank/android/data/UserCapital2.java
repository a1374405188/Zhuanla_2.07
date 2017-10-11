package com.beikbank.android.data;

import java.io.Serializable;

import com.beikbank.android.annotation.DBPrimaryKey;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-4
 * 
 */
public class UserCapital2  implements Serializable{
	/**
	 * 活期总资产
	 */
   public String fundAmount;
   /**
    * 定期总资产
    */
   public String bondAmount;
   /**
    * 活期昨日收益
    */
   public String fundInterestY;
   /**
    * 定期昨日收益
    */
   public String bondInterestY;
   /**
    * 活期累计收益
    */
   public String fundInterestT;
   /**
    * 定期累计收益
    */
   public String bondInterestT;
   /**
    * 总资产，该字段服务器没有返回，是用来数据库缓存的
    */
   @DBPrimaryKey(privaryKey=1)
   public String allMoney;
}
