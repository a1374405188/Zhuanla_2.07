package com.beikbank.android.dao;

import java.io.File;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-14
 *<p>
 *  操作数据库的接口
 *</p>
 *<p>
 *  use demo:   
 *     TableDao td=DbManagerFactory.getTableDao();
 *     td.opean();
 *        操作
 *     
 *     td.close();
 *     
 *</p>
 **/
public  interface TableDao {
  String int_t="int";
  String boolean_t="boolean";
  String String_t="java.lang.String";
 
  /**
   * 打开默认数据库
   * @return  SQLiteDatabase 或者 null
   */
  SQLiteDatabase opean();
  /**
   * 打开指定数据库
   * @param da_file
   * @return  SQLiteDatabase 或者 null
   */
  SQLiteDatabase opean(String da_file);
  /**
   * 关闭数据库
   */
  void close();
  /**
   * 根据对象插入数据库
   * @param obj
   * @throws Exception
   */
  void insert(Object obj) throws Exception;
  /**
   * 根据类型删除记录
   * @param cla 类型
   * @param name 条件名字
   * @param value 条件值
   * @throws Exception
   */
  void delete(Class<?> cla,String name[],Object value[]) throws Exception;
  /**
   * 根据类型查询记录记录
   * @param cla 类型
   * @param name 条件名字
   * @param value 条件值
   * @return ArrayList<Object>
   */
  Object query(Class<?> cla,String name[],Object value[])throws Exception;
  /**
   * 根据类型删除记录
   * @param bean 简单对象
   * @param name 条件名字
   * @param value 条件值
   * @throws Exception
   */
  void update(Object bean,String name[],Object value[])throws Exception;
  /**
   * 根据类型创建表 
   * @param cla 该类型必须为简单的bean 并且要用 DBPrimaryKey 注解标识主键
   * @see DBPrimaryKey
   */
  void createTable(Class<?> cla);
  /**
   * 根据类型删除表
   * @param cla 该类型必须为简单的bean
   */
  void deleteTable(Class<?> cla);
  
}
                                                               