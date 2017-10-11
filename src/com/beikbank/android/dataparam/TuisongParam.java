package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-8-11
 *第一次安装推送
 */
public class TuisongParam {
 @ParamAnnotation(index=0)
 public String umDeviceToken;
 /**
  * 1 iOS 2 android
  */
  @ParamAnnotation(index=1)
 public String rasType;
}
