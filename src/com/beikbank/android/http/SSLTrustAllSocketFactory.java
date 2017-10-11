package com.beikbank.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Certificate;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class SSLTrustAllSocketFactory extends SSLSocketFactory {

	private static final String TAG = "SSLTrustAllSocketFactory";
	private SSLContext mCtx;

	class SSLTrustAllManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			// return null;
			return new X509Certificate[] {};
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			// System.out.println("cert: " + chain[0].toString() + ", authType: "
			// + authType);
		}
	};
    
	public static KeyStore getKS(Context context)
	{  
		
		AssetManager am ;  
		InputStream ins =null;  
		try {    
			    am = context.getAssets();
			    ins = am.open("https123456.cer");  
		        //读取证书  
		        CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");  //问1  
		        java.security.cert.Certificate cer = cerFactory.generateCertificate(ins);  
		        //创建一个证书库，并将证书导入证书库  
		        KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");   //问2  
		        keyStore.load(null, null);  
		        keyStore.setCertificateEntry("trust", cer);  
		        return keyStore;
		} 
		 catch(Exception ex)
		 {
			 
		 }
		finally 
		{  
		        try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		}  
		return null;
	}
	public SSLTrustAllSocketFactory(KeyStore truststore) throws Exception
			 {
		
		
		
		
		super(truststore);
		
		try {
		
			
			
			
			
			
			mCtx = SSLContext.getInstance("TLS");
			mCtx.init(null, new TrustManager[] { new SSLTrustAllManager() },
					null);
			setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Socket createSocket(Socket socket, String host,
			int port, boolean autoClose)
					throws IOException, UnknownHostException {
		return mCtx.getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	@Override
	public Socket createSocket() throws IOException {
		return mCtx.getSocketFactory().createSocket();
	}

	public static SSLSocketFactory getSocketFactory() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory factory = new SSLTrustAllSocketFactory(trustStore);
			return factory;
		} catch (Throwable e) {
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
