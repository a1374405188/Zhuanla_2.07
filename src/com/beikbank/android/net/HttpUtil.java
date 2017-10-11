/**
 * 
 */
package com.beikbank.android.net;

import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.http.MySSLSocketFactory;
import com.beikbank.android.http.SSLCustomSocketFactory;
import com.beikbank.android.http.SSLCustomSocketFactory2;
import com.beikbank.android.http.SSLTrustAllSocketFactory;
import com.beikbank.android.http.SSLUtil;
import com.beikbank.android.net.impl.ErrorCode;

public class HttpUtil
{   
	static String tag="HttpUtil";
	// 创建HttpClient对象
	public  HttpClient httpClient = new DefaultHttpClient();
	public static final String BASE_URL = SystemConfig.kHOST_URL;
	private static String responce_encode="utf-8";
	public  HttpContext hcontext= new BasicHttpContext();
	public static final int TIMEOUT=10000;
	private static final int HTTP_CONNECT_TIMEOUT = 30 * 1000;  
	private static final int HTTP_SOCKET_TIMEOUT = 30 * 1000;
	Context con;
	public HttpUtil(Context con)
	{   
		this.con=con;
		setSSL();
		//httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,TIMEOUT);
		HttpParams hp = httpClient.getParams(); 
		HttpConnectionParams.setConnectionTimeout(hp,HTTP_CONNECT_TIMEOUT); 
		HttpConnectionParams.setSoTimeout(hp,HTTP_SOCKET_TIMEOUT);
		hp.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);  

	}
	
	
	
	
	 /**
	  * 发送json格式参数
	  * @param url
	  * @param json
	  * @return
	  */
	 public  String doPost(String url,String json){
		    //DefaultHttpClient client = new DefaultHttpClient();
		    HttpPost post = new HttpPost(url);
		    //JSONObject response = null;
		    String response=null;
		    try {
		      StringEntity s = new StringEntity(json);
		      s.setContentEncoding("UTF-8");
		      s.setContentType("application/json");//发送json数据需要设置contentType
		      post.setEntity(s);
		      HttpResponse res = httpClient.execute(post);
		      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		        //HttpEntity entity = res.getEntity();
		        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
		        //response = JSONObject.fromObject(result);
		        response=result;
		      }
		    } catch (Exception e) {
		      throw new RuntimeException(e);
		    }
		    return response;
		  }
	/**
	 * 
	 * @param url 发送请求的URL
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public  String getRequest(String url)
		throws Exception
	{  
		
		// 创建HttpGet对象。
		HttpGet get = new HttpGet(url);
		// 发送GET请求
		HttpResponse httpResponse = httpClient.execute(get,hcontext);
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			// 获取服务器响应字符串
			String result = doEncode(httpResponse.getEntity());
			return result;
		}
		else
		{   
			ErrorCode ec=new ErrorCode();
			//String code=ec.getCode(url);
			String code=ErrorCodeInfo.e0;
			int StatusCode=httpResponse.getStatusLine()
					.getStatusCode();
			String text=code+StatusCode;
			LogHandler.writeLogFromString(tag, text);
			return text;
			
		}
	}

	/**
	 * 
	 * @param url 发送请求的URL
	 * @param params 请求参数
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public  String postRequest(String url
		, Map<String ,String> rawParams)throws Exception
	{
		// 创建HttpPost对象。
		HttpPost post = new HttpPost(url);
		// 如果传递参数个数比较多的话可以对传递的参数进行封装
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : rawParams.keySet())
		{
			//封装请求参数
			params.add(new BasicNameValuePair(key , rawParams.get(key)));
		}
		// 设置请求参数
		post.setEntity(new UrlEncodedFormEntity(
			params, responce_encode));
		// 发送POST请求
		HttpResponse httpResponse = httpClient.execute(post,hcontext);
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			// 获取服务器响应字符串
			String result = doEncode(httpResponse.getEntity());
			return result;
		}
		else
		{
			ErrorCode ec=new ErrorCode();
			String code=ErrorCodeInfo.e0;
			int StatusCode=httpResponse.getStatusLine()
					.getStatusCode();
			String text=code+StatusCode;
			LogHandler.writeLogFromString(tag, text);
			return text;
		}
	} 
	/**
	 * 设置安全证书
	 */
	  public void setSSL()
	  {   
		  SchemeRegistry srg=httpClient.getConnectionManager().getSchemeRegistry();
		  srg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		  //srg.register(new Scheme("https", SSLTrustAllSocketFactory.getSocketFactory(), 443));
		  srg.register(new Scheme("https",SSLCustomSocketFactory2.getSslSocketFactory(con), 443));
//		  KeyStore ks=SSLTrustAllSocketFactory.getKS(con);
//		  try {
//			srg.register(new Scheme("https", new SSLTrustAllSocketFactory(ks), 443));
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		  
	  }
	  /**
	   * 对返回的数据设置编码
	   * @param he
	   * @return
	   * @throws Exception
	   */
      private String  doEncode(HttpEntity he) throws Exception
      {   
    	  Header header=he.getContentEncoding();
    	  String re_encode=null;
    	  if(header!=null)
    	  {
    		  re_encode=header.getName();
    	  }
          if(re_encode==null)
          {
        	  re_encode=responce_encode;
          }
    	  
    	  return EntityUtils.toString(he, re_encode);
      }
}


