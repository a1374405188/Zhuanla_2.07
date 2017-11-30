package com.beikbank.android.net.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;
import coma.beikbank.android.R;



/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 
 */
public class LoadApkManager {
	private Activity act;
	String url;
	private String TAG="LoginManager";
	 public LoadApkManager(Activity act,String url)
	  {
		  this.act=act;
		  this.url=url;
	  }
	  public void start()
	  {
		  downloadApk(url);  
	  }
	  ProgressDialog pd;
	//下载apk
		protected void downloadApk(final String url) {  
			
	   //进度条对话框  
			pd = new ProgressDialog(act);  
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
			pd.setMessage("正在下载更新");  
			pd.show();  
			new Thread(){  
				@Override  
				public void run() {  
					try {  
						String s=Environment.getExternalStorageState();
						if(!Environment.MEDIA_MOUNTED.equals(s))
						{   
							Message msg = new Message();  
							ErrorMessage em=new ErrorMessage();
							em.message=ErrorCodeInfo.e17+":"+"内存卡不存在";
							msg.obj=em;
							handler.sendMessage(msg); 
							return;
						}
						File file = getFileFromServer(url, pd);
						Message msg = new Message();
						if(file==null)
						{
							msg.what =HandlerBase.error1;  
							handler.sendMessage(msg);
							return;
						}
						sleep(3000);  
						installApk(file);
						
					    msg = new Message();
						msg.what =HandlerBase.success1;  
						handler.sendMessage(msg);
						//pd.dismiss(); //结束掉进度条对话框  
					} catch (Exception e) {  
						Message msg = new Message();  
						msg.what =HandlerBase.error2;
						ErrorMessage em=new ErrorMessage();
						em.message=ErrorCodeInfo.e17+ErrorCodeInfo.e16;
						msg.obj=em;
						handler.sendMessage(msg);  
						e.printStackTrace();  
						LogHandler.writeLogFromException(TAG, e);
					}  
				}}.start();  
		}  
		//下载
		public File getFileFromServer(String path, ProgressDialog pd) throws Exception{
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				URL url = new URL(path);
				HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				//获取到文件的大小 
				pd.setMax(conn.getContentLength());
				InputStream is = conn.getInputStream();
				File file = new File(Environment.getExternalStorageDirectory(), "zhuanla_android.apk");
				FileOutputStream fos = new FileOutputStream(file);
				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] buffer = new byte[1024];
				int len ;
				int total=0;
				while((len =bis.read(buffer))!=-1){
					fos.write(buffer, 0, len);
					total+= len;
					//获取当前下载量
					pd.setProgress(total);
				}
				fos.close();
				bis.close();
				is.close();
				return file;
			}
			else{
				return null;
			}
		}
		//安装apk
		protected void installApk(File file) {  
		    Intent intent = new Intent();  
		    //执行动作  
		    intent.setAction(Intent.ACTION_VIEW);  
		    //执行的数据类型  
		    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
		    act.startActivity(intent);  
		}  
		Handler handler = new HandlerBase(act){  
		      
		    @Override  
		    public void handleMessage(Message msg) {  
		        // TODO Auto-generated method stub 
		    	if(pd!=null)
		    	{
		    		pd.dismiss();
		    	}
		        super.handleMessage(msg);  
		        switch (msg.what) {  
//		        case UPDATA_CLIENT:  
//		            //对话框通知用户升级程序  
//		        	String downloadUrl=(String)msg.obj;
//		        	LogHandler.writeLogFromString(TAG,downloadUrl);
//		            showUpdataDialog(downloadUrl);  
//		            break;
		        case HandlerBase.success1:
		        	break;
		        }  
		    }  
		};  
		
}
