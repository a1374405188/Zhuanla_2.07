package com.beikbank.android.data;

import java.io.Serializable;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 检查是否需要绑定银行卡
 */
public class CheckBank_data implements Serializable{
	/**
	 * 是否成功
	 */
  public String result;
  /**
   * 返回的信息
   */
  public String message;
  public CheckBank data; 
 
}
