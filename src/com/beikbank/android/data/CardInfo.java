package com.beikbank.android.data;

import android.database.Cursor;

import com.beikbank.android.annotation.DBPrimaryKey;
import com.beikbank.android.db.CardTableMetaData;

public class CardInfo {
	public String type="";
	@DBPrimaryKey(privaryKey=1)
	public String cardNumber;
	public String sid;
	public String bankName;
	public CardInfo(){

	}
//	
//	public CardInfo(Cursor cursor){
//		this.type=cursor.getString(cursor.getColumnIndex(CardTableMetaData.CARD_TYPE));
//		this.cardNumber=cursor.getString(cursor.getColumnIndex(CardTableMetaData.CARD_CARDNUMBER));
//		sid=cursor.getString(cursor.getColumnIndex(CardTableMetaData.CARD_SID));
//		bankName=cursor.getString(cursor.getColumnIndex(CardTableMetaData.CARD_BANKNAME));
//	}
//
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
