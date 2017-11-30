package com.beikbank.android.widget2;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.data2.LunBo;
import com.beikbank.android.net.ImageUrl;
import coma.beikbank.android.R;



import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.FrameLayout.LayoutParams;

/**
 * 
 * @author Administrator
 *该类用于图片轮播管理
 */
public class ImageViewLunbo extends LinearLayout
{   
	/**
	 * 停止动画
	 */
	public static boolean stop=false;
    /**
     * 图片宽度
     */
	int width=android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
	int height=android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
	/**
	 * 一次动画时间
	 */
	int time=2000;
	List<ImageView> imgs=new ArrayList<ImageView>();
	
	ArrayList<LunBo> list2;
	Activity act;
	/**
	 * 当前显示图片的索引
	 */
	int cindex=0;
    boolean end=true;
	TranslateAnimation ta2;
	FrameLayout fl;
	FrameLayout fl_fu;
	public ImageViewLunbo(Context context) {
		super(context);
		
	}
	public ImageViewLunbo(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	/**
	 * 该方法必须被调用
	 * @param act
	 * @param list
	 */
    public void init(Activity act,ArrayList<LunBo> list2)
    {   
    	
    	
    	
    	this.act=act;
    	WindowManager wm = act.getWindowManager();
   	 
        width = wm.getDefaultDisplay().getWidth();
        fl=new FrameLayout(act);
        fl_fu=new FrameLayout(act);
    	android.widget.LinearLayout.LayoutParams lp1=new android.widget.LinearLayout.LayoutParams(width*2,height);
    	fl.setLayoutParams(lp1);
    	fl_fu.setLayoutParams(lp1);
    	ta2=new TranslateAnimation(0,-width,0,0);
     	ta2.setDuration(1500);
     	ta2.setFillAfter(true);
        ta2.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				end=true;
				
			}
		});
        
        cindex=0;
        imgs=new ArrayList<ImageView>();
        end=true;
        
        removeAllViews();
        fl_fu.addView(fl);
        addView(fl_fu);
        
        this.list2=list2;
        
       
        initImgs(list2);
        
        run=new Runnable() 
        {
    		
    		@Override
    		public void run() 
    		{   
    			if(end)
    			{
    			  setView();
    			}
    	       if(!stop)
    	       {
    			 handler.postDelayed(run,time);
    	       }
    		}
    		
    	};
        handler=new Handler();
        handler.postDelayed(run,time);
        
    }
    private void initImgs(List<LunBo> list)
    {   
    	
    	android.widget.FrameLayout.LayoutParams lp=new android.widget.FrameLayout.LayoutParams(width,height);
    	lp.leftMargin=0;
    	android.widget.FrameLayout.LayoutParams lp2=new android.widget.FrameLayout.LayoutParams(width,height);
    	lp.leftMargin=width;
    	for(int i=0;i<list.size();i++)
    	{
    		ImageView img=new ImageView(act);
    		if(i==0)
    		{
    			img.setLayoutParams(lp);
    		}
    		else
    		{
    			img.setLayoutParams(lp2);
    		}
    		
    		imgs.add(img);
    		fl.addView(img);
    		ImageUrl iu=new ImageUrl(img,list.get(i).imgurl);
    		iu.start();
    	}
    	
    	
    	setYuan(list2.size());
    	fl_fu.addView(ll);
    }
    LinearLayout ll;
    /**
     * 轮播图圆点
     */
    private void setYuan(int size)
    {
    	 ll=new LinearLayout(act);
    	int w=android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;
    	int h=android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;
    	int w2=android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
    	int h2=android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;
    	android.widget.LinearLayout.LayoutParams lp1=new android.widget.LinearLayout.LayoutParams(w,h);
    	android.widget.FrameLayout.LayoutParams lp2=new android.widget.FrameLayout.LayoutParams(width,h2);
    	lp2.gravity=Gravity.BOTTOM;
    	
    	ll.setLayoutParams(lp2);
    	ll.setGravity(Gravity.CENTER_HORIZONTAL);
    	ll.setPadding(0,0,0,20);
    	
    	LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.leftMargin=10;
        lp.rightMargin=10;
    	for(int i=0;i<size;i++)
        {
        	View view=act.getLayoutInflater().inflate(R.layout.yuan,ll,false);
        	
        	view.setLayoutParams(lp);
        	ll.addView(view);
        	
        }
    	
    	
    }
    /**
     * 准备好要执行动画的图片
     */
    private void setView()
    {   
    	
    	ImageView img1=imgs.get(cindex);
    	ImageView img2=null;
    	android.widget.FrameLayout.LayoutParams lp=new android.widget.FrameLayout.LayoutParams(width,height);
    	lp.leftMargin=0;
    	img1.clearAnimation();
		fl.updateViewLayout(img1,lp);
		
		if(cindex==imgs.size()-1)
		{
			img2=imgs.get(0);
		}
		else
		{
			img2=imgs.get(cindex+1);
		}
		
		android.widget.FrameLayout.LayoutParams lp2=new android.widget.FrameLayout.LayoutParams(width,height);
    	lp2.leftMargin=width;
    	img2.clearAnimation();
		fl.updateViewLayout(img2,lp2);
		
		

    
		fl.removeView(img2);
		fl.addView(img2);
		cindex++;
		if(cindex>imgs.size()-1)
		{
			cindex=0;
		}
		
		img1.startAnimation(ta2);
		img2.startAnimation(ta2);
		end=false;
    }
    Runnable run;
    Handler handler;
    /**
     * 开始轮播
     */
    public  void start()
    {
    	stop=false;
    	handler.postDelayed(run,time);
    }
    /**
     * 停止动画
     * 必须调用
     * @param isstop
     */
    public  void stop()
    {
    	stop=true;
    }
    
}
