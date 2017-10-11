package com.beikbank.android.data;

import java.util.ArrayList;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-10
 * 交易记录主体数据
 */
public class TranList_Data {
	/**
	 * success or error
	 */
   public String result;
   /**
    *  return message 
    */
   public String message;
   /**
    * @see  TransactionInfo
    */
   public ArrayList<TransactionInfo> data;
}
