package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 是否设置了手势密码
 */
public class ShoushiIsSetParam {
	@ParamAnnotation(index=0)	
  public String userId="";
	/**
	 * 1ios 2android
	 */
	@ParamAnnotation(index=1)	
	  public String type="";
}
