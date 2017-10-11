package com.beikbank.android.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;


import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;



import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.fragment.BeikBankApplication;

import comc.beikbank.android.R;

import android.content.Context;
import android.util.Log;

/**
 * java ssl 验证
 * @author Administrator
 *
 */
public class SSLCustomSocketFactory3 extends SSLSocketFactory 
{ 
public SSLCustomSocketFactory3(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(truststore);
		
	}

private static final String KEY_STORE_TYPE_BKS = "BKS";//证书类型 固定值
private static final String KEY_STORE_TYPE_P12 = "PKCS12";//证书类型 固定值

private static final String KEY_STORE_CLIENT_PATH = "client.bks";//客户端要给服务器端认证的证书
private static final String KEY_STORE_TRUST_PATH = "truststore.bks";//客户端验证服务器端的证书库


/**
 * 真实环境
 */
private static final String KEY_STORE_CLIENT_PATH2 = "client_zs.bks";//客户端要给服务器端认证的证书
private static final String KEY_STORE_TRUST_PATH2 = "truststore_zs.bks";//客户端验证服务器端的证书库
private static final String KEY_STORE_PASSWORD = "123456";// 客户端证书密码
private static final String KEY_STORE_TRUST_PASSWORD = "123456";//客户端证书库密码

/**
 * 获得KeyStore.
 * @param keyStorePath
 *            密钥库路径
 * @param password
 *            密码
 * @return 密钥库
 * @throws Exception
 */
public static KeyStore getKeyStore(String password, String keyStorePath)
        throws Exception {
    // 实例化密钥库
    KeyStore ks = KeyStore.getInstance("BKS");
    // 获得密钥库文件流
    InputStream is = context.getResources().getAssets().open(keyStorePath);;
    // 加载密钥库
    ks.load(is, password.toCharArray());
    // 关闭密钥库文件流
    is.close();
    return ks;
}
public static Context context;
public static SSLContext getSSLContext2(Context con) throws Exception
{
	context=con;
	String password=KEY_STORE_PASSWORD;
	String keyStorePath=null;
	String trustStorePath=null;
	if(!SystemConfig.kHOST_URL_BASE.contains("www.beikbank.com"))
    {
    	
		keyStorePath=KEY_STORE_CLIENT_PATH;
    	trustStorePath=KEY_STORE_TRUST_PATH;
       
    }
    else
    {
    	keyStorePath=KEY_STORE_CLIENT_PATH2;
    	trustStorePath=KEY_STORE_TRUST_PATH2;
       
    	
    }
	
	  // 实例化密钥库
    KeyManagerFactory keyManagerFactory = KeyManagerFactory
            .getInstance(KeyManagerFactory.getDefaultAlgorithm());
    // 获得密钥库
    KeyStore keyStore = getKeyStore(password, keyStorePath);
    // 初始化密钥工厂
    keyManagerFactory.init(keyStore, password.toCharArray());

    // 实例化信任库
    TrustManagerFactory trustManagerFactory = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());
    // 获得信任库
    KeyStore trustStore = getKeyStore(password, trustStorePath);
    // 初始化信任库
    trustManagerFactory.init(trustStore);
    // 实例化SSL上下文
    SSLContext ctx = SSLContext.getInstance("TLS");
    // 初始化SSL上下文
    ctx.init(keyManagerFactory.getKeyManagers(),
            trustManagerFactory.getTrustManagers(), null);
    // 获得SSLSocketFactory
    return ctx;


}
/**
 * 获得SSLSocketFactory.
 * @param password
 *            密码
 * @param keyStorePath
 *            密钥库路径
 * @param trustStorePath
 *            信任库路径
 * @return SSLSocketFactory
 * @throws Exception
 */
private static SSLContext getSSLContext(String password,
        String keyStorePath, String trustStorePath) throws Exception {
    // 实例化密钥库
    KeyManagerFactory keyManagerFactory = KeyManagerFactory
            .getInstance(KeyManagerFactory.getDefaultAlgorithm());
    // 获得密钥库
    KeyStore keyStore = getKeyStore(password, keyStorePath);
    // 初始化密钥工厂
    keyManagerFactory.init(keyStore, password.toCharArray());

    // 实例化信任库
    TrustManagerFactory trustManagerFactory = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());
    // 获得信任库
    KeyStore trustStore = getKeyStore(password, trustStorePath);
    // 初始化信任库
    trustManagerFactory.init(trustStore);
    // 实例化SSL上下文
    SSLContext ctx = SSLContext.getInstance("TLS");
    // 初始化SSL上下文
    ctx.init(keyManagerFactory.getKeyManagers(),
            trustManagerFactory.getTrustManagers(), null);
    // 获得SSLSocketFactory
    return ctx;
}


}
