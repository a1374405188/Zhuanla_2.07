package com.beikbank.android.net;

import java.util.TreeMap;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.utils.Utils;

public class HttpManager extends Thread{
	 String name;
	 String url;
	 String params;
	 WebView wv;
	 int index;
	 /**
	  * 
	  * @param url
	  * @param params
	  * @param name
	  * @param wv
	  * @param index 1 get 2 post
	  */
	 public HttpManager(String url,String params,String name,WebView wv,int index)
	 {
		 this.index=index;
		 this.name=name;
		 this.params=params;
		 this.url=url;
		 this.wv=wv;
	 }
	 
	 @Override
	public void run() 
	 {
		if(index==1)
		{
			get();
		}
		else
		{
			post();
		}
	 }
    private void get()
    {
    	TreeMap<String,String> map=getParm(params);
    	map.put("sign",Utils.getSign(map));
    	url=addMapToUrl(url, map);
    	HttpUtil hu=new HttpUtil(null);
    	String responce=null;
    	String s1="error";
    	try {
    		responce = hu.getRequest(url);
    		if(SystemConfig.isDebug())
    		{
    			Log.d("json",responce);
    		}
    		s1="javascript:"+name+"('"+responce+"')";
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	wv.loadUrl(s1);
//    	Message msg=new Message();
//    	msg.what=1;
//    	msg.obj=s1;
//    	handler.sendMessage(msg);
    }
    private void post()
    {
    	 TreeMap<String,String> map=getParm(params);
 		map.put("sign",Utils.getSign(map));
 		HttpUtil hu=new HttpUtil(null);
 		String responce=null;
 		String s1="error";
 		try {
 			responce=hu.postRequest(url,map);
 			if(SystemConfig.isDebug())
 			{
 				Log.d("json",responce);
 			}
 		    s1="javascript:"+name+"('"+responce+"')";
    		
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		wv.loadUrl(s1);
 		
    }
	/**
	   * 将参数解析成集合
	   * @param params 如 param1=1&param2=2
	   * @return
	   */
	 private TreeMap<String,String> getParm(String params)
	 {
		 if(params==null||params.equals(""))
		 {
			 return null;
		 }
		 String s1[]=params.split("&");
		 TreeMap<String,String> map=new TreeMap<String, String>();
		 String s2[]=null;
		 for(int i=0;i<s1.length;i++)
		 {
			 s2=s1[i].split("=");
			 map.put(s2[0],s2[1]);
		 }
		 return map;
	 }
	 private String addMapToUrl(String url,TreeMap<String,String> map)
	 {   
	 	if(map==null)
	 	{
	 		return url;
	 	}
	 	int a=1;
	 	for(String key : map.keySet())
			{   
	 		if(a==1)
	 		{
	 			url+="?"+key+"="+map.get(key);
	 			a=-1;
	 		}
	 		else
	 		{
	 			url+="&"+key+"="+map.get(key);
	 		}
			}
	 	return url;
	 }
}
