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
public class CheckCodeParam3 {
	@ParamAnnotation(index=3)	
	public String verifiId;
	@ParamAnnotation(index=2)
	public String verificode;
	@ParamAnnotation(index=1)
	public String phoneNumber;
	
	@ParamAnnotation(index=0)
	public String memberID;

}
