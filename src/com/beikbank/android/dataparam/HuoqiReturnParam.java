package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-7-2
 *活期赎回确认
 */
public class HuoqiReturnParam 
{   
	/**
	 * 用户Id
	 */
	 @ParamAnnotation(index=0)
    public String memberID;
	 /**编号
		 * 
		 */
    @ParamAnnotation(index=1)
    public String tradeNo;
	 /**
		 * 密码
		 */
   @ParamAnnotation(index=2)
   public String tradePassword;
    
}
