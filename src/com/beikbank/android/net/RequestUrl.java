package com.beikbank.android.net;

import com.beikbank.android.utils.Configuration;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 **/
public interface RequestUrl {
	public static String SERVER = Configuration.kHOST_URL8443;
	public static String SERVER_443 = Configuration.kHOST_URL8443;
	public static String SERVER_old = Configuration.kHOST_URL;
	/**
	 * //检查手机号码是否注册过
	 */
    public final static String CHECKPHONEINFO="register/hasRegistered.action";
    /**
     * 获取产品列表
     */
    public final static String GETPRODUCTLIST="product/getProductList.action";
    public final static String GETRECOMMENDPRODUCT="product/getRecommendProductInfo.action";
    
    /**
     * 注册时检查验证码是否正确
     */
    public final static String CHECKVERTIFY="register/isVerificodeCorrect.action";
    /**
     * 注册
     */
    public final static String CHECKPASSWORD="register/setLoginPassword.action";
    /**
     * 登录
     */
    public final static String CHECKLOGIN="user/isLoginPasswordCorrect.action";
    /**
     * 获取产品详情
     */
    public final static String GETDEBT="product/getProductInfo.action";
    
    /**
     * 实名认证
     */
    public final static String HANDLEAUTHREALNAME="authenticateRealName.action";
    /**
     * 实名认证v2
     */
    public final static String realname="authenticateRealNameV2.action";
    /**
     * 绑定银行卡
     */
    public final static String CHECKBINDBANK="bindBankCard.action";
    /**
     * 修改登录密码
     */
    public final static String UPDATEPASSWORD="user/updateLoginPassword.action";
    public final static String FEEDBACK="message/feedbackSuggestion.action";
    /**
     * 交易列表
     */
    public final static String TRANSACTIONLIST="trade/getTradeRecordList.action";
    /**
     * 交易详情
     */
    public final static String TRANSACTIONDETAIL="trade/getTradeRecordInfo.action";
    /**
     * 支持银行列表V2
     */
    public final static String BANKLIST="getSystemDataV2.action";
    /**
     * 发送验证码
     */
    public final static String GETIDENTIFYCODE="sendVerificode.action";
    /**
     * 找回密码时登录验证验证码
     */
    public final static String CHECKCODE="user/isVerificodeCorrect.action";
    /**
     * 是否实名认证
     */
    public final static String CHECKAUTH="user/hasAuthenticated.action";
    /**
     * 得到分享
     */
    public final static String getShare="sharev2/getShareInfo.action";
    /**
     * 得到邀请分享
     */
    public final static String getShare2="sharev2/getRequestShareInfo.action";
    /**
     * 得到红包
     */
    public final static String getHongbao="coupon/getUserCoupon.action";
    /**
     * 得到定期收益记录
     */
    public final static String getDingQiShouYi="user/getInterestRecordV3.action";
    /**
     * 得到Win窗口
     */
    public final static String getWin="activity/getPageWindowAD.action";
    
    /**
     * 设置交易密码
     */
    public final static String SETTRANSACTIONPWD="user/setTradePassword.action";
    
    /**
     * 修改交易密码
     */
    public final static String UPDATETRANSACTIONPWD="user/updateTradePassword.action";
    
    
    /**
     * 验证登录密码是否正确
     */
    public final static String CHECKUSERPASSWORD="user/setLoginPassword.action";
    /**
     * 检查更新
     */
    public final static String CHECKUPDATE="checkVersion.action";
    /**
     * 获取收益列表
     */
    public final static String CAPITALLIST="user/getCapitalList.action";
    /**
     * 提升额度
     */
    public final static String CHECKPHONEVETIFY="isVerificodeCorrect.action";
    /**
     * 银行预留手机号
     */
    public final static String CHECKBANKCARDPHONENUMBER="checkBankCardPhoneNumber.action";
    public final static String SMALLREMITTANCE="smallRemittance.action";
    public final static String SMALLREMITTANCELIMIT="smallRemittanceLimit.action";
    /**
     * 得到基金产品
     */
    public final static String GETPRODUCTFUND="product/getProductFundList.action";
    /**
     * 购买
     */
    public final static String PAYFORPRODUCT="trade/payForFundProduct.action";
    /**
     * 总资产
     */
    public final static String TOTALCAPITAL="user/getTotalCapital.action";
    /**
     * 收益
     */
    public final static String INCOME="user/getInterest.action";
    /**
     * 赎回
     */
    public final static String RETURNMONEY="trade/withdrawCash.action";
    /**
     * 投资项目
     */
    public final static String PROJECTLIST="product/getProductFundBondList.action";
    /**
     * 得到用户取现的手续费
     */
    public final static String GET_USER_POUNDAGE="user/getUserPoundage.action";
    /**
     * 得到安全和帮助中心数据
     */
    public final static String get_helpandsafety="oper/getOperHelpList.action";
    
