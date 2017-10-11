package com.beikbank.android.utils;

import comc.beikbank.android.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;

public class HongdianUtil {
	 Context con;
	 int contacyCount=0;
	  public HongdianUtil(Context con)
	  {
		  this.con=con;
	  }
	  public void createShortCut(int count)
	 {
		contacyCount=count;
		//创建快捷方式的Intent
		Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		//不允许重复创建
		shortcutintent.putExtra("duplicate", false);
		//需要现实的名称
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, con.getString(R.string.app_name));
		//快捷图片(每次重绘logo生成一张新图)
		Parcelable icon = Intent.ShortcutIconResource.fromContext(con, R.drawable.logo);
		Bitmap bitmap=generatorContactCountIcon(((BitmapDrawable)(con.getResources().getDrawable(R.drawable.logo))).getBitmap());
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,bitmap);
		//点击快捷图片，运行的程序主入口
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent());
		//发送广播。OK
		 con.sendBroadcast(shortcutintent);
		}
	  private Bitmap generatorContactCountIcon(Bitmap icon){  
	        //初始化画布  
	        int iconSize=(int)con.getResources().getDimension(android.R.dimen.app_icon_size);  
	        Bitmap contactIcon=Bitmap.createBitmap(iconSize, iconSize, Config.ARGB_8888);  
	        Canvas canvas=new Canvas(contactIcon);  
	          
	        //拷贝图片  
	        Paint iconPaint=new Paint();  
	        iconPaint.setDither(true);//防抖动  
	        iconPaint.setFilterBitmap(true);//用来对Bitmap进行滤波处理，这样，当你选择Drawable时，会有抗锯齿的效果  
	        Rect src=new Rect(0, 0, icon.getWidth(), icon.getHeight());  
	        Rect dst=new Rect(0, 0, iconSize, iconSize);  
	        canvas.drawBitmap(icon, src, dst, iconPaint);  
	          
	        //在图片上创建一个覆盖的联系人个数  
	      
	        //启用抗锯齿和使用设备的文本字距  
	        Paint countPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DEV_KERN_TEXT_FLAG);  
	        countPaint.setColor(Color.RED);  
	        countPaint.setTextSize(20f);  
	        countPaint.setTypeface(Typeface.DEFAULT_BOLD);  
	        canvas.drawText(String.valueOf(contacyCount), iconSize-18, 25, countPaint);  
	        return contactIcon;  
	    }
}
