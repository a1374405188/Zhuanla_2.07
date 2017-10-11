package com.beikbank.android.api;

import com.beikbank.android.utils.Configuration;


public class BeikBankUrlApi {

	public static String SERVER = Configuration.kHOST_URL;
    public final static String CHECKPHONEINFO="register/hasRegistered.action";
    public final static String GETPRODUCTLIST="product/getProductList.action";
    public final static String GETRECOMMENDPRODUCT="product/getRecommendProductInfo.action";
    public final static String CHECKVERTIFY="register/isVerificodeCorrect.action";
    public final static String CHECKPASSWORD="register/setLoginPassword.action";
    public final static String CHECKLOGIN="user/isLoginPasswordCorrect.action";
    public final static String GETDEBT="product/getProductInfo.action";
    public final static String HANDLEAUTHREALNAME="authenticateRealName.action";
    public final static String CHECKBINDBANK="bindBankCard.action";
    
    public final static String UPDATEPASSWORD="user/updateLoginPassword.action";
    public final static String FEEDBACK="message/feedbackSuggestion.action";
    public final static String TRANSACTIONLIST="trade/getTradeRecordList.action";
    public final static String TRANSACTIONDETAIL="trade/getTradeRecordInfo.action";
    public final static String BANKLIST="getSystemData.action";
    
    public final static String GETIDENTIFYCODE="sendVerificode.action";
    public final static String CHECKCODE="user/isVerificodeCorrect.action";
    public final static String CHECKAUTH="user/hasAuthenticated.action";
    public final static String SETTRANSACTIONPWD="user/setTradePassword.action";
    public final static String UPDATETRANSACTIONPWD="user/updateTradePassword.action";
    public final static String CHECKUSERPASSWORD="user/setLoginPassword.action";
    public final static String CHECKUPDATE="checkVersion.action";
    public final static String CAPITALLIST="user/getCapitalList.action";
    public final static String CHECKPHONEVETIFY="isVerificodeCorrect.action";
    public final static String CHECKBANKCARDPHONENUMBER="checkBankCardPhoneNumber.action";
    public final static String SMALLREMITTANCE="smallRemittance.action";
    public final static String SMALLREMITTANCELIMIT="smallRemittanceLimit.action";
    
    public final static String GETPRODUCTFUND="product/getProductFundList.action";
    public final static String PAYFORPRODUCT="trade/payForFundProduct.action";
    public final static String TOTALCAPITAL="user/getTotalCapital.action";
    public final static String INCOME="user/getInterest.action";
    public final static String RETURNMONEY="trade/withdrawCash.action";
    public final static String PROJECTLIST="product/getProductFundBondList.action";
}
