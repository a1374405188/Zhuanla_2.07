package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2014-12-24
 * 
 */
public class BindBankCardParam {
	@ParamAnnotation(index=1)
   public  String memberID;
	@ParamAnnotation(index=0)
   public  String cardNumber;
	@ParamAnnotation(index=2)
   public  String cardType;
}
