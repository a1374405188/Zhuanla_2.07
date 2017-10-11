package com.beikbank.android.db;

/**
 * @ClassName: CardTableMetaData
 * @author zhongmin
 * @date 
 *
 */
public class CardTableMetaData {

    public static final String TABLE_NAME = "card_info";
    
    public static final String CARD_TYPE= "CARD_TYPE";
    public static final String CARD_CARDNUMBER = "CARD_CARDNUMBER";
    public static final String CARD_SID="CARD_SID";
    public static final String CARD_BANKNAME="CARD_BANKNAME";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + "(" +
            CARD_CARDNUMBER + " TEXT PRIMARY KEY," +
            CARD_TYPE + " TEXT,"+
            CARD_SID+" TEXT,"+
            CARD_BANKNAME+" TEXT"+
            ");";


    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS  " + TABLE_NAME;
    public static final String DELETE_TABLE_SQL = "delete from  " + TABLE_NAME;


}
