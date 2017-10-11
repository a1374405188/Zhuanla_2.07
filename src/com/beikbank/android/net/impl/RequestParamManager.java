package com.beikbank.android.net.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.beikbank.android.annotation.ParamAnnotation;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 **/
public class RequestParamManager {
	/**
	 * 将对象解析成treeMap参数 不考虑顺序
	 * @param obj
	 * @return   if obj is null return map
	 * @throws Exception
	 */
	public static TreeMap<String,Object> getMapString2(Object obj)throws Exception
	{   
		TreeMap<String,Object> map=new TreeMap<String,Object>();
		if(obj==null)
		{
			return map;
		}
		
		Class cparam=obj.getClass();
		Field pfield[]=cparam.getDeclaredFields();
		Field mfield=null;
		Object mparam=null;
		
		for(int i=0;i<pfield.length;i++)
		{
			
			mfield=pfield[i];
			mfield.setAccessible(true);
			Object obj1=mfield.get(obj);
			Class<?> cla=mfield.getType();
			if(obj1==null)
			{
				 map.put(mfield.getName(),"");
			}
			else
			{ 
				
		               mparam=mfield.get(obj);
		               if(mparam!=null&&!"".equals(mparam))
		                {
		                map.put(mfield.getName(),mparam);
		               }
				  
			}
		}
		return map;
	}
	
	
	
	/**
	 * 将对象解析成treeMap参数
	 * @param obj
	 * @return   if obj is null return map
	 * @throws Exception
	 */
	public TreeMap<String,String> getMapString(Object obj)throws Exception
	{   
		TreeMap<String,String> map=new TreeMap<String, String>();
		if(obj==null)
		{
			return map;
		}
		
		Class cparam=obj.getClass();
		Field pfield[]=cparam.getDeclaredFields();
		Field mfield=null;
		String mparam=null;
		// Field count
		int fcount=0;
		int index=0;
		while(true)
		{   
			
			for(int i=0;i<pfield.length;i++)
			{
				mfield=pfield[i];
				mfield.setAccessible(true);
				ParamAnnotation p=mfield.getAnnotation(ParamAnnotation.class);
				if(p.index()==index)
				{   
					index++;
					break;
				}
				
			}
			Object obj1=mfield.get(obj);
			if(obj1!=null&&!obj1.equals(""))
			{
		      mparam=obj1.toString();
		      if(mparam!=null&&!"".equals(mparam))
		      {
		       map.put(mfield.getName(),mparam);
			
		      }
			}
		    //字段计数加一
			if(fcount==pfield.length)
			{
				break;
			}
			else
			{
				fcount++;
			}
		}
		
		return map;
	}
}
