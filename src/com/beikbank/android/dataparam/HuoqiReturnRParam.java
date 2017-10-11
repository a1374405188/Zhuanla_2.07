package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-7-2
 *活期赎回请求
 */
public class HuoqiReturnRParam 
{   
	/**
	 * 用户Id
	 */
	 @ParamAnnotation(index=0)
    public String memberID;
	 /**金额
		 * 
		 */
    @ParamAnnotation(index=1)
    public String amount;
	 /**
		 * 活期编号
		 */
   @ParamAnnotation(index=2)
   public String fundId;
	 /**
		 * 活期名称
		 */
 @ParamAnnotation(index=2)
 public String productName;
    
}
