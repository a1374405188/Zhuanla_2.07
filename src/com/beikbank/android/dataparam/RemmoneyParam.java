package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-16
 *取现和购买时传递的参数
 **/

public class RemmoneyParam {
	/**
	 * 用户Id
	 */
	@ParamAnnotation(index=0)
	public String memberID;
	/**
	 * 金额
	 */
	@ParamAnnotation(index=1)
	public String amount;
	/**
	 * 基金Id
	 */
	@ParamAnnotation(index=2)
	public String fundId;
	/**
	 * 绑卡Id
	 */
	@ParamAnnotation(index=3)
	public String bandCardId;
	/**
	 * 验证码
	 */
	@ParamAnnotation(index=4)
	public String verificode;
	/**
	 * 交易密码
	 */
	@ParamAnnotation(index=5)
	public String tradePassword;
}
