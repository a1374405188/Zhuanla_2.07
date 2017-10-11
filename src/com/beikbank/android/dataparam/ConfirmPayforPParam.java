package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-21
 * 请求交易
 */
public class ConfirmPayforPParam {
	/**
	 * 用户编号
	 */
	@ParamAnnotation(index=0)	
	public String memberID="";
	/**
	 * 交易号
	 */
	@ParamAnnotation(index=1)	
	  public String tradeNo="";
	/**
	 *交易密码
	 */
	@ParamAnnotation(index=2)	
	  public String tradePassword="";

}
