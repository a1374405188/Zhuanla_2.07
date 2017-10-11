package com.beikbank.android.api;

import android.content.Context;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.http.JsonHttpResponseHandler;
import com.beikbank.android.utils.Configuration;
import com.beikbank.android.utils.MD5;

public class BeikBankApi extends BeikBankBaseApi{

	private static BeikBankApi beikBankClientApi = null;

	public static BeikBankApi getInstance(){
		if(beikBankClientApi == null){
			return new BeikBankApi();
		}else{
			return beikBankClientApi;
		}
	}

	private BeikBankApi(){

	}

	//检查手机号码是否注册过
	public void checkPhoneInfo(Context context,String phoneNumber,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKPHONEINFO,
				checkPhoneInfoParam(phoneNumber),handler);
	} 

	//获取产品列表
	public void getProductListInfo(Context context,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+GETPRODUCTLIST,
				commonInfoParam(),handler);
	} 

	//获取精品推荐(暂时不用)
	public void getRecommendProductInfo(Context context,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+GETRECOMMENDPRODUCT,
				commonInfoParam(),handler);
	}

	//检查验证码是否正确
	public void checkIdentifyInfo(Context context,String verificode,String verifiId,String phoneNumber,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKVERTIFY,
				checkIdentifyInfoParam(verificode,verifiId,phoneNumber),handler);
	}

	//发送验证码
	public void getIdentifyCodeInfo(Context context,String phoneNumber,String checkCodeType,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+GETIDENTIFYCODE,
				getIdentifyCodeInfoParam(phoneNumber,checkCodeType),handler);
	}

	//注册
	public void checkPasswordInfo(Context context,String phoneNumber,String loginPassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKPASSWORD,
				checkPasswordInfoParam(phoneNumber,MD5.md5s32(loginPassword),SystemConfig.SOURCES_CODE),handler);
	}
	//忘记密码  登录设置密码
	public void setLoginPasswordInfo(Context context,String phoneNumber,String loginPassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKUSERPASSWORD,
				checkPasswordInfoParam(phoneNumber,MD5.md5s32(loginPassword),null),handler);
	}

	//登录
	public void checkLoginInfo(Context context,String phoneNumber,String loginPassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKLOGIN,
				checkPasswordInfoParam(phoneNumber,MD5.md5s32(loginPassword),null),handler);
	}

	//设置交易密码
	public void setTransactionPasswordInfo(Context context,String memberID,String transactionPassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+SETTRANSACTIONPWD,
				setTransactionPasswordInfoParam(memberID,MD5.md5s32(transactionPassword)),handler);
	}

	//获取产品详情
	public void getDebtInfo(Context context,String debtID,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+GETDEBT,
				getDebtInfoParam(debtID),handler);
	}

	//实名认证
	public void handleAuthRealnameInfo(Context context,String realName,String idNumber,String memberID,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+HANDLEAUTHREALNAME,
				handleAuthRealnameInfoParam(realName,idNumber,memberID),handler);
	}

	//绑定银行卡
	public void checkBindBankInfo(Context context,String cardNumber,String memberID,String cardType,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKBINDBANK,
				checkBindBankInfoParam(cardNumber,memberID,cardType),handler);
	}

	//修改登录密码
	public void updatePasswordInfo(Context context,String memberID,String oldLoginPassword,String newLoginPassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+UPDATEPASSWORD,
				updatePasswordInfoParam(memberID,MD5.md5s32(oldLoginPassword),MD5.md5s32(newLoginPassword)),handler);
	}
	//修改交易密码
	public void updateTransactionPasswordInfo(Context context,String memberID,String oldTransactionPassword,String newTransactionPassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+UPDATETRANSACTIONPWD,
				updateTransactionPasswordInfoParam(memberID,MD5.md5s32(oldTransactionPassword),MD5.md5s32(newTransactionPassword)),handler);
	}

	//意见反馈
	public void handleFeedbackInfo(Context context,String phoneNumber,String feedback,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+FEEDBACK,
				handleFeedbackInfoParam(phoneNumber,feedback),handler);
	}

	//交易列表
	public void getTransactionListInfo(Context context,String memberID,String startLine,String endLine,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL8443+TRANSACTIONLIST,
				getTransactionListInfoParam2(memberID,startLine,endLine),handler);
	//	mAsyncHttpClient.get(context,Configuration.kHOST_URL+TRANSACTIONLIST,
	//			getTransactionListInfoParam2(memberID,startLine,endLine),handler);
	}

	//交易详情
	public void getTransactionDetailInfo(Context context,String memberID,String transactionID,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL8443+TRANSACTIONDETAIL,
				getTransactionDetailInfoParam(memberID, transactionID),handler);
		
		//mAsyncHttpClient.get(context,Configuration.kHOST_URL+TRANSACTIONDETAIL,
		//		getTransactionDetailInfoParam(memberID, transactionID),handler);
	}

	//支持银行列表
	public void getBankListInfo(Context context,String version, JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+BANKLIST,
				getBankListInfoParam(version),handler);
	}
	//验证验证码
	public void checkCodeInfo(Context context,String phoneNumber, String realName,String idNumber,
			String verificode,String verifiId,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKCODE,
				checkCodeInfoParam(phoneNumber,realName,idNumber,verificode,verifiId),handler);
	}

	//是否实名认证
	public void checkAuthenticatedInfo(Context context,String phoneNumber,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKAUTH,
				checkPhoneInfoParam(phoneNumber),handler);
	}

	//检测更新
	public void checkUpdateInfo(Context context,String version,String osType,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+CHECKUPDATE,
				checkUpdateInfoParam(version, osType),handler);
	}
	//获取收益列表
	public void getCapitalListInfo(Context context,String memberID,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+CAPITALLIST,
				getTransactionListInfoParam(memberID),handler);
	}
	//提升额度
	public void getPhoneVertifyInfo(Context context,String memberID,String verificode,String verifiId,
			String phoneNumber,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKPHONEVETIFY,
				checkPhoneVertifyInfoParam(memberID, verificode, verifiId, phoneNumber),handler);
	}
	//预留手机号码验证
	public void checkBankCardPhoneNumberInfo(Context context,String memberID,
			String phoneNumber,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+CHECKBANKCARDPHONENUMBER,
				checkBankCardPhoneNumberInfoParam(memberID, phoneNumber),handler);
	}
	//小额打款
	public void smallRemittanceInfo(Context context,String memberID,
			JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+SMALLREMITTANCE,
				getTransactionListInfoParam(memberID),handler);
	}
	//小额打款，额度提升
	public void smallRemittanceLimitInfo(Context context,String memberID,
			String amount,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL+SMALLREMITTANCELIMIT,
				smallRemittanceLimitInfoParam(memberID, amount),handler);
	}

	//新版新增的接口---------------------

	//获取基金产品
	public void getProductFundInfo(Context context,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+GETPRODUCTFUND,
				commonInfoParam(),handler);
	}

	//购买
	public void handlePurchaseInfo(Context context,String memberID,String amount,String fundId,String bandCardId,String verificode,String tradePassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL8443+PAYFORPRODUCT,
				handlePurchaseInfoParam(memberID,amount,fundId,bandCardId,verificode,MD5.md5s32(tradePassword)),handler);
		//mAsyncHttpClient.post(context,Configuration.kHOST_URL+PAYFORPRODUCT,
		//		handlePurchaseInfoParam(memberID,amount,fundId,bandCardId,verificode,MD5.md5s32(tradePassword)),handler);
	}

	//赎回
	public void handleReturnInfo(Context context,String memberID,String amount,String fundId,String bandCardId,String verificode,String tradePassword,JsonHttpResponseHandler handler){
		mAsyncHttpClient.post(context,Configuration.kHOST_URL8443+RETURNMONEY,
				handlePurchaseInfoParam(memberID,amount,fundId,bandCardId,verificode,MD5.md5s32(tradePassword)),handler);
		
		//mAsyncHttpClient.post(context,Configuration.kHOST_URL+RETURNMONEY,
				//handlePurchaseInfoParam(memberID,amount,fundId,bandCardId,verificode,MD5.md5s32(tradePassword)),handler);
	}



	//总资产
	public void getTotalCapitalInfo(Context context,String memberID,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+TOTALCAPITAL,
				getTransactionListInfoParam(memberID),handler);
	}

	//收益
	public void getIncomeInfo(Context context,String memberID,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+INCOME,
				getTransactionListInfoParam(memberID),handler);
	}

	//投资项目列表
	public void getProjectInfoList(Context context,String fundId,String startLine,String endLine,JsonHttpResponseHandler handler){
		mAsyncHttpClient.get(context,Configuration.kHOST_URL+PROJECTLIST,
				getProjectInfoParam(fundId,startLine,endLine),handler);
	}


}
