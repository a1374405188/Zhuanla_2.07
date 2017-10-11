package com.beikbank.android.utils;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import com.beikbank.android.jni.JniManager;

import android.util.Base64;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-3
 * 该类用于文本加密和解密
 */
public class BKDes {
	/**
	 * 加密并且编码为base64
	 * @param text
	 * @return
	 */
	public static String encrypToBase64(String text)
	{
		byte b[]=encryptMode(text);
		String base64=Base64.encodeToString(b,Base64.DEFAULT);
		return base64;
	}
	/**
	 * 将base64的加密文本解密
	 * @param text
	 * @return
	 */
	public static String decryptBase64(String text)
	{
		byte b[]=Base64.decode(text, Base64.DEFAULT);
		byte b2[]=decryptMode(b);
		return new String(b2);
	}
	/**
	 * 采用des加密
	 * @param text
	 * @return
	 */
   public static byte[] encryptMode(String text)
   {   
	   try {
		    DESedeKeySpec dks = new DESedeKeySpec(getKey());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(Cipher.ENCRYPT_MODE, securekey);

			return c1.doFinal(text.getBytes());
	    } 
	   catch (Exception e) 
	  {
		  e.printStackTrace();
	  }
	   
	   return null;
   }
   /**
    * 采用des解密
    * @param text
    * @return
    */
   public static  byte[] decryptMode(byte[] text)
   {   
	   try {
		    DESedeKeySpec dks = new DESedeKeySpec(getKey());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(Cipher.DECRYPT_MODE, securekey);
			
			return c1.doFinal(text);
	    } 
	   catch (Exception e) 
	  {
		  e.printStackTrace();
	  }
	   return null;
   }
   private static byte[] key;
   private static  byte[] getKey()
   {   
	   if(key==null)
	   {
		   key=JniManager.getKey();
	   }
	   return key;
   }
}
