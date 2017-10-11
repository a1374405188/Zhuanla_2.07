package com.beikbank.android.data;

import java.io.Serializable;

import com.beikbank.android.annotation.BaseParamAnnotation;
import com.beikbank.android.annotation.ParamAnnotation;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 *<p>
 * 赎回
 *</p>
 **/
public class Remdom  implements Serializable
{  
	   /**
	    * 交易时间
	    */
	   @ParamAnnotation(index=0)
	   public String dealTime;
	   /**
	    * 订单号
	    */
	   @ParamAnnotation(index=1)
	   public String orderNumber;
	   /**
	    * 状态
	    */
	   @ParamAnnotation(index=2)
	   public String status;
 
}
