package com.beikbank.android.utils;

public class BeikBankConstant {

	public static final int TYPE_JSONDATA = 0;
	public static final int TYPE_JSONSTATUS = 1;
	public static final int TYPE_JSONMESSAGE = 2;
	/**
	 * 手机号
	 */
	public static final String INTENT_PHONENUMBER="INTENT_PHONENUMBER";
	public static final String INTENT_VERTIFY="INTENT_VERTIFY";
	public static final String INTENT_VERTIFYID="INTENT_VERTIFYID";
	public static final String INTENT_SID="INTENT_SID";
	public static final String INTENT_SUPPORTBANKS="INTENT_SUPPORTBANKS";
	public static final String INTENT_CHANGEGESTURE="INTENT_CHANGEGESTURE";
	public static final String INTENT_CREATEGESTURE="INTENT_CREATEGESTURE";
	public static final String INTENT_PURCHASE="INTENT_PURCHASE";
	public static final String INTENT_AMOUNT="INTENT_AMOUNT";
	public static final String INTENT_RECOMMEND="INTENT_RECOMMEND";
	public static final String INTENT_TRANSACTION="INTENT_TRANSACTION";
	public static final String INTENT_SHOUXUFEI="INTENT_SHOUXUFEI";
	public static final String INTENT_TITLE="INTENT_TITLE";
	public static final String INTENT_RICHCONTENT="INTENT_RICHCONTENT";
	public static final String INTENT_CAPITALINFO="INTENT_CAPITALINFO";
	public static final String INTENT_CAPITAL="INTENT_CAPITAL";
	public static final String INTENT_CAPITALLIST="INTENT_CAPITALLIST";
	public static final String INTENT_PURCHASESUCCESS="INTENT_PURCHASESUCCESS";
	public static final String INTENT_PURCHASEAMOUNT="INTENT_PURCHASEAMOUNT";
	public static final String INTENT_PURCHASENAME="INTENT_PURCHASENAME";
	public static final String INTENT_TOTALCAPITAL="INTENT_TOTALCAPITAL";
	
	public static final String IS_BINDCARD="IS_BINDCARD";
	/**
	 * 是否创建过手势密码 
	 * 
	 */
	public static final String IS_CREATE_GRESTURE="IS_CREATE_GRESTURE";
	
	
	/**
	 * 手势密码
	 * 
	 */
	public static final String GRESTURE="GRESTURE";
	/**
	 * 是否开启手势密码
	 */
	public static final String IS_BINDGESTURE="IS_BINDGESTURE";
//	public static final String IS_PRODUCTDETAIL="IS_PRODUCTDETAIL";
	/**
	 * 是否忘记交易密码
	 */
	public static final String IS_FORGETTRANSACTIONPWD="IS_FORGETTRANSACTIONPWD";
	public static final String IS_FORGETLOGINPWD="IS_FORGETLOGINPWD";
	public static final String IS_FIRSTLAUCH="IS_FIRSTLAUCH";
	/**
	 * 手机号
	 */
	public static final String LOGIN_ACCOUNT="LOGIN_ACCOUNT";
	/**
	 * 密码
	 */
	public static final String LOGIN_PASSWORD="LOGIN_PASSWORD";
	/**
	 * 不是null标示购买后跳转到订单
	 */
	public static final String index7="index7";
	/**
	 * 购买后设置当前页是资产页
	 */
	public static final String is_after_pay="is_after_pay";
	
	/**
	 * 从新打开过一次应用程序
	 */
	public static final String one_open="one_open";
	/**
	 * != null 和 '' 弹出
	 */
	public static final String is_win="is_win";
	/**
	 * 红包跳转过来
	 */
	public static final String is_after_hongbao="is_after_hongbao";
	/**
	 * 更改实名认证剩余次数
	 */
	public static final String renalName="renalName";
	
	public static final String GESTURE_RETRY2="GESTURE_RETRY";
	public static final String GESTURE_OLD2="GESTURE_OLD";
	public static final String VERSION="VERSION";
	public static final String HOME_TYPE="HOME_TYPE";
	/**
	 * 1 huo 2 ding
	 */
	public static final String huo_ding="huo_ding";
	public static final String PRODUCT_RATE="PRODUCT_RATE";
	
	public static final String BROADCAST_REFRESH_RECOMMENDLIST="BROADCAST_REFRESH_RECOMMENDLIST";
	public static final String BROADCAST_REMIND_RECOMMENDLIST="BROADCAST_REMIND_RECOMMENDLIST";
	
