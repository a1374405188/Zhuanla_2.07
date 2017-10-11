package com.beikbank.android.dao;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.UserInfo;

/**
 * 
 * @author Administrator
 * 定期数据操作
 */
public class DingQiDao 
{  
   public static ArrayList<DingqiP2> get()
   {
	   ArrayList<DingqiP2> list=null;
	   list=(ArrayList<DingqiP2>)TableDaoSimple.query(DingqiP2.class,null,null);
	   
	   
	   return list;
	   
   }
   public static boolean set(List<?> list)
   {
	   boolean b=false;
	   TableDaoSimple.insertAll(list);
	   
	   
	   return b;
   }
}
