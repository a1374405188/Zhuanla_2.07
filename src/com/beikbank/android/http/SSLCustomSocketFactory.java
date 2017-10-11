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



import org.apache.http.conn.ssl.SSLSocketFactory;

import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.fragment.BeikBankApplication;

import comc.beikbank.android.R;

import android.content.Context;
import android.util.Log;



public class SSLCustomSocketFactory extends SSLSocketFactory {  
    private static final String TAG = "SSLCustomSocketFactory";  
  
    private static final String KEY_PASS = "123456";  
  
    public SSLCustomSocketFactory(KeyStore trustStore) throws Throwable {  
        super(trustStore);  
    }  
  
    public static SSLSocketFactory getSocketFactory(Context context) {  
        try {  
        	 
        	
        	
            InputStream ins = context.getResources().openRawResource(R.raw.host);  
  
            KeyStore trustStore = KeyStore.getInstance("BKS");  
            try {  
                trustStore.load(ins, KEY_PASS.toCharArray());  
            }  
            finally {  
                ins.close();  
            }  
            SSLSocketFactory factory = new SSLCustomSocketFactory(trustStore); 
            factory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER); 
            return factory;  
        } catch (Throwable e) {  
            Log.d(TAG, e.getMessage());  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public static SSLSocketFactory getSocketFactory2(Context context) {
        SSLSocketFactory socketFactory=null;
        try {
            // 服务器端需要验证的客户端证书
        	KeyStore    keyStore = KeyStore.getInstance("PKCS12");

            // 客户端信任的服务器端证书
        	KeyStore    trustStore = KeyStore.getInstance("bks");

          //.p12文件  
            InputStream ksIn = context.getResources().openRawResource(R.raw.host2);
//.bks文件
            InputStream tsIn = context.getResources().openRawResource(R.raw.host);
            try {
                keyStore.load(ksIn, "123456".toCharArray());
                trustStore.load(tsIn, "123456".toCharArray());
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

            socketFactory = new SSLSocketFactory(keyStore,"123456", trustStore);
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  socketFactory;
    }
    public static SSLSocketFactory test(Context context)
    {
    	InputStream ins = null;
    	try {
			ins = context.getAssets().open("host2.p12");
		
         //  CertificateFactory cerFactory = CertificateFactory
         //     .getInstance("X.509");
        //  Certificate cer = cerFactory.generateCertificate(ins);
        KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
        keyStore.load(ins,"123456".toCharArray());
       // keyStore.setCertificateEntry("trust", cer);

      SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);
    
      return socketFactory;
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //下载的证书放到项目中的assets目录中
    	return null;
    }
    
    
    
    
   
}  
