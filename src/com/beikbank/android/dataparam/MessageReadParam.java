package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-10
 * 
 */
public class MessageReadParam {
	  /**
	    * 用户id
	    */
		@ParamAnnotation(index=0)
	   public String userId;
	   /**
	    * 消息id 多个消息时中间以,隔开
	    */
		@ParamAnnotation(index=1)
	   public String id;
}
