package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 **/
public class LoginParam {
  @ParamAnnotation(index=0)
  public String phoneNumber;
  @ParamAnnotation(index=1)
  public String loginPassword;
  @ParamAnnotation(index=2)
  public String umDeviceToken;
  @ParamAnnotation(index=3)
  public String rasType;
  @ParamAnnotation(index=4)
  public String rasModel;
  @ParamAnnotation(index=5)
  public String osVersion;
  @ParamAnnotation(index=6)
  public String softVersion;
}