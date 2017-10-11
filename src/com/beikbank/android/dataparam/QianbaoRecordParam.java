package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 
 */
public class QianbaoRecordParam {
	/**
	 * id
	 */
  @ParamAnnotation(index=0)	
  public String memberID="";
  @ParamAnnotation(index=1)	
  public String startLine="";
  @ParamAnnotation(index=2)	
  public String endLine="";
  /**
   * 
   */
  @ParamAnnotation(index=3)	
  public String tradeType="";
}
