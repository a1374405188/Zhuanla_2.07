package com.beikbank.android.dao;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.data.BaseData;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.impl.ThreadManagerSet;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-19
 *<p>
 *打开默认数据库
 *该类是对数据库操作接口的简单封装
 * 不涉及事务操作
 *</p>
 **/
public class TableDaoSimple {
	static String TAG="TableDaoSimple";
   private static  TableDao td=null;
   static
   {   
	   synchronized (ThreadManagerSet.syn_obj) {
		   td=DbManagerFactory.getTableDao(null);
		   td.opean();
		   td.createTable(UserInfo.class);
		   td.createTable(CardInfo.class);
		   td.createTable(FundInfo.class);
		   td.createTable(DingqiP2.class);
		   td.close();
		
	}
   }
   /**
    * 更具class创建一张表，如果表以存在，不进行任何处理
    * @param cla
    */
   public static  void createTable(Class<?> cla)
   {   
	   synchronized (ThreadManagerSet.syn_obj) {
		   if(td==null)
		   {
		       td=DbManagerFactory.getTableDao();
		   }
		   td.opean();
		   td.createTable(cla);
		   td.close();
		
	}
   }
   public static void deleteTable(Class<?> cla)
   {   
	   synchronized (ThreadManagerSet.syn_obj) {
		   if(td==null)
		   {
		       td=DbManagerFactory.getTableDao();
		   }
		   td.opean();
		   td.deleteTable(cla);
		   td.close();
	}
   }
   public static  int insert(Object obj) 
   {  
	   synchronized (ThreadManagerSet.syn_obj) {
		   int a=-1;
		   try
		   {
			   if(td==null)
			   {
			       td=DbManagerFactory.getTableDao();
			   }
		   td.opean();
		   td.insert(obj);
		   td.close();
		   a=0;
		   Log.d(TAG,"db insert success");
		   }catch(Exception e)
		   {
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG,e);
		   }
		   return a;
	   }

   }
   public static  void update(Object bean,String name[],Object value[])
   {   synchronized (ThreadManagerSet.syn_obj) {
	   try
	   {
	     if(td==null)
	     {
	       td=DbManagerFactory.getTableDao();
	    }
	    td.opean();
	    td.update(bean, name, value);
	    td.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   LogHandler.writeLogFromException(TAG,e);
	   }
    }
   }
   public static  Object query(Class<?> cla,String name[],Object value[])
   {  
	   synchronized (ThreadManagerSet.syn_obj) {
		   Object obj=null;
		   try{
	   if(td==null)
	   {
	       td=DbManagerFactory.getTableDao();
	   }
	      td.opean();
	      obj = td.query(cla, name, value);
	      ArrayList<Object> list=(ArrayList<Object>) obj;
	      for(Object obj1:list)
	      {
	    	  if(obj1 instanceof BaseData)
			   {
				   BaseData bd=(BaseData) obj1;
				   bd.huancun=true;
			   }
	    	  
	      }
	      td.close();
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG,e);
		   }
	   return obj;
	   }
   }
   public static  Object queryone(Class<?> cla,String name[],Object value[])
   {   
	   synchronized (ThreadManagerSet.syn_obj) {
	   Object obj=null;
	   try{
		   
	   
	   ArrayList list=(ArrayList) query(cla, name, value);
	   if(list!=null&&list.size()>0)
	   {
		   obj=list.get(0);
		   if(obj instanceof BaseData)
		   {
			   BaseData bd=(BaseData) obj;
			   bd.huancun=true;
		   }
	   }
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   LogHandler.writeLogFromException(TAG,e);
	   }
	   return obj;
	   }
   }
   public static  int delete(Class<?> cla,String name[],Object value[]) 
   { 
	   synchronized (ThreadManagerSet.syn_obj) {
		   int a=-1;
	   try{
		   if(td==null)
		   {
		       td=DbManagerFactory.getTableDao();
		   }
	   td.opean();
	   td.delete(cla, name, value);
	   td.close();
	   a=0;
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   LogHandler.writeLogFromException(TAG,e);
	   }
	   return a;
   }
   }
   
   /**
    * 将表中的数据删除，并且集合中的数据插入数据库
    * @param list
    */
   public static  int insertAll(List<?> list)
   {
	   synchronized (ThreadManagerSet.syn_obj) {
		   int a=-1;
		   try
		   {
			   if(td==null)
			   {
			       td=DbManagerFactory.getTableDao();
			   }
		   Object obj1=list.get(0);
		   td.opean();
		   td.deleteTable(obj1.getClass());
		   td.createTable(obj1.getClass());
		   for(Object obj:list)
		   {
			   td.insert(obj);
			   
		   }
		   td.close();
		   a=0;
		   }catch(Exception e)
		   {
			   e.printStackTrace();
			   LogHandler.writeLogFromException(TAG,e);
		   }
		   return a;
	   }
   }
   
}
