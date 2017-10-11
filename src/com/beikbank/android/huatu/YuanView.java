package com.beikbank.android.huatu;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.utils.DensityUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class YuanView extends View{
	private Paint mPaint;
	private Paint mPaint2;
	private float mBorderWidth=5;
	private int mBorderColor=0xfffb5a5c;
	public int color1=0xffe1e1e1;
	private int width=100;
	private float height=100;

	/**
	 * 圆弧度数
	 */
	private int size=360;
	
	 /**
	  * 圆的宽度
	  */
	 int width_yuan=140;
	private int rect_width=200;

	public YuanView(Context context) {
		super(context);
		init();
	}
	public YuanView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	 private void init(){
	        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeWidth(mBorderWidth);
	        mPaint.setColor(mBorderColor);
	        
	        
	        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPaint2.setStyle(Paint.Style.STROKE);
	        mPaint2.setStrokeWidth(mBorderWidth+2);
	        mPaint2.setColor(0xfff5f5f5);
	        
	    }
	 public void setPaintColor(int color)
	 {
		 mBorderColor=color;
		 invalidate();
	 }
	 /**
	  * 
	  * @param act
	  * @param size 圆弧的度数
	  */
	 public void init2(Activity act,int size)
	 {   
		 
		 WindowManager wm = act.getWindowManager();
	     width = wm.getDefaultDisplay().getWidth();
	     DensityUtil du=new DensityUtil(act);
	     //width=width-du.dip2px(16)*2;
		 width_yuan=countWidth(width);
		
		 //this.width=du.dip2px(300);
	     LayoutParams lp=new LayoutParams(this.width,width_yuan+startY);
	     setLayoutParams(lp);
	     this.size=size;
	     
	 }
	 /**
	  * 计算已可购买的度数
	  * @return
	  */
	 private int countDuShu(DingqiP2 dp2,int total)
	 {
		 int i=0;
		 double a=Double.parseDouble(dp2.raiseAmount);
		 double b=Double.parseDouble(dp2.remainAmount);
		 double c=(b/a);
		 i=(int) (total*(1-c));
		 return i;
	 }

	 
	 
	 /**
	  * 圆开始X轴的位置
	  */
	 int startX=0;
	 /**
	  * 圆开始Y轴的位置
	  */
	 int startY=0;
	
	 /**
	  * 
	  * @param windth 屏幕的宽度
	  * @return 圆的宽度
	  */
	 private int countWidth(int width)
	 {
		 int w=0;
		 w=width/10*7;
		 startX=(width-w)/2;
		 startY=startX;
		 
		 return w;
	 }
	 private boolean sanxin=false;
	 /**
	  * 设置画扇形
	  * @param b
	  */
	public  void setDraw(boolean b)
	{
		sanxin=b;
	}
	RectF rect;
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		canvas.drawColor(0xffffffff);
		startX=(rect_width-width_yuan)/2;
		startY=(rect_width-width_yuan)/2;
		rect=new RectF(startX,startY,width_yuan+startX,width_yuan+startY);
		
		
	  
			
			
			mPaint.setColor(mBorderColor);
			canvas.drawArc(rect,0,size,false,mPaint);
			
		
		
//		View view=null;
//		view.buildDrawingCache();
//		Bitmap bitmap=view.getDrawingCache();
//		Drawable drawable =new BitmapDrawable(bitmap);
//		LinearLayout ll=new LinearLayout(getContext());
		
	}
    
}
