package com.beikbank.android.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


public class SecretUtils {

	private static final String Algorithm = "DESede";
	//private static final String Algorithm1 = "DESede/ECB/PKCS5Padding";
	private static final String PASSWORD_CRYPT_KEY = "8b5705fb1c7c0247a0b0506898526816";

	public static byte[] encryptMode(byte[] src) {
		try {
			DESedeKeySpec dks = new DESedeKeySpec(build3DesKey(PASSWORD_CRYPT_KEY));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, securekey);

			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	 public static byte[] decryptMode(byte[] src) {      
		try {
			//--���ܵ�key
			DESedeKeySpec dks = new DESedeKeySpec(build3DesKey(PASSWORD_CRYPT_KEY));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
			SecretKey securekey = keyFactory.generateSecret(dks);



			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, securekey); 
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	 }

	 public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
		 byte[] key = new byte[24];    //����һ��24λ���ֽ����飬Ĭ�����涼��0
		 byte[] temp = keyStr.getBytes("UTF-8");    //���ַ�ת���ֽ�����
		 if(key.length > temp.length){
			 System.arraycopy(temp, 0, key, 0, temp.length);
		 }else{
			 System.arraycopy(temp, 0, key, 0, key.length);
		 }
		 return key;
	 } 
	 /**
		 * 转换成十六进制字符串
		 * 
		 * @param b
		 * @return
		 * @author SHANHY
		 * @date 2015-8-18
		 */
		public static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";

			for (int n = 0; n < b.length; n++) {
				stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
				if (n < b.length - 1)
					hs = hs + ":";
			}
			return hs.toUpperCase();
		}
}