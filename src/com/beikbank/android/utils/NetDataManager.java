package com.beikbank.android.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.http.SSLCustomSocketFactory3;
import com.beikbank.android.net.ICallBack;

/**
 * 加载网络大数据
 * @author Administrator
 *
 */
public class NetDataManager 
 
{   ICallBack icb;
    String url;
    private Dialog dialog;
    Bitmap bitmap=null;
    Context context;
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==1)
			{
			  if(dialog!=null)
			  {
				  dialog.dismiss();
			  }
			  icb.back(bitmap);
			}
		}
		
		
		
	};
	/**
	 * 
	 * @param icb
	 * @param url
	 * @param context is null不加载进度条，否则加载
	 */
    public  NetDataManager(ICallBack icb,String url,Context context)
    {
    	this.icb=icb;
    	this.url=url;
    	
    	if(context!=null)
    	{
    		this.context=context;
    	  if(dialog==null||!dialog.isShowing())
    	  {
    	    dialog=Utils.createPorgressDialog(context, null);
		    dialog.show();
		    new addData().start();
           }
    	}
    	else
    	{
    		new addData().start();
    	}
    }
    class addData extends Thread
    {

		@Override
		public void run() {
			  URL myFileURL;  
		      
		      try{  
		    	  
		    	  // 从上述SSLContext对象中得到SSLSocketFactory对象     
		            SSLSocketFactory ssf = SSLCustomSocketFactory3.getSSLContext2(context).getSocketFactory();   
		            myFileURL = new URL(url);  
		            URLConnection conn=myFileURL.openConnection();
		            //获得连接  
		           // HttpsURLConnection conn=(HttpsURLConnection)myFileURL.openConnection();  
		            if(conn instanceof HttpsURLConnection)
		            {
		            	((HttpsURLConnection)conn).setSSLSocketFactory(ssf);
		            }
		            
		            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
		            conn.setConnectTimeout(6000);  
		            //连接设置获得数据流  
		            conn.setDoInput(true);  
		            //不使用缓存  
		            conn.setUseCaches(false);  
		            //这句可有可无，没有影响  
		            //conn.connect();  
		            //得到数据流  
		            InputStream is = conn.getInputStream();  
		            //解析得到图片  
		            bitmap = BitmapFactory.decodeStream(is);  
		            //关闭数据流  
		            is.close();
		            
		        }catch(Exception e){  
		            e.printStackTrace();  
		        } 
		     
		      Message msg=new Message();
		      msg.what=1;
		      handler.sendMessage(msg);
		}
    	
    }
}
