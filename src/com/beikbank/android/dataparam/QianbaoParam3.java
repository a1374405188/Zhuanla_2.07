package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *钱包充值和提现交易记录
 */
public class QianbaoParam3 
{  
   /**
    * 用户id
    */
	@ParamAnnotation(index=0)
   public String memberID;
   /**
    * 开始行数
    */
	@ParamAnnotation(index=1)	
   public String startLine;
   /**
    * 结束行数
    */
	@ParamAnnotation(index=2)
   public String endLine;
   /**
    * 2提现，3充值
    */
   @ParamAnnotation(index=3)
   public String tradeType;

}
