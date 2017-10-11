package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 确认修改预留手机号
 */
public class PhoneQuerenParam {
	/**
	 * 用户id
	 */
	@ParamAnnotation(index=0)	
  public String memberID="";
	/**
	 * 验证码id
	 */
	@ParamAnnotation(index=1)	
  public String verifiId="";
	/**
	 * 验证码
	 */
	@ParamAnnotation(index=2)	
  public String verifiCode="";
}
