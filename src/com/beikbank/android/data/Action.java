package com.beikbank.android.data;

import java.io.Serializable;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 活动
 */
public class Action implements Serializable{
	/**
	 * 活动编号
	 */
  public String activityNO;
  /**
   * 广告土
   */
  public String thumb;
  /**
   * 1跳转，2不跳转
   */
  public String type;
}
