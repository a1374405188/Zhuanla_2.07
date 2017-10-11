package com.beikbank.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPref{


	private SharedPreferences mSharedPreferences = null;
	private Editor mEditor = null;
	private static SharedPref sharePref = null;


	/**
	 * register in Application
	 * @method getInstance
	 * @param PREF_NAME
	 * @param ctx
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	public synchronized static SharedPref getInstance(String PREF_NAME , Context ctx){
		if(sharePref != null){
			return sharePref;
		}else{
			return new SharedPref(PREF_NAME , ctx);
		}

	}

	public SharedPref(String PREF_NAME, Context ctx){
		mSharedPreferences = ctx.getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}



	public String getSharePrefString(String key){
		return mSharedPreferences.getString(key, ""); 
	}



	public boolean getSharePrefBoolean(String key , boolean defValue){
		return mSharedPreferences.getBoolean(key, defValue); 
	}



	public int getSharePrefInteger(String key){
		return mSharedPreferences.getInt(key, -1);
	}



	public long getSharePrefLong(String key){
		return mSharedPreferences.getLong(key, -1); 
	}

	public long getSharePrefLong(String key,int value){
		return mSharedPreferences.getLong(key, -1); 
	}

	public float getSharePrefFloat(String key){
		return mSharedPreferences.getFloat(key, -1);
	}


	public boolean putSharePrefString(String key , String value){
		mEditor.putString(key, value);
		return mEditor.commit();
	}

	public boolean putSharePrefBoolean(String key , boolean value){
		mEditor.putBoolean(key, value);
		return mEditor.commit();
	}

	public boolean putSharePrefFloat(String key , float value){
		mEditor.putFloat(key, value);
		return mEditor.commit();
	}

	public boolean putSharePrefLong(String key , long value){
		mEditor.putLong(key, value);
		return mEditor.commit();
	}

	public boolean putSharePrefInteger(String key , int value){
		mEditor.putInt(key, value);
		return mEditor.commit();
	}

	public boolean removeKey(String key){
		mEditor.remove(key);
		return mEditor.commit();
	}


	public boolean clear(){
		mEditor.clear();
		return mEditor.commit();
	}
}

