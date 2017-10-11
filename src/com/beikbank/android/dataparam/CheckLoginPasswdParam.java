package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 
 */
public class CheckLoginPasswdParam {
	@ParamAnnotation(index=0)	
  public String phoneNumber;
	@ParamAnnotation(index=1)
  public String loginPassword;
}
