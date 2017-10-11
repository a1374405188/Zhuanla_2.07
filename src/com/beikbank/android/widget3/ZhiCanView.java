package com.beikbank.android.widget3;

import java.util.ArrayList;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.type.ZhiChan;
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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
/**
 * 
 * @author Administrator
 *资产分布背景图
 */
public class ZhiCanView extends View{
	private Paint mPaint;
	private Paint mPaint2;
	private float mBorderWidth=60;
	private int mBorderColor=0xffe4393c;
	private int width=300;
	private float height=300;
	

	/**
	 * 圆弧度数
	 */
	private int size=360;
	
	/**
	 * 顺时针4个资产分别的角度
	 */
	private int a=30;
	private int b=60;
	private int c=90;
	private int d=180;
	/**
	 * 分割角度
	 */
	private int space=5;
	
	
	private Paint mPainta;
	private Paint mPaintb;
	private Paint mPaintc;
	private Paint mPaintd;
    Activity act;
	public ZhiCanView(Context context) {
		super(context);
		act=(Activity) context;
		init();
	}
	public ZhiCanView(Context context, AttributeSet attrs) {
		super(context, attrs);
		act=(Activity) context;
		init();
	}
	 private void init(){
	        mPainta = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPainta.setStyle(Paint.Style.STROKE);
	        mPainta.setStrokeWidth(mBorderWidth);
	        mPainta.setColor(0xffff635b);
	        
	        
	        mPaintb = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPaintb.setStyle(Paint.Style.STROKE);
	        mPaintb.setStrokeWidth(mBorderWidth);
	        mPaintb.setColor(0xfffebf56);
	        
	        
	        mPaintc = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPaintc.setStyle(Paint.Style.STROKE);
	        mPaintc.setStrokeWidth(mBorderWidth);
	        mPaintc.setColor(0xff69c5ff);
	        
	        mPaintd = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPaintd.setStyle(Paint.Style.STROKE);
	        mPaintd.setStrokeWidth(mBorderWidth);
	        mPaintd.setColor(0xfff2a3da);
	        
	        
	        
	        
	        WindowManager wm = act.getWindowManager();
		    width = wm.getDefaultDisplay().getWidth();
		    
	        width_yuan=countWidth(width);
	        
	        DensityUtil du=new DensityUtil(act);
			int w1=du.dip2px(200);
			
	        float f1=((float)width-w1)/2;
	        startX=(int) f1;
	        startY=du.dip2px(100)/2;
	    }
	 public void setData(ArrayList<ZhiChan> list)
	 {
		 
		 
		 
	 }
	/**
	 * 
	 * @param act
	 * @param a 顺时针 4个资产分别的大小
	 * @param b
	 * @param c
	 * @param d
	 */
	 public void init2(Activity act,ArrayList<Integer> list0)
	 {   
		
//		 
//		 WindowManager wm = act.getWindowManager();
//	     width = wm.getDefaultDisplay().getWidth();
//	     DensityUtil du=new DensityUtil(act);
//	     //width=width-du.dip2px(16)*2;
//		 width_yuan=countWidth(width);
//		
//		 //this.width=du.dip2px(300);
//	     LayoutParams lp=new LayoutParams(this.width,width_yuan+startY);
//	     setLayoutParams(lp);
	     
	     //计算角度；
	     int total_size=0;
	     for(int i=0;i<list0.size();i++)
	     {
	    	 total_size+=list0.get(i);
	     }
	     
	     
	     
	     
	     list=new ArrayList<ZhiCanView.MyPaint>();
	     MyPaint mp=null;
	     int start=0;
	     for(int i=0;i<list0.size();i++)
	     {
	    	 
	    	 Paint p=new Paint();
	    	 p= new Paint(Paint.ANTI_ALIAS_FLAG);
 	          p.setStyle(Paint.Style.STROKE);
 	          p.setStrokeWidth(mBorderWidth);
	    	 if(i==0)
	    	 {
	    		 
	  	          p.setColor(0xfff9803e);
	  	        
	    	 }
	    	 else if(i==1)
	    	 {
	    		 p.setColor(0xffff635b);
	    	 }
	    	 else if(i==2)
	    	 {
	    		 p.setColor(0xfffebf56);
	    	 }
	    	 else if(i==3)
	    	 {
	    		 p.setColor(0xfff2a3da);
	    	 }
	    	 else if(i==4)
	    	 {
	    		 p.setColor(0xfff2a3da);
	    	 }
	    	 else if(i==5)
	    	 {
	    		 p.setColor(0xfff33333);
	    	 }
	    	 else if(i==6)
	    	 {
	    		 p.setColor(0xfff44444);
	    	 }
	    	 else if(i==7)
	    	 {
	    		 p.setColor(0xfff55555);
	    	 }
	    	 else if(i==8)
	    	 {
	    		 p.setColor(0xfff66666);
	    	 }
	    	 else
	    	 {
	    		 p.setColor(0xfff77777);
	    	 }
	    	 double jiaodu=(double)360/total_size*list0.get(i);
	    	 mp=new MyPaint();
	    	 mp.paint=p;
	    	 mp.start=start;
	    	 mp.size=(int) jiaodu;
	    	 if(0<jiaodu&&jiaodu<1)
	    	 {
	    		 mp.size=1;
	    	 }
	    	 start+=mp.size;
	    	 list.add(mp);
	     }
	     
	     
	     
	     invalidate();
	     
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
	 public void drawYiGou(DingqiP2 dp2)
	 {
		
		 
		 invalidate();
		 
	 }
	 
	 
	 /**
	  * 圆开始X轴的位置
	  */
	 int startX=40;
	 /**
	  * 圆开始Y轴的位置
	  */
	 int startY=40;
	 /**
	  * 圆的宽度
	  */
	 int width_yuan=600;
	 /**
	  * 
	  * @param windth 屏幕的宽度
	  * @return 圆的宽度
	  */
	 private int countWidth(int width)
	 {
		 float w=0;
		 WindowManager wm = act.getWindowManager();
		 width = wm.getDefaultDisplay().getWidth();
		 
		 w=width/10*3;
		 DensityUtil du=new DensityUtil(act);
		 w= du.dip2px(200);
		 
		 
		 
		 return (int) w;
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
	ArrayList<MyPaint> list;
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		canvas.drawColor(0xffffffff);
		rect=new RectF(startX,startY,width_yuan+startX,width_yuan+startY);
		int start=90;
		if(list!=null)
		{
		   for(int i=0;i<list.size();i++)
		  {
			MyPaint mp=list.get(i);
			canvas.drawArc(rect,mp.start,mp.size,false,mp.paint);
		  }
		}
//		canvas.drawArc(rect,start,a,false,mPainta);
//		
//		canvas.drawArc(rect,start+a,b,false,mPaintb);
//		canvas.drawArc(rect,start+a+b,c,false,mPaintc);
//		canvas.drawArc(rect,start+a+b+c,d,false,mPaintd);
		
		
		
//		if(sanxin)
//		{   
//			mPaint.setStyle(Paint.Style.FILL);
//			
//			mPaint.setColor(0xfff7dfd6);
//			canvas.drawArc(rect,(360-size)/2+90,size,false,mPaint);
//		}else
//		{
//			//canvas.drawArc(rect,(360-size)/2+90,size,false,mPaint);
//		}
		
		
		

		
	}
	class  MyPaint 
	{
		Paint paint;
		/**
		 * 开始的角度
		 */
		int start;
		/**
		 * 大小
		 */
		int size;
		
	}
    
}
