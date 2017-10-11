package com.beikbank.android.data;

import java.io.Serializable;

import android.database.Cursor;

import com.beikbank.android.db.BankTableMetaData;

public class BankInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	public String disorder;
	public String cumulativeLimit;
	public String singleLimit;
	public String iconUrl;
	public String logoUrl;
	public String type;
	public String bankName;
	
	/**
	 * 最小限额
	 */
	public String minLimit;
	public BankInfo(){

	}
	
	public BankInfo(Cursor cursor){
		this.disorder=String.valueOf(cursor.getInt(cursor.getColumnIndex(BankTableMetaData.BANK_DISORDER)));
		this.cumulativeLimit=cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_CUMULATIVELIMIT));
		this.singleLimit=cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_SINGLELIMIT));
		this.iconUrl=cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_ICONURL));
		this.logoUrl=cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_LOGOURL));
		this.type=cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_TYPE));
		this.bankName=cursor.getString(cursor.getColumnIndex(BankTableMetaData.BANK_NAME));
	}
	
	

	public String getDisorder() {
		return disorder;
	}

	public void setDisorder(String disorder) {
		this.disorder = disorder;
	}

	public String getCumulativeLimit() {
		return cumulativeLimit;
	}

	public void setCumulativeLimit(String cumulativeLimit) {
		this.cumulativeLimit = cumulativeLimit;
	}

	public String getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(String singleLimit) {
		this.singleLimit = singleLimit;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
