package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 找回登录密码时 验证密码
 */
public class CheckCode2Param {
	@ParamAnnotation(index=0)
	public String phoneNumber;
	@ParamAnnotation(index=1)
	public String realName;
	@ParamAnnotation(index=2)
	public String idNumber;
	@ParamAnnotation(index=3)
	public String hasAuthenticated="1";
	@ParamAnnotation(index=4)	
	public String verifiId;
	@ParamAnnotation(index=5)
	public String verificode;
	

}
