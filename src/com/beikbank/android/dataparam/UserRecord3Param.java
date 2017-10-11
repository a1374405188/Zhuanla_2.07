package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * 
 * @author Administrator
 *定期收益
 */
public class UserRecord3Param {
	/**
	 *用户Id
	 */
	@ParamAnnotation(index=0)	
	public String memberID="";
	/**
	 * 前n个月
	 */
	@ParamAnnotation(index=1)	
	  public String monthsBefore="";
}
