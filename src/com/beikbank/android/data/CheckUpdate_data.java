package com.beikbank.android.data;

import java.util.ArrayList;

import com.beikbank.android.annotation.BaseParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-4
 * 
 */
public class CheckUpdate_data {
	/**
	 * 是否成功
	 */
  @BaseParamAnnotation(base=1)
  public String result;
  /**
   * 返回的信息
   */
  @BaseParamAnnotation(base=1)
  public String message;
  /**
   * 数据主体
   */
 @BaseParamAnnotation(base=2)
  public CheckUpdate data;
}
