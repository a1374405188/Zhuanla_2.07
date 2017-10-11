package com.beikbank.android.dao;

import java.util.ArrayList;

import com.beikbank.android.data.BankList;
import com.beikbank.android.exception.LogHandler;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-7
 * 
 */
public class BankListDao {
	static String tag="BankListDao";
	  public static ArrayList<String> getBnakNameList()
	  {  
		  ArrayList<String> nlist=null;
		 try {
		    nlist=new ArrayList<String>();
			ArrayList<BankList> list=(ArrayList<BankList>) TableDaoSimple.query(BankList.class,null,null);
			for(int i=0;i<list.size();i++)
			{
				nlist.add(list.get(i).bankName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogHandler.writeLogFromException(tag, e);
		}
		 return nlist;
	  }
	  public static ArrayList<BankList> getBankList()
	  {
		  ArrayList<BankList> list=null;
			 try {
				list=(ArrayList<BankList>) TableDaoSimple.query(BankList.class,null,null);

			} catch (Exception e) {
				e.printStackTrace();
				LogHandler.writeLogFromException(tag, e);
			}
			 return list;
	  }
	  public static BankList getBankByName(String name)
	  {
		  BankList bl=null;
		  try {
			    String[] n={"bankName"};
			    Object obj[]={name};
				bl=(BankList) TableDaoSimple.queryone(BankList.class,n,obj);
			} catch (Exception e) {
				e.printStackTrace();
				LogHandler.writeLogFromException(tag, e);
			}
		  return bl;
	  }
	  public static BankList getBankByType(String type)
	  {
		  BankList bl=null;
		  try {
			    String[] n={"type"};
			    Object obj[]={type};
				bl=(BankList) TableDaoSimple.queryone(BankList.class,n,obj);
			} catch (Exception e) {
				e.printStackTrace();
				LogHandler.writeLogFromException(tag, e);
			}
		  return bl;
	  }
}
