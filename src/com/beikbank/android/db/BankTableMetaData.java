package com.beikbank.android.db;

/**
 * @ClassName: BankTableMetaData
 * @Description: 银行
 * @author zhongmin
 * @date 
 *
 */
public class BankTableMetaData {

    public static final String TABLE_NAME = "bank_info";
    
    public static final String BANK_DISORDER = "BANK_DISORDER";
    public static final String BANK_CUMULATIVELIMIT = "BANK_CUMULATIVELIMIT";
    public static final String BANK_SINGLELIMIT = "BANK_SINGLELIMIT";
    public static final String BANK_ICONURL = "BANK_ICONURL";
    public static final String BANK_LOGOURL = "BANK_LOGOURL";
    public static final String BANK_NAME = "BANK_NAME";
    public static final String BANK_TYPE= "BANK_TYPE";
    

    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + "(" +
            BANK_TYPE + " TEXT PRIMARY KEY," +
            BANK_DISORDER + " INTEGER," +
            BANK_NAME + " TEXT," +
            BANK_LOGOURL + " TEXT," +
            BANK_ICONURL + " TEXT," +
            BANK_SINGLELIMIT + " TEXT," +
            BANK_CUMULATIVELIMIT + " TEXT);";


    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS  " + TABLE_NAME;
    public static final String DELETE_TABLE_SQL = "delete from  " + TABLE_NAME;


}
