package com.beikbank.android.jni;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogManager;

import com.beikbank.android.exception.LogHandler;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-19
 * 
 */
public class AnquanManager {
	/**
	 * 验证软件是否为正版
	 * @return
	 */
	public static boolean isZhengBan(Context context)
	{   
		
		return getSign(context);
	}
	private static boolean getSign(Context context) {
		try
		{
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm
                .getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();

        while (iter.hasNext()) {
            PackageInfo info = iter.next();
            String packageName = info.packageName;
            //按包名 取签名
            if (packageName.equals("com.beikbank.android")) {
            	for(int i=0;i<info.signatures.length;i++)
            	{
            		String md5=md5s32(info.signatures[i].toByteArray());
                	md5=md5.toUpperCase();
                	String s2=getMD5();
                	if(md5.equals(s2))
                	{
                		return true;
                	}
                	else
                	{
                		if(i==info.signatures.length-1)
                		{
                			return false;
                		}
                	}
            	}
            } 
        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        return true;
    }
	private static String getMD5()
	{
		String s1="36A2";
		String s8="ACFC";
		String s2="8180";
		String s3="099D";
		String s4="C25D";
		String s7="4DCB";
		String s5="56C0";
		String s6="9A97";
		StringBuilder sb=new StringBuilder();
		sb.append(s1);
		sb.append(s2);
		sb.append(s3);
		sb.append(s4);
		sb.append(s5);
		sb.append(s6);
		sb.append(s7);
		sb.append(s8);
		return sb.toString();
	}
	private static String md5s32(byte[] bs) {
		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bs);
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 比较签名的md5是否正确
	 * @return
	 */
   private static boolean parseSignature(byte[] sign)
   {   
	   try {
		CertificateFactory cer=CertificateFactory.getInstance("X.509");
		X509Certificate xcer=(X509Certificate) cer.generateCertificate(new ByteArrayInputStream(sign));
	
		byte[] bcer=xcer.getEncoded();
		String pubKey = xcer.getPublicKey().toString();
		String pubKey2 = xcer.getSubjectDN().toString();
		System.out.println(pubKey);
	   String s= xcer.getSerialNumber().toString();
	    System.out.println(xcer.getSerialNumber());
		String scer=new String(bcer);
		System.out.println(scer);
	} catch (CertificateException e) 
	{
		e.printStackTrace();
	}
	   return false;
   }
}
