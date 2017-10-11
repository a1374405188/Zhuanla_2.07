package com.beikbank.android.gif;

import comc.beikbank.android.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-3
 * 
 */

public class CustomGifView extends View
{
	private Movie mMovie;    
	private long mMovieStart;  
	public CustomGifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	 public void setGifResId(int id)
	 {  

		    mMovie = Movie.decodeStream(getResources().openRawResource(    
				                    id));   

	 }
	  public void onDraw(Canvas canvas) {   
		             long now = android.os.SystemClock.uptimeMillis();    
		                
		             if (mMovieStart == 0) 
		             { // first time    
		                  mMovieStart = now;    
		             }    
		             if (mMovie != null) 
		             {    
		                    
		                 int dur = mMovie.duration();    
		              if (dur == 0) 
		              {    
		                      dur = 1000;    
		              }    
		                  int relTime = (int) ((now-mMovieStart) % dur);                   
		                  mMovie.setTime(relTime);
		                  int a=(getWidth() - mMovie.width())/2;
		                  int b=(getHeight() - mMovie.height())/2;
		                  mMovie.draw(canvas,a,b);    
		                  invalidate();    
		            }    
		          }   

}
