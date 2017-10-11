package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-9
 * 
 */
public class ProjectListParam {
	/**
	 * 基金Id
	 */
	@ParamAnnotation(index=0)	
  public String fundId="";
	/**
	 * 数据开始行数
	 */
	@ParamAnnotation(index=1)	
  public String startLine="";
	/**
	 * 数据结束行数
	 */
	@ParamAnnotation(index=2)	
  public String endLine="";
}
