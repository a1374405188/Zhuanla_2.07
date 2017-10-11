package com.beikbank.android.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *钱包交易记录
 */
public class QianbaoRecord_data implements Serializable{
	  public String result;
	  /**
	   * 返回的信息
	   */
	  public String message;
	  /**
	   * 数据主体
	   */
	  public ArrayList<QianbaoRecord> data;
}
