package com.beikbank.android.dao;

import java.io.File;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author yuguohe
 * <p>
 * 1374405188@qq.com
 * </p>
 *2014-12-23
 * <p>
 * 该类用于应用更新时数据处理
 * </p>
 */
public class ApplicationUpdate {
	static String tag="ApplicationUpdate";
	static Context con;
	/**
	 * 监测是否更新过应用，并进行相应的处理
	 */
   public static void doData(Context context)
   {  
	  try
	  {
		  con=context;
	   TableDao td=DbManagerFactory.getTableDao(con);
   	   SQLiteDatabase sdb=td.opean();
   	   int version=sdb.getVersion();
   	   td.close();
   	   if(version!=SystemConfig.DATA_VERSION)
   	   {   
   		  deleteDb();
   		  deletePer(context);
   	   }
	  }
	  catch(Exception e)
	  {  
		  if(SystemConfig.isDebug())
		  {
		    e.printStackTrace();
		  }
		  LogHandler.writeLogFromException(tag, e);
	  }
   }
   public static void deleteDb()
   {       
	       String s1=SystemConfig.DATA_PATH+getAppInfo(con)	+SystemConfig.DATA_file;
	       File file=new File(s1);
		   if(file!=null&&file.exists())
		   {
			   file.delete();
		   }
   }
	public static String getAppInfo(Context con) {
 		try {
 			String pkName = con.getPackageName();
 			String versionName = con.getPackageManager().getPackageInfo(
 					pkName, 0).versionName;
 			int versionCode = con.getPackageManager()
 					.getPackageInfo(pkName, 0).versionCode;
 			return pkName;
 		} catch (Exception e) {
 		}
 		return null;
 	}
   public static void deletePer(Context context)
   {   
//	   SharedPreferences mSharedPreferences = context.getSharedPreferences(SystemConfig.PREF_NAME, Context.MODE_PRIVATE);
//	   Editor edit= mSharedPreferences.edit();
//	   edit.clear();
//	   edit.commit();
	   BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.FUNDINFO_SID,"");
	   BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.PRODUCT_RATE,"");
	   
	   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
	   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,false);
	   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_FORGETTRANSACTIONPWD,false);
	   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_FORGETLOGINPWD,false);
	   
   }
}
