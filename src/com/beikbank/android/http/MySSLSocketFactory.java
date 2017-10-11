package com.beikbank.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.ssl.SSLSocketFactory;

import comc.beikbank.android.R;




import android.content.Context;

public class MySSLSocketFactory {
	  private static final String KEY_STORE_TYPE_BKS = "bks";//证书类型  
	    private static final String KEY_STORE_TYPE_P12 = "PKCS12";//证书类型  
	  
	  
	    private static final String KEY_STORE_PASSWORD = "123456";//证书密码（应该是客户端证书密码）  
	    private static final String KEY_STORE_TRUST_PASSWORD = "123456";//授信证书密码（应该是服务端证书密码）  
	  
	    public static SSLSocketFactory getSocketFactory(Context context) {  
	  
	  
	        InputStream trust_input = context.getResources().openRawResource(R.raw.host2);//服务器授信证书  
	        InputStream client_input = context.getResources().openRawResource(R.raw.host);//客户端证书  
	        try {  
	                   
	                    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
	                    trustStore.load(trust_input, KEY_STORE_TRUST_PASSWORD.toCharArray());   
	                    KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);    
	                    keyStore.load(client_input, KEY_STORE_PASSWORD.toCharArray());  
	                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
	                    trustManagerFactory.init(trustStore); 
	                    
	                    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());  
	                    keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());  
	                   
	                    
//	                    SSLContext sslContext = SSLContext.getInstance("TLS");
//	                    sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());  
//	                    SSLSocketFactory factory = sslContext.getSocketFactory();  
	                    
	                    SSLSocketFactory factory=null;
						try {
							factory = new SSLCustomSocketFactory(trustStore);
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
	                    return factory;  
	                } catch (Exception e) {  
	                            e.printStackTrace();   
	                           return null;  
	                } finally {   
	                           try {  
	                                 trust_input.close();   
	                                 client_input.close();  
	                           } catch (IOException e) {   
	                                e.printStackTrace();    
	                           }   
	                } 
	        
	    }
}
