package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-5
 * 
 */
public class OneKeyParam {
	//id必须为数字
	 @ParamAnnotation(index=0)
  public String userId;
	 @ParamAnnotation(index=1)
  public String rasType;
	 @ParamAnnotation(index=2)
  public String rasModel;
	 //1ios 2 android
	 @ParamAnnotation(index=3)
  public String osVersion;
	 @ParamAnnotation(index=4)
  public String rasPhoneNumber;
	 @ParamAnnotation(index=5)
  public String softVersion;
	 @ParamAnnotation(index=6)
//  public String rasIp;
//	 @ParamAnnotation(index=7)
  public String netType;
	 @ParamAnnotation(index=7)
  public String rasIM;
	 @ParamAnnotation(index=8)
  public String content;
}
