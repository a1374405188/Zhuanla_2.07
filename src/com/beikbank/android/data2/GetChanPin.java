package com.beikbank.android.data2;

import java.io.Serializable;
import java.util.ArrayList;

import android.R.integer;

public class GetChanPin implements Serializable,Comparable
{
	/**
	 * 基准收益率
	 */
   public String benchmark_return_rate;
   
   /**
    * 借款人服务费基准
    */
   public String     borrower_service_charge_benchmark_id;
   
   /**
    * 借款人服务费费率
    */
   public String      borrower_service_charge_rate;
   
   /**
    * 加息收益率
    */
   public String     increase_interest_return_rate;
   /**
    * 0是否新手标
    */
   public String     is_new_user_mark;
   /**
    * 累计购买金额
    */
   public String  cumulative_purchase_amount;
   /**
    * 是否可转让
    */
   public String           is_transfer;
   /**
    * 出借人服务费基准
    */
   public String  lender_service_charge_benchmark_id;
   /**
    * 出借人服务费费率
    */
   public String   lender_service_charge_rate;
   /**
    * 最大购买金额
    */
   public String       max_amount;
   /**
    * 最小购买金额
    */
   public String       min_amount;
   /**
    * 上架时间
    */
   public String           on_shelf_time;
   
   /**
    * 可购份额
    */
   public String       pro_share;
   /**
    * 产品类型ID
    */
   public String         product_type_id;
   /**
    *   product_id
    */
   public String       product_id;
   /**
    * 产品名称
    */
   public String      product_name;
   /**
    * 起购金额
    */
   public String       purchase_amount;
   /**
    * 份额更新时间
    */
   public String       next_update_time;
   /**
    * 份额更新时间  长度为2的表示每天更新份额； 其它表示具体时间
    */
   public ArrayList<String>       share_update_time;
   
   
   /**
    * 单笔购买金额
    */
   public String       single_purchase_amount	;
   /**
    * 起息时间  1 购买当日起息；2 T+1(购买后次日起息)；3 满标后次日起息
    */
   public String    start_calculating_interest_time;
   /**
    * 转让服务费费率
    */
   public String      transfer_service_rate;
   /**
    * 期限0表示随存随取,并且表示活期；其它表示天数
    */
   public String     term;
   /**
    * 0:智能化产品；1散标，产品化散标
    */
   public String     type;
   /**
    * 1散标 2产品化散标 3智能定期 4智能活期
    */
   public String  product_type_pid;
@Override
public int compareTo(Object another) {
	
	int a;
	int b;
	try
	{   
		GetChanPin gcp=(GetChanPin) another;
		a=Integer.parseInt(term);
		b=Integer.parseInt(gcp.term);
		if(a<b)
		{
			return -1;
		}
		else if(a==b)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	
	
	
	return 0;
}
   
}
