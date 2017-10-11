package com.beikbank.android.media;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 * 图片放缩的工具类
 */
public class ImageManager {
	/**
	 * 
	 * @param src 源图片
	 * @param width 放缩后图片的宽度
	 * @param height 放缩后图片的高度
	 * @return 放缩后的图片
	 */
	public static Bitmap getBitmap(Bitmap src,int width,int height)
	{
		Bitmap bitmap=null;
		if(src==null)
		{
			new NullPointerException("bitmap not can is null");
		}
		float srcw=src.getWidth();
		float srch=src.getHeight();
		float w=width;
		float h=height;
		float sx=w/srcw;
		float sy=h/srch;
		Matrix m=new Matrix();
		m.postScale(sx, sy);
		bitmap=  Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),m,true);
		return bitmap;
	}
	/**
	 * 更具屏幕对图片进行放缩
	 * @param src 源图片
	 * @param width 图片最大宽度
	 * @param height 图片最大高度
	 * @return 放缩后的图片
	 */
   public static Bitmap  getBItmap(Bitmap src,int width,int height)
   {   
	   if(src==null)
	   {
		   return src;
	   }
	   int iwidth=src.getWidth();
	   int iheight=src.getHeight();
	   double scale=(double)iwidth/(double)width;
	   //图片放缩后的高度
	   int height2=(int) ((double)iheight/scale);
	   //最终图片的高度
	   int height3=0;
	   if(height2<height)
	   {
		   height3=height2;
	   }
	   else
	   {
		   height3=height;
	   }
	   Matrix m=new Matrix();
	   float scale2=(float)width/(float)iwidth;
	   m.postScale(scale2,scale2);
	   
	   
       Bitmap bit=  Bitmap.createBitmap(src,0,0,iwidth,iheight,m,true);
       bit=Bitmap.createBitmap(bit,0,0,width, height3);
	   return bit;
   }
   
}
