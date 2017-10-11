package com.beikbank.android.jni;
/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-4
 * 
 */
public class JniManager {
	/**
	 * 得到3des加密 解密需要的密钥
	 * @return
	 */
   public static native byte[] getKey();
   
   
   static 
   {
	   System.loadLibrary("keymanager");
   }
}
