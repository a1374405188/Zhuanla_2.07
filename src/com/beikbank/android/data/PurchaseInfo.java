package com.beikbank.android.data;

import java.io.Serializable;

public class PurchaseInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	public String dealTime;
	public String status;
	public String orderNumber;
	public String preValueDate;
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPreValueDate() {
		return preValueDate;
	}
	public void setPreValueDate(String preValueDate) {
		this.preValueDate = preValueDate;
	}
	
	
}
