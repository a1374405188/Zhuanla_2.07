package com.beikbank.android.security;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.util.Log;

import com.beikbank.android.dataparam.UserRecordParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.impl.RequestParamManager;
import com.beikbank.android.utils.MD5;

/**
 * 
 * @author Administrator
 *这个类是一些用于加密的公共方法
 */
public class Jiami 
{  
  /**
   *  if param is json 
   * @return
   */
  public static String getValue(Object head,Object param)throws Exception
  {   
	  TreeMap<String,String> map=new TreeMap<String, String>();
	  TreeMap<String,Object> ht=RequestParamManager.getMapString2(head);
	  TreeMap<String,Object> pt=RequestParamManager.getMapString2(param);
	  
	  JSONObject ErrReport=new JSONObject();
	  JSONObject header=new JSONObject();
	  JSONObject body=new JSONObject();
	  for(String key:ht.keySet())
	  {
		  header.put(key,ht.get(key));
	  }
	  for(String key:pt.keySet())
	  {  
		 
		  
	      body.put(key,pt.get(key));
		  
		  
	  }
	  header.put("sign","");
	  ErrReport.put("header", header);
	  if(param instanceof JSONObject||param instanceof JSONArray)
	  {
		  ErrReport.put("body",param);
	  }
	  else
	  {
	     ErrReport.put("body",body);
	  
	  }
	    //char[] c=ErrReport.toString().toCharArray();
		//Arrays.sort(c);
        //String s=new String(c);
		String sign=getSign(ErrReport.toString());
		header.put("sign", sign);
		ErrReport.put("header", header);
		String s1=ErrReport.toString();
		
		map.put("params",s1);
		
	  return s1;
  }
  
  
  
  public static String getValue2(Object head,Object param)throws Exception
  {   
	  TreeMap<String,String> map=new TreeMap<String, String>();
	  TreeMap<String,Object> ht=RequestParamManager.getMapString2(head);
	  TreeMap<String,Object> pt=RequestParamManager.getMapString2(param);
	  
	  JSONObject ErrReport=new JSONObject();
	  JSONObject header=new JSONObject();
	  JSONObject body=new JSONObject();
	  for(String key:ht.keySet())
	  {
		  header.put(key,ht.get(key));
	  }
	  for(String key:pt.keySet())
	  {
		  body.put(key,pt.get(key));
	  }
	  header.put("sign","");
	  ErrReport.put("header", header);
	  ErrReport.put("body",body);
	  
	  
	    //char[] c=ErrReport.toString().toCharArray();
		//Arrays.sort(c);
        //String s=new String(c);
		String sign=getSign(ErrReport.toString());
		header.put("sign", sign);
		ErrReport.put("header", header);
		String s1=ErrReport.toString();
		
		map.put("report",s1);
		
	  return s1;
  }
  /**
   * 检验签名是否正确
   * @param json
   * @return
   */
  public static boolean checkSign(String json)
  {
	  boolean b=false;
	  try {
		JSONObject jo=new JSONObject(json);
		JSONObject header=jo.getJSONObject("header");
		String sign=header.getString("sign");
		header.put("sign","");
		
		String sign2=getSign(jo.toString());
		if(sign.equals(sign2))
		{
			b=true;
		}
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  return b;
  }
  
  
   /**
    * 得到报文流水
    * @return
    */
   public static String getLiushui()
   {   
	  long l1=100000000000000000L; 
	  long l2=999999999999999999L;
	  long l=BeikBankApplication.mSharedPref.getSharePrefLong("baowen");
	  if(l==0||l==l2)
	  {
		  l=l1;
	  }
	  BeikBankApplication.mSharedPref.putSharePrefLong("baowen",l+1);
	  String s=l1+"";
	  return s;
   }
   public static String getSign(String json)
   {    
	    json=json.replace("\\","");
	    char[] c=json.toLowerCase().toCharArray();
	    long l=c.length;
		Arrays.sort(c);
        String s=new String(c);
       
		return MD5.md5s32(s+ MD5.md5s32("beikbank"));
	}

}
