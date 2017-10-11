package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 
 */
public class CheckCodeParam {
	@ParamAnnotation(index=1)	
	public String verifiId;
	@ParamAnnotation(index=0)
	public String verificode;
	@ParamAnnotation(index=2)
	public String phoneNumber;

}
