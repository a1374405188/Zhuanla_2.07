package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *余额提现请求V2参数
 */
public class QianbaoParam7 
{  
   /**
    * 用户id
    */
	@ParamAnnotation(index=0)
   public String memberID;
   /**
    * 金额
    */
	@ParamAnnotation(index=1)	
   public String amount;


}
