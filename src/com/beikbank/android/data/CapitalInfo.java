package com.beikbank.android.data;

import java.io.Serializable;

public class CapitalInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	public double earnings;
	public String dealTime;
	public double amount;
	public String valueDateType;
	public String expirationTime;
	public String productName;
	public double yearRate;
	public String productId;
	
	
	public double getEarnings() {
		return earnings;
	}
	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public String getValueDateType() {
		return valueDateType;
	}
	public void setValueDateType(String valueDateType) {
		this.valueDateType = valueDateType;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getYearRate() {
		return yearRate;
	}
	public void setYearRate(double yearRate) {
		this.yearRate = yearRate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
