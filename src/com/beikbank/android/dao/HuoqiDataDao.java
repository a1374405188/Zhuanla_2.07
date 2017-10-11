package com.beikbank.android.dao;

import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.UserInfo;

/**
 * 
 * @author Administrator
 *活期数据库操着
 */
public class HuoqiDataDao 
{ 
  /**
   * 查不到的时候返回null
   * @return
   */
  public static FundInfo get()
  {  
	  FundInfo fi=null;
	  fi=(FundInfo) TableDaoSimple.queryone(FundInfo.class,null,null);
	 
	  return fi;
  }
  /**
   * 
   */
  public static boolean set(FundInfo fi)
  {   
	  if(fi==null)
	  {
		  return false;
	  }
	  TableDaoSimple.deleteTable(fi.getClass());
	  TableDaoSimple.createTable(fi.getClass());
	  int a=TableDaoSimple.insert(fi);
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
