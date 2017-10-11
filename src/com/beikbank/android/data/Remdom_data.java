package com.beikbank.android.data;

import com.beikbank.android.annotation.BaseParamAnnotation;
import com.beikbank.android.annotation.ParamAnnotation;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 *<p>
 * 赎回 返回信息
 *</p>
 **/
public class Remdom_data 
{  

   
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
  public Remdom data;
  
}
