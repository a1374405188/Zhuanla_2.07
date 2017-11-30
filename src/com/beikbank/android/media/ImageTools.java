package com.beikbank.android.media;

import coma.beikbank.android.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-10
 * 
 */
/** 
 * 处理图片的工具类.
 * 
 */
public class ImageTools {
    
    /***//**
     * 图片去色,返回灰度图片
     * @param bmpOriginal 传入的图片
     * @return 去色后的图片
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal,Activity act) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();    
        Config cf=bmpOriginal.getConfig();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        c.drawColor(0x33000000);
        
        Bitmap bitmap=BitmapFactory.decodeResource(act.getResources(),R.drawable.load_net_img_end);
        int w=bitmap.getWidth();
        int h=bitmap.getHeight();
        w=(width-w)/2;
        h=(height-h)/2;
        c.drawBitmap(bitmap,w,h, paint);
        return bmpGrayscale;
    }
    public static Bitmap getBitmap(Activity act)
    {
    	Bitmap bitmap=BitmapFactory.decodeResource(act.getResources(),R.drawable.load_net_img_end);
    	
    	return bitmap;
    }
    
//    /***//**
//     * 去色同时加圆角
//     * @param bmpOriginal 原图
//     * @param pixels 圆角弧度
//     * @return 修改后的图片
//     */
//    public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
//        return toRoundCorner(toGrayscale(bmpOriginal), pixels);
//    }
    
    /***//**
     * 把图片变成圆角
     * @param bitmap 需要修改的图片
     * @param pixels 圆角的弧度
     * @return 圆角图片
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    
    /***//**
     * 使圆角功能支持BitampDrawable
     * @param bitmapDrawable 
     * @param pixels 
     * @return
     */
    public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable, int pixels) {
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
        return bitmapDrawable;
    }
}