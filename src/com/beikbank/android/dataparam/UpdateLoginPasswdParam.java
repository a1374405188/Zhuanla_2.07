package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 修改登录密码
 */
public class UpdateLoginPasswdParam {
	  @ParamAnnotation(index=0)	
	  public String memberID;
		@ParamAnnotation(index=1)
	  public String oldLoginPassword;
		@ParamAnnotation(index=2)
	  public String newLoginPassword;
}
