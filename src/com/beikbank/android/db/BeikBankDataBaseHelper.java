package com.beikbank.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BeikBankDataBaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "BeikBankDataBaseHelper";
	private static final String DATABASE_NAME = "beik.db";
	private static BeikBankDataBaseHelper mHelper;
	private Context context;
	public static final int DATABASE_VERSION = 1;

	public synchronized static BeikBankDataBaseHelper getInstance(Context ctx) {
		if (mHelper == null) {
			return new BeikBankDataBaseHelper(ctx);
		} else {
			return mHelper;
		}
	}

	public BeikBankDataBaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;

	}

	public BeikBankDataBaseHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = ctx;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dropAllTable(db);
//		db.execSQL(RecommendTableMetaData.CREATE_TABLE_SQL);
		db.execSQL(UserTableMetaData.CREATE_TABLE_SQL);
		db.execSQL(BankTableMetaData.CREATE_TABLE_SQL);
		db.execSQL(CardTableMetaData.CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	private static void dropAllTable(SQLiteDatabase db) {

//		db.execSQL(RecommendTableMetaData.DROP_TABLE_SQL);
		db.execSQL(UserTableMetaData.DROP_TABLE_SQL);
		db.execSQL(BankTableMetaData.DROP_TABLE_SQL);
		db.execSQL(CardTableMetaData.DROP_TABLE_SQL);

	}

}
