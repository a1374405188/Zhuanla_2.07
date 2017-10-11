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
public class CheckBank implements Serializable{
	/**
	 * 0：需要绑卡银行卡，1：不需要绑定的银行卡
	 */
  public String UserCardLimit;
 
}
