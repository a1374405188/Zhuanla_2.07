package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 手势密码是否正确
 */
public class ShoushiIsParam {
	@ParamAnnotation(index=0)	
  public String userId="";
	@ParamAnnotation(index=1)	
	  public String passWord="";
	/**
	 * 1ios 2android
	 */
	@ParamAnnotation(index=2)	
	  public String type="";
}
