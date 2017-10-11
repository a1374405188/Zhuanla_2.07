package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-4-14
 * 
 */
public class CheckBankAndPhoneParam {
	
  @ParamAnnotation(index=0)
  public String cardNumber;
  @ParamAnnotation(index=1)
  public String memberID;
  @ParamAnnotation(index=2)
  public String phonenumber;
}
