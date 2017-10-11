package com.beikbank.android.data;

import java.util.ArrayList;

import android.database.Cursor;

import com.beikbank.android.annotation.DBPrimaryKey;
import com.beikbank.android.db.UserTableMetaData;

public class UserInfo {
	@DBPrimaryKey(privaryKey=1)
	public String id;
	public String shumiToken;
	public boolean hasBindCard;
	public String userAmountLimit;
	public String realName;
	public boolean hasUpgrade;
	public String logInToken;
	public boolean hasAuthenticated;
	public String idNumber;
	public boolean hasSetPaypassword;
	/**
	 * 更改实名认证的次数
	 */
	public String countAuthLeft;
	public ArrayList<CardInfo> cards;
	
	public UserInfo(){

	}
	
//	public UserInfo(Cursor cursor){
//		this.id=cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_ID));
//		this.shumiToken=cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_SHUMITOKEN));
//		this.hasBindCard=Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_ISBINDCARD)));
//		this.userAmountLimit=cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_AMOUNTLIMIT));
//		this.realName=cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_REALNAME));
//		this.hasUpgrade=Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_ISUPGRADE)));
//		this.logInToken=cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_LOGINTOKEN));
//		this.hasAuthenticated=Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(
//				UserTableMetaData.USER_ISAUTHENTICATE)));
//		this.idNumber=cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_ICNUMBER));
//		this.hasSetPaypassword=Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(
//				UserTableMetaData.USER_ISSETTRANSACTIONPWD)));
//	}
//	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShumiToken() {
		return shumiToken;
	}
	public void setShumiToken(String shumiToken) {
		this.shumiToken = shumiToken;
	}
	public boolean isHasBindCard() {
		return hasBindCard;
	}
	public void setHasBindCard(boolean hasBindCard) {
		this.hasBindCard = hasBindCard;
	}
	public String getUserAmountLimit() {
		return userAmountLimit;
	}
	public void setUserAmountLimit(String userAmountLimit) {
		this.userAmountLimit = userAmountLimit;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public boolean isHasUpgrade() {
		return hasUpgrade;
	}
	public void setHasUpgrade(boolean hasUpgrade) {
		this.hasUpgrade = hasUpgrade;
	}
	public String getLogInToken() {
		return logInToken;
	}
	public void setLogInToken(String logInToken) {
		this.logInToken = logInToken;
	}
	
	public boolean isHasAuthenticated() {
		return hasAuthenticated;
	}

	public void setHasAuthenticated(boolean hasAuthenticated) {
		this.hasAuthenticated = hasAuthenticated;
	}

	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public ArrayList<CardInfo> getCards() {
		return cards;
	}

	public void setCards(ArrayList<CardInfo> cards) {
		this.cards = cards;
	}

	public boolean isHasSetPaypassword() {
		return hasSetPaypassword;
	}

	public void setHasSetPaypassword(boolean hasSetPaypassword) {
		this.hasSetPaypassword = hasSetPaypassword;
	}
	
	
	
	
	
	
}
