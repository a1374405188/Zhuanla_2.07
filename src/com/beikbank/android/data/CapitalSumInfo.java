package com.beikbank.android.data;

import java.io.Serializable;

public class CapitalSumInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	public String name;
	public String time;
	public double earning;
	public double amount;
	public String productId;
	public String valueDateType;
	public double yearRate;
	
	public CapitalSumInfo(String name,String time,double earning,double amount
			,String productId,String valueDateType,double yearRate){
		this.name=name;
		this.time=time;
		this.earning=earning;
		this.amount=amount;
		this.productId=productId;
		this.valueDateType=valueDateType;
		this.yearRate=yearRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

	public double getEarning() {
		return earning;
	}

	public void setEarning(double earning) {
		this.earning = earning;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getValueDateType() {
		return valueDateType;
	}

	public void setValueDateType(String valueDateType) {
		this.valueDateType = valueDateType;
	}

	public double getYearRate() {
		return yearRate;
	}

	public void setYearRate(double yearRate) {
		this.yearRate = yearRate;
	}
	
	

	
	
}
