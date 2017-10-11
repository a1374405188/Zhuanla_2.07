package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-29
 *定期购买名单列表
 */
public class PayListParam {
	/**
	 * 产品编号
	 */
	@ParamAnnotation(index=0)	
   public String termbondCode;
   /**
    * 开始行
    */
	@ParamAnnotation(index=1)	
   public String startLine;
   /**
    * 结束行
    */
	@ParamAnnotation(index=2)	
   public String endLine;
}
