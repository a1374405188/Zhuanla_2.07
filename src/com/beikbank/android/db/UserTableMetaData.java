package com.beikbank.android.db;

/**
 * @ClassName: UserTableMetaData
 * @Description: 
 * @author zhongmin
 * @date 
 *
 */
public class UserTableMetaData {

    public static final String TABLE_NAME = "user_info";
    
    public static final String USER_ID = "USER_ID";
    public static final String USER_SHUMITOKEN = "USER_SHUMITOKEN";
    public static final String USER_ISBINDCARD= "USER_ISBINDCARD";
    public static final String USER_AMOUNTLIMIT = "USER_AMOUNTLIMIT";
    public static final String USER_REALNAME= "USER_REALNAME";
    public static final String USER_ISUPGRADE= "USER_ISUPGRADE";
    public static final String USER_LOGINTOKEN= "USER_LOGINTOKEN";
    public static final String USER_ISAUTHENTICATE= "USER_ISAUTHENTICATE";
    public static final String USER_ISSETTRANSACTIONPWD= "USER_ISSETTRANSACTIONPWD";
    public static final String USER_ICNUMBER = "USER_ICNUMBER";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + "(" +
            USER_ID + " TEXT PRIMARY KEY," +
            USER_SHUMITOKEN + " TEXT," +
            USER_ISBINDCARD + " TEXT," +
            USER_AMOUNTLIMIT + " TEXT," +
            USER_REALNAME + " TEXT," +
            USER_ISUPGRADE + " TEXT," +
            USER_LOGINTOKEN + " TEXT," +
            USER_ISAUTHENTICATE + " TEXT," +
            USER_ISSETTRANSACTIONPWD + " TEXT," +
            USER_ICNUMBER + " TEXT);";


    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS  " + TABLE_NAME;
    public static final String DELETE_TABLE_SQL = "delete from  " + TABLE_NAME;


}
