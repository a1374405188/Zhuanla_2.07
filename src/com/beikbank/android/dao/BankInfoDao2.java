package com.beikbank.android.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.beikbank.android.data.BankInfo;
import com.beikbank.android.db.BankTableMetaData;
import com.beikbank.android.db.BeikBankDataBaseHelper;

public class BankInfoDao2 {

	public static final String TAG = "BankInfoDao";
	//public BeikBankDataBaseHelper mHelper;
	public static BankInfoDao2 bankInfoDao;
	public TableDao td;
	public BankInfoDao2(Context context){
		//mHelper = BeikBankDataBaseHelper.getInstance(context);
		td=DbManagerFactory.getTableDao();
		SQLiteDatabase db =td.opean();
		db.execSQL(BankTableMetaData.CREATE_TABLE_SQL);
		td.close();
	}

	public static BankInfoDao2 getInstance(Context ctx){
		if(bankInfoDao == null){
			return  new BankInfoDao2(ctx);
		}else{
			return bankInfoDao;
		}
	}
	public synchronized void insertBankInfoList(ArrayList<BankInfo> bankInfoList){
		if(bankInfoList.size()==0){
			return;
		}
		//SQLiteDatabase db = mHelper.getWritableDatabase();
		SQLiteDatabase db =td.opean();
		for(BankInfo bankInfo:bankInfoList){
			db.replace(BankTableMetaData.TABLE_NAME, null, 
					getContentValues(bankInfo));
		}
		db.close();

	}


	public ArrayList<BankInfo> getBankInfoList(){
		ArrayList<BankInfo> list=new ArrayList<BankInfo>();
		//SQLiteDatabase db = mHelper.getReadableDatabase();
		SQLiteDatabase db =td.opean();
		Cursor cursor = db.query(BankTableMetaData.TABLE_NAME, null, 
				null, null, null, null, BankTableMetaData.BANK_DISORDER+" asc");
		if(cursor != null){
			while(cursor.moveToNext()) {
				list.add(new BankInfo(cursor));

			}
			cursor.close();
		}
		db.close();
		return list;
	}
	
	public ArrayList<String> getBankNameList(){
		ArrayList<String> list=new ArrayList<String>();
		//SQLiteDatabase db = mHelper.getReadableDatabase();
		SQLiteDatabase db =td.opean();
		Cursor cursor = db.query(BankTableMetaData.TABLE_NAME, new String[]{BankTableMetaData.BANK_NAME}, 
				null, null, null, null, BankTableMetaData.BANK_DISORDER+" asc");
		if(cursor != null){
			while(cursor.moveToNext()) {
				list.add(cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_NAME)));

			}
			cursor.close();
		}
		db.close();
		return list;
	}
	
	public BankInfo getBankInfoByType(String type){
		BankInfo bankInfo=null;
		//SQLiteDatabase db = mHelper.getReadableDatabase();
		SQLiteDatabase db =td.opean();
		Cursor cursor = db.query(BankTableMetaData.TABLE_NAME, null, 
				BankTableMetaData.BANK_TYPE+" =?", new String[]{type}, null, null, null);
		if(cursor != null){
			if(cursor.getCount() > 0 && cursor.moveToFirst()){
				bankInfo = new BankInfo(cursor);
			}
			cursor.close();
		}
		db.close();
		return bankInfo;
	}
	
	public BankInfo getBankInfoByName(String name){
		BankInfo bankInfo=null;
		//SQLiteDatabase db = mHelper.getReadableDatabase();
		SQLiteDatabase db =td.opean();
		Cursor cursor = db.query(BankTableMetaData.TABLE_NAME, null, 
				BankTableMetaData.BANK_NAME+" like ?", new String[]{name}, null, null, null);
		if(cursor != null){
			if(cursor.getCount() > 0 && cursor.moveToFirst()){
				bankInfo = new BankInfo(cursor);
			}
			cursor.close();
		}
		db.close();
		return bankInfo;
	}

	public int deleteAll(){
		//SQLiteDatabase db = mHelper.getWritableDatabase();
		SQLiteDatabase db =td.opean();
		int count = db.delete(BankTableMetaData.TABLE_NAME, null, null);
		db.close();
		return count;
	}



	public ContentValues getContentValues(BankInfo bindBank){

		ContentValues cv = new ContentValues();
		cv.put(BankTableMetaData.BANK_DISORDER, Integer.parseInt(bindBank.getDisorder()));
		cv.put(BankTableMetaData.BANK_ICONURL, bindBank.getIconUrl());
		cv.put(BankTableMetaData.BANK_LOGOURL, bindBank.getLogoUrl());
		cv.put(BankTableMetaData.BANK_SINGLELIMIT, bindBank.getSingleLimit());
		cv.put(BankTableMetaData.BANK_CUMULATIVELIMIT, bindBank.getCumulativeLimit());
		cv.put(BankTableMetaData.BANK_TYPE, bindBank.getType());
		cv.put(BankTableMetaData.BANK_NAME, bindBank.getBankName());

		return cv;
	}

}
