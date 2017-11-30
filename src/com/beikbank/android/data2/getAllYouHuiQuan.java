package com.beikbank.android.data2;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.beikbank.android.data.Hongbao;
import com.beikbank.android.utils.NumberManager;

public class getAllYouHuiQuan implements Serializable,Comparable
{
	/**
	 * 账户ID
	 */
   public String coupon_name;
   /**
    * 优惠券id
    */
   public String  coupon_no;
   /**
    * 优惠券获取时间
    */
   public String   coupon_obtain_time;
   /**
    * 	优惠券金额
    */
   public String   coupon_value;
   /**
    * 过期时间
    */
   public String   expire_date;
   /**
    * 最小可用期限
    */
   public String   floor_term;
   /**
    * 最大可用期限
    */
   public String   upper_term;
   /**
    * 最小可用金额
    */
   public String   least_amount;
   /**
    * 优惠券使用状态代码0未使用1已使用2已过期3被锁定
    */
   public String    usage_status;
   /**
    * 优惠券使用状态名称
    */
   public String    usage_status_name;
   /**
    * 有效期
    */
   public String    valid_term;
@Override
public int compareTo(Object another) {
	getAllYouHuiQuan gh=(getAllYouHuiQuan) another;
	JSONObject jo=null;
	JSONObject jo2=null;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date de1=null;
	Date de2=null;
	try
	{  
		if(NumberManager.isDaYu(gh.coupon_value,coupon_value)==1)
		{
			return 1;
		}
		else if(NumberManager.isDaYu(gh.coupon_value,coupon_value)==0)
		{
			de1=df.parse(gh.expire_date);
			de2=df.parse(expire_date);
			if(de1.after(de2))
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		else
		{
			return -1;
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return 0;
}
}
