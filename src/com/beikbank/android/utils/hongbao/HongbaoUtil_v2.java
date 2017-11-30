package com.beikbank.android.utils.hongbao;

import java.util.ArrayList;
import java.util.Collections;

import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.data2.getYouHuiQuan;
import com.beikbank.android.utils.NumberManager;

public class HongbaoUtil_v2 {
   public static int getCount1(ArrayList<getAllYouHuiQuan> list)
   {
	   int count=select1(list).size();
	   return count;
   }
   /**
    * 选出未使用的红包
    * @param list
    * @return
    */
	public static ArrayList<getAllYouHuiQuan> select1(ArrayList<getAllYouHuiQuan> list)
	{   
		ArrayList<getAllYouHuiQuan> list1=new ArrayList<getAllYouHuiQuan>();
		for(getAllYouHuiQuan gyh:list)
		{
			if("0".equals(gyh.usage_status))
			{
				list1.add(gyh);
			}
		}
		return list1;
	}
	/**
	 * 已使用或者已过期
	 * @param list
	 * @return
	 */
	public static ArrayList<getAllYouHuiQuan> select2(ArrayList<getAllYouHuiQuan> list)
	{   
		ArrayList<getAllYouHuiQuan> list1=new ArrayList<getAllYouHuiQuan>();
		for(getAllYouHuiQuan gyh:list)
		{
			if("1".equals(gyh.usage_status)||"2".equals(gyh.usage_status))
			{
				list1.add(gyh);
			}
		}
		return list1;
	}
	/**
	 * 选出可用的红包
	 * @param list
	 * @return
	 */
	public static ArrayList<getAllYouHuiQuan> select3(String tianshu,String money,ArrayList<getAllYouHuiQuan> list)
	{   
		ArrayList<getAllYouHuiQuan> list1=new ArrayList<getAllYouHuiQuan>();
		for(getAllYouHuiQuan gyh:list)
		{   
			int a=NumberManager.isDaYu(money,gyh.least_amount);
			int b=NumberManager.isDaYu(money,gyh.coupon_value);
			if(a>=0&&b>=0)
			{   
				double d1=NumberManager.StoD(gyh.floor_term);
				double d2=NumberManager.StoD(gyh.upper_term);
				double d3=NumberManager.StoD(tianshu);
				if(d2>0&&d1>0)
				{
					if(d3>=d1&&d3<=d2)
					{
						list1.add(gyh);
					}
				}
				else if(d2>0&&d1==0)
				{
					if(d3<=d2)
					{
						list1.add(gyh);
					}
				}
				else if(d2==0)
				{
					if(d3>=d1)
					{
						list1.add(gyh);
					}
				}
				else
				{
					list1.add(gyh);
				}
				
			}
		}
		return list1;
	}
	/**
	 * 选出红包组合
	 * @param money
	 * @param list
	 * @return
	 */
	public static getAllYouHuiQuan select4(String money,ArrayList<getAllYouHuiQuan> list)
	{
		
		Collections.sort(list);
		getAllYouHuiQuan gyh=null;
		double d1=0;

		double d2=NumberManager.StoD(money);
		for(int i=0;i<list.size();i++)
		{   
			
			double d3=NumberManager.StoD(list.get(i).coupon_value);
			
			
			if(i==0)
			{
				d1=d2-d3;
				gyh=list.get(i);
				if(d1==0)
				{
					gyh=list.get(i);
					break;
				}
			}
			else
			{
				double d4=d2-d3;
				if(d1<0&&d4<0)
				{   
					if(d4>d1)
					{
						d1=d4;
						gyh=list.get(i);
					}
				}
				else if(d1>0&&d4>0)
				{
					if(d4<d1)
					{
						d1=d4;
						gyh=list.get(i);
					}
				}
				else if(d1<0&&d4>0)
				{
					d1=d4;
					gyh=list.get(i);
				}
				else if(d1>0&&d4<0)
				{
					
						
				}
				else if(d4==0)
				{  
					d1=d4;
				    gyh=list.get(i);
				    break;
					
				}
			}
			
			
		}
		
		return gyh;
	}
}
