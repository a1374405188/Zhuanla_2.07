package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 * 系统通知
 */
public class SysNoticeParam {
	/**
	 * 开始行数
	 */
	   @ParamAnnotation(index=0)
	   public  String startLine;
	   /**
	    * 结束行数
	    */
		@ParamAnnotation(index=1)
	   public  String endLine;
}
