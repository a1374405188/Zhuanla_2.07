package com.beikbank.android.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-21
 * 定期产品列表
 */
public class DingqiP3 implements Serializable
{   
	public ArrayList<DingqiP2> productBond;
	    /** 
	         用户标记:0:新手,1:新手以外(暂定)
	   * */
	 public String userLevel;
}
