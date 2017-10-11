package com.beikbank.android.net;

import java.util.TreeMap;
import java.util.concurrent.Semaphore;

import com.beikbank.android.data.CommonInfo_data;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Notice;
import com.beikbank.android.data.PlayFund_data;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.Remdom;
import com.beikbank.android.data.Remdom_data;
import com.beikbank.android.dataparam.CheckPhoneNumberParam;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.QianbaoParam2;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.net.impl.IBusinessImpl;
 /**
 *<p>copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 *交易的接口
 *</p>
 *<p>
 *  usage:  IBusiness im=(IBusiness)NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
 *</p>
 **/
public interface IBusiness {
	int POST=1;
	int GET=2;
	/**
	 * 得到安全保障和帮助中心数据
	 * @param returnClass HelpAndSafety_data
	 * @param url  请求的url 可以为空
	 * @param paramClass HelpAndSafetyParam
	 * @return
	 * @throws Exception
	 */
	Object helpandSafety(Class<?> returnClass,String url,Object paramClass)throws Exception;
	/**
	 * 实名认证
	 * @param returnClass RealName_data
	 * @param url  请求的url 可以为空
	 * @param paramClass RealNameParam.java
	 * @return
	 * @throws Exception
	 */
	Object authenticateRealName(Class<?> returnClass,String url,Object paramClass)throws Exception;
	/**
	 * 绑定银行卡
	 * @param returnClass BindBankCard_data.class
	 * @param url 请求的url 可以为空
	 * @param paramClass BindBankCardParam
	 * @return
	 * @throws Exception
	 */
    Object bindBankCard(Class<?> returnClass,String url,Object paramClass)throws Exception;
	/**
	 * 取现
	 * @see Remdom_data
	 * @param returnClass Remdom_data.class 不能 null
	 * @param url 请求的url 可以为空
	 * @param paramClass  RemmoneyParam 不能 null
	 * @return  Remdom_data
	 * @throws Exception
	 */
     Object redeemMoney(Class<?> returnClass,String url,Object paramClass) throws Exception;
     
