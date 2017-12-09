package com.beikbank.android.net.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.beikbank.android.annotation.BaseParamAnnotation;
import com.beikbank.android.annotation.ParamAnnotation;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-17
 *<p>
 *   该类用于将json解析成java对象
 *</p>
 **/
public class ResponseParamManager {
	  /**
	   * 解析错误的json
	   * @param mclass
	   * @param json
	   * @return
	   * @throws Exception
	   */
      public Object getObjectError(Class<?> mclass,String json)throws Exception
      {
    	  JSONObject jo=new JSONObject(json);
		  Object obj=mclass.newInstance();
		  Field[] mfields=mclass.getDeclaredFields();
		  Field mfield=null;
		  for(int i=0;i<mfields.length;i++)
		  {
			   mfield=mfields[i];
			   mfield.setAccessible(true);
			   Class<?> cla=mfield.getType();
//			   if(cla.getName().equals("java.util.ArrayList"))
//			   {   
//				   Object data_json=jo.getString(mfield.getName());
//				   if(!data_json.equals("null")&&!"".equals(data_json))
//				   { 
//					 
//				     JSONArray ja=jo.getJSONArray(mfield.getName());
//				     Object obj2=getObjectFromJson2(mfield,ja.toString());
//				     mfield.set(obj, obj2);
//				   }
//			   }
			   if(isBaseType(cla.getName()))
			   {
				   
				   setFieldValue(obj, mfield, jo);
			   }
//			   else
//			   {   
//				   Object data_json=jo.getString(mfield.getName());
//				   if(!data_json.equals("null")&&!"".equals(data_json))
//				   {
//				     JSONObject jo2=jo.getJSONObject(mfield.getName());
//				     Object obj2=getObjectFromJson(mfield.getType(), jo2.toString().trim());
//				     mfield.set(obj, obj2);
//				   }
//			   }
		  }
		  return obj;
      }
	  /**
	   *  解析1级方法
	   * @param mclass
	   * @param json
	   * @return
	   * @throws Exception
	   */
	  public Object getObjectFromJson(Class<?> mclass,String json)throws Exception
	  {   
		  JSONObject jo=new JSONObject(json);
		  Object obj=mclass.newInstance();
		  Field[] mfields=mclass.getDeclaredFields();
		  Field mfield=null;
		  for(int i=0;i<mfields.length;i++)
		  {
			   mfield=mfields[i];
			   mfield.setAccessible(true);
			   Class<?> cla=mfield.getType();
			   if(cla.getName().equals("java.util.ArrayList"))
			   {   
				   Object data_json=jo.getString(mfield.getName());
				   if(!data_json.equals("null")&&!"".equals(data_json))
				   { 
					 
				     JSONArray ja=jo.getJSONArray(mfield.getName());
				     Object obj2=getObjectFromJson2(mfield,ja.toString());
				     mfield.set(obj, obj2);
				   }
			   }
			   else if(isBaseType(cla.getName()))
			   {
				   
				   setFieldValue(obj, mfield, jo);
			   }
			   else
			   {   
				   if(mfield.isSynthetic())
				   {
					   continue;
				   }
				   if (mfield.getName().equals("serialVersionUID")) {
					   continue;
				   }
				   Object data_json=jo.getString(mfield.getName());
				   if(!data_json.equals("null")&&!"".equals(data_json))
				   {
				     JSONObject jo2=jo.getJSONObject(mfield.getName());
				     Object obj2=getObjectFromJson(mfield.getType(), jo2.toString().trim());
				     mfield.set(obj, obj2);
				   }
			   }
		  }
		  return obj;
	  }
	  /**
	   * 解析2级方法
	   * @param mfield
	   * @param json
	   * @return
	   * @throws Exception
	   */
	  public Object getObjectFromJson2(Field mfield,String json)throws Exception
	  {  
		  JSONArray ja=new JSONArray(json);
		 
		  Object obj=null;
		  Type type=mfield.getGenericType();
		  ParameterizedType pt=(ParameterizedType) type;
		  //类型
		  Class<?> param=(Class<?>) pt.getRawType();
		  //泛型
		  Class<?> param1=(Class<?>) pt.getActualTypeArguments()[0];
		  obj=param.newInstance();
		  
			  
			 
			
		  
	        for(int i=0;i<ja.length();i++)
			  {   
				  if(isBaseType(param1.getName()))
				  {
					  ((List) obj).add(ja.getString(i).toString());
				  }
				  else
				  {
				      ((List) obj).add(getObjectFromJson(param1,ja.getJSONObject(i).toString()));
				  }
			  
			  
		    }
		  
		 
		  return obj;
	  }
	 private boolean isBaseType(String type)
	 {
		 boolean b=false;
		 if("int".equals(type))
		 {
			 b=true;
		 }
		 else if("boolean".equals(type))
		 {
			 b=true;
		 }
		 else if("long".equals(type))
		 {
			 b=true;
		 } 
		 else if("double".equals(type))
		 {
			 b=true;
		 }
		 else if("java.lang.String".equals(type))
		 {
			 b=true;
		 }
		 
		 
		 return b;
	 }
	 private void setFieldValue(Object obj,Field mfield,JSONObject jo) throws Exception
	 {
		 Class<?> t=mfield.getType();
		 boolean has=jo.has(mfield.getName());
		 if(!has)
		 {
			 return;
		 }
		 
		 if("int".equals(t.getName()))
		 {
			 mfield.set(obj,jo.getInt(mfield.getName()));
		 }
		 else if("boolean".equals(t.getName()))
		 {
			 mfield.set(obj,jo.getBoolean(mfield.getName()));
		 }
		 else if("long".equals(t.getName()))
		 {
			 mfield.set(obj,jo.getLong(mfield.getName()));
		 }
		 else if("double".equals(t.getName()))
		 {
			 mfield.set(obj,jo.getDouble(mfield.getName()));
		 }
		 else if("java.lang.String".equals(t.getName()))
		 {
			 mfield.set(obj,jo.getString(mfield.getName()));
		 }
	 }
}
