package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * 标示公告(即通知)已读
 * @author Administrator
 *
 */
public class MessageParam3 {
  /**
   * 用户id	
   */
	@ParamAnnotation(index=0)
  public String userId;
  /**
   * 如 1,2,3
   */
	@ParamAnnotation(index=1)
  public String noticeId;
}
