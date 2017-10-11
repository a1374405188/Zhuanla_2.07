package com.beikbank.android.dao;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.TransactionInfo;

public class JiaoYiDao 
{
	public static ArrayList<TransactionInfo> get()
	   {
		   ArrayList<TransactionInfo> list=null;
		   list=(ArrayList<TransactionInfo>)TableDaoSimple.query(TransactionInfo.class,null,null);
		   return list;
		   
	   }
	   public static boolean set(List<?> list)
	   {
		   boolean b=false;
		   TableDaoSimple.insertAll(list);
		   return b;
	   }
}