    /**
     * 一键反馈
     */
    public final static String one_key="message/sendSuggestion.action";
    
    
    /**
     * 判断是否需要验证银行卡
     */
    public final static String ischeckbank="trade/checkTransactionLimit.action";
    
    /**
     * 检查银行预留手机号并发送短信
     */
    public final static String chekandsend="checkAndSendVerificode.action";
    
    /**
     * 获取公告
     */
    public final static String notice="oper/getNotice.action";
    
    
    /**
     * 得到购买上限
     */
    public final static String getFundLimit="product/getProductFundLimit.action";
    
    
    /**
     * 得到消息
     */
    public final static String getMessage="message/getUserMessage.action";
    /**
     * 标记消息已读
     */
    public final static String readMessage="message/readUserMessage.action";
    /**
     * 删除消息
     */
    public final static String delMessage="message/deleteUserMessage.action";
    
    /**
     * 得到广告页
     */
    public final static String getAction="activity/getActivityAd.action";
    /**
     * 得到活动列表
     */
    public final static String getActionList="activity/getActivityList.action";
    /**
     * 得到活动详情
     */
    public final static String getActionInfo="activity/getActivityInfo.action";
    
    
    /**
     * 得到系统通知
     */
    public final static String getSysNotice="message/getUserMessageNotice.action";
    /**
     * 得到用户收益列表
     */
    public final static String getUserRecord="user/getInterestRecord.action";
    
    
    /**
     * 得到登录用户资产信息
     */
    public final static String getUserCapital="user/getUserCapital.action";
    /**
     * 公共消息信息
     */
    public final static String getCommonInfo="getCommonInfo.action";
    /**
     * 得到定期产品详情
     */
    public final static String getDingqiPI="product/getProductBondInfo.action";
    /**
     * 得到定期产品列表
     */
    public final static String getDingqiP="product/getProductBondList.action";
    /**
     * 得到定期产品列表
     */
    public final static String getDingqiPByUser="product/getProductBondListByUser.action";
    /**
     * 购买请求交易
     */
    public final static String reqPayforP="trade/reqPayforProduct.action";
    /**
     * 购买请求交易v2
     */
    public final static String reqPayforPv2="trade/reqPayforProductv2.action";
    
    /**
     * 取现请求交易
     */
    public final static String reqPayforP2="trade/reqWithdrawCash.action";
    /**
     * 购买确认交易
     */
    public final static String confirmPayforP="trade/confirmPayforProduct.action";
    /**
     * 取现确认交易
     */
    public final static String confirmPayforP2="trade/confirmWithdrawCash.action";
    /**
     * 获取用户资产详情
     */
    public final static String getUserCapitalInfo="user/getUserCapitalInfo.action";
    
    /**
     * 得到用户资产
     */
    public final static String getUserCapital2="user/getUserCapitalv3.action";
    /**
     * 绑卡，验证银行预留手机号
     */
    public final static String checkBankAndPhone="bindCardWithPhoneReq.action";
    
    /**
     *绑卡，验证银行预留手机号验证码
     */
    public final static String checkBankAndPhoneR="bindCardWithPhoneReqConfirm.action";
    