     /**
      * 注册
      * @param returnClass  UserInfo<?>
      * @param url  请求的url 可以为空
      * @param paramClass  RegsiterParam
      * 
      * @return
      * @throws Exception
      */
     Object register(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 登录 
      * @param returnClass LoginInfo<?>
      * @param url  请求的url 可以为空
      * @param paramClass  LoginParam
      * 
      * @return
      * @throws Exception
      */
     Object login(Class<?> returnClass,String url,Object paramClass) throws Exception;
     
     /**
      * 取现得到手续费
      * @param Poundage 返回对象
      * @param url  请求的url 可以为空
      * @param paramClass  TotalMoneyParam
      * @return Poundage
      * @throws Exception
      */
     Object getUserPoundage(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 判断手机号是否注册过
      * @param returnClass  CheckPhone_data。class
      * @param url         url 请求的url 可以为空
      * @param paramClass   CheckPhoneNumberParam
      * @return
      * @throws Exception
      */
     Object checkPhoneNumber(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 返回基金的信息
      * @param returnClass FundInfo_data<?>
      * @param url  url 请求的url 可以为空
      * @param paramClass null
      * @param index  这里这个参数没有使用
      * @return
      * @throws Exception
      */
     Object getProductFundList(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 发送get请求
      * @param returnClass Class<?> 不能为空
      * @param url  请求的url 不能为空
      * @param map  请求的参数 为null
      * @return FundInfo_data
      * @throws Exception
      */
     Object getGetData(Class<?> returnClass,String url,TreeMap<String,String> map)throws Exception;
     /**
      * 发送post请求
      * @param returnClass  Class<?> 不能为空
      * @param url  请求的url 不能为空
      * @param map  请求的参数 可以为null
      * @return
      * @throws Exception
      */
     Object getPostData(Class<?> returnClass, String url,TreeMap<String,String> map)throws Exception;
 	/**
 	 * 检查版本更新
 	 * @param returnClass CheckUpdate_data.class
 	 * @param url  请求的url 可以为空
 	 * @param paramClass CheckUpdateParam
 	 * @return
 	 * @throws Exception
 	 * 检查版本更新
 	 */
     Object  checkUpdate(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 一键反馈
      * @param returnClass OneKey_data.class
      * @param url  请求的url 可以为空
      * @param paramClass  OneKeyParam
      * @return
      * @throws Exception
      */
     Object  oneKey(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 得到支持银行列表
      * @param returnClass   BankList_data.class
      * @param url         请求的url 可以为空
      * @param paramClass  BankListParam
      * @return
      * @throws Exception
      */
     Object  getBankList(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 得到总资产
      * @param returnClass TotalMoney_data.class
      * @param url  请求的url 可以为空
      * @param paramClass TotalMoneyParam
      * @return
      * @throws Exception
      */
     Object getTotalMoney(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 资产详细信息
      * @param returnClass MoneyInfo_data.class
      * @param url  请求的url 可以为空
      * @param paramClass MoneyInfoParam.java
      * @return
      * @throws Exception
      */
     Object getMoneyInfo(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 检查登录密码是否正确
      * @param returnClass
      * @param url
      * @param paramClass CheckLoginPasswdParam
      * @return
      * @throws Exception
      */
     Object checkLoginPasswd(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 修改登录密码
      * @param returnClass
      * @param url
      * @param paramClass UpdateLoginPasswdParam
      * @return
      * @throws Exception
      */
     Object updateLoginPasswd(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 是否已经实名认证
      * @see  IsReanlName_data
      * @see  RealNameParam
      * @param returnClass IsReanlName_data.class
      * @param url
      * @param paramClass RealNameParam
      * @return
      * @throws Exception
      * 
      */
     Object isName(Class<?> returnClass,String url,Object paramClass)throws Exception;
     
     /**
      * 发送验证码
      * @see  SendCode_data
      * @see  SendCodeParam
      * @param returnClass SendCode_data.class
      * @param url
      * @param paramClass SendCodeParam
      * @return
      * @throws Exception
      * 
      */
     Object SendCode(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 检查验证码是否正确
      * @see  CheckCode_data
      * @see  CheckCodeParam
      * @param returnClass CheckCode_data
      * @param url
      * @param paramClass CheckCodeParam
      * @return
      * @throws Exception
      * 
      */
     Object CheckCode(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 验证银行预留手机号检查验证码是否正确
      * @see  CheckCode_data
      * @see  CheckCodeParam
      * @param returnClass CheckCode_data
      * @param url
      * @param paramClass CheckCodeParam3
      * @return
      * @throws Exception
      * 
      */
     Object CheckCode3(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 找回登录密码时检查验证码是否正确
      * @see  CheckCode2_data
      * @see  CheckCode2Param
      * @param returnClass CheckCode2_data.class
      * @param url
      * @param paramClass CheckCode2Param
      * @return
      * @throws Exception
      * 
      */
     Object CheckCode2(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 设置交易密码
      * @see  Base_data
      * @param returnClass Base_data
      * @param url
      * @param paramClass SetTPasswdParam
      * @return
      * @throws Exception
      * 
      */
     Object setTPasswd(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 修改交易密码
      * @see  Base_data
      * @see  UpdateTPasswdParam
      * @param returnClass Base_data
      * @param url
      * @param paramClass UpdateTPasswdParam
      * @return
      * @throws Exception
      * 
      */
     Object updateTPasswd(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 购买
      * @see  PlayFund_data
      * @see  RemmoneyParam
      * @param returnClass  PlayFund_data
      * @param url
      * @param paramClass  RemmoneyParam
      * @return
      * @throws Exception
      * 
      */
     Object playFund(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 投资项目列表
      * @see  ProjectList_data
      * @see  ProjectListParam
      * @param returnClass  ProjectList_data.class
      * @param url
      * @param paramClass  ProjectListParam
      * @return
      * @throws Exception
      * 
      */
     Object projectList(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 交易记录列表
      * @see  TranListParam
      * @see  TranListParam
      * @param returnClass  TranList_Data
      * @param url
      * @param paramClass  TranListParam
      * @return
      * @throws Exception
      * 
      */
     Object TranList(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 意见反馈
      * @see  Base_data
      * @see  OneKeyParam2
      * @param returnClass  Base_data.class
      * @param url
      * @param paramClass  OneKeyParam2
      * @return
      * @throws Exception
      * 
      */
     Object OneKey(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 判断是否需要验证银行卡
      * @see  IsChekBnakParam
      * @see  IsChekBnak_data
      * @param returnClass  IsChekBnak_data.class
      * @param url
      * @param paramClass  IsChekBnakParam
      * @return
      * @throws Exception
      * 
      */
     Object IsChekBnak(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 验证预留手机号并发送验证码
      * @see  CheckAndSendParam
      * @see  CheckAndSend_data
      * @param returnClass  CheckAndSend_data.class
      * @param url
      * @param paramClass  CheckAndSendParam
      * @return
      * @throws Exception
      * 
      */
     Object CheckAndSend(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 获取公告
      * @see  Notice
      * @see  Notice_data
      * @param returnClass  Notice_data.class
      * @param url
      * @param paramClass  null
      * @return
      * @throws Exception
      * 
      */
     Object getNotice(Class<?> returnClass,String url,Object paramClass)throws Exception;
     
     
     /**
      * 得到购买上限
      * @see  FundLimit
      * @see  FundLimit_data
      * @param returnClass  FundLimit_data.class
      * @param url
      * @param paramClass  FundLimit
      * @return
      * @throws Exception
      * 
      */
     Object getFundLimit(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * @see  MessageParam
      * @see  Message_data
      * 得到消息
      * @param returnClass Message_data
      * @param url
      * @param paramClass MessageParam
      * @return
      * @throws Exception
      */
     Object getMessage(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * @see  MessageParam
      * @see  Message_data
      * 将消息标记为已读
      * @param returnClass Base_data.class
      * @param url
      * @param paramClass MessageReadParam
      * @return
      * @throws Exception
      */
     Object readMessage(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * @see  MessageParam
      * @see  Message_data
      * 删除消息
      * @param returnClass Base_data.class
      * @param url
      * @param paramClass MessageReadParam
      * @return
      * @throws Exception
      */
     Object delMessage(Class<?> returnClass,String url,Object paramClass)throws Exception;
     /**
      * 得到广告页
      * @param returnClass Action_data
      * @param url
      * @param paramClass  null
      * @return
      * @throws Exception
      */
     Object getActivity(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到活动列表
      * @param returnClass Actionlist_data
      * @param url
      * @param paramClass null
      * @return
      * @throws Exception
      */
     Object getActionList(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到活动详情
      * @param returnClass  ActionInfo_data
      * @param url
      * @param paramClass ActionInfoParam
      * @return
      * @throws Exception
      */
     Object getActionInfo(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 获得系统通知
      * @param returnClass SysNotice_data
      * @param url
      * @param paramClass SysNoticeParam
      * @return
      * @throws Exception
      */
     Object getSysNotice(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 获得用户收益列表
      * @param returnClass UserRecord_data
      * @param url
      * @param paramClass  UserRecordParam
      * @return
      * @throws Exception
      */
     Object getUserRecord(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 获得用户资产信息
      * @param returnClass UserCapital_data
      * @param url
      * @param paramClass  TotalMoneyParam
      * @return
      * @throws Exception
      */
     Object getUserCapital(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 获得用户资产信息
      * @param returnClass UserCapital2_data
      * @param url
      * @param paramClass  TotalMoneyParam
      * @return
      * @throws Exception
      */
     Object getUserCapital2(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 获得用户资产信息
      * @param returnClass {@link CommonInfo_data}
      * @param url
      * @param paramClass  TotalMoneyParam
      * @return
      * @throws Exception
      */
     Object getCommonInfo(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到定期产品详细信息
      * @param returnClass DingqiPI_data
      * @param url
      * @param paramClass  DingqiPIParam
      * @return
      * @throws Exception
      */
     Object getDingqiPI(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到定期产品列表
      * @param returnClass DingqiP_data
      * @param url
      * @param paramClass  DingqiPParam
      * @return
      * @throws Exception
      */
     Object getDingqiP(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 请求交易
      * @param returnClass ReqPayforP_Data
      * @param url
      * @param paramClass  ReqPayforPParam
      * @return
      * @throws Exception
      */
     Object reqPayforP(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 确认交易
      * @param returnClass ConfirmPay_data
      * @param url
      * @param paramClass  ConfirmPayforPParam
      * @return
      * @throws Exception
      */
     Object confirmPayforP(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 获取用户资产详情
      * @param returnClass UserCapitalInfo_data
      * @param url
      * @param paramClass  TotalMoneyParam
      * @return
      * @throws Exception
      */
     Object getUserCapitalInfo(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 绑卡并验证银行预留手机号
      * @param returnClass CheckBankAndPhone_data
      * @param url
      * @param paramClass  CheckBankAndPhoneParam
      * @return
      * @throws Exception
      */
     Object checkBankAndPhone(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 绑卡并验证银行预留手机号验证码
      * @param returnClass CheckBankAndPhoneR_data
      * @param url
      * @param paramClass  CheckBankAndPhoneRParam
      * @return
      * @throws Exception
      */
     Object checkBankAndPhoneR(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到定期产品p2p债券列表
      * @param returnClass DingqiProject_data
      * @param url
      * @param paramClass  DingqiPIParam
      * @return
      * @throws Exception
      */
     Object getDingqiProject(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到qq号
      * @param returnClass {@link Config_data}
      * @param url
      * @param paramClass null
      * @return
      * @throws Exception
      */
     Object getConfigQQ(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 钱包请求充值
      * @param returnClass  {@link Qianbao2_data}
      * @param url
      * @param paramClass {@link QianbaoParam1}
      * @return
      * @throws Exception
      */
     Object qianbao1(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 钱包充值确认
      * @param returnClass {@link Qianbao1_data}
      * @param url
      * @param paramClass {@link QianbaoParam2}
      * @return
      * @throws Exception
      */
     Object qianbao2(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 钱包提现请求
      * @param returnClass {@link Qianbao2_data}
      * @param url
      * @param paramClass {@link QianbaoParam1}
      * @return
      * @throws Exception
      */
     Object qianbao3(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 钱包提现确认
      * @param returnClass  {@link Qianbao1_data}
      * @param url
      * @param paramClass {@link QianbaoParam2}
      * @return
      * @throws Exception
      */
     Object qianbao4(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 钱包提现交易记录
      * @param returnClass  {@link Qianbao3_data}
      * @param url
      * @param paramClass {@link QianbaoParam3}
      * @return
      * @throws Exception
      */
     Object qianbao5(Class<?> returnClass,String url,Object paramClass) throws Exception;
     /**
      * 得到钱包余额
      * @param returnClass  {@link Qianbao4_data}
      * @param url
      * @param paramClass {@link TotalMoneyParam}
      * @return
      * @throws Exception
      */
     Object qianbao6(Class<?> returnClass,String url,Object paramClass) throws Exception;
}
