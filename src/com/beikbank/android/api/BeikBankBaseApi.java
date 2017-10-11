package com.beikbank.android.api;
import java.util.TreeMap;

import android.content.Context;

import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.AsyncHttpClient;
import com.beikbank.android.http.RequestParams;
import com.beikbank.android.utils.Utils;
public class BeikBankBaseApi extends BeikBankUrlApi{


	protected Context mContext; 
	protected AsyncHttpClient mAsyncHttpClient = null;
	protected TreeMap treeMap=new TreeMap();

	protected BeikBankBaseApi(){
		this.mContext = BeikBankApplication.getInstance();
		mAsyncHttpClient= new AsyncHttpClient();
	}

	protected RequestParams checkPhoneInfoParam(String phoneNumber) {

		RequestParams params = new RequestParams();
		params.put("phoneNumber",phoneNumber);
		treeMap.clear();
		treeMap.put("phoneNumber", phoneNumber);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}
	//无参数
	protected RequestParams commonInfoParam() {
		RequestParams params = new RequestParams();
		treeMap.clear();
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams checkIdentifyInfoParam(String verificode,String verifiId,String phoneNumber) {
		RequestParams params = new RequestParams();
		params.put("verificode",verificode);
		params.put("verifiId",verifiId);
		params.put("phoneNumber",phoneNumber);
		treeMap.clear();
		treeMap.put("verificode",verificode);
		treeMap.put("verifiId",verifiId);
		treeMap.put("phoneNumber",phoneNumber);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams getIdentifyCodeInfoParam(String phoneNumber,String checkCodeType) {
		RequestParams params = new RequestParams();
		params.put("phoneNumber",phoneNumber);
		params.put("checkCodeType",checkCodeType);
		treeMap.clear();
		treeMap.put("phoneNumber",phoneNumber);
		treeMap.put("checkCodeType",checkCodeType);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams checkPasswordInfoParam(String phoneNumber,String loginPassword,String code) {
		RequestParams params = new RequestParams();
		params.put("phoneNumber",phoneNumber);
		params.put("loginPassword",loginPassword);
		if(code!=null)
		{
			params.put("idCode",code);
		}
		treeMap.clear();
		treeMap.put("phoneNumber",phoneNumber);
		treeMap.put("loginPassword",loginPassword);
		if(code!=null)
		{
			treeMap.put("idCode",code);
		}
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams getDebtInfoParam(String debtID) {
		RequestParams params = new RequestParams();
		params.put("debtID",debtID);
		treeMap.clear();
		treeMap.put("debtID",debtID);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams handleAuthRealnameInfoParam(String realName,String idNumber,String memberID) {
		RequestParams params = new RequestParams();
		params.put("realName",realName);
		params.put("idNumber",idNumber);
		params.put("memberID",memberID);
		treeMap.clear();
		treeMap.put("realName",realName);
		treeMap.put("idNumber",idNumber);
		treeMap.put("memberID",memberID);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams checkBindBankInfoParam(String cardNumber,String memberID,String cardType) {
		RequestParams params = new RequestParams();
		params.put("cardNumber",cardNumber);
		params.put("memberID",memberID);
		params.put("cardType",cardType);
		treeMap.clear();
		treeMap.put("cardNumber",cardNumber);
		treeMap.put("memberID",memberID);
		treeMap.put("cardType",cardType);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}
	protected RequestParams handlePurchaseInfoParam(String memberID,String amount,String fundId,String bandCardId,String verificode,String tradePassword) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("amount",amount);
		params.put("fundId",fundId);
		params.put("bandCardId",bandCardId);
		params.put("verificode",verificode);
		params.put("tradePassword",tradePassword);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("amount",amount);
		treeMap.put("fundId",fundId);
		treeMap.put("bandCardId",bandCardId);
		treeMap.put("verificode",verificode);
		treeMap.put("tradePassword",tradePassword);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}


	protected RequestParams updatePasswordInfoParam(String memberID,String oldLoginPassword,String newLoginPassword) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("oldLoginPassword",oldLoginPassword);
		params.put("newLoginPassword",newLoginPassword);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("oldLoginPassword",oldLoginPassword);
		treeMap.put("newLoginPassword",newLoginPassword);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams updateTransactionPasswordInfoParam(String memberID,String oldTransactionPassword,
			String newTransactionPassword) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("oldTransactionPassword",oldTransactionPassword);
		params.put("newTransactionPassword",newTransactionPassword);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("oldTransactionPassword",oldTransactionPassword);
		treeMap.put("newTransactionPassword",newTransactionPassword);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams handleFeedbackInfoParam(String phoneNumber,String feedback) {
		RequestParams params = new RequestParams();
		params.put("phoneNumber",phoneNumber);
		params.put("feedback",feedback);
		treeMap.clear();
		treeMap.put("phoneNumber",phoneNumber);
		treeMap.put("feedback",feedback);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams getTransactionListInfoParam(String memberID) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}
	protected RequestParams getTransactionListInfoParam2(String memberID,String startLine,String endLine) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("startLine",startLine);
		params.put("endLine",endLine);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("startLine",startLine);
		treeMap.put("endLine",endLine);
		params.put("sign", Utils.getSign(treeMap));
		return params;
	}

	protected RequestParams getHistoryIncomeInfoParam(String memberID,String startDate,String endDate) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("startDate",startDate);
		treeMap.put("endDate",endDate);
		params.put("sign", Utils.getSign(treeMap));		
		return params;
	}

	protected RequestParams getTransactionDetailInfoParam(String memberID,String transactionID) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("transactionID",transactionID);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("transactionID",transactionID);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams getBankListInfoParam(String version) {
		RequestParams params = new RequestParams();
		params.put("version",version);
		treeMap.clear();
		treeMap.put("version",version);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams checkCodeInfoParam(String phoneNumber, String realName,String idNumber,
			String verificode,String verifiId) {
		RequestParams params = new RequestParams();
		params.put("phoneNumber",phoneNumber);
		params.put("realName",realName);
		params.put("idNumber",idNumber);
		params.put("verificode",verificode);
		params.put("verifiId",verifiId);
		treeMap.clear();
		treeMap.put("phoneNumber",phoneNumber);
		treeMap.put("realName",realName);
		treeMap.put("idNumber",idNumber);
		treeMap.put("verificode",verificode);
		treeMap.put("verifiId",verifiId);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams setTransactionPasswordInfoParam(String memberID,String transactionPassword) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("transactionPassword",transactionPassword);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("transactionPassword",transactionPassword);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams checkUpdateInfoParam(String version,String osType) {
		RequestParams params = new RequestParams();
		params.put("version",version);
		params.put("osType",osType);
		treeMap.clear();
		treeMap.put("version",version);
		treeMap.put("osType",osType);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams checkPhoneVertifyInfoParam(String memberID,String verificode,String verifiId
			,String phoneNumber) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("verificode",verificode);
		params.put("verifiId",verifiId);
		params.put("phoneNumber",phoneNumber);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("verificode",verificode);
		treeMap.put("verifiId",verifiId);
		treeMap.put("phoneNumber",phoneNumber);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams checkBankCardPhoneNumberInfoParam(String memberID,String phoneNumber) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("phoneNumber",phoneNumber);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("phoneNumber",phoneNumber);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}

	protected RequestParams smallRemittanceLimitInfoParam(String memberID,String amount) {
		RequestParams params = new RequestParams();
		params.put("memberID",memberID);
		params.put("amount",amount);
		treeMap.clear();
		treeMap.put("memberID",memberID);
		treeMap.put("amount",amount);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}
	
	protected RequestParams getProjectInfoParam(String fundId,String startLine,String endLine) {
		RequestParams params = new RequestParams();
		params.put("fundId",fundId);
		params.put("startLine",startLine);
		params.put("endLine",endLine);
		treeMap.clear();
		treeMap.put("fundId",fundId);
		treeMap.put("startLine",startLine);
		treeMap.put("endLine",endLine);
		params.put("sign", Utils.getSign(treeMap));	
		return params;
	}
	



}
