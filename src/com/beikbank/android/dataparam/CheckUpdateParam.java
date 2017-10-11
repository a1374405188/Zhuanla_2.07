package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-4
 * 
 */
public class CheckUpdateParam {
	 @ParamAnnotation(index=0)
  public String version;
	/**
	 * 1 android 
	 */
	 @ParamAnnotation(index=1)
  public String osType;
}
