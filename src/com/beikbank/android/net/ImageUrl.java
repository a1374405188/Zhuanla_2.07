package com.beikbank.android.net;

import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 设置
 * @author Administrator
 *
 */
public class ImageUrl extends Thread{
	Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			if(msg.what==1)
			{  
				if(bitmap!=null)
				{
				 //iv.setImageBitmap(bitmap);
					
				// Drawable drawable=new BitmapDrawable(bitmap);
				 //iv.setBackgroundDrawable(drawable);
				 iv.setImageBitmap(bitmap);
			   }
			}
		}
		
	};
	public ImageView iv;
	public String url;
	Bitmap bitmap=null;
   public ImageUrl(ImageView iv,String url)
   {
	   this.iv=iv;
	   this.url=url;
   }

  @Override
  public void run() 
  {
	  URL picUrl;
	try {
		picUrl = new URL(url);
		bitmap= BitmapFactory.decodeStream(picUrl.openStream());
		Message msg=new Message();
		msg.what=1;
		handler.sendMessage(msg);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	 
  }
   
}
