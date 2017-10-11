package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 请求修改预留手机号
 */
public class PhoneQinqiuParam {
	/**
	 * 用户id
	 */
	@ParamAnnotation(index=0)	
  public String memberID="";
	/**
	 * 银行卡号
	 */
	@ParamAnnotation(index=1)	
  public String cardNumber="";
	/**
	 * 手机号
	 */
	@ParamAnnotation(index=2)	
  public String phonenumber="";
}
