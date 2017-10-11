package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 
 */
public class SendCodeParam {
	@ParamAnnotation(index=0)		
  public String phoneNumber;
  /**
   * 验证类型。1.注册 2.绑定手机 3.找回密码 4.额度提升预留手机号验证
   */
	@ParamAnnotation(index=1)	
  public String checkCodeType;
}
