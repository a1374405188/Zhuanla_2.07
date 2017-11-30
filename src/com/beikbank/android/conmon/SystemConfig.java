package com.beikbank.android.conmon;

import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
 /**
 *copyright ygh
 *email: 1374405188@qq.com
 *2014-12-4
 **/
public class SystemConfig {
	/**
	 * 1公共云  测试2 金融云 内测3存管
	 */
    public static int index=3;
    public static String huodong_url;
    public static String base_url = "http://114.55.5.66";
    static
    {
    	if(index==1)
    	{
    		base_url = "http://114.55.5.66";
    		huodong_url="http://118.178.134.251:8088/";
    		//base_url = "http://172.16.100.199";
    	}
    	else if(index==2)
    	{
    		base_url = "http://101.37.3.83";
    		huodong_url="http://101.37.3.83:5062/";
    	}
    	else if(index==3)
    	{
//    		base_url = "http://101.37.3.83";
//    		huodong_url="http://101.37.3.83:13022/";
    		base_url = "http://app.beikbank.com";
    		huodong_url="http://app.beikbank.com:13122/";
    	}
    	
    	
    }
	/**
	 * 
	 * @return
	 */
    public static boolean isDebug()
    {
    	return true;
    	
    }
	//real
    public static String kHOST_URL_BASE = "https://www.beikbank.com:8445/";
    public static String kHOST_URL8443_BASE = "https://www.beikbank.com:8445/";
    
    
    
    
   
  // public static String base_url = "http://101.37.3.83";
    
//    public static String kHOST_URL_BASE = "http://www.beikbank.com/";
//    public static String kHOST_URL8443_BASE = "https://www.beikbank.com:8445/";
//	public static String kHOST_URL = "http://www.beikbank.com/beikbankapp/appapi/";
//	public static String kHOST_URL8443 = "https://www.beikbank.com/beikbankapp/appapi/";
//		
//    public static String kHOST_URL_BASE = "http://tmp.zhuanlalicai.com/";
//	public static String kHOST_URL8443_BASE = "https://tmp.zhuanlalicai.com/";
	//test
//    public static String kHOST_URL_BASE = "http://218.108.7.222:10042/";
//    public static String kHOST_URL8443_BASE = "http://218.108.7.222:10042/";
    
//    public static String kHOST_URL_BASE = "http://172.16.100.139:8081";
//    public static String kHOST_URL8443_BASE = "https://218.75.37.122:8809/";
//    public static String kHOST_URL_BASE = "http://172.16.100.14:8080/";
//    public static String kHOST_URL8443_BASE = "https://172.16.100.14:8081/";
    
    
    
//    public static String kHOST_URL_BASE = "http://172.16.100.15:8080/";
//    public static String kHOST_URL8443_BASE = "https://172.16.100.15:8443/";
//    
//    public static String kHOST_URL_BASE = "http://121.196.236.115:8080/";
//    public static String kHOST_URL8443_BASE = "https://121.196.236.115:8443/";
//  public static String kHOST_URL_BASE = "http://114.55.51.29/";
//  public static String kHOST_URL8443_BASE = "https://114.55.51.29:8443/";

//    
    
    

    
	public static String kHOST_URL = kHOST_URL_BASE+"beikbankapp/appapi/";
	public static String kHOST_URL8443 = kHOST_URL8443_BASE+"beikbankapp/appapi/";
	
	public static String kHOST_URL2 = kHOST_URL_BASE;

	
	/**
	 * log file path
	 */
	public static String LOG_FILE_PATH="/sdcard/beike/log/";
	public static String LOG_FILE="zhuangla.log";
	/**
	 * app source
	 */
	public static String SOURCES_CODE=Cancal.CODE_OTHER2;
	
	/**
	 * db
	 */
	public static String DATA_PATH="/data/data/";
	public static String DATA_file="/beike2.db";
	static
	{
		
		String phonenumber="/a"+BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
		DATA_file=phonenumber+"beike2.db";
		
		
		if(!kHOST_URL2.contains("10032"))
		{   
			
			kHOST_URL2="https://www.beikbank.com:8445/";
		}
		
	}
	/**
	 * 
	 */
	public static final int DATA_VERSION=4;
	
	/**
	 * gongxiang shuju
	 */
	public static final String PREF_NAME = "beik";
	
	
	
}
