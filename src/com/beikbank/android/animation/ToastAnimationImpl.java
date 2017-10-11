package com.beikbank.android.animation;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

import com.beikbank.android.conmon.DisplayManger;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-5
 **/
public class ToastAnimationImpl implements ToastAnimation{
	
	/**
	 * 在当前窗口直行一个动画
	 * @param act
	 * @param parentView 父视图 必须指明宽度高度
	 * @param tagarView  动画视图 必须指明宽度高度
	 */
	public  void performToast(Activity act,final View parentView,final View tagarView)
	{
		  final WindowManager wm=act.getWindowManager();
		  LayoutParams lp=new LayoutParams();
		  lp.x=0;
		  lp.y=0;
		  lp.width=DisplayManger.getWidth(act);
		  lp.height=100;
		  lp.gravity=Gravity.TOP|Gravity.LEFT;
          wm.addView(parentView,lp);
          int h=36;
          
          AnimationSet ams=new AnimationSet(true);
          TranslateAnimation  tam=new TranslateAnimation(0,0,-h,0);
          tam.setDuration(600);
          tam.setFillAfter(true);
          
          TranslateAnimation  tam2=new TranslateAnimation(0,0,0,-h);
          tam2.setDuration(600);
          tam2.setStartOffset(3000);
          
          ams.setAnimationListener(new AnimationListener() {
			
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
				wm.removeView(parentView);
				
			}
		});
          
          ams.addAnimation(tam);
          ams.addAnimation(tam2);
          tagarView.startAnimation(ams);
	}
	 int l;
	 int time;
	@Override
	public void performTextMove(final View tv, int one, int length) 
	{        
		 l=one;
	       time=length;
	      
	      AnimationSet ams=new AnimationSet(true);
	      ams.setFillAfter(true);
	      
	      TranslateAnimation  tam=new TranslateAnimation(0,l,0,0);
        tam.setDuration(time);
        tam.setFillAfter(true);
        

		    final AnimationListener al3=new AnimationListener() {
				
						@Override
						public void onAnimationStart(Animation arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationRepeat(Animation arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animation arg0) {
							   TranslateAnimation  tam4=new TranslateAnimation(l,0,0,0);
						       tam4.setDuration(time);
						       tam4.setFillAfter(true);
						       tv.startAnimation(tam4);
						}
					};
					
        final AnimationListener al2=new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				   TranslateAnimation  tam3=new TranslateAnimation(0,l,0,0);
			       tam3.setDuration(time);
			       tam3.setFillAfter(true);
			       tam3.setAnimationListener(al3);
			       tv.startAnimation(tam3);
			}
		};
        
        

		          
        
        tam.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				  TranslateAnimation  tam2=new TranslateAnimation(l,0,0,0);
		          tam2.setDuration(time);
		         // tam2.setStartOffset(off);
		          tam2.setFillAfter(true);
		          tam2.setAnimationListener(al2);
		          tv.startAnimation(tam2);
			}
		});
      ams.addAnimation(tam);	          
      tv.startAnimation(ams);
}    
	/**
	 * 执行位移动画
	 * @param view
	 * @param p
	 * @param al
	 */
	  public void MoveAnimation1(View view,Params p,AnimationListener al)
	  {
		  TranslateAnimation tam=new TranslateAnimation(p.fromx,p.tox,p.fromy,p.toy);
		  tam.setDuration(p.time);
		  tam.setFillAfter(true);
		  if(al!=null)
		  {
		     tam.setAnimationListener(al);
		  }
		  view.startAnimation(tam);
	  }
	  
	  
	  public void aliphaAnimation(View view,aParams ap,AnimationListener al)
	  {
		  AlphaAnimation aa=new AlphaAnimation(ap.froma,ap.toa);
	      aa.setDuration(ap.time);
	      aa.setFillAfter(false);
	      if(al!=null)
	      {
	    	  aa.setAnimationListener(al);
	      }
	      view.startAnimation(aa);
	  }
}
