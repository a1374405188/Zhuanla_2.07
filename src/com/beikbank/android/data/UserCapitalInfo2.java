package com.beikbank.android.data;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-21
 * 
 */
public class UserCapitalInfo2 implements Serializable{
	
	  /**
	   * 产品编号
	   */
	  public String termbondCode;
	  /**
	   * 产品名称
	   */
	  public String termbondName;
	  /**
	   * 年化收益率
	   */
	  public String yearRate;
	  /**
	   * 加送收益率
	   */
	  public String extraRate;
	  /**
	   * 理财期限
	   */
	  public String termbondPeriod;
	  /**
	   * 可购金额
	   */
	  public String remainAmount;
	  /**
	   * 状态
	   */
	  public String status;
	  /**
	   * 起息时间
	   */
	  public String countDate;
	  /**
	   * 还款时间
	   */
	  public String expirationDate;
	  /**
	   * 购买的金额
	   */
	  public String amount;
	  /**
	   * 返款倒计时
	   */
	  public String  expirationCountdown;
	  /**
	   * 预期收益
	   */
	  public String predictIncome;
}
