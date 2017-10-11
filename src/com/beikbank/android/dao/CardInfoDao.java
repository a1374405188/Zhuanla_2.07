package com.beikbank.android.dao;

import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.utils.BKDes;



public class CardInfoDao {
   
	 public static CardInfo getCardInfo()
	   {   
		   CardInfo ci=(CardInfo)TableDaoSimple.queryone(CardInfo.class,null,null);
		   if(ci!=null&&ci.cardNumber!=null&&!"".equals(ci.cardNumber))
		   {
			   destipy(ci);
		   }
		   return ci;
	   }
	   public static int setCardInfo(CardInfo ci)
	   {   
		   if(ci.cardNumber!=null&&!"".equals(ci.cardNumber))
		   {
			   entipy(ci);
		   }
		  return  TableDaoSimple.insert(ci);
	   }
	   /**
	    * 加密
	    * @param userInfo
	    */
	   public static void entipy(CardInfo ci)
	   {
		   BKDes bd=new BKDes();
		   ci.cardNumber=bd.encrypToBase64(ci.cardNumber);
	   }
	   /**
	    * 解密
	    * @param userInfo
	    */
	   public static void destipy(CardInfo ci)
	   {
		   BKDes bd=new BKDes();
		   ci.cardNumber=bd.decryptBase64(ci.cardNumber);
	   }
//	public static final String TAG = "CardInfoDao";
//	public BeikBankDataBaseHelper mHelper;
//	public static CardInfoDao cardInfoDao;
//	public CardInfoDao(Context context){
//		mHelper = BeikBankDataBaseHelper.getInstance(context);
//	}
//
//	public static CardInfoDao getInstance(Context ctx){
//		if(cardInfoDao == null){
//			return  new CardInfoDao(ctx);
//		}else{
//			return cardInfoDao;
//		}
//	}
//	public synchronized void insertCardInfo(CardInfo cardInfo){
//		if(cardInfo==null){
//			return;
//		}
//		SQLiteDatabase db = mHelper.getWritableDatabase();
//		db.replace(CardTableMetaData.TABLE_NAME, null, 
//				getContentValues(cardInfo));
//		db.close();
//
//	}
//
//
//	public CardInfo getCardInfo(){
//		CardInfo cardInfo=null;
//		SQLiteDatabase db = mHelper.getReadableDatabase();
//		Cursor cursor = db.query(CardTableMetaData.TABLE_NAME, null, 
//				null, null, null, null, null);
//		if(cursor != null){
//			if(cursor.getCount() > 0 && cursor.moveToFirst()){
//				cardInfo = new CardInfo(cursor);
//			}
//			cursor.close();
//		}
//		db.close();
//		return cardInfo;
//	}
//
//	public int deleteAll(){
//		SQLiteDatabase db = mHelper.getWritableDatabase();
//		int count = db.delete(CardTableMetaData.TABLE_NAME, null, null);
//		db.close();
//		return count;
//	}
//
//
//
//	public ContentValues getContentValues(CardInfo cardInfo){
//
//		ContentValues cv = new ContentValues();
//		cv.put(CardTableMetaData.CARD_TYPE, cardInfo.getType());
//		cv.put(CardTableMetaData.CARD_CARDNUMBER, cardInfo.getCardNumber());
//		cv.put(CardTableMetaData.CARD_SID, cardInfo.getSid());
//		cv.put(CardTableMetaData.CARD_BANKNAME,cardInfo.getBankName());
//		return cv;
//	}

}
