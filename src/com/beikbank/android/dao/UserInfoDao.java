package com.beikbank.android.dao;


import com.beikbank.android.data.UserInfo;
import com.beikbank.android.utils.BKDes;


public class UserInfoDao {
   public static UserInfo getUserInfo()
   {   
	   UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//	   if(userInfo!=null&&userInfo.getRealName()!=null&&!"".equals(userInfo.getRealName()))
//	   {
//		   destipy(userInfo);
//	   }
	   return userInfo;
   }
   public static void setUserInfo(UserInfo userInfo)
   {   
//	   if(userInfo!=null&&userInfo.getRealName()!=null&&!"".equals(userInfo.getRealName()))
//	   {
//		   entipy(userInfo);
//	   }
	   TableDaoSimple.insert(userInfo);
   }
//   /**
//    * 加密
//    * @param userInfo
//    */
//   public static void entipy(UserInfo userInfo)
//   {
//	   BKDes bd=new BKDes();
//	   userInfo.setRealName(bd.encrypToBase64(userInfo.getRealName()));
//	   userInfo.setIdNumber(bd.encrypToBase64(userInfo.getRealName()));
//   }
//   /**
//    * 解密
//    * @param userInfo
//    */
//   public static void destipy(UserInfo userInfo)
//   {
//	   BKDes bd=new BKDes();
//	   userInfo.setRealName(bd.decryptBase64(userInfo.getRealName()));
//	   userInfo.setIdNumber(bd.decryptBase64(userInfo.getRealName()));
//   }
//	public static final String TAG = "UserInfoDao";
//	public BeikBankDataBaseHelper mHelper;
//	public static UserInfoDao userInfoDao;
//	public UserInfoDao(Context context){
//		mHelper = BeikBankDataBaseHelper.getInstance(context);
//	}
//
//	public static UserInfoDao getInstance(Context ctx){
//		if(userInfoDao == null){
//			return  new UserInfoDao(ctx);
//		}else{
//			return userInfoDao;
//		}
//	}
//	public synchronized void insertUserInfo(UserInfo userInfo){
//		if(userInfo==null){
//			return;
//		}
//		SQLiteDatabase db = mHelper.getWritableDatabase();
//		db.replace(UserTableMetaData.TABLE_NAME, null, 
//				getContentValues(userInfo));
//		db.close();
//
//	}
//
//
//	public UserInfo getUserInfo(){
//		UserInfo userInfo=null;
////		SQLiteDatabase db = mHelper.getReadableDatabase();
////		Cursor cursor = db.query(UserTableMetaData.TABLE_NAME, null, 
////				null, null, null, null, null);
////		if(cursor != null){
////			if(cursor.getCount() > 0 && cursor.moveToFirst()){
////				userInfo = new UserInfo(cursor);
////			}
////			cursor.close();
////		}
////		db.close();
//		return userInfo;
//	}
//
//	public int deleteAll(){
//		SQLiteDatabase db = mHelper.getWritableDatabase();
//		int count = db.delete(UserTableMetaData.TABLE_NAME, null, null);
//		db.close();
//		return count;
//	}
//
//
//
//	public ContentValues getContentValues(UserInfo userInfo){
//
//		ContentValues cv = new ContentValues();
////		cv.put(UserTableMetaData.USER_ID, userInfo.getId());
////		cv.put(UserTableMetaData.USER_SHUMITOKEN, userInfo.getShumiToken());
////		cv.put(UserTableMetaData.USER_ISBINDCARD, String.valueOf(userInfo.isHasBindCard()));
////		cv.put(UserTableMetaData.USER_AMOUNTLIMIT, userInfo.getUserAmountLimit());
////		cv.put(UserTableMetaData.USER_REALNAME, userInfo.getRealName());
////		cv.put(UserTableMetaData.USER_ISUPGRADE, String.valueOf(userInfo.isHasUpgrade()));
////		cv.put(UserTableMetaData.USER_LOGINTOKEN, userInfo.getLogInToken());
////		cv.put(UserTableMetaData.USER_ISAUTHENTICATE, String.valueOf(userInfo.isHasAuthenticated()));
////		cv.put(UserTableMetaData.USER_ICNUMBER, userInfo.getIdNumber());
////		cv.put(UserTableMetaData.USER_ISSETTRANSACTIONPWD, String.valueOf(userInfo.isHasSetPaypassword()));
//
//		return cv;
//	}

}
