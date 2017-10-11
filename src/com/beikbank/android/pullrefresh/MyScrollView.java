package com.beikbank.android.pullrefresh;

 
import android.annotation.TargetApi;
import android.content.Context;  
import android.os.Build;
import android.util.AttributeSet;  
import android.view.MotionEvent;
import android.widget.ScrollView;

  
@TargetApi(Build.VERSION_CODES.GINGERBREAD) 
public class MyScrollView extends ScrollView{  
  
	   Context context;
	    float lastx;
	    float lasty;
	    public MyScrollView(Context context) {  
	        super(context);  
	        this.context=context;
	        setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
	    } 
	    public MyScrollView(Context context, AttributeSet attrs) {  
	        super(context, attrs); 
	        this.context=context;
	        setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
	    }  
	    int min=10;
	    @Override  
	    public boolean onInterceptTouchEvent(MotionEvent ev) 
	    {
	    	
	    	if(ev.getAction()==MotionEvent.ACTION_DOWN)
	    	{
	    		lastx=ev.getX();
	    		lasty=ev.getY();
	    	}
	    	else if(ev.getAction()==MotionEvent.ACTION_MOVE)
	    	{   
	    		float x=ev.getX();
	    		float y=ev.getY();
	    		float absx=Math.abs(x-lastx);
	    		float absy=Math.abs(y-lasty);
//	    		if((absx>min)&&absx>absy)
//	    		{
//	    			return false;
//	    		}
//	    		else if(absy>min&&absx<absy)
//	    		{
//	    			return true;
//	    		}
	    		if(absx>50)
	    		{
	    			return true;
	    		}
	    		if(absy<min||absx>absy)
	    		{
	    			return false;
	    		}
	    		else
	    		{
	    			//lastx=ev.getX();
		    		//lasty=ev.getY();
	    			
	    		}
	    	}
	    	
	        return super.onInterceptTouchEvent(ev);
	    }
		@Override
		public boolean onTouchEvent(MotionEvent ev) {
			
			return super.onTouchEvent(ev);
		}
	
	   
	  
}  
