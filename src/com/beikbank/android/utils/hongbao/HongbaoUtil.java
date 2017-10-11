package com.beikbank.android.utils.hongbao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.json.JSONObject;

import android.R.interpolator;
import android.app.Activity;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;

public class HongbaoUtil {
	Activity act;
	public HongbaoUtil(Activity act)
	{
		this.act=act;
	}
	/**
	 * 计算红包的金额
	 * @param list
	 * @return
	 */
	public static String getCountMoney(ArrayList<Hongbao> list)
	{
		String s="0";
		JSONObject jo=null;
		Hongbao hb1=null;
		try
		{
		   for(int i=0;i<list.size();i++)
		  {    
			   hb1=list.get(i);
			   String s1=hb1.content;
			   jo=new JSONObject(s1);
			   s1=jo.getString("couponAmont");
			   s=NumberManager.getAddString(s,s1,8);
		  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	/**
	 * 得到红包金额
	 * @return
	 */
	public static String getMoney(Hongbao hb)
	{
		String money="0";
		JSONObject jo=null;
		try
		{
			jo=new JSONObject(hb.content);
			money=jo.getString("couponAmont");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return money;
	}
	/**
	 * 计算优惠券金额
	 * ArrayList<String> list2 list2索引
	 */
	public static String countMoney(ArrayList<Hongbao> list,ArrayList<String> list2)
	{   
		String money="0";
		//优惠券标签
		String tok="";
		try
		{
		  Hongbao hb=null;
		 for(int i=0;i<list2.size();i++)
		 {
			hb=list.get(Integer.parseInt((String) list2.get(i)));
			String s1=hb.content;
			JSONObject jo=null;
			jo=new JSONObject(s1);
			s1=jo.getString("couponAmont");
			money=NumberManager.getAddString(money,s1,8);
			if(tok.equals(""))
			{
				tok=hb.couponToken;
			}
			else
			{
				tok+=","+hb.couponToken;
			}
		 }
		}
        catch (Exception e) {
			
			e.printStackTrace();
		}
		money=NumberManager.getString(money,"1",0);
		//tv1.setText(money+"元");
		//tv2.setText("共"+list2.size()+"项");
		BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao,money);
		BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,tok);
		return money;
	}
	/**
	 * 得到红包组合的索引
	 * @param list
	 * @param list1
	 * @return
	 */
	public  ArrayList<String> getSelectIndex(ArrayList<Hongbao> list,ArrayList<Hongbao> list1)
	{
		ArrayList<String> list3=new ArrayList<String>();
		for(Hongbao hb:list1)
		{
			int i=list.indexOf(hb);
			if(i>=0)
			{
				list3.add(i+"");
			}
		}
		
		
		return list3;
	}
	/**
	 * 选择默认红包组合
	 * @param list
	 * @param buy够买的金额
	 * @return
	 */
	public  ArrayList<Hongbao> select2(ArrayList<Hongbao> list,String buy)
	{   
		//可复选红包总值
		String money="0";
		Hongbao hb1=null;
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		JSONObject jo=null;
		try
		{
		   for(Hongbao hb2:list)
		{   
			 //找出不可复选的最大的红包
			if(hb2.unLimit.equals("0"))
			{
				if(hb1==null)
				{
					hb1=hb2;
				}
				else
				{
					String s1=hb1.content;
					String s2=hb2.content;
					
					jo=new JSONObject(s1);
					s1=jo.getString("couponAmont");
					jo=new JSONObject(s2);
					s2=jo.getString("couponAmont");
					
					double d1=Double.parseDouble(s1);
					double d2=Double.parseDouble(s2);
					if(d1<d2)
					{
						hb1=hb2;
					}
					if(d1==d2)
					{
						Date de1=getDate(hb1.endTime);
						Date de2=getDate(hb2.endTime);
						if(de1.after(de2))
						{
							hb1=hb2;
						}
					}
				}
			}
			//计算可复选红包的总值
			else
			{
				jo=new JSONObject(hb2.content);
				String s1=jo.getString("couponAmont");
				money=NumberManager.getAddString(money,s1,8);
			}
			
		}
		   //
		   String s1="0";
		   if(hb1!=null)
		   {
		     jo=new JSONObject(hb1.content);
		     s1=jo.getString("couponAmont");
		     
		       double d1=Double.parseDouble(s1);
			   double d2=Double.parseDouble(money);
			   if(d1>d2)
			   {
				   list2=new ArrayList<Hongbao>();
				   list2.add(hb1);
			   }
			   else
			   {
				   list2=select3(list, buy);
			   }
		   }
		   else
		   {
			   list2=select3(list, buy);
		   }
		  
		}
      catch (Exception e) {
			
			e.printStackTrace();
		}
		return list2;
	}
	/**
	 * 将长时间转化为短时间
	 * @param time
	 * @return
	 */
	public static String getDate2(String time)
	{    
		 String s1="";
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		     //定义Date对象
          Date date;
		    try {

		    date = df.parse(time);   //将字符串类型的日期/时间解析为Date类型
            s1=df2.format(date);
		    } catch (Exception e) {

		    e.printStackTrace();

		    }
		    return s1;
	}
	private Date getDate(String time)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date date = null;      //定义Date对象

		    try {

		    date = df.parse(time);   //将字符串类型的日期/时间解析为Date类型

		    } catch (Exception e) {

		    e.printStackTrace();

		    }
		    return date;
	}
	/**
	 * 按到期时间倒叙。计算出可复选的默认红包组合
	 * @param list
	 * @param buy 购买金额
	 * @return 
	 */
	private  ArrayList<Hongbao> select3(ArrayList<Hongbao> list,String buy)
	{   
		ArrayList<Hongbao> list1=new ArrayList<Hongbao>();
		//组合金额
		String money="0";
		//得到可复选
		ArrayList<Hongbao> list2=select4(list);
		//时间倒叙排序
		//list2=select5(list2);
		Collections.sort(list2);
		JSONObject jo=null;
		double d1=Double.parseDouble(buy);
		try
		{
		for(Hongbao hb2:list)
		{ 
			if(hb2.unLimit.equals("1"))
			{
			jo=new JSONObject(hb2.content);
			String s1=jo.getString("couponAmont");
			money=NumberManager.getAddString(money,s1,2);
			double d2=Double.parseDouble(money);
			if(d2>d1)
			{
				break;
			}
			else
			{
				list1.add(hb2);
			}
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list1;
	}
	/**
	 * 得到没有过期的红包
	 * @param list
	 * @return
	 */
	public static  ArrayList<Hongbao> select6(ArrayList<Hongbao> list)
	{   
		Hongbao hb1=null;
		//可叠加
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		
		for(Hongbao hb2:list)
		{
			if(hb2.isOld.equals("0"))
			{   
				int a=Integer.parseInt(hb2.needCount);
				if(a>0)
				{
				  list2.add(hb2);
				}
			}
		}
		return list2;
	}
	/**
	 * 得到非过期的红包
	 * @param list
	 * @return
	 */
	public static  ArrayList<Hongbao> select8(ArrayList<Hongbao> list)
	{   
		Hongbao hb1=null;
		//可叠加
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		
		for(Hongbao hb2:list)
		{   
			int a=Integer.parseInt(hb2.needCount);
			if(hb2.isOld.equals("0")&&a>0)
			{
				list2.add(hb2);
			}
		}
		return list2;
	}
	
	
	/**
	 * 得到过期的红包
	 * @param list
	 * @return
	 */
	public static  ArrayList<Hongbao> select7(ArrayList<Hongbao> list)
	{   
		Hongbao hb1=null;
		//可叠加
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		
		for(Hongbao hb2:list)
		{   
			int a=Integer.parseInt(hb2.needCount);
			if(hb2.isOld.equals("1")||a<=0)
			{
				list2.add(hb2);
			}
		}
		return list2;
	}
	/**
	 * 选出可复选的红包，并且俺时间到期排序
	 * @param list
	 * @return
	 */
	private  ArrayList<Hongbao> select4(ArrayList<Hongbao> list)
	{   
		Hongbao hb1=null;
		//可叠加
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		
		for(Hongbao hb2:list)
		{
			if(hb2.unLimit.equals("1"))
			{
				list2.add(hb2);
			}
		}
		return list2;
	}
	/**
	 * 俺时间倒叙排序
	 * @param list
	 * @return
	 */
	private  ArrayList<Hongbao> select5(ArrayList<Hongbao> list)
	{   
		Hongbao hb1=null;
		Hongbao[] hbs=new Hongbao[list.size()];
		for(int i=0;i<list.size();i++)
		{
			hbs[i]=list.get(i);
		}
		for(int i=list.size()-1;i>0;i--)
		{   
			Date d1=getDate(hbs[i].endTime);
			for(int j=0;j<i;j++)
			{   
				Date d2=getDate(hbs[j].endTime);
				if(!d1.before(d2))
				{
					hb1=hbs[i];
					hbs[i]=hbs[j];
					hbs[j]=hb1;
				}
			}
		}
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		for(Hongbao hb:hbs)
		{
			list2.add(hb);
		}
		return list2;
	}
	/**
	 * 比较2个字符数字的大小
	 * @return = 0 s1==s2   <0 s1<s2 >0 s1>s2
	 */
	public static int compreS(String s1,String s2)
	{
		double d1=Double.parseDouble(s1);
		double d2=Double.parseDouble(s2);
		if(d1>d2)
		{
			return 1;
		}
		else if(d1==d2)
		{
			return 0;
		}
		else
		{
			return -1;
		}
		
	}
	/**
	 * 选出可用的红包
	 * DingqiP2 dp 为null活期,否则定期
	 * 如果list null返回 一个空的集合
	 * @param list
	 * @return
	 */
	public ArrayList<Hongbao> select(ArrayList<Hongbao> list,DingqiP2 dp,String buy)
	{   
		if(list==null)
		{
			return new ArrayList<Hongbao>();
		}
		//String money=act.getIntent().getStringExtra(BeikBankConstant.INTENT_AMOUNT);
		ArrayList<Hongbao> list2=new ArrayList<Hongbao>();
		//有值时候购买定期,否则活期
		 if(dp!=null)
		 {
			 for(Hongbao hb:list)
			 {  
				 int a=Integer.parseInt(hb.needCount);
				 if(a>0)
				 {	
				 if(hb.usePType.equals("0")||hb.usePType.equals("2"))
				 {
					 if(hb.isOld.equals("0"))
					 {   
						 int i= HongbaoUtil.compreS(HongbaoUtil.getMoney(hb),buy);
							if(i<=0)
							{
						 double d1=Double.parseDouble(buy);
						 double d2=Double.parseDouble(hb.mustMeet);
						 if(d1>=d2)
						 {   
							 if(dp!=null)
							 {
							  String s3=hb.mustMaxDay;
							  String s4=hb.mustMinDay;
							  double d3=Double.parseDouble(s3);
							  double d4=Double.parseDouble(s4);
							  if(d3==0&&d4==0)
							  {
								  list2.add(hb);
								  continue;
							  }
							  double d5=Double.parseDouble(dp.termbondPeriod);
							  if(d5<=d3&&d5>=d4)
							  {
							    list2.add(hb);
							  }
							 }
						 }
						 }
					 }
				 }
				 }
			 }
		 }
		 else
		 {
			 for(Hongbao hb:list)
			 {   
				 int a=Integer.parseInt(hb.needCount);
				 if(a>0)
				 {	 
				  if(hb.usePType.equals("1")||hb.usePType.equals("2"))
				  {
					 if(hb.isOld.equals("0"))
					 {   
						int i= HongbaoUtil.compreS(HongbaoUtil.getMoney(hb),buy);
						if(i<=0)
						{
						 double d1=Double.parseDouble(buy);
						 double d2=Double.parseDouble(hb.mustMeet);
						 if(d1>=d2)
						 {
							 list2.add(hb);
						 }
						}
					 }
				 }
			  }
			 }
		 }
		
		
		
		return list2;
	}
	
}
