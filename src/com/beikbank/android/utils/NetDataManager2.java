package com.beikbank.android.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import u.aly.co;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.beikbank.android.http.SSLCustomSocketFactory2;
import com.beikbank.android.http.SSLCustomSocketFactory3;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.security.MyX509TrustManager;

/**
 * 加载网络大数据
 * @author Administrator
 *
 */
public class NetDataManager2 

{   ICallBack icb;
    String url;
    private Dialog dialog;
    boolean isSuccess=false;
    String path;
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
			  icb.back(isSuccess);
			}
		}
		
		
		
	};
	/**
	 * 
	 * @param icb
	 * @param url 网络文件地址
	 * @param path 文本保存路径
	 * @param context is null不加载进度条，否则加载
	 */
    public  NetDataManager2(ICallBack icb,String url,String path,Context context)
    {
    	this.icb=icb;
    	this.url=url;
    	this.path=path;
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
			  OutputStream output=null;
			  InputStream is=null;
		      try{  
		    	    
		              
		            // 从上述SSLContext对象中得到SSLSocketFactory对象     
		            SSLSocketFactory ssf = SSLCustomSocketFactory3.getSSLContext2(context).getSocketFactory();   
		    	  
		    	  
		            myFileURL = new URL(url);  
		            //获得连接  
		            HttpsURLConnection conn=(HttpsURLConnection)myFileURL.openConnection();  
		           
		            conn.setSSLSocketFactory(ssf);
		            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
		            conn.setConnectTimeout(6000);  
		            //连接设置获得数据流  
		            conn.setDoInput(true);  
		            //不使用缓存  
		            conn.setUseCaches(false);  
		            //这句可有可无，没有影响  
		            //conn.connect();  
		            //得到数据流  
		            is = conn.getInputStream();  
		            
		           
		            File file=new File(path);
//		            if(file.exists())
//		            {
//		            	file.delete();
//		            }
//		            file.createNewFile();
		            output=new FileOutputStream(file);  
                    //读取大文件  
                    byte[] buffer=new byte[4*1024];
                    int size=0;
                    while((size=is.read(buffer))>0){  
                        output.write(buffer,0,size);  
                    }  
                    output.flush();  
		            //关闭数据流  
		            is.close();
		            output.close();
		            
		            isSuccess=true;
		        }
		        catch(Exception e)
		        {  
		            e.printStackTrace();
		            if(is!=null)
		            {
		            	try {
							is.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
		            }
		            
		            if(output!=null)
		            {
		            	try {
							output.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
		            }
		        } 
		     
		      Message msg=new Message();
		      msg.what=1;
		      handler.sendMessage(msg);
		}
    	
    }
}
