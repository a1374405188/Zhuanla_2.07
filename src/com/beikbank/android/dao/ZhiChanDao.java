package com.beikbank.android.dao;

import java.util.ArrayList;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.UserCapital2;

public class ZhiChanDao {
	 public static UserCapital2 get()
	   {   
		   UserCapital2 uc=null;
		  
		   uc=(UserCapital2)TableDaoSimple.queryone(UserCapital2.class,null,null);
		   
		   
		   return uc;
		   
	   }
	 
	 public static boolean set(Object obj)
	 {
		 
		  TableDaoSimple.deleteTable(obj.getClass());
		  TableDaoSimple.createTable(obj.getClass());
		  int a=TableDaoSimple.insert(obj);
		  if(a>=0)
		  {
			  return true;
		  }
		  else
		  {
			  return false;
		  }
		 
		 
		 
	 }
}
