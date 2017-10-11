package com.beikbank.android.net;
/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-5
 * 
 */
public interface ErrorCodeInfo {
	String tag="server_error";
	String tag1="";
	/**
	 * 完全不知道的错误
	 */
	String e0="000";  
	/**
	 * 检查更新
	 */
	String e1="100";    
    /**
     * 注册
     */
    String e2="101";
    /**
     * 实名认证
     */
    String e3="102";
    /**
     * 绑定银行卡
     */
    String e4="103";
    /**
     * 登录
     */
    String e5="104";
    /**
     * 购买
     */
    String e6="105";
    /**
     * 赎回
     */
    String e7="106";
    /**
     * 获得交易列表
     */
    String e8="107";
    /**
     * 支持银行列表
     */
    String e9="108";
    /**
     * 基金产品
     */
    String e10="109";
    /**
     * 总资产
     */
    String e11="110";
    /**
     * 收益信息
     */
    String e12="111";
    /**
     * 验证登录密码
     */
    String e13="112";
    /**
     * 修改登录密码
     */
    String e14="113";
    /**
     * 是否实名认证
     */
    String e15="114";
    /**
     * 下载apk 安装apk
     */
    String e16="115";
    /**
     * 发送验证码
     */
    String e17="116";
    /**
     * 找回登录密码验证验证码
     */
    String e18="117";

    /**
     * 验证手机号是否注册过
     */
    String e19="118";
    /**
     * 注册时检查验证码是否正确
     */
    String e20="119";
    /**
     * 设置交易密码
     */
    String e21="120";
    /**
     * 跟新交易密码
     */
    String e22="121";
    /**
     * 投资项目列表
     */
    String e23="122";
    /**
     * 交易记录
     */
    String e24="123";
    /**
     * 得到手续费
     */
    String e25="124";
    /**
     * 意见反馈
     */
    String e26="125";
    /**
     *公告
     */
    String e27="126";
    /**
     *帮助中心
     */
    String e28="127";
    /**
     *安全保障
     */
    String e29="128";
    /**
     * 判断是否验证银行卡
     */
    String e30="129";
    /**
     * 验证银行卡预留手机号
     */
    String e31="130";
//错误代码后3位    
    /**
     * ----------------------------------------------------------------------------------- 服务器返回数据错误
     */   
    /**
     * 未知错误
     */
    String ee0="000";
    /**
     * 某项为空
     */
    String ee1="001";
    /**
     * 某项为空
     */
    String ee2="002";
    /**
     * 某项为空
     */
    String ee3="003";
    /**
     * 某项为空
     */
    String ee4="004";
    /**
     * 某项为空
     */
    String ee5="005";
    /**
     * 某项为空
     */
    String ee6="006";
    /**
     * 某项为空
     */
    String ee7="007";
    /**
     * 如果服务器错误message is null or "" 
     */
    String ee8="008";
    /**
     * 服务器传递回来的不能解析成double
     */
    String ee9="009";
    /**
     * 服务器传递回来的不能解析成double
     */
    String ee10="010";
    /**
     * 服务器传递回来的不能解析成double
     */
    String ee11="011";
    /**
     * 服务器传递回来的不能解析成double
     */
    String ee12="012";
    /**
     * 
     */
    String ee40="040";
    /**
     * 在handler 中处理时出现错误
     */
    String ee41="041";
    /**
     * 在handler 中处理时出现错误2
     */
    String ee42="042";
    /**
     * -----------------------------------------------------------服务器返回json异常
     */
    /**
     * JSONException joson异常
     */
    String ee250="250";
    /**
     * -----------------------------------------------------------服务器网络异常
     */
    /**
     * SocketTimeoutException 服务器无响应
     */
    String ee60="060";
    /**
     * 没有网络
     */
    String ee61="061";
    /**
     * HttpHostConnectException
     */
    String ee62="062";
    /**
     * UnknownHostException
     */
    String ee63="063";
    /**
     * HttpResponseException
     */
    String ee64="064";

    /**
     * RedirectException
     */
    String ee66="066";
    /**
     * 
     */
    String ee67="067";
    /**
     * 
     */
    String ee68="068";
    /**
     * -----------------------------------------------------------客户端网络异常
     */
    /**
     * ClientProtocolException
     */
    String ee600="600";

    /**
     * -----------------------------------------------------------客户端数据库操作异常
     */
    /**
     * 数据库异常
     */
    String ee700="700";
}
