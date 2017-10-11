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
public class ReqPayforPParam {
	/**
	 * 产品类型
	 */
	@ParamAnnotation(index=0)	
	public String productType="";
	/**
	 * 产品编号
	 */
	@ParamAnnotation(index=1)	
	  public String productId="";
	/**
	 *产品名称
	 */
	@ParamAnnotation(index=2)	
	  public String productName="";
	/**
	 * 金额
	 */
	@ParamAnnotation(index=3)	
	  public String amount="";
	/**
	 * 用户编号 
	 */
	@ParamAnnotation(index=4)	
	  public String memberID="";
	/**
	 * 0银行卡，1余额，2组合
	 */
	@ParamAnnotation(index=5)	
	  public String tradeMode="";
}
