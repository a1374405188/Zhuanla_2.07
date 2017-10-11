package com.beikbank.android.data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 红包
 * @author Administrator
 *
 */
public class Hongbao implements Serializable,Comparable{
  public String couponToken;
  /**
   * needCount
   */
  public String needCount;
  public String beginTime;
  public String cType;
  public String endTime;
  public String content;
  /**
   * 作用的类型 0为定期，1为活期，2为通用',
   */
  public String usePType;
  public String title;
  /**
   * 最小的天数
   */
  public String mustMinDay;
  /**
   * 最大的天数
   */
  public String mustMaxDay;
  public String name;
  /**
   * 功能描述
   */
  public String aim;
  /**
   * 产品必须的最小金额
   */
  public String mustMeet;
  public String couponStockId;

  /**
   *是否可以叠加，默认为0 不可以叠加，1为可以叠加
   */
  public String unLimit;
  /**
   * 1为已经过期，0为未过期
   */
  public String isOld;
@Override
public int compareTo(Object another) {
	Hongbao hb1=(Hongbao)another;
	String s1=hb1.content;
	JSONObject jo=null;
	JSONObject jo2=null;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date de1=null;
	Date de2=null;
	try {
		de1=df.parse(hb1.endTime);
		de2=df.parse(endTime);
		
		jo=new JSONObject(s1);
		jo2=new JSONObject(content);
		s1=jo.getString("couponAmont");
		
		String s2=jo2.getString("couponAmont");
		double d1=Double.parseDouble(s1);
		double d2=Double.parseDouble(s2);
		
		if(d1>d2)
		{
			return 1;
		}
		else if(d1==d2)
		{
			if(hb1.endTime.equals(endTime))
			{
				return 0;
			}
			else
			{
				
				if(de1.after(de2))
				{
					return -1;
				}
				else
				{
					return 1;
				}
			}
		}
		else
		{

			return -1;
		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 1;
}

}
