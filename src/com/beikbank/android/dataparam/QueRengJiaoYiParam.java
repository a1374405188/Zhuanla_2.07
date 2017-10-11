package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * 
 * @author Administrator
 * 富有确认交易请求
 */
public class QueRengJiaoYiParam 
{   
	/**
	 * id
	 */
	@ParamAnnotation(index=0)
	public String memberID;
	/**
	 * 交易编号
	 */
	@ParamAnnotation(index=1)
	public  String tradeNo;
	/**
	 * 交易密码
	 */
	@ParamAnnotation(index=2)
	public String tradePassword;

	
}
