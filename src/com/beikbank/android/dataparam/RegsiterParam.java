package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;
import com.beikbank.android.conmon.Cancal;
import com.beikbank.android.conmon.SystemConfig;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 *注册参数
 **/
public class RegsiterParam {
  @ParamAnnotation(index=0)
  public String phoneNumber;
  @ParamAnnotation(index=1)
  public String loginPassword;
  @ParamAnnotation(index=2)
  public String idCode=SystemConfig.SOURCES_CODE;
  
  @ParamAnnotation(index=3)
  public String umDeviceToken="";
  @ParamAnnotation(index=4)
  public String rasType="2";
  @ParamAnnotation(index=5)
  public String rasModel="";
  @ParamAnnotation(index=6)
  public String osVersion="";
  @ParamAnnotation(index=7)
  public String softVersion="";
}
