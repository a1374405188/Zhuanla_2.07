package com.beikbank.android.pullrefresh;

import android.annotation.SuppressLint;  
import android.content.Context;  
import android.util.AttributeSet;  
import android.util.DisplayMetrics;  
import android.widget.ScrollView;  
  
public class ZhangPhilScrollView extends ScrollView{  
  
    // 可以下来或上发的距离
    private static final int MAX_OVERSCROLL_Y = 200;  
  
    private Context mContext;  
    private int newMaxOverScrollY;  
  
    public ZhangPhilScrollView(Context context) {  
        super(context);  
          
        init(context);  
    }  
  
    public ZhangPhilScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
          
        init(context);  
    }  
  
    /* 
     * public ZhangPhilListView(Context context, AttributeSet attrs, int 
     * defStyle) { super(context, attrs, defStyle); this.mContext = context; 
     * init(); } 
     */  
  
    @SuppressLint("NewApi")  
    private void init(Context context) {  
          
        this.mContext = context;  
          
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();  
        float density = metrics.density;  
        newMaxOverScrollY = (int) (density * MAX_OVERSCROLL_Y);  
          
        //
        this.setVerticalScrollBarEnabled(false);  
          
        //没满屏的时候可以滑动
        this.setOverScrollMode(ScrollView.OVER_SCROLL_ALWAYS);  
    }  
  

    @SuppressLint("NewApi")  
    @Override  
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,  
            int scrollY, int scrollRangeX, int scrollRangeY,  
            int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {  
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,  
                scrollRangeX, scrollRangeY, maxOverScrollX, newMaxOverScrollY,  
                isTouchEvent);  
    }  
}  