    /**
     * 得到定期债券组合
     */
    public final static String getProductFundBondList="product/getProductBondBondList.action";
    /**
     * 得到qq号
     */
    public final static String getConfigQQ="getCommonConfig.action";
    
    /**
     * 钱包请求充值
     */
    public final static String qianbao1="trade/reqRecharge.action";
    /**
     * 钱包请求充值确认
     */
    public final static String qianbao2="trade/confirmRecharge.action";
    /**
     * 钱包请求取现
     */
    public final static String qianbao3="trade/reqWithdrawCashFromBalance.action";
    /**
     * 钱包请求取现确认
     */
    public final static String qianbao4="trade/confirmWithdrawCashFromBalance.action";
    /**
     * 钱包请求取现交易记录
     */
    public final static String qianbao5="trade/getTradeRechargeRecordList.action";
    /**
     * 钱包余额
     */
    public final static String qianbao6="user/getUserBalanceCapital.action";
    
    /**
     * 余额提现请求V2
     */
    public final static String qianbao7="trade/reqWithdrawCashFromBalanceV2.action";
    /**
     * 余额提现确认V2
     */
    public final static String qianbao8="trade/confirmWithdrawCashFromBalanceV2.action";
    /**
     * 定期购买名单
     */
    public final static String payList="product/getPurchasersList.action";
    /**
     * 获取余额资产
     */
    public final static String yuer="user/getUserBalanceCapital.action";
    /**
     * 活期转定期请求
     */
    public final static String huoqiToR="trade/reqFondChangeBond.action";
    /**
     * 活期转定期确认
     */
    public final static String huoqiTo="trade/confirmFondChangeBond.action";
    /**
     * 活期赎回请求
     */
    public final static String huoqiReturnR="trade/reqRedeemProductFund.action";
    /**
     * 活期赎回确认
     */
    public final static String huoqiReturn="trade/confirmRedeemProductFund.action";
    /**
     * 钱包交易记录
     */
    public final static String qianbaoRecord="trade/getCapitalRecordList.action";
    
    /**
     * 推送
     */
    public final static String tuisong="register/installPushMessage.action";
    /**
     * 得到活动
     */
    public final static String getHuodong="activity/selectActivityListByUser.action";
    /**
     * 得到消息2
     */
    public final static String getMessage2="message/getUserMessageNoticeV2.action";
    /**
     * 标记公告已读
     */
    public final static String readMessage3="message/lookNotice.action";
    /**
     * 得到标识
     */
    public final static String getBiaoshi="system/getSysIconInfo.action";
    /**
     * 检查是否需要绑定银行卡
     */
    public final static String checkBank="trade/checkUserAccount.action";
    /**
     * 富有短线验证确认交易
     */
    public final static String queRenJiaoyi="trade/confirmFoiouPayforProduct.action";
    /**
     * 富有短信充值验证确认交易
     */
    public final static String queRenJiaoyi2="trade/confirmFuiouRecharge.action";
    
    /**
     * 帮助中心1级
     */
    public final static String help1="oper/selectMenuHelp.action";
    /**
     * 帮助中心2级
     */
    public final static String help2="oper/getOperHelpListV2.action";
    /**
     * 新的通用接口地址
     */
    public final static String tongyong="activity/appapi";
    
   
    
    
    /**
     * 查询预留手机号
     */
    public final static String yuliu_phone_get="getUserBindCardPhoneNumber.action";
    
    /**
     * 请求修改预留手机号
     */
    public final static String yuliu_phone_qinqiu="updateBindCardPhoneReq.action";

    /**
     * 确认修改预留手机号
     */
    public final static String yuliu_phone_queren="updateBindCardPhoneConfirm.action";
    
    
    /**
     * 判断用户设置手势密码
     */
    public final static String shoushi_isset="user/selectGesturePassword.action";
    
    
    /**
     * 判断手势密码是否正确
     */
    public final static String shoushi_is="user/equalsGesturePassword.action";

    /**
     * 创建修改手势密码 若用户关闭手势密码调用这个接口密码参数传入1
     */
    public final static String shoushi_cre="user/addGesturePassword.action";
}
