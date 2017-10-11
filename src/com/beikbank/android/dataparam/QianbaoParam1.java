package com.beikbank.android.dataparam;

import java.io.Serializable;

import android.app.Activity;

import com.beikbank.android.annotation.ParamAnnotation;
import com.beikbank.android.utils.Utils;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-27
 *充值和提现，请求参数
 */
public class QianbaoParam1 implements Serializable
{  
   /**
    * 用户id
    */
	@ParamAnnotation(index=0)	
   public String memberID;
   /**
    * 金额
    */
	@ParamAnnotation(index=1)	
   public String amount;
	/**
	 * 软件版本
	 */
	@ParamAnnotation(index=3)
	public  String softVersion;
	public QianbaoParam1(Activity act)
	{
		softVersion=Utils.getVersion(act);
	}
	
}
