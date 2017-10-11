package com.beikbank.android.dao;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-14
 **/
public class DbManagerFactory {
	public static TableDao  td;
    public static TableDao getTableDao()
    {   
    	if(td==null)
    	{
    		  td=new TableDaoImpl();
    	}
    	return td;
    }
}
