package com.beikbank.android.data;

import java.io.Serializable;

public class TransactionInfo implements Serializable{
	/**
	 * 实际扣款交易金额
	 */
	public String transactionAmount;
	/**
	 * 交易id
	 */
	public String tradingID;
	/**
	 * 交易日期
	 */
	public String purchaseDate;
	/**
	 * //0：成功 1：失败 4：处理中 5：失效 2：订单生成状态
	 */
	public String transactionStatus;
	/**
	 * 产品名称
	 */
	public String productName;
	public String productID;
	/**
	 * 产品类型1: 基金 0: 债权
	 */
	public String productType;
	public String orderNumber;
	/**
	 * 1：充值 2：银行卡购买 3：账户购买 4：赎回 5：提现 6:转让  11.网银充值12.网银购买
	 */
	public String tradeType;
	/**
	 * 理财期限
	 */
	public String termbondPeriod;
	/**
	 * 失败时返回信息
	 */
	public String retMsg;
	/**
	 * 年化收益率
	 */
	public String yearRate;
	/**
	 * 原始交易金额
	 */
	public String planAmount="0";
	/**
	 * 交易模式0:银行卡，1:余额
	 */
	public String tradeMode;
	/**
	 * 加赠收益率
	 */
	public String extraRate;
//	public double getTransactionAmount() {
//		return transactionAmount;
//	}
//	public void setTransactionAmount(double transactionAmount) {
//		this.transactionAmount = transactionAmount;
//	}
	public String getTradingID() {
		return tradingID;
	}
	public void setTradingID(String tradingID) {
		this.tradingID = tradingID;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	
	
	
}