	public static final String REFRESHLOGIN="REFRESHLOGIN";
	/**
	 * 第一次登录设置这个标志
	 */
	public static final String FIRST_LOGIN="first_login";
	/**
	 * 第一次登录设置这个标志
	 */
	public static final String first_enter="first_enter";
	
	
	/**
	 * 第一次打开精选
	 */
	public static final String first_enter_jinxuan="first_enter_jinxuan";
	/**
	 * 第一次打开产品
	 */
	public static final String first_enter_chanping="first_enter_chanping";
	/**
	 * 是否设置交易密码
	 */
	public static final String BUY_PASSWD="1";
	
	public static final String FUNDINFO_SID="2";
	
	/**
	 * 登录数据操作成功标志
	 */
	public static final String DO_SUCCESS="DO_SUCCESS1";
	
	public static final String DO_SUCCESS1_VALUE="1";
	public static final String DO_ERROR1_VALUE="-1";
	
	/**
	 * 网络错误
	 */
	public static final String net_error="net_error";
	
	/**
	 * 刷新主界面
	 */
	public static final String re_home="re_home";
	/**
	 * 退出应用
	 */
	public static final String exit="exit";
	
	/**
	 * 网络是否连接
	 */
	public static final String is_net="no_net";
	/**
	 * 是否需要验证银行卡    true 不需要 false 需要
	 */
	public static final String is_check_bink="is_check_bink";
	
	/**
	 * 是否需要获取公告
	 */
	public static final String is_notice="is_notice";
	
	/**
	 * 进行了一次登录
	 */
	public static final String is_log="is_log";
	
	/**
	 * 第一次绑卡时是否需要显示提示  false 需要
	 */
	public static final String is_finrst_bank="is_finrst_bank";
	
	/**
	 * 系统最后一次异常
	 */
	public static final String s_end_error="s_end_error";
	/**
	 * 第一次打开 false 第一次打开  true不是
	 */
	public static final String first_open="first_open";
	
/**
 * 以下是2.0新加的-----------------------------------------------------------------	
 */
	
	/**
	 * 是否实名 0非实名；1实名
	 */
	public static final String is_shimin="is_shimin";
	/**
	 * 是否绑卡0未绑卡；1已绑卡
	 */
	public static final String is_bindbank="is_bindbank";
	/**
	 * 是否设置交易密码0没有；1有
	 */
	public static final String is_jiaoyi="is_jiaoyi";
	/**
	 * 是老用户 0新用户 1老用户
	 */
	public static final String is_olduser="is_olduser";
	/**
	 * 用户code
	 */
	public static final String user_code="user_code";
	/**
	 * 账号id
	 */
	public static final String acc_id="acc_id";
	/**
	 * 账号id
	 */
	public static final String order_id="order_id";
	/**
	 * 用户type
	 */
	public static final String user_type="user_type";
	/**
	 * 用户真实姓名
	 */
	public static final String real_name="real_name";
	/**
	 *  身份证号
	 */
	public static final String id_number="id_number";
	/**
	 *银行卡号
	 */
	public static final String bank="bank";
	
	/**
	 *银行名字
	 */
	public static final String bank_name="bank_name";
	/**
	 *银行小图标
	 */
	public static final String icon_url="icon_url";
	/**
	 *银行大图标
	 */
	public static final String logo_url="logo_url";

	/**
	 *银行卡限额
	 */
	public static final String bank_xiane="bank_xiane";
	/**
	 *银行卡最小限额
	 */
	public static final String bank_min_amount="bank_min_amount";
	/**
	 *银行卡最大限额
	 */
	public static final String bank_max_amount="bank_ax_amount";
	/**
	 *钱包金额
	 */
	public static final String qianbao="qianbao";
	/**
	 *账号id
	 */
	public static final String zhanghao="zhanghao";
	/**
	 *短信支付或者交易密码 1代表跳转交易密码页面0跳转验证码页面
	 */
	public static final String mima_duanxin="mima_duanxin";
	
	
	
	
	/**
	 *支付方式   2：银行卡购买 3：账户购买
	 */
	public static final String pay_type="pay_type";
	
	/**
	 *是否隐藏资产数据 1隐藏 0不隐藏
	 */
	public static final String money_is_yincang="money_is_yincang";
	
}
