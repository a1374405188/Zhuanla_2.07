package com.beikbank.android.dao;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import com.beikbank.android.annotation.DBPrimaryKey;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
 
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-14
 **/
public class TableDaoImpl implements TableDao{
   public static SQLiteDatabase sdb;
   Context con;
   public TableDaoImpl(Context con)
   {
	   this.con=con;
   }
	@Override
	public SQLiteDatabase opean() {
		 String s1=SystemConfig.DATA_PATH+ApplicationUpdate.getAppInfo(con)+SystemConfig.DATA_file;
		return opean(s1);
	}

	@Override
	public void close() {
		if(sdb!=null&&sdb.isOpen())
		{
			sdb.close();
		}
	}

	@Override
	public void insert(Object obj) throws Exception {

		String sql=insertSqlFromObj(obj);
			sdb.execSQL(sql);
	}

	@Override
	public void delete(Class<?> cla,String name[],Object value[]) throws Exception {
		String sql=deleteSqlFromObj(cla, name, value);
		sdb.execSQL(sql);

	}

	@Override
	public Object query(Class<?> cla,String name[],Object value[]) throws Exception{
		String sql=selectSqlFromObj(cla, name, value);
		Cursor cursor=sdb.rawQuery(sql, null);
		Field fields[]=cla.getFields();
		Field field=null;
		Class<?> cla2;
		
		ArrayList<Object> list=new ArrayList<Object>();
		Object obj=null;
		while(cursor.moveToNext())
		{   
			obj=cla.newInstance();
			for(int i=0;i<fields.length;i++)
			{
				field=fields[i];
				field.setAccessible(true);
				cla2=field.getType();
				int index=cursor.getColumnIndex(field.getName());
				if(index>-1)
				{
				    if(TableDao.String_t.equals(cla2.getName()))
				    {   
					
					field.set(obj,cursor.getString(index));
				    }
				    else if(TableDao.int_t.equals(cla2.getName()))
				   {
					
					field.set(obj,cursor.getInt(index));
			    	}
			    	else if(TableDao.boolean_t.equals(cla2.getName()))
				   {   
					int b=cursor.getInt(index);
					if(b==1)
					{
						field.set(obj,true);
					}
					else
					{
						field.set(obj,false);
					}
					
			     	}
				}
			}
			list.add(obj);
		}
		return list;
	}

	@Override
	public void update(Object bean,String name[],Object value[]) throws Exception {
		String sql=updateSqlFromObj(bean, name, value);
		sdb.execSQL(sql);
	}

	@Override
	public void createTable(Class<?> cla) {
		String sql=createTableSqlFromObj(cla);
		sdb.execSQL(sql);
	}

	@Override
	public void deleteTable(Class<?> cla) {
		String sql=deleteTableSqlFromObj(cla);
		sdb.execSQL(sql);
	}
	@Override
	public SQLiteDatabase opean(String path) {
		if(sdb==null||!sdb.isOpen())
		{   
			File f=new File(path);
			boolean b=f.exists();
			//sdb=SQLiteDatabase.openOrCreateDatabase(,null);
			sdb=SQLiteDatabase.openOrCreateDatabase(path,null);
			if(!b)
			{
				sdb.setVersion(SystemConfig.DATA_VERSION);
			}
		}
		if(sdb!=null&&sdb.isOpen())
		{
			return sdb;
		}
		else
		{
			return null;
		}
	}
	/**
	 * drop table table1
	 * @param bean
	 * @return
	 */
	private String deleteTableSqlFromObj(Class<?> cla)
	{
		String sql="drop table if exists ";
		sql+=cla.getSimpleName();
		return sql;
	}
	/**
	 * 更具对象创建一张表
	 * @param obj  craate table test1(a text primary key,b text,c boolen,d int)
	 * @return
	 */
	private  String createTableSqlFromObj(Class<?> cla)
	   {  
		
		   String s1="create table if not exists ";
		   String tablename;
		   String text="";
		   tablename=cla.getSimpleName();
		   Field fields[]=cla.getDeclaredFields();
		   Field mfield=null;
		   Class<?> type=null;
		   for(int i=0;i<fields.length;i++)
		   {
			   mfield=fields[i];
			   type=mfield.getType();
			   if(type.getName().equals(TableDao.String_t))
			   {   
				  
				   text+=mfield.getName()+" text";
			   }
			   else if(type.getName().equals(TableDao.boolean_t))
			   {
				   text+=mfield.getName()+" bool";
			   }
			   else if(type.getName().equals(TableDao.int_t))
			   {
				   text+=mfield.getName()+" int";
			   }
			   else
			   {
				   continue;
			   }
			   //是否为主件
				   DBPrimaryKey pk=mfield.getAnnotation(DBPrimaryKey.class);
				   if(pk!=null&&pk.privaryKey()==1)
				   {
					   text+=" primary key";
				   }
			   //是否是最后一个
			   if(i!=fields.length-1)
			   {
				   text+=",";
			   }
			   
		   }
		   String sql=s1+tablename+"("+text+");";
		   return sql;
	   }
	/**
	 * 更具简单对象生产sql
	 * insert into C0(b2) values(1);
	 * @param obj
	 * @return
	 */
	private  String insertSqlFromObj(Object obj) throws Exception

