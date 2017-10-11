package com.beikbank.android.net.impl;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.RedirectException;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONException;


import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.RequestUrl;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-5
 * 匹配错误代码
 */
public class ErrorCode {
    public String getCode(String url)
    {   
    	String a="-1";
    	if(url.contains(RequestUrl.CHECKUPDATE))
    	{
    		a=ErrorCodeInfo.e1;
    	}
    	else if(url.contains(RequestUrl.CHECKPASSWORD))
    	{
    		a=ErrorCodeInfo.e2;
    	}
    	else if(url.contains(RequestUrl.HANDLEAUTHREALNAME))
    	{
    		a=ErrorCodeInfo.e3;
    	}
    	else if(url.contains(RequestUrl.CHECKBINDBANK))
    	{
    		a=ErrorCodeInfo.e4;
    	}
    	else if(url.contains(RequestUrl.CHECKLOGIN))
    	{
    		a=ErrorCodeInfo.e5;
    	}
    	else if(url.contains(RequestUrl.PAYFORPRODUCT))
    	{
    		a=ErrorCodeInfo.e6;
    	}
    	else if(url.contains(RequestUrl.RETURNMONEY))
    	{
    		a=ErrorCodeInfo.e7;
    	}
    	else if(url.contains(RequestUrl.BANKLIST))
    	{
    		a=ErrorCodeInfo.e9;
    	}
    	else if(url.contains(RequestUrl.GETPRODUCTFUND))
    	{
    		a=ErrorCodeInfo.e10;
    	}
    	else if(url.contains(RequestUrl.TOTALCAPITAL))
    	{
    		a=ErrorCodeInfo.e11;
    	}
    	else if(url.contains(RequestUrl.INCOME))
    	{
    		a=ErrorCodeInfo.e12;
    	}
    	else if(url.contains(RequestUrl.CHECKUSERPASSWORD))
    	{
    		a=ErrorCodeInfo.e13;
    	}
    	else if(url.contains(RequestUrl.UPDATEPASSWORD))
    	{
    		a=ErrorCodeInfo.e14;
    	}
    	else if(url.contains(RequestUrl.CHECKAUTH))
    	{
    		a=ErrorCodeInfo.e15;
    	}
    	else if(url.contains(RequestUrl.GETIDENTIFYCODE))
    	{
    		a=ErrorCodeInfo.e17;
    	}
    	else if(url.contains(RequestUrl.CHECKCODE))
    	{
    		a=ErrorCodeInfo.e18;
    	}
     	else if(url.contains(RequestUrl.CHECKPHONEINFO))
    	{
    		a=ErrorCodeInfo.e19;
    	}
       	else if(url.contains(RequestUrl.CHECKVERTIFY))
    	{
    		a=ErrorCodeInfo.e20;
    	}
    	else if(url.contains(RequestUrl.SETTRANSACTIONPWD))
    	{
    		a=ErrorCodeInfo.e21;
    	}
    	else if(url.contains(RequestUrl.UPDATETRANSACTIONPWD))
    	{
    		a=ErrorCodeInfo.e22;
    	}
    	else if(url.contains(RequestUrl.PROJECTLIST))
    	{
    		a=ErrorCodeInfo.e23;
    	}
    	else if(url.contains(RequestUrl.PROJECTLIST))
    	{
    		a=ErrorCodeInfo.e24;
    	}
    	else if(url.contains(RequestUrl.GET_USER_POUNDAGE))
    	{
    		a=ErrorCodeInfo.e25;
    	}
    	else if(url.contains(RequestUrl.FEEDBACK))
    	{
    		a=ErrorCodeInfo.e26;
    	}
    	return a;
    }
    public static synchronized String getCodeFromException(Exception e)
    {
    	String s="000";
    	if(e instanceof SocketTimeoutException)
    	{
    		s=ErrorCodeInfo.ee60;
    	}
    	else if(e instanceof HttpHostConnectException)
    	{
    		s=ErrorCodeInfo.ee62;
    	}
    	else if(e instanceof UnknownHostException)
    	{
    		s=ErrorCodeInfo.ee63;
    	}
       	else if(e instanceof HttpResponseException)
    	{
    		s=ErrorCodeInfo.ee64;
    	}
    	else if(e instanceof ClientProtocolException)
    	{
    		s=ErrorCodeInfo.ee600;
    	}
    	else if(e instanceof CircularRedirectException)
    	{
    		s=ErrorCodeInfo.ee66;
    	}
    	else if(e instanceof RedirectException)
    	{
    		s=ErrorCodeInfo.ee67;
    	}
    	else if(e instanceof JSONException)
    	{
    		s=ErrorCodeInfo.ee250;
    	}
    	return s;
    }
    
    /**
     * 
     * @param e
     * @return true这个异常需要发送 false不需要发送
     */
    public static synchronized boolean getFromException(Exception e)
    {
    	boolean b=false;
    	if(e instanceof SocketTimeoutException)
    	{
    		
    	}
    	else if(e instanceof HttpHostConnectException)
    	{
    		
    	}
    	else if(e instanceof UnknownHostException)
    	{
    		
    	}
       	else if(e instanceof HttpResponseException)
    	{
    		
    	}
    	else if(e instanceof ClientProtocolException)
    	{
    		
    	}
    	else if(e instanceof CircularRedirectException)
    	{
    		
    	}
    	else if(e instanceof RedirectException)
    	{
    		
    	}
    	else if(e instanceof JSONException)
    	{
    		
    	}
    	else
    	{
    		b=true;
    	}
    	return b;
    }
}
