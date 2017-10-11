package com.beikbank.android.activity;

import java.net.MalformedURLException;
import java.net.URL;

import com.beikbank.android.data.Action;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.media.ImageManager;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 广告页
 */
public class ActionActivity extends Activity{
    ImageView iv;
    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action);
		StateBarColor.init(this,0xffffffff);
	    initView();
	    initData();
	}
    private void initView()
    {
    	iv=(ImageView)findViewById(R.id.iv);
    	
    }
    Action action;
    private void initData()
    {   
    	Intent intent=getIntent();
    	action=(Action)intent.getSerializableExtra("action");
    	new ImageLoad().start();
    }
    Bitmap bitmap;
    class ImageLoad extends Thread
    {

		@Override
		public void run() {
			URL url;
			try {
				url = new URL(action.thumb);
			    bitmap=BitmapFactory.decodeStream(url.openStream());
			    double iwidth=bitmap.getWidth();
			    double iheight=bitmap.getHeight();
			    double scle=iheight/iwidth;
			    
			    //屏幕宽度
			    int dwidth=BeikBankApplication.getWidth(act);
			    //屏幕高度
			    int dheight=BeikBankApplication.getHeight(act);
			    //状态栏高度
			    int sheight=BeikBankApplication.getStaticHeight(act);
			    
			    
			    //屏幕高度
			    int height=(int) (dwidth*scle);
			    if(height>dheight-sheight)
			    {
			    	height=dheight-sheight;
			    }
			    
			  //  bitmap=bitmap.createScaledBitmap(bitmap,dwidth,height,false);
			    bitmap=ImageManager.getBItmap(bitmap,dwidth, height);
				Message msg=new Message();
				msg.what=1;
				handler.sendMessage(msg);
			} catch (Exception e) {
				Message msg=new Message();
				msg.what=1;
				handler.sendMessage(msg);
				e.printStackTrace();
			}
		}
    	
    }
    class sleeptime extends Thread
    {

		@Override
		public void run() {
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Message msg=new Message();
			msg.what=2;
			handler.sendMessage(msg);
		}
    	
    }
    //
    boolean is_action=false;
    Handler handler=new Handler()
    {

		@Override
		public void handleMessage(Message msg) {
		    if(msg.what==1)
		    {
		    	if(bitmap!=null)
		    	{
		    		iv.setImageBitmap(bitmap);
		    		if("1".equals(action.type))
		    		{
		    			iv.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								Intent intent1=new Intent(act,HomeActivity3.class);
						    	startActivity(intent1);
						    	
								Intent intent=new Intent(act,HuodongActivity2.class);
								
								intent.putExtra("index",1);
								startActivity(intent);
								is_action=true;
								finish();
							}
						});
		    		}
		    	}
		    	
		    	new sleeptime().start();
		    }
		    if(msg.what==2)
		    {   
		    	if(!is_action)
		    	{ 
		    		if(!is_return)
		    		{
		    			  Intent intent=new Intent(act,HomeActivity3.class);
				    	  startActivity(intent);
				    	  if(bitmap!=null)
				    	  {
				    		  bitmap.recycle();
				    	  }
				    	  finish();
		    		}
		    		 Log.e("tag0","oncleate");
		    	}
		    }
		}
    	
    };
    /**
     * 点击返回键
     */
    boolean is_return;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				is_return=true;
			}
		  return super.onKeyDown(keyCode, event);
	}
}
