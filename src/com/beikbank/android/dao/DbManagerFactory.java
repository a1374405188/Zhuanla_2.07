package com.beikbank.android.dao;

import android.content.Context;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-14
 **/
public class DbManagerFactory {
	public static TableDao  td;
    public static TableDao getTableDao(Context con)
    {   
    	if(td==null)
    	{
    		  td=new TableDaoImpl(con);
    	}
    	return td;
    }
    public static TableDao getTableDao()
    {   
    	if(td==null)
    	{
    		  td=new TableDaoImpl(null);
    	}
    	return td;
    }
}
