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


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;



import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.fragment.BeikBankApplication;
import coma.beikbank.android.R;



import android.content.Context;
import android.util.Log;


public class SSLCustomSocketFactory2 extends SSLSocketFactory 
{ 
public SSLCustomSocketFactory2(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(truststore);
		
	}

private static final String KEY_STORE_TYPE_BKS = "BKS";//证书类型 固定值
private static final String KEY_STORE_TYPE_P12 = "PKCS12";//证书类型 固定值

private static final String KEY_STORE_CLIENT_PATH = "client3.bks";//客户端要给服务器端认证的证书
private static final String KEY_STORE_TRUST_PATH = "truststore3.bks";//客户端验证服务器端的证书库


/**
 * 真实环境
 */
private static final String KEY_STORE_CLIENT_PATH2 = "client_zs.bks";//客户端要给服务器端认证的证书
private static final String KEY_STORE_TRUST_PATH2 = "truststore_zs.bks";//客户端验证服务器端的证书库
private static final String KEY_STORE_PASSWORD = "123456";// 客户端证书密码
private static final String KEY_STORE_TRUST_PASSWORD = "123456";//客户端证书库密码

/**
 * 获取SslSocketFactory
 *
 * @param context 上下文
 * @return SSLSocketFactory
 */
public static SSLSocketFactory getSslSocketFactory(Context context) {
    try {
        // 服务器端需要验证的客户端证书
        KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_BKS);
        // 客户端信任的服务器端证书
        KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_BKS);
       
        InputStream ksIn=null;
        InputStream tsIn =null;
        if(!SystemConfig.kHOST_URL_BASE.contains("www.beikbank.com"))
        {
        	
        
            ksIn = context.getResources().getAssets().open(KEY_STORE_CLIENT_PATH);
            tsIn = context.getResources().getAssets().open(KEY_STORE_TRUST_PATH);
        }
        else
        {
        	 ksIn = context.getResources().getAssets().open(KEY_STORE_CLIENT_PATH2);
             tsIn = context.getResources().getAssets().open(KEY_STORE_TRUST_PATH2);
        	
        	
        }
        
        try {
            keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());
            trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ksIn.close();
            } catch (Exception ignore) {
            }
            try {
                tsIn.close();
            } catch (Exception ignore) {
            }
        }
             return new SSLSocketFactory(keyStore, KEY_STORE_PASSWORD, trustStore);
    } catch (Exception e) {
        e.printStackTrace();
    } 
    return null;
}

/**
 * 获取SSL认证需要的HttpClient
 *
 * @param context 上下文
 * @param port    端口号
 * @return HttpClient
 */
public static HttpClient getSslSocketFactoryHttp(Context context, int port) {
    HttpClient httpsClient = new DefaultHttpClient();
    SSLSocketFactory sslSocketFactory = getSslSocketFactory(context);
    if (sslSocketFactory != null) {
        Scheme sch = new Scheme("https", sslSocketFactory, port);
        httpsClient.getConnectionManager().getSchemeRegistry().register(sch);
    }
    return httpsClient;
}
}
