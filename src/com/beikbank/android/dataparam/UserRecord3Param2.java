package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * 
 * @author Administrator
 *定期收益 新的请求参数
 */
public class UserRecord3Param2 {
	/**
	 *用户Id
	 */
	@ParamAnnotation(index=0)	
	public String memberID="";
	/**
	 * 页计数
	 */
	@ParamAnnotation(index=1)	
	  public String pageSize="";
	/**
	 * 开始页
	 */
	@ParamAnnotation(index=2)	
	  public String page="";
}