	{
		String sql="insert into ";
		String tableName=obj.getClass().getSimpleName();
		String text="";
		String value="";
		
		Class<?> cla=obj.getClass();
		Field fields[]=cla.getDeclaredFields();
		Field field=null;
        for(int i=0;i<fields.length;i++)
        {   
        	field=fields[i];
        
        	
        	Class<?> cla2=field.getType();
        	if(cla2.getName().equals(TableDao.String_t))
        	{   
        		field.setAccessible(true);
        		String s=(String)field.get(obj);
        		if(s==null)
        		{
        			s="";
        		}
        		value+="'"+s+"'";
        	}
        	else if(cla2.getName().equals(TableDao.int_t))
        	{
        		value+=field.getInt(obj);
        	}
        	else if(cla2.getName().equals(TableDao.boolean_t))
        	{
        		boolean b=field.getBoolean(obj);
        		if(b)
        		{
        			value+=1;
        		}
        		else
        		{
        			value+=0;
        		}
        	}
        	else
        	{
        		continue;
        	}
        	text+=field.getName();
        	if(i!=fields.length-1)
        	{
        		text+=",";
        	}
        	if(i!=fields.length-1)
        	{
        		value+=",";
        	}
        }
        sql+=tableName+"("+text+")  "+"values"+"("+value+")";
		return sql;
	}
	/**
	 * select * from table1 where a=1 and ;
	 * @param obj 可以是Class查询所有    或者具体的object
	 * @return
	 */
	private String selectSqlFromObj(Class<?> cla,String name[], Object value[])
	{
		String sql="select * from  ";

		if(name==null||value==null)
		{
			sql+=cla.getSimpleName();
		}
		else
		{   
			String table_name=cla.getSimpleName();
			sql+=table_name;
			sql+=" where 1=1 ";
			Field fields[]=cla.getDeclaredFields();
            sql=doParam(sql, fields, name, value);
		}
		return sql;
	}
	/**
	 * UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson' 
	 * 得到更新的sql重简单bean
	 * @param bean
	 * @param name
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String updateSqlFromObj(Object bean,String name[], Object value[]) throws Exception
	{
		String sql="update ";
		String tableName="";
		String set=" set ";
		Class<?> cla=bean.getClass();
		tableName=cla.getSimpleName();
		sql+=tableName+set;
		
		Field fields[]=cla.getDeclaredFields();
		Field field=null;
		String pram_name=null;
		String cla_name=null;
		Class<?> cla2=null;
		for(int i=0;i<fields.length;i++)
		{
			field=fields[i];
			field.setAccessible(true);
			cla2=field.getType();
			pram_name=field.getName();
			cla_name=cla2.getName();
			
			if(TableDao.int_t.equals(cla_name))
			{
				sql+=field.getInt(bean);
			}
			else if(TableDao.boolean_t.equals(cla_name))
			{   
				boolean b=field.getBoolean(bean);
				if(b)
				{
					sql+="1";
				}
				else
				{
					sql+="0";
				}
			}
			else if(TableDao.String_t.equals(cla_name))
			{   
				Object obj=field.get(bean);
				String s="";
				if(obj!=null)
				{
					s=(String) obj;
				}
				sql+="'"+s+"'";
			}
			else
			{
				continue;
			}
			sql+=pram_name+"=";
			if(i!=fields.length-1)
			{
				sql+=",";
			}
		}
		sql+=" where 1=1 ";
		sql=doParam(sql, fields, name, value);
		return sql;
	}
	/**
	 * delete from table1 where a="1";
	 * 得到删除sql重对象
	 * @param bean
	 * @param name
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String deleteSqlFromObj(Class<?> cla,String name[], Object value[])throws Exception
	{
		String sql="delete from ";
		String tableName="";
		String s1=" where 1=1 ";
		tableName=cla.getSimpleName();
		sql+=tableName+s1;
		
		
		Field fields[]=cla.getDeclaredFields();
		sql=doParam(sql, fields, name, value);
		return sql;
	}
	/**
	 * 将参数添加到解析成sql
	 * @param sql
	 * @param fields
	 * @param name
	 * @param value
	 * @return
	 */
	private String doParam(String sql,Field fields[],String name[], Object value[])
	{   
		if(name==null||value==null)
		{
			return sql;
		}
		Class cla=null;
		Field field=null;
		for(int i=0;i<fields.length;i++)
		{
			field=fields[i];
			field.setAccessible(true);
			for(int j=0;j<name.length;j++)
			{   
				cla=field.getType();
				if(field.getName().equals(name[j]))
				{   
					if(cla.getName().equals(TableDao.int_t))
					{
						sql+=" and "+field.getName()+"="+value[j];
					}
					if(cla.getName().equals(TableDao.boolean_t))
					{   
						if((Boolean)value[j]==true)
						{
							sql+=" and "+field.getName()+"="+1;
						}
						else
						{
							sql+=" and "+field.getName()+"="+0;
						}
					}
					if(cla.getName().equals(TableDao.String_t))
					{
						sql+=" and "+field.getName()+"="+"'"+value[j]+"'";
					}
				}
			}
		}
		return sql;
	}


}
