package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-10
 * 消息使用和删除
 */
public class MessageParam {
   /**
    * 用户id
    */
	@ParamAnnotation(index=0)
   public String userId;
   /**
    * 现在未使用 用''代替
    */
	@ParamAnnotation(index=1)
   public String type;
   /**
    * 开始行
    */
	@ParamAnnotation(index=2)
   public String startLine;
   /**
    * 结束行
    */
	@ParamAnnotation(index=3)
   public String endLine;
}
